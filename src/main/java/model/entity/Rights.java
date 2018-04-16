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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rights")
@Getter
@Setter
public class Rights extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name", length = 50)
	private String name;

	@Column(name = "level")
	private Integer level;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", length = 16)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", length = 16)
	private Date updatedAt;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rights")
	private Set<User> users = new HashSet<>(0);

}
