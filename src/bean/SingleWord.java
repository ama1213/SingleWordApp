package bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SingleWord implements Serializable {
	// フィールドの定義
	private static final long serialVersionUID = 1L;
	private String userName; //ユーザ名
	private String comment; //ひとことコメント
	private String date; //発言日時

	// コンストラクタ（引数なし）
	public SingleWord() {
		super();
	}

	// コンストラクタ（引数あり）
	public SingleWord(String userName, String comment) {
		this.userName = userName;
		this.comment = comment;
		this.date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
	}

	// コンストラクタ（引数あり・DAO用）
	public SingleWord(String userName, String comment, String date) {
		this.userName = userName;
		this.comment = comment;
		this.date = date;
	}

	//getterメソッド
	public String getUserName() {
		return userName;
	}

	public String getComment() {
		return comment;
	}

	public String getDate() {
		return date;
	}
}
