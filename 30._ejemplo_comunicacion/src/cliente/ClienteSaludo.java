package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClienteSaludo {
	
	public static void main(String[] args) {
		try(Socket sc = new Socket("localhost",8000);){
		PrintStream out = new PrintStream(sc.getOutputStream());
		out.println("David");
			BufferedReader bf = new BufferedReader (new InputStreamReader(sc.getInputStream()));
		String dato = bf.readLine();
		System.out.println("El servidor me envía: "+dato);
		}
		catch(IOException ex) {
			ex.printStackTrace();	
		}
	}
}
