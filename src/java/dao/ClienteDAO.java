
package dao;

import java.util.List;
import modelo.Cliente;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import util.JpaUtil;


public class ClienteDAO {
    public boolean inserir(Cliente cliente){
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(cliente);
        tx.commit();
        manager.close();
        return true;
    }
    
     public List<Cliente> listarTodos(){
        EntityManager manager = JpaUtil.getEntityManager();
        CriteriaQuery<Cliente> query = manager.getCriteriaBuilder().createQuery(Cliente.class);
        query.select(query.from(Cliente.class));
        List<Cliente> lista = manager.createQuery(query).getResultList();
        manager.close();
        
        return lista;
    }
     
      public boolean excluir(Cliente cliente){
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction(); 
        tx.begin();
        // recupera a referência ao objeto
        Cliente temp = manager.find(Cliente.class, cliente.getId());
        manager.remove(temp);
        tx.commit();
        manager.close();
        
        return true;
    }
      
    public Cliente buscarPorCpf(String cpf){
        EntityManager manager = JpaUtil.getEntityManager();
        Query query = manager.createQuery("SELECT c FROM Cliente as c WHERE c.cpf =:cpf");
        query.setParameter("cpf", cpf);
        Cliente cliente = (Cliente) query.getResultList().get(0);
        manager.close();
        
        return cliente;
    }
    
     public Cliente buscarPorNome(String nome){
        EntityManager manager = JpaUtil.getEntityManager();
        Query query = manager.createQuery("SELECT c FROM Cliente as c WHERE c.nome =:nome");
        query.setParameter("nome", nome);
        Cliente cliente = (Cliente) query.getResultList().get(0);
        manager.close();
        
        return cliente;
    }
      
    public boolean cpfJaCadastrado(String cpf){
        EntityManager manager = JpaUtil.getEntityManager();
        Cliente cliente = null;
        Query query = manager.createQuery("SELECT c FROM Cliente as c WHERE c.cpf =:cpf");
        query.setParameter("cpf", cpf);
        try{
            cliente = (Cliente) query.getSingleResult();
        }
        catch(Exception e){
        }
        manager.close();
        
        if(cliente == null){
            return false;   
        }
        else{
            return true;
        }
    }
    
      public boolean alterar(Cliente cliente){
        EntityManager manager = JpaUtil.getEntityManager();
        manager.getTransaction().begin();
        manager.merge(cliente);
        manager.getTransaction().commit();
        manager.close();
        return true;
    }
}
