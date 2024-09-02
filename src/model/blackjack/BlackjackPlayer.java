package model.blackjack;

import model.common.Card;
import model.common.Player;
import view.TextInterface;


public class BlackjackPlayer extends Player {
	
	private boolean stayed;
	
	public BlackjackPlayer(String name, boolean isAi) {
		super(name, isAi);
		stayed = false;
	}
	
	@Override
	public void reset() {
		super.reset();
		stayed = false;
	}
	
	@Override
	public void addCard(Card card) {
		super.addCard(card);
		TextInterface.display(String.format("%s was added to %s's hand", card.getRankName(), name));
		if (hasBusted()) {
			TextInterface.display(String.format("%s busts with a %d", name, getHandTotal()));
		}
	}
	
	public boolean hasStayed() {
		return stayed;
	}
	
	public boolean hasBusted() {
		return getHandTotal() > 21;
	}
	
	public String hitOrStay() {
		String question = String.format("%s, your hand total is %d. Would you like to Hit or Stay?", name, getHandTotal());
		if (isAi) {
			TextInterface.display(question);
			if (getHandTotal() < 17) {
				TextInterface.display("Hit");
				return "Hit";
			} else {
				TextInterface.display("Stay");
				this.stayed = true;
				return "Stay";
			}
		} else {
			String response = TextInterface.getValidResponse(question, "Hit", "Stay");
			if (response.equalsIgnoreCase("Stay")) {
				stayed = true;
			}
			return response;
		}
	}
	
	public int getHandTotal() {
		int aceCount = 0;
		int handTotal = 0;
		
		for (Card card : hand.peekAll()) {
			int value = card.getValue();
			
			if (value > 1 && value <= 10) {
				handTotal += value;
			} else if (value > 10) {
				handTotal += 10;
			} else {
				handTotal += 11;
				aceCount++;
			}
		}
		
		while (handTotal > 21 && aceCount > 0) {
			handTotal -= 10;
			aceCount--;
		}
		
		return handTotal;
	}
}
