package com.smartgig.database.dao;
/**e-commerce site administrator
 **/
public class Admin {
	/**adminId PK
	 **/
	private int adminId;
	private String uname;
	private String passwd;
	public Admin(int adminId, String uname, String passwd) {
		super();
		this.adminId = adminId;
		this.uname = uname;
		this.passwd = passwd;
	}
	public Admin() {
		super();
	}
	
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}
