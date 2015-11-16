package org.telegram.client.pojo;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING, length = 10)
@XmlSeeAlso({ AudioMessage.class, DocumentMessage.class, PhotoSize.class, StickerMessage.class, VideoMessage.class, VoiceMessage.class })
public abstract class MediaMessage implements Message {
	@XmlElement(name = "file_id", required = true)
	@Id
	private String id;

	@XmlElement(name = "file_size")
	private int size;
}
