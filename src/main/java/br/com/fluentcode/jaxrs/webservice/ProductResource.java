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
import br.com.fluentcode.jaxrs.dto.ProductDTO;
import br.com.fluentcode.jaxrs.entity.Product;
import br.com.fluentcode.jaxrs.util.HibernateUtil;
import br.com.fluentcode.jaxrs.util.JaxbUtil;

@Path("/product")
public class ProductResource {

	/**
	 * TODO Remover o DTO e pegar o cara do banco
	 * 
	 * Produces xml. The DTO should be annotated with JAXB @XmlRootElement.
	 */
	@GET
	@Path("/{ id }/xml")
	@Produces(MediaType.APPLICATION_XML)
	public ProductDTO getProductAsXML(@PathParam("id") int id) {
		return new ProductDTO(id, "refrigerator", 1499.99);
	}

	/**TODO Remover o DTO e pegar o cara do banco
	 * 
	 * Produces json.
	 */
	@GET
	@Path("/{ id }/json")
	@Produces(MediaType.APPLICATION_JSON)
	public ProductDTO getProductAsJSON(@PathParam("id") int id) {
		return new ProductDTO(id, "refrigerator", 1499.99);
	}

	/**
	 * 
	 * Request example:
	 * 
	 * curl -v -H "Content-Type: application/xml" -d
	 * "<product><name>refrigerator</name><price>1499.99</price></product>"
	 * localhost:8080/jaxrs-example/resources/product/
	 * 
	 */
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response insert(String xml) {
		// unmarshal
		Product product = new JaxbUtil<Product>().unmarshal(xml, Product.class);

		// insert
		Session session = HibernateUtil.getSessionFactory().openSession();
		ProductDAO dao = new ProductDAO(session);
		session.beginTransaction();
		dao.insert(product);
		session.getTransaction().commit();
		session.close();

		// location
		URI location = URI.create("/product/" + product.getId());

		return Response.created(location).build();
	}

}
