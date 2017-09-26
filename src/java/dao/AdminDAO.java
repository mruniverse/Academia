
package dao;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelo.Admin;
import util.JpaUtil;


public class AdminDAO implements Serializable {
    EntityManager manager;
    
    public Admin autenticar(Admin admin){
        Admin temp = null; // administrador retornado na consulta ao banco
        manager = JpaUtil.getEntityManager();
        TypedQuery<Admin> query = manager.createQuery("SELECT a FROM admin a WHERE a.login = :login AND a.senha = :senha",
                Admin.class); 
        query.setParameter("login", admin.getLogin());
        query.setParameter("senha", admin.getSenha());
        try{
            temp = query.getSingleResult(); 
        }
        catch(Exception e){ }    
        finally{
            manager.close();
        }        
        return temp;
    }
    
}
