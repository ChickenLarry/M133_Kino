package ch.bzz.noel.kino.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ch.bzz.noel.kino.data.DataHandler;
import ch.bzz.noel.kino.model.Film;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * Film service
 */
@Path("film")
public class FilmService {

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
