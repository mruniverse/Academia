
package controle;

import dao.TreinoDAO;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Treino;

@ManagedBean(name = "treinoControle")
@ViewScoped
public class TreinoControle implements Serializable {
    private Treino treino;
    private Treino aux;
    private TreinoDAO dao;
    private List<Treino> treinos;
    
    public TreinoControle(){
        treino = new Treino();
        dao = new TreinoDAO();
        treinos = dao.listarTodos();
    }
    
    public void salvarNovoTreino(){
        dao.inserir(treino);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Treino cadastrado", null));
        treinos.add(treino);
        treino = new Treino();
    }
    
    public void preparaAlterar(Treino c){
        aux = c;
    }
    
    public void alterar(){
        System.out.println("alteracao: " + aux.getTreino());
        dao.alterar(aux);
    }
    
    public void excluir(Treino c){
        dao.excluir(c);
        treinos.remove(c);
    }
    

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }

    public TreinoDAO getDao() {
        return dao;
    }

    public void setDao(TreinoDAO dao) {
        this.dao = dao;
    }

    public List<Treino> getTreinos() {
        return treinos;
    }

    public void setTreinos(List<Treino> treinos) {
        this.treinos = treinos;
    }

    public Treino getAux() {
        return aux;
    }

    public void setAux(Treino aux) {
        this.aux = aux;
    }
    
}
