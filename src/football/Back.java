package football;

public class Back extends Player {

	private boolean yellowCard;
	public int steal;
	
	public Back(String playerName, int playerX, int playerY, int shirtNumber) {
		super(playerName, playerX, playerY, shirtNumber);
		this.yellowCard = false;
		steal = 0;
	}

	public int getSteal() {
		return steal;
	}

	@Override
	public void specialMove(Ball ball) throws NullPointerException {
		if(hasNowBall() == false) {
			
			// if exists opposite Player with the ball on closer boxes
			if(	(ball.getBallX() == playerX) && ((ball.getBallY() == playerY - 1) || (ball.getBallY() == playerY + 1))) {
				double prob = Math.random();
				
				if(prob < 0.7) {				// 70% take the ball
					ball.setBallY(playerY);
					steal++;
					System.out.println(getPlayerName() + " stole the ball");
				} else if(prob < 0.9) {			// 20% -> yellow card
					
					if(yellowCard == false) {
						this.yellowCard = true;
						System.out.println(getPlayerName() + " took Yellow card");
					} else {
						deletePlayer(getPlayerName());
						System.out.println(getPlayerName() + " took Red card\n");
					}
				}
			}
		}
	}	
	
}
