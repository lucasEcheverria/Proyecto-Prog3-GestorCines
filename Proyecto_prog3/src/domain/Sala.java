package domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Sala {
	private static int cont = 0;
	private int id;
	private ArrayList<Butaca> butacas;
	private HashMap<String, Pelicula> horarios;
	
	public Sala() {
		super();
		this.id = cont;
		cont ++;
	}

	public Sala(ArrayList<Butaca> butacas, HashMap<String, Pelicula> horarios) {
		super();
		this.id = cont;
		cont ++;
		this.butacas = butacas;
		this.horarios = horarios;
	}

	public int getId() {
		return id;
	}

	public ArrayList<Butaca> getButacas() {
		return butacas;
	}

	public void setButacas(ArrayList<Butaca> butacas) {
		this.butacas = butacas;
	}

	public HashMap<String, Pelicula> getHorarios() {
		return horarios;
	}

	public void setHorarios(HashMap<String, Pelicula> horarios) {
		this.horarios = horarios;
	}
	
	public ArrayList<Butaca> crearButacas() {
		ArrayList<Butaca> asientos = new ArrayList<Butaca>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 10; j++) {
				Butaca b = new Butaca(i, j, false, false,this.id);
				if (i==7||i==8) {
					b.setVip(true);
				}
				asientos.add(b);
			}
			
		}
		return asientos;
	}
	
	public void cargarPelis() {
		this.horarios= new HashMap<String, Pelicula>();
		try {
			File f = new File("resource/data/Pelis.txt");
			Scanner sc = new Scanner(f);
			while (sc.hasNextLine()) {
				String string = sc.nextLine();
				String [] campos = string.split(";");
				String titulo = campos[0];
				String dir = campos[1];
				Genero tipo = Genero.valueOf(campos[2]);
				float duracion = Float.parseFloat(campos[3]);
				String rutafoto = campos[4];
				boolean tresd = Boolean.parseBoolean(campos[5]);
				int id_sala = Integer.parseInt(campos[6]);
				
				String[] fechas = campos[7].split(",");
				if (id_sala==this.id) {
					Pelicula peli = new Pelicula(titulo, tipo, duracion, dir, rutafoto, tresd);
					for (int i = 0; i < fechas.length; i++) {
						if (!horarios.keySet().contains(fechas[i])) {
							this.horarios.put(fechas[i],peli);
						}		
					}
				}
				
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
