package cn.edu.aynu.shuse.servlet;

import java.io.IOException;
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
	// 拿到处理 User 和 Linkman 表的业务类
	private UsersService service = new UsersService();
	private LinkmanService linkmanService = new LinkmanService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 调用 dopost 方法
		this.doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 【1】根据method 来判断执行那个方法
		String method = request.getParameter("method");
		int i = Integer.parseInt(method);
		switch (i) {
		case 1:// 注册用户账号密码
			addUser(request, response);
			break;
		case 2:// 登陆到系统
			register(request, response);
			break;
		case 3:// 查询该用户的联系人
			panduan(request, response);

			break;
		case 4:// 根据id查询联系人
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

		// 【1】获取Session，设置为false 防止创建新的 session
		HttpSession session = request.getSession(false);
		// 【2】判断session 是否为空 ，重定向到我们的登陆页面
		if (session == null) {
			response.sendRedirect("/linkman/index.jsp");
			return;
		}
		// 【3】移除我们添加的 user
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
		// 【1】拿到我们表单中的参数值
		int userid = Integer.parseInt(request.getParameter("userid"));
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		String zipcode = request.getParameter("zipcode");
		// 【2】封装到 linkmans 对象中
		linkmans.setUserId(userid);
		linkmans.setName(name);
		linkmans.setTel(tel);
		linkmans.setAddress(address);
		linkmans.setZipcode(zipcode);
		// 【3】添加联系人信息
		linkmanService.addLinkman(linkmans);
		response.getWriter().print("添加成功");

	}

	/**
	 * 修改联系人信息 第一步 根据id 查询的联系人并显示在 edit.jsp 页面
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void queryByidLinkman(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 【1】拿到我们的 id 参数值
		String id = request.getParameter("id");
		int i = Integer.parseInt(id);
		// 【2】根据id 查询该联系人的信息，并返回成一个 linkmans 对象
		Linkmans linkmans = linkmanService.queryByidLinkman(i);
		// 【3】设置到 request 域对象中
		request.setAttribute("linkmans", linkmans);
		// 【4】转发到 编辑联系人页面
		request.getRequestDispatcher("/jsp/edit.jsp")
				.forward(request, response);
	}

	/**
	 * 修改联系人第二步 修改联系人的数据
	 * 
	 * @param request
	 * @param response
	 */
	private void updateByidLinkman(HttpServletRequest request,
			HttpServletResponse response) {
		// 根据联系人的id
		// 【1】 将请求的参数 封装成 linkmans 对象
		Linkmans linkmans = CommonsUtils.toBean(request.getParameterMap(),//
				Linkmans.class);
		// 【2】执行修改信息操作
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
		// 【1】
		String id = request.getParameter("id");
		int i = Integer.parseInt(id);
		// 【2】
		linkmanService.deleteByIdLinkman(i);
		response.getWriter().print("删除成功");
	}

	/**
	 * 针对top.jsp页面的 查看联系人(1) 还是 添加联系人(2)
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
			queryByUserIdAllLinkman(request, response);

			break;
		case 2:// 跳转到 add.jsp页面
			addjsp(request, response);
			
			break;
		}

	}

	/**  添加该用户的联系人信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
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

	/**
	 * 查看该用户的联系人
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
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
	 * 登陆到系统
	 */
	private void register(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 拿到返回的参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username.trim().isEmpty() || password.trim().isEmpty()) {
			// 返回用户不存在
			response.getWriter().print("用户名或者密码不能为空");
			return;
		}

		Users user = service.selectByUserName(username);
		if (user == null) {
			// 返回用户不存在
			response.getWriter().print("用户不存在，请重试");
			response.setHeader("Refresh",
					"1;URL=http://localhost:8080/linkman/index.jsp");
			return;
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
		} else {
			response.getWriter().print("账号或者密码错误，请重新登陆");
			response.setHeader("Refresh",
					"1;URL=http://localhost:8080/linkman/index.jsp");
			return;
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
		if (username.trim().isEmpty() || password.trim().isEmpty()) {
			// 返回用户不存在
			response.getWriter().print("用户名或者密码不能为空");
			return;
		}

		service.add(username, password);
		// 插入成功就跳转到登陆页面
		response.getWriter().print("注册成功,三秒后跳转到登陆页面");
		response.setHeader("Refresh",
				"3;URL=http://localhost:8080/linkman/index.jsp");
	}

}
