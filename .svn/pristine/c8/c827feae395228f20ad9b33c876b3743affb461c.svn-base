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
package org.powerstone.workflow.dao.hibernate;

import org.powerstone.workflow.dao.FlowMetaFileDAO;
import java.util.List;
import org.powerstone.workflow.model.FlowMetaFile;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>Title: PowerStone</p>
 */

public class FlowMetaFileDAOImpl
    extends HibernateDaoSupport
    implements FlowMetaFileDAO {
  private static Log log = LogFactory.getLog(FlowMetaFileDAOImpl.class);

  public List getAllFlowMetaFiles() {
    return getHibernateTemplate().findByNamedQuery("AllFlowMetaFiles");
  }

  public FlowMetaFile getFlowMetaFile(Long flowFileID) {
    return (FlowMetaFile) getHibernateTemplate().load(FlowMetaFile.class,
        flowFileID);
  }

  public void saveFlowMetaFile(FlowMetaFile flowMetaFile) {
    this.getHibernateTemplate().saveOrUpdate(flowMetaFile);
    getHibernateTemplate().flush();
  }

  public void removeFlowMetaFile(Long flowFileID) {
    FlowMetaFile flowMetaFile =
        (FlowMetaFile) getHibernateTemplate().load(FlowMetaFile.class,
                                                   flowFileID);
    getHibernateTemplate().delete(flowMetaFile);
    getHibernateTemplate().flush();
  }


  public void updatePreviewImage(FlowMetaFile fmFile) {
    throw new java.lang.UnsupportedOperationException(
        "Method updatePreviewImage(FlowMetaFile fmFile) not yet implemented.");
  }

}
