package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

import Util.Utilidades;
import model.Pedido;

public class PedidosService {
	private final String RUTA = "pedidos.txt";
	private final String SEPARADOR = "-";
	private double facturacion = 0;
	private String path;

	public PedidosService() {
		path = RUTA;
	}

	public boolean agregarPedido(Pedido pedido) {
		if (!existePedido(pedido)) {
			try {
				Files.writeString(path, Utilidades.construirCadena(pedido, SEPARADOR), StandardOpenOption.APPEND);
			} catch (IOException ex) {
				ex.getMessage();
			}
		}
	}

	private boolean existePedido(Pedido pedido) {

	}

	public Pedido procesarPedido() {

	}

	private void volcarPedidos(List<Pedido> pedidos) {
		try {
			Files.write(path,
					pedidos.stream().map(p -> Utilidades.construirCadena(p, SEPARADOR)).collect(Collectors.toList()),
					StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Pedido priorizarPedido(int numeroPedido) {

	}

	private Pedido obtenerPedido(int numeroPedido) {

	}

	public double facturacionPendiente() {

	}

	public void eliminarPedidos(String producto) {

	}

	public List<Pedido> pedidosPendientes() {

	}
}