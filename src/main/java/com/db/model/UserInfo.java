package com.db.model;

public class UserInfo {
	private String email;
	private String password;
	private String username;
	private String lastname;
	private String firstname;
	private String address;
	private String phonenumber;
	private String role;
	private int totalpaid;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastName) {
		this.lastname = lastName;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstName) {
		this.firstname = firstName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phoneNumber) {
		this.phonenumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "UserInfo [email=" + email + ", password=" + password + ", username=" + username + ", lastname="
				+ lastname + ", firstname=" + firstname + ", address=" + address + ", phonenumber=" + phonenumber + "]";
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the totalpaid
	 */
	public int getTotalpaid() {
		return totalpaid;
	}

	/**
	 * @param totalpaid the totalpaid to set
	 */
	public void setTotalpaid(int totalpaid) {
		this.totalpaid = totalpaid;
	}

}
