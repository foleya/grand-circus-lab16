package co.grandcircus.lab16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CountriesApp {

	public static void main(String[] args) {

		CountryFileUtil fileUtil = new CountryFileUtil();
		Scanner scnr = new Scanner(System.in);
		
		boolean programRunning = true;
		System.out.println("Welcome to the Country List Reader/Writer!");

		do {
			List<String> mainMenuOptions = new ArrayList<String>(
					Arrays.asList("List Countries", "Add Country", "Delete Country", "Quit Program"));
			System.out.println("Main Menu: What would you like to do?");
			for (int i = 0; i < mainMenuOptions.size(); i++) {
				System.out.println((i + 1) + ". " + mainMenuOptions.get(i));
			}
			boolean menuChoiceIsValid = false;
			int userInput = 0;
			do {
				try {
					userInput = Integer.parseInt(scnr.nextLine().trim());
					Validators.validateChoiceInMenu(userInput, mainMenuOptions.size());
					menuChoiceIsValid = true;
				} catch (IllegalArgumentException ex) {
					System.out.println(
							"You must enter an option between 1 and " + mainMenuOptions.size() + ". Please Try again:");
				}
			} while (!menuChoiceIsValid);
			String userChoice = mainMenuOptions.get(userInput - 1);
			switch (userChoice) {
			case ("List Countries"):
				listCountries(fileUtil);
				pause(scnr);
				break;
			case ("Add Country"):
				addCountry(fileUtil, scnr);
				pause(scnr);
				break;
			case ("Delete Country"):
				break;
			case ("Quit Program"):
				programRunning = false;
				break;
			}

		} while (programRunning);

		System.out.println("\nThanks for using the Country List Reader/Writer! Goodbye!");

	}

	// I just pause the program by forcing the user to hit enter to continue!
	private static void pause(Scanner scnr) {
		System.out.println("\nHit enter to return to the Main Menu");
		scnr.nextLine();
	}

	private static void addCountry(CountryFileUtil fileUtil, Scanner scnr) {
		System.out.println("Please enter the name of the country you would like to add to the list:");
		String countryName = Validators.getValidCountry(scnr);
		Country country = new Country(countryName);
		fileUtil.appendLine(country);
		System.out.println("Great! " + countryName + " was added to the list!");
	}

	private static void listCountries(CountryFileUtil fileUtil) {
		System.out.println("Here is the current list of countries:");
		List<Country> list = fileUtil.readFile();
		for (int i = 0; i < list.size(); i++) {
			System.out.println((i + 1) + ". " + list.get(i).name);
		}
	}

}