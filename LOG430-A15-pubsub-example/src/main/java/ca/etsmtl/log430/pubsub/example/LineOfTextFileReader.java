package ca.etsmtl.log430.pubsub.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Provides the methods that allow the caller to open an existing file and read
 * one line of input (to end-of-line) from the file.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.3, 2015-Oct-02
 */

/*
 * Modification Log **********************************************************
 * v1.3, R. Champagne, 2015-Oct-02 - Changed how the buffered reader is initialized
 * so that resources read properly from project root.
 * 
 * v1.2, R. Champagne, 2011-Feb-24 - Various refactorings, conversion of
 * comments to javadoc format.
 * 
 * v1.1, R. Champagne, 2002-Jun-19 - Adapted for use at ETS.
 * 
 * v1.0, A.J. Lattanze, 12/29/99 - Original version.
 * ***************************************************************************
 */

public class LineOfTextFileReader {

	private BufferedReader bReader = null;

	/**
	 * Opens a file.
	 * 
	 * @param fileName Name of the file
	 * @return true if file successfully open, false otherwise.
	 */
	public boolean openFile(String fileName) {
		boolean result;

		try {
		    bReader = new BufferedReader(new InputStreamReader(
			    this.getClass().getResourceAsStream("/" + fileName)));
			result = true;
		} catch (Exception err) {
			result = false;
		}
		return (result);
	}

	/**
	 * Reads a single line of text in a file.
	 * 
	 * @return a String containing the line of text.
	 */
	public String readLineOfText() {
		String lineOfText = null;

		// Read a line of text from the input file and convert it to upper case
		try {
			lineOfText = bReader.readLine();
		} catch (Exception err) {
			try {
				throw (err);
			} catch (Exception e) {
				// We are in real trouble if we get here.
			}
		}
		return (lineOfText);
	}

	/**
	 * Closes the file.
	 * 
	 * @return true if file successfully closed, false otherwise.
	 */
	public boolean closeFile() {
		boolean result = true;

		// Close the input file
		try {
			bReader.close();
		} catch (Exception e) {
			result = false;
		}

		return (result);

	}
}