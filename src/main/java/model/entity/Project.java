package model.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "projects")
@Getter
@Setter
public class Project extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Column(name = "project_manager", nullable = false)
	private Integer projectManager;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", length = 16)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", length = 16)
	private Date updatedAt;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Set<Report> reports = new HashSet<>(0);

}
