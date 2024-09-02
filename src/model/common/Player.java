package model.common;


import view.TextInterface;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Player {
	
	protected Hand hand;
	protected final String name;
	protected final boolean isAi;
	
	public Player(String name, boolean isAi) {
		this.name = name;
		this.hand = new Hand();
		this.isAi = isAi;
	}
	
	public void sortHand() {
		this.hand.sort();
	}
	
	public void reset() {
		this.hand = new Hand();
	}
	
	public void addCard(Card card) {
		this.hand.add(card);
	}
	
	public void addCards(List<Card> cards) {
		cards.forEach(this::addCard);
	}
	
	public String getName() {
		return name;
	}
	
	public Card playTopCard() {
		return this.hand.drawFirst();
	}
	
	public boolean hasCards() {
		return !this.hand.isEmpty();
	}
	
	public int handSize() {
		return this.hand.size();
	}
	
	/**
	 * Allows the player to select a card to play from their hand. This method continuously prompts the player
	 * until a valid card is selected. If the play is an AI, then the aiChoice card will instead be played.
	 *
	 * @param question The question the player will be prompted
	 * @return the card selected by the player
	 */
	public Card playCard(String question) {
		if (isAi) {
			return this.hand.drawRandom();
		}
		
		String[] cardDisplayNames = this.hand.peekAll().stream().map(Card::getDisplayName).toArray(String[]::new);
		String cardDisplayName = TextInterface.getValidResponse(String.format("%s, %s %s", name, question, Arrays.toString(cardDisplayNames)), cardDisplayNames);
		Optional<Card> cardOptional = this.hand.getAndRemove(cardDisplayName);
		
		return cardOptional.orElseThrow();//if this throws an exception than a bug exist
	}
}
