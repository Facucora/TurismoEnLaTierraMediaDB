package turismoEnLaTierraMedia;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import dao.*;
import jdbc.*;

public class App {

	public static List<Atraccion> atraccionesAll;
	private static List<Usuario> usuarios;
	private static List<Promocion> promociones;
	private static List<Sugerible> sugeribles;
	private static List<Sugerible> sugeriblesUsuario;

	public static void main(String[] args) {

		atraccionesAll = new LinkedList<Atraccion>();
		usuarios = new LinkedList<Usuario>();
		promociones = new LinkedList<Promocion>();
		sugeribles = new LinkedList<Sugerible>();
		sugeriblesUsuario = new LinkedList<Sugerible>();
	
	
//		System.out.println(UsuarioDAO.findAll());
		atraccionesAll = AtraccionDAO.findAll(); // Lee el archivo, crea las atracciones y las guarda en
		// una lista
		sugeribles.addAll(atraccionesAll);

		usuarios = UsuarioDAO.findAll(); // Lee el archivo, crea a los usuarios y los guarda en una
// listaF
		promociones = PromocionDAO.findAll(); // Lee el archivo, crea las promociones y las guarda en
		// una lista
		sugeribles.addAll(promociones);
//		System.out.println(sugeribles);
//
		System.out.println("USUARIOS\n");
		for (Usuario u : usuarios)
			System.out.println(u); // Recorre cada usuario de la lista y los imprime en consola
		System.out.println("------------------------------------------------------------------\n");

		System.out.println("ATRACCIONES\n");
		for (Atraccion a : atraccionesAll)
			System.out.println(a); // Recorre cada atraccion de la lista y las imprime en consola
		System.out.println("------------------------------------------------------------------\n");

		System.out.println("PROMOCIONES\n");
		for (Promocion p : promociones)
			System.out.println(p); // Recorre cada usuario de la lista y los imprime en consola
		System.out.println("------------------------------------------------------------------\n");

		for (Usuario u : usuarios) {

			System.out.println("********************\n");
			System.out.println("USUARIO: " + u.getNombre());

			sugeribles.sort(new ComparadorDeSugeribles(u));

			for (Sugerible s1 : sugeribles) {

				while (u.tieneTiempo(s1) && u.puedeCostear(s1) && !u.yaCompro(s1) && s1.hayCupo()) {
					sugeriblesUsuario.add(s1);
					

					System.out.println("=============================================================\n");

					System.out.println("ACTIVIDAD SUGERIDA:");

					System.out.println(s1);

					System.out.println("\n");

					System.out.println("Oprima 'S' para comprar o 'N' para continuar");

					String opcion = "";

					Scanner entradaEscanerOpcion = new Scanner(System.in); // Creación de un objeto Scanner;

					opcion = entradaEscanerOpcion.nextLine(); // Invocamos un método sobre un objeto Scanner;
					if (opcion.equals("n") || opcion.equals("N")) {
						System.out.println("\n");
						
						break;
					}
					if (opcion.equals("s") || opcion.equals("S")) {
						
						u.comprarSugerible(s1);
						System.out.println("SUGERENCIA COMPRADA");
						System.out.println("TIEMPO DISPONIBLE: " + u.getTiempoDisponible());
						System.out.println("PRESUPUESTO RESTANTE: " + u.getPresupuesto());
						
						ItinerarioDAO.insert(u);
						AtraccionDAO.updateCupo(s1);

						continue;
					}
					if (!opcion.equals("s") || !opcion.equals("n") || opcion.equals("N") || opcion.equals("S")) {
						System.out.println("Entrada incorrecta");
						System.out.println("\n");
						continue;
					}

				}
				
			}
			System.out.println("\n");

		}
		
		System.out.println("NO HAY MÁS USUARIOS");
		System.out.println(atraccionesAll);
	}
}