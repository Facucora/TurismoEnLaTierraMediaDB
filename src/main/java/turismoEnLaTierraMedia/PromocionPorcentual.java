package turismoEnLaTierraMedia;

import java.util.List;

public class PromocionPorcentual extends Promocion {
	
	private int precioFinal;
	private int precio;
	
	
	public PromocionPorcentual(int id, String nombreDeLaPromo, TipoDeAtraccion tipo, 
			int precio, String beneficio, List<Atraccion> atracciones) {
		super(id, nombreDeLaPromo, tipo, beneficio, atracciones); 
		this.precio = precio;
	}
	
	public int getPrecioDesc() {
		return this.precio;
	}


	
	public int getCosto() {
		double precioARedondear = 0;
		precioFinal = 0;
		for (int i = 0; i < super.atracciones.size(); i++) {
			precioFinal += super.atracciones.get(i).getCosto();
			precioARedondear = -((precioFinal*this.precio/100)-precioFinal);			

		}
		return  (int) Math.round(precioARedondear);
	}


	
	public String toString() {
		String nombreDeLasAtracciones = ""; 
		for(Atraccion a:atracciones) {
			nombreDeLasAtracciones += a.getNombre() + ", ";
		}
		return "PromocionPorcentual: " + nombreDeLaPromo + ", Tipo: " + tipo + ", Porcentaje De Descuento: " + this.precio + "%"
				+ "\n" +"   Atracciones Incluidas: " + nombreDeLasAtracciones + "\n"
				+ "   Tiempo Total: " + this.getTiempo() + ", Precio Final: " + this.getCosto() +  "\n"  + 
				"   Ahorro Comprando La Promo: " + this.getAhorro() + " monedas" + "\n" ;
				
	}


	
	public Atraccion getAtraccionGratis() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public int getAhorro() {
		int precioReal= 0;
		for(Atraccion a:super.atracciones) {
		precioReal += a.getCosto();
		} 
		return  (precioReal - this.getCosto());
	}
	

}
