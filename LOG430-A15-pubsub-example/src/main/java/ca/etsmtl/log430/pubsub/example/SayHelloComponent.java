package ca.etsmtl.log430.pubsub.example;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Upon notification, writes a message via Displays.
 * 
 * @author R. Champagne
 * @version 1.1, 2015-Oct-06
 */

/*
 * Modification Log **********************************************************
 * v1.1, R. Champagne, 2015-Oct-06 - Added debugging info as example via log4j.
 * 
 * v1.0, R. Champagne, 2015-Oct-02 - Original version.
 * ***************************************************************************
 */

public class SayHelloComponent {
    /** Logger instance */
	private static final Logger logger = LoggerFactory.getLogger(SystemInitialize.class);
	
	/**
	 * The handleEvent() method is called whenever the event type
	 * in the method parameter is posted on the event bus.
	 * 
	 * @param evt The event this method gets triggered by. 
	 */
	@Subscribe
	public void handleEvent(SayHelloEvent evt) {
		// Debugging example
		logger.debug("\nEvent " + evt.getClass().toString() + " received in " + 
				this.getClass().toString() + 
				", published by " +
				evt.getSource().getClass().toString() + ".\n");

		Displays display = new Displays();
		display.sayHello();
	}

}