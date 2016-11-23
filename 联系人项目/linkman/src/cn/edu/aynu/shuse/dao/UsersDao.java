package cn.edu.aynu.shuse.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.edu.aynu.shuse.bean.Users;
import cn.edu.aynu.shuse.utils.JDBCUtils;

/**
 * 对user表的数据操作，两方面{ （1）根据账户名，去查找密码 （2）注册添加用户的信息 }
 * 
 * @author Administrator
 * 
 */
public class UsersDao {
	/**
	 * QueryRunner类简化了执行SQL语句的代码，它与ResultSetHandler组合在一起就能完成大部分的数据库操作，大大减少编码量。
	 * 针对不同的数据库操作，QueryRunner类提供的不同的方法。
	 */
	QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

	/**
	 * （1）根据账户名，去查找密码 
	 * 
	 * @throws SQLException
	 */
	public Users select(String name) throws SQLException {
		String sql = "select * from user where username=?";
		Users list = qr.query(sql, new BeanHandler<Users>(Users.class), name);
		return list;
	}

	/**
	 * （2）注册添加用户的信息
	 * 
	 * @throws SQLException
	 */
	public void add(String name, String password) throws SQLException {

		String sql = "insert into user(username,password) values(?,?) ";
		qr.update(sql, name, password);

	}

}
