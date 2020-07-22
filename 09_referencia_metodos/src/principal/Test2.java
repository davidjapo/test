package principal;

import java.util.stream.Stream;

public class Test2 {

	public static void main(String[] args) {
		Stream<String> cursos=Stream.of("Principios de Java","JavaScript","PHP",".NET","c++","Aprenda Java ya","Python","Java EE","Todo Java","Linux"); 
		System.out.println(cursos
				.map(String::toLowerCase) // se aplica referencia a método en lugar de una expresión lambda.
				.filter(c->c.contains("java")) //No se puede usar una referencia a un método porque no puede deducir sobre que objeto aplicarlo debido a que está invocando al método.
				.sorted() 
				.findFirst() 
				.orElse("Curso no encontrado")); 
	}

}
