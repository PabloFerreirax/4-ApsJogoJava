package br.com.aps.quarto.semestre.dao;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.aps.quarto.semestre.modelo.Combate;
import br.com.aps.quarto.semestre.modelo.Dialogo;
import br.com.aps.quarto.semestre.modelo.Fase;
import javax.persistence.criteria.CriteriaDelete;

public class CombateDAO extends DAO<Combate>{

	private EntityManager em;
	
	public CombateDAO( EntityManager em) {
		super(Combate.class, em);
		this.em = em;
	}
	
	public Combate getByFase(Fase fase) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		
		CriteriaQuery<Combate> criteria = builder.createQuery(Combate.class);
		
		Root<Combate> root = criteria.from(Combate.class);
		
		Expression<Fase> expression = root.get("fase");
		Predicate predicate = builder.equal(expression, fase);
		
		criteria.where(predicate);
		
		return em.createQuery(criteria).getSingleResult();
		
	}

    public void deleteByFase(Fase fase){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        
        CriteriaDelete<Combate> criteria = builder.createCriteriaDelete(Combate.class);
        
        Root<Combate> from = criteria.from(Combate.class);
        
        Expression<Combate> compoExpressao = from.get("fase");
        
        Predicate faseEqual = builder.equal(compoExpressao, fase);
        
        criteria.where(faseEqual);
        
        em.createQuery(criteria).executeUpdate();
    }
}
