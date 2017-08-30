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
  //��������ģ��
  public String[][] getWebModules();

  //��������ģ��
  public List getAllModules();

  //�½�һ��ģ��
  public WebModule createWebModule(WebModule webModule);

  //�½�һ����Դ
  public Resource createResource(Resource resource);

  //ɾ��һ��ģ��
  public void removeWebModule(String webModuleID);

  //ɾ��һ����Դ
  public void removeResource(String rsID);

  //����һ��ģ��
  public WebModule findWebModule(String webModuleID);

  //����������һ����Դ
  public Resource findResource(String rsID);

  //��resourceID����Դ
  public Resource findResourceByResourceID(String resourceID);

  //�����ֲ�����Դ
  public Resource findResourceByName(String resourceName);

  //����ģ��
  public WebModule updateWebModule(WebModule webModule);

  //������Դ
  public Resource updateResource(Resource resource);

  //����������Դ
  public List getAllResources();

  public void addResourceToWebModule(String rsID, String webModuleID);
}
