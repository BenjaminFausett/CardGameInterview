package model.common;

public enum GameName {
	
	CRAZY_EIGHTS("Crazy Eights"),
	GOPS("GOPS");
	
	private final String displayName;
	
	private GameName(String displayName) {
		this.displayName = displayName;
	}
	
	public  String getDisplayName() {
		return displayName;
	}
	
	public static GameName fromDisplayName(String displayName) {
		for(GameName current : GameName.values()) {
			if(displayName.equalsIgnoreCase(current.getDisplayName())) {
				return current;
			}
		}
		return null;
	}
	
	
}
