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

import org.powerstone.workflow.exception.ExceptionMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**工作流进程的状态数据：变量名-值
 * @hibernate.class table="WF_FLOW_PROC_RELATIVE_DATA"
 */

public class FlowProcRelativeData
    extends BaseObject {
  private static Log log = LogFactory.getLog(FlowProcRelativeData.class);

  private Long id = new Long( -1);
  private FlowProc flowProc;
  private FlowNodeOutputParamBinding flowNodeOutputParamBinding;
  private String driverParamValue;

  /**
   * @hibernate.id column="PK_FLOW_PROC_DATA_ID"
   * 		   unsaved-value="-1"
   *               generator-class="native"
   * @return Long
   */
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @hibernate.many-to-one
   * column="FK_FLOW_PROC_ID"
   * class="org.powerstone.workflow.model.FlowProc"
   * @return FlowProc
   */
  public FlowProc getFlowProc() {
    return flowProc;
  }

  public void setFlowProc(FlowProc flowProc) {
    this.flowProc = flowProc;
  }

  /**
   * @hibernate.many-to-one
   * column="FKFLOWNODEOUTPUTPARAMBINDINGID"
   * class="org.powerstone.workflow.model.FlowNodeOutputParamBinding"
   * @return FlowNodeOutputParamBinding
   */
  public FlowNodeOutputParamBinding getFlowNodeOutputParamBinding() {
    return flowNodeOutputParamBinding;
  }

  public void setFlowNodeOutputParamBinding(FlowNodeOutputParamBinding
                                            flowNodeOutputParamBinding) {
    this.flowNodeOutputParamBinding = flowNodeOutputParamBinding;
  }

  /**
   * @hibernate.property
   * 		column="VC_DRIVER_PARAM_VALUE"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getDriverParamValue() {
    return driverParamValue;
  }

  public void setDriverParamValue(String driverParamValue) {
    this.driverParamValue = driverParamValue;
  }

  /**
   * 根据驱动输出参数的值，查找对应的节点输出参数值的枚举
   * 如果输出参数没有枚举，返回驱动输出参数的值，
   * 如果输出参数有枚举，但不能和输出参数值匹配，抛出异常FlowProcException
   * @return String
   */
  public String getCorrespondingNodeParamValue() {
    if (getFlowNodeOutputParamBinding().getFlowNodeOutputParamEnumeBindings().
        size() > 0) {
      FlowNodeOutputParamEnumeBinding nodeOutputParamEnumeBinding =
          getFlowNodeOutputParamBinding().
          findNodeOutputParamEnumeBindingByNodeParamEnume(getDriverParamValue());
      if (nodeOutputParamEnumeBinding != null) {
        return nodeOutputParamEnumeBinding.getNodeOutputParamEnume();
      }
      else {
        log.warn(ExceptionMessage.ERROR_FLOWPROC_UN_MATCHED_OUTPUT_PARAM_VALUE +
                 "[" + getDriverParamValue() + "]"+this);
        return getDriverParamValue();
      }
    }
    else {
      return this.getDriverParamValue();
    }
  }

//------------------------------------------------------------------------------
  public boolean equals(Object object) {
    if (! (object instanceof FlowProcRelativeData)) {
      return false;
    }
    FlowProcRelativeData fpr = (FlowProcRelativeData) object;
    return new EqualsBuilder().
        append(this.getId().toString(), fpr.getId().toString())
        .append(this.getDriverParamValue(), fpr.getDriverParamValue())
        .isEquals();
  }

  public int hashCode() { //随机选择两个奇数，每个类不同
    return new HashCodeBuilder(296335803, 297569255).append(
        this.getId().toString()).append(this.getDriverParamValue())
        .toHashCode();
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId().toString())
        .append("driverParamValue", this.getDriverParamValue())
        .append("id", this.getFlowNodeOutputParamBinding())
        .toString();
  }
}
