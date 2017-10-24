
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

@ManagedBean(name = "clienteControle")
@ViewScoped
public class ClienteControle implements Serializable {
    private Cliente cliente;
    private Cliente aux;
    private ClienteDAO dao;
    private List<Cliente> clientes;
    private Treino treino;
    
    public ClienteControle(){
        cliente = new Cliente();
        treino = new Treino();
        dao = new ClienteDAO();
        clientes = dao.listarTodos();
    }
    
    public void cadastrarCliente(){
        dao.inserir(cliente);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente cadastrado", null));
        clientes.add(cliente);
        cliente = new Cliente();
    }
    
    public void preparaAlterar(Cliente c){
        aux = c;
    }
    
    public void alterar(){
        System.out.println("alteracao: " + aux.getNome());
        dao.alterar(aux);
    }
    
    public void preparaListar(Cliente c){
        aux = c;
    }
    
    public void excluir(Cliente c){
        dao.excluir(c);
        clientes.remove(c);
    }

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }
    

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente curso) {
        this.cliente = curso;
    }

    public ClienteDAO getDAO() {
        return dao;
    }

    public void setDAO(ClienteDAO dao) {
        this.dao = dao;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente getAux() {
        return aux;
    }

    public void setAux(Cliente aux) {
        this.aux = aux;
    }
    
}
