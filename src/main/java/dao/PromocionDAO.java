package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import jdbc.TurismoConnectionProvider;
import turismoEnLaTierraMedia.TipoDeAtraccion;
import turismoEnLaTierraMedia.Promocion;
import turismoEnLaTierraMedia.Atraccion;
import turismoEnLaTierraMedia.PromocionAxB;
import turismoEnLaTierraMedia.PromocionAbsoluta;
import turismoEnLaTierraMedia.PromocionPorcentual;


public class PromocionDAO {
	
		
	
	private static Promocion toPromocionPorcentual(ResultSet result) throws SQLException {
			return new PromocionPorcentual(result.getInt(1), result.getString(2), TipoDeAtraccion.valueOf(result.getString(3)), result.getInt(4), result.getString(5), AtraccionDAO.findAllByPromoId(result.getInt(1)));
		}
	
	private static Promocion toPromocionAxB(ResultSet result) throws SQLException {
	
		return new PromocionAxB(result.getInt(1), result.getString(2), TipoDeAtraccion.valueOf(result.getString(3)), AtraccionDAO.findById(result.getInt(4)), result.getString(5), AtraccionDAO.findAllByPromoId(result.getInt(1)));
		
		}
	private static Promocion toPromocionAbsoluta(ResultSet result) throws SQLException {
			return new PromocionAbsoluta(result.getInt(1), result.getString(2), TipoDeAtraccion.valueOf(result.getString(3)), result.getInt(4), result.getString(5), AtraccionDAO.findAllByPromoId(result.getInt(1)));
		}

	public static List<Promocion> findAll(){
			
		try {
				String sql = "SELECT * FROM PROMOCION";
				Connection conn = TurismoConnectionProvider.getConnection();
				
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet results = statement.executeQuery();
				
				List<Promocion> promociones =  new LinkedList<Promocion>();
				while(results.next()) {
						if(results.getString(5).equals("AxB")) {
						promociones.add(toPromocionAxB(results));
						System.out.println(toPromocionAxB(results).getAtracciones());
						} if(results.getString(5).equals("Porcentual")) {
							promociones.add(toPromocionPorcentual(results));
						}if((results.getString(5).equals("Absoluta")))  {
						promociones.add(toPromocionAbsoluta(results));
						}
			}
				return promociones;

		} catch (SQLException e) {
				throw new MissingDataException(e);
		}
			
	}

	public static int countAll() {
		try {
			String sql = "SELECT COUNT(ID) AS TOTAL FROM PROMOCION";
			Connection conn = TurismoConnectionProvider.getConnection();
				
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet result = statement.executeQuery();
				
				result.next();
				int total = result.getInt("TOTAL");
				
				return total;
				
			} catch (SQLException e) {
				throw new MissingDataException(e);
			}
		}

		public static int insert(Promocion t) {
			 
			try {
				String sql = "INSERT INTO PROMOCION (ID, NOMBRE, TIPO, PRECIO_DESC, BENEFICIO) VALUES(?, ?, ?, ?, ?)";
				Connection conn = TurismoConnectionProvider.getConnection();
				
				PreparedStatement statement = conn.prepareStatement(sql);
				
				statement.setInt(1, t.getId());
				statement.setString(2, t.getNombre());
				statement.setString(3, t.getTipo().name());
				statement.setInt(4, t.getCosto());
				statement.setString(5, t.getBeneficio());
				
							
				return statement.executeUpdate();
			} catch (SQLException e) {
				throw new MissingDataException(e);
				}
					
			}
		
		public static int insertAtraccionPromo(Promocion t) {
			 
			try {
				String sql = "INSERT INTO PROMOCION_ATRACCION (ID_PROMO, NOMBRE_PROMO, ID_ATRACCION, NOMBRE_ATRACCION) VALUES(?, ?, ?, ?)";
				Connection conn = TurismoConnectionProvider.getConnection();
				
				PreparedStatement statement = conn.prepareStatement(sql);
				
				statement.setInt(1, t.getId());
				statement.setString(2, t.getNombre());
				for(Atraccion a: t.getAtracciones()) {
					statement.setInt(3, (a).getId());
					statement.setString(4, a.getNombre());
				}				
							
				return statement.executeUpdate();
			} catch (SQLException e) {
				throw new MissingDataException(e);
				}
					
			}



		public static int delete(Promocion a) {
			try {
				String sql = "DELETE FROM PROMOCION WHERE ID = ?";
				Connection conn = TurismoConnectionProvider.getConnection();
				
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, a.getId());
				
				return statement.executeUpdate();
			} catch (SQLException e) {
				throw new MissingDataException(e);
			}
		}

		public static Promocion findById(int id) {
			try {
				String sql = "SELECT * FROM PROMOCION WHERE ID =?";
				Connection conn = TurismoConnectionProvider.getConnection();
				
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, id);
				ResultSet result = statement.executeQuery();
				
				Promocion promociones = null;
				if(result.next()) {
					if(result.getString(5).equals("AxB")) {
					promociones = toPromocionAxB(result);
					} if(result.getString(5).equals("Porcentual")) {
						promociones = toPromocionPorcentual(result);
					}if((result.getString(5).equals("Absoluta")))  {
					promociones = toPromocionAbsoluta(result);
					}
				}
				return promociones;
			} catch (SQLException e) {
				throw new MissingDataException(e);
			}
		}

		public static List<Promocion> findByNombre(String nombre) {
			try {
				String sql = "SELECT * FROM PROMOCION WHERE NOMBRE = ?";
				Connection conn = TurismoConnectionProvider.getConnection();
				
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, nombre);
				ResultSet results = statement.executeQuery();
				
				List<Promocion> promociones =  new LinkedList<Promocion>();
				while(results.next()) {
					if(results.getString(5).equals("AxB")) {
						promociones.add(toPromocionAxB(results));
						System.out.println(toPromocionAxB(results).getAtracciones());
						} if(results.getString(5).equals("Porcentual")) {
							promociones.add(toPromocionPorcentual(results));
						}if((results.getString(5).equals("Absoluta")))  {
						promociones.add(toPromocionAbsoluta(results));
						}
			}
				return promociones;
			} catch (SQLException e) {
				throw new MissingDataException(e);
			}
		}
		
		
	}

