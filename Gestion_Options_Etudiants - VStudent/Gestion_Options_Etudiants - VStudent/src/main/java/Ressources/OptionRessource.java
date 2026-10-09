package Ressources;


import entities.Option;
import metiers.OptionBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("options")
public class OptionRessource {
    OptionBusiness optionBusiness = new OptionBusiness();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Option> getList(){

        return optionBusiness.getListeOptions();
    }
    @GET
    @Path("{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListByID (@PathParam("code") int code ){
        Option opt=optionBusiness.getOptionByCode(code);
        if(opt!=null)
            return Response.status(Response.Status.OK).entity(opt).build();
            return Response.status(Response.Status.NOT_FOUND).entity("opt not found ").build();
    }
    @PUT
    @Path("{code}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOption(@PathParam("code")int code,Option opt ){
        if (optionBusiness.getOptionByCode(code)!=null){
            optionBusiness.updateOption(code,opt);
            return  Response.status(Response.Status.OK).entity("added with succes").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("echec dajout").build();
    }
}
