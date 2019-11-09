/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aps.quarto.semestre.apsjogo;

import br.com.aps.quarto.semestre.dao.FaseDAO;
import br.com.aps.quarto.semestre.modelo.Fase;
import br.com.aps.quarto.semestre.modelo.Final;
import br.com.aps.quarto.semestre.modelo.TipoFase;
import br.com.aps.quarto.semestre.util.JPAUtil;
import javax.persistence.EntityManager;

/**
 *
 * @author tsdev04
 */
public class Teste {
    public static void main (String[] args){
        try{
            EntityManager em = JPAUtil.getEntityManager();
           /* 
            Fase f = new Fase();
            
            f.setDsFase("desc");
            f.setSeqFase(1);
            f.setTipoFase(TipoFase.PERGUNTA);
            */
            
            System.out.println(new FaseDAO(em).getAll().get(0));
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
