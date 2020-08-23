package model;

public class Currencie {
	private String code;
	private String name;
	private String symbol;
	
	public Currencie(String code, String name, String symbol) {
		super();
		this.code = code;
		this.name = name;
		this.symbol = symbol;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "code:"+code+",name:"+name+",symbol:"+symbol;
	}
	
	
	

}
