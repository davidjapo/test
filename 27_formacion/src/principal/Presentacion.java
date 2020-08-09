package principal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.Curso;
import service.CursosService;

public class Presentacion {

	public static void main(String[] args) {
		CursosService service = new CursosService();
		/*
		Curso curso = null;
		Scanner leer = new Scanner(System.in);
		
		System.out.print("Introduzca el nombre del curso: ");
		String nombreCurso = leer.nextLine();
		System.out.print("Introduzca la duración del curso: ");
		int duracionCurso = Integer.parseInt(leer.nextLine());
		SimpleDateFormat personalizado = new SimpleDateFormat("dd/MM/yyyy");
		System.out.print("Introduzca la fecha de inicio del curso (dd/MM/yyyy): ");
		String fecha = leer.nextLine();
		Date fechaInicio =null;
		try {
			fechaInicio = personalizado.parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		*/
		
		/*
		//Lista de cursos entre dos fechas dadas.
		curso = new Curso(nombreCurso,duracionCurso,fechaInicio);
		service.altaCurso(curso);
		SimpleDateFormat personalizadoDos = new SimpleDateFormat("dd/MM/yyyy");
		System.out.print("Introduce la fecha inicial: ");
		String fecha1 = leer.nextLine();
		System.out.print("Introduce la fecha final: ");
		String fecha2 = leer.nextLine();
		Date fechaInicial =null, fechaFinal= null;
		try {
			fechaInicial = personalizadoDos.parse(fecha1);
			fechaFinal = personalizadoDos.parse(fecha2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		service.cursosEntreFechas(fechaInicial, fechaFinal);
		*/	
		
		
		System.out.println("Total de cursos: "+service.totalCursos());
	}

}
