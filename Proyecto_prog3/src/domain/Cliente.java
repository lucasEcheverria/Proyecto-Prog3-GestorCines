package domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Cliente extends Persona{
	private String correo; 
	private HashMap<Entrada, Integer> carrito_de_compra;
	
	public Cliente(String user, String contrasenia, String nombre, String apellidos, String dni, String correo,
			HashMap<Entrada, Integer> carrito_de_compra) {
		super(user, contrasenia, nombre, apellidos, dni);
		this.correo = correo;
		this.carrito_de_compra = carrito_de_compra;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public HashMap<Entrada, Integer> getCarrito_de_compra() {
		return carrito_de_compra;
	}

	public void setCarrito_de_compra(HashMap<Entrada, Integer> carrito_de_compra) {
		this.carrito_de_compra = carrito_de_compra;
	}
	
	public HashMap<Entrada, Integer> cargarEntradas() {
		HashMap<Entrada, Integer> entradas = new HashMap<Entrada, Integer>();
		File f = new File("resource/data/Entrada.txt");
		try {
			Scanner sc = new Scanner(f);
			while (sc.hasNextLine()) {
				String string = sc.nextLine();
				String[] campos = string.split(";");
				
				int id_sala = Integer.parseInt(campos[0]);
				int id_peli = Integer.parseInt(campos[1]);
				String titulo = campos[2];
				int fila = Integer.parseInt(campos[3]);
				int column = Integer.parseInt(campos[4]);
				boolean vip = Boolean.valueOf(campos[5]);
				String hora = campos[6];
				
				Butaca butaca = new Butaca(fila, column, vip, true, id_sala);
				Entrada entrada = new Entrada(butaca, id_peli, titulo, id_sala,10,hora);

				
				entradas.put(entrada, 1);
			}
			sc.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return entradas;
	}
	
}
