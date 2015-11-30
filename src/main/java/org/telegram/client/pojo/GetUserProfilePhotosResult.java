package org.telegram.client.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class GetUserProfilePhotosResult extends Result<UserProfilePhotos> {

	private UserProfilePhotos result;
	
	@Override
	public UserProfilePhotos getResult() {
		return this.result;
	}
}
