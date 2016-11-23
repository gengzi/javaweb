package cn.edu.aynu.shuse.service;

import java.sql.SQLException;
import java.util.List;

import cn.edu.aynu.shuse.bean.Linkmans;
import cn.edu.aynu.shuse.dao.LinkmanDao;

/**
 * （1）添加联系人（需要添加userid） 
 * （2） 查询联系人（根据userid来查找该用户的联系人） 
 * （3）删除联系人（根据linkman的id删除 ）
 * （4）修改联系人信息（需要两步操作，先按linkman表的id 查找到该联系人的全部信息，然后再根据id修改联系人的信息）
 * 
 * @author Administrator
 * 
 */
public class LinkmanService {
	private LinkmanDao dao = new LinkmanDao();

	/**
	 * 查询联系人（根据userid来查找该用户的联系人）
	 * 
	 * @param userid
	 * @return
	 */
	public List<Linkmans> queryByUserIdAllLinkman(int userid) {
		try {
			List<Linkmans> list = dao.select(userid);
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 根据id 删除联系人
	 * 
	 * @param id
	 */
	public void deleteByIdLinkman(int id) {
		try {
			dao.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 根据id 拿到一个联系人的信息
	 * 
	 * @param id
	 * @return
	 */
	public Linkmans queryByidLinkman(int id) {

		try {
			Linkmans linkmans = dao.selectbyid(id);
			return linkmans;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 根据id值 来修改联系人的数据
	 * 
	 * @param linkmans
	 */
	public void updateByidLinkman(Linkmans linkmans) {
		try {
			dao.update(linkmans);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 添加联系人（需要添加userid）
	 * 
	 * @param linkman
	 */
	public void addLinkman(Linkmans linkman) {

		try {
			dao.add(linkman);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
