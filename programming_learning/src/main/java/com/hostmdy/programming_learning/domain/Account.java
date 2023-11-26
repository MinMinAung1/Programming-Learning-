package com.hostmdy.programming_learning.domain;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private String email;
	private String password;
	private String role;
	private String status;

	private LocalDate createAt;
	private LocalDate updateAt;

//	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
//	private List<Comment> comments = new ArrayList<>();

	public Account() {
	}

	@PrePersist
	void onCreate() {
		this.createAt = LocalDate.now();

	}

	@PreUpdate
	void onUpdate() {
		this.updateAt = LocalDate.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDate createAt) {
		this.createAt = createAt;
	}

	public LocalDate getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDate updateAt) {
		this.updateAt = updateAt;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", status=" + status + ", createAt=" + createAt + ", updateAt=" + updateAt + "]";
	}
}
