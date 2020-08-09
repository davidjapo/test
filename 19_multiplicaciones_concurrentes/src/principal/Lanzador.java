package principal;

import java.util.Scanner;

import recurso.Acumulador;
import tareas.TareaMultiplicacion;

public class Lanzador {

	public static void main(String[] args) {
		
		}
	
	//Lanzamos las tareas
	private static void lanzador(Runnable t) {
		new Thread(t).start();
	}
}
