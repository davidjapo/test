			package service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import recurso.Acumulador;

//Entender que si un objeto o recurso está siendo utilizado por varias tareas, y
//utilizando la multitarea, se observa que no le da tiempo a la tarea a realizar la
//suma de la cantidad de multiplicaciones realizadas y se van sobrescribiendo unas
//con otras. Esto es debido a que están los 200ms en sleep().

public class TablaMultiplicar implements Runnable {
	private int num;
	Acumulador acumulador = new Acumulador();
	static Lock lc =new ReentrantLock();

	public TablaMultiplicar(int num, Acumulador acumulador) {
		this.num = num;
		this.acumulador = acumulador;
	}

	@Override
	public void run() {
		int resultado = 0;
		for (int i = 1; i <= 10; i++) {
			System.out.println(num + "x" + i + "=" + (num * i));
			lc.lock();

			synchronized (acumulador) { // Se utiliza el bloque sincronizado para evitar el problema de sobreescritura
										// de la tarea valor++
				int valor = acumulador.getValor();
				valor++;
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				acumulador.setValor(valor);
				lc.unlock();
			}
		}
		System.out.println("Valor del acumulador es: " + acumulador.getValor());
	}

}
