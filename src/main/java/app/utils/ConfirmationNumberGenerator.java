package app.utils;

import java.security.SecureRandom;

public class ConfirmationNumberGenerator {

	private static final String CONFIRMATION_CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final int TOTAL_CONFIRMATION_CHARACTERS = 6;

	private static SecureRandom RANDOM = new SecureRandom();

	public static String generate() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < TOTAL_CONFIRMATION_CHARACTERS; i++) {
			sb.append(CONFIRMATION_CHARACTERS.charAt(RANDOM.nextInt(CONFIRMATION_CHARACTERS.length())));
		}
		return sb.toString();
	}

	private ConfirmationNumberGenerator() {
	}

}
