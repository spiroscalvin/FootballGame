package football;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
	private static final String FILE_NAME = "player.txt";													
	
	public static void main(String[] args) throws FileNotFoundException {
		Game game = new Game();
		game.ball = new Ball();
		game.teamA = new Team("Barcelona");
		game.teamB = new Team("Real Madrid");
		
		String[] names = new String[6];
		int[] personals = new int[18]; 	// 3 x 6(players)
		int countName = 0, countP = 0;
		
		/*-----------------Read from a File----------------*/
		File file = new File(FILE_NAME);
		Scanner sc = new Scanner(file);		
		sc.useDelimiter("\\s+");
		
		while(sc.hasNext()) {
			if(sc.hasNextInt()) {
				personals[countP] = sc.nextInt();
				countP++;
			} else {
				names[countName] = sc.next();
				countName++;
			}
		}
		sc.close();
		
		int j = 0;
		for (int i=0; i<3 ; i++) {
			game.insertToTeam(true, new Back(names[i], personals[j], personals[j+1], personals[j+2]));
			game.insertToTeam(false, new Forward(names[i+1], personals[j+3], personals[j+4], personals[j+5]));
			j += 6;
		}
		/*-------------------------------------------------*/		
		game.refreshBoard();
		
		while (game.getRounds() < 10) {
			System.out.println("-------------- Round: " +  game.getRounds() + "--------------\n");
			
			for (int row=0; row<10; row++) {
				for (int col=0; col<6; col++) {
					
					// if box has a Player
					if(game.board[row][col] == null) {
						
						// if ball is in the box
						if((game.ball.ballX == row) && (game.ball.ballY == col)) {
							System.out.print("o");
						} else {
							System.out.print("-");
						}
					} else {
						
						if((game.ball.ballX == row) && (game.ball.ballY == col)) {
							System.out.print(game.board[row][col].getShirtNumber() + "*");
						} else {
							System.out.print(game.board[row][col].getShirtNumber());
						}
					}
				}
				
				System.out.print("\n");
			}
			
			game.runTurn();
			if((game.teamA.goals == 7) || (game.teamB.goals == 7)) {
				break;
			}
		}
		
		System.out.println(	"\t\t     Statistics \n" +
				"Team:\t" 		+ game.teamA.getTeamName() 	+ "\t vs \t" 	+ game.teamB.getTeamName() 	+ "\n" +
				"Goals:\t    " 	+ game.teamA.goals			+ "\t\t\t     " + game.teamB.goals 			+ "\n" +
				"Pases:\t    " 	+ game.teamA.pases			+ "\t\t\t     " + game.teamB.pases			+ "\n" +
				"Faults:\t    " + game.teamA.faults			+ "\t\t\t     " + game.teamB.faults			+ "\n");
	}

}
