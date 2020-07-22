package principal;

import java.io.IOException;
import java.io.PrintStream;

public class Escritura {

	public static void main(String[] args) {
		String ruta="/home/japo/Escritorio/"
				+ "Enlace hacia Curso Programador JAVA - Getafe - 2020/"
				+ "5.- Projectos_JAVA/ejercicios/acceso_a_ficheros/pruebas.txt";
		try(PrintStream out = new PrintStream(ruta)){
		
		out.println("Es la línea 1");
		out.println(3555);
		out.println(true);
		out.println("\nFin del programa");
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}

	}

}
