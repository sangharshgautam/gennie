package uk.co.sangharsh.service;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.telegram.client.pojo.ReplyKeyboardMarkup;
import org.telegram.client.pojo.SendableImage;
import org.telegram.client.pojo.User;
import org.telegram.client.type.Command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TicTacToe extends TwinPlayerGame{

	private static final String WHITE_SPACE = " ";

	private PlayerMove[] matrix;
	
	private BufferedImage template;

	private Player player;

	private Player winner;
	
	public TicTacToe(User playerOne) throws IOException {
		super(playerOne);
		this.matrix  = new PlayerMove[9];
		setTemplate();
	}

	private void setTemplate() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file1 = new File(classLoader.getResource("template.png").getFile());
		BufferedImage originalImg = ImageIO.read(file1);
		template = new BufferedImage(originalImg.getWidth(), originalImg.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		template.getGraphics().drawImage(originalImg, 0, 0, originalImg.getWidth(), originalImg.getHeight(), null);
	}

	public List<List<String>> keyboard() {
		return keyboard(this.player);
	}

	private List<List<String>> keyboard(Player player) {
		return Arrays.asList(getRow(1), getRow(4), getRow(7), getRow4());
	}

	private List<String> getRow4() {
		return Arrays.asList(Command.QUIT.toString());
	}

	private List<String> getRow(int index) {
		return Arrays.asList(matrixValue(index-1), matrixValue(index), matrixValue(index+1));
	}
	private String matrixValue(int index) {
		return matrix[index] !=null ? WHITE_SPACE : this.player.toString()+(index+1);
	}
	public TicTacToe move(Command command, Player player) throws Exception {
		String string = command.toString();
		if(StringUtils.length(string) == 2){
			Move move = Move.valueOf("MOVE"+string.substring(1, 2));
			int moveIndex = move.getIndex();
			if(this.matrix[moveIndex] !=  null){
				throw new Exception("Invalid Move");
			}else{
				this.matrix[moveIndex] = new PlayerMove(player, move);
				drawMove(player.getImage(), move);
			}
		}
		boolean mate = checkMate();
		if(mate){
			this.winner =  player;
		}else{
			if(player.equals(this.player)){
				return systemMove();
			}
		}
		return this;
	}
	public TicTacToe move(Command command) throws Exception {
		return move(command, this.player);
	}
	private TicTacToe systemMove() throws Exception {
		//check own doubles to win
		boolean winPossible = isWinningMove(0, 1, 2);
		if(winPossible){
			return winTheGame(0, 1, 2);
		}else{
			for(int i =0;i<matrix.length;i++){
				PlayerMove playerMove =  matrix[i];
				if(playerMove == null){
					Player opponent = this.player.getOpponent();
					return move(Command.valueOf(opponent.toString()+(i+1)), opponent);
				}
			}
		}
		return this;
	}


	private TicTacToe winTheGame(int... indexes) throws Exception {
		int winIndex  = -1;
		for(int index : indexes){
			PlayerMove playerMove = matrix[index];
			if(playerMove == null){
				winIndex = index;
				break;
			}
		}
		Player opponent = this.player.getOpponent();
		return winIndex !=-1 ? move(Command.valueOf(opponent.toString()+(winIndex+1)), opponent) :  this;
	}

	private boolean isWinningMove(int...indexes) {
		Player system = this.player.getOpponent();
		int counter =0;
		for(int index : indexes){
			PlayerMove playerMove = matrix[index];
			if(playerMove!=null){
				if(this.player.equals(playerMove.player())){
					return false;
				}else if(system.equals(playerMove.player())){
					counter++;
				}
			}
		}
		return counter ==2;
	}

	private boolean checkMate() {
		boolean row1 = checkLine(0, 1, 2);
		boolean row2 = checkLine(3, 4, 5);
		boolean row3 = checkLine(6, 7, 8);
		
		boolean col1 = checkLine(0, 3, 6);
		boolean col2 = checkLine(1, 4, 7);
		boolean col3 = checkLine(2, 5, 8);
		
		
		boolean diagonal1 = checkLine(0, 4, 8);
		boolean diagonal2 = checkLine(2, 4, 6);
		boolean checkmate = BooleanUtils.xor(new boolean[]{row1, row2, row3, col1, col2, col3, diagonal1, diagonal2});
		return checkmate;
	}

	private boolean checkLine(int index1, int index2, int index3) {
		PlayerMove first = matrix[index1];
		PlayerMove second = matrix[index2];
		PlayerMove third = matrix[index3];
		
		if(Arrays.asList(first, second, third).contains(null)){
			return false;
		}else{
			boolean end = first.player().equals(second.player()) && second.player().equals(third.player());
			if(end){
				drawLine(first, third);
			}
			return end; 
		}
	}

	private void drawLine(PlayerMove a, PlayerMove b) {
		Graphics2D graphics = (Graphics2D)this.template.getGraphics();
		graphics.setColor(Color.BLUE);
		graphics.setStroke(new BasicStroke(10F));
		graphics.drawLine(a.move().getX()+25, a.move().getY(), b.move().getX()+25, b.move().getY()+40);
	}
	private void drawMove(BufferedImage playerBi, Move move) {
		Graphics2D graphics = (Graphics2D)this.template.getGraphics();
		graphics.drawImage(playerBi, move.getX(), move.getY(), playerBi.getWidth(), playerBi.getHeight(), null);
	}
	public static void main(String[] args) throws Exception {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		System.out.println(new GsonBuilder().setPrettyPrinting().serializeNulls().create().toJson(src));
		TicTacToe game = new TicTacToe(null).set(Player.X).move(Command.X1).move(Command.X4).move(Command.X7);
		System.out.println(gson.toJson(game.keyboard()));
		FileUtils.copyFile(game.getBoard(), new File("C:/sang/test.png"));
		/*List<List<String>> keyboard = game.move(Command.X5).move(Command.X8).keyboard();
		
		System.out.println(gson.toJson(keyboard));
		System.out.println(gson.toJson(new TicTacToe(null).set(Player.X).move(Command.X4).move(Command.X5).move(Command.X6).keyboard()));
		System.out.println(gson.toJson(new TicTacToe(null).set(Player.X).move(Command.X7).move(Command.X8).move(Command.X9).keyboard()));
		
		System.out.println(gson.toJson(game.move(Command.X4).move(Command.X7).keyboard()));
		System.out.println(gson.toJson(new TicTacToe(null).set(Player.X).move(Command.X2).move(Command.X5).move(Command.X8).keyboard()));
		System.out.println(gson.toJson(new TicTacToe(null).set(Player.X).move(Command.X3).move(Command.X6).move(Command.X9).keyboard()));
		
		
		System.out.println(gson.toJson(game.move(Command.X5).move(Command.X9).keyboard()));
		System.out.println(gson.toJson(new TicTacToe(null).set(Player.X).move(Command.X3).move(Command.X5).move(Command.X7).keyboard()));*/

	}

	public SendableImage reply(String message, String command) throws IOException {
		
		String player = command;
		if(command.trim().length() >1){
			player = command.substring(0, 1);
		}
		List<List<String>> keyboard = keyboard(Player.valueOf(player));
		return reply(message, command, keyboard);
	}
	
	public SendableImage reply(String message, String command, List<List<String>> keyboard) throws IOException {
		return SendableImage.create(message, ReplyKeyboardMarkup.selective(keyboard).oneTime(), this.getBoard() );
	}

	private File getBoard() throws IOException {
		File file = File.createTempFile("tictactoe", ""+System.currentTimeMillis()+".png");
		ImageIO.write(template, "png", file);
		return file;
	}

	public TicTacToe set(Player player) {
		this.player = player;
		return this;
	}

	public boolean ended() {
		return this.winner != null;
	}

	public boolean isPlayerWinner() {
		return this.player.equals(this.winner);
	}
}
