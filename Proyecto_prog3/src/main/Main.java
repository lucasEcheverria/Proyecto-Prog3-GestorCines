package main;

import domain.Cartelera;
import gui.Inicio_sesion;

public class Main {

	public static void main(String[] args) {
        Cartelera cartelera = new Cartelera();
        cartelera.setCartelera(cartelera.cargarCartelera());
        
		new Inicio_sesion(cartelera);
		
	}

}
