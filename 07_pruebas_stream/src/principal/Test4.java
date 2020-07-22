package principal;

import java.util.stream.Stream;

public class Test4 {

	public static void main(String[] args) {
		Stream<String> nombres = Stream.of("Pepe","Ana","Lucas","Ana","Marta","Pepe","David","Gema");
		nombres
			.distinct()
			.limit(5)
			.forEach(n->System.out.println(n));
	}

}
