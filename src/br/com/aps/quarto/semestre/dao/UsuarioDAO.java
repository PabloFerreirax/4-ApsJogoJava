/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aps.quarto.semestre.dao;

import br.com.aps.quarto.semestre.modelo.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

/**
 *
 * @author tsdev04
 */
public class UsuarioDAO extends DAO<Usuario>{

    private EntityManager em;
    
    public UsuarioDAO(EntityManager em) {
        super(Usuario.class, em);
        
        this.em = em;
    }
    
    public boolean login(String nick, String senha){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        
        CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
        
        Root<Usuario> from = criteria.from(Usuario.class);
        
        Expression eNickname = from.get("nickname");
        Expression eSenha = from.get("senha");
        
        criteria.where(builder.equal(eNickname, nick), builder.equal(eSenha, senha));
        
        return em.createQuery(criteria).getResultList().size()>0;
    }
    
    public Usuario getByNickName(String nick){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        
        CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
        
        Root<Usuario> from = criteria.from(Usuario.class);
        
        Expression eNickname = from.get("nickname");
        
        criteria.where(builder.equal(eNickname, nick));
        
        return em.createQuery(criteria).getSingleResult();
    }
}
