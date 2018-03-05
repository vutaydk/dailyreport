package com.dailyreport.model;
// Generated Mar 5, 2018 3:36:32 PM by Hibernate Tools 5.2.8.Final

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Project generated by hbm2java
 */
@Entity
@Table(name = "project", schema = "dbo", catalog = "dailyreport")
public class Project {

	private String projectCode;
	private Serializable name;
	private Date startAt;
	private Date finishAt;
	private Date createdAt;
	private Date updatedAt;
	private Set<Report> reports = new HashSet<Report>(0);

	public Project() {
	}

	public Project(String projectCode) {
		this.projectCode = projectCode;
	}

	public Project(String projectCode, Serializable name, Date startAt, Date finishAt, Date createdAt, Date updatedAt,
			Set<Report> reports) {
		this.projectCode = projectCode;
		this.name = name;
		this.startAt = startAt;
		this.finishAt = finishAt;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.reports = reports;
	}

	@Id

	@Column(name = "project_code", unique = true, nullable = false, length = 18)
	public String getProjectCode() {
		return this.projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	@Column(name = "name")
	public Serializable getName() {
		return this.name;
	}

	public void setName(Serializable name) {
		this.name = name;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "start_at", length = 10)
	public Date getStartAt() {
		return this.startAt;
	}

	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "finish_at", length = 10)
	public Date getFinishAt() {
		return this.finishAt;
	}

	public void setFinishAt(Date finishAt) {
		this.finishAt = finishAt;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	public Set<Report> getReports() {
		return this.reports;
	}

	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}

}
