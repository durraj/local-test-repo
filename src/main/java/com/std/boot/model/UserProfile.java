package com.std.boot.model;

import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity(name="app_user")
public class UserProfile {
	public UserProfile(){}
	
	public UserProfile(String firstName, String lastName, String email, String password2, String confirmpassword) {
	this.firstName=firstName;
	this.lastName=lastName;
	this.email=email;
	this.password=password2;
	this.passwordConfirm=confirmpassword;
	}
	@Setter
	@Getter
	@Column(name="first_name",nullable = false, length = 30)
	private String firstName;
	
	@Setter
	@Getter
	@Column(name="last_name",nullable = false, length = 30)
	private String lastName;
	
	@Setter
	@Getter
	@Column(name="email",nullable = false, length = 30)
    private String email;
	
	@Setter
	@Getter
	@Column(name="password",nullable = false, length = 30)
	private String password;
    
	@Transient
	private String passwordConfirm;
	@ManyToMany
	@JoinTable(name = "app_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
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
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
