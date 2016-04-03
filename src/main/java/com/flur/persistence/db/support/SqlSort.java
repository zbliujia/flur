package com.flur.persistence.db.support;

import java.util.ArrayList;
import java.util.List;

/**
 * 排序对象
 * @author WX
 *
 */
public class SqlSort {
	
	private List<SortField> sortFields;
	
	/**
	 * 添加排序字段和排序类型
	 * @param field
	 * @param order
	 */
	public SqlSort on(String field, SqlOrder sqlOrder){
		if(this.sortFields == null){
			this.sortFields = new ArrayList<SortField>();
		}
		SortField sortField = new SortField(field, sqlOrder);
		this.sortFields.add(sortField);
		return this;
	}

	public List<SortField> getSortFields() {
		return sortFields;
	}
	
	/**
	 * 重写的toString，转为sql排序语句
	 */
	@Override
	public String toString(){
		if(this.sortFields != null){
			StringBuffer buffer = new StringBuffer();
			buffer.append(" order by ");
			for(int i = 0; i < this.sortFields.size(); i++) {
				SortField field = this.sortFields.get(i);
				if(i != 0){
					buffer.append(", ");
				}
				buffer.append(field.getField());
				buffer.append(" ");
				buffer.append(field.getOrder().getOrderType());
			}
			return buffer.toString();
		}
		return "";
	}
	
}
