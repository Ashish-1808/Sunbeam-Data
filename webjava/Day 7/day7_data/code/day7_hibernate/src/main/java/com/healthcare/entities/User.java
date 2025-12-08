package com.healthcare.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity //mandatory cls level annotation
@Table(name="users")//to declare table name
public class User {
	@Id //PK constraint
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//auto ID generation - default id generation strategy - AUTO . To specify auto_increment : GenerationType.IDENTITY
	@Column(name="user_id") //to declare column name
	private Long userId;
	@Column(name="first_name",length = 30)//to declare varchar size
	private String firstName;
	@Column(name="last_name",length = 30)//to declare varchar size
	private String lastName;
	/*
	 * In case of java.util.Date , Calendar ... - @Temporal
	 */
	private LocalDate dob;
	@Column(length = 50,unique = true)//add unique constraint
	private String email;
	@Column(length = 400,nullable = false)//varchar(400), NOT NULL constraint
	private String password;
	@Column(length=14, unique = true)
//	@Transient //skip from persistence -no col generation
//	private String confirmPassword;
	private String phone;
	@Column(name="reg_amount")
	private int regAmount;
	@Enumerated(EnumType.STRING) //col type - enum | varchar : enum constants
	private UserRole userRole;
	@Lob //longblob : mysql
 	private byte[] image;
	public User() {
		// TODO Auto-generated constructor stub
	}
	

	public User(String firstName, String lastName, LocalDate dob, String email, String password, String phone,
			int regAmount, UserRole userRole) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.regAmount = regAmount;
		this.userRole = userRole;
	}


	// getters & setters
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getRegAmount() {
		return regAmount;
	}

	public void setRegAmount(int regAmount) {
		this.regAmount = regAmount;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
				+ ", email=" + email + ", phone=" + phone + ", regAmount=" + regAmount + ", userRole=" + userRole + "]";
	}
}
