package uk.co.sangharsh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.client.pojo.PhotoSize;

import uk.co.sangharsh.dao.Dao;
import uk.co.sangharsh.dao.PhotoSizeDao;

@Service
@Transactional
public class PhotoSizeServiceImpl extends AsbtractServiceImpl<PhotoSize> implements PhotoSizeService {
	
	@Autowired 
	private PhotoSizeDao photoSizeDao;
	
	@Override
	protected Dao<PhotoSize> getDao() {
		return this.photoSizeDao;
	}

	@Override
	public void mergeAll(List<PhotoSize> photoSizes) {
		for(PhotoSize photoSize : photoSizes){
			this.photoSizeDao.merge(photoSize);
		}
	}
}
