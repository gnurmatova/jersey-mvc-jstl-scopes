package command;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Song;
import connectionprovider.ConnectionProvider;

public class CreateSongCommand {

	public String execute(Song s) {

		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("INSERT INTO SONGS(title, artist) VALUES(?, ?) Returning id");
			stmt.setString(1, s.getTitle());
			stmt.setString(2, s.getArtist());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return rs.getString("id");
			}

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "-1";
	}

}
