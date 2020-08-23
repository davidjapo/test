package principal;

import java.util.Scanner;

import service.CuentaService;

/*
 * Desarrollar una aplicación para un cajero virtual,
 * utilizando la base de datos “bancadb”. Al iniciarse el
 * programa se solicitará al usuario la introducción del número de
 * cuenta, si es válido, se le mostrarán al usuario las opciones a
 * realizar, sino, se mostrará un mensaje de error y el programa
 * finalizará. Las opciones que debe permitir el programa son:
 * sacar dinero, ingresar dinero, mostrar saldo y últimos movimientos
 * (de los últimos 30 días), realizar transferencia a otra cuenta.
*  Se realizará una segunda parte de la aplicación para administradores,
*  que permitirá añadir nuevas cuentas, nuevos clientes y titulares
*  asociados a cuentas.
 */

public class Presentacion {

	private static CuentaService service = new CuentaService();
	private static Scanner leer = new Scanner(System.in);
	private static int numCuenta;

	public static void main(String[] args) {
		solicitarCuenta(service, leer);
	}

	private static void printMenu() {
		System.out.println();
		System.out.println("**************************");
		System.out.println("  ---Cajero Virtual---");
		System.out.println("**************************");
		System.out.println();
		System.out.println("1.- Ingresar dinero");
		System.out.println("2.- Sacar dinero");
		System.out.println("3.- Mostrar saldo y últimos movimientos");
		System.out.println("4.- Realizar transferencia a otra cuenta");
		System.out.println("5.- Acceso administradores");
		System.out.println("6.- Salir");
		System.out.println();
		System.out.print("Introduzca una opción: ");
	}

	private static void solicitarCuenta(CuentaService datos, Scanner leer) {
		System.out.print("\nIntroduzca el número de cuenta: ");
		numCuenta = Integer.parseInt(leer.nextLine());
		if (datos.validarCuenta(numCuenta)) {
			callMenu();
		} else {
			System.out.println("Error: El número de cuenta no existe.");
		}
	}

	private static void callMenu() {
		int opcion = 0;
		do {
			printMenu();
			opcion = Integer.parseInt(leer.nextLine());
			switch (opcion) {
			case 1:
				ingresar(service, leer, numCuenta);
				break;
			case 2:
				extraer(service, leer, numCuenta);
				break;
			case 3:
				movimientos(service, numCuenta);
				break;
			case 4:
				transferencia(service, numCuenta, leer);
				break;
			case 5:
				Menu.callMenu();
				break;
			case 6:
				System.out.println("Estás saliendo del programa...");
				break;
			default:
				System.out.println("No has elegido una opción válida.");
				break;
			}
		} while (opcion != 6);

	}

	private static void ingresar(CuentaService datos, Scanner leer, int numCuenta) {
		System.out.print("Introduzca la cantidad a ingresar: ");
		double cantidad = Double.parseDouble(leer.nextLine());
		if (datos.ingresarDinero(numCuenta, cantidad)) {
			System.out.println(cantidad + " euros han sido ingresados en la cuenta " + numCuenta);
		} else {
			System.out.println("No se ha podido realizar el ingreso...\nContacte con su oficina virtual.");
		}
	}

	private static void extraer(CuentaService datos, Scanner leer, int numCuenta) {
		System.out.print("Introduzca la cantidad a extraer: ");
		double cantidad = Double.parseDouble(leer.nextLine());
		if (datos.extraerDinero(numCuenta, cantidad)) {
			System.out.println("De la cuenta nº " + numCuenta + " se han extraído " + cantidad + " euros.");
		} else {
			System.out.println("No se ha podido realizar la extracción...\nContacte con su oficina virtual.");
		}
	}

	private static void movimientos(CuentaService datos, int numCuenta) {
		System.out.println("Estos son los movimientos de los últimos 30 días:\n");
		System.out.println("-------------------------------------------------");
		System.out.println("|       Fecha       |   Operación   |  Cantidad  ");
		System.out.println("-------------------------------------------------");
		datos.getMovimientos(numCuenta);
		System.out.println("\nSaldo de la cuenta: " + datos.getSaldoCuenta(numCuenta) + " euros.");
		System.out.println();
		System.out.println();
	}

	private static void transferencia(CuentaService datos, int numCuenta, Scanner leer) {
		System.out.print("Introduzca el nº de cuenta de destino: ");
		int cuentaDestino = Integer.parseInt(leer.nextLine());
		if (datos.validarCuenta(cuentaDestino)) {
			System.out.print("Introduzca la cantidad a transferir: ");
			double cantidad = Integer.parseInt(leer.nextLine());
			if (datos.transferencia(numCuenta, cuentaDestino, cantidad)) {
				System.out.println("La transferencia de " + cantidad + " euros a la cuenta " + cuentaDestino
						+ " ha sido realizada correctamente.");
			} else {
				System.out.println("La transferencia no ha podido realizarse.");
			}
		} else {
			System.out.println("Error: El número de cuenta destino no existe.");
			callMenu();
		}
	}

}
