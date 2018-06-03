/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entita.Immagine;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

/**
 *
 * @author mauro
 */
@Stateless
@Path("immagine")
public class ImmagineFacadeREST extends AbstractFacade<Immagine> {

    private static final Logger _log = Logger.getLogger(ImmagineFacadeREST.class.getName());

    @PersistenceContext(unitName = "prova3PU")
    private EntityManager em;

    public ImmagineFacadeREST() {
        super(Immagine.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Immagine entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Immagine entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Immagine find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Immagine> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Immagine> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
       @GET
    @Path("/get-image-by-name-as-stream/{name}")
    public Response getImageByNameAsStream(@PathParam("name") String name) {
    	final byte[] imageAsArrayOfByte;
    	try {
			_log.info("Get image by name as stream : " + name);
                        
                Query q=em.createNamedQuery("Immagine.findByName");
                //q.setParameter(1, name);
                q.setParameter("name", name);
                Immagine immagine=(Immagine) q.getSingleResult();
                
			imageAsArrayOfByte = immagine.getByteImmagine();
			return Response.ok(new StreamingOutput() {
				
				@Override
				public void write(OutputStream os) throws IOException,
						WebApplicationException {
					os.write(imageAsArrayOfByte);
				}
			}).build();
		} catch (RuntimeException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new String(e.getMessage())).build();
		}
    }
     @GET
    @Path("/get-image-by-id-as-stream/{id}")
    public Response getImageByIdAsStream(@PathParam("id")  Long id) {
    	final byte[] imageAsArrayOfByte;
    	try {
			_log.info("Get image by id as stream : " + id);
                      
                
                Immagine immagine=this.find(id);
               
			imageAsArrayOfByte = immagine.getByteImmagine();
			return Response.ok(new StreamingOutput() {
				
				@Override
				public void write(OutputStream os) throws IOException,
						WebApplicationException {
					os.write(imageAsArrayOfByte);
				}
			}).build();
		} catch (RuntimeException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new String(e.getMessage())).build();
		}
    }
    
}
