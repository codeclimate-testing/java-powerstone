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

import java.util.*;

import org.apache.commons.lang.builder.*;
import org.apache.commons.logging.*;

/**
 * @hibernate.class table="CA_WEB_MODULE"
 * <p>Title: PowerStone</p>
 */
public class WebModule
    extends BaseObject {
  private static Log log = LogFactory.getLog(WebModule.class);
  private Long webModuleID = new Long( -1);
  private String webModuleName;
  private String facePage;
  private String status;
  private List resources = new ArrayList();
  private List users = new ArrayList();

  /**
   * @hibernate.id column="PK_WEB_MODULE_ID"
   * 		   unsaved-value="-1"
   *               generator-class="native"
   * @return Long
   */
  public Long getWebModuleID() {
    return webModuleID;
  }

  /**
   * @hibernate.property
   * 		column="VC_WEB_MODULE_NAME"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   *            unique="true"
   * @return String
   */
  public String getWebModuleName() {
    return webModuleName;
  }

  /**
   * @hibernate.property
   * 		column="VC_FACE_PAGE"
   * 		length="255"
   * 		type="string"
   * @return String
   */
  public String getFacePage() {
    return facePage;
  }

  /**
   * @hibernate.property
   * 		column="VC_STATUS"
   * 		length="255"
   * 		type="string"
   * @return String
   */
  public String getStatus() {
    return status;
  }

  /**
   * @hibernate.bag name="resources" cascade="all-delete-orphan" lazy="true" inverse="true"
   * @hibernate.collection-key column="FK_WEB_MODULE_ID"
   * @hibernate.collection-one-to-many class="org.powerstone.ca.model.Resource"
   * @return List
   */
  public List getResources() {
    return resources;
  }

  public void removeResource(Resource resource) {
    if(resource!=null){
      resource.setWebModule(null);
      getResources().remove(resource);
    }else{
      log.warn("atempt to remove null resource from WebModule"+this);
    }
  }

  public void addResource(Resource resource){
    if(resource!=null){
      resource.setWebModule(this);
      getResources().add(resource);
    }else{
      log.warn("atempt to add null resource to WebModule"+this);
    }
  }

  /**
   * @hibernate.bag name="users" cascade="all" lazy="true" inverse="true" table="CA_JOIN_USER_WEBMODULE"
   * @hibernate.collection-key column="FK_WEB_MODULE_ID"
   * @hibernate.collection-many-to-many class="org.powerstone.ca.model.WebModule" column="FK_USER_ID"
   * @return List
   */
  public List getUsers() {
    return users;
  }

  public void setFacePage(String facePage) {
    this.facePage = facePage;
  }

  public void setResources(List resources) {
    this.resources = resources;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setUsers(List users) {
    this.users = users;
  }

  public void setWebModuleID(Long webModuleID) {
    this.webModuleID = webModuleID;
  }

  public void setWebModuleName(String webModuleName) {
    this.webModuleName = webModuleName;
  }

//------------------------------------------------------------------------------
  public boolean equals(Object object) {
    if (! (object instanceof WebModule)) {
      return false;
    }
    WebModule webModule = (WebModule) object;
    return new EqualsBuilder()
        .append(this.getWebModuleName(), webModule.getWebModuleName())
        .append(this.getStatus(), webModule.getStatus())
        .append(this.getFacePage(), webModule.getFacePage()).isEquals();
  }

  public int hashCode() {
    //随机选择两个奇数，每个类不同
    return new HashCodeBuilder(1967893, 1967895).append(
        this.getWebModuleID().toString())
        .append(this.getWebModuleName())
        .append(this.getStatus())
        .append(this.getFacePage()).toHashCode();
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("webModuleID", this.getWebModuleID().toString())
        .append("webModuleName", this.getWebModuleName())
        .append("status", this.getStatus())
        .append("facePage", this.getFacePage()).toString();
  }
}
