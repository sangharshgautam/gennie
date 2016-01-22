package uk.co.sangharsh.service;

public enum Move {
	MOVE1(0,0, 0+50, 0+100),
	MOVE2(0,1, 110+50, 0+100),
	MOVE3(0,2, 220+50, 0+100),
	MOVE4(1,0, 0+50, 110+100),
	MOVE5(1,1,110+50, 110+100),
	MOVE6(1,2, 220+50, 110+100),
	MOVE7(2,0, 0+50, 220+100),
	MOVE8(2,1, 110+50, 220+100),
	MOVE9(2,2, 220+50, 220+100);
	
	private int indexX;
	private int indexY;
	private int x;
	private int y;

	Move(int indexX, int indexY, int x, int y){
		this.indexX = indexX;
		this.indexY = indexY;
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getIndexX() {
		return indexX;
	}

	public int getIndexY() {
		return indexY;
	}
	
}
