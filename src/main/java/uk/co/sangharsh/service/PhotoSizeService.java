package uk.co.sangharsh.service;

import java.util.List;

import org.telegram.client.pojo.PhotoSize;

public interface PhotoSizeService extends Service<PhotoSize>{

	void mergeAll(List<PhotoSize> photo);

}
