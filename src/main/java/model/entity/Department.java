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

import lombok.Data;

@Data
@Entity
@Table(name = "departments")
public class Department {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "department_code", length = 4)
	private String departmentCode;

	@Column(name = "name", length = 50)
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", length = 23)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", length = 23)
	private Date updatedAt;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
	private Set<DepartmentDetail> departmentDetails = new HashSet<>(0);

}
