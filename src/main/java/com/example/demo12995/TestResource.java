package com.example.demo12995;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Component
@Path("/test")
@Produces(MediaType.TEXT_PLAIN)
public class TestResource {

    @GET
    @PreAuthorize("isAuthenticated()")
    public String test() {
        return "Hello, World!";
    }

    @Path("/reverse")
    public String test(@QueryParam("s") @NotNull String s) {
        return new StringBuilder(s).reverse().toString();
    }
}
