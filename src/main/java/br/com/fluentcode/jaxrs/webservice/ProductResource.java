package br.com.fluentcode.jaxrs.webservice;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;

import br.com.fluentcode.jaxrs.dao.ProductDAO;
import br.com.fluentcode.jaxrs.entity.Product;
import br.com.fluentcode.jaxrs.util.HibernateUtil;

@Path("/product")
public class ProductResource {

	/**
	 * 
	 * Produces XML. For the JAX-RS API to the parser, the Product must be
	 * annotated with JAXB @XmlRootElement.
	 * 
	 * <p>
	 * Request example:
	 * </p>
	 * 
	 * curl -v http://localhost:8080/jaxrs-example/resources/product/1
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Product find(@PathParam("id") Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		ProductDAO dao = new ProductDAO(session);
		return dao.findById(id);
	}

	/**
	 * 
	 * Consumes XML. For the JAX-RS API to the parser, the Product must be
	 * annotated with JAXB @XmlRootElement.
	 * 
	 * <p>
	 * Request example:
	 * </p>
	 * 
	 * curl -v -H "Content-Type: application/xml" -d
	 * {@code"<product><name>refrigerator</name><price>1499.99</price></product>"}
	 * http://localhost:8080/jaxrs-example/resources/product
	 * 
	 */
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response insert(Product product) {
		// insert
		Session session = HibernateUtil.getSessionFactory().openSession();
		ProductDAO dao = new ProductDAO(session);
		session.beginTransaction();
		dao.insert(product);
		session.getTransaction().commit();
		session.close();

		// return status code 201 created and the product location
		URI location = URI.create("/product/" + product.getId());

		return Response.created(location).build();
	}
	
	/**
	 * <p>
	 * Request example:
	 * </p>
	 * 
	 * curl -v -X "DELETE" http://localhost:8080/jaxrs-example/resources/product/1
	 * 
	 */
	@DELETE
	@Path("/{id}")
	public Response remove(@PathParam("id") Integer id){
		// delete
		Session session = HibernateUtil.getSessionFactory().openSession();
		ProductDAO dao = new ProductDAO(session);
		session.beginTransaction();
		dao.delete(new Product(id));
		session.getTransaction().commit();
		session.close();
		
		// return status code 200 ok
		return Response.ok().build();
	}
	
	/**
	 * 
	 * Consumes XML. For the JAX-RS API to the parser, the Product must be
	 * annotated with JAXB @XmlRootElement.
	 * 
	 * <p>
	 * Request example:
	 * </p>
	 * 
	 * curl -v -X "PUT" -H "Content-Type: application/xml" -d
	 * {@code"<product><id>1</id><name>Notebook</name><price>3699.99</price></product>"}
	 * http://localhost:8080/jaxrs-example/resources/product
	 * 
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response update(Product product) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		ProductDAO dao = new ProductDAO(session);
		session.beginTransaction();
		dao.merge(product);
		session.getTransaction().commit();
		session.close();
		
		// return status code 200 ok
		return Response.ok().build();
	}

}
