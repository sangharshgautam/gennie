package uk.co.sangharsh.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.client.pojo.ReplyKeyboardMarkup;
import org.telegram.client.pojo.Result;
import org.telegram.client.pojo.SendableImage;
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
	
	private Map<String, Game> games = new HashMap<String, Game>();

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
			List<List<String>> keyboard;
			Command command = Command.lookup(message.text().toUpperCase());
			User from = message.from();
			try{
				switch (command) {
				case WHOAMI:
					reply =  SendableText.create(from.toString());
					break;
				case QUIT:
					//terminate game
					this.games.remove(from.getIdAsString());
				case HI:
					keyboard = new ArrayList<List<String>>(){{
						add(new ArrayList<String>(){{
							add(Command.TICTACTOE.toString());
						}});
						add(new ArrayList<String>(){{
							add(Command.CHESS.toString());
						}});
					}};
					reply =  SendableText.create("Select a game...", ReplyKeyboardMarkup.selective(keyboard).oneTime());
					break;
				case TICTACTOE:
					this.games.put(from.getIdAsString(), new TicTacToe(from));
					keyboard = new ArrayList<List<String>>(){{
						add(new ArrayList<String>(){{
							add(Player.X.toString());
							add(Player.O.toString());
						}});
						add(new ArrayList<String>(){{
							add(Command.QUIT.toString());
						}});
					}};
					reply =  SendableText.create(command.toString()+ ": Select your player ...", ReplyKeyboardMarkup.selective(keyboard).oneTime());
					break;
				case X:
				case O:
					Game game = this.games.get(from.getIdAsString());
					TicTacToe ticTacToe = (TicTacToe)game;
					reply = ticTacToe.set(Player.valueOf(command.toString())).reply(command.toString());
					break;
				case O1:
				case O2:
				case O3:
				case O4:
				case O5:
				case O6:
				case O7:
				case O8:
				case O9:
				case X1:
				case X2:
				case X3:
				case X4:
				case X5:
				case X6:
				case X7:
				case X8:
				case X9:
					if(!this.games.containsKey(from.getIdAsString())){
						this.games.put(from.getIdAsString(), new TicTacToe(from));
					}
					game = this.games.get(from.getIdAsString());
					ticTacToe = ((TicTacToe)game).move(command);
					if(ticTacToe.ended()){
						System.out.println("Player Winner "+ticTacToe.isPlayerWinner());
					}
					reply = ticTacToe.reply(command.toString());
					break;
				case UNKNOWN:
				default:
	//				int sentiment = sentimentAnalyzingService.findSentiment(message.text());
	//				reply = SendableText.create("Sentiment-> "+sentiment);
					reply = SendableText.thank(from);
					break;
				}
			} catch (Exception e) {
				reply =  SendableText.create(e.getMessage());
			}
			Result<Boolean> actionSet = telegramService.setStatus(message, ChatAction.TYPING);
			result = msg(message, reply);
			if(result.isOk()){
				updateService.update(update.markProcessed());
			}
		}
	}

	private Result<Telegram> msg(Telegram message, SendableText reply) {
		if(reply instanceof SendableImage){
			return telegramService.message(message.chat().getIdAsString(), (SendableImage)reply);
		}
		return telegramService.message(message.chat().getIdAsString(), reply);
	}
}
