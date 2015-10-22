import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.h2.tools.RunScript;

public class MainDao {

	private static final String DB_DRIVER = "org.h2.Driver";
	private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;MODE=Oracle";
	private static final String DB_USER = "sa";
	private static final String DB_PASSWORD = "";

	static String createQuery = "CREATE TABLE PERSON(id int primary key, name varchar(255))";
	static String insertQuery = "INSERT INTO PERSON" + "(id, name) values" + "(?,?)";
	static String selectQuery = "select * from PERSON";

	static String insertSqlScript = "RUNSCRIPT FROM 'test.sql'";

	public static void main(String[] args) throws Exception {
		MainDao dao = new MainDao();
		Connection connection = dao.getConnection();
		dao.insertTestData(connection);
		dao.readData(connection,selectQuery);
		dao.runSqlScript(connection, insertSqlScript);
		dao.readData(connection, "select * from customers", true);

	}
	private void readData(Connection connection, String sql, boolean flag) throws SQLException{
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (resultSet.next()) {
		    for (int i = 1; i <= columnsNumber; i++) {
		        if (i > 1) System.out.print(",  ");
		        String columnValue = resultSet.getString(i);
		        System.out.print(columnValue + " " + rsmd.getColumnName(i));
		    }
		    System.out.println("");
		}
	}
	
	private void readData(Connection connection, String selectQuery) throws SQLException {
		PreparedStatement selectPreparedStatement = connection.prepareStatement(selectQuery);
		ResultSet rs = selectPreparedStatement.executeQuery();
		System.out.println("H2 In-Memory Database inserted through PreparedStatement");

		while (rs.next()) {
			System.out.println("Id " + rs.getInt("id") + " Name " + rs.getString("name"));
		}
		selectPreparedStatement.close();
	}

	public Connection getConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}

	public void runSqlScript(Connection connection, String sql) throws SQLException,
			FileNotFoundException {
		// PreparedStatement scriptPreparedStatement = connection.prepareStatement(sql);
		// FileReader reader = new FileReader("test.sql");
		InputStream is = MainDao.class.getResourceAsStream("test.sql");
		Reader reader = new InputStreamReader(is);
		RunScript.execute(connection, reader);
	}

	public void insertTestData(Connection connection) throws SQLException {
		PreparedStatement createPreparedStatement = connection.prepareStatement(createQuery);
		createPreparedStatement.executeUpdate();
		createPreparedStatement.close();

		PreparedStatement insertPreparedStatement = connection.prepareStatement(insertQuery);
		insertPreparedStatement.setInt(1, 1);
		insertPreparedStatement.setString(2, "Jason");
		insertPreparedStatement.executeUpdate();
		insertPreparedStatement.close();
	}
}
