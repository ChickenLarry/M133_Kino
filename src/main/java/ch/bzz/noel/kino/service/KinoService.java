package ch.bzz.noel.kino.service;

import jakarta.ws.rs.Path;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ch.bzz.noel.kino.data.DataHandler;
import ch.bzz.noel.kino.model.Kino;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * Kino service
 *
 *  * @author noel
 *  * @version 1.0
 */
@Path("kino")
public class KinoService  {

    /**
     * @return List of all kinos
     *
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
     * @param kinoUUID
     *
     */
    @GET
    @Path("read/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readKino(
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

}
