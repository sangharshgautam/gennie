package org.telegram.client.pojo;

import javax.persistence.DiscriminatorValue;
import javax.xml.bind.annotation.XmlType;

@DiscriminatorValue("private")
@XmlType(name="private")
public class PrivateChat extends Chat {

}
