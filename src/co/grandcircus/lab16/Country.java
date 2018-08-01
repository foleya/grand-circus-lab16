package co.grandcircus.lab16;

public class Country {
	String name;

	public Country(String name) {
		super();
		this.name = name;
	}
	
	public Country() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Country [name=" + name + "]";
	}
	
	
}
