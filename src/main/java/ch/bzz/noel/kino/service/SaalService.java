package ch.bzz.noel.kino.service;

import jakarta.ws.rs.Path;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ch.bzz.noel.kino.data.DataHandler;
import ch.bzz.noel.kino.model.Saal;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


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
     *
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
     * @param saalUUID
     *
     */
    @GET
    @Path("read/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response readSaal(
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
}
