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
package org.powerstone.ca.service;

import java.util.*;

import org.powerstone.ca.model.*;

public interface ResourceManager {
  //查找所有模块
  public String[][] getWebModules();

  //查找所有模块
  public List getAllModules();

  //新建一个模块
  public WebModule createWebModule(WebModule webModule);

  //新建一个资源
  public Resource createResource(Resource resource);

  //删除一个模块
  public void removeWebModule(String webModuleID);

  //删除一个资源
  public void removeResource(String rsID);

  //查找一个模块
  public WebModule findWebModule(String webModuleID);

  //按主键查找一个资源
  public Resource findResource(String rsID);

  //按resourceID找资源
  public Resource findResourceByResourceID(String resourceID);

  //按名字查找资源
  public Resource findResourceByName(String resourceName);

  //更新模块
  public WebModule updateWebModule(WebModule webModule);

  //更新资源
  public Resource updateResource(Resource resource);

  //查找所有资源
  public List getAllResources();

  public void addResourceToWebModule(String rsID, String webModuleID);
}
