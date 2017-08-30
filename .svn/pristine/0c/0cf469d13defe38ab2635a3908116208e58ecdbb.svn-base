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
package org.powerstone.workflow.dao;

import java.util.*;

import org.powerstone.workflow.model.*;

/**
 * <p>Title: PowerStone</p>
 */
public interface FlowDeployDAO {
  public FlowDeploy getFlowDeploy(Long flowDeployID);

  public void saveFlowDeploy(FlowDeploy flowDeploy);

  public FlowNodeBinding getFlowNodeBinding(Long flowNodeBindingID);

  public void saveFlowNodeBinding(FlowNodeBinding flowNodeBinding);

//  public FlowNodeInputParamBinding getFlowNodeInputParamBinding(Long flowNodeInputParamBindingID);
//
//  public FlowNodeOutputParamBinding getFlowNodeOutputParamBinding(Long flowNodeOutputParamBindingID);
//
//  public void saveFlowNodeOutputParamBinding(FlowNodeOutputParamBinding flowNodeOutputParamBinding);
//
//  public FlowNodeOutputParamEnumeBinding getFlowNodeOutputParamEnumeBinding(Long flowNodeOutputParamEnumeBindingID);

  public void removeFlowDeploy(Long flowDeployID);

  public List findFlowNodeBindingsByDriver(Long flowDriverID);

  public List findFlowNodeBindsByUserPerformer(Long userID);

  public List findFlowNodeBindsByRolePerformer(Long roleID);
}
