
package dao;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelo.Administrador;
import util.JpaUtil;


public class AdministradorDAO implements Serializable {
    EntityManager manager;
    
    public Administrador autenticar(Administrador adm){
        Administrador temp = null;
        manager = JpaUtil.getEntityManager();
        TypedQuery<Administrador> query = manager.createQuery("SELECT a FROM Administrador a WHERE a.login = :login AND a.senha = :senha",
                Administrador.class); 
        query.setParameter("login", adm.getLogin());
        query.setParameter("senha", adm.getSenha());
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
