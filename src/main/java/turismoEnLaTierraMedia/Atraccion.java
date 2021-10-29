package turismoEnLaTierraMedia;

import java.util.Objects;

public class Atraccion implements Sugerible {
	private int id;
	private int costo;
	protected String nombre;
	protected TipoDeAtraccion tipo;
	private double tiempo;
	private int cupo;

	public Atraccion(int id, String nombre, int costo, double tiempo, int cupo, TipoDeAtraccion tipo) {
		this.id = id;
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
		this.cupo = cupo;
		this.tipo = tipo;
	}
	
	public Atraccion(String nombre, int costo, double tiempo, int cupo, TipoDeAtraccion tipo) {
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
		this.cupo = cupo;
		this.tipo = tipo;
	}
	
	
	public Atraccion(String nombre) {
		this.nombre = nombre;
		
	}

	public int getCupo() {
		return cupo;
	}
	
	public int getId() {
		return this.id;
	}

	
	public boolean hayCupo() {
		return this.cupo > 0;
	}

	
	public void restarCupo() {
		this.cupo--;
	}

	
	public double getTiempo() {
		return this.tiempo;
	}

	
	public int getCosto() {
		return this.costo;
	}

	public String getNombre() {
		return this.nombre;
	}

	
	public TipoDeAtraccion getTipo() {
		return this.tipo;
	}

	
	public boolean esPromocion() {
		return false;
	}

	
	public String toString() {
		return "Atraccion " + nombre + ", Tiempo: " + tiempo + ", Costo: " + costo + ", Cupo: " + cupo +  ", Tipo: " + tipo + "\n";
	}

	
	public boolean esOContiene(Sugerible sugerencia) {
		return this.equals(sugerencia);
	}

	
	public int hashCode() {
		return Objects.hash(costo, nombre, tiempo, tipo);
	}

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		return costo == other.costo && Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(tiempo) == Double.doubleToLongBits(other.tiempo) && tipo == other.tipo;
	}

	
	public Atraccion getAtraccionGratis() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
