package ch.bzz.noel.kino.service;

import ch.bzz.noel.kino.data.DataHandler;
import ch.bzz.noel.kino.model.Film;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

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

    /**
     * Create a new film
     *
     * @Param Film
     * @return Response
     */
    @PUT
    @Path("create")
    @Produces (MediaType.TEXT_PLAIN)
    public Response insertFilm(
            @FormParam("titel") String titel,
            @FormParam("laenge") int laenge,
            @FormParam("preis") int preis,
            @FormParam("regisseur") String regisseur,
            @FormParam("hauptdarsteller") String hauptdarsteller
    ){
        Film film = new Film();
        film.setFilmUUID(UUID.randomUUID().toString());
        film.setTitel(titel);
        film.setLaenge(laenge);
        film.setPreis(preis);
        film.setRegisseur(regisseur);
        film.setHauptdarsteller(hauptdarsteller);

        DataHandler.getInstance().insertFilm(film);
        return Response
                .status(200)
                .entity("Film erfolgreich angelegt")
                .build();
    }

    /**
     * Update a film
     *
     * @Param Film
     * @return Response
     */
    @POST
    @Path("update")
    @Produces (MediaType.TEXT_PLAIN)
    public Response updateFilm(
            @FormParam("filmUUID") String filmUUID,
            @FormParam("titel") String titel,
            @FormParam("laenge") int laenge,
            @FormParam("preis") int preis,
            @FormParam("regisseur") String regisseur,
            @FormParam("hauptdarsteller") String hauptdarsteller
    ){
        int httpStatus = 200;
        Film film = DataHandler.getInstance().readFilmByUUID(filmUUID);
        if (film == null) {
            film.setTitel(titel);
            film.setLaenge(laenge);
            film.setPreis(preis);
            film.setRegisseur(regisseur);
            film.setHauptdarsteller(hauptdarsteller);

            DataHandler.getInstance().updateFilm();
        }else {
            httpStatus = 404;
        }
        return Response
                .status(httpStatus)
                .entity("Film erfolgreich aktualisiert")
                .build();
    }

    /**
     * Delete a film identified by uuid
     * @param filmUUID
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteFilm(
            @FormParam("uuid") String filmUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.getInstance().deleteFilm(filmUUID)) {
            httpStatus = 404;
        }
        return Response
                .status(httpStatus)
                .entity("film gelöscht")
                .build();
    }
}
