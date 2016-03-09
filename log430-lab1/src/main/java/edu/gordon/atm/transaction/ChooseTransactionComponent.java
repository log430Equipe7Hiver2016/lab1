/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gordon.atm.transaction;

import edu.gordon.event.ChooseTransactionEvent;
import com.google.common.eventbus.Subscribe;
import edu.gordon.atm.physical.CustomerConsole;

/**
 *
 * @author Fred
 */
public class ChooseTransactionComponent {
     	/**
	 * The handleEvent() method is called whenever the event type
	 * in the method parameter is posted on the event bus.
	 * 
	 * @param evt The event this method gets triggered by. 
	 */
	@Subscribe
	public void handleEvent(ChooseTransactionEvent evt) {
            
            Session session = (Session) evt.getSource();
            
            try
            {
                session.setChoosingState();
                session.setTransaction(
                            Transaction.makeTransaction(session.getComponents(),
                                    session.getCard(), session.getPIN(),
                                    session.getId(),session.getPlace(),
                                    session.getBankName()));
                        session.setPerformingState();
                    }
                    catch(CustomerConsole.Cancelled e)
                    {
                        session.setEjectingCardState();
                    }
            
	}   
}
