package model.common;

import java.util.*;

public class CardCollection extends AbstractList<Card> {
	
	protected List<Card> cards;
	protected static final Random random = new Random();
	
	public CardCollection() {
		this.cards = new ArrayList<>();
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	public List<Card> drawAll() {
		return drawFirst(this.size());
	}
	
	public Card drawFirst() {
		if (isEmpty()) {
			return null;
		}
		return cards.remove(0);
	}
	
	public List<Card> drawFirst(int n) {
		List<Card> drawnCards = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			drawnCards.add(drawFirst());
		}
		return drawnCards;
	}
	
	public Card drawLast() {
		if (isEmpty()) {
			return null;
		}
		return cards.remove(cards.size() - 1);
	}
	
	
	public List<Card> drawLast(int n) {
		List<Card> drawnCards = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			drawnCards.add(drawLast());
		}
		return drawnCards;
	}
	
	public Card drawRandom() {
		if (isEmpty()) {
			return null;
		}
		return cards.remove(random.nextInt(cards.size()));
	}
	
	public List<Card> drawRandom(int n) {
		List<Card> drawnCards = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			drawnCards.add(drawRandom());
		}
		return drawnCards;
	}
	
	public boolean isEmpty() {
		return cards.isEmpty();
	}
	
	public void discardAll() {
		cards.clear();
	}
	
	public List<Card> peekAll() {
		return Collections.unmodifiableList(cards);
	}
	
	public Card peekFirst() {
		return this.get(0);
	}
	
	public Card peekLast() {
		return this.get(cards.size() - 1);
	}
	
	@Override
	public Card get(int index) {
		return cards.get(index);
	}
	
	@Override
	public int size() {
		return cards.size();
	}
	
	@Override
	public boolean add(Card card) {
		return this.cards.add(card);
	}
}
