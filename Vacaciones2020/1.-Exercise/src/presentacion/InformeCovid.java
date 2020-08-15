/*
 * Realizar una aplicación para cargar los datos de COVID de un csv o un JSON a base de datos.
 * Al iniciarse la aplicación, solicitará la dirección del fichero y procederá a cargar en la
 * base de datos los datos del fichero que no estén ya cargados. Es decir, aquellas
 * combinaciones de fecha y abreviatura de comunidad que ya estén en la BD no se cargarán
 * de nuevo. 
 */

package presentacion;

import java.util.Scanner;

import service.CasosService;

public class InformeCovid {
	static Scanner leer = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("\n******************************");
		System.out.println(" -Programa de volcado a BBDD- ");
		System.out.println("******************************");
		System.out.println();
		System.out.print("Introduzca la ruta del fichero a volcar: ");
		String ruta = leer.nextLine();

		if (ruta.endsWith(".csv")) {
			CasosService service = new CasosService(ruta);
			// Se controla si el archivo existe o no:
			String resultado = (service.ifExistFile()) ? "Procesando..." : "El fichero no existe.";
			System.out.println(resultado);

			// Se controla el mensaje al usuario según el procesado realizado.
			resultado = (service.csvToDDBB(service.csvToList())) ? 
					"Se han leído " + service.csvToList().size() + " registros y "+service.getContador()+" han sido"
							+ " insertados en la base de datos satisfactoriamente!" : "El volcado no se ha realizado.";
			System.out.println(resultado);

		} else if (ruta.endsWith(".json")) {
			CasosService service = new CasosService(ruta);
			// Se controla si el archivo existe o no:
			String resultado = (service.ifExistFile()) ? "Procesando..." : "El fichero no existe.";
			System.out.println(resultado);

			// Se controla el mensaje al usuario según el procesado realizado.
			resultado = (service.jsonToDDBB(service.jsonToArray())) ? service.getContador()+" registros se han insertado en la base de datos sin errores."
					: "El volcado no ha sido realizado.";
			System.out.println(resultado);
		} else {
			System.out.println("El nombre del fichero no es correcto. ¡Intentelo de nuevo!");
		}

	}

}
