package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.SingleWord;

public class SingleWordDAO {
	private final String DRIVER_NAME = "org.h2.Driver";
	private final String JDBC_URL = "jdbc:h2:c:/tools/h2/bin";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	public List<SingleWord> findAll() {
		List<SingleWord> sList = new ArrayList<SingleWord>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// JDBCドライバを読み込み
			Class.forName(DRIVER_NAME);
			// データベースへ接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			// SELECT文を準備
			String sql = "SELECT name,comment,create_date FROM bbs ORDER BY create_date DESC";
			// 準備したSQLをデータベースに届けるPrepareStatementインスタンスを取得する
			pstmt = conn.prepareStatement(sql);
			// SQLを実行し、結果はResultSetインスタンスに格納される
			rs = pstmt.executeQuery();
			// 結果を１レコードづつ取得する
			while (rs.next()) {
				// 取得したレコードから「name」項目のデータを取得する
				String name = rs.getString("name");
				// 取得したレコードから「comment」項目のデータを取得する
				String comment = rs.getString("comment");
				// 取得したレコードから「create_date」項目のデータを取得する
				String date = rs.getString("create_date");
				// SingleWordインスタンスにデータを保存する
				SingleWord sw = new SingleWord(name, comment, date);
				// リストにSingleWordインスタンスを追加する
				sList.add(sw);
			}
		} catch (SQLException e) {
			// データベース接続やSQL実行失敗時の処理
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// JDBCドライバが見つからなかったときの処理
			e.printStackTrace();
			return null;
		} finally {
			// PrepareStatementインスタンス、ResultSetインスタンスのクローズ処理
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// クローズ処理失敗時の処理
					e.printStackTrace();
					return null;
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// クローズ処理失敗時の処理
					e.printStackTrace();
					return null;
				}
			}
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// データベース切断失敗時の処理
					e.printStackTrace();
					return null;
				}
			}
		}
		return sList;
	}

	public boolean add(SingleWord singleword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// JDBCドライバを読み込み
			Class.forName(DRIVER_NAME);
			// データベースへ接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			// INSERT文を準備
			String sql = "INSERT INTO BBS (name,comment,create_date) VALUES (?,?,?)";
			// 4.準備したSQLをデータベースに届けるPrepareStatementインスタンスを取得する
			pstmt = conn.prepareStatement(sql);
			// INSERT文の？に使用する値を設定
			pstmt.setString(1, singleword.getUserName());
			pstmt.setString(2, singleword.getComment());
			pstmt.setString(3, singleword.getDate());
			// SQLを実行し、結果はresultに格納される
			int result = pstmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			// データベース接続やSQL実行失敗時の処理
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			// JDBCドライバが見つからなかったときの処理
			e.printStackTrace();
			return false;
		} finally {
			// PrepareStatementインスタンスのクローズ処理
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// クローズ処理失敗時の処理
					e.printStackTrace();
					return false;
				}
			}
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// データベース切断失敗時の処理
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}
}