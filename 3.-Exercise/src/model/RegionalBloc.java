package model;

import java.util.Arrays;
import java.util.List;

public class RegionalBloc {
	private String acronym;
	private String name;
	private String[] otherAcronyms;
	private String[] otherNames;
	
	public RegionalBloc(String acronym, String name, String[] otherAcronyms, String[] otherNames) {
		super();
		this.acronym = acronym;
		this.name = name;
		this.otherAcronyms = otherAcronyms;
		this.otherNames = otherNames;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getOtherAcronyms() {
		return otherAcronyms;
	}

	public void setOtherAcronyms(String[] otherAcronyms) {
		this.otherAcronyms = otherAcronyms;
	}

	public String[] getOtherNames() {
		return otherNames;
	}

	public void setOtherNames(String[] otherNames) {
		this.otherNames = otherNames;
	}

	@Override
	public String toString() {
		return "acronym:"+acronym+",name:"+name+",otherAcronyms:"+Arrays.toString(otherAcronyms)+",otherNames"+Arrays.toString(otherNames);
	}
	
	

}
