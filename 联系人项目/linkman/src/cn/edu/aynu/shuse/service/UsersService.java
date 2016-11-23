package cn.edu.aynu.shuse.service;

import java.sql.SQLException;
import java.util.List;

import cn.edu.aynu.shuse.bean.Users;
import cn.edu.aynu.shuse.dao.UsersDao;

/**
 * 注册账号 和 查找账号
 * 
 * @author Administrator
 * 
 */
public class UsersService {
	private UsersDao dao = new UsersDao();

	/**
	 * 注册账号
	 * 
	 * @param name
	 * @param password
	 */
	public void add(String name, String password) {

		try {
			dao.add(name, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查找账号
	 * 
	 * @param name
	 * @return
	 */
	public Users selectByUserName(String name) {

		try {
			Users list = dao.select(name);
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
