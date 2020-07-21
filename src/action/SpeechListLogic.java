package action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.SingleWord;
import bean.User;
import dao.SingleWordDAO;

public class SpeechListLogic implements CommonLogic {

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
			// リクエストパラメータ「comment」から値を取得
			String comment = request.getParameter("comment");
			// データ登録、取得用にDAOインスタンスを生成
			SingleWordDAO dao = new SingleWordDAO();

			if (comment != null) {
				if (comment.length() != 0) {
					// 投稿された「ひとこと」をインスタンスとして生成
					String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
					SingleWord word = new SingleWord(user.getUserName(), comment, date);
					// DAOでデータベースに追加登録
					if (dao.add(word) == false) {
						// エラー画面へ
						return "error.jsp";
					}
				} else {
					// エラーメッセージをリクエストスコープに保存
					request.setAttribute("errorMessage", "ひとこと発言してください。");
				}
			}

			// DAOで「ひとこと」リストを取得
			List<SingleWord> singlewordList = dao.findAll();
			// リクエストスコープにリストを登録
			request.setAttribute("singlewordList", singlewordList);
			// 新規発言も含めて再表示
			return "speechList.jsp";
		}
	}
}
