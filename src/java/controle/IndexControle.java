
package controle;

import dao.AdminDAO;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.Admin;

@ManagedBean(name = "indexController")
@ViewScoped
public class IndexControle implements Serializable {
    private Admin admin;
    
    public IndexControle(){
        admin = new Admin();
    }
    
    public String autenticar(){
        AdminDAO dao = new AdminDAO();
        Admin temp;
        temp = dao.autenticar(admin);
        if (temp == null){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário ou senha inválidos", null));
            return null;
        }  
        
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ectx = context.getExternalContext();
        HttpSession session = (HttpSession) ectx.getSession(true);
        session.setAttribute("usuarioLogado", getAdmin());        
        return "menu";
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    
}
