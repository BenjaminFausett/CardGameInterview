package model.gofish;

import model.blackjack.BlackjackPlayer;
import model.common.Card;
import model.common.CardGame;
import model.common.Deck;
import model.common.Player;
import view.TextInterface;

import java.util.List;
import java.util.Optional;

public class GoFish extends CardGame {
	
	private final GoFishPlayer player;
	private final GoFishPlayer computer;
	
	private Deck deck;
	
	public GoFishPlayer currentPlayer;
	public GoFishPlayer otherPlayer;
	
	public GoFish(String playerName) {
		this.player = new GoFishPlayer(playerName, false);
		this.computer = new GoFishPlayer(playerName, true);
		
		this.deck = new Deck();
	}
	
	
	@Override
	protected String getGameName() {
		return "Go Fish";
	}
	
	@Override
	protected void resetGame() {
		this.player.reset();
		this.computer.reset();
		this.deck = new Deck();
		this.deck.shuffle();
		
		this.currentPlayer = this.player;
		this.otherPlayer = this.computer;
		
		for(int i = 0; i < 12; i++) {
			this.player.addCard(deck.drawFirst());
			this.computer.addCard(deck.drawFirst());
		}
	}
	
	@Override
	protected void playRound() {
		Card.Rank rank = currentPlayer.selectRankToFishFor();
		
		List<Card> cardsFound = otherPlayer.fishForCards(rank);
		
		if(cardsFound.isEmpty()) {
			TextInterface.display("Go Fish!");
			currentPlayer.addCard(deck.drawFirst());
		} else {
			TextInterface.display("Found " + cardsFound.size() + " cards");
			currentPlayer.addCards(cardsFound);
		}
		
		this.switchPlayerTurn();
	}
	
	@Override
	protected boolean isGameOver() {
		return this.deck.isEmpty() || !this.player.hasCards() || !this.computer.hasCards();
	}
	
	@Override
	protected Optional<Player> getWinner() {
		if(this.player.getBooks() > this.computer.getBooks()) {
			return Optional.of(player);
		} else if(this.computer.getBooks() > this.player.getBooks()) {
			return Optional.of(computer);
		} else {
			return Optional.empty();
		}
	}
	
	@Override
	protected void displayFinalScore() {
		TextInterface.display(this.player.getName() + " created " + this.player.getBooks() + " books");
		TextInterface.display(this.computer.getName() + " created " + this.computer.getBooks() + " books");
	}
	
	private void switchPlayerTurn() {
		GoFishPlayer temp = currentPlayer;
		currentPlayer = otherPlayer;
		otherPlayer = temp;
	}
}
