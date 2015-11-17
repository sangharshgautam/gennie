package uk.co.sangharsh.dao;

import org.springframework.stereotype.Repository;
import org.telegram.client.pojo.PhotoSize;

@Repository
public class PhotoSizeDaoImpl extends  AbstractBaseDaoImpl<PhotoSize> implements PhotoSizeDao{

	protected PhotoSizeDaoImpl() {
		super(PhotoSize.class);
	}

}
