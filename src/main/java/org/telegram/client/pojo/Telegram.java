package org.telegram.client.pojo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang3.StringUtils;
import org.telegram.client.adpater.UnixDateTimeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="TG_MESSAGE")
public class Telegram extends Identifiable{

	public Telegram() {
		super();
	}

	private Telegram(int id, User from, Date date) {
		super();
		this.id = id;
		this.from = from;
		this.date = date;
	}

	@XmlElement(name="message_id", required = true)
	@Id 
	private int id;
	
	@XmlElement(required = true)
	@ManyToOne(cascade = CascadeType.ALL)
	private User from;
	
	@XmlElement
	@Transient
	private Chat chat;;
	
	@XmlElement(required = true)
	@XmlJavaTypeAdapter(value = UnixDateTimeAdapter.class)
	private Date date;
	
/*	@XmlElement(required = true)
	private Conversation chat;*/
	
	@XmlElement(name="forward_from")
	@ManyToOne(cascade = CascadeType.ALL)
	private User forwardFrom;
	
	@XmlElement(name="forward_date")
	private String forwardDate;
	
	@XmlElement(name="reply_to_message")
	@Transient
	private Telegram replyToMessage;
	
	@XmlElement
	@Column
	private String text;
	
	@XmlElements(value={
		@XmlElement(name="audio", type=AudioMessage.class),
		@XmlElement(name="document", type=DocumentMessage.class),
//		@XmlElement(name="photo", type=List.class),
		@XmlElement(name="sticker", type=StickerMessage.class),
		@XmlElement(name="video", type=VideoMessage.class),
		@XmlElement(name="contact", type=ContactMessage.class),
		@XmlElement(name="location", type=LocationMessage.class)
	})
	@Transient
	private Message details;
	
	@XmlElement(name="new_chat_participant")
	@Transient
	private User newChatParticipant;
	
	@XmlElement(name="left_chat_participant")
	@Transient
	private User leftChatParticipant;
	
	@XmlElement(name="new_chat_title")
	@Transient
	private String newChatTitle;
	
	@Transient
	private List<PhotoSize> newChatPhoto;
	
	private boolean deleteChatPhoto;
	
	private boolean groupChatCreated;
	
	private String caption;


	public int getId() {
		return id;
	}

	public User getFrom() {
		return from;
	}
	
	public String text() {
		return StringUtils.isNotBlank(text) ? text.trim() : StringUtils.EMPTY;
	}

	@Override
	public String toString() {
		return "Telegram [id=" + id + ", from=" + from + ", chat=" + chat
				+ ", date=" + date + ", forwardFrom=" + forwardFrom
				+ ", forwardDate=" + forwardDate + ", replyToMessage="
				+ replyToMessage + ", text=" + text + ", details=" + details
				+ ", newChatParticipant=" + newChatParticipant
				+ ", leftChatParticipant=" + leftChatParticipant
				+ ", newChatTitle=" + newChatTitle + ", newChatPhoto="
				+ newChatPhoto + ", deleteChatPhoto=" + deleteChatPhoto
				+ ", groupChatCreated=" + groupChatCreated + ", caption="
				+ caption + "]";
	}

	public static Telegram dummy() {
		return new Telegram(234, User.dummy(), new Date());
	}

	public Chat chat() {
		return chat;
	}

	public Message getDetails() {
		return details;
	}
}
