package service;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.Pais;

public class ServicePaises {
	String json;
	
	public ServicePaises() {
		json="[{\"country\":\"USA\",\"population\":249903450,\"capital\":\"washington\",\"temperature\":21.5,\"foundation\":1750,\"continent\":\"America\"},";
		json+="{\"country\":\"Canada\",\"population\":145290345,\"capital\":\"Ottawa\",\"temperature\":14.3,\"foundation\":1870,\"continent\":\"America\"},";
		json+="{\"country\":\"Spain\",\"population\":47345901,\"capital\":\"Madrid\",\"temperature\":24.7,\"foundation\":1520,\"continent\":\"Europe\"},";
		json+="{\"country\":\"Mexico\",\"population\":95000360,\"capital\":\"Mexico\",\"temperature\":26.2,\"foundation\":1880,\"continent\":\"America\"},";
		json+="{\"country\":\"Norway\",\"population\":31034000,\"capital\":\"Oslo\",\"temperature\":10.9,\"foundation\":1901,\"continent\":\"Europe\"},";
		json+="{\"country\":\"Germany\",\"population\":95456920,\"capital\":\"Berlin\",\"temperature\":18.0,\"foundation\":1830,\"continent\":\"Europe\"},";
		json+="{\"country\":\"Japan\",\"population\":110478934,\"capital\":\"Tokio\",\"temperature\":21.6,\"foundation\":1857,\"continent\":\"Asia\"},";
		json+="{\"country\":\"Russia\",\"population\":215678924,\"capital\":\"Moscow\",\"temperature\":11.3,\"foundation\":1802,\"continent\":\"Asia\"},";
		json+="{\"country\":\"France\",\"population\":76256702,\"capital\":\"Paris\",\"temperature\":19.4,\"foundation\":1670,\"continent\":\"Europe\"},";
		json+="{\"country\":\"United Kingdom\",\"population\":65023789,\"capital\":\"London\",\"temperature\":17.4,\"foundation\":1650,\"continent\":\"Europe\"}]";
				
	}

	private Stream<JSONObject> JsonToStream() {
		//creamos el objeto JSONParser y el objeto JSONArray.
		JSONParser parser = new JSONParser();
		JSONArray array;
		
		//leemos el documento y lo volcamos en la variable JSONArray.
		try {
			array = (JSONArray) parser.parse(json);
			return (Stream<JSONObject>)array.stream();	
		}
		catch(ParseException ex) {
			ex.getMessage();
			ex.printStackTrace();
			return Stream.empty();
		}
	}
	
	private Pais convertirAPais(JSONObject s) {
		return new Pais((String)s.get("country"), (String)s.get("capital"), (long)s.get("population"), (String)s.get("continent"));
	}
	
	public int paisesMasHabitantesValor(long habitantes) {
		return (int) JsonToStream()
				.filter(s->(long)s.get("population")>habitantes)
				.count();
	}
	
	public double temperaturaMediaPaises() {
			return JsonToStream()
					.mapToDouble(p->(double)p.get("temperature"))
					.average()
					.orElse(0);
	}
	
	public boolean algunPaisCondicion(long habitantesMax, long yearMenor) {
		
				
	}
	
	public Pais paisMasPoblado() {
		return JsonToStream()
				.map(j->convertirAPais(j))
				.max((p1,p2)->(int)p1.getHabitantes()-p2.getHabitantes())
				
	}
	
	public List<Pais> paisesFundacionAnterior(long year){
		
	}
	public String continenteConMasPaises() {
		
	}
	public Map<String,List<Pais>> paisesPorContinente(){
		
	}
}