package blackjack;
import blackjack.deck.*;
import blackjack.hand.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BlackJack{
	private Deck deck;
	private Hand playerHand;
	private Hand bankHand;
	private boolean gameFinished;
	private int playerScore;
	private int bankScore;
	private String SCORE_FILENAME;
	/**            CONSTRUCTOR BLACKJACK                                             **/
	public BlackJack(){
		gameFinished = false;
		deck = new Deck(1);
		playerHand = new Hand();
		bankHand = new Hand();
		bankScore = 0;
		playerScore = 0;
		SCORE_FILENAME ="files/Scores.txt";
		try{
			reset();
		}
		catch(EmptyDeckException ex){
			System.exit(-1);
		}
	}
/**  Function reset: Remet la partie à 0 (mais ne réinitialise pas le nombre de cartes tirees)**/
	public void reset() throws EmptyDeckException{
		playerHand.clear();
		bankHand.clear();
		gameFinished = false; // Le jeu une fois réitinialisé est loin d' etre termine
		playerHand.add(deck.draw());
		bankHand.add(deck.draw());
	}
	public String getPlayerHandString(){
		return playerHand.toString();
	}
	public String getBankHandString(){
		return bankHand.toString();
	}
	public int getPlayerBest(){
		return playerHand.best();
	}
	public int getBankBest(){
		return bankHand.best();
	}
	public boolean isPlayerWinner(){
		if (!gameFinished) //aucun gagnant n'est declare si le jeu n'est pas fini
			return false;
		else{
			if(playerHand.best()>21) //si la main du joueur est supérieure a 21 , celui-ci a perdu
				return false;  
			else {
				if(bankHand.best()>21) return true; //si plutot la main de la banque depasse 21, le joueur a gagne
				else if ( playerHand.best()>bankHand.best()) return true; //si plutit la main du joueur surpasse celle de la banque
								//il a gagne
				else return false; //les autres cas il perd
			}
		}
	}
	public boolean isBankWinner(){
		if (!gameFinished) //aucun gagnant n'est declare si le jeu n'est pas fini
			return false;
		else{
			if(bankHand.best()>21) //si la main de la banque est supérieure a 21 , celui-ci a perdu
				return false;
			else {
				if(playerHand.best()>21) return true; //si plutot la main du joueur depasse 21, la banque a gagne
				else if ( bankHand.best()>playerHand.best()) return true;//si plutot la main de la banque surpasse celle du joueur
								//elle a gagne
				else return false;//les autres cas elle perd
			}
		}
	}
	public boolean isGameFinished(){
		if(gameFinished) return true;
		else return false;
	}
	public void playerDrawAnotherCard() throws EmptyDeckException {
		if (!gameFinished){
			playerHand.add(deck.draw());
			if(playerHand.best()>21)
				gameFinished = true;
		}
	}
	public void bankLastTurn()throws EmptyDeckException{
	do{
		bankHand.add(deck.draw());
	}while((bankHand.best()<21) && (bankHand.best()<=playerHand.best())) ;
	gameFinished = true;
	}
	public void updateScores(){
		if (isBankWinner()) bankScore++;
		else if(isPlayerWinner()) playerScore++;
	}
	
	public void writeScore(){
		FileWriter fw= null;
		BufferedWriter bw= null;
		try {
			fw = new FileWriter(SCORE_FILENAME,true);
			bw = new BufferedWriter(fw);
			String content ="Player " + playerScore + "\nBank " + bankScore + "\n";
			bw.write(content);
			
		}catch(IOException e){e.printStackTrace();System.exit(-1);}
		finally{
			try {
				if (bw != null) bw.close();
				if (fw != null) fw.close();
			}catch(IOException e){e.printStackTrace();System.exit(-1);}
		}
	}
	public void readScore(){
		FileReader fr= null;
		BufferedReader br= null;
		try {
			fr = new FileReader(SCORE_FILENAME);
      			br= new BufferedReader(fr);
			String strLignCourant;
			 while ((strLignCourant = br.readLine()) != null) {
				System.out.println(strLignCourant);
			      }
			
		}catch(IOException e){e.printStackTrace();System.exit(-1);}
		finally{
			try {
				if (br != null) br.close();
		        	if (fr != null) fr.close();
			}catch(IOException e){e.printStackTrace();System.exit(-1);}
		}
	}
}
