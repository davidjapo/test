package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

import model.Caso;
import util.Utilidades;

public class CasoService {
	private Socket socket;
	
	public CasoService(Socket socket) {
		this.socket = socket;
		service();
	}

	public void service() {
		Stream<Caso> listado = Utilidades.jsonToStream();
	
		try(PrintStream out = new PrintStream(socket.getOutputStream());){
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String ccaa_iso = in.readLine();	
			//out.println("---"+Utilidades.convertIsoCcaa(ccaa_iso)+"---");
			
			//LocalDate ld1 = LocalDate.parse(fechaPicoContagio(listado, ccaa_iso), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						
			out.println(sdf.parse(String.valueOf(fechaPicoContagio(listado, ccaa_iso))));
			
			/*String resultado = "\nTotal positivos: "+totalPositivos(listado, ccaa_iso)+
					"\nMedia positivos diarios: "+mediaPositivosDiarios(listado, ccaa_iso)+
					"\nPico de contagios: "+fechaPicoContagio(listado, ccaa_iso);*/

			
		}catch(IOException | ParseException io) {
			io.printStackTrace();
		}finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
	
	private int totalPositivos(Stream<Caso> listado, String ccaa) {
		return listado.filter(c -> c.getCcaa_iso().equals(ccaa))
				.mapToInt(c -> c.getNum_casos())
				.sum();
	}
	
	private double mediaPositivosDiarios(Stream<Caso> listado, String ccaa) {
		return totalPositivos(listado, ccaa)/listado.map(c -> c.getFecha())
				.count();
	}
	
	private Date fechaPicoContagio(Stream<Caso> listado, String ccaa) {
		Stream<Caso> listadoBis = Utilidades.jsonToStream();
		int maxPositivoDia = maxPositivoDia(listadoBis, ccaa);
		return listado.filter(c -> c.getCcaa_iso().contains(ccaa) && c.getNum_casos()  == maxPositivoDia)
				.map(c -> c.getFecha())
				.findFirst().get();
	}
	
	private int maxPositivoDia(Stream<Caso> listado, String ccaa) {
		return listado.filter(c -> c.getCcaa_iso().equals(ccaa))
				.mapToInt(c -> c.getNum_casos())
				.max().getAsInt();
	}
}
