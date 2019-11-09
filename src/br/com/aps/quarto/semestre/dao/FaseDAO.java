package br.com.aps.quarto.semestre.dao;

import javax.persistence.EntityManager;

import br.com.aps.quarto.semestre.modelo.Fase;
import br.com.aps.quarto.semestre.modelo.TipoFase;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

public class FaseDAO extends DAO<Fase>{

    private EntityManager em;
    
    public FaseDAO(EntityManager em) {
            super(Fase.class, em);
            // TODO Auto-generated constructor stub
            this.em = em;
    }
    
    public Fase getFirst() {
    	String jspl = "select f from Fase f where f.seqFase = (select min(f2.seqFase) from Fase f2 where f2.tipoFase <> :tipo and f2.ativo = true)";
    	
    	return em.createQuery(jspl, Fase.class).setParameter("tipo", TipoFase.FINAL).getSingleResult();
    }
    
    public boolean existNext(Fase fase) {
    	String jspl = "select f.idFase from Fase f where f.seqFase = (select min(f2.seqFase) from Fase f2 where f2.tipoFase <> :tipo and f2.seqFase > :seqAtual and f2.ativo = true)";
    	
    	return em.createQuery(jspl ).setParameter("tipo", TipoFase.FINAL).setParameter("seqAtual", fase.getSeqFase()).getResultList().size()>0;
    
    }
    
    public Fase getNext(Fase fase) {
    	String jspl = "select f from Fase f where f.seqFase = (select min(f2.seqFase) from Fase f2 where f2.tipoFase <> :tipo and f2.seqFase > :seqAtual and f2.ativo = true)";
    	
    	return em.createQuery(jspl, Fase.class).setParameter("tipo", TipoFase.FINAL).setParameter("seqAtual", fase.getSeqFase()).getSingleResult();
    }
    
    @Override
    public void delete(Fase fase){
        fase.setAtivo(false);
        em.merge(fase);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Fase> getAll(){

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Fase> criteria = builder.createQuery(Fase.class);
            Root<Fase> root = criteria.from(Fase.class);
            
            Expression expression = root.get("ativo");
            
            criteria.where(builder.equal(expression, true));

            return em.createQuery(criteria).getResultList();
    }
    

}
