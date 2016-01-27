/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gordon.banking;

/**
 *
 * @author fred
 */
/** Representation for status of a transaction that failed (for reason other than
     *  invalid PIN)
     */
    public class Failure extends Status
    {
        /** Constructor
         *
         *  @param message description of the failure
         */
        public Failure(String message)
        {
            this.message = message;
        }
        
        public boolean isSuccess()
        {
            return false;
        }
        
        public boolean isInvalidPIN()
        {
            return false;
        }
        
        public String getMessage()
        {
            return message;
        }
        
        private String message;
    }
