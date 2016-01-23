package uk.co.sangharsh.service;

public enum Move {
	MOVE1(0, 0+50, 0+100),
	MOVE2(1, 110+50, 0+100),
	MOVE3(2, 220+50, 0+100),
	MOVE4(3, 0+50, 110+100),
	MOVE5(4, 110+50, 110+100),
	MOVE6(5, 220+50, 110+100),
	MOVE7(6, 0+50, 220+100),
	MOVE8(7, 110+50, 220+100),
	MOVE9(8, 220+50, 220+100);
	
	private int index;
	private int x;
	private int y;

	Move(int index, int x, int y){
		this.index = index;
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getIndex() {
		return index;
	}
	
}
