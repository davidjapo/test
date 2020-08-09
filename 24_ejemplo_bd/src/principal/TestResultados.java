package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestResultados {
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
	
public static void main(String[] args) {
	try (Connection con = DriverManager.getConnection(cadenaConexion, user, password)) {
		// Envío de instrucciones SQL:
		String sql="SELECT nombre FROM contactos;";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql); //Devuelve un ResultSet que permite recorrer las filas que cumplen la condición.
		while(rs.next()) {
			System.out.println(rs.getString("nombre"));
		}
	} 
	catch (SQLException e) {
		e.printStackTrace();
	}
	
}
}
