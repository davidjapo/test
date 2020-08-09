package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Alumno;

public class AlumnosService {

	// Lista de alumnos matriculados en un curso a partir de su denominación.
	public List<Alumno> alumnosCurso(String denominacion){
		List<Alumno> alumnos = new ArrayList<>();
		try(Connection con = Datos.getConnection()){
			String sql = "SELECT alumnos.* FROM alumnos,cursos WHERE cursos.denominacion=? and alumnos.idcurso=cursos.idCurso";
					PreparedStatement st = con.prepareStatement(sql);
					st.setString(1, denominacion);
					ResultSet rs = st.executeQuery();
					while(rs.next()) {
						alumnos.add(new Alumno(rs.getInt("dni"),
								rs.getString("nombre"),
								rs.getString("email"),
								rs.getInt("edad")));
					}
			}
			catch(SQLException ex) {
				ex.printStackTrace();
				return null;
	}
	}
}
