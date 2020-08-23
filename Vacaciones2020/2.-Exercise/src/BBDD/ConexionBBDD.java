package BBDD;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class ConexionBBDD {
	private static String driver;
	private static String cadenaConexion;
	private static String user;
	private static String password;
	private static final String FILE = "src/BBDD/config.json";
	
	//carga del driver:
	static {
		try {
			cargarPropiedades();
			Class.forName(driver);
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection obtenerConexion() throws SQLException {
		return DriverManager.getConnection(cadenaConexion,user,password);
	}
	
	//Obtiene los datos del fichero .json y los carga en las variables:
	private static void cargarPropiedades() throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		JsonObject conn = JsonParser.parseReader(new FileReader(FILE)).getAsJsonObject();
		driver = conn.get("driver").getAsString();
		cadenaConexion = conn.get("cadenaConexion").getAsString();
		user = conn.get("user").getAsString();
		password = conn.get("password").getAsString();
	}
	
}
