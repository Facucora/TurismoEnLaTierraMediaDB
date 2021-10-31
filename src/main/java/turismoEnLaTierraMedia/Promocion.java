package turismoEnLaTierraMedia;

import java.util.List;

public abstract class Promocion implements Sugerible {
	protected int id;
	protected List<Atraccion> atracciones;
	protected TipoDeAtraccion tipo;
	protected int precioODesc;
	protected String beneficio;
	protected double tiempoTotal;
	protected String nombreDeLaPromo;
	
	

	public Promocion(int id, String nombreDeLaPromo, TipoDeAtraccion tipo, String beneficio, List<Atraccion> atracciones) {
		this.id = id;
		this.nombreDeLaPromo = nombreDeLaPromo;
		this.tipo = tipo;
		this.beneficio = beneficio;
		this.atracciones = atracciones;
	}

	
	
	
	public boolean esPromocion() {
		return true;
	}

	
	public boolean hayCupo() {
		for (Atraccion a : this.atracciones) {
			if (!a.hayCupo())
				return false;
		}		
		return true;
	}
	
	
	public void restarCupo() {
		for (Atraccion a : this.atracciones) {
			a.restarCupo();
		}
	}

	
	public double getTiempo() {
		tiempoTotal = 0;
		for (int i = 0; i < this.atracciones.size(); i++) {
			tiempoTotal += this.atracciones.get(i).getTiempo();
		}
		return tiempoTotal;
	}

	
	public TipoDeAtraccion getTipo() {
		return this.tipo;
	}

	
	public boolean esOContiene(Sugerible s) {
		for (Atraccion a : this.atracciones) {
			if (a.equals(s))
				return true;
		}
		return false;
	}

	public List<Atraccion> getAtracciones() {
		return atracciones;
	}
	
	public abstract int getAhorro();

	public String getBeneficio() {
		return this.beneficio;
	}


	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}




	public String getNombre() {
		// TODO Auto-generated method stub
		return this.nombreDeLaPromo;
	}
}
