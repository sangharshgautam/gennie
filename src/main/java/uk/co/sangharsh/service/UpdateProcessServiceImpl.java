package uk.co.sangharsh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.client.pojo.Result;
import org.telegram.client.pojo.SendableText;
import org.telegram.client.pojo.Telegram;
import org.telegram.client.pojo.Update;
import org.telegram.client.pojo.User;
import org.telegram.client.type.ChatAction;
import org.telegram.client.type.Command;

@Service
@Transactional
public class UpdateProcessServiceImpl implements UpdateProcessService {

	@Autowired
	private TelegramService telegramService;

	@Autowired
	private UpdateService updateService;

	@Override
	public void pullUpdates() {
		Result<List<Update>> result = telegramService.getUpdates();
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
			Result<Telegram> result = null;
			SendableText reply;
			Command command = Command.lookup(message.text().toUpperCase());
			User from = message.from();
			switch (command) {
			case WHOAMI:
				reply =  SendableText.create(from.toString());
				break;
			case UNKNOWN:
			default:
				reply = SendableText.thank(from);
				break;
			}
			Result<Boolean> actionSet = telegramService.setStatus(message, ChatAction.TYPING);
			result = telegramService.reply(message, reply);
			if(result.isOk()){
				updateService.update(update.markProcessed());
			}
		}
	}
}
