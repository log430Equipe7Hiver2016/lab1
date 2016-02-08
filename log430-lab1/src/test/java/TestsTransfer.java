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
public class TestsTransfer {
    
    private static ATM unAtm;
    private static ATMService unAtmService;
    private static Session uneSession;
    private static Card uneCarte;
    private static Transfer unTransfer;
    private static Simulation uneSimulation;
    
    public TestsTransfer() {
        
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        TestsTransfer.unAtm = new ATM(42, "Gordon College", "First National Bank of Anus",
                             null /* We're not really talking to a bank! */);
        
        TestsTransfer.unAtmService = new ATMService(42, "Gordon College", "First National Bank of Anus",
                             null /* We're not really talking to a bank! */);
        
        TestsTransfer.uneCarte = new Card(1);
        
        TestsTransfer.uneSimulation = new Simulation(unAtmService);
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
    public void testValidTransfer() {
        
        Boolean doAgain = false;
        final Money amount = new Money(10);
        Balances testBalances = new Balances();
        testBalances.setBalances(new Money(1010), new Money(1010));

        int[] cash = new int[2];
         
        cash[0] = 10;
        cash[1] = 0;
         
         
        Message unMessage = new Message(3, 1, 42, 
                   1, 0, 1, cash);
     
        unTransfer = new Transfer(unAtm.getComponents(), uneCarte, 42, unAtm.getID(),
                 unAtm.getPlace(), unAtm.getBankName());
        
        unTransfer.completeTransaction(unMessage);
        
        unTransfer.connectToBank();
                 
        assertTrue(unTransfer.getBalances().equals(testBalances));
        
    }
}
