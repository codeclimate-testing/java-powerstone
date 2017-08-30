package org.appfuse.web.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PagingTag extends TagSupport {
	private static Log log = LogFactory.getLog(PagingTag.class);

	private String bordercolor;

	private String bgcolor;

	private String width;

	private String styleClass;

	private String url;

	public String getBgcolor() {
		return bgcolor;
	}

	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}

	public String getBordercolor() {
		return bordercolor;
	}

	public void setBordercolor(String bordercolor) {
		this.bordercolor = bordercolor;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public int doStartTag() throws JspException {
		return TagSupport.SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		url=((HttpServletRequest)pageContext.getRequest()).getContextPath()+url;
		// get the paging model from reuest context
		PageModel pm = (PageModel) pageContext.getRequest().getAttribute(
				PagingController.DEFAULT_PAGE_MODEL_NAME);
		
		int newPageNo = pm.computeNewPageNo();
		int totalPages = pm.computePageCount();
		int[] nearPages = new int[3];
		nearPages[0] = newPageNo - 1;
		nearPages[1] = newPageNo;
		nearPages[2] = newPageNo + 1;

		String styleString = " class=" + styleClass + " ";
		// html bar for paging bar
		String barHtml;
		// format the start of the bar
		barHtml = "<tr bordercolor=" + bordercolor + " bgcolor=" + bgcolor
				+ ">\n";
		barHtml += "<td width=100% colspan=100 align=center>\n";
		// link to first page
		if (totalPages == 0) {
			barHtml += "<span" + styleString
					+ "><font face=webdings>9</font></span>\n";

		} else {
			barHtml += "<span" + styleString + "><a title=首页 href='" + url
					+ "&" + PagingController.TO_FIRST_PARAM_NAME + "=true'"
					+ " class='sblue_'><font face=webdings>9</font></a></span>\n";
		}
		
		String currPage="&"+PagingController.CURR_PAGE_PARAM_NAME+"="+newPageNo;
		
		// link to previous page
		if (nearPages[0] > 0) {
			barHtml += "<span" + styleString + "><a title=上页 href='" + url+currPage
					+ "&" + PagingController.TO_LAST_PARAM_NAME + "=true'"
					+ " class='sblue_'><font face=webdings>7</font></a></span>\n";
		} else {
			barHtml += "<span" + styleString
					+ "><font face=webdings>7</font></span>\n";
		}
		// link to near pages
		for (int i = 0; i < 3; i++) {
			if (nearPages[i] > 0 && nearPages[i] <= totalPages) {
				if (i == 1) {
					barHtml += "<span" + styleString
							+ "><b><font color=#ff0000>" + nearPages[i]
							+ "</font></b></span>\n";
				} else {
					barHtml += "<span" + styleString + "><b><a href='" + url
							+ "&" + PagingController.TO_PAGE_NO_PARAM_NAME
							+ "=" + nearPages[i] + "' class='sblue_'>"
							+ nearPages[i] + "</a></b></span>\n";
				}
			}
		}

		// link to next page
		if (nearPages[2] <= totalPages) {
			barHtml += "<span"
					+ styleString
					+ "><a title=下页 href='"
					+ url
					+ currPage+"&"
					+ PagingController.TO_NEXT_PARAM_NAME + "=true'"
					+ " class='sblue_'><font face=webdings>8</font></a></span>\n";
		} else {
			barHtml += "<span" + styleString
					+ "><font face=webdings>8</font></span>\n";
		}
		
		// link to end page
		if (totalPages == 0) {
			barHtml += "<span" + styleString
					+ "><font face=webdings>:</font></span>\n";
		} else {
			barHtml += "<span" + styleString + "><a title=尾页 href='" + url
					+ "&" + PagingController.TO_END_PARAM_NAME + "=true'"
					+ " class='sblue_'><font face=webdings>:</font></a></span>\n";
		}
		
		// common info on the bar
		barHtml += "<span" + styleString + ">页次：<b>" + newPageNo + "</b>/<b>"
				+ totalPages + "</b>页 每页<b>" + pm.getPageSize() + "</b> 总数<b>"
				+ pm.getTotalRecordsNumber() + "</b></span>\n";
		// Go form
		barHtml += "<span"
				+ styleString
				+ ">转到: <input type=text id='targetPage' maxlength=10 size=3 name=Page class=txt value="
				+ newPageNo + ">\n";
		barHtml += "<input type=button value=Go class=manage_button onclick=javascript:go2page(document.all.targetPage.value,"
				+ totalPages + ")></span>\n";
		// format the end of the bar
		barHtml += "</td>\n";
		barHtml += "</tr>\n";

		// Javascript code for paging bar
		String barJs;
		// set the javascript code for paging bar
		barJs = "<script langage=javascript>\n";
		barJs += "function go2page(page,totalpages){\n";
		barJs += "if(isNaN(page) | page<=0 | page>totalpages){\n";
		barJs += "alert('请输入有效页号！')\n";
		barJs += "}else{\n";
		barJs += "this.location = '" + url + "&" + PagingController.TO_PAGE_NO_PARAM_NAME
		+ "=' + page;\n";
		barJs += "}\n";
		barJs += "}\n";
		barJs += "</script>\n";
		
		log.info(barJs);
		log.info(barHtml);
		// write out
		try {
			pageContext.getOut().println(barJs);
			pageContext.getOut().println(barHtml);
		} catch (IOException ioe) {
			throw new JspException(ioe);
		}

		return EVAL_PAGE;
	}
}