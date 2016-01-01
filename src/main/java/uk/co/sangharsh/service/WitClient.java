package uk.co.sangharsh.service;

import ai.wit.api.client.pojo.Message;

public interface WitClient {
	Message query(String query);
}
