package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import model.Contacto;

public class ContactoService {

	static String driver = "com.mysql.cj.jdbc.Driver";
	static String cadenaConexion = "jdbc:mysql://localhost:3306/miscontactos?serverTimezone=UTC";
	static String user = "root";
	static String password = "@r00t2020";

	static {
		// Conexión con SGBD:
		try {
			Class.forName(driver); // Para crear un objeto a partir de su nombre, en lugar de new
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	private Connection obtenerconexion() throws SQLException {
		return DriverManager.getConnection(cadenaConexion, user, password);
	}

	public boolean addContacto(Contacto contacto) {
		// Conexión con el SGBD:
		try (Connection con =obtenerconexion()) {
			// Envío de instrucciones SQL:

			// Opcion Statement:
			/*
			 * String sql =
			 * "insert into contactos (nombre,email,edad) values ('"+contacto.getNombre()+
			 * "','"+contacto.getEmail()+"',"+contacto.getEdad()+")"; Statement st =
			 * con.createStatement(); st.execute(sql);
			 */

			// Opción PreparedStatement
			String sql = "insert into contactos (nombre,email,edad) values (?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, contacto.getNombre());
			st.setString(2, contacto.getEmail());
			st.setInt(3, contacto.getEdad());
			st.execute();
			return true;
		}
		catch(SQLIntegrityConstraintViolationException sqlex) {
			System.out.println("El email ya existe.");
			return false;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean delContact(String email) {
		try (Connection con = DriverManager.getConnection(cadenaConexion, user, password)) {
			String sql = "DELETE FROM contactos WHERE email=?";
			PreparedStatement st = con.prepareStatement(sql);	
			st.setString(1, email);
			return st.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Contacto searchContact(String email) {
		try (Connection con = DriverManager.getConnection(cadenaConexion, user, password)) {
			String sql = "SELECT * FROM contactos WHERE email=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,email);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				return new Contacto(rs.getString("nombre"),rs.getString("email"),rs.getInt("edad"));
			}else {
				return null;
			}
		}		
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	/*
	public List<Contacto> viewContact(Contacto con) {

	}*/

}
