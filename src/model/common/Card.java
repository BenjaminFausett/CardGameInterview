package model.common;

import java.util.Comparator;
import java.util.Objects;

public final class Card implements Comparable<Card> {
	
	private final Rank rank;
	private final Suit suit;
	
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public Card(int value, Suit suit) {
		this(Rank.fromValue(value), suit);
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public Rank getRank() {
		return rank;
	}
	
	public int getValue() {
		return rank.getValue();
	}
	
	public String getRankName() {
		return rank.getName();
	}
	
	public String getSuitName() {
		return suit.getName();
	}
	
	public String getDisplayName() {
		return getRankName() + getSuitName();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		
		if (obj instanceof Card) {
			Card card = (Card) obj;
			return (this.getRank() == card.getRank()) && (this.getSuit() == card.getSuit());
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getRank(), getSuit());
	}
	
	@Override
	public int compareTo(Card other) {
		return Comparator.comparing(Card::getRank)
				.thenComparing(Card::getSuit)
				.compare(this, other);
	}
	
	@Override
	public String toString() {
		return this.getDisplayName();
	}
	
	public enum Suit {
		
		SPADE("S"),
		HEART("H"),
		CLUB("C"),
		DIAMOND("D");
		
		private final String name;
		
		Suit(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
	}
	
	/**
	 * Represents the rank of a card.
	 */
	public enum Rank {
		ACE(1, "A"), TWO(2, "2"), THREE(3, "3"), FOUR(4, "4"), FIVE(5, "5"),
		SIX(6, "6"), SEVEN(7, "7"), EIGHT(8, "8"), NINE(9, "9"), TEN(10, "10"),
		JACK(11, "J"), QUEEN(12, "Q"), KING(13, "K");
		
		private final int value;
		private final String name;
		
		Rank(int value, String name) {
			this.value = value;
			this.name = name;
		}
		
		/**
		 * Converts a value to its corresponding Rank.
		 *
		 * @param value the value of the rank
		 * @return the Rank corresponding to the value
		 */
		public static Rank fromValue(int value) {
			switch (value) {
				case 1:
					return ACE;
				case 2:
					return TWO;
				case 3:
					return THREE;
				case 4:
					return FOUR;
				case 5:
					return FIVE;
				case 6:
					return SIX;
				case 7:
					return SEVEN;
				case 8:
					return EIGHT;
				case 9:
					return NINE;
				case 10:
					return TEN;
				case 11:
					return JACK;
				case 12:
					return QUEEN;
				case 13:
					return KING;
				default:
					return null;
			}
		}
		
		public int getValue() {
			return value;
		}
		
		public String getName() {
			return name;
		}
	}
}