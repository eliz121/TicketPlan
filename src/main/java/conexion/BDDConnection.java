package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BDDConnection {

	private static Connection cnn = null;

	private BDDConnection() {
		String servidor = "localhost";
		String database = "clientes";
		String usuario = "root";
		String password = "";

		String url = "jdbc:mysql://" + servidor + "/" + database + "?useSSL=false&serverTimezone=UTC";
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			cnn = DriverManager.getConnection(url, usuario, password);
			System.out.println("✅ Conexión a MySQL exitosa");
		} catch (SQLException e) {
			System.err.println("❌ Error al conectar a la base de datos: " + e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConexion() {
		if (cnn == null) {
			new BDDConnection();
		}else {
			System.out.println("Conexión ya existente reutilizada");
		}
		return cnn;
	}

	public static void cerrar(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rs = null;
	}

	public static void cerrar(PreparedStatement pstmt) {
		try {
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void cerrar() {
		if (cnn != null) {
			try {
				cnn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cnn = null;
		}
	}
}
