package com.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="user")
public class User {

	@Id
	private String userName;
	private String password;
	private String email;
	private String address;
	private String mobile;
	
	public User() {}
	
	public User(String userName, String password, String email, String address, String mobile) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.address = address;
		this.mobile = mobile;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", email=" + email + ", address=" + address
				+ ", mobile=" + mobile + "]";
	}
	
	
}
