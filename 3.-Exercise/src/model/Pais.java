package model;

import java.util.Arrays;
import java.util.List;

import service.PaisesService;

public class Pais {
	private String name;
	private String[] topLevelDomain;
	private String alpha2Code;
	private String alpha3Code;
	private String[] callingCodes;
	private String capital;
	private String[] altSpellings;
	private String region;
	private String subregion;
	private Long population;
	private Double[] latlng;
	private String demonym;
	private double area;
	private double gini;
	private String[] timezones;
	private String[] borders;
	private String nativeName;
	private String numericCode;
	private Currencie[] currencies;
	private Language[] languages;
	private Translation translations;
	private String flag;
	private RegionalBloc[] regionalBlocs;
	private String cioc;
	
	public Pais(String name, String[] topLevelDomain, String alpha2Code, String alpha3Code, String[] callingCodes,
			String capital, String[] altSpellings, String region, String subregion, Long population, Double[] latlng,
			String demonym, double area, double gini, String[] timezones, String[] borders, String nativeName,
			String numericCode, Currencie[] currencies, Language[] languages, Translation translations, String flag,
			RegionalBloc[] regionalBlocs, String cioc) {
		super();
		this.name = name;
		this.topLevelDomain = topLevelDomain;
		this.alpha2Code = alpha2Code;
		this.alpha3Code = alpha3Code;
		this.callingCodes = callingCodes;
		this.capital = capital;
		this.altSpellings = altSpellings;
		this.region = region;
		this.subregion = subregion;
		this.population = population;
		this.latlng = latlng;
		this.demonym = demonym;
		this.area = area;
		this.gini = gini;
		this.timezones = timezones;
		this.borders = borders;
		this.nativeName = nativeName;
		this.numericCode = numericCode;
		this.currencies = currencies;
		this.languages = languages;
		this.translations = translations;
		this.flag = flag;
		this.regionalBlocs = regionalBlocs;
		this.cioc = cioc;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String[] getTopLevelDomain() {
		return topLevelDomain;
	}



	public void setTopLevelDomain(String[] topLevelDomain) {
		this.topLevelDomain = topLevelDomain;
	}



	public String getAlpha2Code() {
		return alpha2Code;
	}



	public void setAlpha2Code(String alpha2Code) {
		this.alpha2Code = alpha2Code;
	}



	public String getAlpha3Code() {
		return alpha3Code;
	}



	public void setAlpha3Code(String alpha3Code) {
		this.alpha3Code = alpha3Code;
	}



	public String[] getCallingCodes() {
		return callingCodes;
	}



	public void setCallingCodes(String[] callingCodes) {
		this.callingCodes = callingCodes;
	}



	public String getCapital() {
		return capital;
	}



	public void setCapital(String capital) {
		this.capital = capital;
	}



	public String[] getAltSpellings() {
		return altSpellings;
	}



	public void setAltSpellings(String[] altSpellings) {
		this.altSpellings = altSpellings;
	}



	public String getRegion() {
		return region;
	}



	public void setRegion(String region) {
		this.region = region;
	}



	public String getSubregion() {
		return subregion;
	}



	public void setSubregion(String subregion) {
		this.subregion = subregion;
	}



	public Long getPopulation() {
		return population;
	}



	public void setPopulation(Long population) {
		this.population = population;
	}



	public Double[] getLatlng() {
		return latlng;
	}



	public void setLatlng(Double[] latlng) {
		this.latlng = latlng;
	}



	public String getDemonym() {
		return demonym;
	}



	public void setDemonym(String demonym) {
		this.demonym = demonym;
	}



	public double getArea() {
		return area;
	}



	public void setArea(double area) {
		this.area = area;
	}



	public double getGini() {
		return gini;
	}



	public void setGini(double gini) {
		this.gini = gini;
	}



	public String[] getTimezones() {
		return timezones;
	}



	public void setTimezones(String[] timezones) {
		this.timezones = timezones;
	}



	public String[] getBorders() {
		return borders;
	}



	public void setBorders(String[] borders) {
		this.borders = borders;
	}



	public String getNativeName() {
		return nativeName;
	}



	public void setNativeName(String nativeName) {
		this.nativeName = nativeName;
	}



	public String getNumericCode() {
		return numericCode;
	}



	public void setNumericCode(String numericCode) {
		this.numericCode = numericCode;
	}



	public Currencie[] getCurrencies() {
		return currencies;
	}



	public void setCurrencies(Currencie[] currencies) {
		this.currencies = currencies;
	}



	public Language[] getLanguages() {
		return languages;
	}



	public void setLanguages(Language[] languages) {
		this.languages = languages;
	}



	public Translation getTranslations() {
		return translations;
	}



	public void setTranslations(Translation translations) {
		this.translations = translations;
	}



	public String getFlag() {
		return flag;
	}



	public void setFlag(String flag) {
		this.flag = flag;
	}



	public RegionalBloc[] getRegionalBlocs() {
		return regionalBlocs;
	}



	public void setRegionalBlocs(RegionalBloc[] regionalBlocs) {
		this.regionalBlocs = regionalBlocs;
	}



	public String getCioc() {
		return cioc;
	}



	public void setCioc(String cioc) {
		this.cioc = cioc;
	}



	@Override
	public String toString() {
		StringBuilder listado= new StringBuilder();
		listado.append("[País: "+name+" | Capital: "+capital+" | Área: "+area+" | Población: "+population+" | Alpha2Code: "+alpha2Code+"]");
		return listado.toString();
	}

	
	
	/*@Override
	public String toString() {
		StringBuilder listado= new StringBuilder();
		listado.append("\nName: "+name);
		listado.append("\ntopLevelDomain: "+Arrays.toString(topLevelDomain));
		listado.append("\nalpha2Code: "+alpha2Code);
		listado.append("\nalpha3Code: "+alpha3Code);
		listado.append("\ncallingCodes: "+Arrays.toString(callingCodes));
		listado.append("\ncapital: "+capital);
		listado.append("\naltSpellings: "+Arrays.toString(altSpellings));
		listado.append("\nregion: "+region);
		listado.append("\nsubregion: "+subregion);
		listado.append("\npopulation: "+population);
		listado.append("\nlatlng: "+Arrays.toString(latlng));
		listado.append("\ndemonym: "+demonym);
		listado.append("\narea: "+area);
		listado.append("\ngini: "+gini);
		listado.append("\ntimezones: "+Arrays.toString(timezones));
		listado.append("\nborders: "+Arrays.toString(borders));
		listado.append("\nnativeName: "+nativeName);
		listado.append("\nnumericCode: "+numericCode);
		listado.append("\ncurrencies: "+Arrays.toString(currencies));
		listado.append("\nlanguages: "+Arrays.toString(languages));
		listado.append("\ntranslations: "+translations.toString());
		listado.append("\nflag: "+flag);
		listado.append("\nregionalBlocs: "+Arrays.toString(regionalBlocs));	
		listado.append("\ncioc: "+cioc);
		return listado.toString();		
	}*/





	
	
	
	
}
