package ch.bzz.kino.service;

import ch.bzz.kino.data.DataHandler;
import ch.bzz.kino.model.Film;
import ch.bzz.kino.model.Saal;
import ch.bzz.kino.model.User;
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
@Path("user")
public class UserService {

    /**
     * @return Login of User
     *
     *
     * @param username
     * @param password
     */
    @POST
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(
            @FormParam("username") String username,
            @FormParam("password") String password
    ) {
        int httpStatus;
        User user = DataHandler.getInstance().readUser(username, password);
        if (user.getUserRole().equals("guest")) {
            httpStatus = 404;
        } else {
            httpStatus = 200;
        }
        NewCookie cookie = new NewCookie(
                "userRole",
                user.getUserRole(),
                "/",
                "kino.ch",
                "Login-Cookie",
                600,
                false
        );
        return Response
                .status(httpStatus)
                .entity("")
                .cookie(cookie)
                .build();
    }

    @DELETE
    @Path("logout")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logout(
    ) {
        NewCookie cookie = new NewCookie(
                "userRole",
                "guest",
                "/",
                "kino.ch",
                "Logout-Cookie",
                1,
                false
        );
        return Response
                .status(200)
                .entity("")
                .cookie(cookie)
                .build();
    }

    /**
     * @return List of all users
     * @throws JsonProcessingException
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listUser(
            @CookieParam("userRole") String userRole
    ) {
        List<User> userList = null;
        int httpStatus;
        if (userRole == null || userRole.equals("guest")) {
            httpStatus = 404;
        } else {
            httpStatus = 200;
            userList = DataHandler.getInstance().readAllUser();
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
                .entity(userList)
                .cookie(cookie)
                .build();
    }

    /**
     * Read a user by uuid
     *
     * @param userUUID
     */
    @GET
    @Path("read/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response readUser(
            @NotEmpty
            @CookieParam("userRole") String userRole,
            @Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
            @PathParam("uuid") String userUUID

    ) {
        User user = DataHandler.getInstance().readUserByUUID(userUUID);
        if (user == null) {
            return Response
                    .status(404)
                    .entity("User not found")
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
                .entity(user)
                .cookie(cookie)
                .build();
    }

    /**
     * Create a new user
     *
     * @return Response
     * @Param User
     */
    @PUT
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertUser(
            @Valid @BeanParam User user,
            @CookieParam("userRole") String userRole,
            @FormParam("userUUID") String userUUID
    ) {
        user.setUserUUID(userUUID);
        user.setUserUUID(UUID.randomUUID().toString());
        DataHandler.getInstance().insertUser(user);

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
                .entity("User succesfully inserted")
                .cookie(cookie)
                .build();
    }

    /**
     * Update a user
     *
     * @return Response
     * @Param User
     */
    @POST
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUser(
            @Valid @BeanParam User user,
            @FormParam("userUUID") String userUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        User oldUser = DataHandler.getInstance().readUserByUUID(userUUID);
        if (user == null) {
            oldUser.setUserUUID(UUID.randomUUID().toString());
            oldUser.setUserName(user.getUserName());
            oldUser.setPassword(user.getPassword());
            oldUser.setUserRole(user.getUserRole());


            DataHandler.getInstance().updateUser();
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
                .entity("User succesfully updated")
                .cookie(cookie)
                .build();
    }

    /**
     * Delete a User identified by uuid
     *
     * @return Response
     * @Param userUUID
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUser(
            @NotEmpty
            @Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
            @FormParam("uuid") String userUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        if (!DataHandler.getInstance().deleteUser(userUUID)) {
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
                .entity("user deleted")
                .cookie(cookie)
                .build();
    }

}
