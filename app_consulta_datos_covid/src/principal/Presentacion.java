package principal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

import service.CasosService;
import util.Utilidades;

public class Presentacion {
	
	private static Scanner leer = new Scanner(System.in);

	public static void main(String[] args) {
		callMenu();

	}
	
	private static void printMenu() {
		System.out.println("\n1.- Lista de casos producidos entre 2 fechas.");
		System.out.println("2.- Total de positivos producidos en un día.");
		System.out.println("3.- Fecha en la que se produjo el pico de contagios.");
		System.out.println("4.- Media de positivos diarios.");
		System.out.println("5.- Total de positivos de una comunidad.");
		System.out.println("6.- Lista de casos agrupados por comunidad");
		System.out.println("7.- Salir");
		System.out.println();
		System.out.print("Elija una opción: ");
	}
	
	private static void callMenu() {
		CasosService service = new CasosService();
		int opcion;
		//service.cargarDatos();
		do {
			printMenu();
			opcion = Integer.parseInt(leer.nextLine());
			switch(opcion) {
			case 1:
				listaEntreFechas(service);
				break;
			case 2:
				totalXDia(service);
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				System.out.println("\nEstas saliendo del programa...");
				break;
			default:
				System.out.println("No has elegido una opción válida.");
				break;
			}
		}while(opcion!=7);
	}
	
	private static void listaEntreFechas(CasosService datos) {
		System.out.print("Introduzca la fecha inicial (dd/MM/yyyy): ");
		String fecha1= leer.nextLine();
		System.out.print("Introduzca la fecha final (dd/MM/yyyy): ");
		String fecha2= leer.nextLine();
		System.out.println();
		LocalDate ld1 = LocalDate.parse(fecha1, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		LocalDate ld2 = LocalDate.parse(fecha2, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		datos.listaCasosEntreFechas(ld1,ld2);
	}

	private static void totalXDia(CasosService datos) {
		System.out.print("Introduzca una fecha (dd/MM/yyyy): ");
		String dia = leer.nextLine();
		System.out.println();
		LocalDate fecha = LocalDate.parse(dia, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		System.out.println(datos.positivosXDia(fecha)+" positivos en toda España para este día.");
	}
}
