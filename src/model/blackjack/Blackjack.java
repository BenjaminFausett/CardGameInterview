package model.blackjack;

import model.common.CardGame;
import model.common.Deck;
import model.common.Player;
import view.TextInterface;

import java.util.Optional;

/**
 * Represents a Blackjack card game, extending the {@link CardGame} class. This class is responsible
 * for managing the game mechanics specific to Blackjack, such as dealing cards, handling player actions (hit or stay),
 * and determining game outcomes including winner determination based on Blackjack rules.
 * <p>
 * The game involves a dealer and a player, both represented by {@link BlackjackPlayer}. The dealer plays according to
 * standard casino rules (typically standing on all 17s) and the player's actions are determined by user input. The game
 * continues until either the player or the dealer decides to stay or busts.
 */
public class Blackjack extends CardGame {
	
	private final BlackjackPlayer dealer;
	private final BlackjackPlayer player;
	private Deck deck;
	
	public Blackjack(String playerName) {
		super();
		this.dealer = new BlackjackPlayer("Dealer", true);
		this.player = new BlackjackPlayer(playerName, false);
	}
	
	@Override
	protected String getGameName() {
		return "Blackjack";
	}
	
	@Override
	protected void resetGame() {
		deck = new Deck();
		deck.shuffle();
		
		dealer.reset();
		dealer.addCard(deck.drawFirst());
		
		player.reset();
		player.addCards(deck.drawFirst(2));
	}
	
	@Override
	protected void playRound() {
		BlackjackPlayer activePlayer = getActivePlayer();
		
		String action = activePlayer.hitOrStay();
		
		if (action.equalsIgnoreCase("Hit")) {
			activePlayer.addCard(deck.drawFirst());
		}
	}
	
	@Override
	protected boolean isGameOver() {
		if (player.hasBusted() || dealer.hasBusted()) {
			return true;
		}
		
		return player.hasStayed() && dealer.hasStayed();
	}
	
	@Override
	protected Optional<Player> getWinner() {
		if (player.hasBusted()) {
			return Optional.of(dealer);
		}
		if (dealer.hasBusted()) {
			return Optional.of(player);
		}
		
		int dealerScore = dealer.getHandTotal();
		int playerScore = player.getHandTotal();
		
		if (dealerScore > playerScore) {
			return Optional.of(dealer);
		}
		if (playerScore > dealerScore) {
			return Optional.of(player);
		}
		
		return Optional.empty();
	}
	
	@Override
	protected void displayFinalScore() {
		String playerScore = String.format("%s: %d", player.getName(), player.getHandTotal());
		String dealerScore = String.format("%s: %d", dealer.getName(), dealer.getHandTotal());
		
		TextInterface.display(String.format("%s\n%s", playerScore, dealerScore));
	}
	
	private BlackjackPlayer getActivePlayer() {
		if (!player.hasStayed()) {
			return player;
		}
		
		return dealer;
	}
	
}
