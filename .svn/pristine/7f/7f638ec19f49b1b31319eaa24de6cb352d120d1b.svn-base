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
package org.powerstone.workflow.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**工作流驱动的注册信息
 * @hibernate.class table="WF_DRIVER"
 * @hibernate.query name="AllWorkflowDrivers"
 * query=" from WorkflowDriver wd order by wd.flowDriverID asc"
 * @hibernate.query name="FlowDriversByContextPath"
 * query=" from WorkflowDriver wd where wd.contextPath = ? "
 * @hibernate.query name="DriverByReadDO"
 * query=" from WorkflowDriver wd where wd.contextPath = ? and wd.readURL = ? "
 * @hibernate.query name="DriverByWriteDO"
 * query=" from WorkflowDriver wd where wd.contextPath = ? and wd.writeURL = ? "
 * @hibernate.query name="AllDriverContextPath"
 * query=" select distinct wd.contextPath from WorkflowDriver wd "
 */

public class WorkflowDriver
    extends BaseObject {
  private static Log log = LogFactory.getLog(WorkflowDriver.class);
  private Long flowDriverID = new Long( -1);
  private String flowDriverName;
  private String memo;
  private String readURL;
  private String writeURL;
  private String contextPath;
  private java.util.List wfDriverOutputParams = new ArrayList();
  private java.util.List wfDriverInputParams = new ArrayList();

  /**
   * @hibernate.id column="PK_FLOW_DRIVER_ID"
   * 		   unsaved-value="-1"
   *               generator-class="native"
   * @return Long
   */
  public Long getFlowDriverID() {
    return flowDriverID;
  }

  public void setFlowDriverID(Long flowDriverID) {
    this.flowDriverID = flowDriverID;
  }

  /**
   * @hibernate.property
   * 		column="VC_FLOW_DRIVER_NAME"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getFlowDriverName() {
    return flowDriverName;
  }

  public void setFlowDriverName(String flowDriverName) {
    this.flowDriverName = flowDriverName;
  }

  /**
   * @hibernate.property
   * 		column="VC_MEMO"
   * 		length="255"
   * 		type="string"
   * @return String
   */
  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  /**
   * @hibernate.property
   * 		column="VC_READ_URL"
   * 		length="255"
   * 		type="string"
   * @return String
   */
  public String getReadURL() {
    return readURL;
  }

  public void setReadURL(String readURL) {
    this.readURL = readURL;
  }

  /**
   * @hibernate.property
   * 		column="VC_WRITE_URL"
   * 		length="255"
   * 		type="string"
   * @return String
   */
  public String getWriteURL() {
    return writeURL;
  }

  public void setWriteURL(String writeURL) {
    this.writeURL = writeURL;
  }

  /**
   * @hibernate.property
   * 		column="VC_CONTEXT_PATH"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getContextPath() {
    return contextPath;
  }

  public void setContextPath(String contextPath) {
    this.contextPath = contextPath;
  }

  /**
   * @hibernate.bag name="wfDriverOutputParams"
   * cascade="all-delete-orphan"
   * lazy="true"
   * inverse="true"
   * @hibernate.collection-key
   * column="FK_FLOW_DRIVER_ID"
   * @hibernate.collection-one-to-many
   * class="org.powerstone.workflow.model.WFDriverOutputParam"
   * @return List
   */
  public java.util.List getWfDriverOutputParams() {
    return wfDriverOutputParams;
  }

  public void setWfDriverOutputParams(java.util.List wfDriverOutputParams) {
    this.wfDriverOutputParams = wfDriverOutputParams;
  }

  /**
   * @hibernate.bag name="wfDriverInputParams"
   * cascade="all-delete-orphan"
   * lazy="true"
   * inverse="true"
   * @hibernate.collection-key
   * column="FK_FLOW_DRIVER_ID"
   * @hibernate.collection-one-to-many
   * class="org.powerstone.workflow.model.WFDriverInputParam"
   * @return List
   */
  public List getWfDriverInputParams() {
    return wfDriverInputParams;
  }

  public void setWfDriverInputParams(List wfDriverInputParams) {
    this.wfDriverInputParams = wfDriverInputParams;
  }

  public void addOutputParam(WFDriverOutputParam wdOutParam) {
    this.getWfDriverOutputParams().add(wdOutParam);
    wdOutParam.setWorkflowDriver(this);
  }

  public void removeOutputParam(WFDriverOutputParam wdOutParam) {
    this.getWfDriverOutputParams().remove(wdOutParam);
    wdOutParam.setWorkflowDriver(null);
  }

  public void addInputParam(WFDriverInputParam wdInParam) {
    this.getWfDriverInputParams().add(wdInParam);
    wdInParam.setWorkflowDriver(this);
  }

  public void removeInputParam(WFDriverInputParam wdInParam) {
    this.getWfDriverInputParams().remove(wdInParam);
    wdInParam.setWorkflowDriver(null);
  }

  public void removeAllParams() {
    if (getWfDriverInputParams().size() > 0) {
      for (Iterator it = getWfDriverInputParams().iterator(); it.hasNext(); ) {
        WFDriverInputParam para = (WFDriverInputParam) it.next();
        para.setWorkflowDriver(null);
      }
      getWfDriverInputParams().clear();
    }
    if (getWfDriverOutputParams().size() > 0) {
      for (Iterator it = getWfDriverOutputParams().iterator(); it.hasNext(); ) {
        WFDriverOutputParam para = (WFDriverOutputParam) it.next();
        para.setWorkflowDriver(null);
      }
      getWfDriverOutputParams().clear();
    }
  }

//------------------------------------------------------------------------------
  public boolean equals(Object object) {
    if (! (object instanceof WorkflowDriver)) {
      return false;
    }

    WorkflowDriver fd = (WorkflowDriver) object;
    //log.debug("私有变量["+fd.contextPath+"+"+this.contextPath+"]");
    boolean result = new EqualsBuilder().
        append(this.getFlowDriverID().toString(), fd.getFlowDriverID().toString())
        .append(this.getContextPath(), fd.getContextPath())
        .append(this.getFlowDriverName(), fd.getFlowDriverName())
        .append(this.getMemo(), fd.getMemo())
        .append(this.getWriteURL(), fd.getWriteURL())
        .isEquals();
//    if (log.isDebugEnabled()) {
//      log.debug("[" + this +"]equals[" + object + "][" + result + "]");
//    }
    return result;
  }

  public int hashCode() {
    //随机选择两个奇数，每个类不同
    return new HashCodeBuilder(1856335803, 187569255)
        .append(this.getFlowDriverID().toString())
        .append(this.getContextPath())
        .append(this.getFlowDriverName())
        .append(this.getMemo())
        .append(this.getWriteURL())
        .toHashCode();
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("flowDriverID", this.getFlowDriverID().toString())
        .append("contextPath", this.getContextPath())
        .append("flowDriverName", this.getFlowDriverName())
        .append("memo", this.getMemo())
        .append("writeURL", this.getWriteURL())
        .toString();
  }

}
