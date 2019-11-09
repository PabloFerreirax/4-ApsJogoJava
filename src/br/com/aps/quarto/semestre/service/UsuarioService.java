/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aps.quarto.semestre.service;

import br.com.aps.quarto.semestre.dao.DAO;
import br.com.aps.quarto.semestre.dao.FaseDAO;
import br.com.aps.quarto.semestre.dao.ProgressoDAO;
import br.com.aps.quarto.semestre.dao.UsuarioDAO;
import br.com.aps.quarto.semestre.exception.ValorInvalidoException;
import br.com.aps.quarto.semestre.modelo.Progresso;
import br.com.aps.quarto.semestre.modelo.Usuario;
import br.com.aps.quarto.semestre.util.JPAUtil;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.jasper.tagplugins.jstl.core.If;

/**
 *
 * @author tsdev04
 */
public class UsuarioService {
    public void save(Usuario usuario){
        EntityManager em = JPAUtil.getEntityManager();
		
        try {
            em.getTransaction().begin();
            
            checkUsuario(usuario);
            
            new UsuarioDAO(em).insert(usuario);
            
            em.getTransaction().commit();
        }
        catch (ValorInvalidoException e) {
			em.getTransaction().rollback();
			throw new ValorInvalidoException(e);
		}
        catch(PersistenceException e){
            throw new RuntimeException("Erro de sql. Detalhe: "+getCause(e).getMessage());
        }
		catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException("Não foi possível salvar o usu�rio causado por um erro desconhecido. Detalhe"+e);
		}
		finally {
			em.close();
		}
		
    }
    
    public void save(Progresso progresso){
        EntityManager em = JPAUtil.getEntityManager();
		
        try {
            em.getTransaction().begin();

            checkProgresso(progresso);
            
            new ProgressoDAO(em).insert(progresso);

            em.getTransaction().commit();
        }
        catch (ValorInvalidoException e) {
			em.getTransaction().rollback();
			throw new ValorInvalidoException(e);
		}
        catch(PersistenceException e){
            throw new RuntimeException("Erro de sql. Detalhe: "+getCause(e).getMessage());
        }
		catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException("Não foi possível salvar o usu�rio causado por um erro desconhecido. Detalhe"+e);
		}
		finally {
			em.close();
		}
		
    }
    
    public boolean login(String nick, String senha){
        EntityManager em = JPAUtil.getEntityManager();

        try {
        	UsuarioDAO dao = new UsuarioDAO(em);
        	
        	boolean existe = dao.login(nick, senha);
        	
            return existe;
        }catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao realizar o login",e);
        }
        finally {
            em.close();
        }
    }
    
    public List<Usuario> getAll(){
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return new UsuarioDAO(em).getAll();
        }catch (Exception e) {
            throw new RuntimeException("Não foi possível obter as fases",e);
        }
        finally {
            em.close();
        }
    }
    
    public void update(Usuario usuario){
        EntityManager em = JPAUtil.getEntityManager();
		
        try {
            em.getTransaction().begin();
            
            checkUsuario(usuario);
            
            if(usuario.getIdUsuario()==null)
                throw new ValorInvalidoException("O usuário não existe, pois o id está nulo");
                
            new UsuarioDAO(em).merge(usuario);
            
            em.getTransaction().commit();
        }
        catch (ValorInvalidoException e) {
			em.getTransaction().rollback();
			throw new ValorInvalidoException(e);
		}
        catch(PersistenceException e){
            throw new RuntimeException("Erro de sql. Detalhe: "+getCause(e).getMessage());
        }
		catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException("Não foi possível salvar o usu�rio causado por um erro desconhecido. Detalhe"+e);
		}
		finally {
			em.close();
		}
		
    }
    
    public void update(Progresso progresso){
        EntityManager em = JPAUtil.getEntityManager();
		
        try {
            em.getTransaction().begin();
            
            checkProgresso(progresso);
            
            if(progresso.getIdProgresso()==null)
                throw new ValorInvalidoException("O progresso não existe, pois o id está nulo");
                
            new ProgressoDAO(em).merge(progresso);
            
            em.getTransaction().commit();
        }
        catch (ValorInvalidoException e) {
			em.getTransaction().rollback();
			throw new ValorInvalidoException(e);
		}
        catch(PersistenceException e){
            throw new RuntimeException("Erro de sql. Detalhe: "+getCause(e).getMessage());
        }
		catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException("Não foi possível salvar o usu�rio causado por um erro desconhecido. Detalhe"+e);
		}
		finally {
			em.close();
		}
		
    }
    
    public void delete(Usuario usuario){
        EntityManager em = JPAUtil.getEntityManager();
		
        try {
            em.getTransaction().begin();
            
            checkUsuario(usuario);
            
            new ProgressoDAO(em).deleteByUsuario(usuario);
            
            new UsuarioDAO(em).delete(usuario);
            
            em.getTransaction().commit();
        }
        catch (ValorInvalidoException e) {
            em.getTransaction().rollback();
            throw new ValorInvalidoException(e);
        }
        catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Não foi possível apagar o usuário causado por um erro desconhecido",e);
        }
        finally {
            em.close();
        }
    }
    
    public void delete(Progresso progresso){
        EntityManager em = JPAUtil.getEntityManager();
		
        try {
            em.getTransaction().begin();

            checkProgresso(progresso);
            
            new ProgressoDAO(em).delete(progresso);

            em.getTransaction().commit();
        }
        catch (ValorInvalidoException e) {
            em.getTransaction().rollback();
            throw new ValorInvalidoException(e);
        }
        catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Não foi possível apagar o progresso causado por um erro desconhecido",e);
        }
        finally {
            em.close();
        }
    }
    
    public boolean existProgresso(Usuario usuario) {
    	EntityManager em = JPAUtil.getEntityManager();

        try {
            return new ProgressoDAO(em).existByUsuario(usuario);
        }catch (Exception e) {
            throw new RuntimeException("Não foi possível obter as fases",e);
        }
        finally {
            em.close();
        }
    }
    
    public Progresso getProgressoByUsuario(Usuario usuario) {
    	
    	EntityManager em = JPAUtil.getEntityManager();

        try {
        	Progresso progresso = null;
    		//VERIFICAR SE N�O EXISTE PROGRESSO
    		
			boolean continuar = true;
			
			while (continuar) {
				if(existProgresso(usuario)) {
    				progresso = new ProgressoDAO(em).getByUsuario(usuario);
    				
    				if(progresso.getFase().getAtivo()) {
    					continuar = false;
    				}
    				else {
    					delete(progresso);
    					progresso = null;
    				}
				}else {
					continuar = false;
				}
			}
			
			if(progresso==null)
				progresso = criarProgresso(em, usuario);
			
    		
    		return progresso; 
        	
        }catch (Exception e) {
            throw new RuntimeException("N�o foi poss�vel obter o progresso do usu�rio",e);
        }
        finally {
            em.close();
        }
    	
    }
    
    private Progresso criarProgresso(EntityManager em, Usuario usuario) {
    	
    	Progresso progresso = new Progresso();
		progresso.setUsuario(usuario);
		progresso.setFase(new FaseDAO(em).getFirst());
		progresso.setQtdPontos(0);
		progresso.setDataProgresso(new Date());
		
		save(progresso);
		
		return progresso;
    }
    
    public Usuario getUsuarioByNickName(String nickname) {
    	EntityManager em = JPAUtil.getEntityManager();

        try {
        	return new UsuarioDAO(em).getByNickName(nickname);
        }catch (Exception e) {
            throw new RuntimeException("N�o foi poss�vel obter o usu�rio",e);
        }
        finally {
            em.close();
        }
    }
    
    private void checkUsuario(Usuario usuario){
    	
    }
    
    private void checkProgresso(Progresso progresso){
    
    }
    
    private Throwable getCause(Throwable e) {
        Throwable cause = null; 
        Throwable result = e;

        while(null != (cause = result.getCause())  && (result != cause) ) {
            result = cause;
        }
        return result;
    }
}
