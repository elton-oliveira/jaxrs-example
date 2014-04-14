package br.com.fluentcode.jaxrs.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.fluentcode.jaxrs.dto.ProductDTO;

@Path ("/product")
public class ProductResource {

	/**
	 * 
	 * Produces xml.
	 * The DTO should be annotated with JAXB @XmlRootElement.
	 */
	@GET
	@Path("/{ id }/xml")
	@Produces(MediaType.APPLICATION_XML)
	public ProductDTO getProductAsXML(@PathParam("id") int id) {
		return new ProductDTO(id, "geradeira", 1499.99);
	}

	/**
	 * 
	 * Produces json.
	 * The DTO should be annotated with JAXB @XmlRootElement.
	 */
	@GET
	@Path("/{ id }/json")
	@Produces(MediaType.APPLICATION_JSON)
	public ProductDTO getProductAsJSON(@PathParam("id") int id) {
		return new ProductDTO(id, "geradeira", 1499.99);
	}

}
