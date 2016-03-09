/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gordon.atm.transaction;

import edu.gordon.event.ReadPinEvent;
import com.google.common.eventbus.Subscribe;
import edu.gordon.atm.physical.*;

/**
 *
 * @author Fred
 */
public class ReadPinComponent {
    
    @Subscribe
    public void handleEvent(ReadPinEvent evt){
        
        Session session = (Session) evt.getSource();
        AtmComponents physicalComponents = session.getComponents();
        
        try
            {
                session.setPIN( physicalComponents.getCustomerConsole().readPIN(
                "Please enter your PIN\n" +
                "Then press ENTER"));
                session.setChoosingState();
            }
        catch(CustomerConsole.Cancelled e)
            {
                session.setEjectingCardState();
            }
    }
    
}
