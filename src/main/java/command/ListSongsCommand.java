package command;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Song;
import connectionprovider.ConnectionProvider;

public class ListSongsCommand {

	public ArrayList<Song> execute() {
		ArrayList<Song> ret = new ArrayList<Song>();
		try {
			Connection connection = ConnectionProvider.getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Songs");
			while (rs.next()) {
				Song s = new Song();
				s.setArtist(rs.getString("artist"));
				s.setTitle(rs.getString("title"));
				s.setId(rs.getInt("id"));
				ret.add(s);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

}
