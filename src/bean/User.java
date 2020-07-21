package bean;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;

	public User() {}

	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return this.userName;
	}
	public String getPassword() {
		return this.password;
	}

}
