package model.common;

import java.util.Collections;
import java.util.Optional;

public class Hand extends CardCollection {
	
	public Hand() {
		super();
	}
	
	/**
	 * Attempts to find and remove a card from the collection based on its display name. This method iterates over the collection
	 * to locate a card that matches the specified display name. If found, the card is removed from the collection.
	 * The method ensures safe operation by returning an {@link Optional} which will be empty if no card matches the given name.
	 *
	 * @param cardDisplayName the display name of the card to find and remove. Examples: "Ace of Spades", "Ten of Clubs", "Seven of Diamonds", "King of Hearts"
	 * @return an {@link Optional} containing the card if found, or an empty {@link Optional} if no card matches the specified name
	 */
	public Optional<Card> getAndRemove(String cardDisplayName) {
		Optional<Card> cardOptional = cards.stream().filter(card -> card.getDisplayName().equalsIgnoreCase(cardDisplayName)).findFirst();
		cardOptional.ifPresent(card -> cards.remove(card));
		
		return cardOptional;
	}
	
	public void sort() {
		Collections.sort(this.cards);
	}
}
