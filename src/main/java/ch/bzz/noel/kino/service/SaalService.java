package ch.bzz.noel.kino.service;

import ch.bzz.noel.kino.data.DataHandler;
import ch.bzz.noel.kino.model.Saal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
     * @Param Saal
     * @return Response
     */
    @PUT
    @Path("create")
    @Produces (MediaType.TEXT_PLAIN)
    public Response insertSaal(
            @FormParam("saalUUID") String saalUUID,
            @FormParam("saalNummer") int saalNummer,
            @FormParam("plaetze") int plaetze,
            @FormParam("reihen") int reihen,
            @FormParam("anzahlPlaetzeProReihe") int anzahlPlaetzeProReihe
    ){
        Saal saal = new Saal();
        saal.setSaalNummer(saalNummer);
        saal.setPlaetze(plaetze);
        saal.setReihen(reihen);
        saal.setAnzahlPlaetzeProReihe(anzahlPlaetzeProReihe);

        DataHandler.getInstance().insertSaal(saal);
        return Response
                .status(200)
                .entity("Saal erfolgreich angelegt")
                .build();
    }

    /**
     * Update a saal
     *
     * @Param Saal
     * @return Response
     */
    @POST
    @Path("update")
    @Produces (MediaType.TEXT_PLAIN)
    public Response updateSaal(
            @FormParam("saalUUID") String saalUUID,
            @FormParam("saalNummer") int saalNummer,
            @FormParam("plaetze") int plaetze,
            @FormParam("reihen") int reihen,
            @FormParam("anzahlPlaetzeProReihe") int anzahlPlaetzeProReihe
    ){
        int httpStatus = 200;
        Saal saal = DataHandler.getInstance().readSaalByUUID(saalUUID);
        if (saal == null) {
            saal.setSaalUUID(UUID.randomUUID().toString());
            saal.setSaalNummer(saalNummer);
            saal.setPlaetze(plaetze);
            saal.setReihen(reihen);
            saal.setAnzahlPlaetzeProReihe(anzahlPlaetzeProReihe);

            DataHandler.getInstance().updateFilm();
        }else {
            httpStatus = 404;
        }
        return Response
                .status(httpStatus)
                .entity("Saal erfolgreich aktualisiert")
                .build();
    }

    /**
     * Delete a Saal identified by uuid
     * @Param saalUUID
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteSaal(
            @Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
            @FormParam("saalUUID") String saalUUID
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
