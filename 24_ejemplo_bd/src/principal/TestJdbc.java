package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJdbc {

	static String driver ="com.mysql.cj.jdbc.Driver";
	static String cadenaConexion = "jdbc:mysql://localhost:3306/miscontactos?serverTimezone=UTC";
	static String user ="root";
	static String password ="@r00t2020";
	
	static {
		//Conexión con SGBD:
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //Para crear un objeto a partir de su nombre, en lugar de new

	}
	
	public static void main(String[] args) {	
		//Conexión con el SGBD:
		try(Connection con=DriverManager.getConnection(cadenaConexion,user,password)) {
			//Envío de instrucciones SQL:
			String sql="insert into contactos(nombre,email,edad) values ('Daniel','danielin@dd.es',11)";
			Statement st = con.createStatement();
			st.execute(sql);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
