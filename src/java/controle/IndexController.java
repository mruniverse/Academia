
package controle;

import dao.AdministradorDAO;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.Administrador;

@ManagedBean(name = "indexController")
@ViewScoped
public class IndexController implements Serializable {
    private Administrador admin;
    
    public IndexController(){
        admin = new Administrador();
    }
    
    public String autenticar(){
        AdministradorDAO dao = new AdministradorDAO();
        Administrador temp;
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
        return "pagina-adm";
    }

    public Administrador getAdmin() {
        return admin;
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }
    
}
