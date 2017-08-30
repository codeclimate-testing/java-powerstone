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
package org.powerstone.ca.dao;

import org.powerstone.*;
import org.powerstone.ca.model.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AuthenticateDAOTest
    extends AbstractSpringTestCase {
  private static Log log = LogFactory.getLog(AuthenticateDAOTest.class);
  private AuthenticateDAO authenticateDAO = null;
  UserToken ut = null;

  protected void onSetUpInTransaction() throws Exception {
    ut = new UserToken();
    ut.setIpAddress("192.168.0.1");
    ut.setLastAccessTime("123456");
    ut.setLogOnTime("2005-10-10 10:10:10");
    ut.setToken("589ouytreee");
    ut.setUserID(new Long(1));
    authenticateDAO.saveUserToken(ut);
  }

  public void testClearDataOverdue() {
    log.debug(ut);
    this.assertEquals("number of Data", 1,
                      jdbcTemplate.queryForInt(
        "select count(*) from CA_USER_TOKEN"));
    authenticateDAO.clearDataOverdue("123455");
    this.assertEquals("number of Data", 1,
                      jdbcTemplate.queryForInt(
        "select count(*) from CA_USER_TOKEN"));
    authenticateDAO.clearDataOverdue("123457");
    this.assertEquals("number of Data", 0,
                      jdbcTemplate.queryForInt(
        "select count(*) from CA_USER_TOKEN"));
  }

  public void testFindByTokenAndIp() {
    UserToken actualReturn = authenticateDAO.findByTokenAndIp(ut.getToken(),
        ut.getIpAddress());
    assertEquals("FindByTokenAndIp return value", ut, actualReturn);
  }

  public void testRemoveUserData() {
    this.assertEquals("number of UserDatas", 1,
                      jdbcTemplate.queryForInt(
        "select count(*) from CA_USER_TOKEN"));
    authenticateDAO.removeUserData(ut.getUserID(), ut.getIpAddress());
    this.assertEquals("number of UserDatas", 0,
                      jdbcTemplate.queryForInt(
        "select count(*) from CA_USER_TOKEN"));
  }

  public void testSaveUserToken() {
    ut.setLastAccessTime("14789");
    authenticateDAO.saveUserToken(ut);
    this.assertEquals("number of groups", "14789",
                      jdbcTemplate.queryForObject(
        "select VC_LAST_ACCESS_TIME from CA_USER_TOKEN", String.class));
    log.debug(ut);
  }

  public void setAuthenticateDAO(AuthenticateDAO authenticateDAO) {
    this.authenticateDAO = authenticateDAO;
  }
}
