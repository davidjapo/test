package principal;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import recurso.Acumulador;
import service.TablaMultiplicar;

public class Lanzador {
	
	static ExecutorService exec = Executors.newCachedThreadPool();

	public static void main(String[] args) {
		final int tareas = 3;
		Scanner leer = new Scanner(System.in);

		// Creamos el recurso compartido
		Acumulador acumulador = new Acumulador();
		int[] numeros = new int[tareas];
		for (int i = 0; i < numeros.length; i++) {
			System.out.print("Introduzca un número: ");
			numeros[i] = leer.nextInt();
		}
		for (int i : numeros) {
			// Pasamos el recurso compartido a cada tarea para que lo use.
			lanzador(new TablaMultiplicar(i, acumulador));
		}
		exec.shutdown();

	}

	// Lanzamos las tareas
	private static void lanzador(Runnable t) {
		exec.submit(t);
	}

}
