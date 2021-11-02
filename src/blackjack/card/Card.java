package blackjack.card;
public class Card {
	Value value;
	Color color;
	public Card(Value value,Color color){
	this.value=value;
	this.color=color;
	}
	public String toString(){
		return value.getPoints()+color.getSymbole();
	}
	public String getColorSymbole(){
		return color.getSymbole();
	}
	public String getColorName(){
		return color.name();
	}
	public String getValueSymbole(){
		return value.getSymbole();
	}
	public int getPoints(){
		return value.getPoints();
	}
}
