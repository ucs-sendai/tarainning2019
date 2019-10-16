package jp.ucs.bean;

public class Login{
	private String userId;
	private String pass;

	public Login(){

	}

	public Login(String userId,String pass){
		this.userId = userId;
		this.pass = pass;
	}

	public String getUserId() {
		return userId;
	}

	public String getPass() {
		return pass;
	}



}
