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
import turismoEnLaTierraMedia.App;
import turismoEnLaTierraMedia.Atraccion;
import turismoEnLaTierraMedia.Promocion;
import turismoEnLaTierraMedia.Sugerible;


public class AtraccionDAO {
	
		
	private static Atraccion toAtraccion(ResultSet result) throws SQLException {
			return new Atraccion(result.getInt(1), result.getString(2), result.getInt(3), result.getDouble(4), result.getInt(5), TipoDeAtraccion.valueOf(result.getString(6)));
	}

	public static List<Atraccion> findAll(){
			
		try {
				String sql = "SELECT * FROM ATRACCION";
				Connection conn = TurismoConnectionProvider.getConnection();
				
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet results = statement.executeQuery();
				
				List<Atraccion> atracciones =  new LinkedList<Atraccion>();
				while(results.next()) {
					atracciones.add(toAtraccion(results));
			}
				return atracciones;
		} catch (SQLException e) {
				throw new MissingDataException(e);
		}
			
	}
	
	public static List<Atraccion> findAllByPromoId(int id){
		
		try {
				String sql = "SELECT id, nombre, precio, tiempo, cupo, tipo FROM atraccion INNER JOIN promocion_atracion ON atraccion.id = promocion_atracion.id_atraccion WHERE promocion_atracion.id_promo = ?";
				Connection conn = TurismoConnectionProvider.getConnection();
				
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, id);
				ResultSet result = statement.executeQuery();
				
				List<Atraccion> atracciones = new LinkedList<Atraccion>();
				while(result.next()) {
					
					for (Atraccion a : App.atraccionesAll) {
						if (a.getId() == result.getInt(1)) {
							atracciones.add(a);
						}
					}
					
				}
				return atracciones;
				
		} catch (SQLException e) {
				throw new MissingDataException(e);
		}
			
	}

	public static int countAll() {
		try {
			String sql = "SELECT COUNT(ID) AS TOTAL FROM ATRACCION";
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

		public static int insert(Atraccion t) {
			Connection conn = null;
			try {
				String sql = "INSERT INTO ATRACCION (ID, NOMBRE, PRECIO, TIEMPO, CUPO, TIPO) VALUES(?, ?, ?, ?, ?, ?)";
				conn = TurismoConnectionProvider.getConnection();
				
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, t.getId());
				statement.setString(2, t.getNombre());
				statement.setInt(3, t.getCosto());
				statement.setDouble(4, t.getTiempo());
				statement.setInt(5, t.getCupo());
				statement.setString(6, t.getTipo().name());
				
							
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

		public static int update(Atraccion t) {
			try {
				String sql = "UPDATE ATRACCION SET PRESUPUESTO = ?, TIEMPO = ?, CUPO = ? WHERE ID = ?";
				Connection conn = TurismoConnectionProvider.getConnection();
				
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, t.getCosto());
				statement.setDouble(2, t.getTiempo());
				statement.setInt(3, t.getCupo());
				statement.setInt(4, t.getId());

				return statement.executeUpdate();
				
			} catch (SQLException e) {
				throw new MissingDataException(e);
			}
		}
		
		public static void updateCupo(Sugerible s) {
			try {
				String sql = "UPDATE ATRACCION SET cupo = cupo - 1 WHERE id = ?";

				Connection conn = TurismoConnectionProvider.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				
	
				if(s.esPromocion()) {
					for (Atraccion a : ((Promocion) s).getAtracciones()) {
						statement.setInt(1, a.getId());
						statement.executeUpdate();
					}
				}else {
					statement.setInt(1, s.getId());
					statement.executeUpdate();

				}
				
			} catch (SQLException e) {
				throw new MissingDataException(e);
			}
		}


		public static int delete(Atraccion a) {
			try {
				String sql = "DELETE FROM ATRACCION WHERE ID = ?";
				Connection conn = TurismoConnectionProvider.getConnection();
				
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, a.getId());
				
				return statement.executeUpdate();
			} catch (SQLException e) {
				throw new MissingDataException(e);
			}
		}

		public static Atraccion findById(int id) {
			try {
				String sql = "SELECT * FROM ATRACCION WHERE ID =?";
				Connection conn = TurismoConnectionProvider.getConnection();
				
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, id);
				ResultSet result = statement.executeQuery();
				
				Atraccion atracciones = null;
				if(result.next()) {
					atracciones = toAtraccion(result);
				}
				return atracciones;
			} catch (SQLException e) {
				throw new MissingDataException(e);
			}
		}

		public static List<Atraccion> findByNombre(String nombre) {
			try {
				String sql = "SELECT * FROM ATRACCION WHERE NOMBRE = ?";
				Connection conn = TurismoConnectionProvider.getConnection();
				
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, nombre);
				ResultSet results = statement.executeQuery();
				
				List<Atraccion> atracciones =  new LinkedList<Atraccion>();
				while(results.next()) {
					atracciones.add(toAtraccion(results));
				}
				return atracciones;
			} catch (SQLException e) {
				throw new MissingDataException(e);
			}
		}


	}

