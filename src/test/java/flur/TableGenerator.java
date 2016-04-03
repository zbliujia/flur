package flur;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.flur.entity.GameAnswer;
import com.flur.entity.GamePlayer;
import com.flur.entity.UserLike;
import com.flur.persistence.db.annotation.Table;

public class TableGenerator {

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://42.62.26.220:3306/flur?characterEncoding=UTF-8";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "tkpass123";

	public static void main(String[] args) throws Exception {
		createTable(UserLike.class);
	}

	private static void createTable(Class<?> cls) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("CREATE TABLE ");
		sb.append(getTableName(cls));
		sb.append(" ( id int auto_increment primary key");
		//添加字段
		Field[] fields = cls.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			String name = field.getName();
			if(name.equals("id")){
				continue;
			}
			String type = " varchar (50) ";
			if(field.getType().equals(int.class)){
				type = " int ";
			}else if(field.getType().equals(long.class)){
				type = " bigint ";
			}
			sb.append(",");
			sb.append(name + type + " NOT NULL");
		}
		sb.append(")");
		Class.forName(DB_DRIVER);
		Connection conn = DriverManager.getConnection(DB_CONNECTION, DB_USER,
				DB_PASSWORD);
		Statement stmt = conn.createStatement();
		stmt.execute(sb.toString());
		stmt.close();
		conn.close();
	}

	/**
	 * 获取一个class注解定义的表名
	 * 
	 * @param cls
	 * @return
	 */
	private static String getTableName(Class<?> cls) {
		Table annotation = cls.getAnnotation(Table.class);
		String tableName = annotation.name();
		return tableName;
	}

}
