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
package org.powerstone.workflow.dao.jdbc;

import org.powerstone.workflow.dao.FlowMetaFileDAO;
import java.util.List;
import org.powerstone.workflow.model.FlowMetaFile;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.support.
    AbstractLobStreamingResultSetExtractor;
import org.springframework.jdbc.LobRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.jdbc.core.support.
    AbstractLobCreatingPreparedStatementCallback;
import java.sql.PreparedStatement;
import org.springframework.jdbc.support.lob.LobCreator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>Title: PowerStone</p>
 */

public class FlowMetaFileDAOJDBC
    extends JdbcDaoSupport
    implements FlowMetaFileDAO {
  private LobHandler lobHandler;
  private static Log log = LogFactory.getLog(FlowMetaFileDAOJDBC.class);

  /**
   * Set the LobHandler to use for BLOB/CLOB access.
   * Could use a DefaultLobHandler instance as default,
   * but relies on a specified LobHandler here.
   * @see org.springframework.jdbc.support.lob.DefaultLobHandler
   */
  public void setLobHandler(LobHandler lobHandler) {
    this.lobHandler = lobHandler;
  }

  public List getAllFlowMetaFiles() {
    /**@todo Implement this org.powerstone.workflow.dao.FlowMetaFileDAO method*/
    throw new java.lang.UnsupportedOperationException(
        "Method getAllFlowMetaFiles() not yet implemented.");
  }

  public FlowMetaFile getFlowMetaFile(final Long flowFileID) {
    final FlowMetaFile fmFile = new FlowMetaFile();
    fmFile.setFlowFileID(flowFileID);

    getJdbcTemplate().query(
        " SELECT VC_WORKFLOW_FILE,VC_PREVIEW_IMAGE FROM WF_FLOW_META_FILE_STORE WHERE PK_FLOW_FILE_ID=? ",
        new Object[] {flowFileID}
        ,
        new AbstractLobStreamingResultSetExtractor() {
      protected void handleNoRowFound() throws LobRetrievalFailureException {
        throw new IncorrectResultSizeDataAccessException(
            "WF_FLOW_META_FILE_STORE row with id '" + flowFileID
            + "' not found in database", 1, 0);
      }

      public void streamData(ResultSet rs) throws SQLException, IOException {
        fmFile.setWorkflowFileInput(lobHandler.getBlobAsBinaryStream(rs, 1));
        fmFile.setPreviewImageInput(lobHandler.getBlobAsBinaryStream(rs, 2));
      }
    }
    );

    return fmFile;
  }

  public void saveFlowMetaFile(final FlowMetaFile flowMetaFile) {
    try {
      this.getFlowMetaFile(flowMetaFile.getFlowFileID());
//      this.removeFlowMetaFile(flowMetaFile.getFlowFileID());
      getJdbcTemplate().execute(
          "UPDATE WF_FLOW_META_FILE_STORE SET VC_PREVIEW_IMAGE = ? , VC_WORKFLOW_FILE = ? WHERE PK_FLOW_FILE_ID = ? ",
          new AbstractLobCreatingPreparedStatementCallback(this.lobHandler) {
        protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws
            SQLException {
          lobCreator.setBlobAsBinaryStream(ps, 1,
                                           flowMetaFile.getPreviewImageInput(),
                                           flowMetaFile.getPreviewImageSize().
                                           intValue());
          lobCreator.setBlobAsBinaryStream(ps, 2,
                                           flowMetaFile.getWorkflowFileInput(),
                                           flowMetaFile.getWorkflowFileSize().
                                           intValue());
          ps.setInt(3, flowMetaFile.getFlowFileID().intValue());
        }
      }
      );
    }
    catch (IncorrectResultSizeDataAccessException ie) {
//      if (log.isDebugEnabled()) {
//        log.debug(ie);
//      }

      getJdbcTemplate().execute(
          "INSERT INTO WF_FLOW_META_FILE_STORE (PK_FLOW_FILE_ID, VC_PREVIEW_IMAGE, VC_WORKFLOW_FILE) VALUES (?, ?, ?)",
          new AbstractLobCreatingPreparedStatementCallback(this.lobHandler) {
        protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws
            SQLException {
          ps.setInt(1, flowMetaFile.getFlowFileID().intValue());
          lobCreator.setBlobAsBinaryStream(ps, 2,
                                           flowMetaFile.getPreviewImageInput(),
                                           flowMetaFile.getPreviewImageSize().
                                           intValue());
          lobCreator.setBlobAsBinaryStream(ps, 3,
                                           flowMetaFile.getWorkflowFileInput(),
                                           flowMetaFile.getWorkflowFileSize().
                                           intValue());
        }
      }
      );
    }

  }

  public void removeFlowMetaFile(Long flowFileID) {
    this.getFlowMetaFile(flowFileID);
    getJdbcTemplate().update(
        " DELETE FROM WF_FLOW_META_FILE_STORE where PK_FLOW_FILE_ID = " +
        flowFileID);
  }

  public void updatePreviewImage(final FlowMetaFile fmFile) {
    this.getFlowMetaFile(fmFile.getFlowFileID());

    getJdbcTemplate().execute(
        "UPDATE WF_FLOW_META_FILE_STORE SET VC_PREVIEW_IMAGE = ? WHERE PK_FLOW_FILE_ID = ? ",
        new AbstractLobCreatingPreparedStatementCallback(this.lobHandler) {
      protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws
          SQLException {
        lobCreator.setBlobAsBinaryStream(ps, 1,
                                         fmFile.getPreviewImageInput(),
                                         fmFile.getPreviewImageSize().
                                         intValue());

        ps.setInt(2, fmFile.getFlowFileID().intValue());
      }
    }
    );

  }
}
