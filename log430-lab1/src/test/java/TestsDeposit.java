/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import edu.gordon.atm.ATM;
import edu.gordon.atm.physical.CustomerConsole;
import edu.gordon.banking.*;
import edu.gordon.atm.transaction.*;
import edu.gordon.simulation.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author fred
 */
public class TestsDeposit {
    
    private static ATM unAtm;
    private static Session uneSession;
    private static Card uneCarte;
    private static Deposit unDepot;
    private static Simulation uneSimulation;
    
    
    
    
    public TestsDeposit() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        TestsDeposit.unAtm = new ATM(42, "Gordon College", "First National Bank of Anus",
                             null /* We're not really talking to a bank! */);
        
        TestsDeposit.uneCarte = new Card(1234567);
        
        TestsDeposit.uneSimulation = new Simulation(unAtm);
        
        
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
     @Test
     public void testValidDeposit() {
         
         Boolean doAgain = false;
         Receipt receipt;
         
         Message unMessage = new Message(2, uneCarte, 42, 
                   1, -1, 2, new Money(10));
     
         unDepot = new Deposit(unAtm.getComponents(), uneCarte, 42, unAtm.getID(),
                 unAtm.getPlace(), unAtm.getBankName());
         
         
        
        try {
            receipt = unDepot.completeTransaction(unMessage);
        } catch (CustomerConsole.Cancelled ex) {
            Logger.getLogger(TestsDeposit.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertEquals(false, doAgain);
         
     }
}