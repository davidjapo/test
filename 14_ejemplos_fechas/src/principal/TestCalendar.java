package principal;

import java.util.Calendar;

public class TestCalendar {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		System.out.println("Año: "+cal.get(Calendar.YEAR));
		System.out.println("Mes: "+cal.get(Calendar.MONTH));
		System.out.println("DIA: "+cal.get(Calendar.DAY_OF_MONTH));
		System.out.println("Hora: "+cal.get(Calendar.HOUR_OF_DAY));
		System.out.println("Minutos: "+cal.get(Calendar.MINUTE));
		System.out.println("Día de la semana: "+cal.get(Calendar.DAY_OF_WEEK));

	}

}
