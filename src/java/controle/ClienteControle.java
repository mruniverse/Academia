
package controle;

import dao.ClienteDAO;
import dao.TreinoDAO;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Treino;
import modelo.Cliente;

@ManagedBean(name = "clienteControle")
@ViewScoped
public class ClienteControle implements Serializable {
    private Treino treinoSelecionado;
    private List<Treino> treinos;
    private Cliente novoCliente;
    private ClienteDAO dao;
    private TreinoDAO treinoDAO;
    
    
    public ClienteControle(){
        treinoSelecionado = new Treino();
        treinoDAO = new TreinoDAO(); 
        treinos = treinoDAO.listarTodos();
        novoCliente = new Cliente();    
    }
    
    public void salvar(){
        novoCliente.setTreino(treinoSelecionado);
        treinoSelecionado.getCliente().add(novoCliente);
        treinoDAO.alterar(treinoSelecionado);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente cadastrada", null));
    }
    
    public Treino getTreinoSelecionado() {
        return treinoSelecionado;
    }

    public void setTreinoSelecionado(Treino cursoSelecionado) {
        this.treinoSelecionado = cursoSelecionado;
    }

    public List<Treino> getTreinos() {
        return treinos;
    }

    public void setTreinos(List<Treino> treinos) {
        this.treinos = treinos;
    }

    public Cliente getCliente() {
        return novoCliente;
    }

    public void setCliente(Cliente cliente) {
        this.novoCliente = cliente;
    }

    public TreinoDAO getTreinoDAO() {
        return treinoDAO;
    }

    public void setTreinoDAO(TreinoDAO treinoDAO) {
        this.treinoDAO = treinoDAO;
    }
    
}
