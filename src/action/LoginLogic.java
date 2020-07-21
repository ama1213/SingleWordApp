package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;

public class LoginLogic implements CommonLogic {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		if( password.equals("P")) {
			HttpSession session = request.getSession();
			session.setAttribute("user", new User(userName,password));
			return "speech.jsp";
		} else {
			request.setAttribute("loginFailure", "ログインに失敗しました。もう一度入力してください。");
			return "login.jsp";
		}
	}
}
