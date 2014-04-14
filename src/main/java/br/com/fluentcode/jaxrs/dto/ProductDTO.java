package br.com.fluentcode.jaxrs.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * The JAX-RS specification uses JAXB to generate XML and JSON. 
 * The principle JAXB specification allows only produce XML. However, 
 * JAXB architecture is flexible and can easily be extended to also produce JSON.
 *
 */
@XmlRootElement
public class ProductDTO {
	
	private Integer id;
	
	private String name;
	
	private Double price;
	
	public ProductDTO(){}
	
	public ProductDTO(Integer id, String name, Double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
}
