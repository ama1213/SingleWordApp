package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommonLogic;

@WebServlet("/SingleWord")
public class SingleWordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SingleWordServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		CommonLogic logic = null;
		String next = "/WEB-INF/jsp/";
		if ( action == null ) {
			next = next + "login.jsp";
		} else {
			try {
				Class clazz = Class.forName(action);
				logic = (CommonLogic) clazz.newInstance();
				String fileName = logic.execute(request, response);
				next = next + fileName;
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				next = next + "error.jsp";
			}
		}
		request.getRequestDispatcher(next).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
