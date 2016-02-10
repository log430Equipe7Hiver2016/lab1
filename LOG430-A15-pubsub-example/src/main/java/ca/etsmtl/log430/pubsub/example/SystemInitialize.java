package ca.etsmtl.log430.pubsub.example;

import com.google.common.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Initializes the system. First each component is instantiated. Then a loop
 * runs through a menu until exit optin is selected.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.7, 2015-Oct-02
 */

/*
 * Modification Log **********************************************************
 * v1.7, R. Champagne, 2015-Oct-06 - Added constructor, moved contents of main
 * in private method so 'this' could be passed to event constructors, added
 * log4j logging of events.
 * 
 * v1.6, R. Champagne, 2015-Oct-02 - Various refactorings for new lab.
 * 
 * v1.5, R. Champagne, 2013-Oct-06 - Various refactorings for new lab.
 * 
 * v1.4, R. Champagne, 2012-Jun-19 - Various refactorings for new lab.
 * 
 * v1.3, R. Champagne, 2012-Feb-14 - Various refactorings for new lab.
 * 
 * v1.2, R. Champagne, 2011-Feb-24 - Various refactorings, conversion of
 * comments to javadoc format.
 * 
 * v1.1, R. Champagne, 2002-Jun-19 - Adapted for use at ETS.
 * 
 * v1.0, A.J. Lattanze, 12/29/99 - Original version.
 * ***************************************************************************
 */

public class SystemInitialize {

	public SystemInitialize() {}
	
	private void initAndLoopMenu() {
		/*
		 * We instantiate each system component. After these components are
		 * instantiated, they simply wait until EventBus broadcasts
		 * changes. 
		 */
		new CommonData();
		EventBus eventBus = new EventBus();

		eventBus.register(new ListProjectsComponent());
		eventBus.register(new SayHelloComponent());

		Logger logger = LoggerFactory.getLogger(SystemInitialize.class);
		logger.info("Logger started.");
		
		char userChoice;
		boolean done = false;
		Menus menu = new Menus();

		while (!done) {
			userChoice = menu.mainMenu();

			switch (userChoice) {
			case '1':
				eventBus.post(new ListProjectsEvent(this));
				break;

			case '2':
				eventBus.post(new SayHelloEvent(this));
				break;

			case 'X':
			case 'x':
				done = true;
				break;
			}
		}
	}

	public static void main(String argv[]) {
		SystemInitialize s = new SystemInitialize();
		s.initAndLoopMenu();
	}
}