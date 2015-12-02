package uk.co.sangharsh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.client.pojo.ReplyKeyboardMarkup;
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
	
	/*@Autowired
	private SentimentAnalyzingService sentimentAnalyzingService;*/

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
			ReplyKeyboardMarkup markup = null;
			List<List<String>> keyboard;
			Command command = Command.lookup(message.text().toUpperCase());
			User from = message.from();
			switch (command) {
			case WHOAMI:
				reply =  SendableText.create(from.toString());
				break;
			case QUIT:
				//terminate game
			case HI:
				reply =  SendableText.create("Select a game...");
				keyboard = new ArrayList<List<String>>(){{
					add(new ArrayList<String>(){{
						add(Command.TICTACTOE.toString());
					}});
				}};
				markup = ReplyKeyboardMarkup.selective(keyboard).oneTime();
				break;
			case TICTACTOE:
				reply =  SendableText.create(command.toString()+ ": Select your player ...");
				keyboard = new ArrayList<List<String>>(){{
					add(new ArrayList<String>(){{
						add("X");
						add("O");
					}});
					add(new ArrayList<String>(){{
						add(Command.QUIT.toString());
					}});
				}};
				markup = ReplyKeyboardMarkup.selective(keyboard).oneTime();
				break;
			case X:
				reply = SendableText.create("Enter your move");
				keyboard = new ArrayList<List<String>>(){{
					add(new ArrayList<String>(){{
						add("1");
						add("2");
						add("3");
					}});
					add(new ArrayList<String>(){{
						add("4");
						add("5");
						add("6");
					}});
					add(new ArrayList<String>(){{
						add("7");
						add("8");
						add("9");
					}});
					add(new ArrayList<String>(){{
						add(Command.QUIT.toString());
					}});
				}};
				break;
			case O:
				reply = SendableText.create("Enter your move");
				keyboard = new ArrayList<List<String>>(){{
					add(new ArrayList<String>(){{
						add("1");
						add("2");
						add("3");
					}});
					add(new ArrayList<String>(){{
						add("4");
						add("5");
						add("6");
					}});
					add(new ArrayList<String>(){{
						add("7");
						add("8");
						add("9");
					}});
					add(new ArrayList<String>(){{
						add(Command.QUIT.toString());
					}});
				}};
				break;
			case UNKNOWN:
			default:
//				int sentiment = sentimentAnalyzingService.findSentiment(message.text());
//				reply = SendableText.create("Sentiment-> "+sentiment);
				reply = SendableText.thank(from);
				break;
			}
			Result<Boolean> actionSet = telegramService.setStatus(message, ChatAction.TYPING);
			result = telegramService.reply(message, reply, markup);
			if(result.isOk()){
				updateService.update(update.markProcessed());
			}
		}
	}
}
