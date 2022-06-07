package ch.bzz.noel.kino.service;

import ch.bzz.noel.kino.data.DataHandler;
import ch.bzz.noel.kino.model.Film;
import ch.bzz.noel.kino.model.Kino;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

/**
 * Kino service
 *
 * @author noel
 * @version 1.0
 */
@Path("kino")
public class KinoService {

    /**
     * @return List of all kinos
     * @throws JsonProcessingException
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listKinos() {
        List<Kino> kinoList = DataHandler.getInstance().readallKinos();
        try {
            return Response
                    .status(200)
                    .entity(new ObjectMapper().writeValueAsString(kinoList))
                    .build();
        } catch (JsonProcessingException e) {
            return Response
                    .status(500)
                    .entity("Fehler beim Serialisieren des Kinos")
                    .build();
        }
    }

    /**
     * Read a kino by uuid
     *
     * @param kinoUUID
     */
    @GET
    @Path("read/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readKino(
            @NotEmpty
            @Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
            @PathParam("uuid") String kinoUUID

    ) {
        Kino kino = DataHandler.getInstance().readKinoByUUID(kinoUUID);
        if (kino == null) {
            return Response
                    .status(404)
                    .entity("Kino nicht gefunden")
                    .build();
        }

        return Response
                .status(200)
                .entity(kino)
                .build();
    }

    /**
     * Create a new kino
     *
     * @Param Kino
     * @return Response
     */
    @PUT
    @Path("create")
    @Produces (MediaType.TEXT_PLAIN)
    public Response insertKino(
            @Valid @BeanParam Kino kino,
            @FormParam("kinoUUID") String kinoUUID
    ){
        kino.setKinoUUID(kinoUUID);
        kino.setKinoUUID(UUID.randomUUID().toString());

        DataHandler.getInstance().insertKino(kino);
        return Response
                .status(200)
                .entity("Kino erfolgreich angelegt")
                .build();
    }

    /**
     * Update a kino
     *
     * @Param Kino
     * @return Response
     */
    @POST
    @Path("update")
    @Produces (MediaType.TEXT_PLAIN)
    public Response updateKino(
            @Valid @BeanParam Kino kino,
            @FormParam("kinoUUID") String kinoUUID
    ){
        int httpStatus = 200;
        Kino oldKino = DataHandler.getInstance().readKinoByUUID(kinoUUID);
        if (kino == null) {
            oldKino.setKinoUUID(UUID.randomUUID().toString());
            oldKino.setName(kino.getName());
            oldKino.setOrt(kino.getOrt());

            DataHandler.getInstance().updateFilm();
        }else {
            httpStatus = 404;
        }
        return Response
                .status(httpStatus)
                .entity("Kino erfolgreich aktualisiert")
                .build();
    }

    /**
     * Delete a kino identified by uuid
     * @Param kinoUUID
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteKino(
            @NotEmpty
            @Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
            @QueryParam("uuid") String kinoUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.getInstance().deleteKino(kinoUUID)) {
            httpStatus = 404;
        }
        return Response
                .status(httpStatus)
                .entity("kino gelöscht")
                .build();
    }
}
