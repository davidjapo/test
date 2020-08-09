package service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.stream.Stream;

import model.Casos;
import util.Utilidades;

public class CasosService {

	private final String RUTA = "datos_ccaas.json";
	// private final String SEPARADOR= ",";
	public String path = RUTA;
	// private Path pt = Paths.get(path); // Para ser usado con .csv no para json.

	public CasosService() {
		this.path = RUTA;
	}

	// 1.- Lista de casos producidos entre 2 fechas.
	public void listaCasosEntreFechas(LocalDate fecha1, LocalDate fecha2) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		Date fechaOne = Utilidades.convertLocalDatetoDate(fecha1.minusDays(1));
		Date fechaTwo = Utilidades.convertLocalDatetoDate(fecha2.plusDays(1));
		Utilidades.jsonToStream().filter(c -> c.getFecha().after(fechaOne) && c.getFecha().before(fechaTwo))
				.forEach(c -> System.out.println(Utilidades.convertIsoCcaa(c.getCcaa_iso()) + " - "
						+ sdf.format(c.getFecha()) + " ---> " + c.getNum_casos() + " casos producidos."));
	}

	// 2.- Total de positivos producidos en un día:
	public int positivosXDia(LocalDate dia) {
		return Utilidades.jsonToStream()
			.filter(c -> c.getFecha().equals(dia))
			.mapToInt(c -> c.getNum_casos()) //devuelve un IntStream
			.sum(); // suma los elementos del IntStream anterior???????
		
		/*
		 return Utilidades.jsonToStream()
				.map(Casos::getNum_casos)
				.reduce(0,(a,b)-> a+b);
		*/
	}

	// 3.- Fecha en la que se produjo el pico de contagios.:

	// 4.- Media de positivos diarios:

	// 5.- Total de positivos de una comunidad:

	// 6.- Lista de casos agrupados por comunid:

}
