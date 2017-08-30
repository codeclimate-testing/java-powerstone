/*
 * Copyright 2004-2005 the original author or authors.
 *
 * Licensed under the LGPL license, Version 2.1 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.gnu.org/copyleft/lesser.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author daquanda(liyingquan@gmail.com)
 * @author kevin(diamond_china@msn.com)
 */
package org.powerstone.ca.service.impl;

import org.powerstone.ca.service.AuthenticateManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.powerstone.ca.dao.AuthenticateDAO;
import org.powerstone.ca.service.UserManager;
import javax.servlet.http.Cookie;
import java.util.Date;
import java.util.GregorianCalendar;
import org.powerstone.ca.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.powerstone.util.StringUtil;
import org.powerstone.ca.model.UserToken;

public class AuthenticateManagerImpl
    implements AuthenticateManager {
  private static Log log = LogFactory.getLog(AuthenticateManagerImpl.class);
  private final static String DEFAULT_CA_TOKEN = "ca_token";
  private final static String DEFAULT_TOKEN_LIFE_CYCLE = "1800000";
  private final static boolean DEFAULT_SET_SERVER_DOMAIN = false;

  private AuthenticateDAO authenticateDAO;
  private UserManager userManager;
  private static String caToken = DEFAULT_CA_TOKEN;
  private static String tokenLifeCycle = DEFAULT_TOKEN_LIFE_CYCLE; //令牌的生命周期
  private static String serverDomain; //用户登陆的域
  private static boolean setServerDomain = DEFAULT_SET_SERVER_DOMAIN; //域是否有效

  /**
   * @param request HttpServletRequest
   * @return String userID (not userName)
   */
  public String getRemoteUser(HttpServletRequest request) {
    Cookie[] cks = request.getCookies();
    if (cks != null) {
      for (int i = 0; i < cks.length; i++) {
        Cookie tok = cks[i];
        if (tok.getName().equals(this.caToken)) {
          UserToken ut =
              authenticateDAO.findByTokenAndIp(tok.getValue(),
                                               request.getRemoteAddr());
          if (ut != null) {
            ut.setLastAccessTime(
                new Long(new GregorianCalendar().getTime().getTime()).toString()
                );

            authenticateDAO.saveUserToken(ut);
            return ut.getUserID().toString();
          }
        }
      }
    }
    return User.ANONYMOUS_USER_ID;
  }

  public boolean authenticate(String userName, String pass,
                              HttpServletRequest request,
                              HttpServletResponse response) {
    Date logOn = new GregorianCalendar().getTime();
    //清除过期数据
    this.clearDataOverdue(logOn.getTime() -
                          new Long(tokenLifeCycle).longValue());

    User user = userManager.findUserByUserName(userName);
    if (user == null) {
      log.warn("log in with a valid userName[" + userName + "]");
      return false;
    }

    String ipAdd = request.getRemoteAddr();
    if (user.getPassword().equals(StringUtil.hash(pass))) {
      //插入登陆数据
      UserToken ut = new UserToken();
      ut.setUserID(user.getId());
      ut.setIpAddress(ipAdd);
      ut.setLogOnTime(logOn.toString());
      ut.setLastAccessTime(new Long(logOn.getTime()).toString());
      String token = this.makeToken(user.getId(), ipAdd, logOn.toString());
      ut.setToken(token);
      authenticateDAO.removeUserData(user.getId(), ipAdd);
      authenticateDAO.saveUserToken(ut);

      //Cookie写入客户端
      Cookie ck = new Cookie(this.caToken, token);
      //ck.setMaxAge(10000000);
      if (this.setServerDomain) {
        ck.setDomain(this.serverDomain);
      }
      ck.setPath("/");
      response.addCookie(ck);
      if (log.isDebugEnabled()) {
        log.debug("user["+userName+"]success log in ===========");
      }
      return true;
    }
    else {
      log.warn("user["+userName+"]log in failed!");
      return false;
    }
  }

  public void leaveLine(HttpServletResponse response) {
    Cookie ck = new Cookie(this.caToken, "###");
    if (this.setServerDomain) {
      ck.setDomain(this.serverDomain);
    }
    ck.setPath("/");
    response.addCookie(ck);
  }

  private String makeToken(Long userID, String ipAdd, String logOnTime) {
    return new Integer( (userID.toString() + ipAdd + logOnTime).hashCode()).
        toString();
  }

  private void clearDataOverdue(long oldTime) {
    authenticateDAO.clearDataOverdue(new Long(oldTime).toString());
  }

  public void setAuthenticateDAO(AuthenticateDAO
                                 authenticateDAO) {
    this.authenticateDAO = authenticateDAO;
  }

  public void setUserManager(UserManager userManager) {
    this.userManager = userManager;
  }

  public void setCaToken(String caToken) {
    this.caToken = caToken;
  }

  public void setServerDomain(String serverDomain) {
    this.serverDomain = serverDomain;
  }

  public void setSetServerDomain(boolean setServerDomain) {
    this.setServerDomain = setServerDomain;
  }

  public void setTokenLifeCycle(String tokenLifeCycle) {
    this.tokenLifeCycle = tokenLifeCycle;
  }

}
