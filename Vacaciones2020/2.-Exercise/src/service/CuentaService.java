package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import BBDD.ConexionBBDD;
import util.Utilidades;

public class CuentaService {
	
	

	public boolean validarCuenta(int num) {
		try (Connection con = ConexionBBDD.obtenerConexion()) {
			String sql = "SELECT numeroCuenta FROM cuentas WHERE numeroCuenta=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean ingresarDinero(int numCuenta, double cantidad) {
		try (Connection con = ConexionBBDD.obtenerConexion()) {
			String sql = "UPDATE cuentas SET saldo = (saldo+?) WHERE numeroCuenta=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, cantidad);
			ps.setInt(2, numCuenta);
			int ifIngresoOK = ps.executeUpdate();
			if (ifIngresoOK == 1) {
				registrarMovimiento(numCuenta, LocalDateTime.now(), Utilidades.formatoFecha, cantidad, "Ingreso");
				return true;
			}
			return false;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean extraerDinero(int numCuenta, double cantidad) {
		try (Connection con = ConexionBBDD.obtenerConexion()) {
			String sql = "UPDATE cuentas SET saldo = (saldo-?) WHERE numeroCuenta=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, cantidad);
			ps.setInt(2, numCuenta);
			int ifExtraccionOK = ps.executeUpdate();
			if (ifExtraccionOK == 1) {
				registrarMovimiento(numCuenta, LocalDateTime.now(), Utilidades.formatoFecha, cantidad, "Extracción");
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean registrarMovimiento(int idCuenta, LocalDateTime fecha, DateTimeFormatter formatoFecha,
			double cantidad, String operacion) {
		try (Connection con = ConexionBBDD.obtenerConexion()) {
			String sql = "INSERT INTO movimientos (idCuenta,fecha,cantidad,operacion) VALUES (?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idCuenta);
			ps.setString(2, fecha.format(formatoFecha));
			ps.setDouble(3, cantidad);
			ps.setString(4, operacion);
			int ifMovimientoOK = ps.executeUpdate();
			if (ifMovimientoOK == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public void getMovimientos(int numCuenta) {
		try(Connection con = ConexionBBDD.obtenerConexion()){
			String sql = "SELECT fecha,operacion,cantidad FROM movimientos WHERE fecha > (SELECT SUBDATE(NOW(), INTERVAL 30 DAY)) AND idCuenta=? ORDER BY idMovimiento DESC";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, numCuenta);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				System.out.println(" "+rs.getString("fecha")+"   "+rs.getString(2)+"        "+rs.getString("cantidad"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public double getSaldoCuenta(int numCuenta) {
		try(Connection con = ConexionBBDD.obtenerConexion()){
			String sql = "SELECT saldo FROM cuentas WHERE numeroCuenta=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, numCuenta);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getDouble(1);
			}
			return 0.0;
		}catch(SQLException e) {
			e.printStackTrace();
			return 0.0;
		}
	}

	public boolean transferencia(int numCuenta, int cuentaDestino, double cantidad) {
		try(Connection con = ConexionBBDD.obtenerConexion()){
			//Sentencia SQL para restar la cantidad de la cuenta de origen:
			String sql = "UPDATE cuentas SET saldo = (saldo-?) WHERE numeroCuenta=?;";
			//Sentencia SQL para sumar la cantidad a la cuenta de destino:
			String sqlBis = "UPDATE cuentas SET saldo = (saldo+?) WHERE numeroCuenta=?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1,cantidad);
			ps.setInt(2, numCuenta);
			PreparedStatement psB = con.prepareStatement(sqlBis);
			psB.setDouble(1,cantidad);
			psB.setInt(2, cuentaDestino);
			int ifOrigenOK = ps.executeUpdate();
			if(ifOrigenOK==1) {
				registrarMovimiento(numCuenta, LocalDateTime.now(), Utilidades.formatoFecha, cantidad, "Transf. sal.");
				int ifDestinoOK = psB.executeUpdate();
				if(ifDestinoOK ==1) {
					registrarMovimiento(cuentaDestino, LocalDateTime.now(), Utilidades.formatoFecha, cantidad, "Transf. ent.");
					return true;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
