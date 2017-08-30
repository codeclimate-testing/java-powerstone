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

import java.util.*;

import org.powerstone.ca.model.*;

public interface ResourceDAO {
  //初始化，查找所有模块
  public List findAllWebModules();

  public List findAllResources();

  //新建一个模块
  public void saveWebModule(WebModule webModule);

  //新建一个资源
  public void saveResource(Resource resource);

  //删除一个模块
  public void removeWebModule(Long webModuleID);

  //按ID查找模块
  public WebModule findWebModuleByPrimaryKey(Long webModuleID);

  //按ID查找资源
  public Resource findResourceByPrimaryKey(Long rsID);

  //按ResourceID查找资源
  public Resource findResourceByResourceID(String resourceID);
  //按ResourceName查找资源
  public Resource findResourceByName(String resourceName);

}
