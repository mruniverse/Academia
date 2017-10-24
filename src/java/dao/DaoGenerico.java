
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import util.JpaUtil;

/**
 * Classe genérica de persistência.
 * @author José
 */
public class DaoGenerico<T> {
    private Class<T> classe;
    EntityManager manager;
    
    public DaoGenerico(Class<T> classe){
        this.classe = classe;
    }
    
    public T alterar(T objeto) {
        manager = JpaUtil.getEntityManager();
        manager.getTransaction().begin();
        objeto = manager.merge(objeto);
        manager.getTransaction().commit();
        manager.close();
        return objeto;
    }
    
    public T autenticar(String login, String senha, String tabela){
        T temp = null; // usuario retornado na consulta ao banco
        manager = JpaUtil.getEntityManager();
        String sql = "SELECT u FROM " + tabela + " u WHERE u.login = :login AND u.senha = :senha";        
        TypedQuery<T> query = manager.createQuery(sql, classe);
        query.setParameter("login", login);
        query.setParameter("senha", senha);
        try{
            temp = query.getSingleResult(); 
        }
        catch(Exception e){ }     //aqui poderia haver um tratamento de exceção mais decente
        finally{
            manager.close();
        }        
        return temp;
    }
    
    /**
     * Busca pelo id do objeto
     * @param id
     * @return 
     */
    public T buscar(Object id){
        T objeto;
        manager = JpaUtil.getEntityManager();
        objeto = manager.find(classe, id);
        manager.close();
        return objeto;
    }
      
    public void excluir(Integer id) {
        manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        T temp = manager.find(classe, id);
        manager.remove(temp);
        tx.commit();
        manager.close();
    }
    
    public boolean inserir(T objeto) {         
        manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(objeto);
        tx.commit();
        manager.close();
        return true;
    }
    
    /**
     * Traz a lista de todos os objetos de determinada classe.
     * @return 
     */
    public List<T> listar(){
        manager = JpaUtil.getEntityManager();
        CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classe);
        query.select(query.from(classe));
        List<T> lista = manager.createQuery(query).getResultList();
        manager.close();
        return lista;
    }
    
}
