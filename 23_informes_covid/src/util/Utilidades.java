package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

import model.CaseJson;
import model.Caso;

public class Utilidades {
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	static HashMap<String, String> comunidades = new HashMap<>();	
	static {
		comunidades.put("AN", "Andalucía");
		comunidades.put("AR", "Aragón");
		comunidades.put("AS", "Asturias");
		comunidades.put("CN", "Canarias");
		comunidades.put("CB", "Cantabria");
		comunidades.put("CM", "Castilla-La Mancha");
		comunidades.put("CL", "Castilla y León");
		comunidades.put("CT", "Cataluña");
		comunidades.put("EX", "Extremadura");
		comunidades.put("GA", "Galicia");
		comunidades.put("IB", "Islas Baleares");
		comunidades.put("RI", "La Rioja");
		comunidades.put("MD", "Madrid");
		comunidades.put("MC", "Murcia");
		comunidades.put("NC", "Navarra");
		comunidades.put("PV", "País Vasco");
		comunidades.put("VC", "Valenciana");
		comunidades.put("CE", "Ceuta");
		comunidades.put("ML", "Melilla");
	}
	
	public static String convertirAbreviaturaNombre(String abr) {
		return comunidades.get(abr);
	}

	public static Caso stringToCaso(String f) {
		String[] partes =f.split("[,]");
		try {
			return new Caso(convertirAbreviaturaNombre(partes[0]),
					sdf.parse(partes[1]),
					Integer.parseInt(partes[2]));
		}
		catch(ParseException pe) {
			pe.getMessage();
			pe.printStackTrace();
			return null;
		}
	}
	

	
	public static LocalDate convertDateToLocalDate(Date f) {
		//convertir Date a LocalDate
				LocalDate ld=Instant.ofEpochMilli(f.getTime())
						.atZone(ZoneId.systemDefault())
						.toLocalDate(); 
				return ld;
	}
	
	public static Date convertLocalDatetoDate(LocalDate local) {
		//convertir LocalDate a Date
		Date fe=Date.from(
				local.atStartOfDay(ZoneId.systemDefault())
				.toInstant()); 
		return fe; 
	}
	
		
	public static Caso convertirJsonACaso(CaseJson json) {
		return new Caso(convertirAbreviaturaNombre(json.getCcaa_iso()),json.getFecha(),json.getNum_casos());
	}
	
}
