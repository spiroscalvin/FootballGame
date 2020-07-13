package football;

public class Ball extends Game {

	public int ballX;
	public int ballY;
	Player currentPlayer;
	Player previousPlayer;
	
	public Ball() {
		ballX = 5;
		ballY = 3;	
		
		try {
			for (Player p : teamA.getPlayers()) {
				if((p.getPlayerX() == ballX) && (p.getPlayerY() == ballY)) {
					this.previousPlayer = currentPlayer;
					this.currentPlayer = p;
				}
			}
			for (Player p : teamB.getPlayers()) {
				if((p.getPlayerX() == ballX) && (p.getPlayerY() == ballY)) {
					this.previousPlayer = currentPlayer;
					this.currentPlayer = p;
				} else {
					this.currentPlayer = null;
				}
			}
		} catch (NullPointerException e) {
			e.getMessage();
		}
	}
		
	public int getBallX() {
		return this.ballX;
	}

	public void setBallX(int ballX) {
		this.ballX = ballX;
		checkCurrent();
	}

	public int getBallY() {
		return this.ballY;
	}

	public void setBallY(int ballY) {
		this.ballY = ballY;
		checkCurrent();
	}

	public void setCurrentPlayer(Player p) {
		this.setBallX(p.getPlayerX());
		this.setBallY(p.getPlayerY());
		
		this.currentPlayer = p;
		alterTeamBall(p);
	}
	
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	public Player getPreviousPlayer() {
		return previousPlayer;
	}

	public void setPreviousPlayer() {
		this.previousPlayer = currentPlayer;
	}
	/*-------------------------------------------------*/	
	
	public void checkCurrent() {
		if(board[ballX][ballY] != null) {
			this.currentPlayer = board[ballX][ballY];
		}
	}
	
	public void alterTeamBall(Player p) {
		if(p != null) {
			for (int i=0; i<3; i++) {
				if(p == teamA.getPlayers()[i]) {
					teamA.setHasNowBall(true);
					teamB.setHasNowBall(false);
				} else if(p == teamB.getPlayers()[i]) {
					teamB.setHasNowBall(true);
					teamA.setHasNowBall(false);
				}
			}
		}
		
		teamA.setHasNowBall(false);
		teamB.setHasNowBall(false);		
	}
	
	public void delegate(Player p) {
		try {
			if(currentPlayer == null) { 
				if(	(p.getPlayerX() == ballX) && ((p.getPlayerY() == ballY - 1) || (p.getPlayerY() == ballY + 1)) ){
					this.currentPlayer = p;
					this.ballX = p.getPlayerX();
					this.ballY = p.getPlayerY();
					
					if(previousPlayer.getTeamName() == currentPlayer.getTeamName()) {
						p.pases++;
						System.out.println("Successful transfer from " + p.getTeamName());
					} else {
						previousPlayer.faults++;
						System.out.println("Unsuccessful transfer from " + previousPlayer.getTeamName());
					}
					
					this.previousPlayer = null;
				}				
			}
		} catch(NullPointerException e) {
			System.out.println("Didn't find Player! Delegate randomly to closest player");
			delegate();	
		}
	}
	
	public void delegate() {
		if(board[ballX][ballY] != null) {
			int min = 0;
			
			for (int i=0; i<6; i++) {
				
				if(board[ballX][i] != null) {
					
					if(Math.abs(ballY-i) < min) {
						min = Math.abs(ballY-i);
						ballY = i;
					}
				}
			}
		
			this.currentPlayer = board[ballX][ballY];
			this.previousPlayer = null;
		}
	}
	
}
