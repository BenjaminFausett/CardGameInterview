package model.blackjack;

import model.common.*;

import java.util.Optional;


/**
 * Represents a Blackjack card game, extending the {@link CardGame} class.
 * <p>
 * Blackjack is a card game where players compete against a dealer to get a hand total
 * as close to 21 as possible without exceeding it ("busting").
 *
 * <p><b>Gameplay Overview:</b></p>
 * <ul>
 *   <li>The player and the dealer are initially dealt two cards.</li>
 *   <li>Card values: Number cards are worth their number, face cards (Jack, Queen, King) are worth 10,
 *       and Aces are worth 1 or 11 (whichever is best for the hand).</li>
 *   <li>The Players take turns choosing to "hit" (take another card) or "stand" (keep current hand).</li>
 *   <li>If a hand exceeds 21, it busts and loses automatically.</li>
 *   <li>After the player chooses to stand, the dealer plays by hitting until reaching 17, at which point the dealer stands and the game is over.</li>
 *   <li>Winning conditions:
 *     <ul>
 *       <li>Player wins if: Player hand ≤ 21 and greater than dealer's hand</li>
 *       <li>Player wins if: Dealer busts and player does not</li>
 *
 *       <li>Dealer wins if: Player busts</li>
 *       <li>Dealer wins if: Dealer hand ≤ 21 and greater than player's hand</li>
 *
 *       <li>Nobody wins if: Both players have the same hand value</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 */
public class Blackjack extends CardGame {
	
	public Blackjack(String playerName) {
		throw new UnsupportedOperationException("This method is not yet implemented");
	}
	
	@Override
	protected String getGameName() {
		return "Blackjack";
	}
	
	@Override
	protected void resetGame() {
		throw new UnsupportedOperationException("This method is not yet implemented");
	}
	
	@Override
	protected void playRound() {
		throw new UnsupportedOperationException("This method is not yet implemented");
	}
	
	@Override
	protected boolean isGameOver() {
		throw new UnsupportedOperationException("This method is not yet implemented");
	}
	
	@Override
	protected Optional<Player> getWinner() {
		throw new UnsupportedOperationException("This method is not yet implemented");
	}
	
	@Override
	protected void displayFinalScore() {
		throw new UnsupportedOperationException("This method is not yet implemented");
	}
	
}
