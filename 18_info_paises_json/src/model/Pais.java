package model;

public class Pais {
	private String country;
	private String capital;	
	private long population;
	private String continent;
	private double temperature;
	private int foundation;
	
	public Pais(String country, String capital, long population, String continent, double temperature, int foundation) {
		super();
		this.country = country;
		this.capital = capital;
		this.population = population;
		this.continent = continent;
		this.temperature = temperature;
		this.foundation = foundation;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public long getPopulation() {
		return population;
	}

	public void setPopulation(long population) {
		this.population = population;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public int getFoundation() {
		return foundation;
	}

	public void setFoundation(int foundation) {
		this.foundation = foundation;
	}

		

}
