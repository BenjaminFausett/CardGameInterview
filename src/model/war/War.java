package model.war;

import model.common.*;

import java.util.Optional;

/**
 * Represents a War card game, extending the {@link CardGame} class.
 * <p>
 * War is a simple card game where two players compete to win all the cards by playing
 * higher-ranked cards against each other.
 *
 * <p><b>Gameplay Overview:</b></p>
 * <ul>
 *   <li>The deck is shuffled and split evenly between two players.</li>
 *   <li>Each player reveals the top card of their deck simultaneously.</li>
 *   <li>The player with the higher-ranked card wins both cards and places them at the bottom of their deck.</li>
 *   <li>If both cards have the same rank a "war" happens:
 *     <ul>
 *       <li>Each player places three face-down cards and one face-up card.</li>
 *       <li>The face-up cards are compared; the higher wins all cards on the table.</li>
 *       <li>If another tie occurs, the war process repeats recursively.</li>
 *     </ul>
 *   </li>
 *   <li>The game continues until one player has all the cards.</li>
 * </ul>
 *
 */
public class War extends CardGame {
	
	public War(String playerName) {
		throw new UnsupportedOperationException("This method is not yet implemented");
	}
	
	@Override
	protected String getGameName() {
		return "War";
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
