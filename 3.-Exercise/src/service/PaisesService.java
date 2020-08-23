package service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import com.google.gson.Gson;

import model.Pais;

/*
   Dado el documento JSON con los datos de países, realizar una capa de lógica de
   negocio que permita obtener los siguientes datos:
   
    • Lista de países pertenecientes a la región indicada como parámetro
    • País más poblado
    • Total de países cuyo número de habitantes supere el valor recibido como parámetro
    • Paises cuyos nombres contengan la combinación de letras recibida como parámetro
    • Posición (longitud y latitud) del país cuyo alfa2code se recibe como parámetro
    • Población media de la región que se recibe como parámetro
    • Tabla con las regiones y la población total de cada una
    
Nota: Un país contiene la siguiente información: Nombre, capital, área y población
*/

public class PaisesService {
	
	private String path;
	private Path pt;
	private Stream<Pais> listado;

	public PaisesService(){
		path = "all.json";
		pt = Paths.get(path);
	}
	
	//From object JSON or objects collections JSON at the file .json to Array.
	private Pais[] jsonToArray() {
		if(Files.exists(pt)){
			Gson gson = new Gson();
			Reader lectura = null;
			try {
				lectura = Files.newBufferedReader(pt);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return gson.fromJson(lectura, Pais[].class);
		}
		return null;
	}
	
	
	//Lista de países pertenecientes a la región indicada como parámetro
	public List<String> listadoPaisesPorRegion(String region){
		listado = Arrays.stream(jsonToArray());
		return listado.filter(p -> p.getRegion().equalsIgnoreCase(region))
			.map(p -> p.getName())
			.collect(Collectors.toList());
	}
	
	
	//País más poblado:
	public Optional<String> paisMasPoblado() {
		listado = Arrays.stream(jsonToArray());
		double poblacion = listado.mapToDouble(p -> p.getPopulation()) 
				.max().getAsDouble();
		listado = Arrays.stream(jsonToArray());
		return listado.filter(p -> p.getPopulation() == poblacion)
		.map(p -> p.getName())
		.findAny();
	}
	
	
	//Primera forma de construir el método para: País más poblado:
	/*public String paisMasPoblado(){
		listado = Arrays.stream(jsonToArray());
		StringBuilder poblacion = new StringBuilder("0");
		StringBuilder nombrePais = new StringBuilder(".");
		listado.forEach(p -> {
			if(p.getPopulation().compareTo(Long.parseLong(poblacion.toString())) > 0) {
				poblacion.replace(0, poblacion.length(), p.getPopulation().toString());
				nombrePais.replace(0, nombrePais.length(), p.getName());
			}
		});
		return nombrePais.toString();
	}*/
	
	
	//Total de países cuyo número de habitantes supere el valor recibido como parámetro:
	public long numPaisesHab(int poblacion) {
		listado = Arrays.stream(jsonToArray());
		return listado.filter(p -> p.getPopulation()>(long)poblacion)
		.count();
	}
	
	//Paises cuyos nombres contengan la combinación de letras recibida como parámetro:
	public List<Pais> nombrePaisesLetras(String combinacion) {
		listado = Arrays.stream(jsonToArray());
		return listado.filter(p -> p.getName().contains(combinacion))
		.collect(Collectors.toList());

	}
	
	
	//Paises cuyos nombres contengan la combinación de letras recibida como parámetro:
	/*public void nombrePaisesLetras(String combinacion) {
		listado = Arrays.stream(jsonToArray());
		listado.filter(p -> p.getName().contains(combinacion))
		.forEach(System.out::println);
	}*/
	
	
	//Posición (longitud y latitud) del país cuyo alfa2code se recibe como parámetro:
	public Optional<Double[]> posicionPais(String alfa2code) {
		listado = Arrays.stream(jsonToArray());
		return listado.filter(p -> p.getAlpha2Code().equalsIgnoreCase(alfa2code))
		.map(p -> p.getLatlng())
		.findAny();
	}
	
	//Población media de la región que se recibe como parámetro:
	public OptionalDouble poblacionMediaRegion(String region) {
		listado = Arrays.stream(jsonToArray());
		return listado.filter(p -> p.getRegion().equalsIgnoreCase(region))
				.mapToLong(p -> p.getPopulation())
				.average();
	}
	
	//Tabla con las regiones y la población total de cada una:
	public Map<String,Long> tablaRegionPoblacion(){
		listado = Arrays.stream(jsonToArray());
		return listado.filter(p -> !p.getRegion().equals(""))
				.collect(Collectors.groupingBy(Pais::getRegion,Collectors.summingLong(Pais::getPopulation)));
	}
	
	
	//Número de elementos del stream:
	public long numElementStream() {
		listado = Arrays.stream(jsonToArray());
		return listado.count();
	}
	
}
