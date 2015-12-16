package com.slack.api.client.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Channel {
	private String id;
	private String name;
	
	@XmlElement(name="is_channel")
	private boolean channel;
	
	private String created;
	
	private String creator;
	
	@XmlElement(name="is_archived")
	private boolean archived;
	
	@XmlElement(name="is_general")
	private boolean general;
	
	@XmlElement(name="is_member")
	private boolean member;
	
	private List<String> members;
	
	private Detail topic;
	
	private Detail purpose;
	
	private int num_members;
	public String id(){
		return this.id;
	}
}

