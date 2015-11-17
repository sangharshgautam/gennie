package org.telegram.client.pojo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="STICKER")
@DiscriminatorValue("STICKER")
public class StickerMessage extends PhotoSize{
//	@OneToMany
//	private PhotoSize thumb; 	//Optional. Sticker thumbnail in .webp or .jpg format
}
