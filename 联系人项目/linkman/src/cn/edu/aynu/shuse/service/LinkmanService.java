package cn.edu.aynu.shuse.service;

import java.sql.SQLException;
import java.util.List;

import cn.edu.aynu.shuse.bean.Linkmans;
import cn.edu.aynu.shuse.dao.LinkmanDao;

/**
 * 联系人的业务逻辑
 * 
 * @author Administrator
 * 
 */
public class LinkmanService {
	private LinkmanDao dao = new LinkmanDao();

	// 查询所有的联系人
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
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

	}

	/** 根据id值 来修改联系人的数据
	 * @param linkmans
	 */
	public void updateByidLinkman(Linkmans linkmans) {
		try {
			dao.update(linkmans);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void addLinkman(Linkmans linkman){
		
		try {
			dao.add(linkman);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
