package ca.etsmtl.log430.pubsub.example;

import com.google.common.eventbus.Subscribe;

/**
 * Upon notification, lists the projects that were read into the vector stored in
 * the CommonData class.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.5, 2015-Oct-02
 */

/*
 * Modification Log **********************************************************
 * v1.5, R. Champagne, 2015-Oct-02 - Various refactorings for new lab.
 * 
 * v1.4, R. Champagne, 2013-Oct-06 - Various refactorings for new lab.
 * 
 * v1.3, R. Champagne, 2012-Jun-19 - Various refactorings for new lab.
 * 
 * v1.2, R. Champagne, 2011-Feb-24 - Various refactorings, conversion of
 * comments to javadoc format.
 * 
 * v1.1, R. Champagne, 2002-Jun-19 - Adapted for use at ETS.
 * 
 * v1.0, A.J. Lattanze, 12/29/99 - Original version.
 * ***************************************************************************
 */

public class ListProjectsComponent {

	/**
	 * The handleEvent() method is called whenever the event type
	 * in the method parameter is posted on the event bus.
	 * 
	 * @param evt The event this method gets triggered by. 
	 */
	@Subscribe
	public void handleEvent(ListProjectsEvent evt) {

			Displays display = new Displays();
			display.displayProjectList(CommonData.theListOfProjects
					.getList());
	}
}