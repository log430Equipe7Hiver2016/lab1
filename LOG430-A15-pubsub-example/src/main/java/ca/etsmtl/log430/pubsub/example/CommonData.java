package ca.etsmtl.log430.pubsub.example;

/**
 * Contains data that is used (directly or indirectly) by all
 * classes.
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
 * 
 * ***************************************************************************
 */

public class CommonData {
	/** The file that contains the information for projects. */
	private String defaultProjectsFile = "projects.txt";
	
	/** Variable used to determine if initialization has been realized. */
	private static boolean initialized = false;

	/** Object that reads the projects from a file. */
	static ProjectReader theListOfProjects;

	/**
	 * Initializes the project list using the default lists
	 */
	public CommonData() {
		if (!initialized) {
			theListOfProjects = new ProjectReader(defaultProjectsFile);
			initialized = true;
		} // if

		// If either list is null, you are in trouble.
		if (theListOfProjects.getList() == null) {
			System.out.println("\n\n *** The projects list is empty ***");
		}
	}
}