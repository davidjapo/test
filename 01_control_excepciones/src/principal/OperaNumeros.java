package principal;


import java.util.Scanner;

public class OperaNumeros {

	public static void main(String[] args) {
		int numerador,denominador,resultado;
		Scanner leer = new Scanner(System.in);
		try {
		System.out.print("Introduce el numerador: ");
		numerador=Integer.parseInt(leer.nextLine());
		System.out.print("Introduce el denominador: ");
		denominador=Integer.parseInt(leer.nextLine());
		resultado=numerador/denominador;
		System.out.println("La división es: "+resultado);
		}
		catch(NumberFormatException ex) {
			System.out.println("\nNúmero incorrecto, try again. Motivo: "+ex.getMessage());
		}
		/*
		 * catch(ArithmeticException ex) {
		 * System.out.println("\nNo se puede dividir entre 0. Motivo: "+ex.getMessage())
		 * ; }
		 */	
		
		//esto es un comentario de una linea
	
		catch(Exception ex) {
			System.out.println("\nError inesperado del programa:\nPor favor,"
					+ " contate con el programador.\n"
					+ "David De la Cruz - E-mail: ddd@yahoo.com");
			System.out.println("\nError: "+ex.getMessage());
			ex.printStackTrace();
		}
		finally {
			System.out.println("\n--FIN del programa--");
			leer.close();
		}
		
	}

}
