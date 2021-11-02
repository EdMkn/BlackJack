package blackjack.hand;
import blackjack.deck.*;
import blackjack.card.*;
import java.util.List;
import java.util.LinkedList;
import java.lang.Math;
public class Hand{
	private LinkedList<Card> cardList;
	
	public Hand(){
		cardList = new LinkedList<Card> ();
	}
	public String toString(){
		String yo="[";
		for(Card c:cardList){
			yo+=c+" ";
		}
		yo+="] : [";
		for (int i: count()){
			yo+=i+" ";
		}
		yo+="]";
		return yo;
	}
	public void add (Card card){
		cardList.add(card);
		
	}
	public List<Integer> count(){
		List<Integer> HdcardList = new LinkedList<Integer>();//tableau pour les differentes sommes
		int mult=1;/* nombre d'élements pour HdcardList */
		HdcardList.add(0);//au départ on considère qu'il n'y a qu'une seule somme
		for(Card c:cardList){
                /*L'as ne prend que 2 valeurs: soit 1 soit 11. Peut importe l'ordre de combinaison, les différentes sommes seront
		plus ou moins différentes d'un multiple de 10 (11-1=10)*/
			if(c.getValueSymbole()=="A"){
				HdcardList.add(mult*10);
				mult+=1;
			}
		}
		for(int k=0;k<cardList.size();k++){
			for(int j=0;j<HdcardList.size();j++){//addition des valeurs présentes dans la main à chaque élément de la liste
				HdcardList.set(j,HdcardList.get(j)+cardList.get(k).getPoints());	
			}
		}
		return HdcardList;
	}
	public int best(){
		List<Integer> HdcardList = count();
		int max=HdcardList.get(0);
		for(int i:HdcardList){
			if(max>21){
				if(i<=21)
					max=i;
				else max=Math.max(i,max);}
			else{
				if (i<=21)
					max=Math.max(i,max);
			}
		}
		return max;
	} 
	public void clear(){
		cardList.clear();
	}
}
