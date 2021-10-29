package turismoEnLaTierraMedia;

import java.util.LinkedList;
import java.util.List;

public interface Sugerible {
List <Sugerible> atraccionComprada = new LinkedList<Sugerible>();

	
	
	public int getCosto();
	
	public TipoDeAtraccion getTipo();
	
	public double getTiempo();
	
	public boolean hayCupo();
	
	public boolean esPromocion();

	public boolean esOContiene(Sugerible sugerencia);

	public void restarCupo();
	
	public Atraccion getAtraccionGratis();
	@Override
	public String toString();


}
