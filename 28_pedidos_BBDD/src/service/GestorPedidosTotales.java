package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.PedidoTienda;

public class GestorPedidosTotales {

	public void grabarPedidos(List<PedidoTienda> pedidos) {
		// Grabar en BBDD
		try (Connection con = Datos.getConnection()) {
			String sql = "INSERT INTO pedidos (tienda,producto,unidades,precioUnitario,fecha,seccion) VALUES (?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			pedidos.forEach(p -> {
				try {
					ps.setString(1, p.getTienda());
					ps.setString(2, p.getProducto());
					ps.setInt(3, p.getUnidades());
					ps.setDouble(4, p.getPrecioUnitario());
					ps.setDate(5, new java.sql.Date(p.getFecha().getTime()));
					ps.setString(6, p.getSeccion());
					ps.execute();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}

	}
}
