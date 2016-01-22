package uk.co.sangharsh.service;

public enum Move {
	X1(0,0, 0+50, 0+100),
	X2(0,1, 110+50, 0+100),
	X3(0,2, 220+50, 0+100),
	X4(1,0, 0+50, 110+100),
	X5(1,1,110+50, 110+100),
	X6(1,2, 220+50, 110+100),
	X7(2,0, 0+50, 220+100),
	X8(2,1, 110+50, 220+100),
	X9(2,2, 220+50, 220+100);
	
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
