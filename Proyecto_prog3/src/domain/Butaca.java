package domain;

public class Butaca implements Reservable{
	private int fila;
	private int columna;
	private boolean vip;
	private boolean estado;
	private int id_sala;

	public Butaca(int fila, int columna, boolean vip, boolean estado, int id_sala) {
		super();
		this.fila = fila;
		this.columna = columna;
		this.vip = vip;
		this.estado = estado;
		this.id_sala = id_sala;
	}
	
	
	public int getId_sala() {
		return id_sala;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}



	public void setColumna(int columna) {
		this.columna = columna;
	}



	public boolean isVip() {
		return vip;
	}



	public void setVip(boolean vip) {
		this.vip = vip;
	}



	public boolean isEstado() {
		return estado;
	}



	public void setEstado(boolean estado) {
		this.estado = estado;
	}


	@Override
	public boolean cancelar_reserva() {
		
		return false;		
	}

	@Override
	public boolean reservar() {
		
		return false;
	}
	
	
}
