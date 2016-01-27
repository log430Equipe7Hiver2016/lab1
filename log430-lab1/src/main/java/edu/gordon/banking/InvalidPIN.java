/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gordon.banking;

/**
 *
 * @author fred
/** Representation for status of a transaction that failed due to an invalid PIN
     */
    public class InvalidPIN extends Failure
    {
        /** Constructor
         *
         *  @param message description of the failure
         */
        public InvalidPIN()
        {
            super("Invalid PIN");
        }
        
        public boolean isInvalidPIN()
        {
            return true;
        }
    }
