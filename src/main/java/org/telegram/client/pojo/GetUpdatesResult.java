package org.telegram.client.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class GetUpdatesResult extends AbstractResult<List<Update>> {

	private List<Update> result;
	
	@Override
	public List<Update> getResult() {
		return this.result;
	}

}
