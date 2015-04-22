package kr.ckent.common.vo;

import kr.ckent.common.base.BaseObject;

public class ComBaseVO extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8706459066649122248L;
	
	
	private String searchCondition="";
	private String searchKeyword="";
	private int pageIdx = 1;
	private int listCnt = 10;
	private int startIdx = 0;


	
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public int getPageIdx() {
		return pageIdx;
	}
	public void setPageIdx(int pageIdx) {
		this.pageIdx = pageIdx;
	}
	public int getListCnt() {
		return listCnt;
	}
	public void setListCnt(int listCnt) {
		this.listCnt = listCnt;
	}
	public int getStartIdx() {
		return startIdx;
	}
	public void setStartIdx(int startIdx) {
		this.startIdx = startIdx;
	}

	
	
}
