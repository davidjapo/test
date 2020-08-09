package service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.Caso;

public abstract class BaseService {
	public abstract Stream<Caso> streamCaso();
	
	// 1.- Lista de casos producidos entre 2 fechas.
	public List<Caso> casosEntreFechas(Date fechaInicial, Date fechaFin){
		return streamCaso()
				.filter(c->(c.getFecha().getTime()>=fechaInicial.getTime()) && 
							(c.getFecha().getTime()<=fechaFin.getTime()))
				.collect(Collectors.toList());
	}
	
	// 2.- Total de positivos producidos en un día:
	public int casosEnDia(Date fecha) {
		return streamCaso()
		 .filter(c->c.getFecha().compareTo(fecha)==0)
		 //.collect(Collectors.summingInt(c->c.getPositivos()));
		 .collect(Collectors.summingInt(Caso::getPositivos));
	}
	
	// 3.- Fecha en la que se produjo el pico de contagios.:
	public Date picoContagios() {
		Map<Date,List<Caso>> grupoPorFecha = streamCaso()
				.collect(Collectors.groupingBy(Caso::getFecha));
		return grupoPorFecha
			.keySet().stream()
			/*.max((d1,d2)->grupoPorFecha.get(d1).stream().mapToInt(Caso::getPositivos).sum()-
				grupoPorFecha.get(d2).stream().mapToInt(Caso::getPositivos).sum()
			.get();*/
			.max((d1,d2)->casosEnDia(d1)-casosEnDia(d2))
			.get();
		
	}
	
	// 4.- Media de positivos diarios:
	public int mediaPositivos() {
		Map<Date,List<Caso>> grupoPorFecha = streamCaso()
				.collect(Collectors.groupingBy(Caso::getFecha));
		return grupoPorFecha.values().stream()
			.collect(Collectors.averagingInt(l->l.stream().mapToInt(Caso::getPositivos).sum()))
			.intValue();
	}
	
	// 5.- Total de positivos de una comunidad:
	public int totalPositivosComunidad(String nombre) {
		return streamCaso()
				.filter(c->c.getComunidad().equals(nombre))
				.mapToInt(Caso::getPositivos)
				.sum();
	}
	
	// 6.- Lista de casos agrupados por comunidad:
	public Map<String,List<Caso>> casosPorComunidad(){
		return streamCaso()
				.collect(Collectors.groupingBy(Caso::getComunidad));
	}
}
