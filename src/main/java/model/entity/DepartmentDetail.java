package model.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "department_details")
@Getter
@Setter
public class DepartmentDetail {

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "users"))
	@Id
	@Column(name = "user_id", unique = true, nullable = false)
	private int userId;

	@Column(name = "department_id", nullable = false)
	private Integer departmentId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", length = 16)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", length = 16)
	private Date updatedAt;

}
