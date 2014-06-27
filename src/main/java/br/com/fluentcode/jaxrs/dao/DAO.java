package br.com.fluentcode.jaxrs.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

public class DAO<T>{
	
	private final Session session;
	private final Class<T> classe;
	
	public DAO(Session session, Class<T> classe){
		this.session = session;
		this.classe = classe;
	}
	
	@SuppressWarnings("unchecked")
	public T findById(Serializable id){
		return (T) session.get(classe, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(){
		Criteria criteria = session.createCriteria(this.classe);
		return criteria.list();
	}
	
	public void insert(T t){
		session.persist(t);
	}
	
	public void delete(T t){
		session.delete(t);
	}

	@SuppressWarnings("unchecked")
	public T merge(T t) {
		return (T) this.session.merge(t);
	}
	

}
