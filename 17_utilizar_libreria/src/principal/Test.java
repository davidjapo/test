package principal;

import java.time.LocalDate;
import java.util.Date;

import util.Utilidades;

public class Test {
	public static void main(String[] args) {
		Date miFecha = new Date();
		LocalDate nd = Utilidades.convertirALocalDate(miFecha);
	}
}
