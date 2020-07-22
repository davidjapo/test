package principal;

import java.util.stream.Stream;

public class Test9 {

	
	//De los cursos disponibles obtener el curso que contenga java, previa ordenación alfabéticamente, sino lo encuentra que lo informe al usuario.
	public static void main(String[] args) {
		Stream<String> cursos=Stream.of("Principios de Java","JavaScript","PHP",".NET","c++","Aprenda Java ya","Python","Java EE","Todo Java","Linux"); 
		System.out.println(cursos
				.map(s->s.toLowerCase())
				.filter(c->c.contains("java")) //Filtra la lista
				.sorted() //Ordena de forma natural
				.findFirst() //Escoge el primero de la lista que cumpla el filtro
				.orElse("Curso no encontrado")); //devuelve un mensaje si no se encuentra
	}

}
