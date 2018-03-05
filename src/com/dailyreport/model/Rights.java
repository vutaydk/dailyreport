package com.dailyreport.model;
// Generated Mar 5, 2018 4:53:59 PM by Hibernate Tools 5.2.8.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Rights generated by hbm2java
 */
@Entity
@Table(name = "rights", schema = "dbo", catalog = "dailyreport")
public class Rights {

	private Integer id;
	private String name;
	private Integer lv;
	private Date createdAt;
	private Date updatedAt;
	private Set<User> users = new HashSet<User>(0);

	public Rights() {
	}

	public Rights(String name, Integer lv, Date createdAt, Date updatedAt, Set<User> users) {
		this.name = name;
		this.lv = lv;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.users = users;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "lv")
	public Integer getLv() {
		return this.lv;
	}

	public void setLv(Integer lv) {
		this.lv = lv;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", length = 23)
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", length = 23)
	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rights")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
