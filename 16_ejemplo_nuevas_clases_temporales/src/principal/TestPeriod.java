package principal;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TestPeriod {

	public static void main(String[] args) {
		//Cuantos días de diferencia hay entre 2 instantes de tiempo:
		long f1 = 20946599508L;
		long f2 = 22947729031L;
		/*
		 * LocalDate ld1 = LocalDate.ofEpochDay(f1); LocalDate ld2 =
		 * LocalDate.ofEpochDay(f2);
		 */
		LocalDateTime ld1 = LocalDateTime.ofInstant(Instant.ofEpochMilli(f1), ZoneId.systemDefault());
		LocalDateTime ld2 = LocalDateTime.ofInstant(Instant.ofEpochMilli(f2), ZoneId.systemDefault());
		/*
		 * Period periodo = Period.between(ld2, ld1); periodo = periodo.normalized();
		 * System.out.println(periodo); System.out.println("Días: "+periodo.getDays());
		 * System.out.println("Meses: "+periodo.getMonths());
		 */
		Duration duracion =Duration.between(ld2, ld1);
		System.out.println(duracion);
		System.out.println("Días: "+duracion.toDays());
	}

}
