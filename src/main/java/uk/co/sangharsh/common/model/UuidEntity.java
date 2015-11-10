package uk.co.sangharsh.common.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.hibernate.annotations.GenericGenerator;
@XmlAccessorType(XmlAccessType.FIELD)
@MappedSuperclass
public abstract class UuidEntity {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid" )
	@Column(name= "UUID")
	private String uuid;

	public String getUuid() {
		return uuid;
	}
	
	@PrePersist
	protected void onCreate(){
		this.uuid = UUID.randomUUID().toString();
	}
}
