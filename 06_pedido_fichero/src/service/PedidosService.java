package service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import model.Pedido;

public class PedidosService {
	private List<Pedido> pedidos = new ArrayList<>();
	private double facturacion;
	String ruta = "Pedidos.txt";

	public boolean listaLlena() {
		return pedidos.isEmpty();
	}

	public int longitudLista() {
		return pedidos.size();
	}

	public boolean agregarPedido(Pedido pedido) {
		return agregarAFichero(pedido);
	}

	private boolean agregarAFichero(Pedido pedido) {
		if (!existePedido(pedido)) {
			try (PrintStream ps = new PrintStream(new FileOutputStream(ruta, true));) {
				ps.println(pedido.getNumeroPedido() + "," + pedido.getProducto() + "," + pedido.getPrecio());
				pedidos.clear();
				llenarLista();
				return true;
			} catch (IOException ex) {
				ex.getMessage();
				ex.printStackTrace();
			}
		}
		return false;
	}

	public void llenarLista() throws IOException {
		BufferedReader bf = new BufferedReader(new FileReader(ruta));
		String linea;
		while ((linea = bf.readLine()) != null) {
			Pedido pedido = new Pedido();
			String[] temp = linea.split(",");
			for (int i = 0; i < temp.length;) {
				pedido.setNumeroPedido(Integer.parseInt(temp[i]));
				i++;
				pedido.setProducto(temp[i]);
				i++;
				pedido.setPrecio(Double.parseDouble(temp[i]));
				i++;
			}
			if (!existePedido(pedido))
				pedidos.add(pedido);
		}bf.close();
	}

	private boolean existePedido(Pedido pedido) {
		return pedidos.contains(pedido); // contains recorrre la lista y llama al equals para comparar.

	}

	public Pedido procesarPedido() throws IOException {
		pedidos.clear();
		llenarLista();
		return pedidos.remove(0);

	}

	public Pedido priorizarPedido(int numeroPedido) throws IOException {
		Pedido pedido = null, aux;
		pedidos.clear();
		llenarLista();
		for (Pedido p : pedidos) {
			if (p.getNumeroPedido() == numeroPedido) {
				pedido = p;
				int pos = pedidos.indexOf(pedido);
				// Se intercambia con el anterior si no es el primero:
				if (pos > 0) {
					// intercambiamos el pedido por el de la posicion anterior
					aux = pedidos.get(pos - 1);
					pedidos.set(pos - 1, pedido);
					pedidos.set(pos, aux);
				}
			}
		}
		actualizarFichero();
		return pedido;
	}

	public void actualizarFichero() throws FileNotFoundException {
		PrintStream zero = new PrintStream(ruta);
		PrintStream ps = new PrintStream(new FileOutputStream(ruta, true));
		if(!pedidos.isEmpty()) {
			zero.println(pedidos.get(0).getNumeroPedido() + "," + pedidos.get(0).getProducto() + ","
					+ pedidos.get(0).getPrecio());
			for (int i = 1; i < pedidos.size(); i++) {
				ps.println(pedidos.get(i).getNumeroPedido() + "," + pedidos.get(i).getProducto() + ","
						+ pedidos.get(i).getPrecio());
			}
		}ps.close();
		zero.close();

	}

	public double facturacionPendiente() throws IOException {
		facturacion = 0;
		pedidos.clear();
		llenarLista();
		pedidos.forEach(p -> facturacion += p.getPrecio());
		return facturacion;
	}

	// Elimina todos los pedidos cuyo nombre de producto contenga ese texto.
	public void eliminarPedidos(String producto) {
		pedidos.removeIf(p -> p.getProducto().contains(producto));
	}

	public List<Pedido> pedidosPendientes() throws IOException {
		pedidos.clear();
		llenarLista();
		return pedidos;
	}
}
