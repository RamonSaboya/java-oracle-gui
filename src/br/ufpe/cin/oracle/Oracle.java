package br.ufpe.cin.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Oracle {

	private static Oracle INSTANCE;

	public static boolean initialize(String address, String username, String password) {
		try {
			INSTANCE = new Oracle(address, username, password);
		} catch (SQLException ex) {
			ex.printStackTrace();

			return false;
		}

		return true;
	}

	public static Oracle getInstance() {
		return INSTANCE;
	}

	// Amount of connections used by the app
	private static final int POOL_SIZE = 1;

	// Thread-safe control of connections
	private List<Connection> connections;
	private AtomicInteger currentConnection;

	private Oracle(String address, String username, String password) throws SQLException {
		this.connections = new ArrayList<Connection>();
		this.currentConnection = new AtomicInteger(0);

		// Setup connection pool
		for (int i = 1; i <= POOL_SIZE; i++) {
			connections.add(createConnection(address, username, password));
		}

		// Create table
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("CREATE TABLE files(name VARCHAR2(100) NOT NULL, data BLOB NOT NULL, CONSTRAINT files_pkey PRIMARY KEY (name))");

			stmt.close();
		} catch (SQLException ex) {
			// Ignored, table already exists
		}
	}

	private Connection createConnection(String address, String user, String password) throws SQLException {
		String URL = "jdbc:oracle:thin:@" + address + ":1521:XE";

		return DriverManager.getConnection(URL, user, password);
	}

	// Obtains sequential thread-safe connection
	public Connection getConnection() {
		synchronized (connections) {
			if (currentConnection.get() == connections.size()) {
				currentConnection.set(0);
			}

			return connections.get(currentConnection.getAndIncrement());
		}
	}
}
