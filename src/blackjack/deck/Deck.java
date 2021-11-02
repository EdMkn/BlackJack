package blackjack.deck;
import blackjack.card.*;
import java.util.LinkedList;
import java.util.Collections;
public class Deck {
	private LinkedList <Card> cardList;
	public Deck (int nbBox)
	{
		cardList = new LinkedList<Card> ();
		//récuperation de toutes les instances de Value (13 valeurs)
		Value[] valuesArray = Value.values(); // Recuperation du tableau
		//récuperation de toutes les instances de Color (4 couleurs)
		Color[] colorsArray = Color.values();
		//création de nbBox jeux pour constituer le Deck
		for (int i = 0; i < nbBox; i++){
			//création d'un jeu:
			for (int k = 0; k < colorsArray.length; k++)
			{
				for (int j = 0; j < valuesArray.length; j++)
				{
					Card c = new Card(valuesArray[j], colorsArray[k]);
					cardList.add(c);
				}
			}
		}
		Collections.shuffle(cardList);
	}
	public String toString(){
		String yo="[";
		for(Card c:cardList){
			yo+=c+" ";
		}
		yo+="]";
		return "yo";
	}
	public Card draw() throws EmptyDeckException{
		if(cardList.size()==0){
			throw new EmptyDeckException("Error,the deck has insufficient cards");
		}else{
		Card myCard = cardList.pollFirst();
		return myCard;}
	}
}
