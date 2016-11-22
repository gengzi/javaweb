package cn.edu.aynu.shuse.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * 字符过滤器处理中文乱码
 * 
 * @author Administrator
 * 
 */
public class CharacterFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		// 拦截请求处理乱码
		// post请求
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		/*
		 * 对request进行包装 characterRequest功能增强了，获取中文参数直接处理乱码
		 */
		CharacterRequest characterRequest = new CharacterRequest(request);
		// 放行
		chain.doFilter(characterRequest, response);

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

// 对request对象重新包装（增强的功能：处理get请求的中文乱码）
class CharacterRequest extends HttpServletRequestWrapper {

	public CharacterRequest(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	/*
	 * 重写getParameter(),增强的功能：处理get请求的中文乱码
	 */
	public String getParameter(String name) {
		// 调用被包装对象已有的方法，获取请求参数
		String value = super.getParameter(name);
		if (value == null) {
			return null;
		}
		// 判断请求方式
		String method = super.getMethod();
		// 如果是get请求
		if ("get".equalsIgnoreCase(method)) {
			try {
				value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return value;

	}

}
