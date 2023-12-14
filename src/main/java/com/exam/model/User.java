package com.exam.model;

import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.exam.model.exam.Result;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User implements UserDetails{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	
	private String email;
	private String phone;
	private String forgot1;
	private String forgot2;
	
	
	public String getForgot1() {
		return forgot1;
	}




	public void setForgot1(String forgot1) {
		this.forgot1 = forgot1;
	}




	public String getForgot2() {
		return forgot2;
	}




	public void setForgot2(String forgot2) {
		this.forgot2 = forgot2;
	}




	private boolean enabled=true;
	private String profile;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<Result> results;
	
	public Set<Result> getResults() {
		return results;
	}




	public void setResults(Set<Result> results) {
		this.results = results;
	}




	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "user")
	@JsonIgnore // to prevent circular dependency
	private Set<UserRole> userRoles = new HashSet<>(); 
	
	


	public Set<UserRole> getUserRoles() {
		return userRoles;
	}




	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}




	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

		
	

	public User(long id, String userName, String password, String firstName, String lastName, String email,
			String phone, boolean enabled, String profile) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.enabled = enabled;
		this.profile = profile;
	}




	public String getProfile() {
		return profile;
	}




	public void setProfile(String profile) {
		this.profile = profile;
	}




	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
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


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}




	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Authority> set = new HashSet<>();
		this.userRoles.forEach(userRole->{
			set.add(new Authority(userRole.getRole().getRoleName()));
		});
		return set;
	}




	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return getUserName();
	}




	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}




	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}




	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
