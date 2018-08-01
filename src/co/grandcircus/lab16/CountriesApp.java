package co.grandcircus.lab16;

import java.util.List;

public class CountriesApp {

	public static void main(String[] args) {
		CountryFileUtil fileUtil = new CountryFileUtil();
		
		
		// Print a list of countries
		List<Country> list = fileUtil.readFile();
		for (Country c : list) {
			System.out.println(c);
		}
		
		// Write a new country
		Country country = new Country("USA");
		fileUtil.appendLine(country);
		
		// Delete a country??
		
		// Quit the app

	}

}