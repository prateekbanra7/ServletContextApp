package in.abc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static {
		System.out.println("Servlet Loading...");
	}

	public TestServlet() {
		System.out.println("Servelt Instantiation...");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("Servlet Initialization...");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();

		// Attribute data which is dynamic
		context.setAttribute("rimil", "chumru");
		context.setAttribute("sengel", "jakra");
		context.setAttribute("hoyo", "nara");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Output</title></head>");
		out.println("<body align = 'center'>");
		out.println("<table border = '1'>");
		out.println("<tr><th>ParameterName</th><th>ParameterValue</th></tr>");

		Enumeration<String> parameterNames = context.getInitParameterNames();
		System.out.println(parameterNames);
		while (parameterNames.hasMoreElements()) {
			String parameterName = (String) parameterNames.nextElement();
			String parameterValue = context.getInitParameter(parameterName);
			out.println("<tr>");
			out.println("<td>" + parameterName + "</td><td>" + parameterValue + "</td>");
			out.println("</tr>");
		}

		out.println("<tr><th>AttributeName</th><th>AttributeValue</th></tr>");
		Enumeration<String> attributeNames = context.getAttributeNames();

		// Attribute Data which is dynamic
		while (attributeNames.hasMoreElements()) {
			String attributeName = (String) attributeNames.nextElement();
			Object attributeValue = context.getAttribute(attributeName);
			out.println("<tr>");
			out.println("<td>" + attributeName + "</td><td>" + attributeValue + "</td>");
			out.println("</tr>");
		}

		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

}
