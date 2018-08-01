package co.grandcircus.lab16;

import java.util.Scanner;

public class Validators {

	public static String getValidCountry(Scanner scnr) {
		try {
			String userInput = scnr.nextLine().trim();
			validateAlphabeticOnly(userInput);
			return userInput;
		} catch (IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
			return getValidCountry(scnr);
		}
	}

	public static void validateAlphabeticOnly(String input) {
		if (!input.matches("^(?!.*  )[a-zA-Z ]*$")) {
			throw new IllegalArgumentException(
					"-- You may only enter alphabetic characters (no numbers, special characters) --");
		}
	}

	public static void validateChoiceInMenu(int userInput, int optionsLength) {
		// This method just validates that a user has selected a number within the
		// bounds of the menu
		if (userInput < 1 || userInput > optionsLength) {
			throw new IllegalArgumentException();
		}
	}

}
