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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "employee_code", nullable = false, length = 12)
	private String employeeCode;

	@Column(name = "password", nullable = false, length = 18)
	private String password;

	@Column(name = "email", length = 120)
	private String email;

	@Column(name = "name", length = 50)
	private String name;

	@Column(name = "rights")
	private Integer rights;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", length = 16)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", length = 16)
	private Date updatedAt;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "approver_id")
	private Set<Report> reportsForApproverId = new HashSet<>(0);

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private Set<Report> reportsForUserId = new HashSet<>(0);

//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "user_id")
//	private DepartmentDetail departmentDetail;

	@OneToMany
	@JoinColumn(name = "project_manager")
	private Set<Project> projects = new HashSet<Project>(0);

}
