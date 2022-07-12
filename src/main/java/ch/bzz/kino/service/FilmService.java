package ch.bzz.kino.service;

import ch.bzz.kino.data.DataHandler;
import ch.bzz.kino.model.Film;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

/**
 * @Author: Noel
 *
 * @Since 1.0.0-SNAPSHOT
 *
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
    public Response listFilme(
            @CookieParam("userRole") String userRole
    ) {
        List<Film> filmList = null;
        int httpStatus;
        if (userRole == null || userRole.equals("guest")) {
            httpStatus = 404;
        } else {
            httpStatus = 200;
            filmList = DataHandler.getInstance().readAllFilme();
        }

        NewCookie cookie = new NewCookie(
                "userRole",
                userRole,
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        return Response
                .status(httpStatus)
                .entity(filmList)
                .cookie(cookie)
                .build();
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
            @NotEmpty
            @CookieParam("userRole") String userRole,
            @Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
            @PathParam("uuid") String filmUUID

    ) {
        Film film = DataHandler.getInstance().readFilmByUUID(filmUUID);
        if (film == null) {
            return Response
                    .status(404)
                    .entity("Film not found")
                    .build();
        }

        NewCookie cookie = new NewCookie(
                "userRole",
                userRole,
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        return Response
                .status(200)
                .entity(film)
                .cookie(cookie)
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
            @NotEmpty
            @Valid @BeanParam Film film,
            @CookieParam("userRole") String userRole,
            @FormParam("filmUUID") String filmUUID
    ){
        film.setFilmUUID(filmUUID);
        film.setFilmUUID(UUID.randomUUID().toString());
        DataHandler.getInstance().insertFilm(film);

        NewCookie cookie = new NewCookie(
                "userRole",
                userRole,
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        return Response
                .status(200)
                .entity("Film succesfully inserted")
                .cookie(cookie)
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
            @NotEmpty
            @Valid @BeanParam Film film,
            @FormParam("filmUUID") String filmUUID,
            @CookieParam("userRole") String userRole
    ){
        int httpStatus = 200;

        Film oldFilm = DataHandler.getInstance().readFilmByUUID(film.getFilmUUID());
        if (film == null) {
            oldFilm.setTitel(film.getTitel());
            oldFilm.setLaenge(film.getLaenge());
            oldFilm.setPreis(film.getPreis());
            oldFilm.setHauptdarsteller(film.getHauptdarsteller());
            oldFilm.setRegisseur(film.getRegisseur());

            DataHandler.getInstance().updateFilm();
        }else {
            httpStatus = 404;
        }

        NewCookie cookie = new NewCookie(
                "userRole",
                userRole,
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        return Response
                .status(httpStatus)
                .entity("Film succesfully updated")
                .cookie(cookie)
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
            @NotEmpty
            @Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
            @FormParam("uuid") String filmUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        if(userRole.equals("admin"))
        if (!DataHandler.getInstance().deleteFilm(filmUUID)) {
            httpStatus = 404;
        } else {
            httpStatus = 200;
        }

        NewCookie cookie = new NewCookie(
                "userRole",
                userRole,
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        return Response
                .status(httpStatus)
                .entity("film deleted")
                .cookie(cookie)
                .build();
    }

}