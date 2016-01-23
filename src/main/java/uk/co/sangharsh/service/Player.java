package uk.co.sangharsh.service;

public enum Player {
	X,O;

	public Player getOpponent() {
		return X.equals(this) ? O: X;
	}
}
