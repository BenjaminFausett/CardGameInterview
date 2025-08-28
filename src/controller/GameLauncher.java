package controller;

import model.common.GameName;
import model.crazyeights.CrazyEights;
import model.gops.Gops;
import view.TextInterface;

import java.util.Arrays;

public class GameLauncher {
	
	public void start() {
		String name = TextInterface.getResponse("Welcome to CardGames! What is your name?");
		
		String[] availableGames = Arrays.stream(GameName.values()).map(GameName::getDisplayName).toArray(String[]::new);
	
		TextInterface.display("Available Games: " + String.join(", ", availableGames));
		String response = TextInterface.getValidResponse("What game would you like to play?", availableGames);
		
		switch (GameName.fromDisplayName(response)) {
			case GOPS:
				new Gops(name).play();
				break;
			case CRAZY_EIGHTS:
				new CrazyEights(name).play();
				break;
		}
	}
}
