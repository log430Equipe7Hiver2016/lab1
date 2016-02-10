package ca.etsmtl.log430.pubsub.example;


/**
 * Displays various types of information to the screen.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.7, 2015-Oct-02
 */

/*
 * Modification Log
 * ************************************************************************
 * v1.7, R. Champagne, 2015-Oct-02 - Various refactorings for new lab.
 * 
 * v1.6, R. Champagne, 2013-Sep-13 - Various refactorings for new lab.
 * 
 * v1.5, R. Champagne, 2012-Jun-19 - Various refactorings for new lab.
 * 
 * v1.3, R. Champagne, 2012-Feb-02 - Various refactorings for new lab.
 * 
 * v1.2, 2011-Feb-02, R. Champagne - Various refactorings, javadoc comments.
 * 
 * v1.1, 2002-May-21, R. Champagne - Adapted for use at ETS.
 * 
 * v1.0, 12/29/99, A.J. Lattanze - Original version.
 * ************************************************************************
 */

public class Displays {

	private int lineCount = 0;
	private int maxLinesDisplayed = 18;

	/**
	 * Counts the number of lines that has been printed. Once a set number of
	 * lines has been printed, the user is asked to press the enter key to
	 * continue. This prevents lines of text from scrolling off of the page.
	 * 
	 * @param linesToAdd
	 */
	private void lineCheck(int linesToAdd) {

		Termio terminal = new Termio();

		if (lineCount >= maxLinesDisplayed) {

			lineCount = 0;
			System.out.print("\n*** Press Enter To Continue ***");
			terminal.keyboardReadChar();

		} else {

			lineCount += linesToAdd;

		} // if

	} // LineCheck

	/**
	 * Displays a project object's elements as follows: ID, name, start date,
	 * end date, and priority. Note that the resources assigned to the project
	 * are not listed by this method.
	 * 
	 * @param account The project
	 */
	public void displayProject(Project account) {
		System.out.println(account.getID() + " "
				+ account.getProjectName() + " "
				+ account.getStartDate() + " "
				+ account.getEndDate() + " "
				+ account.getPriority());
	}

	/**
	 * Displays the projects in a project list. Displays the same
	 * information that is listed in the displayProject() method listed above.
	 * 
	 * @param list The project list
	 */
	public void displayProjectList(ProjectList list) {

		boolean done;
		Project project;

		System.out.print("\n");
		lineCheck(1);

		list.goToFrontOfList();
		done = false;

		while (!done) {
			project = list.getNextProject();
			if (project == null) {
				done = true;
			} else {
				displayProject(project);
				lineCheck(1);
			} // if
		} // while
	}
	
	public void sayHello() {
		System.out.println("\nHello !!!\n");
	}

} // Display