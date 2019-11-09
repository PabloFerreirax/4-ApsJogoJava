package br.com.aps.quarto.semestre.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class DAO<E> {

	private Class classe;
	private EntityManager em;

	public DAO(Class classe, EntityManager em) {
		this.classe = classe;
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public E getById(Long id) {
		return (E) em.find(classe, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<E> getAll(){
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<E> criteria = builder.createQuery(classe);
		criteria.from(classe);
		
		return em.createQuery(criteria).getResultList();
	}
	
	public void merge(E e){
		em.merge(e);
	}
	
	public void insert(E e) {
		em.persist(e);
	}
	
	public void delete(E e) {
		em.remove(em.merge(e));
	}
	
}
