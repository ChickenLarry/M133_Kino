package ch.bzz.kino.util;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.util.*;


/**
 * @Author: Noel
 *
 * @Since 1.0.0-SNAPSHOT
 *
 */

@Provider
public class AuthorizationFilter implements javax.ws.rs.container.ContainerRequestFilter {
    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        Method method = resourceInfo.getResourceMethod();

        if(method.isAnnotationPresent(DenyAll.class)) {
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity("Access blocked for all users !!").build());
        } else if (!method.isAnnotationPresent(PermitAll.class) &&
                method.isAnnotationPresent(RolesAllowed.class)) {
            RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
            Set<String> requiredRoles = new HashSet<>(Arrays.asList(rolesAnnotation.value()));
            String userRole = getToken(requestContext.getHeaders());

            if (userRole == null || !isUserAllowed(requiredRoles, userRole)){
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity("You cannot access this resource").build());
            }
        }


    }

    /**
     * checks if the userrole is sufficent to access the service
     * @param requiredRoles  the required roles
     * @param userRole  the role of this user
     * @return  allowed true/false
     */
    private boolean isUserAllowed(final Set<String> requiredRoles, String userRole)
    {
        return (requiredRoles.contains(userRole));
    }

    /**
     * get the authorization token
     * @param headers  the request headers
     * @return token
     */
    private String getToken(MultivaluedMap<String, String> headers) {
        String token = "";
        final List<String> authorizations = headers.get(AUTHORIZATION_PROPERTY);
        if (authorizations != null) {
            for (String entry : authorizations) {
                String[] data = entry.split(" ");
                if (data[0].equals("Bearer")) {
                    token = data[1];
                }
            }

            final Map<String, String> claims = JWToken.readClaims(token);
            if (claims.isEmpty()) return null;
            return claims.get("role");
        } else {
            return null;
        }
    }
}
