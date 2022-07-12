package ch.bzz.kino.service;

import ch.bzz.kino.data.DataHandler;
import ch.bzz.kino.model.Kino;
import ch.bzz.kino.model.Saal;
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
@Path("saal")
public class SaalService {

    /**
     * @return List of all saals
     * @throws JsonProcessingException
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listSaal(
            @CookieParam("userRole") String userRole
    ) {
        List<Saal> saalList = null;
        int httpStatus;
        if (userRole == null || userRole.equals("guest")) {
            httpStatus = 404;
        } else {
            httpStatus = 200;
            saalList = DataHandler.getInstance().readAllSaal();
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
                .entity(saalList)
                .cookie(cookie)
                .build();
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
            @CookieParam("userRole") String userRole,
            @Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
            @PathParam("uuid") String saalUUID

    ) {
        Saal saal = DataHandler.getInstance().readSaalByUUID(saalUUID);
        if (saal == null) {
            return Response
                    .status(404)
                    .entity("Saal not found")
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
                .entity(saal)
                .cookie(cookie)
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
            @NotEmpty
            @Valid @BeanParam Saal saal,
            @CookieParam("userRole") String userRole,
            @FormParam("saalUUID") String saalUUID
    ) {
        saal.setSaalUUID(saalUUID);
        saal.setSaalUUID(UUID.randomUUID().toString());
        DataHandler.getInstance().insertSaal(saal);

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
                .entity("Saal succesfully inserted")
                .cookie(cookie)
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
            @FormParam("saalUUID") String saalUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        Saal oldSaal = DataHandler.getInstance().readSaalByUUID(saalUUID);
        if (saal == null) {
            oldSaal.setSaalUUID(UUID.randomUUID().toString());
            oldSaal.setSaalNummer(saal.getSaalNummer());
            oldSaal.setPlaetze(saal.getPlaetze());
            oldSaal.setReihen(saal.getReihen());
            oldSaal.setAnzahlPlaetzeProReihe(saal.getAnzahlPlaetzeProReihe());

            DataHandler.getInstance().updateSaal();
        } else {
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
                .entity("Saal succesfully updated")
                .cookie(cookie)
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
            @FormParam("uuid") String saalUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        if (!DataHandler.getInstance().deleteSaal(saalUUID)) {
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
                .entity("saal deleted")
                .cookie(cookie)
                .build();
    }

}
