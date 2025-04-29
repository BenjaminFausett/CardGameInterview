package model.x;

import model.common.CardGame;
import model.common.Player;

import java.util.Optional;

public class X extends CardGame {
	
	public X(String playerName) {
		throw new UnsupportedOperationException("This method is not yet implemented");
	}
	
	@Override
	protected String getGameName() {
		return "X";
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
