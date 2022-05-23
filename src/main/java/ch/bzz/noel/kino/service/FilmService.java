package ch.bzz.noel.kino.service;

import ch.bzz.noel.kino.data.DataHandler;
import ch.bzz.noel.kino.model.Film;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * Film service
 *
 * @author noel
 * @version 1.0
 */
@Path("film")
public class FilmService {

    /**
     * @return List of all films
     * @throws JsonProcessingException
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listFilme() {
        List<Film> filmList = DataHandler.getInstance().readallFilme();
        try {
            return Response
                    .status(200)
                    .entity(new ObjectMapper().writeValueAsString(filmList))
                    .build();
        } catch (JsonProcessingException e) {
            return Response
                    .status(500)
                    .entity("Fehler beim Serialisieren der Filme")
                    .build();
        }
    }

    /**
     * Read a film by uuid
     *
     * @param filmUUID
     */
    @GET
    @Path("read/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readFilm(
            @PathParam("uuid") String filmUUID

    ) {
        Film film = DataHandler.getInstance().readFilmByUUID(filmUUID);
        if (film == null) {
            return Response
                    .status(404)
                    .entity("Film nicht gefunden")
                    .build();
        }

        return Response
                .status(200)
                .entity(film)
                .build();
    }


}
