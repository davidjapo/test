package principal;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Scanner;

import model.Pais;
import service.PaisesService;

public class Presentacion {

	public static void main(String[] args) {
		Scanner leer = new Scanner(System.in);
		PaisesService service = new PaisesService();
		System.out.println("Número de paises: "+service.numElementStream());
		System.out.print("\n1.- Indique una región: ");
		String region = leer.nextLine();
		System.out.println();		
		List<String> paisesRegion = service.listadoPaisesPorRegion(region);
		if(!paisesRegion.isEmpty()) {
			paisesRegion.forEach(System.out::println);
		}else {
			System.out.println("La región introducida no existe...");
		}
		System.out.println();
		System.out.println("\n2.- País con más población: "+service.paisMasPoblado().get());
		System.out.print("\n3.- Introduzca una cantidad de habitantes: ");
		long numPais;
		if((numPais = service.numPaisesHab(Integer.parseInt(leer.nextLine())))>1) {
			System.out.println(numPais+" países tienen más habitantes que la cantidad introducida.");
		}
		else {
			System.out.println(numPais+" país tiene más habitantes que la cantidad introducida.");
		}
		System.out.print("\n4.- Introduzca una combinación de letras: ");
		List<Pais> paises = service.nombrePaisesLetras(leer.nextLine());
		if(!paises.isEmpty()) {
			paises.forEach(System.out::println);
		}else {
			System.out.println("No existen países con esa combinación de letras...");
		}
		System.out.print("\n5.- Introduzca el código alfa2code para mostrar la posición del país: ");
		Optional<Double[]> resultado = service.posicionPais(leer.nextLine());
		if(resultado.isPresent()) {
			Double[] posicion = resultado.get();
			System.out.println("Latitud: "+posicion[0]);
			System.out.println("Longitud: "+posicion[1]);
		}else {
			System.out.println("No existe ningún país con el código alfa2code facilitado...");
		}
		System.out.print("\n6.- Introduzca una región para mostrar la media de su población: ");
		region = leer.nextLine();
		OptionalDouble media = service.poblacionMediaRegion(region);
		if(media.isPresent()) {
			System.out.println("Media de la población en la región "+region+": "+Math.round(media.getAsDouble()));
		}else {
			System.out.println("La región facilitada no existe...");
		}
		System.out.println("\n7.- Tabla región vs población total:");
		System.out.println(service.tablaRegionPoblacion().toString());
	}

}
