package service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Curso;

public class CursosService {

	public List<Curso> recuperarCursos() {
		try (Connection con = Datos.getConnection()) {
			List<Curso> cursos = new ArrayList<>();
			String sql = "SELECT * FROM cursos";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				cursos.add(new Curso(rs.getInt("idCurso"), rs.getString("denominacion"), rs.getInt("duracion"),
						rs.getDate("fechaInicio")));
			}
			return cursos;
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
			return null;
		}
	}

	public boolean altaCurso(Curso curso) {
		try (Connection con = Datos.getConnection()) {

			String sql = "insert into cursos(denominacion,duracion,fechaInicio) values (?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, curso.getDenominacion());
			st.setInt(2, curso.getDuracion());
			st.setDate(3, new java.sql.Date(curso.getFechaInicio().getTime())); // Construye un Date de SQL.
			st.execute();
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	// Lista de cursos que comienzan entre 2 fechas dadas.
	public List<Curso> cursosEntreFechas(Date fecha1, Date fecha2) {
		try (Connection con = Datos.getConnection()) {
			List<Curso> cursos = new ArrayList<>();
			String sql = "SELECT * FROM escuela WHERE fechaInicio BETWEEN + ? ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setDate(1, fecha1);
			st.setDate(2, fecha2);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				cursos.add(new Curso(rs.getInt("idCurso"), rs.getString("denominacion"), rs.getInt("duracion"),
						rs.getDate("fechaInicio")));
			}
			return cursos;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public int totalCursos() {
		try (Connection con = Datos.getConnection()) {
			CallableStatement cs = con.prepareCall("{call totalCursos(?)}");
			// Se registran los parámetros de salida:
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			return cs.getInt(1);
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 1;
		}

	}

}
