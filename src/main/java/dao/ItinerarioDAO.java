package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.TurismoConnectionProvider;
import turismoEnLaTierraMedia.Sugerible;
import turismoEnLaTierraMedia.TipoDeAtraccion;
import turismoEnLaTierraMedia.Usuario;

public class ItinerarioDAO {
	
	public static int insert(Usuario t) {
		try {
			String query = "INSERT INTO ITINERARIO (ID_USUARIO, ID_PROMO, ID_ATRACCION) VALUES(?, ?, ?)";
			Connection conn= TurismoConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			for(Sugerible s : t.getCompras()) {
				if(s.esPromocion()) {
					statement.setInt(1, t.getId());
					statement.setInt(2, s.getId());
					statement.setString(3, "NULL");
				}
				else {
					statement.setInt(1, t.getId());
					statement.setString(2, "NULL");
					statement.setInt(3, s.getId());
				}
			}
			
			return statement.executeUpdate();
			
		} catch (SQLException e) {
			throw new MissingDataException(e);
			}
		}

	public static int delete(Usuario t) {
		try {
			String query = "DELETE FROM ITINERARIO WHERE ID_USUARIO = ?";
			Connection conn= TurismoConnectionProvider.getConnection();
			
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, t.getId());
			
			return statement.executeUpdate();
			
		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}



}