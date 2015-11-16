package org.telegram.client.pojo;

import javax.persistence.DiscriminatorValue;
import javax.xml.bind.annotation.XmlType;

@DiscriminatorValue("group")
@XmlType(name="group")
public class GroupChat extends Chat {

}
