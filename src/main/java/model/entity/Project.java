package model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
@Entity
@Table(name = "projects")
public class Project {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "project_code", nullable = false, length = 4)
	private String projectCode;

	@Column(name = "name", length = 50)
	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name = "start_at", length = 10)
	private Date startAt;

	@Temporal(TemporalType.DATE)
	@Column(name = "finish_at", length = 10)
	private Date finishAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", length = 23)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", length = 23)
	private Date updatedAt;

	@Expose
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	private Set<Report> reports;

}
