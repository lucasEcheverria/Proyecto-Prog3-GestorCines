package domain;

public class Empleado {
	private Puesto puesto; 
	private float salario;
	
	public Empleado(Puesto puesto, float salario) {
		super();
		this.puesto = puesto;
		this.salario = salario;
	}

	public Puesto getPuesto() {
		return puesto;
	}

	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}
	
	
}
