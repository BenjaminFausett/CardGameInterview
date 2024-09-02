package controller;

import model.blackjack.Blackjack;
import model.common.CardGame;
import model.crazyeights.CrazyEights;
import model.gops.Gops;
import model.war.War;
import view.TextInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class GameLauncher {
	
	public void start() {
		String name = TextInterface.getResponse("Welcome to CardGames! What is your name?");
		
		TextInterface.display("Available Games: War, Blackjack, GOPS, Crazy Eights");
		
		while (true) {
			String response = TextInterface.getValidResponse("What game would you like to play?", "War", "Blackjack", "GOPS", "Crazy Eights", "Quit");
			
			switch (response.toUpperCase()) {
				case "WAR":
					new War(name).play();
					break;
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
