package domain;

public class Pelicula {
	private static int cont = 1;
	private int id;
	private String titulo;
	private String director;
	private Genero tipo;
	private float duracion;
	private String rutafoto;
	private boolean tresd;	
	
	public Pelicula() {
		super();
		this.id = cont;
		cont++;
	}

	public Pelicula(String titulo, Genero tipo, float duracion, String director, String rutafoto, boolean tresd) {
		super();
		this.id = cont;
		this.titulo = titulo;
		this.tipo = tipo;
		this.duracion = duracion;
		this.director = director;
		this.rutafoto = rutafoto;
		this.tresd = tresd;
		cont++;
	}

	public boolean isTresd() {
		return tresd;
	}

	public void setTresd(boolean tresd) {
		this.tresd = tresd;
	}

	public String getRutafoto() {
		return rutafoto;
	}

	public void setRutafoto(String rutafoto) {
		this.rutafoto = rutafoto;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public static int getCont() {
		return cont;
	}

	public static void setCont(int cont) {
		Pelicula.cont = cont;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Genero getTipo() {
		return tipo;
	}

	public void setTipo(Genero tipo) {
		this.tipo = tipo;
	}

	public float getDuracion() {
		return duracion;
	}

	public void setDuracion(float duracion) {
		this.duracion = duracion;
	}

	
	

}
