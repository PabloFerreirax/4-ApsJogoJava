package br.com.aps.quarto.semestre.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;

import br.com.aps.quarto.semestre.modelo.Dialogo;
import br.com.aps.quarto.semestre.modelo.Fase;
import javax.persistence.criteria.CriteriaDelete;

public class DialogoDAO extends DAO<Dialogo> {

    private EntityManager em;

    public DialogoDAO(EntityManager em) {
            super(Dialogo.class, em);

            this.em = em;
    }

    public List<Dialogo> getByFase(Fase fase){
            List<Dialogo> list = new ArrayList<Dialogo>();

            //ELE CRIA A QUERY
            CriteriaBuilder builder = em.getCriteriaBuilder();

            //ELE ARMAZENA A QUERY
            CriteriaQuery<Dialogo> criteria = builder.createQuery(Dialogo.class);

            //INFORMA A ENTIDADE QUE VAI ESTAR NO FROM DO SELECT (vai fazer .... from Dialogo)
            Root<Dialogo> from = criteria.from(Dialogo.class);

            //INFORMA O CAMPO QUE VAI SER FEITO A EXPRESSÃO (vai fazer ... WHERE fase) 
            Expression<Fase> compoExpressao = from.get("fase");
            //VAI MONTAR O PEDICATO ... WHERE fase = :fase (e substitui a :fase pela fase informado no pâmetro do método)
            Predicate faseEqual = builder.equal(compoExpressao, fase);

            //COLOCAR O PREDICATO NO WHERE DA CONSULTA
            criteria.where(faseEqual);

            return em.createQuery(criteria).getResultList();
    }
        
    public void deleteByFase(Fase fase){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        
        CriteriaDelete<Dialogo> criteria = builder.createCriteriaDelete(Dialogo.class);
        
        Root<Dialogo> from = criteria.from(Dialogo.class);
        
        Expression<Fase> compoExpressao = from.get("fase");
        
        Predicate faseEqual = builder.equal(compoExpressao, fase);
        
        criteria.where(faseEqual);
        
        em.createQuery(criteria).executeUpdate();
    }
}
