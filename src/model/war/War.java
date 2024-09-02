package model.war;

import model.common.*;
import view.TextInterface;

import java.util.Optional;

public class War extends CardGame {
	
	private final Player player;
	private final Player computer;
	
	private final CardCollection winnerPot;
	
	public War(String playerName) {
		this.player = new Player(playerName, false);
		this.computer = new Player("Computer", true);
		this.winnerPot = new CardCollection();
	}
	
	@Override
	protected String getGameName() {
		return "War";
	}
	
	@Override
	protected void resetGame() {
		player.reset();
		computer.reset();
		winnerPot.discardAll();
		
		Deck deck = new Deck();
		deck.shuffle();
		
		while (deck.size() >= 2) {
			player.addCard(deck.drawFirst());
			computer.addCard(deck.drawFirst());
		}
	}
	
	@Override
	protected void playRound() {
		Card playerCard = player.playTopCard();
		Card computerCard = computer.playTopCard();
		
		TextInterface.display(String.format("%s: %s", player.getName(), playerCard.getRankName()));
		TextInterface.display(String.format("%s: %s", computer.getName(), computerCard.getRankName()));
		
		winnerPot.add(playerCard);
		winnerPot.add(computerCard);
		winnerPot.shuffle();
		
		if (playerCard.getValue() > computerCard.getValue()) {
			TextInterface.display(String.format("%s won and gained %d cards", player.getName(), winnerPot.size()));
			player.addCards(winnerPot.drawAll());
		} else if (computerCard.getValue() > playerCard.getValue()) {
			TextInterface.display(String.format("%s won and gained %d cards", computer.getName(), winnerPot.size()));
			computer.addCards(winnerPot.drawAll());
		} else {
			TextInterface.display("Tie, its a war!");
			if (player.hasCards() && computer.hasCards()) {
				TextInterface.display("Both players placed a card face down");
				winnerPot.add(player.playTopCard());
				winnerPot.add(computer.playTopCard());
			} else {
				TextInterface.display("Out of cards, game over!");
			}
		}
	}
	
	@Override
	protected boolean isGameOver() {
		return !player.hasCards() || !computer.hasCards();
	}
	
	@Override
	protected void displayFinalScore() {
		String playerScore = String.format("%s: %d", player.getName(), player.handSize());
		String computerScore = String.format("%s: %d", computer.getName(), computer.handSize());
		
		TextInterface.display(String.format("%s\n%s", playerScore, computerScore));
	}
	
	@Override
	protected Optional<Player> getWinner() {
		if (player.hasCards()) {
			return Optional.of(player);
		}
		if (computer.hasCards()) {
			return Optional.of(computer);
		}
		return Optional.empty();
	}
}
