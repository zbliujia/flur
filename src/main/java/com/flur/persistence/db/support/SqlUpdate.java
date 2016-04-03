package com.flur.persistence.db.support;

import java.util.ArrayList;
import java.util.List;

import com.flur.common.util.Validator;

public class SqlUpdate {
	
	private StringBuffer sqlBuffer;
	private List<Object> statementParams = new ArrayList<Object>();
	
	public SqlUpdate() {
		super();
		this.sqlBuffer = new StringBuffer();
	}

	public SqlUpdate set(String field, Object value){
		if(Validator.notEmpty(sqlBuffer.toString())){
			this.sqlBuffer.append(", ");
		}
		this.sqlBuffer.append(field);
		this.sqlBuffer.append(" = ");
		this.sqlBuffer.append("?");
		this.statementParams.add(value);
		return this;
	}
	
	public SqlUpdate inc(String field, Object value){
		if(Validator.notEmpty(sqlBuffer.toString())){
			this.sqlBuffer.append(", ");
		}
		this.sqlBuffer.append(field);
		this.sqlBuffer.append(" = ");
		this.sqlBuffer.append(field);
		this.sqlBuffer.append("+");
		this.sqlBuffer.append(value);
		return this;
	}
	
	public SqlUpdate dec(String field, Object value){
		if(Validator.notEmpty(sqlBuffer.toString())){
			this.sqlBuffer.append(", ");
		}
		this.sqlBuffer.append(field);
		this.sqlBuffer.append(" = ");
		this.sqlBuffer.append(field);
		this.sqlBuffer.append("-");
		this.sqlBuffer.append(value);
		return this;
	}
	
	/**
	 * 重写toString方法，获取格式化之后的sql
	 */
	@Override
	public String toString(){
		return this.sqlBuffer.toString();
	}
	
	/**
	 * 获取sql管道参数
	 * @return
	 */
	public List<Object> getParams() {
		return this.statementParams;
	}
	
}
