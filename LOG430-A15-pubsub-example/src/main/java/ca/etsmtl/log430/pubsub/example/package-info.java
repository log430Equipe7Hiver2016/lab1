/**
 * Example for assignment 2, ETS course LOG430 - Architecture logicielle.
 * The purpose of this assignment is to introduce the publish-subscribe architectural
 * style.<br><br>
 * 
 * The application is a contrived project management system. It is a menu
 * driven system that allows the following options:
 * 
 * <pre>
 *    1) List projects
 *    2) Say hello
 *    X) Exit.
 * </pre>
 * 
 * Dynamically, the main program initializes the primary objects
 * and dispatches commands at the user's request. When the program is started,
 * the project objects are initialized from a file (<tt>projects.txt</tt>).
 * The format of this file is listed in the
 * {@link ca.etsmtl.log430.pubsub.example.ProjectReader ProjectReader} class header.
 * 
 * <br><br><b>Running the program:</b><br>
 * 
 * <blockquote>
 * <tt>java SystemInitialize</tt>
 * </blockquote>
 *
 * @author A.J. Lattanze - CMU - 1999
 * @author Roger Champagne - ETS - 2002-2015
 * @version 2015-Oct-02
 */
package ca.etsmtl.log430.pubsub.example;