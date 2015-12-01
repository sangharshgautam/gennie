package org.telegram.client.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class GetFileResult extends Result<TgFile> {

	private TgFile result;
	
	@Override
	public TgFile getResult() {
		return this.result;
	}

}
