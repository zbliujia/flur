package com.flur.common.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flur.persistence.db.support.SqlQuery;

public class PageInfo<T> implements Serializable {

	private static final long serialVersionUID = 4499433851865302277L;
	
	//符合条件元素总数
	private long elementsCount;
	
	//开始行的索引
	private int startIndex;

	//分页元素大小
	private int pageSize;

	//当前页索引
	private int curPage;
	
	//页数统计
	private long pageCount;
	
	//分页元素集合
	private List<T> elements;
	
	private SqlQuery sqlQuery;
	
	private Class<T> entityClass;
	
	/** constructor */
	public PageInfo() {
		this.curPage = 0;
		this.pageSize = 10;
		this.startIndex = 0;
		this.sqlQuery = new SqlQuery();
	}
	
	public PageInfo(int curPage, int pageSize, SqlQuery sqlQuery, Class<T> entityClass) {
		this.curPage = curPage;
		this.pageSize = pageSize;
		sqlQuery.limit(pageSize);
		if(curPage < 1){
			curPage = 1;
		}
		if(pageSize < 1){
			pageSize = 10;
		}
		this.startIndex = (curPage - 1) * pageSize;
		sqlQuery.skip(startIndex);
		this.sqlQuery = sqlQuery;
		this.entityClass = entityClass;
	}
	
	/**
	 * 转成Grid所需的对象
	 * @return
	 */
	public Map<String, Object> toApiJson(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("elements", this.elements);
		map.put("page", this.curPage);
		map.put("pageSize", this.pageSize);
		map.put("pageCount", this.pageCount);
		map.put("elementsCount", this.elementsCount);
		return map;
	}
	
	/**
	 * 转成Grid所需的对象
	 * @return
	 */
	public Map<String, Object> toGridJson(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", this.elements);
		map.put("page", this.curPage);
		map.put("pageSize", this.pageSize);
		map.put("total", this.pageCount);
		map.put("records", this.elementsCount);
		return map;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}
	
	public List<T> getElements() {
		return elements;
	}

	public void setElements(List<T> elements) {
		this.elements = elements;
	}
	
	public long getElementsCount() {
		return elementsCount;
	}

	public void setElementsCount(long elementsCount) {
		this.elementsCount = elementsCount;
	}
	
	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	
	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public SqlQuery getSqlQuery() {
		return sqlQuery;
	}

	public void setSqlQuery(SqlQuery sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

}
