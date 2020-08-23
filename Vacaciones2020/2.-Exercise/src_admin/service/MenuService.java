package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import BBDD.ConexionBBDD;
import model.Cliente;
import model.Cuenta;
import model.Titular;
import util.Utilidades;


public class MenuService {

	public boolean addCuenta(Cuenta newCuenta) {
		try(Connection con = ConexionBBDD.obtenerConexion()){
			//Se comprueba numeroCuenta por si está duplicado, antes de insertarlos en la BBDD:
			String sql = "INSERT INTO cuentas (numeroCuenta,saldo,tipoCuenta) SELECT DISTINCT ?,?,? FROM DUAL WHERE NOT EXISTS(SELECT * FROM cuentas WHERE numeroCuenta=?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, newCuenta.getNumeroCuenta());
			ps.setDouble(2, newCuenta.getSaldo());
			ps.setString(3, newCuenta.getTipocuenta());
			ps.setInt(4, newCuenta.getNumeroCuenta());
			int resultado = ps.executeUpdate();
			if(resultado == 1) {
				registrarMovimiento(newCuenta.getNumeroCuenta(), LocalDateTime.now(), Utilidades.formatoFecha, newCuenta.getSaldo(), "Ingreso inicial");
				return true;
			}
			return false;			 
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public int addCliente(Cliente newCliente) {
		try(Connection con = ConexionBBDD.obtenerConexion()){
			String sql = "INSERT INTO clientes (dni,nombre,direccion,telefono) SELECT DISTINCT ?,?,?,? FROM DUAL WHERE NOT EXISTS(SELECT * FROM clientes WHERE dni=?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, newCliente.getDni());
			ps.setString(2, newCliente.getNombre());
			ps.setString(3, newCliente.getDireccion());
			ps.setInt(4, newCliente.getTelefono());
			ps.setInt(5, newCliente.getDni());
			return ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int addTitutar(Titular newTitular) {
		try(Connection con = ConexionBBDD.obtenerConexion()){
			String sql = "INSERT INTO titulares (idCuenta,idCliente) SELECT DISTINCT ?,? FROM DUAL WHERE NOT EXISTS(SELECT * FROM titulares WHERE idCuenta=? AND idCliente=?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, newTitular.getIdCuenta());
			ps.setInt(2, newTitular.getIdCliente());
			ps.setInt(3, newTitular.getIdCuenta());
			ps.setInt(4, newTitular.getIdCliente());
			return ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	private void registrarMovimiento(int idCuenta, LocalDateTime fecha, DateTimeFormatter formatoFecha,
			double cantidad, String operacion) {
		try (Connection con = ConexionBBDD.obtenerConexion()) {
			String sql = "INSERT INTO movimientos (idCuenta,fecha,cantidad,operacion) VALUES (?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idCuenta);
			ps.setString(2, fecha.format(formatoFecha));
			ps.setDouble(3, cantidad);
			ps.setString(4, operacion);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
}
