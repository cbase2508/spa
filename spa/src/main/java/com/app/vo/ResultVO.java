package com.app.vo;

import java.util.List;

public class ResultVO {

	public List<String> list;
	
	public int totalRecords;
	
	public int currentPage;
	
	public int totalPages;
	
	public int recordsPerPage=10;

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		
		totalPages = totalRecords%recordsPerPage>0?totalRecords/recordsPerPage+1:totalRecords/recordsPerPage;
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}
	
	
}
