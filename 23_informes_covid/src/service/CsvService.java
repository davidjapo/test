package service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Caso;
import util.Utilidades;

public class CsvService extends BaseService {
	Path path;
	String RUTA;
	
	public CsvService(String ruta) {
		this.RUTA=ruta;
		path=Paths.get(RUTA);
		if(!Files.exists(path)) {
			try {
				Files.createFile(path);
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	@Override
	public Stream<Caso> streamCaso() {
		try {
			return Files.lines(path, StandardCharsets.ISO_8859_1)
					//.map(s->Utilidades.stringToPedido(s));
					.map(Utilidades::stringToCaso);
		}
		catch(IOException ex) {
			ex.printStackTrace();
			return Stream.empty();
		}
		
	}

}
