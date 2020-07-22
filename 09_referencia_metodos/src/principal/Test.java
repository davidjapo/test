package principal;

import java.util.stream.Stream;

public class Test {

	public static void main(String[] args) {
		Stream<String> nombres = Stream.of("Pepe","Ana","Lucas","Ana","Marta","Pepe","David","Gema");
		nombres
			.distinct()
			.map(String::length) // en lugar de usar lambdas, se usa una referencia a método.
			.forEach(System.out::println);
	}

}
