package ch.bzz.noel.kino.service;

import ch.bzz.noel.kino.data.DataHandler;
import ch.bzz.noel.kino.model.Film;
import ch.bzz.noel.kino.model.Kino;
import ch.bzz.noel.kino.model.Saal;
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
 * Saal service
 *
 * @author noel
 * @version 1.0
 */
@Path("Saal")
public class SaalService {

    /**
     * @return List of all saals
     * @throws JsonProcessingException
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)

    public Response listSaal() {
        List<Saal> saalList = DataHandler.getInstance().readallSaele();
        try {
            return Response
                    .status(200)
                    .entity(new ObjectMapper().writeValueAsString(saalList))
                    .build();
        } catch (JsonProcessingException e) {
            return Response
                    .status(500)
                    .entity("Fehler beim Serialisieren der Saale")
                    .build();
        }
    }

    /**
     * Read a saal by uuid
     *
     * @param saalUUID
     */
    @GET
    @Path("read/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response readSaal(
            @NotEmpty
            @Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
            @PathParam("uuid") String saalUUID

    ) {
        Saal saal = DataHandler.getInstance().readSaalByUUID(saalUUID);
        if (saal == null) {
            return Response
                    .status(404)
                    .entity("Saal nicht gefunden")
                    .build();
        }

        return Response
                .status(200)
                .entity(saal)
                .build();
    }

    /**
     * Create a new saal
     *
     * @return Response
     * @Param Saal
     */
    @PUT
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertSaal(
            @Valid @BeanParam Saal saal,
            @FormParam("saalUUID") String saalUUID
    ) {
        saal.setSaalUUID(saalUUID);
        saal.setSaalUUID(UUID.randomUUID().toString());

        DataHandler.getInstance().insertSaal(saal);
        return Response
                .status(200)
                .entity("Saal erfolgreich angelegt")
                .build();
    }

    /**
     * Update a saal
     *
     * @return Response
     * @Param Saal
     */
    @POST
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateSaal(
            @Valid @BeanParam Saal saal,
            @FormParam("saalUUID") String saalUUID
    ) {
        int httpStatus = 200;
        Saal oldSaal = DataHandler.getInstance().readSaalByUUID(saalUUID);
        if (saal == null) {
            oldSaal.setSaalUUID(UUID.randomUUID().toString());
            oldSaal.setSaalNummer(saal.getSaalNummer());
            oldSaal.setPlaetze(saal.getPlaetze());
            oldSaal.setReihen(saal.getReihen());
            oldSaal.setAnzahlPlaetzeProReihe(saal.getAnzahlPlaetzeProReihe());

            DataHandler.getInstance().updateFilm();
        } else {
            httpStatus = 404;
        }
        return Response
                .status(httpStatus)
                .entity("Saal erfolgreich aktualisiert")
                .build();
    }

    /**
     * Delete a Saal identified by uuid
     *
     * @return Response
     * @Param saalUUID
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteSaal(
            @NotEmpty
            @Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
            @QueryParam("uuid") String saalUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.getInstance().deleteSaal(saalUUID)) {
            httpStatus = 404;
        }
        return Response
                .status(httpStatus)
                .entity("Saal gelöscht")
                .build();
    }
}
