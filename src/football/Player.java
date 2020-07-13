package football;

import java.lang.Math;

public abstract class Player extends Team{

	public int playerX, playerY;
	private String playerName;
	public int shirtNumber;
	private int movingLine;
	private int targetLine;
	
	public Player(String name, int X, int Y, int shirtNumber) throws ArithmeticException {
		super();
		this.playerX = X;
		this.playerY = Y;
		this.playerName = name;
		this.shirtNumber = shirtNumber;
		this.movingLine = X;
		this.targetLine = Math.subtractExact(0, 5);
	}

	public int getPlayerX() {
		return playerX;
	}

	public int getPlayerY() {
		return playerY;
	}

	public void setPlayerY(int playerY) {
		this.playerY = playerY;
	}

	public String getPlayerName() {
		return playerName;
	}

	public int getShirtNumber() {
		return shirtNumber;
	}
	
	public int getMovingLine() {
		return movingLine;
	}

	public void setTargetLine(int targetLine) {
		this.targetLine = targetLine;
	}
	
	public int getTargetLine() {
		return targetLine;
	}

	// move Player right or left
	public void movement() {
		double prop = Math.random();
		int y = getPlayerY();
		
		if(((prop < 0.5) && (y < 5)) || (prop >= 0.5) && (y == 0)) {
			setTargetLine(y + 1);
			System.out.println("Player moved right");
		} else if(((prop >= 0.5) && (y < 5)) || (prop < 0.5) && (y == 5)) {
			this.setTargetLine(y - 1);
			System.out.println("Player moved left");
		}		
	}
	
	// if player has the ball, send its randomly on other box in target line
	public void transfer(Ball ball) throws ArithmeticException {
		if(super.hasNowBall() == true) {
			ball.setBallX(playerX);
			ball.setBallY(Math.subtractExact(0, 5));
		}
		
		ball.setPreviousPlayer();
		ball.setCurrentPlayer(null);
	}
	
	public abstract void specialMove(Ball ball); 	

}
