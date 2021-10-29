package dao;

public class ItinerarioDAO {
	
	saveItinerario(Usuario u) {
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			UPDATE USARIO
			for(atraccion comprada) {
				INSERT ITENERARIO
				UPDATE ATRACCION
			}
		} catch (SQLException e) {
			conn.rollback();
		} finally {
			conn.commit();
		}
	}


SET cupo = cupo - 1
cupo = ?


UPDATE atracciones SET cupo = cupo -1 WHERE id = ?


query = UPDATE USUARIO
statementUpdateUsuario = prepare(query)
statementUpdateUsuario.setAlgo(algo, Algo)
statementUpdateUsuario.execute()
	
	
	
	dqweqweqweqw

}
