package cn.edu.aynu.shuse.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.edu.aynu.shuse.bean.Users;
import cn.edu.aynu.shuse.utils.JDBCUtils;

/**
 * 对User 的数据操作
 * 
 * @author Administrator
 * 
 */
public class UsersDao {
	QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

	/**
	 * 根据账号姓名 去查找 密码
	 * 
	 * @throws SQLException
	 */
	public Users select(String name) throws SQLException {
		String sql = "select * from user where username=?";
		Users list = qr.query(sql,
				new BeanHandler<Users>(Users.class), name);
		return list;
	}

	/**
	 * 添加数据
	 * 
	 * @throws SQLException
	 */
	public void add(String name, String password) throws SQLException {

		String sql = "insert into user(username,password) values(?,?) ";
		qr.update(sql, name, password);
		
	}

}
