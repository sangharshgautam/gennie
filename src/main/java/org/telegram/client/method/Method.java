package org.telegram.client.method;

import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import org.telegram.client.param.Param;

public enum Method {
    getMe,
    sendMessage,
    forwardMessage,
    sendPhoto,
    sendAudio,
    sendDocument,
    sendSticker,
    sendVideo,
    sendLocation,
    sendChatAction,
    getUserProfilePhotos,
    getUpdates,
    setWebhook,
    getFile;
    
	public <T> T get(WebTarget webtarget, Class<T> clazz) {
		return webtarget.path(this.toString()).request().get(clazz);
	}
	
	public <T> T post(WebTarget webtarget, Class<T> clazz, Entity<?> entityPayload) {
		return webtarget.path(this.toString()).request().post(entityPayload, clazz);
	}
	
	public <T> T get(WebTarget webtarget, Class<T> clazz, Map<Param, String> params) {
		return setParams(webtarget.path(this.toString()), params).request().get(clazz);
	}
	
	public <T> T get(WebTarget webtarget, GenericType<T> genericType) {
		return webtarget.path(this.toString()).request().get(genericType);
	}
	
	public <T> T get(WebTarget webtarget, GenericType<T> genericType, Map<Param, String> params) {
		return setParams(webtarget.path(this.toString()), params).request().get(genericType);
	}
	

	
	private WebTarget setParams(WebTarget target, Map<Param, String> params) {
		for(Entry<Param, String> entry: params.entrySet()){
			String key = entry.getKey().toString().toLowerCase();
			String value = entry.getValue();
			target  = target.queryParam(key, value);
		}
		return target;
	}
}
