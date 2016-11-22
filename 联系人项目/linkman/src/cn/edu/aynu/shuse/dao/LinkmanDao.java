package cn.edu.aynu.shuse.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.edu.aynu.shuse.bean.Linkmans;
import cn.edu.aynu.shuse.utils.JDBCUtils;

/**
 * 对Linkman 表的增删改查
 * 
 * @author Administrator
 * 
 */
public class LinkmanDao {
	private QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

	/**
	 * 添加联系人，需要添加外键的userid 的值
	 * 
	 * @param linkman
	 * @throws SQLException
	 */
	public void add(Linkmans linkman) throws SQLException {
		String sql = "insert into linkman(name,tel,address,zipcode,userId) values(?,?,?,?,?)";
		queryRunner.update(sql, linkman.getName(), linkman.getTel(),//
				linkman.getAddress(), linkman.getZipcode(),//
				linkman.getUserId());

	}

	/**
	 * 根据userid 查询该用户的所有联系人
	 * 
	 * @return List<Linkmans>
	 * @throws SQLException
	 */
	public List<Linkmans> select(int userid) throws SQLException {
		String sql = "select * from linkman where userId=?";
		List<Linkmans> list = queryRunner.query(sql,//
				new BeanListHandler<Linkmans>(Linkmans.class), userid);

		return list;
	}

	/**
	 * 根据联系人表中的 id 删除
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void delete(int id) throws SQLException {
		String sql = "delete from linkman where id=?";
		queryRunner.update(sql, id);

	}

	/**
	 * 根据 id 查询一个联系人的信息
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Linkmans selectbyid(int id) throws SQLException {
		String sql = "select * from linkman where id=?";
		Linkmans linkmans = queryRunner.query(sql, new BeanHandler<Linkmans>(//
				Linkmans.class), id);

		return linkmans;
	}

	// 修改联系人信息
	public void update(Linkmans linkmans) throws SQLException {
		String sql = "update linkman set name=?,tel=?,address=?,zipcode=? where id=?";

		queryRunner.update(sql, linkmans.getName(), linkmans.getTel(),
				linkmans.getAddress(), linkmans.getZipcode(),linkmans.getId());
	}

}
