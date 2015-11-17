package org.telegram.client.pojo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlType;

@Entity
@DiscriminatorValue("channel")
@XmlType(name="channel")
public class ChannelChat extends Chat{

}
