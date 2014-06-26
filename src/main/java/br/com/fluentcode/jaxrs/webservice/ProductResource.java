package br.com.fluentcode.jaxrs.webservice;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
	 * Produces XML, For the JAX-RS API to the parser, the Product must be
	 * annotated with JAXB @XmlRootElement.
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
	 * "<product><name>refrigerator</name><price>1499.99</price></product>"
	 * localhost:8080/jaxrs-example/resources/product
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

}
