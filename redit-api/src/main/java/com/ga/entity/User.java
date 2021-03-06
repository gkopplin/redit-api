package com.ga.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
	
    @NotBlank(message = "Username cannot be blank")
    @Column(unique = true, nullable = false)
    private String username;
	
    @Column(nullable = false)
    private String password;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column
    private String address;
    
    @Column
    private String mobile;
    
    @Column(name = "addl_email")
    private String addlEmail;
    
    
    @JsonIgnore
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Post> posts;
    
    @JsonIgnore
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Comment> comments;
    

    public User() {}
    
    public Long getUserId() {
		return userId;
    }

    public void setUserId(Long userId) {
		this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

	public String getAddlEmail() {
		return addlEmail;
	}

	public void setAddlEmail(String addlEmail) {
		this.addlEmail = addlEmail;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	
    
    
}