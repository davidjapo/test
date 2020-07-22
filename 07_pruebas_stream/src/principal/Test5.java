package principal;

import java.util.stream.Stream;

public class Test5 {

	public static void main(String[] args) {
		Stream<Integer> numeros = Stream.of(7,7,84,31,75,84,14,7);
		numeros
			.distinct()
			.filter(n->n%2==0)
			.sorted((n1,n2)->n2-n1) //ordenado de mayor a menor.	
			.forEach(n->System.out.println(n));

	}

}
