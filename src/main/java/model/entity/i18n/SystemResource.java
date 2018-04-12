package model.entity.i18n;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "system_resource")
public class SystemResource {
	@EmbeddedId
	private SystemResourcePk pk;
	private String content;
}
