package model.common;


import view.TextInterface;

import java.util.Optional;

/**
 * Provides a framework for creating card games. It defines the structure and flow of a typical card game
 * through several abstract methods that need to be implemented by subclasses to specify the game details.
 * This class makes use of {@link TextInterface} to interact with the user, displaying messages and
 * fetching inputs.
 */
public abstract class CardGame {
	
	/**
	 * Starts and manages the card game loop. It continues to play rounds of the game
	 * until the game is over. It handles game resets, displays game starts,
	 * checks for game over conditions, and announces winners or ties.
	 */
	public void play() {
		TextInterface.display("Welcome to " + this.getGameName());
		
		boolean playAgain = true;
		
		while (playAgain) {
			TextInterface.display("Setting up a new game of " + this.getGameName());
			this.resetGame();
			while (!this.isGameOver()) {
				this.playRound();
			}
			this.displayFinalScore();
			Optional<Player> winner = this.getWinner();
			if (winner.isPresent()) {
				TextInterface.display("\n" + winner.get().getName() + " wins!");
			} else {
				TextInterface.display("\nTie game, nobody wins!");
			}
			playAgain = TextInterface.getValidResponse("\nPlay " + this.getGameName() + " again?", "Yes", "No").equalsIgnoreCase("Yes");
			
		}
		TextInterface.display("\nThanks for playing " + this.getGameName());
	}
	
	/**
	 * Returns the name of the game.
	 *
	 * @return the name of the game
	 */
	protected abstract String getGameName();
	
	/**
	 * Resets the game to a clean state before starting a new game.
	 */
	protected abstract void resetGame();
	
	/**
	 * Plays a single round of the game.
	 */
	protected abstract void playRound();
	
	/**
	 * Checks if the game is over.
	 *
	 * @return true if the game is over, false otherwise
	 */
	protected abstract boolean isGameOver();
	
	/**
	 * Determines the winner of the game.
	 *
	 * @return an {@link Optional} containing the winner if there is one, or an empty {@link Optional} if there is no winner
	 */
	protected abstract Optional<Player> getWinner();
	
	/**
	 * Displays to the text interface the end state/score of the game
	 */
	protected abstract void displayFinalScore();
	
}
