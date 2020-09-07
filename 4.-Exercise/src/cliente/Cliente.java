package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
		
	/* 4.- Desarrollar una aplicación cliente-servidor para la gestión
	 * de los datos Covid:
	 *  
	 * Por un lado, el servidor será una aplicación que recibirá por
	 * parte del cliente el nombre de la comunidad autónoma,
	 * entregándole como respuesta un resumen de datos de esa comunidad:
	 *  
	 * Total positivos, media de positivos diarios y fecha de pico
	 * de contagios de esa comunidad. 
	 * 
	 * Implementar también un cliente que conecte con el servidor,
	 * mande el nombre de la comunidad y guarde los resultados
	 * en un fichero de texto local.
	 */	
	
	public static void main(String[] args) {
		try(Socket socket = new Socket("localhost", 8000);){
			PrintStream out = new PrintStream(socket.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Scanner leer = new Scanner(System.in);
			System.out.print("Introduzca el código ISO de una comunidad autónoma: ");
			out.println(leer.nextLine());
			System.out.println();
			System.out.println(in.readLine());
		}catch(IOException io) {
			io.printStackTrace();
		}
	}
}
