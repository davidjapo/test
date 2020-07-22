package principal;

import java.util.stream.Stream;

public class Test6 {

	public static void main(String[] args) {
		Stream<String> nombres = Stream.of("Pepe","Ana","Lucas","Ana","Marta","Pepe","David","Gema");
		/*nombres
			.distinct()
			.map(s->s.length())
			.forEach(n->System.out.println(n));*/
		
		System.out.println(nombres
				.distinct()
				.mapToInt(s->s.length()) //devuelve un IntStream
				.sum()); //calcula la suma a partir del IntStream.
	}

}
