
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaQuery;
import modelo.Treino;
import util.JpaUtil;

public class TreinoDAO {
     public boolean inserir(Treino treino){
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(treino);
        tx.commit();
        manager.close();
        return true;
    }
    
     public List<Treino> listarTodos(){
        EntityManager manager = JpaUtil.getEntityManager();
        CriteriaQuery<Treino> query = manager.getCriteriaBuilder().createQuery(Treino.class);
        query.select(query.from(Treino.class));
        List<Treino> lista = manager.createQuery(query).getResultList();
        manager.close();
        return lista;
    }
     
     public boolean excluir(Treino treino){
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction(); 
        tx.begin();
        // recupera a referência ao objeto
        Treino temp = manager.find(Treino.class, treino.getId());
        manager.remove(temp);
        tx.commit();
        manager.close();
        
        return true;
    }
     
    public boolean alterar(Treino treino){
        EntityManager manager = JpaUtil.getEntityManager();
        manager.getTransaction().begin();
        manager.merge(treino);
        manager.getTransaction().commit();
        manager.close();
        return true;
    }
}
