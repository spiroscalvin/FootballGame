package football;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

	public Team teamA;
	public Team teamB;
	public Ball ball;
	private int rounds = 0;
	Player[][] board = new Player [10][6];
	List<Team> teams = new ArrayList<>();
	
	public int getRounds() {
		return rounds;
	}
	
	public void setRounds(int rounds) {
		this.rounds = rounds;		
	}
	
	public void setList() {
		teams = new ArrayList<>();
		this.teams.add(teamA);
		this.teams.add(teamB);
	}
	
	public void insertToTeam(boolean team, Player p) {
		if(team == true) {
			teamA.addPlayer(p);
		} else {
			teamB.addPlayer(p);
		}	
	}

	public void runTurn() {
		rounds++;
		boolean cond = true;
		
		while (cond) {
			Collections.shuffle(teams);
			
			for (int i=1; i<3; i++) {
				teams.get(i).action(ball);
			}
						
			// if ball is closer to goal
			if((ball.getBallX() == 8) && ((ball.getBallY() == 2) || (ball.getBallY() == 3))) {
				double prob = Math.random();
				if(prob < 0.5) {
					teamB.addGoal(1); // 50% goal
					System.out.println(teamB.getTeamName() + ": Goooooooal");
				} else {
					ball.delegate(); // delegate to closest player
				}
			}
			if((ball.getBallX() == 1) && ((ball.getBallY() == 2) || (ball.getBallY() == 3))) {
				double prob = Math.random();
				if(prob < 0.5) {
					teamA.addGoal(1); // 50% goal
					System.out.println(teamA.getTeamName() + ": Goooooooal");
				} else {
					ball.delegate(); // delegate to closest player
				}
			}
			
			cond = false;
		}
		
		// refresh players on board
		for (int i=0; i<3; i++) {
			int X = teamA.getPlayers()[i].getMovingLine();
			int Y = teamA.getPlayers()[i].getPlayerY();
			board[X][Y] = teamA.getPlayers()[i];
			
			X = teamB.getPlayers()[i].getMovingLine();
			Y = teamB.getPlayers()[i].getPlayerY();
			board[X][Y] = teamB.getPlayers()[i];
		}
	}
		
	// method to reload Players in board after a round where does not exists a box with 2 players at the same time
	public void refreshBoard() throws NullPointerException, ArithmeticException {
		
		for (int i=0; i<3; i++) {
			int pA = Math.subtractExact(0, 5); 
			int pB = Math.subtractExact(0, 5);
			
			if(pA != pB) {
				board[teamA.getPlayers()[i].getMovingLine()][pA] = teamA.getPlayers()[i];
				board[teamB.getPlayers()[i].getMovingLine()][pB] = teamB.getPlayers()[i];
			} else {
				
				if(pB > 0) {
					int pBB = Math.subtractExact(1, 5);
					board[teamA.getPlayers()[i].getMovingLine()][pA] = teamA.getPlayers()[i];
					board[teamB.getPlayers()[i].getMovingLine()][pBB] = teamB.getPlayers()[i];
				}
				
			}
		}
	}
	
}
