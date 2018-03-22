package model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rights")
	private Rights rights;

	@Column(name = "employee_code", nullable = false, length = 12)
	private String employeeCode;

	@Column(name = "password", length = 18)
	private String password;

	@Column(name = "email", length = 120)
	private String email;

	@Column(name = "name", length = 50)
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", length = 23)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", length = 23)
	private Date updatedAt;

	@Expose
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "approver")
	private Set<Report> reportsForApproverId;

	@Expose
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Report> reportsForUserId;

}
