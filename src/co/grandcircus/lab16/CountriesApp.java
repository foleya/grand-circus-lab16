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
				deleteCountry(fileUtil, scnr);
				pause(scnr);
				break;
			case ("Quit Program"):
				programRunning = false;
				break;
			}

		} while (programRunning);

		System.out.println("\nThanks for using the Country List Reader/Writer! Goodbye!");

	}

	// TODO: What if there are no countries?
	private static void deleteCountry(CountryFileUtil fileUtil, Scanner scnr) {
		List<Country> list = fileUtil.readFile();
		if (list.size() != 0) {
			// If there are countries, print them all
			System.out.println("Select the country you would like to delete (1-" + list.size() + "):");
			for (int i = 0; i < list.size(); i++) {
				System.out.println((i + 1) + ". " + list.get(i).name);
			}
			
			// TODO: Validation!!
			int indexOfCountry = Integer.parseInt(scnr.nextLine().trim()) - 1;
			
			// get country from list by index
			fileUtil.deleteItem(list.get(indexOfCountry));
			
			// TODO: Add confirmation?
			
			// Confirm deletion.
			System.out.println("Great! " + list.get(indexOfCountry).getName() + " was deleted!");
			
		} else {
			// Otherwise inform the user the list is empty
			System.out.println("Sorry! There are no countries in the list!");
		}

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
		List<Country> list = fileUtil.readFile();
		if (list.size() != 0) {
			// If there are countries, print them all
			System.out.println("Here is the current list of countries:");
			for (int i = 0; i < list.size(); i++) {
				System.out.println((i + 1) + ". " + list.get(i).name);
			}
		} else {
			// Otherwise inform the user the list is empty
			System.out.println("Sorry! There are no countries in the list!");
		}
	}

}