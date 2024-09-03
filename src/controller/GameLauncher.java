package controller;

import model.blackjack.Blackjack;
import model.crazyeights.CrazyEights;
import model.gops.Gops;
import view.TextInterface;

public class GameLauncher {
	
	public void start() {
		String name = TextInterface.getResponse("Welcome to CardGames! What is your name?");
		
		TextInterface.display("Available Games: Blackjack, GOPS, Crazy Eights");
		
		while (true) {
			String response = TextInterface.getValidResponse("What game would you like to play?", "Blackjack", "GOPS", "Crazy Eights", "Quit");
			
			switch (response.toUpperCase()) {
				case "BLACKJACK":
					new Blackjack(name).play();
					break;
				case "GOPS":
					new Gops(name).play();
					break;
				case "CRAZY EIGHTS":
					new CrazyEights(name).play();
					break;
				case "QUIT":
					System.exit(0);
			}
		}
	}
}
