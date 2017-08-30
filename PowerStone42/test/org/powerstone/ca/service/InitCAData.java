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

import org.powerstone.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.powerstone.ca.model.WebModule;
import org.powerstone.ca.model.User;
import org.powerstone.ca.model.Resource;

public class InitCAData
    extends AbstractSpringTestCase {
  protected static Log log = LogFactory.getLog(InitCAData.class);
  protected ResourceManager resourceManager = null;
  protected UserManager userManager = null;

  protected void onSetUpInTransaction() throws Exception {
  }

  public void testCreateWebModule() {
    log.info("----------------------");
    User user;
    for (int i = 1; i < 30; i++) {
      user = new User();
      user.setUserName("admin"+i);
      user.setPassword("admin");
      user.setEmail("auser"+i+"@powerstone.org");
      user.setMemo("auser");
      user.setRealName("auser"+i);
      user.setSex("male");
      userManager.registerUser(user);
    }
    user = new User();
    user.setUserName("admin");
    user.setPassword("admin");
    user.setEmail("daquanda@powerstone.org");
    user.setMemo("administrator");
    user.setRealName("daquanda");
    user.setSex("male");
    userManager.registerUser(user);

    WebModule wm1 = new WebModule();
    wm1.setWebModuleName("系统管理");
    resourceManager.createWebModule(wm1);

    Resource res1 = new Resource(); //角色管理
    res1.setActionURL("/role_manage/");
    res1.setResourceID("function_rolemanage");
    res1.setResourceName("角色管理");
    resourceManager.createResource(res1);
    resourceManager.addResourceToWebModule(res1.getId().toString(),
                                           wm1.getWebModuleID().toString());

    Resource res2 = new Resource(); //用户管理
    res2.setActionURL("/user_manage/");
    res2.setResourceID("function_usermanage");
    res2.setResourceName("用户管理");
    resourceManager.createResource(res2);
    resourceManager.addResourceToWebModule(res2.getId().toString(),
                                           wm1.getWebModuleID().toString());

    Resource res3 = new Resource(); //授权管理
    res3.setActionURL("/priv_manage/");
    res3.setResourceID("function_privmanage");
    res3.setResourceName("授权管理");
    resourceManager.createResource(res3);
    resourceManager.addResourceToWebModule(res3.getId().toString(),
                                           wm1.getWebModuleID().toString());

    Resource res4 = new Resource(); //资源管理
    res4.setActionURL("/resource_manage/");
    res4.setResourceID("function_resourcemanage");
    res4.setResourceName("资源管理");
    resourceManager.createResource(res4);
    resourceManager.addResourceToWebModule(res4.getId().toString(),
                                           wm1.getWebModuleID().toString());

    Resource res6 = new Resource(); //流程管理
    res6.setActionURL("/wf/");
    res6.setResourceID("function_flowmanage");
    res6.setResourceName("流程管理");
    resourceManager.createResource(res6);
    resourceManager.addResourceToWebModule(res6.getId().toString(),
                                           wm1.getWebModuleID().toString());

    userManager.giveCommonFunctionRight(user.getId().toString(),
                                        res1.getId().toString());
    userManager.giveCommonFunctionRight(user.getId().toString(),
                                        res2.getId().toString());
    userManager.giveCommonFunctionRight(user.getId().toString(),
                                        res3.getId().toString());
    userManager.giveCommonFunctionRight(user.getId().toString(),
                                        res4.getId().toString());
    userManager.giveCommonFunctionRight(user.getId().toString(),
                                        res6.getId().toString());

    log.info("----------------------");

    //This will cause the transaction to commit instead of roll back
    //--to populate the database!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    super.setComplete();
  }

  public void setResourceManager(ResourceManager resourceManager) {
    this.resourceManager = resourceManager;
  }

  public void setUserManager(UserManager userManager) {
    this.userManager = userManager;
  }
}
