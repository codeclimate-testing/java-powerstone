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
 * @hibernate.class table="CA_RESOURCE"
 * @hibernate.query name="AllResources" query="select rs from Resource as rs"

 * @hibernate.query name="AllWebModules" query="select wm from WebModule as wm"

 * @hibernate.query name="ResourceByResourceID"
 *  query="select rs from Resource as rs where rs.resourceID=?"

 * @hibernate.query name="ResourceByName"
 *  query="select rs from Resource as rs where rs.resourceName=?"

 * <p>Title: PowerStone</p>
 */
public class Resource
    extends BaseObject {
  private Long id = new Long( -1);
  private String resourceID;
  private String resourceName;
  private String actionURL;
  private WebModule webModule;

  /**
   * @hibernate.id column="PK_ID"
   * 		   unsaved-value="-1"
   *               generator-class="native"
   * @return Long
   */
  public Long getId() {
    return id;
  }

  /**
   * @hibernate.property
   * 		column="VC_RESOURCE_ID"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   *            unique="true"
   * @return String
   */
  public String getResourceID() {
    return resourceID;
  }

  /**
   * @hibernate.property
   * 		column="VC_RESOURCE_NAME"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   *            unique="true"
   * @return String
   */
  public String getResourceName() {
    return resourceName;
  }

  /**
   * @hibernate.property
   * 		column="VC_ACTION_URL"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getActionURL() {
    return actionURL;
  }

  /**
   * @hibernate.many-to-one
   * column="FK_WEB_MODULE_ID"
   * class="org.powerstone.ca.model.WebModule"
   * @return WebModule
   */
  public WebModule getWebModule() {
    return webModule;
  }

  public void setActionURL(String actionURL) {
    this.actionURL = actionURL;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setResourceID(String resourceID) {
    this.resourceID = resourceID;
  }

  public void setResourceName(String resourceName) {
    this.resourceName = resourceName;
  }

  public void setWebModule(WebModule webModule) {
    this.webModule = webModule;
  }

//------------------------------------------------------------------------------
  public boolean equals(Object object) {
    if (! (object instanceof Resource)) {
      return false;
    }
    Resource resource = (Resource) object;
    return new EqualsBuilder()
        .append(this.getResourceID(), resource.getResourceID())
        .append(this.getResourceName(), resource.getResourceName())
        .append(this.getWebModule(), resource.getWebModule()).isEquals();
  }

  public int hashCode() {
    //随机选择两个奇数，每个类不同
    return new HashCodeBuilder(1367893, 1367895).append(
        this.getId().toString())
        .append(this.getResourceID())
        .append(this.getResourceName()).toHashCode();
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId().toString())
        .append("resourceID", this.getResourceID())
        .append("resourceName", this.getResourceName())
        .append("webModule", this.getWebModule()).toString();
  }
}
