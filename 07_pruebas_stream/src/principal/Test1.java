package principal;

import java.util.stream.Stream;

public class Test1 {

	public static void main(String[] args) {
		Stream<Integer> numeros = Stream.of(7,21,14,31,75,84,14);
		//System.out.println("Total números: "+numeros.count());
		numeros.forEach(n->System.out.println(n));
	}

}
