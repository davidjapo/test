package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.Persona;

public class PersonasService {
	List<Persona> personas=new ArrayList<>();
	public PersonasService() {
		personas.add(new Persona("Alex","al@gg.com",20));				
		personas.add(new Persona("Juan","alfd@gg.com",29));
		personas.add(new Persona("Elena","al@gg.es",17));
		personas.add(new Persona("Marta","alaaoi@gg.com",34));
		personas.add(new Persona("Lucas","alert@gg.es",44));
		personas.add(new Persona("Alicia","sdfl@gg.com",35));
	}
	//persona más joven
	public Persona obtenerMasJoven() {
		return personas
		.stream()
		.min((p1,p2)->p1.getEdad()-p2.getEdad())
		.orElse(null);
		
	}
	//el número de personas cuya edad es superior a la media
	public int superiorMedia() {
		/*
		 * double media = personas .stream() .mapToDouble(p->p.getEdad()) .average()
		 * .orElse(0); return (int) personas .stream() .filter(p->p.getEdad()>media)
		 * .count();
		 */
		
		double media =
				personas.stream()
				.collect(Collectors.averagingDouble(p->p.getEdad()));
		
		return (int)personas
				.stream()
				.filter(p->p.getEdad()>media)
				.count();
				
	}
	//personas ordenadas por edad
	public List<Persona> ordenadasPorEdad(){
		return personas
				.stream()
				.sorted((p1,p2)->p1.getEdad()-p2.getEdad())
				.collect(Collectors.toList());
	}
	//lista nombres de personas
	public List<String> nombres(){
		return personas
				.stream()
				.map(p->p.getNombre())
				.collect(Collectors.toList());
	}
	//dominio sea igual al indicado
	public List<Persona> personasDominio(String dominio){
		return personas
				.stream()
				.filter(p->p.getEmail().endsWith(dominio))
				.collect(Collectors.toList());
	}
	
	
	
	
}