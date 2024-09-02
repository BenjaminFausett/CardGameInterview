package model.crazyeights;

import model.common.*;
import view.TextInterface;

import java.util.Optional;

/**
 * Represents a Crazy Eights card game, extending the {@link CardGame} class.
 * <p>
 * Crazy Eights is a card game where 2 players aim to be the first to get rid of all their cards.
 * Each player is dealt 7 cards, and they take turns matching the top card of the discard pile by rank or suit.
 * If unable to match, they draw cards until they can play. Eights are wild and can be played at any time.
 */
public class CrazyEights extends CardGame {
	
	private final CrazyEightsPlayer player;
	private final CrazyEightsPlayer computer;
	private Deck deck;
	private CardCollection discardPile;
	private int round;
	
	public CrazyEights(String playerName) {
		this.player = new CrazyEightsPlayer(playerName, false);
		this.computer = new CrazyEightsPlayer("Computer", true);
	}
	
	@Override
	protected String getGameName() {
		return "Crazy Eights";
	}
	
	@Override
	protected void resetGame() {
		this.player.reset();
		this.computer.reset();
		this.deck = new Deck();
		this.deck.shuffle();
		this.discardPile = new CardCollection();
		this.discardPile.add(deck.drawFirst());
		
		this.player.addCards(this.deck.drawFirst(8));
		this.computer.addCards(this.deck.drawFirst(8));
		
		this.player.sortHand();
		this.computer.sortHand();
		
		this.round = 0;
	}
	
	@Override
	protected void playRound() {
		Card topCard = this.discardPile.peekLast();
		TextInterface.display("Discard pile top card: " + topCard.getDisplayName());
		
		CrazyEightsPlayer currentPlayer = getCurrentPlayer();
		
		while(!currentPlayer.hasValidCard(topCard)) {
			if (deck.isEmpty()) {
				this.deck.addAll(this.discardPile);
				this.discardPile.discardAll();
				this.deck.shuffle();
			}
			TextInterface.display(currentPlayer.getName() + ", You have no valid cards to play and draw a card from the deck.");
			currentPlayer.addCard(deck.drawFirst());
			currentPlayer.sortHand();
		}
		
		this.discardPile.add(currentPlayer.chooseCardToPlay(topCard));
		this.round += 1;
	}
	
	private CrazyEightsPlayer getCurrentPlayer() {
		if (this.round % 2 == 0) {
			return this.player;
		} else {
			return this.computer;
		}
	}
	
	@Override
	protected boolean isGameOver() {
		return !player.hasCards() || !computer.hasCards();
	}
	
	@Override
	protected Optional<Player> getWinner() {
		if (!player.hasCards() && !computer.hasCards()) {
			return Optional.empty();
		}
		if (!player.hasCards()) {
			return Optional.of(player);
		}
		if (!computer.hasCards()) {
			return Optional.of(computer);
		}
		return Optional.empty();
	}
	
	@Override
	protected void displayFinalScore() {
		TextInterface.display(player.getName() + " has " + player.handSize() + " cards\n" + computer.getName() + " has " + computer.handSize() + " cards");
	}
}
