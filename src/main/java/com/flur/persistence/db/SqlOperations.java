package com.flur.persistence.db;

import java.util.List;
import java.util.Map;

import com.flur.common.util.PageInfo;
import com.flur.persistence.db.support.SqlQuery;
import com.flur.persistence.db.support.SqlUpdate;

/**
 * 数据库操作接口
 * @author wx
 *
 */
public interface SqlOperations {
	
	/**
	 * 新增
	 */
	public void insert(Object entity);
	
	/**
	 * 保存，insert or update
	 * @param entity
	 */
	public void save(Object entity);
	
	/**
	 * 删除
	 */
	public <T> void removeById(int id, Class<T> entityClass);
	
	public <T> void remove(SqlQuery query, Class<T> entityClass);
	
	/**
	 * 更新
	 */
	public <T> void update(SqlQuery query, SqlUpdate update, Class<T> entityClass);
	
	public <T> void update(int id, SqlUpdate update, Class<T> entityClass);
	
	public <T> void update(Object entity);
	
	
	/**
	 * 查询
	 */
	public <T> List<T> find(SqlQuery query, Class<T> entityClass);
	
	public <T> T findById(int id, Class<T> entityClass);
	
	public <T> T findOne(SqlQuery query, Class<T> entityClass);
	
	public <T> T findAndRemove(SqlQuery query, Class<T> entityClass);
	
	public <T> T findAndRemove(int id, Class<T> entityClass);
	
	public List<Map<String, Object>> find(String sql);
	
	public Map<String, Object> findOne(String sql);
	
	/**
	 * 根据条件查询符合数据数量
	 */
	public <T> long count(SqlQuery query, Class<T> entityClass);
	/**
	 * 统计所有记录条数
	 * @param entityClass
	 * @return long
	 */
	public <T> long count(Class<T> entityClass);

	/**
	 * 分页查询
	 * @param page
	 * @param entityClass
	 * @param collectionName
	 */
	public <T> PageInfo<T> getObjectPagination(PageInfo<T> page);
	
	public String getTableName(Class<?> cls);
	
}
