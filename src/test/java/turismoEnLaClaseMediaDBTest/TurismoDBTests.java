package turismoEnLaClaseMediaDBTest;

import static org.junit.Assert.* ;


//IMPORTANTE
// PARA REALIZAR LOS TEST ES NECESARIO CAMBIAR EL *ADMINITRADORDEARCHIVOS/LEERPROMOCIONES* A LA RUTA DEL ARCHIVO /PromocionesPruebaIgna para que coincida con los numeros esperados

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import turismoEnLaTierraMedia.TipoDeAtraccion;
import turismoEnLaTierraMedia.*;

@SuppressWarnings("unused")
public class TurismoDBTests {
	Atraccion a1;
	Atraccion a2;
	Atraccion a3;
	Atraccion a4;
	Promocion p1;
	Promocion p2;
	Promocion p3;
	Promocion p4;
	Promocion p5;
	List<Atraccion> listaAtracciones;
	List<Promocion> listaPromo;
	Usuario u1;

		@Before
		public void setUp() {
		listaAtracciones = AdministradorDeArchivos.leerAtracciones();
		listaPromo = AdministradorDeArchivos.leerPromociones();
		u1= new Usuario("Pepito", 100, 100, TipoDeAtraccion.AVENTURA);
		a1 = listaAtracciones.get(0);
		a2 = listaAtracciones.get(1);
		a3 = listaAtracciones.get(2);
		a4 = listaAtracciones.get(3);
		p1 = listaPromo.get(0);
		p2 = listaPromo.get(1);
		p3 = listaPromo.get(2);
		p4 = listaPromo.get(3);
		}
		
		
		
		@Test
		public void testCompila() {
			System.out.println(listaAtracciones);
			//System.out.println(listaPromo);
			//System.out.println(p1);
			//System.out.println(p2);
			//System.out.println(p3);
			//System.out.println(p4);
			
		}
		
		@Test
		public void testCosto() {
			assertEquals(10, p1.getCosto());
			assertEquals(8, p2.getCosto());
			assertEquals(12, p3.getCosto());
			assertEquals(5, p4.getCosto());
		}
			
		
		@Test
		public void testTiempo() {
			assertEquals(5.5, p1.getTiempo(),0);
			assertEquals(7, p2.getTiempo(),0);
			assertEquals(9.5, p3.getTiempo(),0);
			assertEquals(5.5, p4.getTiempo(),0);
			
		}
		
		@Test
		public void testTipo() {
			assertEquals(TipoDeAtraccion.AVENTURA, p1.getTipo());
			assertEquals(TipoDeAtraccion.AVENTURA, p2.getTipo());
			assertEquals(TipoDeAtraccion.AVENTURA, p3.getTipo());
			assertEquals(TipoDeAtraccion.AVENTURA, p4.getTipo());
			
		}
		
		@Test
		public void testContienAtraccion() {
			assertTrue(p1.esOContiene(a1));
			assertTrue(p1.esOContiene(a2));
			assertFalse(p1.esOContiene(a4));
			assertTrue(p3.esOContiene(a2));
		}
		
		@Test
		public void testAhorro() {
			assertEquals(4, p1.getAhorro());
			assertEquals(4, p2.getAhorro());
			assertEquals(4, p3.getAhorro());
			assertEquals(7, p4.getAhorro());
		}
		
		
		@Test 
		public void testNro1() {
		u1.comprarSugerible(a1);
		assertFalse(u1.tieneTiempo(p2) && u1.puedeCostear(p2) && !u1.yaCompro(p2));
		
		}

		@Test 
		public void testNro2(){
		u1.comprarSugerible(p1);
		assertFalse(u1.tieneTiempo(a2) && u1.puedeCostear(a2) && !u1.yaCompro(a2));
		
		}
		
		@Test 
		public void testNro3(){
		u1.comprarSugerible(p1);
		assertFalse(u1.tieneTiempo(p2) && u1.puedeCostear(p2) && !u1.yaCompro(p2));
		
		}
		
		@Test 
		public void testNro4(){
		u1.comprarSugerible(p3);
		assertFalse(u1.tieneTiempo(p4) && u1.puedeCostear(p4) && !u1.yaCompro(p4));
				
		}		

	}