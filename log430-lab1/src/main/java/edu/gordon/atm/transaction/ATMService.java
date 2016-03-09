package edu.gordon.atm.transaction;

import java.net.InetAddress;
import com.google.common.eventbus.EventBus;

import edu.gordon.atm.ATM;
import edu.gordon.event.ATMEvent;



public class ATMService extends ATM implements Runnable {
	
	private Session currentSession;
	
	private EventBus eventBus;
	
    /** The current state of the ATM - one of the possible values listed below
     */
    private int state;
    
    /** Becomes true when the operator panel informs the ATM that the switch has
     *  been turned on - becomes false when the operator panel informs the ATM
     *  that the switch has been turned off.
     */
    private boolean switchOn;
    
    /** Becomes true when the card reader informs the ATM that a card has been
     *  inserted - the ATM will make this false when it has tried to read the
     *  card
     */
    private boolean cardInserted;
	
    /** The ATM is off.  The switch must be turned on before it can operate
     */
    private static final int OFF_STATE = 0;
    
    /** The ATM is on, but idle.  It can service a customer, or it can be shut down
     */
    private static final int IDLE_STATE = 1;
    
    /** The ATM is servicing a customer.
     */
    private static final int SERVING_CUSTOMER_STATE = 2;

	public ATMService(int id, String place, String bankName, InetAddress bankAddress, EventBus bus) {		
		
		super(id, place, bankName, bankAddress);
		
		this.eventBus = bus;
		
		// Set up initial conditions when ATM first created        
        state = OFF_STATE;
        switchOn = false;
        cardInserted = false; 
        
        
		
	}
	
	
	 /** The main program/applet will create a Thread that executes
     *  this code.
     */
    public void run()
    {
    	
    	
    	
        currentSession = null;
        
        while (true)
        {
            switch(state)
            {
                case OFF_STATE:
                
                    //super.getCustomerConsole().display("Not currently available");
                	this.eventBus.post(new ATMEvent(this) );
                	System.out.println("OFF STATE ");
                	
                	synchronized(this)
                    {
                        try
                        { 
                            wait();
                        }
                        catch(InterruptedException e)
                        { }
                    }
                    
                    if (switchOn)
                    {
                        performStartup();
                        state = IDLE_STATE;
                    }
                                            
                    break;
                    
                case IDLE_STATE:
                
                	//super.getCustomerConsole().display("Please insert your card");
                	this.eventBus.post(new ATMEvent(this) );
                	
                    cardInserted = false;
                                        
                    synchronized(this)
                    {
                        try
                        { 
                            wait();
                        }
                        catch(InterruptedException e)
                        { }
                    }       
                    
                    if (cardInserted)
                    {
                        currentSession = new Session(super.getComponents(), super.getID(),
                        super.getPlace(), super.getBankName() );
                        state = SERVING_CUSTOMER_STATE;
                    }
                    else if (! switchOn)
                    {
                        performShutdown();
                        state = OFF_STATE;
                    }
                    
                    break;
            
                case SERVING_CUSTOMER_STATE:
                                    
                    // The following will not return until the session has
                    // completed
                    
                    currentSession.performSession();
                    
                    state = IDLE_STATE;
                    
                    break;
                
            }
        }
    }
    
    
    
    public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	public void invalidPIN(int pin){
        
        currentSession.setPIN(pin);
        
    }
    
    /** Inform the ATM that the switch on the operator console has been moved
     *  to the "on" position.
     */
    public synchronized void switchOn()
    {
        switchOn = true;
        notify();
    }
    
    /** Inform the ATM that the switch on the operator console has been moved
     *  to the "off" position.
     */
    public synchronized void switchOff()
    {
        switchOn = false;
        notify();
    }
    
    /** Inform the ATM that a card has been inserted into the card reader.
     */
    public synchronized void cardInserted()
    {
        cardInserted = true;
        notify();
    }
    
    private void performStartup()
    {
        int initialCash = super.getOperatorPanel().getInitialCash();
        super.getCashDispenser().setInitialCash(initialCash);
        super.getNetworkToBank().openConnection();     
    }
    
    /** Perform the System Shutdown use case when switch is turned off
     */
    private void performShutdown()
    {
    	super.getNetworkToBank().closeConnection();
    }
    
}
