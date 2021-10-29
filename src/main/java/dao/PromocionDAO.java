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
		List<Atraccion> atraccionAxB = AtraccionDAO.findAllByPromoId(result.getInt(1));
		atraccionAxB.remove(AtraccionDAO.findById(result.getInt(4)));
		return new PromocionAxB(result.getInt(1), result.getString(2), TipoDeAtraccion.valueOf(result.getString(3)), AtraccionDAO.findById(result.getInt(4)), result.getString(5), atraccionAxB);
		
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
			Connection conn = null;
			try {
				String sql = "INSERT INTO PROMOCION (ID, NOMBRE, PRECIO, TIEMPO, CUPO, TIPO) VALUES(?, ?, ?, ?, ?, ?)";
				conn = TurismoConnectionProvider.getConnection();
				
				PreparedStatement statement = conn.prepareStatement(sql);
				
				
				String querry = "SELECT Beneficio FROM PROMOCION WHERE beneficio LIKE \"Porcentual\"";
				conn = TurismoConnectionProvider.getConnection();
				
				PreparedStatement statement2 = conn.prepareStatement(querry);
				ResultSet result = statement2.executeQuery();
				result.next();
				String total = result.getString("TIPO");
				if(total == "porcentual")
				
				
				statement.setInt(1, t.getId());
				statement.setString(2, t.getNombre());
				statement.setInt(3, t.getCosto());
				statement.setDouble(4, t.getTiempo());
				statement.setString(5, t.getTipo().name());
				
							
				return statement.executeUpdate();
			} catch (SQLException e) {
				throw new MissingDataException(e);
			}finally {
					  if (conn != null) {
					    try {
					      conn.close(); 
					    } catch (SQLException e) {
					    	throw new MissingDataException(e);
					    }
					  }
					}
			}

		public static int update(Promocion t) {
			try {
				String sql = "UPDATE PROMOCION SET PRESUPUESTO = ?, TIEMPO = ? WHERE ID = ?";
				Connection conn = TurismoConnectionProvider.getConnection();
				
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, t.getCosto());
				statement.setDouble(2, t.getTiempo());
				statement.setInt(, t.getId());

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
					promociones.add(toPromocion(results));
				}
				return promociones;
			} catch (SQLException e) {
				throw new MissingDataException(e);
			}
		}
		
		
	}

