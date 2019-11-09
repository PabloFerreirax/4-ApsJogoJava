package br.com.aps.quarto.semestre.dao;

import br.com.aps.quarto.semestre.modelo.Combate;
import br.com.aps.quarto.semestre.modelo.Fase;
import javax.persistence.EntityManager;

import br.com.aps.quarto.semestre.modelo.Final;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class FinalDAO extends DAO<Final>{

    private EntityManager em;
	public FinalDAO(EntityManager em) {
		super(Final.class, em);
                
                this.em = em;
		// TODO Auto-generated constructor stub
	}

    public void deleteByFase(Fase fase){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        
        CriteriaDelete<Final> criteria = builder.createCriteriaDelete(Final.class);
        
        Root<Final> from = criteria.from(Final.class);
        
        Expression<Combate> compoExpressao = from.get("idFase");
        
        Predicate faseEqual = builder.equal(compoExpressao, fase.getIdFase());
        
        criteria.where(faseEqual);
        
        em.createQuery(criteria).executeUpdate();
    }
    
    public Final getByPontos(int ponto) {
    	String jpql = "SELECT f FROM Final f WHERE :ponto BETWEEN f.intervaloInf AND f.intervaloSup";
    	
    	return em.createQuery(jpql, Final.class).setParameter("ponto", ponto).setMaxResults(1).getSingleResult();
    }
    
    
    @Override
    public void delete(Final fim){
        fim.setAtivo(false);
        
        em.merge(fim);
    }
}
