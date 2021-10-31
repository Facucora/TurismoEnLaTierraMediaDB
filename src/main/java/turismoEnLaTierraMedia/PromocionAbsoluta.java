package turismoEnLaTierraMedia;

import java.util.List;

public class PromocionAbsoluta extends Promocion {
	
	public PromocionAbsoluta(int id, String nombreDeLaPromo,
			TipoDeAtraccion tipo, int precioODesc, String beneficio, List<Atraccion> atracciones) {
		super(id, nombreDeLaPromo, tipo, beneficio, atracciones);
		this.precioODesc = precioODesc;
	}
	
	public int getCosto() {
		return this.precioODesc;
	}

	
	public String toString() {
		String nombreDeLasAtracciones = ""; 
		for(Atraccion a:atracciones) {
			nombreDeLasAtracciones += a.getNombre() + ", ";			
		}
		return "PromocionAbsoluta: " + nombreDeLaPromo + ", Tipo: " + tipo + "\n" 
				+ "   Atracciones Incluidas: " + nombreDeLasAtracciones + "\n" 
				+ "   Tiempo Total: " + this.getTiempo() + ", Precio Total: " + this.precioODesc + "\n"  + "   Ahorro Comprando La Promo: " + this.getAhorro() + " monedas" + "\n";
	}

	
	public Atraccion getAtraccionGratis() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAhorro() {
		int precioReal= 0;
		for(Atraccion a:super.atracciones) {
		precioReal += a.getCosto();
		} 
		return  precioReal - this.precioODesc;
	}
	
	
}
