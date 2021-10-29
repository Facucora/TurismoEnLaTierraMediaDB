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
import turismoEnLaTierraMedia.Usuario;

public class UsuarioDAO{
	
	private static Usuario toUsuario(ResultSet result) throws SQLException {
		return new Usuario(result.getInt(1), result.getString(2), result.getInt(3),
							result.getDouble(4), TipoDeAtraccion.valueOf(result.getString(5)));
	}

	public static List<Usuario> findAll(){
		
		try {
			String sql = "SELECT * FROM USUARIO";
			Connection conn = TurismoConnectionProvider.getConnection();
			
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			
			List<Usuario> usuarios =  new LinkedList<Usuario>();
			while(results.next()) {
				usuarios.add(toUsuario(results));
			}
			return usuarios;
		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
		
	}

	public static int countAll() {
		try {
			String sql = "SELECT COUNT(ID) AS TOTAL FROM BANDAS";
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

	public static int insert(Usuario t) {
		Connection conn = null;
		try {
			String sql = "INSERT INTO USUARIO (ID, NOMBRE, PRESUPUESTO, TIEMPO, PREFERENCIA) VALUES(?, ?, ?, ?, ?)";
			conn = TurismoConnectionProvider.getConnection();
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, t.getId());
			statement.setString(2, t.getNombre());
			statement.setInt(3, t.getPresupuesto());
			statement.setDouble(4, t.getTiempoDisponible());
			statement.setString(5, t.getTipoFavorito().name());
			
						
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

	public static int update(Usuario t) {
		try {
			String sql = "UPDATE USUARIO SET PRESUPUESTO = ?, TIEMPO = ? WHERE ID = ?";
			Connection conn = TurismoConnectionProvider.getConnection();
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, t.getPresupuesto());
			statement.setDouble(2, t.getTiempoDisponible());
			statement.setInt(3, t.getId());

			return statement.executeUpdate();
			
		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}

	public static int delete(Usuario t) {
		try {
			String sql = "DELETE FROM USUARIO WHERE ID = ?";
			Connection conn = TurismoConnectionProvider.getConnection();
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, t.getId());
			
			return statement.executeUpdate();
		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}

	public static Usuario findById(int id) {
		try {
			String sql = "SELECT * FROM USUARIO WHERE ID =?";
			Connection conn = TurismoConnectionProvider.getConnection();
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			Usuario usuario = null;
			if(result.next()) {
			 usuario = toUsuario(result);
			}
			return usuario;
		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}

	public static List<Usuario> findByNombre(String nombre) {
		try {
			String sql = "SELECT * FROM USUARIO WHERE NOMBRE = ?";
			Connection conn = TurismoConnectionProvider.getConnection();
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			ResultSet results = statement.executeQuery();
			
			List<Usuario> usuarios =  new LinkedList<Usuario>();
			while(results.next()) {
				usuarios.add(toUsuario(results));
			}
			return usuarios;
		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}


}
