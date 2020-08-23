package principal;

import java.util.Scanner;

import model.Cliente;
import model.Cuenta;
import model.Titular;
import service.MenuService;

/*
 *  Se realizará una segunda parte de la aplicación para administradores,
 *  que permitirá añadir nuevas cuentas, nuevos clientes y titulares
 *  asociados a cuentas.
 */

public class Menu {

	public static void main(String[] args) {
		callMenu();

	}
	
	private static void printMenu() {
		System.out.println();
		System.out.println("*************************");
		System.out.println("      Administración     ");
		System.out.println("      Cajero Virtual     ");
		System.out.println("*************************");
		System.out.println();
		System.out.println("1.- Añadir nueva cuenta");
		System.out.println("2.- Añadir nuevo cliente");
		System.out.println("3.- Añadir titutales");
		System.out.println("4.- Salir");
		System.out.println();
		System.out.print("Introduzca una opción: ");
	}
	
	public static void callMenu() {
		Scanner leer = new Scanner (System.in);
		MenuService service = new MenuService();
		int opcion=0;
		do {
			printMenu();
			opcion = Integer.parseInt(leer.nextLine());
			switch(opcion) {
			case 1:
				nuevaCuenta(service,leer);
				break;
			case 2:
				nuevoCliente(service, leer);
				break;
			case 3:
				nuevoTitular(service, leer);
				break;
			case 4:
				System.out.println("Estás saliendo del programa.");
				break;
			default:
				System.out.println("\nNo has elegido una opción válida...");
				break;
			}
		}while(opcion!=4);
	}
	
	private static void nuevaCuenta(MenuService datos, Scanner leer) {
		System.out.print("\nIntroduzca el número de cuenta a crear: ");
		int numCuenta = Integer.parseInt(leer.nextLine());
		System.out.print("Introduzca el tipo de cuenta a crear: ");
		String tipo = leer.nextLine();
		System.out.print("Introduzca el saldo inicial de la cuenta: ");
		double saldo = Double.parseDouble(leer.nextLine());
		Cuenta newCuenta = new Cuenta(numCuenta,saldo,tipo);
		if(datos.addCuenta(newCuenta)) {
			System.out.println("\nEl nº de cuenta "+ numCuenta+" ha sido añadido correctamente.");
		}else {
			System.out.println("\nEl nº de cuenta "+ numCuenta+" ya existe.");
		}
	}
	
	private static void nuevoCliente(MenuService datos, Scanner leer) {
		System.out.print("\nIntroduzca el nº de DNI (sin letra) del cliente a crear: ");
		int dni = Integer.parseInt(leer.nextLine());
		System.out.print("Introduzca el nombre del cliente: ");
		String nombre = leer.nextLine();
		System.out.print("Introduca la dirección del cliente: ");
		String direccion = leer.nextLine();
		System.out.print("Introduzca el teléfono del cliente: ");
		int telefono = Integer.parseInt(leer.nextLine());
		Cliente newCliente = new Cliente(dni,nombre,direccion,telefono);
		if(datos.addCliente(newCliente) == 1) {
			System.out.println("\nEl cliente con nº de DNI "+dni+" ha sido añadido correctamente.");
		}else {
			System.out.println("\nYa existe el nº de DNI "+dni+" asociado a otro cliente.");
		}
	}
	
	private static void nuevoTitular(MenuService datos, Scanner leer) {
		System.out.print("\nIntroduzca el dni del cliente: ");
		int dni = Integer.parseInt(leer.nextLine());
		System.out.print("Introduzca el nº de cuenta: ");
		int numCuenta = Integer.parseInt(leer.nextLine());
		Titular newTitular = new Titular(numCuenta,dni); 
		if(datos.addTitutar(newTitular) == 1) {
			System.out.println("\nEl nº de cuenta "+numCuenta+" ha sido asociado al cliente con DNI nº "+dni+" satisfactoriamente.");
		}else {
			System.out.println("\nEl nº de cuenta "+numCuenta+" ya existe asociado a este titular.");
		}
	}

}
