package football;

public class Forward extends Player {
	
	public Forward(String playerName, int playerX, int playerY, int shirtNumber) {
		super(playerName, playerX, playerY, shirtNumber);
	}

	@Override
	public void specialMove(Ball ball) {
	// if team-mate has ball, then move 2 boxes away from closer opposite player (get in movingLine)
		if(hasNowBall() == true) {
			
			for (int i=0; i<6; i++) {
				Player p = ball.board[getPlayerX()][i];
				
				if((p != null) && (p.getTeamName() == getTeamName()) && (i != getPlayerY())) {
					
					if(i < playerY) {
						playerY -= 2;
						if(playerY < 0) { playerY = 0; }
					} else {
						playerY += 2;
						if(playerY > 5) { playerY = 5; }
					}
					
				}
			}
		}
	}

	@Override
	public void transfer(Ball ball) throws NullPointerException {
	// if player has the ball send it with better probability in center columns
		
		if(super.hasNowBall() == true) {
			double prob = Math.random();
			
			if(prob < 0.6) {		
				ball.setBallX(getTargetLine());
				int temp = (Math.random() < 0.5) ? 2 : 3;
				ball.setBallY(temp);
			} else 
				if(prob < 0.9) {
					ball.setBallX(getTargetLine());
					int temp = (Math.random() < 0.5) ? 1 : 4;
					ball.setBallY(temp);
				} else {
					ball.setBallX(getTargetLine());
					int temp = (Math.random() < 0.5) ? 0 : 5;
					ball.setBallY(temp);
				}
		}
	}

}
