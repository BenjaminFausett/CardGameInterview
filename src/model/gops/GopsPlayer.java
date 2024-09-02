package model.gops;

import model.common.Card;
import model.common.Player;
import view.TextInterface;

import java.util.Random;

/**
 * Represents a player in a game of GOPS (Game of Pure Strategy), extending the functionality of the {@link Player} class.
 * This class handles the management of the player's score and the logic for bidding a card during the game.
 */
public class GopsPlayer extends Player {
	
	private final Random random;
	private final Card.Suit suit;
	private int score;
	
	public GopsPlayer(String name, boolean isAi, Card.Suit suit) {
		super(name, isAi);
		score = 0;
		this.random = new Random();
		this.suit = suit;
		this.hand.addAll(new GopsHand(suit));
	}
	
	@Override
	public void reset() {
		super.reset();
		this.hand.addAll(new GopsHand(suit));
	}
	
	public int getScore() {
		return score;
	}
	
	public void incrementScore(int n) {
		TextInterface.display(String.format("%s won %d points", name, n));
		score += n;
	}
	
	public Card getBid(int prizeCardValue) {
		String question = "Which card will you bid?";
		if (isAi) {
			TextInterface.display(String.format("%s, %s", name, question));
			TextInterface.display("[]");
			return this.selectAiBidCard(prizeCardValue);
		}
		return this.playCard(question);
	}
	
	
	private Card selectAiBidCard(int prizeCardValue) {
		int targetValue = prizeCardValue + random.nextInt(5) - 2;
		Card selectedCard = hand.peekFirst();
		int minDifference = Math.abs(selectedCard.getValue() - targetValue);
		
		for (Card card : hand) {
			int currentDifference = Math.abs(card.getValue() - targetValue);
			if (currentDifference < minDifference) {
				minDifference = currentDifference;
				selectedCard = card;
			}
		}
		
		return selectedCard;
	}
}
