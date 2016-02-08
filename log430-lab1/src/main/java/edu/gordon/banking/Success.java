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
/** Representation for status of a transaction that succeeded
     */
    public class Success extends Status
    {
        public boolean isSuccess()
        {
            return true;
        }
        
        public boolean isInvalidPIN()
        {
            return false;
        }
        
        public String getMessage()
        {
            return null;
        }
    }
