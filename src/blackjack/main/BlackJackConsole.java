package blackjack.main;
import blackjack.deck.*;
import blackjack.*;
import java.util.Scanner;

public class BlackJackConsole{
	public BlackJackConsole(){
	System.out.println("Welcome to the BlackJack table. Let's play !");
	Scanner scan = new Scanner(System.in);	
	Scanner scan2 = new Scanner(System.in);	
	String choix, continu;
	BlackJack TheGame = new BlackJack();
	do{
		TheGame.readScore();
		System.out.println("The Bank draw : "+ TheGame.getBankHandString());
		System.out.println("You draw : "+ TheGame.getPlayerHandString());
		do{
			System.out.println("Do you want another card ? [y/n] ");
			choix=scan.next();
			switch (choix){
				case "n":try {
					TheGame.bankLastTurn();
					System.out.println("The Bank draw cards. New hand: "+ TheGame.getBankHandString());
					break;
				} catch (EmptyDeckException ex) {
					System.exit(-1);
				}	
				case "y":try{
					TheGame.playerDrawAnotherCard();
				}catch(EmptyDeckException ex){
					System.exit(-1);
				}
				System.out.println("Your new hand : "+ TheGame.getPlayerHandString());
					break;
			}
		}while(!TheGame.isGameFinished());
		System.out.println("Your best: "+ TheGame.getPlayerBest());
		System.out.println("Bank best: "+ TheGame.getBankBest());
		if(TheGame.isPlayerWinner()) System.out.println("You won! ");
		else if(TheGame.isBankWinner()) System.out.println("The bank had always won... ");
		else System.out.println("Draw !");
		TheGame.updateScores();
		TheGame.writeScore();
		System.out.println("\nDo you wish to continue?[y/n] ");
		continu=scan2.next();
		if(continu.compareTo("y") ==0){  
			try{
				TheGame.reset();
			}
			catch(EmptyDeckException ex){
				System.exit(-1);
			}
		}
		
	}while(continu.compareTo("y") ==0);
	}
	public static void main(String[] args){
		new BlackJackConsole();
	}
}
