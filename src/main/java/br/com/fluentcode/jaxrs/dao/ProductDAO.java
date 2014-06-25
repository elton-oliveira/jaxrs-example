package br.com.fluentcode.jaxrs.dao;

import org.hibernate.Session;

import br.com.fluentcode.jaxrs.entity.Product;

public class ProductDAO {
	
	private final DAO<Product> dao;
	
	public ProductDAO(Session session) {
		dao = new DAO<Product>(session, Product.class);
	}
	
	public void insert(Product document){
		this.dao.insert(document);
	}

}
