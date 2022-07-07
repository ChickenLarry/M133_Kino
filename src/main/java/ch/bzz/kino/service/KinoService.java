package ch.bzz.kino.service;

import ch.bzz.kino.data.DataHandler;
import ch.bzz.kino.model.Kino;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

/**
 * @Author: Noel
 *
 * @Since 1.0.0-SNAPSHOT
 *
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
    public Response listKinos(
            @CookieParam("userRole") String userRole
    ) {
        List<Kino> kinoList = DataHandler.getInstance().readAllKino();
        int httpStatus;
        if (userRole == null || userRole.equals("guest")) {
            httpStatus = 404;
        } else {
            httpStatus = 200;
        }
        return Response
                .status(httpStatus)
                .entity(kinoList)
                .build();
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
                    .entity("Kino not found")
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
                .entity("Kino succesfully created")
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

            DataHandler.getInstance().updateKino();
        }else {
            httpStatus = 404;
        }
        return Response
                .status(httpStatus)
                .entity("Kino succesfully updated")
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
                .entity("kino deleted")
                .build();
    }
}
