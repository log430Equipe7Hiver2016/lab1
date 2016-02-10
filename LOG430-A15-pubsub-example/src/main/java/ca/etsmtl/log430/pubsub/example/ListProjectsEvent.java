package ca.etsmtl.log430.pubsub.example;

import java.util.EventObject;

/**
 * Empty event container.
 * 
 * @author R. Champagne, ETS
 * @version 1.1, 2015-Oct-06
 */

/*
 * Modification Log **********************************************************
 * v1.1, R. Champagne, 2015-Oct-02 - added inheritance of EventObject.
 * 
 * v1.0, R. Champagne, 2015-Oct-02 - Original version.
 * ***************************************************************************
 */

public class ListProjectsEvent extends EventObject {

	public ListProjectsEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
}