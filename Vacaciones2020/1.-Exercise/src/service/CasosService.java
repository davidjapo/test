package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import com.google.gson.Gson;

import BBDD.ConexionBBDD;
import model.Casos;

public class CasosService {
	private String path;
	private Path pt;
	private int contador;
	
	//Se comprueban los casos duplicados implementando la interfaz BiFunction en un objeto:
	BiFunction<Stream<Casos>,Casos,Boolean> biFunction = (l,caso) -> {
		Optional<Casos> op = l.filter(c -> c.getCcaa_iso().equalsIgnoreCase(caso.getCcaa_iso()) && c.getFecha().equals(caso.getFecha())).findFirst();
		return op.isPresent();
	};

	public CasosService(String ruta) {
		super();
		this.path = ruta;
		pt = Paths.get(path);
	}

	/*public Stream<Casos> csvToList() {
		if (ifExistFile()) {
			final String SEPARADOR = ",";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<Casos> casos = new ArrayList<>();
			String linea = null;
			try (BufferedReader bf = new BufferedReader(new FileReader(path));) {
				while ((linea = bf.readLine()) != null) {
					String[] lineas = linea.split(SEPARADOR);
					Casos nuevoCaso = new Casos(lineas[0], sdf.parse(lineas[1]), Integer.parseInt(lineas[2]),
							Integer.parseInt(lineas[3]), Integer.parseInt(lineas[4]), Integer.parseInt(lineas[5]),
							Integer.parseInt(lineas[6]));
					//Se comprueban los casos duplicados antes de insertarlos en la lista:
					if(!biFunction.apply(casos.stream(), nuevoCaso))
					casos.add(nuevoCaso);
				}
				return casos.stream();
			} catch (IOException | NumberFormatException | ParseException e1) {
				e1.printStackTrace();
				return null;
			}
		}
		return null;
	}*/
	
	//Igual que el método anterior pero con un Stream y sin implementar el control de duplicados:
	public Stream<Casos> csvToList() {
		if (ifExistFile()) {
			final String SEPARADOR = ",";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Stream<String> stream = Files.lines(Paths.get(path));
				return stream.map(linea -> linea.split(SEPARADOR))
				.map(Casos::buildFromArray);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return null;
	}

	public boolean csvToDDBB(Stream<Casos> casos) {
		if (casos != null) {
			try (Connection con = ConexionBBDD.obtenerConexion();) {
				// Sintaxis SQL para insertar cumpliendo condición, primero consulta que no exista y después inserta:
				String sql = "INSERT INTO casosCsv (ccaa_iso,fecha,num_casos,num_casos_prueba_pcr,num_casos_prueba_test_ac,num_casos_prueba_otras,num_casos_prueba_desconocida) SELECT DISTINCT ?,?,?,?,?,?,? FROM DUAL WHERE NOT EXISTS(SELECT * FROM casosCsv WHERE ccaa_iso=? AND fecha=?)";
				PreparedStatement ps = con.prepareStatement(sql);
				casos.forEach(c -> {
					try {
						ps.setString(1, c.getCcaa_iso());
						ps.setDate(2, new java.sql.Date(c.getFecha().getTime()));
						ps.setInt(3, c.getNum_casos());
						ps.setInt(4, c.getNum_casos_prueba_pcr());
						ps.setInt(5, c.getNum_casos_prueba_test_ac());
						ps.setInt(6, c.getNum_casos_prueba_otras());
						ps.setInt(7, c.getNum_casos_prueba_desconocida());
						ps.setString(8, c.getCcaa_iso());
						ps.setDate(9, new java.sql.Date(c.getFecha().getTime()));
						//Se ejecuta la sentencia sql y se guarda el resultado de si la tupla ha sido añadida:
						int ifAdd = ps.executeUpdate();
						if(ifAdd==1) {
							contador++;
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				});
				return true;
			} catch (SQLException ex) {
				ex.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public Casos[] jsonToArray() {
		if (ifExistFile()) {
			Gson gson = new Gson();
			Reader lectura = null;
			try {
				lectura = Files.newBufferedReader(Paths.get(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return gson.fromJson(lectura, Casos[].class);
		}
		return null;
	}

	public boolean jsonToDDBB(Casos[] casos) {
		if (casos != null) {
			try (Connection con = ConexionBBDD.obtenerConexion();) {
				String sql = "INSERT INTO casosJson (ccaa_iso,fecha,num_casos,num_casos_prueba_pcr,num_casos_prueba_test_ac,num_casos_prueba_otras,num_casos_prueba_desconocida) SELECT DISTINCT ?,?,?,?,?,?,? FROM DUAL WHERE NOT EXISTS(SELECT * FROM casosJson WHERE ccaa_iso=? AND fecha=?)";
				PreparedStatement ps = con.prepareStatement(sql);
				for (Casos c : casos) {
					ps.setString(1, c.getCcaa_iso());
					ps.setDate(2, new java.sql.Date(c.getFecha().getTime()));
					ps.setInt(3, c.getNum_casos());
					ps.setInt(4, c.getNum_casos_prueba_pcr());
					ps.setInt(5, c.getNum_casos_prueba_test_ac());
					ps.setInt(6, c.getNum_casos_prueba_otras());
					ps.setInt(7, c.getNum_casos_prueba_desconocida());
					ps.setString(8, c.getCcaa_iso());
					ps.setDate(9, new java.sql.Date(c.getFecha().getTime()));
					//Se ejecuta la sentencia sql y se guarda el resultado de si la tupla ha sido añadida:
					int ifAdd = ps.executeUpdate();
					if(ifAdd==1) {
						contador++;
					}
				}
				return true;
			} catch (SQLException ex) {
				ex.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public boolean ifExistFile() {
		if (Files.exists(pt)) {
			return true;
		}
		return false;
	}

	//Obtiene el conteo de tuplas añadidas en la BBDD:
	public int getContador() {
		return contador;		
	}
	

}
