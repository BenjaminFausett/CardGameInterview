package model.gofish;

import model.common.Card;
import model.common.Player;
import view.TextInterface;

import java.util.*;
import java.util.stream.Collectors;

public class GoFishPlayer extends Player {
	
	
	private int books;
	private final Random random;
	
	
	/**
	 * Constructor for a Player
	 *
	 * @param name  The name of the player
	 * @param isNpc true if this player will be controlled by the computer, false if this player is controlled by a real person
	 */
	public GoFishPlayer(String name, boolean isNpc) {
		super(name, isNpc);
		this.random = new Random();
		this.books = 0;
	}
	
	public void reset() {
		super.reset();
		this.books = 0;
	}
	
	public List<Card> fishForCards(Card.Rank rank) {
		List<Card> foundCards = this.hand.stream().filter(card -> card.getRank() == rank).collect(Collectors.toList());
		this.hand.removeAll(foundCards);
		
		return foundCards;
	}
	
	public Card.Rank selectRankToFishFor() {
		List<Card.Rank> ranks = this.hand.stream().map(Card::getRank).distinct().collect(Collectors.toList());
		
		if(this.isNpc) {
			return ranks.get(random.nextInt(ranks.size()));
		} else {
			String[] options = ranks.stream().map(rank -> String.valueOf(rank.getValue())).toArray(String[]::new);
			int response = Integer.parseInt(TextInterface.getValidResponse("What card would you like to fish for?", options));
			
			return ranks.stream().filter(option -> option.getValue() == response).findFirst().orElseThrow();
		}
	}
	
	public void addCard(Card newCard) {
		int count = (int) this.hand.stream().filter(card -> card.getRank() == newCard.getRank()).count();
		
		if(count == 3) {
			this.books += 1;
			this.hand.removeIf(card -> card.getRank() == newCard.getRank());
			
			TextInterface.display(name + " made a book of " + newCard.getRank() + "'s!");
		} else {
			super.addCard(newCard);
		}
	}
	
	public int getBooks() {
		return this.books;
	}
	
}
