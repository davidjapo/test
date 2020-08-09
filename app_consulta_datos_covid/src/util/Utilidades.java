package util;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Stream;

import com.google.gson.Gson;

import model.Casos;
import service.CasosService;


public class Utilidades {
	
	//Utilidad para transformar de Objeto Casos a Cadena de carácteres
	//y para transformar de Cadena de carácteres a Objeto Casos
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static final String SEPARADOR = ",";
	

	/*public static Casos construirCaso(String cadena) {
		String[] array = cadena.split(SEPARADOR);
		try {
			return new Casos(array[0], Date.parse(array[1]),Integer.parseInt(array[2]));
		}
		catch(ParseException pe) {
			pe.getMessage();
			pe.printStackTrace();
			return null;
		}
	}
	*/
	
	/*
	public static String construirCadena(Pedido pedido, String SEPARADOR) {
		return pedido.getProducto()+SEPARADOR+pedido.getUnidades()+SEPARADOR+pedido.getPrecioUnitario()+SEPARADOR+pedido.getSeccion()+SEPARADOR+sdf.format(pedido.getFecha());
	}*/
	
	
	//Utilidad de conversión de fechas:	
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

	//Utilidad para convertir las ccaa en format ISO 2 digitos en nombres:
	public static String convertIsoCcaa(String iso2D){
		switch(iso2D) {
		case "AN":
			return "Andalucía (AN)";
		case "AR":
			return "Aragón (AR)";
		case "AS":
			return "Asturias (AS)";
		case "CN":
			return "Canarias (CN)";
		case "CB":
			return "Cantabria (CB";
		case "CM":
			return "Castilla La Mancha (CM)";
		case "CL":
			return "Castilla y León (CL)";
		case "CT":
			return "Cataluña (CT)";
		case "EX":
			return "Extremadura (EX)";
		case "GA":
			return "Galicia (GA)";
		case "IB":
			return "Islas Baleares (IB)";
		case "RI":
			return "La Rioja (RI)";
		case "MD":
			return "Comunidad de Madrid (MD)";
		case "MC":
			return "Murcia (MC)";
		case "NC":
			return "Navarra (NC)";
		case "PV":
			return "País Vasco (PV)";
		case "VC":
			return "Comunidad Valenciana (VC)";
		case "CE":
			return "Ceuta (CE)";
		case "ML":
			return "Melilla (ML)";
		}
		return null;
	}
	
	
	
	public static Stream<Casos> jsonToStream(){
		Gson gson = new Gson();
		CasosService service = new CasosService();
		Reader lectura = null;
		try {
			lectura = Files.newBufferedReader(Paths.get("datos_ccaas.json"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Casos[] casos = gson.fromJson(lectura,Casos[].class);
		return Arrays.stream(casos); 
	}
	
	
	//Utilidad para transformar de Stream a objeto Casos:
	

}
