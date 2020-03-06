package hotel;

public class CookieBean {
	private String username;
	private String password;
	private int flag;
	

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CookieBean(String username, String password, int flag) {
		super();
		this.username = username;
		this.password = password;
		this.flag = flag;
	}

	public CookieBean() {
		super();
		// TODO Auto-generated constructor stub
	}

}
