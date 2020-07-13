package football;

public class Team {

	private String teamName;
	public int faults;
	public int pases;
	public int goals;
	Player[] players;
	private boolean hasNowBall;
	
	public Team() {
		teamName = "";
	}
	
	public Team(String teamName) {
		this.teamName = teamName;
		this.faults = 0;
		this.pases = 0;
		this.goals = 0;
		this.players = new Player[3];
		this.hasNowBall = false;
	}
	
	public String getTeamName() {
		return teamName;
	}

	public int getGoal() {
		return goals;
	}
	
	public void addGoal(int goal) {
		this.goals += goal;
	}
	
	public Player[] getPlayers() {
		return this.players;
	}
	
	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public boolean hasNowBall() {
		return hasNowBall;
	}

	public void setHasNowBall(boolean hasNowBall) {
		this.hasNowBall = hasNowBall;
	}

	/*---------------------*/
	public void action(Ball ball) {
		for(Player p : players) {
			double prob = Math.random();
			
			if(prob < 0.35) {
				p.movement();
			} else if(prob < 0.70) {
				p.transfer(ball);
			} else {
				p.specialMove(ball);
			}
			
			ball.delegate(p);	
		}
	}
	
	public void addPlayer(Player p) {
		if(p != null) {
			int i = 0;
			
			while(players[i] != null) {
				i++;
			}
			this.players[i] = p;
			System.out.println("Player " + p + "added to Team " + getTeamName());
		}
	}
	
	public void deletePlayer(String name) {
		for (Player p : players) {
			if(p.getPlayerName() == name) {
				p = null;
			}
		}
		
		setPlayers(players);
	}

}
