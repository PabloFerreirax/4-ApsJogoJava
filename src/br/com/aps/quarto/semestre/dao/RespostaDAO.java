package br.com.aps.quarto.semestre.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.aps.quarto.semestre.modelo.Combate;
import br.com.aps.quarto.semestre.modelo.Fase;
import br.com.aps.quarto.semestre.modelo.Resposta;
import javax.persistence.criteria.CriteriaDelete;

public class RespostaDAO extends DAO<Resposta>{

	private EntityManager em;
	
	public RespostaDAO( EntityManager em) {
		super(Resposta.class, em);
		this.em = em;
	}

	public List<Resposta> getByFase(Fase fase) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		
		CriteriaQuery<Resposta> criteria = builder.createQuery(Resposta.class);
		
		Root<Resposta> root = criteria.from(Resposta.class);
		
		Expression<Fase> expression = root.get("fase");
		Predicate predicate = builder.equal(expression, fase);
		
		criteria.where(predicate);
		
		return em.createQuery(criteria).getResultList();
		
	}
    public void deleteByFase(Fase fase){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        
        CriteriaDelete<Resposta> criteria = builder.createCriteriaDelete(Resposta.class);
        
        Root<Resposta> from = criteria.from(Resposta.class);
        
        Expression<Resposta> compoExpressao = from.get("fase");
        
        Predicate faseEqual = builder.equal(compoExpressao, fase);
        
        criteria.where(faseEqual);
        
        em.createQuery(criteria).executeUpdate();
    }
}
