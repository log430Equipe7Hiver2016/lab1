package ca.etsmtl.log430.pubsub.example;


/**
 * Presents the user with menus, accepts their choice, ensures their
 * choice is valid, and returns their choice to the caller. The menu is
 * presented as follows:
 * <pre>
 *    1) List projects
 *    2) Say hello
 *    X) Exit.</pre>
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.6, 2015-Oct-02.
 */

/*
 * Modification Log
 * ***************************************************************************
 * v1.6, R. Champagne, 2015-Oct-02 - Various refactorings for new lab.
 * 
 * v1.5, R. Champagne, 2013-Sep-13 - Various refactorings for new lab.
 * 
 * v1.4, R. Champagne, 2012-May-31 - Various refactorings for new lab.
 * 
 * v1.3, R. Champagne, 2012-Feb-02 - Various refactorings for new lab.
 * 
 * v1.2, 2011-Feb-02, R. Champagne - Various refactorings, javadoc comments.
 * 
 * v1.1, 2002-May-21, R. Champagne - Adapted for use at ETS.
 * 
 * v1.0, 12/29/99, A.J. Lattanze - Original version.
 * ***************************************************************************
 */

public class Menus {

	public char mainMenu() {

		Termio terminal = new Termio();
		char userChoice = ' ';
		boolean error = true;

		while (error) {

			System.out.println("\n\n1) List projects");
			System.out.println("2) Say hello");
			System.out.println("X) Exit");
			System.out.print("\n\nEnter your choice and press return >> ");

			userChoice = terminal.keyboardReadChar();

			if ((userChoice != 'X') && (userChoice != 'x')
					&& (userChoice < '1') && (userChoice > '2')) {

				System.out.print("\n\n*** Invalid Choice:: " + userChoice
						+ " ***");

			} else {

				error = false;

			} // if

		} // while

		return (userChoice);

	} // MainMenu

} // Menus