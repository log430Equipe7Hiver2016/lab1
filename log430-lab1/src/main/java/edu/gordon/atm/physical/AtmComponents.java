/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gordon.atm.physical;

/**
 *
 * @author fred
 */
public class AtmComponents {
    
    public AtmComponents(CardReader cardReader, CashDispenser cashDispenser,
            CustomerConsole customerConsole, EnvelopeAcceptor envelopeAcceptor,
            OperatorPanel operatorPanel, ReceiptPrinter receiptPrinter,
            NetworkToBank networkToBank){
        
        theCardReader = cardReader;
        theCashDispenser = cashDispenser;
        theCustomerConsole = customerConsole;
        theEnvelopeAcceptor = envelopeAcceptor;
        theOperatorPanel = operatorPanel;
        theReceiptPrinter = receiptPrinter;
        theNetworkToBank = networkToBank;
        
    }
    
    public CardReader getCardReader(){
        return theCardReader;
    }
    
    public CashDispenser getCashDispenser(){
        return theCashDispenser;
    }
    
    public CustomerConsole getCustomerConsole(){
        return theCustomerConsole;
    }
    
    public EnvelopeAcceptor getEnvelopeAcceptor(){
        return theEnvelopeAcceptor;
    }
    
    public OperatorPanel getOperatorPanel(){
        return theOperatorPanel;
    }
    
    public ReceiptPrinter getReceiptPrinter(){
        return theReceiptPrinter;
    }
    
    public NetworkToBank getNetworkToBank(){
        return theNetworkToBank;
    }
    
    private CardReader theCardReader;
    private CashDispenser theCashDispenser;
    private CustomerConsole theCustomerConsole;
    private EnvelopeAcceptor theEnvelopeAcceptor;
    private OperatorPanel theOperatorPanel;
    private ReceiptPrinter theReceiptPrinter;
    private NetworkToBank theNetworkToBank;
    
}
