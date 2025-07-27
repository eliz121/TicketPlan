package serviciosREST;


import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import modelo.JPA.impl.JPAEventoDAO;
import modelo.dao.EventoDAO;
import modelo.entities.Evento;

@Path("/eventos")
public class eventoRecurso {
    private EventoDAO dao;

    public eventoRecurso() {
        dao = new JPAEventoDAO();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Evento> getEventos() {
        return dao.obtenerEventos();
    }
    
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Evento getEventoByPathParam(@PathParam("id") int id) {
        return dao.obtenerEvento(id);
    }
    
    @Path("/query")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Evento getEventoByQueryParam(@QueryParam("id") int id) {
        return dao.obtenerEvento(id);
    }
    
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean guardarEvento(Evento e) {
        return dao.guardarEvento(e);
    }
    
    @Path("/update")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean actualizarEvento(Evento e) {
        return dao.actualizarEvento(e);
    }
    
    @Path("/delete/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public boolean eliminarEvento(@PathParam("id") int id) throws Exception {
        return dao.eliminarEvento(id);
    }
}
