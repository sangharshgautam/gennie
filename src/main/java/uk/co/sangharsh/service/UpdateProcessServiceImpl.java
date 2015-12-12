package uk.co.sangharsh.service;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

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
	public void process() throws IOException {
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
				this.games.remove(from.getIdAsString());
			case HI:
				reply =  SendableText.create("Select a game...");
				keyboard = new ArrayList<List<String>>(){{
					add(new ArrayList<String>(){{
						add(Command.TICTACTOE.toString());
					}});
					add(new ArrayList<String>(){{
						add(Command.CHESS.toString());
					}});
				}};
				markup = ReplyKeyboardMarkup.selective(keyboard).oneTime();
				break;
			case TICTACTOE:
				this.games.put(from.getIdAsString(), new TicTacToe(from));
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
			case O:
				reply = SendableText.create("Enter your move");
				Game game = this.games.get(from.getIdAsString());
				TicTacToe ticTacToe = (TicTacToe)game;
				keyboard = ticTacToe.keyboard(command.toString());
				markup = ReplyKeyboardMarkup.selective(keyboard).oneTime();
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
				/*BufferedImage bi = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
				Graphics graphics = bi.getGraphics();
				graphics.setColor(Color.WHITE);
				graphics.fillRect(0, 0, 200, 200);
				graphics.setColor(Color.BLUE);
				graphics.drawLine(0, 0, 100, 100);
				File file = File.createTempFile("tictactoe", ""+System.currentTimeMillis()+".jpg");
				ImageIO.write(bi, "jpeg", file);*/
				reply = SendableText.create("Enter your move");
				game = new TicTacToe(from);
				ticTacToe = ((TicTacToe)game).move(command.toString());
				keyboard = ticTacToe.keyboard(command.toString());
				markup = ReplyKeyboardMarkup.selective(keyboard).oneTime();
				break;
			case UNKNOWN:
			default:
//				int sentiment = sentimentAnalyzingService.findSentiment(message.text());
//				reply = SendableText.create("Sentiment-> "+sentiment);
				reply = SendableText.thank(from);
				break;
			}
			Result<Boolean> actionSet = telegramService.setStatus(message, ChatAction.TYPING);
			result = telegramService.message(message.chat().getIdAsString(), reply, markup);
			if(result.isOk()){
				updateService.update(update.markProcessed());
			}
		}
	}
}
