package org.telegram.client.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="TG_UPDATE")
public class Update extends Identifiable{

	@XmlElement(name = "update_id", required = true)
	@Id 
	private int id;
	
	@XmlElement(required = true)
	@OneToOne(cascade = CascadeType.ALL)
	private Telegram message;

	private boolean processed = false;
	
	public Update(){
		super();
	}
	
	private Update(int id, Telegram message) {
		super();
		this.id = id;
		this.message = message;
	}

	public static Update dummy(){
		return new Update(123, Telegram.dummy());
	}

	public int getId() {
		return id;
	}

	public Telegram getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "Update [id=" + id + ", message=" + message + "]";
	}
	
	public String getCommand(){
		String[] arr = this.getMessage().text().split(StringUtils.SPACE);
		return arr[0];
	}

	public Update markProcessed() {
		this.processed = true;
		return this;
	}
	
}
