package cn.edu.aynu.shuse.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.aynu.shuse.bean.Linkmans;
import cn.edu.aynu.shuse.bean.Users;
import cn.edu.aynu.shuse.service.LinkmanService;
import cn.edu.aynu.shuse.service.UsersService;
import cn.edu.aynu.shuse.utils.CommonsUtils;

public class LinkManServlet extends HttpServlet {
	private UsersService service = new UsersService();
	private LinkmanService linkmanService = new LinkmanService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = request.getParameter("method");
		int i = Integer.parseInt(method);
		switch (i) {
		case 1:// 注册账号密码
			addUser(request, response);
			break;
		case 2:// 登陆
			register(request, response);
			break;
		case 3:// 查询联系人
			panduan(request, response);

			break;
		case 4:// 根据id 查询联系人
			queryByidLinkman(request, response);

			break;
		case 6:// 修改联系人
			updateByidLinkman(request, response);

			break;
		case 5:// 删除联系人
			deleteByIdLinkman(request, response);

			break;
		case 7:// 添加联系人
			addLinkman(request, response);

			break;
		case 8:// 退出登录
			exit(request, response);
			break;
		case 9:// 处理一下 top 页面的 userid 参数
			init(request, response);
			break;

		default:
			break;
		}

	}

	/**
	 * 退出登陆
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void exit(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		// 获取Session 防止创建新的 session
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("/linkman/index.jsp");
			return;
		}

		session.removeAttribute("user");
		response.sendRedirect("/linkman/index.jsp");

	}

	/**
	 * 添加联系人
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void addLinkman(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Linkmans linkmans = new Linkmans();
		int userid = Integer.parseInt(request.getParameter("userid"));
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		String zipcode = request.getParameter("zipcode");
		linkmans.setUserId(userid);
		linkmans.setName(name);
		linkmans.setTel(tel);
		linkmans.setAddress(address);
		linkmans.setZipcode(zipcode);

		linkmanService.addLinkman(linkmans);
		response.getWriter().print("添加成功");

	}

	/**
	 * 根据id 查询的联系人显示在 edit.jsp 页面
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void queryByidLinkman(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		int i = Integer.parseInt(id);
		Linkmans linkmans = linkmanService.queryByidLinkman(i);
		request.setAttribute("linkmans", linkmans);
		request.getRequestDispatcher("/jsp/edit.jsp")
				.forward(request, response);
	}

	/**
	 * 修改联系人的数据
	 * 
	 * @param request
	 * @param response
	 */
	private void updateByidLinkman(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		int i = Integer.parseInt(id);
		Linkmans linkmans = CommonsUtils.toBean(request.getParameterMap(),//
				Linkmans.class);
		linkmanService.updateByidLinkman(linkmans);
	}

	/**
	 * 根据id 删除联系人
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void deleteByIdLinkman(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		int i = Integer.parseInt(id);
		linkmanService.deleteByIdLinkman(i);
		response.getWriter().print("删除成功");
	}

	/**
	 * 处理一下 top 页面的 userid 参数
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void init(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userid = request.getParameter("userid");
		request.setAttribute("userid", userid);
		request.getRequestDispatcher("/jsp/top.jsp").forward(request, response);
	}

	/**
	 * 
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void panduan(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String i = request.getParameter("i");
		int panduan = Integer.parseInt(i);

		switch (panduan) {
		case 1: // 根据userid 来查询联系人的信息
			System.out.println("11");
			queryByUserIdAllLinkman(request, response);

			break;
		case 2:// 跳转到 add.jsp页面
			System.out.println("22");
			// 跳转到这个页面
			addjsp(request, response);

			break;

		default:
			break;
		}

	}

	private void addjsp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Users user = (Users) session.getAttribute("user");

		if (user == null) {
			response.getWriter().print("登陆失败，请重新登陆！（3秒后跳转到登陆页面）");
			response.setHeader("Refresh",
					"3;URL=http://localhost:8080/linkman/index.jsp");
			return;
		}

		request.setAttribute("userid", user.getId());
		request.getRequestDispatcher("/jsp/add.jsp").forward(request, response);
	}

	private void queryByUserIdAllLinkman(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		Users user = (Users) session.getAttribute("user");

		if (user == null) {
			response.getWriter().print("登陆失败，请重新登陆！（3秒后跳转到登陆页面）");
			response.setHeader("Refresh",
					"3;URL=http://localhost:8080/linkman/index.jsp");
			return;
		}

		List<Linkmans> linkmans = linkmanService.queryByUserIdAllLinkman(user
				.getId());
		request.setAttribute("linkmans", linkmans);
		request.getRequestDispatcher("/jsp/list.jsp")
				.forward(request, response);
	}

	/**
	 * 登陆
	 */
	private void register(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 拿到返回的参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Users user = service.selectByUserName(username);
		if (user == null) {
			// 返回用户不存在
			response.getWriter().print("用户不存在");
		}

		if (user.getPassword().equals(password.trim())) {
			// 密码相同，3秒后 跳转到联系人页面 携带我们id

			// 获取Session
			HttpSession session = request.getSession();
			// 将username保存在session域中（多个请求可以共享数据）
			session.setAttribute("user", user);
			response.getWriter().print("登陆成功，三秒后跳转到功能页面");
			response.setHeader("Refresh",
					"3;URL=http://localhost:8080/linkman/jsp/index.jsp");
		}

	}

	/**
	 * 注册账号
	 */
	private void addUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 拿到返回的参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		service.add(username, password);
		// 插入成功就跳转到登陆页面
		response.getWriter().print("注册成功,三秒后跳转到登陆页面");
		response.setHeader("Refresh",
				"3;URL=http://localhost:8080/linkman/index.jsp");
	}

}
