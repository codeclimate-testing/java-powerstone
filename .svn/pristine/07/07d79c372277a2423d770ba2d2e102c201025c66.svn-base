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

import org.powerstone.ca.model.UserToken;

public interface AuthenticateDAO {
  //创建一条记录
  public void saveUserToken(UserToken ut);

  //按token，ip查找登陆记录
  public UserToken findByTokenAndIp(String token, String ipAdd);

  //清除过期数据
  public void clearDataOverdue(String timeOverdue);

  //清除过期数据
  public void removeUserData(Long userID, String ipAdd);

//  //按用户，ip，时间查找登陆记录
//  public UserToken findByUsIpOt(Long userID, String ipAdd, String oldTime);
}
