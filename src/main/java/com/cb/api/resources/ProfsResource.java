package com.cb.api.resources;

import com.cb.business.services.ProfsService;
import model.Prof;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@Path("/api/v1/professionals")
@Consumes(MediaType.APPLICATION_JSON + "; charset=utf-8")
@Produces(MediaType.APPLICATION_JSON)
public class ProfsResource {
    private ProfsService profsService;
    public ProfsResource(ProfsService profsService) {
        this.profsService = profsService;
    }

    @GET()
    @Path("/{id}")
    public Response getProf(@PathParam("id") final int id) {
        return Response.ok().entity(profsService.getProf(id)).build();
    }

    @GET()
    public Response getProfs() {
        return Response.ok().entity(profsService.getProfs()).build();
    }

    @POST()
    public Response addProf(final @NotNull Prof prof) {
        this.profsService.addProf(prof);
        return Response.ok().entity(profsService.getProf(prof.getId())).build();
    }

    @POST()
    @Path("/addMultiple")
    public Response addProfs(final @NotNull List<Prof> profs) {
        for(Prof prof : profs) {
            this.profsService.addProf(prof);
        }
        return Response.ok().entity("multiple professionals added").build();
    }

    @PUT()
    @Path("/{id}")
    public Response updateProf(final @NotNull Prof prof, @PathParam("id") final int id){
        this.profsService.updateProf(prof, id);
        return Response.ok().entity("{\"message\" : \"user updated!\"}").build();
    }

    @DELETE()
    @Path("/{id}")
    public Response deleteProf(@PathParam("id") final int id){
        this.profsService.deleteProf(id);
        return Response.ok().entity("{\"message\" : \"user deleted!\"}").build();
    }

    @GET()
    @Path("/companies")
    public Response getCompanies() {
        Set<String> companies = this.profsService.listCompanies();
        return Response.status(200).entity(companies).build();
    }

    @GET()
    @Path("/companies/{name}")
    public Response getCompanies(@PathParam("name") String name) {
        name = name.replace('+', ' ');
        List<Prof> results= this.profsService.listCompanies(name);
        return Response.status(200).entity(results).build();
    }

    @GET()
    @Path("/jobTitles")
    public Response getJobTitles() {
        Set<String> jobTitles = this.profsService.listJobTitles();
        return Response.status(200).entity(jobTitles).build();
    }

    @GET()
    @Path("/jobTitles/{name}")
    public Response getJobTitles(@PathParam("name") String name) {
        name = name.replace('+', ' ');
        List<Prof> results= this.profsService.listJobTitles(name);
        return Response.status(200).entity(results).build();
    }
}
