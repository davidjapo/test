package presentacion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor {
		
	public static void main(String[] args) {
		System.out.println("Esperando llamada entrante...");
		ExecutorService executor = Executors.newCachedThreadPool();
		try(ServerSocket server = new ServerSocket(8000);){
			while(true) {
				Socket sc = server.accept();
				{
					System.out.println("Llamada entrante recibida.");
				}
				executor.submit(new HiloCliente(sc));
			}
		}catch(IOException io) {
			io.printStackTrace();
		}
		
	}
}
