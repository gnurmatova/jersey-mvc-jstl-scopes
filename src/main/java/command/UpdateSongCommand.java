package command;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Song;
import connectionprovider.ConnectionProvider;

public class UpdateSongCommand {

	public String execute(Song s) {

		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("UPDATE SONGS SET title=?, artist=? WHERE id=?");
			stmt.setString(1, s.getTitle());
			stmt.setString(2, s.getArtist());
			stmt.setInt(3, s.getId());
			stmt.executeUpdate();

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "-1";
	}

}
