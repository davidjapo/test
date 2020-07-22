package principal;

import java.util.stream.Stream;

public class Test3 {

	public static void main(String[] args) {
		Stream<Integer> numeros = Stream.of(7,7,14,31,75,84,14,7);
		numeros
			.distinct()
			.limit(2)
			.forEach(n-> System.out.println(n));

	}

}
