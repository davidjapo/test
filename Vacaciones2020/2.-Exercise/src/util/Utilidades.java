package util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Utilidades {
	
	// Utilidad de conversión de fechas:
	
	public static DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public static LocalDate convertDateToLocalDate(Date f) {
		// convertir Date a LocalDate
		LocalDate ld = Instant.ofEpochMilli(f.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		return ld;
	}

	public static Date convertLocalDatetoDate(LocalDate local) {
		// convertir LocalDate a Date
		Date fe = Date.from(local.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return fe;
	}
}
