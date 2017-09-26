/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import modelo.Cliente;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.JpaUtil;

/**
 *
 * @author a120133
 */
public class ClienteDaoTest {
    
    public ClienteDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

//    @Test
//    public void testInserir() {
//        ClienteDAO dao = new ClienteDAO(); 
//        Cliente c = new Cliente();
//        c.setNome("Guto");
//        c.setCpf("477.999.545-12");
//        if(dao.cpfJaCadastrado(c.getCpf())){
//            System.out.println("Cpf existente!");
//        }
//        else{
//            dao.inserir(c);
//        }
//    }
    
    @Test
    public void testListarTodos(){
        List<Cliente> lista = new ClienteDAO().listarTodos();
        for (Cliente c : lista){
            System.out.println("Cliente: "+ c.getNome());
            System.out.println("CPF: "+ c.getCpf());
        }
    }

//    @Test
//    public void testInserir() {
//        AdminDAO dao = new AdminDAO(); 
//        Admin a = new Admin();
//        a.setNome("Guto");
//
//    }
    
//    @Test
//    public void excluirCliente(){
//        ClienteDAO dao = new ClienteDAO();
//        Cliente c = dao.buscarPorCpf("477.687.545-12");
//        System.out.println(c.getNome());
//        dao.excluir(c);
//    }
}
