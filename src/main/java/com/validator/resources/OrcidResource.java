package com.validator.resources;

import com.validator.service.OrcidValidator;
import javax.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Map;
import java.util.HashMap;

@Path("/orcid")
@Produces(MediaType.APPLICATION_JSON)
public class OrcidResource {
    private final OrcidValidator validator;

    @Inject
    public OrcidResource(OrcidValidator validator) {
        this.validator = validator;
    }

    @GET
    @Path("/validate/{orcid}")
    public Response validateOrcid(@PathParam("orcid") String orcid) {
        try {
            var result = validator.validate(orcid);
            var response = new HashMap<String, Object>();
            response.put("orcid", orcid);
            response.put("formatValid", result.isFormatValid());
            response.put("exists", result.exists());
            if (result.getName() != null) {
                response.put("name", result.getName());
            }
            return Response.ok(response).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity(Map.of("error", "Invalid ORCID format"))
                .build();
        }
    }
}
