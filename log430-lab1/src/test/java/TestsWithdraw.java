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
public class TestsWithdraw {
    
    private static ATM unAtm;
    private static ATMService unAtmService;
    private static Session uneSession;
    private static Card uneCarte;
    private static Withdrawal unWithdrawal;
    private static Simulation uneSimulation;
    
    public TestsWithdraw() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        EventBus eventBus = new EventBus();
        
        TestsWithdraw.unAtm = new ATM(42, "Gordon College", "First National Bank of Anus",
                             null /* We're not really talking to a bank! */);
        
        TestsWithdraw.unAtmService = new ATMService(42, "Gordon College", "First National Bank of Anus",
                             null /* We're not really talking to a bank! */, eventBus);
        
        TestsWithdraw.uneCarte = new Card(1);
        
        TestsWithdraw.uneSimulation = new Simulation(unAtmService);
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
    public void testValidWithdraw() {
    
        Boolean doAgain = false;
        final Money amount = new Money(10);
        Balances testBalances = new Balances();
        testBalances.setBalances(new Money(90), new Money(90));

        int[] cash = new int[2];
         
        cash[0] = 10;
        cash[1] = 0;
         
         
        Message unMessage = new Message(0, 1, 42, 
                   1, 0, -1, cash);
     
        unWithdrawal = new Withdrawal(unAtm.getComponents(), uneCarte, 42, unAtm.getID(),
                 unAtm.getPlace(), unAtm.getBankName());
        
        unWithdrawal.completeTransaction(unMessage);
        
        unWithdrawal.connectToBank();
         
         
        assertTrue(unWithdrawal.getBalances().equals(testBalances));
    
    }
}
