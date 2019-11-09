package br.com.aps.quarto.semestre.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;

import br.com.aps.quarto.semestre.dao.CombateDAO;
import br.com.aps.quarto.semestre.dao.DAO;
import br.com.aps.quarto.semestre.dao.DialogoDAO;
import br.com.aps.quarto.semestre.dao.FaseDAO;
import br.com.aps.quarto.semestre.dao.FinalDAO;
import br.com.aps.quarto.semestre.dao.RespostaDAO;
import br.com.aps.quarto.semestre.exception.ValorInvalidoException;
import br.com.aps.quarto.semestre.modelo.Combate;
import br.com.aps.quarto.semestre.modelo.Dialogo;
import br.com.aps.quarto.semestre.modelo.Fase;
import br.com.aps.quarto.semestre.modelo.Final;
import br.com.aps.quarto.semestre.modelo.Resposta;
import br.com.aps.quarto.semestre.modelo.TipoFase;
import br.com.aps.quarto.semestre.util.JPAUtil;
import javax.persistence.PersistenceException;
import org.hibernate.HibernateException;

public class FaseService {

	public void save(Fase fase, List<Dialogo> dialogos, Combate combate) {
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			em.getTransaction().begin();

            //VERIFICANDO SE EXISTE ALGO DE ERRADO COM O COMBATE INFORMADO
            checkCombate(fase, combate);
            
            //SALVANDO A FASE E OS DIALOGOS
			saveFaseDialogos(em, fase, dialogos);
            
            //SALVANDO O COMBATE
            combate.setFase(fase);
			new CombateDAO(em).insert(combate);
			
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
			throw new RuntimeException("NÃ£o foi possÃ­vel salvar a fase causado por um erro desconhecido. Detalhe"+e);
		}
		finally {
			em.close();
		}
		
	}
	
	public void save(Fase fase, List<Dialogo> dialogos, List<Resposta> respostas) {
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			em.getTransaction().begin();
			
            //VERIFIVANDO SE EXISTE ALGO DE ERRADO COM AS RESPOSTAS
            checkResposta(fase, respostas);
            
            //SALVANDO A FASE E OS DIALOGOS NO BANCO DE DADOS
			saveFaseDialogos(em, fase, dialogos);
                        
                        //SALVANDO AS RESPOSTAS NO BANCO DE DADOS
			RespostaDAO respostaDAO = new RespostaDAO(em);
			
			for (Resposta resposta : respostas) {
                                resposta.setFase(fase);
				respostaDAO.insert(resposta);
			}
			
			em.getTransaction().commit();
			
		}catch (ValorInvalidoException e) {
			em.getTransaction().rollback();
			throw new ValorInvalidoException(e);
		}
        catch(PersistenceException e){
            throw new RuntimeException("Erro de sql. Detalhe: "+getCause(e).getMessage());
        }
		catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException("NÃ£o foi possÃ­vel salvar a fase causado por um erro desconhecido. Detalhe"+e);
		}
		finally {
			em.close();
		}
		
	}
	
	private void saveFaseDialogos(EntityManager em, Fase fase, List<Dialogo> dialogos) throws Exception {
		//VERIFICANDO SE TEM ALGO ERRADO COM OS PARÃ‚METROS INFORMADOS
		checkFase(fase, dialogos);
		
		new FaseDAO(em).insert(fase);
		
		saveDialogos(em, fase, dialogos);
	}
	
	public void save(Final fim, List<Dialogo> dialogos) {
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			em.getTransaction().begin();
            
            //VERIFICANDO SE TEM ALGO ERRADO COM OS PARÃ‚METROS INFORMADOS
            checkFase((Fase) fim, dialogos);
            
            //SALANDO O FINAL NO BANCO DE DADOS
			new FinalDAO(em).insert(fim);
                        
			saveDialogos(em, fim, dialogos);
			
			em.getTransaction().commit();
			
		}catch (ValorInvalidoException e) {
			em.getTransaction().rollback();
			throw new ValorInvalidoException(e);
		}
        catch(PersistenceException e){
            throw new RuntimeException("Erro de sql. Detalhe: "+getCause(e).getMessage());
        }
		catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException("NÃ£o foi possÃ­vel salvar o final causado por um erro desconhecido. Detalhe"+e);
		}
		finally {
			em.close();
		}
		
	}
	
	/**
        ESSE MÃ‰TODO SALVA OS DIÃ�LOGOS NO BANCO DE DADOS
        */
	private void saveDialogos(EntityManager em, Fase fase, List<Dialogo> dialogos) throws Exception {
		
		DialogoDAO dialogoDAO =  new DialogoDAO(em);
		
		int seq = 1;
		for (Dialogo dialogo : dialogos) {
            dialogo.setFase(fase);
            dialogo.setSeqDialogo(seq);
		    dialogoDAO.insert(dialogo);
		    seq++;
		}
	}
	
        
	public void update(Fase fase, List<Dialogo> dialogos, Combate combate) {
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			em.getTransaction().begin();
                        
            //VERIFICANDO SE O VALOR DO COMBATE INFORMADO ESTÃ� CERTO
            checkCombate(fase, combate);

            //APAGANDO OS DADOS ANTERIORES DE DIALOGO, RESPOSTAS E COMBATE CASO EXISTAM E SALVANDOOS DIALOGOS
			updateFaseDialogo(em, fase, dialogos);
	        
	        //SALVANDO O NOVO VALOR DO COMBATE
	        combate.setFase(fase);
			new CombateDAO(em).merge(combate);
			
			em.getTransaction().commit();
			
		}catch (ValorInvalidoException e) {
			em.getTransaction().rollback();
			throw new ValorInvalidoException(e);
		}	
        catch(PersistenceException e){
            throw new RuntimeException("Erro de sql. Detalhe: "+getCause(e).getMessage());
        }
		catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException("NÃ£o foi possÃ­vel alterar a fase causado por um erro desconhecido. Detalhe"+e);
		}
		finally {
			em.close();
		}
		
	}
	
	
	public void update(Fase fase, List<Dialogo> dialogos, List<Resposta> respostas) {
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			em.getTransaction().begin();
                        
            //VERIFICANDO SE AS RESPOSTAS ESTÃƒO CERTAS
            checkResposta(fase, respostas);

            //APAGANDO OS DADOS ANTERIORES DE DIALOGO, RESPOSTAS E COMBATE CASO EXISTAM E SALVANDOOS DIALOGOS
			updateFaseDialogo(em, fase, dialogos);
			
            //SALVANDO AS NOVAS RESPOSTAS
			RespostaDAO respostaDAO = new RespostaDAO(em);
			
			for (Resposta resposta : respostas) {
                                resposta.setFase(fase);
				respostaDAO.merge(resposta);
			}
			
			em.getTransaction().commit();
			
		}catch (ValorInvalidoException e) {
			em.getTransaction().rollback();
			throw new ValorInvalidoException(e);
		}
                catch(PersistenceException e){
                    throw new RuntimeException("Erro de sql. Detalhe: "+getCause(e).getMessage());
                }
		catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException("NÃ£o foi possÃ­vel alterar a fase causado por um erro desconhecido. Detalhe"+e);
		}
		finally {
			em.close();
		}
		
	}
	
	public void update(Final fim, List<Dialogo> dialogos) {
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			em.getTransaction().begin();
                        
            //VERIFICANDO SE TEM ALGO ERRADO COM OS PARÃ‚METROS INFORMADOS
            checkFase((Fase) fim, dialogos);

            //SE O ID ESTIVER NULO NÃƒO DEIXAR ATUALIZAR
            if(fim.getIdFase()==null)
                throw new Exception("O final informado nÃ£o existe, o id estÃ¡ nulo");
            
            //ATUALIZANDO O FINAL
			new FinalDAO(em).merge(fim);
                        
                        //APAGANDO OS DADOS ANTERIORES DE DIALOGO, RESPOSTAS E COMBATE CASO EXISTAM E SALVANDOOS DIALOGOS
                        updateFaseAll(em, fim, dialogos);
			
			em.getTransaction().commit();
			
		}catch (ValorInvalidoException e) {
			em.getTransaction().rollback();
			throw new ValorInvalidoException(e);
		}
                catch(PersistenceException e){
                    throw new RuntimeException("Erro de sql. Detalhe: "+getCause(e).getMessage());
                }
		catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException("NÃ£o foi possÃ­vel alterar o final causado por um erro desconhecido. Detalhe"+e);
		}
		finally {
			em.close();
		}
		
	}

	
	private void updateFaseDialogo(EntityManager em, Fase fase, List<Dialogo> dialogos) throws Exception {
        //VERIFICANDO SE TEM ALGO ERRADO COM OS PARÃ‚METROS INFORMADOS
        checkFase(fase, dialogos);
        
        //SALVANDO OS DIÃ�LOGOS E APAGANDO OS DADOS ANTIGOS DOS DIALOGOS, COMBATE E RESPOSTAS PARA INSERIR NOVOS
        updateFaseAll(em, fase, dialogos);
        
        //VERIFICANDO SE O ID ESTÃ� NULO
        if(fase.getIdFase()==null)
            throw new Exception("A fase nÃ£o existe, o id estÃ¡ nulo");
        
        //ATUALIZANDO A FASE
		new FaseDAO(em).merge(fase);
	}
	
        /**
          Aqui estÃ£o os comandos comuns em todos os updates seja Fase ou Final
        */
        private void updateFaseAll(EntityManager em, Fase fase, List<Dialogo> dialogos) throws Exception{
            
            //APAGANDO OS VALORES ANTIDOS DO BANCO DE DADOS PARA O COMBATE, REPOSTAS E DIALOGOS
            new CombateDAO(em).deleteByFase(fase);
            new RespostaDAO(em).deleteByFase(fase);
            new DialogoDAO(em).deleteByFase(fase);
            
            DialogoDAO dialogoDAO = new DialogoDAO(em);
            
            for(Dialogo dialogo: dialogos){
                dialogo.setFase(fase);
                dialogoDAO.merge(dialogo);
            }
        }
	
	public Fase getFaseById(Long id) {
		
		EntityManager em = JPAUtil.getEntityManager();
		try {
			return new FaseDAO(em).getById(id);
		}catch (Exception e) {
			throw new RuntimeException("Erro ao obter a fase. Detalhe: "+ e.getMessage());
		}finally {
			em.close();
		}
	}
	
	public Final getFinalById(Long id) {
		
		EntityManager em = JPAUtil.getEntityManager();
		try {
			return new FinalDAO(em).getById(id);
		}catch (Exception e) {
			throw new RuntimeException("Erro ao obter o final. Detalhe: "+ e);
		}finally {
			em.close();
		}
	}
	
	public List<Dialogo> getDialogosByFase(Fase fase){
		List<Dialogo> list = new ArrayList<Dialogo>();
		
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			list = new DialogoDAO(em).getByFase(fase);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao obter os diÃ¡logos da fase. Detalhe: "+ e);
		}
		finally {
			em.close();
		}
		
		return list;
	}
	
	public Combate getCombateByFase(Fase fase) {
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			if(fase.getTipoFase()!=TipoFase.COMBATE)
				throw new ValorInvalidoException("A FASE INFORMADA NÃƒO POSSUI COMBATE, APENAS AS FASES COM O TIPO 'COMBATE' POSSUEM COMBATE");
			
			return new CombateDAO(em).getByFase(fase);
		}catch (ValorInvalidoException e) {
			throw new ValorInvalidoException(e);
		}catch (Exception e) {
			throw new RuntimeException("Erro ao obter o combate da fase. Detalhe: "+e.getMessage());
		}finally {
			em.close();
		}
	}
	
	public List<Resposta> getRespostaByFase(Fase fase){
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			if(fase.getTipoFase()!=TipoFase.PERGUNTA && fase.getTipoFase() != TipoFase.COMPLETAR) 
				throw new ValorInvalidoException("A FASE INFORMADA NÃƒO POSSUI RESPOSTAS, APENAS AS FASES COM O TIPO 'PERGUNTA' E 'COMPLETAR' POSSUEM RESPOSTAS");
			
			return new RespostaDAO(em).getByFase(fase);
		}catch (ValorInvalidoException e) {
			throw new ValorInvalidoException(e);
		}catch (Exception e) {
			throw new RuntimeException("Erro ao obter as respostas da fase. Detalhe: "+e.getMessage());
		}finally {
			em.close();
		}
	}
	
	public List<Fase> getAll() {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			return new FaseDAO(em).getAll();
		}catch (Exception e) {
			throw new RuntimeException("Erro ao obter as fases. Detalhe: "+e.getMessage());
		}
		finally {
			em.close();
		}
	}
	
        public void delete(Fase fase)throws Exception{
            EntityManager em = JPAUtil.getEntityManager();
            try{
                em.getTransaction().begin();
                
                if(fase.getTipoFase() == TipoFase.COMBATE)
                    new CombateDAO(em).deleteByFase(fase);
                else if(fase.getTipoFase() != TipoFase.FINAL)
                    new RespostaDAO(em).deleteByFase(fase);
                
                new DialogoDAO(em).deleteByFase(fase);
                
                if(fase.getTipoFase() == TipoFase.FINAL){
                    new FinalDAO(em).delete(Final.valueOf(fase));
                }
                else{
                    new FaseDAO(em).delete(fase);
                }
                    
                em.getTransaction().commit();
            }catch(Exception e){
                em.getTransaction().rollback();
                throw new Exception("erro ao apagar, detalhe: "+e.getMessage(), e);
            }finally{
                em.close();
            }
        }
        
        
        public Fase getPrimeiraFase(){
        	EntityManager em = JPAUtil.getEntityManager();
    		
    		try {
    			return new FaseDAO(em).getFirst();
    		}catch (Exception e) {
    			throw new RuntimeException("Erro ao obter a primeira fase. Detalhe: "+e.getMessage());
    		}
    		finally {
    			em.close();
    		}
        }
        
        public Fase getProximaFase(Fase faseAtual, int pontos) {
        	EntityManager em = JPAUtil.getEntityManager();
    		
    		try {
    			FaseDAO dao = new FaseDAO(em);
    			
    			if(dao.existNext(faseAtual))
    				return dao.getNext(faseAtual);
    			else 
    				return getFinalByPontos(pontos);
    				
    		}catch (Exception e) {
    			throw new RuntimeException("Erro ao obter a primeira fase. Detalhe: "+e.getMessage());
    		}
    		finally {
    			em.close();
    		}	
        }
	
        
        public int valorDadoCombate(int pontoProgresso, int vidaInimigo) {
        	return pontoProgresso + new Random().nextInt(vidaInimigo+1);
        }
        
	
	public Final getFinalByPontos(Integer pontos) {
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			return new FinalDAO(em).getByPontos(pontos);
		}catch (Exception e) {
			throw new RuntimeException("Erro ao obter a primeira fase. Detalhe: "+e.getMessage());
		}
		finally {
			em.close();
		}	
	}

	private void checkResposta(Fase fase, List<Resposta> respostas) throws ValorInvalidoException{
		if(fase.getTipoFase()!=TipoFase.PERGUNTA && fase.getTipoFase() != TipoFase.COMPLETAR) 
			throw new ValorInvalidoException("NÃƒO PODE INFORMAR RESPOSTAS PARA ESSE TIPO DE FASE, APENAS AS FASES COM OS SEGUINTES TIPO PERMITEM RESPOSTAS: PERGUNTA E COMPLETAR");
		if(fase.getTipoFase()==TipoFase.PERGUNTA && (respostas.size()!=2 && respostas.size()!=4))
			throw new ValorInvalidoException("O TIPO DE FASE PERGUNTA PODE CONTER APENAS 2 OU 4 RESPOSTAS");
		if(fase.getTipoFase()==TipoFase.COMPLETAR && respostas.size()!=1)
			throw new ValorInvalidoException("O TIPO DE FASE COMPLETAR ACEITA APENAS UMA RESPOSTA");
	}
	
	private void checkFase(Fase fase, List<Dialogo> dialogos) {
		if(dialogos.isEmpty())
			throw new ValorInvalidoException("É NECESSÁRIO QUE INFORME PELO MENOS 1 DIALOGO A FASE");
		if(fase.getDsFase().length()>60)
			throw new ValorInvalidoException("A DESCRIÇÃO DA FASE NÃO PODE TER MAIS DE 60 CARACTERES");
	}
        
    private void checkCombate(Fase fase, Combate combate){
        if(fase.getTipoFase() !=TipoFase.COMBATE)
               throw new ValorInvalidoException("A fase informada nÃ£o Ã© combate");
        if(combate.getDificuldade()==null)
           throw new ValorInvalidoException("Informe a dificuldade do combate");
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
