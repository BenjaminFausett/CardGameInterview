package model.gops;

import model.common.Card;
import model.common.Hand;

public class GopsHand extends Hand {
	
	/**
	 * Constructs a GOPS hand consisting only of cards from the specified suit.
	 *
	 * @param suit the suit of the cards that will make up the GOPS hand
	 */
	public GopsHand(Card.Suit suit) {
		super();
		for (int i = 13; i > 0; i--) {
			cards.add(new Card(i, suit));
		}
	}
}
