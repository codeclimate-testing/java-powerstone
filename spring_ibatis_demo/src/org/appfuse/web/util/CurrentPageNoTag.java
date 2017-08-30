package org.appfuse.web.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CurrentPageNoTag extends TagSupport {
	private static Log log = LogFactory.getLog(CurrentPageNoTag.class);

	public int doStartTag() throws JspException {
		return TagSupport.SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		PageModel pm = (PageModel) pageContext.getRequest().getAttribute(
				PagingController.DEFAULT_PAGE_MODEL_NAME);

		int newPageNo = pm.computeNewPageNo();
		try {
			log.info(newPageNo+"");
			super.pageContext.getOut().write(new Integer(newPageNo).toString());
		} catch (Exception ex) {
			log.error(ex);
		}
		return TagSupport.EVAL_PAGE;
	}
}
