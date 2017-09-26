
package controle;

import dao.ClienteDAO;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Cliente;
import modelo.Treino;

@ManagedBean(name = "treinoControle")
@ViewScoped
public class TreinoControle implements Serializable {
    private Cliente clienteSelecionado;
    private List<Cliente> clientes;
    private Treino novoTreino;
    private ClienteDAO clienteDAO;
    private Treino aux;
    
    
    public TreinoControle(){
        clienteSelecionado = new Cliente();
        clienteDAO = new ClienteDAO(); 
        clientes = clienteDAO.listarTodos();
        novoTreino = new Treino();    
    }
    
    public void salvar(){
        novoTreino.setCliente(clienteSelecionado);
        clienteSelecionado.getTreino().add(novoTreino);
        clienteDAO.alterar(clienteSelecionado);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Treino cadastrado", null));
    }
    
    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Treino getTreino() {
        return novoTreino;
    }

    public void setTreino(Treino treino) {
        this.novoTreino = treino;
    }

    public ClienteDAO getClienteDAO() {
        return clienteDAO;
    }

    public void setClienteDAO(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }
    

}
