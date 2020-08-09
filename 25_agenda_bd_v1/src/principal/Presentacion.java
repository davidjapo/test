package principal;

import java.util.Scanner;

import model.Contacto;
import service.ContactoService;

public class Presentacion {

	static Scanner leer = new Scanner(System.in);

	public static void main(String[] args) {
		callMenu();
	}

	public static void printMenu() {
		System.out.println();
		System.out.println("1.- Agregar contacto");
		System.out.println("2.- Eliminar contacto");
		System.out.println("3.- Buscar contacto");
		System.out.println("4.- Mostrar contactos");
		System.out.println("5.- Salir");
		System.out.println();
		System.out.print("Elige una opción: ");
	}

	public static void callMenu() {
		ContactoService service = new ContactoService();
		int opcion;
		do {
			printMenu();
			opcion = Integer.parseInt(leer.nextLine());
			switch (opcion) {
			case 1:
				agregarContacto(service);
				break;
			case 2:
				eliminarContacto(service);
				break;
			case 3:
				buscarContacto(service);
				break;
			case 4:
				mostrarContacto(service);
				break;
			case 5:
				System.out.println("\nEstas saliendo del programa...");
				break;
			default:
				System.out.println("La opción no es correcta...");
				break;
			}

		} while (opcion !=3);

	}

	public static void agregarContacto(ContactoService service) {
		Contacto contacto;
		System.out.print("\nIntroduce el nombre: ");
		String nombre = leer.nextLine();
		System.out.print("Introduce el email: ");
		String email = leer.nextLine();
		System.out.print("Introduce la edad: ");
		int edad = Integer.parseInt(leer.nextLine());
		contacto =new Contacto(nombre,email,edad);
		if(service.addContacto(contacto)) {
			System.out.println("\nContacto añadido!");
		}else {
			System.out.println("Contacto NO se ha añadido.");
		}
	}
	
	public static void mostrarContacto(ContactoService service) {
		System.out.println("Estos son los nombres obtenidos:\n");
		
	}
	
	public static void eliminarContacto(ContactoService service) {
		System.out.print("Introduzca el email del contacto a eliminar: ");
		String email = leer.nextLine();
		if(service.delContact(email)) {
			System.out.println("\nEl contacto ha sido eliminado.");
		}else {
			System.out.println("No existe ese email.");
		}
	}
	
	public static void buscarContacto(ContactoService service) {
		System.out.print("Introduzca el email del contacto a buscar: ");
		String email = leer.nextLine();
		Contacto contacto = service.searchContact(email);
		if(contacto!=null) {
			System.out.println("Id: "+contacto.getIdcontacto());
			System.out.println("Nombre: "+contacto.getNombre());
			System.out.println("Email: "+contacto.getEmail());
			System.out.println("Edad: "+contacto.getEdad());
		}else {
			System.out.println("No existe un contacto con ese email...");
		}
	}

}
