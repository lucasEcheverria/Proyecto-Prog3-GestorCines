package domain;

public abstract class Persona {
	private String user;
	private String contrasenia;
	private String nombre; 
	private String apellidos;
	private String dni;
	
	public Persona(String user, String contrasenia, String nombre, String apellidos, String dni) {
		super();
		this.user = user;
		this.contrasenia = contrasenia;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
	
	
	
	
	
}
