package com.kerem.userman.filter;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.NameBinding;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import com.kerem.userman.dao.UserDao;
import com.kerem.userman.model.Role;
import com.kerem.userman.model.User;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@AuthorizationNeeded
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationNeededFilter implements ContainerRequestFilter {
	

    @Context
    private UriInfo uriInfo;
    
	@Inject
	@Named("userDaoImpl")
    private UserDao userDao;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        List<String> roles = retrieveUserRoles(requestContext);
        AuthorizationNeeded securedAnnotation = getSecuredAnnotation(requestContext);

        if (!hasRequiredRoles(securedAnnotation, roles)) {
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
        }
    }

    private List<String> retrieveUserRoles(ContainerRequestContext requestContext) {
        List<String> roles = new ArrayList<>();
    	User user = userDao.findByEmail("keskinkrm55@gmail.com");
        Role userRole = user.getRole();
        if (userRole.isCanUserAdd()) {
        	roles.add("AddUser");
        }
        if (userRole.isCanUserEdit()) {
        	roles.add("EditUser");
        }
        if (userRole.isCanUserDelete()) {
        	roles.add("DeleteUser");
        }
    	return roles;
    }
    

    private boolean hasRequiredRoles(AuthorizationNeeded securedAnnotation, List<String> userRoles) {
        String[] requiredRoles = securedAnnotation.value();
        for (String requiredRole : requiredRoles) {
            if (!userRoles.contains(requiredRole)) {
                return false;
            }
        }
        return true;
    }
}
