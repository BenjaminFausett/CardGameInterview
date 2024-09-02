package model.crazyeights;

import model.common.Card;
import model.common.CardCollection;
import model.common.Player;
import view.TextInterface;

import java.util.stream.Collectors;

public class CrazyEightsPlayer extends Player {
	
	public CrazyEightsPlayer(String name, boolean isAi) {
		super(name, isAi);
	}
	
	public boolean hasValidCard(Card targetCard) {
		return this.hand.stream().anyMatch(card -> isValidCard(targetCard, card));
	}
	
	public Card chooseCardToPlay(Card targetCard) {
		String question = "What card would you like to play?";
		
		if (isAi) {
			TextInterface.display(String.format("%s, %s", name, question));
			Card aiCard = getValidOptions(targetCard).drawRandom();
			TextInterface.display(aiCard.getDisplayName());
			return aiCard;
		} else {
			Card potentialCard = this.playCard(question);
			
			while (!this.isValidCard(targetCard, potentialCard)) {
				this.hand.add(potentialCard);
				TextInterface.display("You cant play that right now.");
				potentialCard = this.playCard(question);
			}
			return potentialCard;
		}
	}
	
	private CardCollection getValidOptions(Card targetCard) {
		CardCollection validCards = new CardCollection();
		validCards.addAll(this.hand.stream().filter(card -> isValidCard(targetCard, card)).collect(Collectors.toList()));
		
		return validCards;
	}
	
	private boolean isValidCard(Card targetCard, Card potentialCard) {
		return potentialCard.getSuit().equals(targetCard.getSuit()) || potentialCard.getRank().equals(targetCard.getRank()) || potentialCard.getRank() == Card.Rank.EIGHT;
	}
}
