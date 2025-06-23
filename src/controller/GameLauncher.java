package controller;

import model.crazyeights.CrazyEights;
import model.gops.Gops;
import view.TextInterface;

public class GameLauncher {
	
	public void start() {
		String name = TextInterface.getResponse("Welcome to CardGames! What is your name?");
		
		while (true) {
			TextInterface.display("Available Games: GOPS, Crazy Eights, X");
			String response = TextInterface.getValidResponse("What game would you like to play?", "GOPS", "Crazy Eights", "Quit");
			
			switch (response.toUpperCase()) {
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
