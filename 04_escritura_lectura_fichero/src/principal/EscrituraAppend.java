package principal;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class EscrituraAppend {

	public static void main(String[] args) {
		String ruta="/home/japo/Escritorio/"
				+ "Enlace hacia Curso Programador JAVA - Getafe - 2020/"
				+ "5.- Projectos_JAVA/ejercicios/acceso_a_ficheros/pruebas.txt";
		 //si es true se activa el modo append para no sobrescribir el fichero.
		try(FileOutputStream fout = new FileOutputStream(ruta, true);
			PrintStream out = new PrintStream(fout)){
		
		out.println("\nHola David");
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}


	}

}
