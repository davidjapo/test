																																package service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.Pedido;
import util.Utilidades;

public class GestorPedidos {
	Path path;
	private final String dir = "pedidos.txt";
	private final String SEPARADOR = ",";

	//Te devuelve un Stream de Pedido
	private Stream<Pedido> streamPedido() {
		try {
			return Files.lines(path, StandardCharsets.UTF_8)
					//.map(s -> Utilidades.construirPedido(s));
					.map(Utilidades::construirPedido); // es como la expresión anterior pero expresada como referencia a metodo en lugar de como expresión lambda.
		} catch (IOException ex) {
			ex.getMessage();
			ex.printStackTrace();
			return null;
		}
	}

	// almacena el pedido recibido
	public void grabarPedido(Pedido pedido) {
		try {
			Files.writeString(path, Utilidades.construirCadena(pedido, SEPARADOR) + System.lineSeparator(),
					StandardCharsets.UTF_8, StandardOpenOption.APPEND);
		} catch (IOException ex) {
			ex.getMessage();
			ex.printStackTrace();
		}
	}

	// media de ventas pedidos de esa seccion
	public double promedioSeccion(String seccion) {
		return streamPedido()
					.filter(p -> p.getSeccion().equals(seccion))
					.collect(Collectors.averagingDouble(p -> p.getPrecioUnitario() * p.getUnidades()));
	}

	// ventas totales de el producto indicado
	public double totalProducto(String producto) {
		return streamPedido()
				.filter(p->p.getProducto().equals(producto))
				

	}

	// devuelve el pedido con venta superior

	public Pedido pedidoSuperior() {

	}

	// devuelve lista de pedidos de una sección
	public List<Pedido> pedidosSeccion(String seccion) {
		return streamPedido()
				.filter(p->p.getSeccion().equals(seccion))
				.collect(Collectors.toList());
				
	}

	// devuelve el pedido con fecha más reciente
	public Pedido pedidoMasReciente() {
		return streamPedido()
				.max((p1,p2)->p1.getFecha().compareTo(p2.getFecha()))
				.orElse(null);
	}

	// devuelve lista de pedidos, posteriores a la fecha indicada
	public List<Pedido> pedidosPosterioresFecha(Date fecha) {
		return streamPedido()
				.filter(p->p.getFecha().after(fecha))
				.collect(Collectors.toList());
	}

	// lista de nombres de sección, no repetidas
	public List<String> secciones() {
		return streamPedido()
				//.map(p->p.getSeccion())
				.map(Pedido::getSeccion) // misma expresión que la anterior pero expresada como referencia a metodo.
				.distinct()
				.collect(Collectors.toList());

	}
	
	public List<Pedido> pedidosRangoFecha(LocalDate fecha, Period periodo){
		LocalDate fechaFin =fecha.plus(periodo); //A la fecha inicial le suma el periodo para hacer la fecha final	
		return streamPedido()
				.filter(p->p.getFecha().after(Utilidades.convertLocalDatetoDate(fecha)) &&
						p.getFecha().before(Utilidades.convertLocalDatetoDate(fechaFin)))
				.collect(Collectors.toList());
	}

}
