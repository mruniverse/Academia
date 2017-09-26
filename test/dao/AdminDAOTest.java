
package dao;

import modelo.Admin;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author José
 */
public class AdminDAOTest {
    
    public AdminDAOTest() {
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

    @Test
    public void testAutenticar(){
        Admin admin = new Admin();
        admin.setLogin("admin");
        admin.setSenha("admin");
        Admin result = new AdminDAO().autenticar(admin);
        if (result == null){
            System.out.println("----- falha autenticação");
        }
        else {
            System.out.println("------ autenticação correta");
        }
        
    }
    
}
