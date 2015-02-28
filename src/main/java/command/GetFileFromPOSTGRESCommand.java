package command;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectionprovider.ConnectionProvider;

public class GetFileFromPOSTGRESCommand {

	public InputStream execute(String filename) {

		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement stmt = conn
					.prepareStatement("SELECT FILE FROM FILES WHERE FILENAME=?");
			stmt.setString(1, filename);
			ResultSet rs = stmt.executeQuery();
	        if (rs != null) {
	            while (rs.next()) {
	                InputStream is = rs.getBinaryStream("file");
	                return is;
	            }
	            rs.close();
	        }    
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
