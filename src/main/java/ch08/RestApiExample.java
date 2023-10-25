package ch08;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class RestApiExample {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello(){
        return "Hello API result - text_plain";
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    public String sayHello(@QueryParam("msg") String msg){
        return "<h1>" + msg + " API result - text_html<h1>";
    }
}
