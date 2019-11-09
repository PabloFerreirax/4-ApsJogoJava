/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aps.quarto.semestre.dao;

import br.com.aps.quarto.semestre.modelo.Fase;
import br.com.aps.quarto.semestre.modelo.Progresso;
import br.com.aps.quarto.semestre.modelo.Resposta;
import br.com.aps.quarto.semestre.modelo.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author tsdev04
 */
public class ProgressoDAO extends DAO<Progresso>{

    private EntityManager em;
    
    public ProgressoDAO(EntityManager em) {
        super(Progresso.class, em);
        
        this.em = em;
    }
    
    public void deleteByUsuario(Usuario usuario){
     
        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaDelete<Progresso> criteria = builder.createCriteriaDelete(Progresso.class);

        Root<Progresso> from = criteria.from(Progresso.class);

        Expression<Progresso> compoExpressao = from.get("usuario");

        Predicate faseEqual = builder.equal(compoExpressao, usuario);

        criteria.where(faseEqual);

        em.createQuery(criteria).executeUpdate();
    
    }
    
    public Progresso getByUsuario(Usuario usuario){
        
        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Progresso> criteria = builder.createQuery(Progresso.class);

        Root<Progresso> from = criteria.from(Progresso.class);

        Expression<Progresso> compoExpressao = from.get("usuario");

        Predicate faseEqual = builder.equal(compoExpressao, usuario);

        criteria.where(faseEqual);
        
        criteria.orderBy(builder.desc(from.get("dataProgresso")));

        return em.createQuery(criteria).setMaxResults(1).getSingleResult();
    
    }
    
    
    public boolean existByUsuario(Usuario usuario) {
    	     
    	CriteriaBuilder builder = em.getCriteriaBuilder();
		
		CriteriaQuery<Progresso> criteria = builder.createQuery(Progresso.class);
		
		Root<Progresso> root = criteria.from(Progresso.class);
		
		Expression<Usuario> expression = root.get("usuario");
		Predicate predicate = builder.equal(expression, usuario);
		
		criteria.where(predicate);
		
		return em.createQuery(criteria).getResultList().size()>0;
    }
    
}
