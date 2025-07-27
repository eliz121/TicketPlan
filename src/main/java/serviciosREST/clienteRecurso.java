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

import modelo.JPA.impl.JPAClienteDAO;
import modelo.dao.ClienteDAO;
import modelo.entities.Cliente;

@Path("/cliente")
public class clienteRecurso {
	
	private ClienteDAO dao;
	
    public clienteRecurso() {
        dao = new JPAClienteDAO();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> extraerClientes(){
        return dao.extraerClientes();
    }
    
    @Path("/{cedula}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente obtenerInformacionCliente(@PathParam("cedula") String cedula) {
    	return dao.obtenerInformacionCliente(cedula);
    }
    
      
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean guardarCliente(Cliente cliente){
    	return dao.guardarCliente(cliente);
    }
    
    @Path("/update")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean actualizarCliente(Cliente cliente) {
    	return dao.actualizarCliente(cliente);
    }
    
    @Path("/delete/{cedula}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public boolean eliminarCliente(@PathParam("cedula") String cedula) throws Exception {
    	return dao.eliminarCliente(cedula);
    }

}
