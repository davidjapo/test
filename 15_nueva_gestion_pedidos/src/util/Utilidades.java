package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.Pedido;

public class Utilidades {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static final String SEPARADOR = ",";

	public static Pedido construirPedido(String cadena) {
		String[] array = cadena.split(SEPARADOR);
		try {
			return new Pedido(array[0],Integer.parseInt(array[1]),Double.parseDouble(array[2]),array[3],sdf.parse(array[4]));
		}
		catch(ParseException pe) {
			pe.getMessage();
			pe.printStackTrace();
			return null;
		}
	}
	
	public static String construirCadena(Pedido pedido, String SEPARADOR) {
		return pedido.getProducto()+SEPARADOR+pedido.getUnidades()+SEPARADOR+pedido.getPrecioUnitario()+SEPARADOR+pedido.getSeccion()+SEPARADOR+sdf.format(pedido.getFecha());
	}
}
