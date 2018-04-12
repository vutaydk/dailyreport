package model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tasks")
@Getter
@Setter
public class Task extends EntityBase {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "task_code", nullable = false, length = 4)
	private String taskCode;

	@Column(name = "name", length = 50)
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", length = 16)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", length = 16)
	private Date updatedAt;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
	private Set<ReportPart> reportDetails = new HashSet<>(0);

}
