package com.dailyreport.model;
// Generated Mar 5, 2018 4:53:59 PM by Hibernate Tools 5.2.8.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Report generated by hbm2java
 */
@Entity
@Table(name = "report", schema = "dbo", catalog = "dailyreport")
public class Report {

	private int id;
	private Project project;
	private User user;
	private Integer timeWork;
	private String note;
	private Date createdAt;
	private Date updatedAt;
	private String leaderApprove;
	private String managerApprove;
	private String directorApprove;

	public Report() {
	}

	public Report(int id) {
		this.id = id;
	}

	public Report(int id, Project project, User user, Integer timeWork, String note, Date createdAt, Date updatedAt,
			String leaderApprove, String managerApprove, String directorApprove) {
		this.id = id;
		this.project = project;
		this.user = user;
		this.timeWork = timeWork;
		this.note = note;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.leaderApprove = leaderApprove;
		this.managerApprove = managerApprove;
		this.directorApprove = directorApprove;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_code")
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_code")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "time_work")
	public Integer getTimeWork() {
		return this.timeWork;
	}

	public void setTimeWork(Integer timeWork) {
		this.timeWork = timeWork;
	}

	@Column(name = "note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
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

	@Column(name = "leader_approve", length = 18)
	public String getLeaderApprove() {
		return this.leaderApprove;
	}

	public void setLeaderApprove(String leaderApprove) {
		this.leaderApprove = leaderApprove;
	}

	@Column(name = "manager_approve", length = 18)
	public String getManagerApprove() {
		return this.managerApprove;
	}

	public void setManagerApprove(String managerApprove) {
		this.managerApprove = managerApprove;
	}

	@Column(name = "director_approve", length = 18)
	public String getDirectorApprove() {
		return this.directorApprove;
	}

	public void setDirectorApprove(String directorApprove) {
		this.directorApprove = directorApprove;
	}

}
