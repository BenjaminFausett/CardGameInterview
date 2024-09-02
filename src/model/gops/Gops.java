package model.gops;

import model.common.Card;
import model.common.CardGame;
import model.common.Deck;
import model.common.Player;
import view.TextInterface;

import java.util.Optional;

/**
 * Represents the card game "Game of Pure Strategy" (GOPS), extending the {@link CardGame} class, where players bid cards
 * from their hand in attempts to win prize cards. Each round, players simultaneously select a card to bid in secret.
 * The player with the higher card wins the prize card for that round, and the player with the most points at the end of
 * the game wins. This class manages the flow of the game, handling card draws, bids, and score calculations.
 * <p>
 * The game involves two players: one human player and one computer player, each managed by a {@link GopsPlayer} instance.
 * The deck for prize cards and the individual decks for players are handled within the class, ensuring each player only
 * sees their own cards, but not the opponent's selections until revealed.
 */
public class Gops extends CardGame {
	
	private final GopsPlayer player;
	private final GopsPlayer computer;
	
	private final Deck deck;
	
	public Gops(String playerName) {
		super();
		this.deck = new Deck();
		this.player = new GopsPlayer(playerName, false, Card.Suit.HEART);
		this.computer = new GopsPlayer("Computer", true, Card.Suit.SPADE);
	}
	
	@Override
	protected String getGameName() {
		return "GOPS";
	}
	
	@Override
	protected void resetGame() {
		this.player.reset();
		this.computer.reset();
		this.deck.discardAll();
		this.deck.addAll(new GopsHand(Card.Suit.DIAMOND));
	}
	
	@Override
	protected void playRound() {
		Card prize = deck.drawRandom();
		
		TextInterface.display(String.format("The prize card for this round is %s", prize.getRankName()));
		
		Card computerBid = computer.getBid(prize.getValue());
		Card playerBid = player.getBid(prize.getValue());
		
		TextInterface.display(String.format("%s's bid %s", computer.getName(), computerBid.getRankName()));
		TextInterface.display(String.format("%s's bid %s", player.getName(), playerBid.getRankName()));
		
		if (playerBid.getValue() > computerBid.getValue()) {
			player.incrementScore(prize.getValue());
		}
		if (computerBid.getValue() > playerBid.getValue()) {
			computer.incrementScore(prize.getValue());
		}
		if (playerBid.getValue() == computerBid.getValue()) {
			TextInterface.display("Tie! Nobody wins this round");
		}
	}
	
	@Override
	protected boolean isGameOver() {
		return deck.isEmpty();
	}
	
	@Override
	protected void displayFinalScore() {
		String playerScore = String.format("%s: %d", player.getName(), player.getScore());
		String computerScore = String.format("%s: %d", computer.getName(), computer.getScore());
		
		TextInterface.display(String.format("%s\n%s", playerScore, computerScore));
	}
	
	@Override
	protected Optional<Player> getWinner() {
		if (player.getScore() > computer.getScore()) {
			return Optional.of(player);
		} else if (computer.getScore() > player.getScore()) {
			return Optional.of(computer);
		}
		
		return Optional.empty();
	}
}
