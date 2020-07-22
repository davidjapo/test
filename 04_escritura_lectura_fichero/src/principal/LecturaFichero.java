package principal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LecturaFichero {

	public static void main(String[] args) {
		String ruta="/home/japo/Escritorio/"
				+ "Enlace hacia Curso Programador JAVA - Getafe - 2020/"
				+ "5.- Projectos_JAVA/ejercicios/acceso_a_ficheros/pruebas.txt";
		try(FileReader fr = new FileReader(ruta);
			BufferedReader bf = new BufferedReader(fr);){
			
			/*String linea=bf.readLine();
			while(linea!=null) {
			  System.out.println(linea);
			  linea=bf.readLine();
			}*/
			 
			String linea;
			while((linea=bf.readLine())!=null) {
				System.out.println(linea);
			}
			
			
			//System.out.println(bf.readLine());
			//System.out.println(bf.readLine());
			//System.out.println(bf.readLine());
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}

}
