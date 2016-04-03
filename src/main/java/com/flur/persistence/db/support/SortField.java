package com.flur.persistence.db.support;

public class SortField {
	
	private String field;
	private SqlOrder order;
	
	public SortField(String field, SqlOrder order) {
		super();
		this.field = field;
		this.order = order;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public SqlOrder getOrder() {
		return order;
	}

	public void setOrder(SqlOrder order) {
		this.order = order;
	}
	
}
