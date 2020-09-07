package presentacion;

import java.net.Socket;

import service.CasoService;

public class HiloCliente implements Runnable{
	private Socket socket;

	public HiloCliente(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		CasoService datos = new CasoService(socket);
	}
}
