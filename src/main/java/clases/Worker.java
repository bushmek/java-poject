package clases;

import java.io.Serializable;
/**
*
* author @cluckin
*/
public class Worker implements Serializable {

	private String login,pass,name,phoneNumber,filial,permission;
	
	public Worker() {
		this.login="0";
		this.pass="0";
		this.name="0";
		this.phoneNumber="0";
		this.filial="0";
		this.permission="0";
	}
	public Worker(String login,String pass,String name,String phoneNumber,String filial,String permission) {
		this.login=login;
		this.pass=pass;
		this.name=name;
		this.phoneNumber=phoneNumber;
		this.filial=filial;
		this.permission=permission;
	}
	
	//getter's
	public String getLogin() {
		return login;
	}
	
	
	public String getName() {
		return name;
	}
	
	public String getFilial() {
		return filial;
	}
	
	public String getPermissionLevel() {
		return permission;
	}
}
