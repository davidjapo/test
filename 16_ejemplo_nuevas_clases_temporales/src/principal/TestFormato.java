package principal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestFormato {

	public static void main(String[] args) {
		//Para dar formato a una fecha:		
		LocalDate fecha = LocalDate.of(2010,11,24);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println(fecha.format(dtf));
				
		//Parseado de fechas:
		String miFecha = "30/08/2012";
		LocalDate nfecha = LocalDate.parse(miFecha,dtf);
		System.out.println(nfecha);
	}

}
