package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class HiloCliente implements Runnable {
	private Socket socket;

	public HiloCliente(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try (PrintStream out = new PrintStream(socket.getOutputStream());
				BufferedReader bf = new BufferedReader (new InputStreamReader(socket.getInputStream()));) {	
			String nombre = bf.readLine();
			out.println("Saludo desde el servidor "+nombre);
		} catch (IOException io) {
			io.printStackTrace();
		}
		finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
