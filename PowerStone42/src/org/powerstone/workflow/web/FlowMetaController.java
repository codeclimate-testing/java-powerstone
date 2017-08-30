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
package org.powerstone.workflow.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.RedirectView;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.IOException;
import org.powerstone.workflow.exception.FlowMetaException;
import org.powerstone.workflow.service.FlowMetaManager;
import org.powerstone.util.ResponseWriter;
import org.powerstone.workflow.model.WorkflowMeta;
import org.powerstone.workflow.model.FlowMetaFile;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.HashMap;

public class FlowMetaController extends MultiActionController {
	private FlowMetaManager flowMetaManager;

	private static Log log = LogFactory.getLog(FlowMetaController.class);

	private static final int IMAGE_SIZE = 120;

	public void setFlowMetaManager(FlowMetaManager flowMetaManager) {
		this.flowMetaManager = flowMetaManager;
	}

	public ModelAndView uploadFlowFile(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile flowFile = multipartRequest.getFile("flow");
		MultipartFile imageFile = multipartRequest.getFile("image");

		try {
			WorkflowMeta wm = flowMetaManager.uploadFlowMetaFile(flowFile
					.getInputStream(), flowFile.getInputStream(), imageFile
					.getInputStream(), new Long(flowFile.getSize()), new Long(
					imageFile.getSize()));

			ResponseWriter.parentLeftReload(response);
			request.getSession().setAttribute("refresh", "refresh");
			return new ModelAndView(new RedirectView(request.getContextPath()
					+ "/wf/see_flow_meta.fm?flowMetaID=" + wm.getFlowMetaID()));
		} catch (IOException ex) {
			log.error(ex);
			request.setAttribute("message", "IOException!");
		} catch (FlowMetaException ex) {
			log.error(ex);
			request.setAttribute("message", ex.getMessage());
		}
		return uploadPage(request, response);
	}

	public ModelAndView updateBTOfFlow(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String flowMetaID = request.getParameter("flowMetaID");
		String btToJoin = request.getParameter("btToJoin");
		flowMetaManager.updateBusinessType(flowMetaID, btToJoin);

		return seeFlowMeta(request, response);
	}

	public ModelAndView seeFlowMeta(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String flowMetaID = request.getParameter("flowMetaID");
		WorkflowMeta wm = flowMetaManager.getWorkflowMeta(flowMetaID);
		HashMap model = new HashMap();
		model.put("workflowMeta", wm);
		model.put("flowDeploies", wm.getFlowDeploies());
		return new ModelAndView("seeFlowMeta", model);
	}

	public ModelAndView previewImageForm(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String flowMetaID = request.getParameter("flowMetaID");
		return new ModelAndView("updatePreviewImage", "flowMetaID", flowMetaID);
	}

	public ModelAndView updatePreviewImage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String flowMetaID = request.getParameter("flowMetaID");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile imageFile = multipartRequest.getFile("image");

		try {
			flowMetaManager.updatePreviewImage(flowMetaID, imageFile
					.getInputStream(), new Long(imageFile.getSize()));
		} catch (IOException ex) {
			log.error(ex);
		} catch (FlowMetaException ex) {
			log.error(ex);
		}

		return new ModelAndView(new RedirectView(request.getContextPath()
				+ "/wf/see_flow_meta.fm?flowMetaID=" + flowMetaID));
	}

	// 看原图
	public ModelAndView previewBig(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String flowMetaID = request.getParameter("flowMetaID");
		WorkflowMeta wm = flowMetaManager.getWorkflowMeta(flowMetaID);
		FlowMetaFile fmFile = flowMetaManager.getFlowMetaFile(wm
				.getFlowFileInUse().getFlowFileID().toString());

		// 生成缩略图
		BufferedImage bis = ImageIO.read(fmFile.getPreviewImageInput());
		if (bis != null) {
			ImageIO.write(bis, "jpeg", response.getOutputStream());
		} else {
			log.warn("流程[" + flowMetaID + "]缺少预览图片！");
		}
		return null;
	}

	public ModelAndView previewFlowMeta(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String flowMetaID = request.getParameter("flowMetaID");
		
		WorkflowMeta wm = flowMetaManager.getWorkflowMeta(flowMetaID);		
		log.info("///////////"+wm.getBusinessType());
		if (log.isDebugEnabled()) {
			log.debug("test hibernate lazy loading[" + wm.getFlowMetaID()+"]");
		}
		
		FlowMetaFile fmFile = flowMetaManager.getFlowMetaFile(wm
				.getFlowFileInUse().getFlowFileID().toString());
		// 生成缩略图
		BufferedImage bis = ImageIO.read(fmFile.getPreviewImageInput());
		if (bis != null) {
			int w = bis.getWidth();
			int h = bis.getHeight();
			int nw = IMAGE_SIZE; // final int IMAGE_SIZE = 120;
			int nh = (nw * h) / w;
			if (nh > IMAGE_SIZE) {
				nh = IMAGE_SIZE;
				nw = (nh * w) / h;
			}
			double sx = (double) nw / w;
			double sy = (double) nh / h;
			AffineTransform transform = new AffineTransform();
			transform.setToScale(sx, sy);
			AffineTransformOp ato = new AffineTransformOp(transform, null);
			BufferedImage bid = new BufferedImage(nw, nh,
					BufferedImage.TYPE_3BYTE_BGR);
			ato.filter(bis, bid);
			ImageIO.write(bid, "jpeg", response.getOutputStream());
		} else {
			log.warn("流程[" + flowMetaID + "]缺少预览图片！");
		}
		// FileCopyUtils.copy(fmFile.getPreviewImageInput(),
		// response.getOutputStream());
		// }

		// FileCopyUtils.copy(fmFile.getPreviewImageInput(),
		// response.getOutputStream());
		return null;
	}

	public ModelAndView removeFlowMeta(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String flowMetaID = request.getParameter("flowMetaID");
		WorkflowMeta wm = flowMetaManager.getWorkflowMeta(flowMetaID);
		if (wm.getFlowDeploies().size() == 0) {
			flowMetaManager.removeWorkflowMeta(flowMetaID);
			request.getSession().setAttribute("message",
					"flowMeta removed successfully.");
			return new ModelAndView("successSubmit");
		} else {
			request.getSession().setAttribute("message",
					"error.flowMeta.with_deploies!");
			return new ModelAndView(new RedirectView(request.getContextPath()
					+ "/wf/see_flow_meta.fm?flowMetaID=" + flowMetaID));
		}
	}

	public ModelAndView uploadPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("uploadFlowmeta");
	}

}
