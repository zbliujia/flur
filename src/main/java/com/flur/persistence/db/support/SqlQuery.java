package com.flur.persistence.db.support;



/**
 * sql查询对象
 * @author WX
 *
 */
public class SqlQuery {
	
	private SqlCriteria criteria;
	private Integer skip = null;
	private Integer limit = null;
	private SqlSort sort = null;
	private String groupBy = null;
	
	public SqlQuery(){}
	
	public SqlQuery(SqlCriteria sqlCriteria) {
		super();
		this.criteria = sqlCriteria;
	}
	
	public SqlCriteria getCriteria() {
		return criteria;
	}
	
	public String getCriteriaString() {
		if(this.criteria == null){
			return "";
		}else{
			return criteria.toString();
		}
	}
	
	public SqlQuery limit(int limit){
		this.limit = limit;
		return this;
	}
	
	public SqlQuery skip(int skip){
		this.skip = skip;
		return this;
	}
	
	public SqlQuery groupBy(String field){
		this.groupBy = field;
		return this;
	}
	
	/**
	 * 排序
	 * @return
	 */
	public SqlSort sort(){
		if(this.sort == null){
			this.sort = new SqlSort();
		}
		return this.sort;
	}
	
	public SqlSort getSort(){
		return this.sort;
	}
	
	public String getSortString() {
		if(this.sort == null){
			return "";
		}else{
			return this.sort.toString();
		}
	}

	public Integer getSkip() {
		return skip;
	}

	public Integer getLimit() {
		return limit;
	}

	public String getGroupBy() {
		return groupBy;
	}

}
