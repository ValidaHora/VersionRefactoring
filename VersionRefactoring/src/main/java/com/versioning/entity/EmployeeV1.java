package com.versioning.entity;

public class EmployeeV1 {

//  @NotNull
//  @Max(value = 99999999)
//  @Min(value = 1)
	private int id;
//  @NotNull
//  @NotBlank
//  @NotEmpty
//  @Size(min=1, max=40)
	private String fullName;
//  @Email
//  @Size(max=30)
	private String email;
//  @Size(min = 1, max = 20)
	private String phone;
	private int	status;

  public EmployeeV1() {
  }
  
	public EmployeeV1(int id) {
	  this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
