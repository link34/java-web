package com.jdc.web.ch2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletInformationServlet
 */
@WebServlet(urlPatterns = { "/ServletInformationServlet", "/servlet-info" }, initParams = {
		@WebInitParam(name = "param-1", value = "Initialization Parameter 1"),
		@WebInitParam(name = "param-2", value = "Initialization Parameter 2") })
public class ServletInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String TR_FORMAT = "<tr><td>%s</td><td>%s</td></tr>\n";
	private static final String HEAD = "<head><title>%s</title></head>\n";
	private static final String H1 = "<h1>%s</h1>\n";
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Enumeration<String> names = getInitParameterNames();
		
		// writing result to response
		PrintWriter pw = response.getWriter();
		pw.println("<html>");
		pw.printf(HEAD, "Request Header Informations");
		pw.println("<body>");
		pw.printf(H1, "Request Header Informations");
		pw.println("<table>");

		while(names.hasMoreElements()) {
			String name = names.nextElement();
			pw.printf(TR_FORMAT, name, getInitParameter(name));
		}

		pw.printf(TR_FORMAT, "getPathInfo", request.getPathInfo());
		pw.printf(TR_FORMAT, "getServletPath", request.getServletPath());
		pw.printf(TR_FORMAT, "getRequestURL", request.getRequestURL());
		
		pw.println("</table>");
		pw.println("<a href='javascript:history.back();'>Back</a>");
		pw.println("</body>");
		pw.println("</html>");
	}

}
