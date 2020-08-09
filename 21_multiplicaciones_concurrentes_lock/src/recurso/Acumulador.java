package recurso;

public class Acumulador {
	private int valor;
	
	public void agregar(int cantidad) {
		valor+=cantidad;
	}
	
	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
}
