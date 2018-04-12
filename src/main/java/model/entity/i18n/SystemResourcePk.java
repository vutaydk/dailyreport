package model.entity.i18n;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import common.i18n.ResourceType;
import lombok.Data;

@Embeddable
@Data
public class SystemResourcePk implements Serializable{
	@Column(name = "resource_code")
	private String resourceCode;
	@Column(name = "resource_type")
	@Enumerated(EnumType.STRING)
	private ResourceType resourceType;
	private String locale;
}
