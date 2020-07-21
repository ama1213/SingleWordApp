package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.SingleWord;
import bean.User;
import dao.SingleWordDAO;

public class SpeechLogic implements CommonLogic {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// ログイン有無のチェックのため、セッションスコープからユーザ情報を取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// ログインチェック
		if (user == null) {
			// ログインしていない場合（直接リクエスト）
			request.setAttribute("loginFailure", "ログイン画面を経由していません。ログイン処理から行ってください。");
			return "login.jsp";
		} else {
			// ログインしている場合
			SingleWordDAO dao = new SingleWordDAO();
			List<SingleWord> singlewordList = dao.findAll();
			request.setAttribute("singlewordList", singlewordList);
			return "speechList.jsp";
		}
	}
}