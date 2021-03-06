package principal;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TestTransformacion {

	public static void main(String[] args) {
		//convertir Date a LocalDate
		Date f=new Date();
		LocalDate ld=Instant.ofEpochMilli(f.getTime())
				.atZone(ZoneId.systemDefault())
				.toLocalDate(); 
		System.out.println(ld);
		//convertir LocalDate a Date
		LocalDate local=LocalDate.of(1999, 9, 25);
		Date fe=Date.from(
				local.atStartOfDay(ZoneId.systemDefault())
				.toInstant()); 
		System.out.println(fe); 

	}

}
