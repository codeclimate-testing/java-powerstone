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
package org.powerstone.ca.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @hibernate.class table="CA_USER_TOKEN"
 * @hibernate.query name="ByTokenAndIp"
 *  query="select ut from UserToken as ut where ut.token = ? and ut.ipAddress = ?"

 * @hibernate.query name="DataOverdue"
 *  query="select ut from UserToken as ut where ut.lastAccessTime < ?"

 * @hibernate.query name="UserData"
 *  query="select ut from UserToken as ut where ut.userID = ? and ut.ipAddress = ?"
 * <p>Title: PowerStone</p>
 */
public class UserToken
    extends BaseObject {
  private Long logID = new Long( -1);
  private String token;
  private Long userID;
  private String ipAddress;
  private String logOnTime;
  private String lastAccessTime;

  /**
   * @hibernate.id column="PK_LOG_ID"
   * 		   unsaved-value="-1"
   *               generator-class="native"
   * @return Long
   */
  public Long getLogID() {
    return logID;
  }

  /**
   * @hibernate.property
   * 		column="VC_TOKEN"
   * 		length="50"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getToken() {
    return token;
  }

  /**
   * @hibernate.property
   * 		column="VC_USER_ID"
   * 		type="long"
   *            not-null="true"
   * @return String
   */
  public Long getUserID() {
    return userID;
  }

  /**
   * @hibernate.property
   * 		column="VC_IP_ADDRESS"
   * 		length="50"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getIpAddress() {
    return ipAddress;
  }

  /**
   * @hibernate.property
   * 		column="VC_LOG_ON_TIME"
   * 		length="50"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getLogOnTime() {
    return logOnTime;
  }

  /**
   * @hibernate.property
   * 		column="VC_LAST_ACCESS_TIME"
   * 		length="50"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getLastAccessTime() {
    return lastAccessTime;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  public void setLastAccessTime(String lastAccessTime) {
    this.lastAccessTime = lastAccessTime;
  }

  public void setLogID(Long logID) {
    this.logID = logID;
  }

  public void setLogOnTime(String logOnTime) {
    this.logOnTime = logOnTime;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public void setUserID(Long userID) {
    this.userID = userID;
  }

//------------------------------------------------------------------------------
  public boolean equals(Object object) {
    if (! (object instanceof UserToken)) {
      return false;
    }
    UserToken userToken = (UserToken) object;
    return new EqualsBuilder()
        .append(this.getUserID().toString(), userToken.getUserID().toString())
        .append(this.getToken(), userToken.getToken())
        .append(this.getIpAddress(), userToken.getIpAddress()).isEquals();
  }

  public int hashCode() {
    //随机选择两个奇数，每个类不同
    return new HashCodeBuilder(1867893, 1867895).append(
        this.getLogID().toString())
        .append(this.getUserID().toString())
        .append(this.getToken())
        .append(this.getIpAddress()).toHashCode();
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getLogID().toString())
        .append("userID", this.getUserID().toString())
        .append("token", this.getToken())
        .append("ipAddress", this.getIpAddress()).toString();
  }
}
