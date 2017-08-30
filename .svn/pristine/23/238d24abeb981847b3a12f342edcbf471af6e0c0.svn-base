package org.appfuse.web.util;

public class PageModel {

	private int pageSize;

	private String currPageNo;

	private String pageTo = null;

	private int totalRecordsNumber;

	boolean beEnd = false;

	boolean beFirst = false;

	boolean beLast = false;

	boolean beNext = false;

	public String getCurrPageNo() {
		return currPageNo;
	}

	public void setCurrPageNo(String currPageNo) {
		this.currPageNo = currPageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecordsNumber() {
		return totalRecordsNumber;
	}

	public void setTotalRecordsNumber(int totalNumber) {
		this.totalRecordsNumber = totalNumber;
	}

	public int computeRecordsBeginNo() {
		return (computeNewPageNo() - 1) * pageSize;
	}

	public int computeNewPageNo() {
		Integer pageNum = (currPageNo != null ? new Integer(currPageNo) : new Integer("0"));
		int newCurrPageNo = 0;
		int pageCount = computePageCount();

		if (pageTo != null) {
			newCurrPageNo = new Integer(pageTo).intValue();
		} else if (!beFirst && !beLast && !beNext && !beEnd) {
			newCurrPageNo = 1;
		} else if (beFirst) {
			newCurrPageNo = 1;
		} else if (beLast) {
			newCurrPageNo = pageNum.intValue() - 1;
		} else if (beNext) {
			newCurrPageNo = pageNum.intValue() + 1;
		} else if (beEnd) {
			newCurrPageNo = pageCount;
		}
		if (newCurrPageNo < 1) {
			newCurrPageNo = 1;
		}
		if (newCurrPageNo > pageCount) {
			newCurrPageNo = pageCount;
		}
		return newCurrPageNo;
	}

	public int computePageCount() {
		int pageCount = 0;
		if (totalRecordsNumber % pageSize == 0) {
			pageCount = totalRecordsNumber / pageSize;
		} else {
			pageCount = totalRecordsNumber / pageSize + 1;
		}
		return pageCount;
	}

	public void setBeEnd(boolean beEnd) {
		this.beEnd = beEnd;
	}

	public void setBeFirst(boolean beFirst) {
		this.beFirst = beFirst;
	}

	public void setBeLast(boolean beLast) {
		this.beLast = beLast;
	}

	public void setBeNext(boolean beNext) {
		this.beNext = beNext;
	}

	public void setPageTo(String pageTo) {
		this.pageTo = pageTo;
	}
	public void clear(){
		this.setBeEnd(false);
		this.setBeFirst(false);
		this.setBeNext(false);
		this.setBeLast(false);
	}
}
