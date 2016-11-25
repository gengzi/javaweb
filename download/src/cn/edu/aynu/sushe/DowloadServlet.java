package cn.edu.aynu.sushe;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

public class DowloadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �ļ�����
		// �õ����ǵĲ�����
		String filename = request.getParameter("filename");
		// ����һ���ļ�������������
//		filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");

		// �õ��ļ�����ʵĿ¼������·����
		String path = this.getServletContext().getRealPath(
				"/WEB-INF/downloads/" + filename);
		// �ж��ļ�����ʲô����
		String mimeType = this.getServletContext().getMimeType(filename);
		// �������ݵ�����
		response.setContentType(mimeType);

		// �������ط�ʽ dispositio ����
		response.setHeader("Content-Disposition", "attachment;filename="
				+ URLEncoder.encode(filename, "UTF-8"));

		// ʹ���ļ������� ��ȡ����
		FileInputStream fileInputStream = new FileInputStream(path);

		// ʹ����������ļ����浽�ͻ���

		// ʹ�� commons-io ���е�
		IOUtils.copy(fileInputStream, response.getOutputStream());

		fileInputStream.close();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
