package com.flur.persistence.db.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * sql查询条件构建对象
 * @author WX
 *
 */
public class SqlCriteria {
	
	private StringBuffer sqlBuffer;
	List<SqlCriteria> orCriterias = null;
	private List<Object> statementParams = new ArrayList<Object>();
	
	public SqlCriteria(String field){
		this.sqlBuffer = new StringBuffer();
		sqlBuffer.append(field);
	}
	
	public static SqlCriteria where(String field){
		return new SqlCriteria(field);
	}
	
	/**
	 * sql等于条件
	 * @param value
	 * @return
	 */
	public SqlCriteria is(Object value){
		if(value == null){
			sqlBuffer.append(" is null");
		}else{
			sqlBuffer.append(" = ?");
			this.statementParams.add(value);
		}
		return this;
	}
	
	/**
	 * sql不等于条件
	 * @param value
	 * @return
	 */
	public SqlCriteria ne(Object value){
		sqlBuffer.append(" != ?");
		this.statementParams.add(value);
		return this;
	}
	
	/**
	 * sql and操作
	 * @param field
	 * @return
	 */
	public SqlCriteria and(String field){
		sqlBuffer.append(" and ");
		sqlBuffer.append(field);
		return this;
	}
	
	/**
	 * sql 大于条件
	 * @param value
	 * @return
	 */
	public SqlCriteria gt(Object value){
		sqlBuffer.append(" > ?");
		this.statementParams.add(value);
		return this;
	}
	
	/**
	 * sql 大于等于条件
	 * @param value
	 * @return
	 */
	public SqlCriteria gte(Object value){
		sqlBuffer.append(" >= ?");
		this.statementParams.add(value);
		return this;
	}
	
	/**
	 * sql 小于条件
	 * @param value
	 * @return
	 */
	public SqlCriteria lt(Object value){
		sqlBuffer.append(" < ?");
		this.statementParams.add(value);
		return this;
	}
	
	/**
	 * sql 小于等于条件
	 * @param value
	 * @return
	 */
	public SqlCriteria lte(Object value){
		sqlBuffer.append(" <= ?");
		this.statementParams.add(value);
		return this;
	}
	
	/**
	 * sql like操作，需要添加%
	 * @param value
	 * @return
	 */
	public SqlCriteria like(String value){
		String str = value.replace("'", "");
		sqlBuffer.append(" like '");
		sqlBuffer.append(str);
		sqlBuffer.append("'");
		return this;
	}
	
	/**
	 * sql like操作，需要添加%
	 * @param value
	 * @return
	 */
	public SqlCriteria notlike(String value){
		String str = value.replace("'", "");
		sqlBuffer.append(" not like '");
		sqlBuffer.append(str);
		sqlBuffer.append("'");
		return this;
	}
	
	public SqlCriteria in(Collection<?> items){
		sqlBuffer.append(" in (");
		Iterator<?> ite = items.iterator();
		int index = 0;
		while(ite.hasNext()){
			Object item = ite.next();
			if(index != 0){
				sqlBuffer.append(", ");
			}
			sqlBuffer.append("?");
			this.statementParams.add(item);
			index++;
		}
		sqlBuffer.append(")");
		return this;
	}
	
	public SqlCriteria in(Object[] items){
		List<Object> itemList = new ArrayList<Object>();
		for(int i = 0; i < items.length; i++){
			Object item = items[i];
			itemList.add(item);
		}
		return this.in(itemList);
	}
	
	/**
	 * not In
	 * @param items
	 * @return
	 */
	public SqlCriteria notIn(Collection<?> items){
		sqlBuffer.append(" not in (");
		Iterator<?> ite = items.iterator();
		int index = 0;
		while(ite.hasNext()){
			Object item = ite.next();
			if(index != 0){
				sqlBuffer.append(", ");
			}
			sqlBuffer.append("?");
			this.statementParams.add(item);
			index++;
		}
		sqlBuffer.append(")");
		return this;
	}
	
	
	/**
	 * not In
	 * @param items
	 * @return
	 */
	public SqlCriteria notIn(Object[] items){
		List<Object> itemList = new ArrayList<Object>();
		for(int i = 0; i < items.length; i++){
			Object item = items[i];
			itemList.add(item);
		}
		return this.notIn(itemList);
	}
	
	/**
	 * sql or操作
	 * @param criterias 多个SqlCriteria条件对象
	 * @return
	 */
	public SqlCriteria or(SqlCriteria... criterias){
		if(this.orCriterias == null){
			this.orCriterias = new ArrayList<SqlCriteria>();
		}
		for(int i = 0; i < criterias.length; i++){
			SqlCriteria criteria = criterias[i];
			this.orCriterias.add(criteria);
		}
		return this;
	}
	
	/**
	 * 重写toString方法，获取格式化之后的sql
	 */
	@Override
	public String toString(){
		if(this.orCriterias == null){
			return this.sqlBuffer.toString();
		}else{
			String result = "(" + this.sqlBuffer.toString() + ")";
			for (int i = 0; i < this.orCriterias.size(); i++) {
				SqlCriteria or = this.orCriterias.get(i);
				result += " or (" + or.toString() + ")";
			}
			return result;
		}
	}

	/**
	 * 获取sql管道参数
	 * @return
	 */
	public List<Object> getParams() {
		if(this.orCriterias == null){
			return this.statementParams;
		}else{
			List<Object> result = new ArrayList<Object>();
			for (int i = 0; i < this.orCriterias.size(); i++) {
				SqlCriteria or = this.orCriterias.get(i);
				result.addAll(or.getParams());
			}
			return result;
		}
	}
	
}
