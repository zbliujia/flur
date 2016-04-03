package com.flur.persistence.db;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.flur.common.util.PageInfo;
import com.flur.common.util.Validator;
import com.flur.persistence.db.annotation.Table;
import com.flur.persistence.db.support.EntityRowMapper;
import com.flur.persistence.db.support.InsertPreparedStatementCreator;
import com.flur.persistence.db.support.MapRowMapper;
import com.flur.persistence.db.support.QueryPreparedStatementCreator;
import com.flur.persistence.db.support.QueryUpdatePreparedStatementCreator;
import com.flur.persistence.db.support.SqlCriteria;
import com.flur.persistence.db.support.SqlQuery;
import com.flur.persistence.db.support.SqlUpdate;

/**
 * 关系型数据库操作实现类
 * @author WX
 *
 */
@Component
public class SqlOperationsImpl implements SqlOperations {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void insert(Object entity) {
		//获取表名
		String tableName = this.getTableName(entity.getClass());
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into ");
		buffer.append(tableName);
		buffer.append(" (");
		StringBuffer valueBuffer = new StringBuffer();
		valueBuffer.append("(");
		//获取字段
		Field idField = null;
		Field[] fields = entity.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String name = field.getName();
			if(name.equals("id")){
				//如果是id，暂存一下，执行完对其进行设置
				field.setAccessible(true);
				idField = field;
			}
			if(i != 0){
				buffer.append(", ");
				valueBuffer.append(", ");
			}
			buffer.append(name);
			valueBuffer.append("?");
		}
		buffer.append(")");
		valueBuffer.append(")");
		buffer.append(" values ");
		buffer.append(valueBuffer.toString());
		String sql = buffer.toString();
		//执行
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.jdbcTemplate.update(new InsertPreparedStatementCreator(entity, sql), keyHolder);
		try {
			idField.set(entity, keyHolder.getKey().intValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void save(Object entity){
		Field field = null;
		try {
			field = entity.getClass().getDeclaredField("id");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		if(field == null){
			//没有id字段，直接插入
			this.insert(entity);
			return;
		}
		field.setAccessible(true);
		Object idValue = null;
		try {
			idValue = field.get(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		if(Validator.isEmpty(idValue) || idValue.toString().equals("0")){
			//如果值是空的，直接插入
			this.insert(entity);
			return;
		}
		Object exists = this.findById(Integer.parseInt(idValue.toString()), entity.getClass());
		if(exists == null){
			//记录不存在，插入
			this.insert(entity);
		}else{
			//记录存在，更新
			this.update(entity);
		}
	}

	@Override
	public <T> void removeById(int id, Class<T> entityClass) {
		SqlQuery query = new SqlQuery(SqlCriteria.where("id").is(id));
		this.remove(query, entityClass);
	}

	@Override
	public <T> void remove(SqlQuery query, Class<T> entityClass) {
		String tableName = this.getTableName(entityClass);
		StringBuffer buffer = new StringBuffer();
		buffer.append("delete from ");
		buffer.append(tableName);
		if(query.getCriteria() != null){
			buffer.append(" where ");
			buffer.append(query.getCriteriaString());
		}
		String sql = buffer.toString();
		this.jdbcTemplate.update(new QueryPreparedStatementCreator(query, sql));
	}

	@Override
	public <T> void update(SqlQuery query, SqlUpdate update, Class<T> entityClass) {
		String tableName = this.getTableName(entityClass);
		StringBuffer buffer = new StringBuffer();
		buffer.append("update ");
		buffer.append(tableName);
		buffer.append(" set ");
		buffer.append(update.toString());
		if(query.getCriteria() != null){
			buffer.append(" where ");
			buffer.append(query.getCriteriaString());
		}
		String sql = buffer.toString();
		this.jdbcTemplate.update(new QueryUpdatePreparedStatementCreator(query, update, sql));
	}

	@Override
	public <T> void update(int id, SqlUpdate update, Class<T> entityClass) {
		SqlQuery query = new SqlQuery(SqlCriteria.where("id").is(id));
		this.update(query, update, entityClass);
	}
	
	@Override
	public <T> void update(Object entity){
		Field idField = null;
		Object idValue = null;
		try {
			idField = entity.getClass().getDeclaredField("id");
			if(idField == null){
				return;
			}
			idField.setAccessible(true);
			idValue = idField.getInt(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		SqlQuery query = new SqlQuery(SqlCriteria.where("id").is(idValue));
		SqlUpdate update = new SqlUpdate();
		//获取字段
		Field[] fields = entity.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			String name = field.getName();
			try {
				if(!name.equals("id")){
					field.setAccessible(true);
					Object fieldValue = field.get(entity);
					update.set(name, fieldValue);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//执行
		this.update(query, update, entity.getClass());
	}

	@Override
	public <T> List<T> find(SqlQuery query, Class<T> entityClass) {
		String tableName = this.getTableName(entityClass);
		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from ");
		buffer.append(tableName);
		if(query.getCriteria() != null){
			buffer.append(" where ");
			buffer.append(query.getCriteriaString());
		}
		if(query.getGroupBy() != null){
			buffer.append(" group by ");
			buffer.append(query.getGroupBy());
		}
		if(query.getSort() != null){
			buffer.append(query.getSortString());
		}
		if(query.getLimit() != null){
			buffer.append(" limit ");
			if(query.getSkip() != null){
				buffer.append(query.getSkip());
			}else{
				buffer.append("0");
			}
			buffer.append(", ");
			buffer.append(query.getLimit());
		}
		String sql = buffer.toString();
		return this.jdbcTemplate.query(new QueryPreparedStatementCreator(query, sql), new EntityRowMapper<T>(entityClass));
	}

	@Override
	public <T> T findById(int id, Class<T> entityClass) {
		String tableName = this.getTableName(entityClass);
		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from ");
		buffer.append(tableName);
		buffer.append(" where id = ");
		buffer.append(id);
		String sql = buffer.toString();
		List<T> list = this.jdbcTemplate.query(sql, new EntityRowMapper<T>(entityClass));
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public <T> T findOne(SqlQuery query, Class<T> entityClass) {
		List<T> list = this.find(query, entityClass);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public <T> T findAndRemove(SqlQuery query, Class<T> entityClass) {
		T find = this.findOne(query, entityClass);
		if(find != null){
			//删除
			this.remove(query, entityClass);
		}
		return find;
	}

	@Override
	public <T> T findAndRemove(int id, Class<T> entityClass) {
		T find = this.findById(id, entityClass);
		if(find != null){
			//删除
			this.removeById(id, entityClass);
		}
		return find;
	}
	
	@Override
	public List<Map<String, Object>> find(String sql) {
		List<Map<String, Object>> result = this.jdbcTemplate.query(sql, new MapRowMapper());
		return result;
	}

	@Override
	public Map<String, Object> findOne(String sql) {
		List<Map<String, Object>> list = this.find(sql);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}


	@Override
	public <T> long count(SqlQuery query, Class<T> entityClass) {
		String tableName = this.getTableName(entityClass);
		StringBuffer buffer = new StringBuffer();
		buffer.append("select count(id) from ");
		buffer.append(tableName);
		Object[] args = new Object[0];
		if(query.getCriteria() != null){
			buffer.append(" where ");
			buffer.append(query.getCriteriaString());
			//把管道参数转为数组
			List<Object> params = query.getCriteria().getParams();
			args = new Object[params.size()];
			for (int i = 0; i < params.size(); i++) {
				Object param = params.get(i);
				args[i] = param;
			}
		}
		String sql = buffer.toString();
		return this.jdbcTemplate.queryForLong(sql, args);
	}

	@Override
	public <T> long count(Class<T> entityClass) {
		String tableName = this.getTableName(entityClass);
		StringBuffer buffer = new StringBuffer();
		buffer.append("select count(id) from ");
		buffer.append(tableName);
		String sql = buffer.toString();
		return this.jdbcTemplate.queryForLong(sql);
	}

	@Override
	public <T> PageInfo<T> getObjectPagination(PageInfo<T> page) {
		long totalCount = this.count(page.getSqlQuery(), page.getEntityClass());
		long pageCount = 1;
		if(totalCount % page.getPageSize() == 0){
			pageCount = totalCount / page.getPageSize();
		}else{
			pageCount = totalCount / page.getPageSize() + 1;
		}
		List<T> elements = this.find(page.getSqlQuery(), page.getEntityClass());
		page.setElements(elements);
		page.setPageCount(pageCount);
		page.setElementsCount(totalCount);
		return page;
	}
	
	/**
	 * 获取一个class注解定义的表名
	 * @param cls
	 * @return
	 */
	public String getTableName(Class<?> cls){
		Table annotation = cls.getAnnotation(Table.class);
		String tableName = annotation.name();
		return tableName;
	}

}
