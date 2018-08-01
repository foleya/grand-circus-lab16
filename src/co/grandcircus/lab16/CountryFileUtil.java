package co.grandcircus.lab16;

public class CountryFileUtil extends AbstractFileUtil<Country> {

	public CountryFileUtil() {
		super("CountriesTextFile.txt");
	}

	@Override
	protected Country convertLineToItem(String line) {
		// the .split is not really necessary, but keeping it in case file format changes
		// String[] values = line.split("\t");
		Country country = new Country(line);
		// country.setName(line); 	/* could do value[index] here with more values */
		return country;
	}

	@Override
	protected String convertItemToLine(Country item) {
		return String.format("%s", item.getName());
	}

	
}
