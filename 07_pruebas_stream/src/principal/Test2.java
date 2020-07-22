package principal;

import java.util.stream.Stream;

public class Test2 {

	public static void main(String[] args) {
		Stream<Integer> numeros = Stream.of(7,21,14,31,75,84,14,7);
		System.out.println("Total de numeros: "+
		numeros
			.distinct() //elimina los elementos duplicados.
			.limit(2)
			.count()); //cuenta la cantidad de elementos.
	}

}
