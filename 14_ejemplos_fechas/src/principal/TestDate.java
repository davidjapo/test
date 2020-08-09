package principal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TestDate {

	public static void main(String[] args) {
		Date fecha = new Date(); // para representar fechas en javabeans por ejemplo.
		System.out.println(fecha);
		System.out.println(fecha.getTime());
		
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, new Locale("es"));
		System.out.println(df.format(fecha));
		DateFormat df2 = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, new Locale("es"));
		System.out.println(df2.format(fecha));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YY");
		System.out.println(sdf.format(fecha));
		
		//Parseado de fecha
		SimpleDateFormat personalizado = new SimpleDateFormat("dd-MM-yyyy");
		String miFecha = "13-01-1982";
		try {
			Date nf = personalizado.parse(miFecha);
			System.out.println(nf);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
