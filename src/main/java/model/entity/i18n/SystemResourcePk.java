package model.entity.i18n;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class SystemResourcePk implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name = "resource_code")
	private String resourceCode;
	@Column(name = "resource_type")
	private String resourceType;
	private String locale;
}
