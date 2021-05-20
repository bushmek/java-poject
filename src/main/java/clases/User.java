package clases;

public class User {

	private String login,pass,name;
	private double discount;
	
	public User() {
		
	}
	public User(String login,String pass,String name) {
		this.login=login;
		this.pass=pass;
		this.name=name;
		
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getPass() {
		return pass;
	}
	
	public String getName() {
		return name;
	}
}
