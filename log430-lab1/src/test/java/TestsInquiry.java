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
import edu.gordon.banking.*;
import edu.gordon.atm.transaction.*;
import edu.gordon.simulation.*;
import com.google.common.eventbus.EventBus;

/**
 *
 * @author fred
 */
public class TestsInquiry {
    
    private static ATM unAtm;
    private static ATMService unAtmService;
    private static Session uneSession;
    private static Card uneCarte;
    private static Inquiry unInquiry;
    private static Simulation uneSimulation;
    
    public TestsInquiry() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        EventBus eventBus = new EventBus();
        
        TestsInquiry.unAtm = new ATM(42, "Gordon College", "First National Bank of Anus",
                             null /* We're not really talking to a bank! */);
        
        TestsInquiry.unAtmService = new ATMService(42, "Gordon College", "First National Bank of Anus",
                             null /* We're not really talking to a bank! */, eventBus);
        
        TestsInquiry.uneCarte = new Card(1);
        
        TestsInquiry.uneSimulation = new Simulation(unAtmService);
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
    public void testValidInquiry() {
        
        Boolean doAgain = false;
        final Money amount = new Money(10);
        Balances testBalances = new Balances();
        testBalances.setBalances(new Money(100), new Money(100));

        int[] cash = new int[2];
         
        cash[0] = 0;
        cash[1] = 0;
         
         
        Message unMessage = new Message(0, 1, 42, 
                   1, -1, 0, cash);
     
        unInquiry = new Inquiry(unAtm.getComponents(), uneCarte, 42, unAtm.getID(),
                 unAtm.getPlace(), unAtm.getBankName());
        
        unInquiry.completeTransaction(unMessage);
        
        unInquiry.connectToBank();
         
         
        assertTrue(unInquiry.getBalances().equals(testBalances));
    }
}
