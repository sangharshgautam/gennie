package uk.co.sangharsh.service;

public class PlayerMove {
	private Player player;

	private Move move;

	public PlayerMove(Player player, Move move) {
		super();
		this.player = player;
		this.move = move;
	}

	public Player player() {
		return player;
	}

	public Move move() {
		return move;
	}
}
