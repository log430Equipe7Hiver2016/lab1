/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gordon.atm.transaction;

import edu.gordon.event.PerformTransactionEvent;
import com.google.common.eventbus.Subscribe;
/**
 *
 * @author Fred
 */
public class PerformTransactionComponent {
     	/**
	 * The handleEvent() method is called whenever the event type
	 * in the method parameter is posted on the event bus.
	 * 
	 * @param evt The event this method gets triggered by. 
	 */
	@Subscribe
	public void handleEvent(PerformTransactionEvent evt) {
            
            Session session = (Session) evt.getSource();
            
            try
            {
                boolean doAgain = 
                    session.getTransaction().performTransaction();
                    if (doAgain)
                        session.setChoosingState();
                    else
                        session.setEjectingCardState();
            }
            catch(Transaction.CardRetained e)
                {
                    session.setFinalState();
                } 
	}    
}
