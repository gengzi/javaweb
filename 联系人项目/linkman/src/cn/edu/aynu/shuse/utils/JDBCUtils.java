package cn.edu.aynu.shuse.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 创建数据库连接池
 * @author Administrator
 *
 */
public class JDBCUtils {
	//读取的是C3P0-config默认配置创建数据库连接池对象
	private static DataSource ds = new ComboPooledDataSource();
	//获取数据库连接池对象
	public static DataSource getDataSource(){
		return ds;
	}
	//从池中获取连接
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	}	
}
