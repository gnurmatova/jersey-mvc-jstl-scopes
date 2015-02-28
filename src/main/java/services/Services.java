package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Song;

import org.glassfish.jersey.server.mvc.Viewable;

import util.Constants;
import util.PropertiesLookup;

import com.fasterxml.jackson.databind.ObjectMapper;
import command.CreateSongCommand;
import command.GetSongCommand;
import command.ListSongsCommand;
import command.UpdateSongCommand;

@Path("song")
public class Services {
	ObjectMapper mapper = new ObjectMapper();

	@GET
	@Path("mvc")
	public Viewable index() {
		return new Viewable("/index.jsp");
	}

	@GET
	@Path("mvc/model")
	public Response mvcModel() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", "Gula");
		List<Song> songList = new ArrayList<Song>();
		ListSongsCommand cmd = new ListSongsCommand();
		songList = cmd.execute();
		map.put("songs", songList);
		return Response.ok(new Viewable("/model.jsp", map)).build();
	}

	@GET
	@Path("mvc/jstlmodel")
	public Response mvcJSTLModel() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", "Gula");
		List<Song> songList = new ArrayList<Song>();
		ListSongsCommand cmd = new ListSongsCommand();
		songList = cmd.execute();
		map.put("songs", songList);
		return Response.ok(new Viewable("/jstlmodel.jsp", map)).build();
	}

	@POST
	@Path("mvc/create")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response post(@FormParam("title") String title,
			@FormParam("artist") String artist) {
		Song s = new Song();
		s.setArtist(artist);
		s.setTitle(title);
		CreateSongCommand cmd = new CreateSongCommand();
		return Response.ok(new Viewable("/create_success.jsp", cmd.execute(s)))
				.build();
	}

	@GET
	@Path("mvc/getsong")
	public Response mvcGetSong(@QueryParam("id") int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		GetSongCommand cmd = new GetSongCommand();
		Song s = cmd.execute(id);
		map.put("song", s);
		return Response.ok(new Viewable("/view_song2.jsp", map)).build();
	}

	@GET
	@Path("mvc/scopes")
	public Response mvcScopes(@Context HttpServletRequest request) {
		String res = "";

		res += "rVar=" + request.getAttribute("rVar");
		return Response.ok(res).build();
	}

	@GET
	@Path("properties/{property}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response propertiesCheck(@PathParam("property") String property) {
		PropertiesLookup pl = new PropertiesLookup();
		String pValue = pl.getProperty(property);
		return Response.status(200).entity(pValue).build();
	}

	// Browse all songs
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response browseSongs(@QueryParam("offset") int offset,
			@QueryParam("count") int count) {
		ListSongsCommand command = new ListSongsCommand();
		ArrayList<Song> list = command.execute();
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put(Constants.Pagination.DATA, list);
		hm.put(Constants.Pagination.OFFSET, offset);
		hm.put(Constants.Pagination.COUNT, count);
		String songString = null;
		try {
			songString = mapper.writeValueAsString(hm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(songString).build();
	}

	// get song by Id
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getSong(@PathParam("id") int id) {
		GetSongCommand command = new GetSongCommand();
		String songString = null;
		try {
			songString = mapper.writeValueAsString(command.execute(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(songString).build();
	}

	// Add a song
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response createSongs(String payload) {
		CreateSongCommand create = new CreateSongCommand();
		Song s = null;
		String i = "";
		try {
			s = mapper.readValue(payload, Song.class);
		} catch (Exception ex) {
			ex.printStackTrace();
			Response.status(400).entity("could not read string").build();
		}
		try {
			i = create.execute(s);
		} catch (Exception e) {
			e.printStackTrace();
			Response.status(500).build();
		}
		return Response.status(200).entity(i).build();
	}

	// Update a song
	@POST
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response updateSongs(String payload, @PathParam("id") int id) {
		UpdateSongCommand update = new UpdateSongCommand();
		Song s = null;
		try {
			s = mapper.readValue(payload, Song.class);
			s.setId(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			Response.status(400).entity("could not read string").build();
		}
		try {
			update.execute(s);
		} catch (Exception e) {
			e.printStackTrace();
			Response.status(500).build();
		}
		return Response.status(200).build();
	}

	// Delete a song
	@DELETE
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response delSong(@PathParam("id") int id) {

		return Response.status(200).entity("").build();

	}

	// Search songs
}
