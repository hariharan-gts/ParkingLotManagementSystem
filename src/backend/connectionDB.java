package backend;

 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionDB {

	final String DB_URL
		= "jdbc:mysql://localhost:3306/vehicle?autoReconnect=true&useSSL=false";

	final String USER = "root";

	final String PASS = "password";

public Connection connectDB()
	{
		
		Connection con = null;

		try {

	
			Class.forName("com.mysql.cj.jdbc.Driver");

		
			con = DriverManager.getConnection(DB_URL, USER,
											PASS);
		}

		catch (SQLException e) {

			
			e.printStackTrace();
		}

		catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		return con;
	}
}
