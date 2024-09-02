package view;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Scanner;

public final class TextInterface {
	
	private static final double DISPLAY_DELAY = 0.75; // delay in seconds to throttle text output speed
	
	private static final Scanner scanner = new Scanner(System.in);
	private static Instant lastDisplayTime = Instant.now();
	
	private TextInterface() {
		throw new AssertionError("Suppressing default constructor to prevent instantiation");
	}
	
	/**
	 * Displays a given message to the user with a delay to prevent spamming. If the last message was displayed recently,
	 * it waits for the remainder of the delay period before displaying the new message.
	 *
	 * @param message the message to display
	 */
	public static void display(String message) {
		try {
			Duration difference = Duration.between(lastDisplayTime, Instant.now());
			if (difference.toMillis() < delayInMilli()) {
				Thread.sleep(delayInMilli() - difference.toMillis());
			}
		} catch (Exception ignore) {
		}
		
		
		System.out.println(message);
		lastDisplayTime = Instant.now();
	}
	
	private static int delayInMilli() {
		return (int) (DISPLAY_DELAY * 1000);
	}
	
	/**
	 * Prompts the user with a question and ensures the response is one of the provided valid responses.
	 * This method is case-insensitive and will repeatedly prompt the user until a valid response is received.
	 *
	 * @param question       the question to ask the user
	 * @param validResponses a list of valid responses
	 * @return the user's response, guaranteed to be one of the valid responses
	 */
	public static String getValidResponse(String question, String... validResponses) {
		while (true) {
			String response = getResponse(question);
			
			for (String validResponse : validResponses) {
				if (validResponse.equalsIgnoreCase(response)) {
					return validResponse;
				}
			}
			
			display("I dont understand. Please say any of the following using any casing: " + Arrays.toString(validResponses));
		}
	}
	
	/**
	 * Prompts the user with a question and returns their response.
	 *
	 * @param question the question to ask
	 * @return the user's response
	 */
	public static String getResponse(String question) {
		display(question);
		return scanner.nextLine().trim();
	}
	
}
