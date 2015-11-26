package uk.co.sangharsh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.client.pojo.GetUpdatesResult;
import org.telegram.client.pojo.MessageResult;
import org.telegram.client.pojo.Telegram;
import org.telegram.client.pojo.TextReply;
import org.telegram.client.pojo.Update;

@Service
@Transactional
public class UpdateProcessServiceImpl implements UpdateProcessService {

	@Autowired
	private TelegramService telegramService;

	@Autowired
	private UpdateService updateService;

	@Override
	public void pullUpdates() {
		GetUpdatesResult result = telegramService.getUpdates();
		if (result.isOk()) {
			pushUpdates(result.getResult());
		}
	}

	@Override
	public void pushUpdates(List<Update> updates) {
		for (Update update : updates) {
			push(update);
		}
	}

	@Override
	public void push(Update update) {
		Update dbUpdate = updateService.findBy(update.getId());
		if (dbUpdate == null) {
			updateService.create(update);
		} else {
			System.out.println("Update already received");
		}
	}

	@Override
	public void process() {
		List<Update> updates = updateService.findUnprocessed();
		for (Update update : updates) {
			Telegram message = update.getMessage();
			MessageResult result = null;
			TextReply reply = TextReply.thank(message.from());
			if("whoami".equals(message.text())){
				reply = new TextReply(message.from().toString());
				result = telegramService.send(update, false, reply);
			}
			if(result.isOk()){
				updateService.update(update.markProcessed());
			}
		}
	}
}
