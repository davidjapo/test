package principal;

import java.io.IOException;
import java.util.Scanner;

import model.Pedido;
import service.PedidosService;

public class GestorPedidos {

	public static void main(String[] args) throws IOException {
		callMenu();
	}

	private static void printMenu() {
		System.out.println();
		System.out.println("\n1.- Agregar pedido");
		System.out.println("2.- Procesar pedido");
		System.out.println("3.- Priorizar pedido");
		System.out.println("4.- Facturación pendiente");
		System.out.println("5.- Pedidos pendientes");
		System.out.println("6.- Salir");
	}

	private static void callMenu() throws IOException {
		PedidosService datos = new PedidosService();
		Scanner leer = new Scanner(System.in);
		int opcion;
		datos.llenarLista();
		do {
			printMenu();
			System.out.print("\nElige una opción: ");
			opcion = Integer.parseInt(leer.nextLine());
			switch (opcion) {
			case 1:
				addPedido(datos, leer);
				break;
			case 2:
				procesar(datos);
				break;
			case 3:
				priorizar(datos, leer);
				break;
			case 4:
				facturacionPendiente(datos);
				break;
			case 5:
				pedidosPendientes(datos);
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

	private static void addPedido(PedidosService data, Scanner leer) {
		Pedido p = new Pedido();
		System.out.print("\nNúmero de Pedido: ");
		p.setNumeroPedido(Integer.parseInt(leer.nextLine()));
		System.out.print("Nombre de producto: ");
		p.setProducto(leer.nextLine());
		System.out.print("Precio: ");
		p.setPrecio(Double.parseDouble(leer.nextLine()));
		if (data.agregarPedido(p)) {
			System.out.println("\nEl pedido nº " + p.getNumeroPedido() + " ha sido enviado a "
					+ "la cola de pedidos pendientes de procesar.");
		} else {
			System.out.println(
					"El número de pedido '" + p.getNumeroPedido() + "' ya existe, introduzca otro distinto...");
		}
	}

	private static void procesar(PedidosService data) throws IOException {
		if (!data.listaLlena()) {
			System.out.println("\nEl pedido nº " + data.procesarPedido().getNumeroPedido() + " está siendo procesado.");
			data.actualizarFichero();
		} else {
			System.out.println("No se ha procesado ningún pedido...");
		}
	}

	private static void priorizar(PedidosService data, Scanner leer) throws IOException {
		if (!data.listaLlena() && data.longitudLista()!=1) {
			System.out.print("\nIntroduzca el nº de pedido a priorizar: ");
			int num = Integer.parseInt(leer.nextLine());
			if (data.priorizarPedido(num) != null) {
				System.out.println("El pedido " + num + " ha sido priorizado en la lista.");
			} else {
				System.out.println("El nº de pedido introducido no existe.");
			}
		} else {
			System.out.println("No hay pedidos que priorizar...");
		}

	}

	private static void facturacionPendiente(PedidosService data) throws IOException {

		if (data.facturacionPendiente() != 0) {
			System.out.println(
					"\nLa facturación pendiente por procesar es de " + data.facturacionPendiente() + " euros.");
		} else {
			System.out.println("No hay facturación pendiente por procesar...");
		}
	}

	private static void pedidosPendientes(PedidosService data) throws IOException {
		if (!data.listaLlena()) {
			System.out.println("\nEstos son los productos pendientes de procesar:");
			for (Pedido p : data.pedidosPendientes()) {
				System.out.println(p.getProducto());
			}
		} else {
			System.out.println("No hay pedidos pendientes de procesar...");
		}
	}

}
