/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gordon.atm.transaction;

import edu.gordon.event.ReadCardEvent;
import com.google.common.eventbus.Subscribe;
import edu.gordon.banking.Card;

/**
 *
 * @author Fred
 */
public class ReadCardComponent {
    	/**
	 * The handleEvent() method is called whenever the event type
	 * in the method parameter is posted on the event bus.
	 * 
	 * @param evt The event this method gets triggered by. 
	 */
	@Subscribe
	public void handleEvent(ReadCardEvent evt) {
            
            Session session = (Session) evt.getSource();
            
            Card card = (new Card(session.getComponents().getCardReader().readCard()));
            
            session.setCard(card);
                    
            if (card.getNumber() != 0)
                session.setReadingPINState();
            else
            {
                session.getComponents().getCustomerConsole().display("Unable to read card");
                session.setEjectingCardState();
            }
            
	}
    
}
