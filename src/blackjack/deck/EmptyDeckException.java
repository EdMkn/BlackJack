package blackjack.deck;
import java.lang.Exception;

public class EmptyDeckException extends Exception{
	public EmptyDeckException(String message){
		System.out.println(message);
	}
}
