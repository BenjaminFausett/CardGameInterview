package model.common;

public class Deck extends CardCollection {
	
	/**
	 * Constructs a standard 52-card deck
	 */
	public Deck() {
		super();
		for (Card.Suit suit : Card.Suit.values()) {
			for (Card.Rank rank : Card.Rank.values()) {
				cards.add(new Card(rank, suit));
			}
		}
	}
	
}
