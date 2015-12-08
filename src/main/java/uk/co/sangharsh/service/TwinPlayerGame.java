package uk.co.sangharsh.service;

import org.telegram.client.pojo.User;

public abstract class TwinPlayerGame implements Game {
	private User playerOne;

	public TwinPlayerGame(User playerOne) {
		this.playerOne = playerOne;
	}

}
