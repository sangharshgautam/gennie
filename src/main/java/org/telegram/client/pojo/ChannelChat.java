package org.telegram.client.pojo;

import javax.persistence.DiscriminatorValue;
import javax.xml.bind.annotation.XmlType;

@DiscriminatorValue("channel")
@XmlType(name="channel")
public class ChannelChat extends Chat{

}
