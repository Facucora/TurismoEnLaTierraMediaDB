package turismoEnLaTierraMedia;

import java.util.List;

public class PromocionAxB extends Promocion {
	// la atraccion gratis la pasamos por archivo (la ultima atraccion)

	private int precioFinal;
	
	private Atraccion atraccionGratis;

	public PromocionAxB(int id, String nombreDeLaPromo, 
			TipoDeAtraccion tipo, Atraccion atraccionGratis, String beneficio, List<Atraccion> atracciones) {
		super(id, nombreDeLaPromo, tipo, beneficio, atracciones);
		
		atracciones.remove(this.atraccionGratis);
		this.atracciones = atracciones;
		this.atraccionGratis = atraccionGratis;
	
		
	}

	
	public int getCosto() {
		precioFinal = 0;
		
//		for (int i = 0; i < super.atracciones.size(); i++) {
//			if (super.atracciones.get(i).getNombre() != atraccionGratis.getNombre())
//				precioFinal += super.atracciones.get(i).getCosto();
//		}
		 for(Atraccion a : this.atracciones) {
			precioFinal += a.getCosto();
		}
		 precioFinal -= this.atraccionGratis.getCosto();
		return this.precioFinal;
	}

	
	public String toString() {
		String nombreDeLasAtracciones = "";
		for (Atraccion a : atracciones) {
			nombreDeLasAtracciones += a.getNombre() + ", ";
		}
		return "PromocionAxB: " + nombreDeLaPromo + ", Tipo : " + tipo + "\n " 
				+ "  Atracciones Incluidas: " + atraccionGratis.getNombre()
				+ ", " + nombreDeLasAtracciones + "\n " 
				+ "  Tiempo Total: " + this.getTiempo() + ", Precio Total: "
				+ this.getCosto() + "\n"  + "   Ahorro Comprando La Promo: " + this.getAhorro() + " monedas" + "\n";
	}
	
	
	public double getTiempo() {
		tiempoTotal = 0;
		for (int i = 0; i < this.atracciones.size(); i++) {
			tiempoTotal += this.atracciones.get(i).getTiempo();
		}
		tiempoTotal += atraccionGratis.getTiempo();
		return tiempoTotal;
	}
	
	public Atraccion getAtraccionGratis() {
		return atraccionGratis;
	}
	
	
	public boolean esOContiene(Sugerible s) {
		if(this.atraccionGratis.equals(s)) {
			return true;
		}
		else for (Atraccion a : super.atracciones) {
			if (a.equals(s))
				return true;
		}
		return false;
	}
	
	
	public int getAhorro() {
		return this.atraccionGratis.getCosto();
	}
	
	
	public void restarCupo() {
		super.restarCupo();
		this.atraccionGratis.restarCupo();
		
		}
	
	public List<Atraccion> getAtracciones(){
		List<Atraccion> atraccionAxB;
		atraccionAxB = this.atracciones;
		//atraccionAxB.add(getAtraccionGratis());
		return atraccionAxB;
	}
	
	public boolean hayCupo() {
		for (Atraccion a : this.atracciones) {
			if (!a.hayCupo() || !this.atraccionGratis.hayCupo())
				return false;
		}		
		return true;
	}

	

}
