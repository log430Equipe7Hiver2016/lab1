/* * ATM Example system - file Transaction.java    * * copyright (c) 2001 - Russell C. Bjork * */ package edu.gordon.atm.transaction;import edu.gordon.atm.physical.CustomerConsole;import edu.gordon.atm.physical.AtmComponents;import edu.gordon.banking.Balances;import edu.gordon.banking.Card;import edu.gordon.banking.Message;import edu.gordon.banking.Receipt;import edu.gordon.banking.Status;import edu.gordon.banking.Success;import edu.gordon.banking.Failure;import edu.gordon.banking.InvalidPIN;/** Abstract base class for classes representing the various kinds of *  transaction the ATM can perform */public abstract class Transaction{    /** Constructor     *     *  @param edu.gordon.atm the ATM used to communicate with customer     *  @param session the session in which this transaction is being performed     *  @param card the customer's card     *  @param pin the PIN entered by the customer     */         protected Transaction(AtmComponents physicalComponents, Card card, int pin,            int id, String place, String bankName)    {        this.physicalComponents = physicalComponents;        this.card = card;        this.pin = pin;        this.serialNumber = nextSerialNumber ++;        this.balances = new Balances();        this.id = id;        this.place = place;        this.bankName = bankName;                state = GETTING_SPECIFICS_STATE;    }             /** Create a transaction of an appropriate type by asking the customer     *  what type of transaction is desired and then returning a newly-created     *  member of the appropriate subclass     *     *  @param edu.gordon.atm the ATM used to communicate with customer     *  @param session the session in which this transaction is being performed     *  @param card the customer's card     *  @param pin the PIN entered by the customer     *  @return a newly created Transaction object of the appropriate type     *  @exception CustomerConsole.Cancelled if the customer presses cancel instead     *         of choosing a transaction type     */    public static Transaction makeTransaction(AtmComponents physicalComponents,                                              Card card, int pin, int id,                                              String place, String bankName)                                throws CustomerConsole.Cancelled                  {        int choice = physicalComponents.getCustomerConsole().readMenuChoice(                "Please choose transaction type", TRANSACTION_TYPES_MENU);                                switch(choice)        {            case 0:                            return new Withdrawal(physicalComponents, card, pin, id,                place, bankName);                            case 1:                            return new Deposit(physicalComponents, card, pin, id,                place, bankName);                            case 2:                            return new Transfer(physicalComponents, card, pin, id,                place, bankName);                            case 3:                            return new Inquiry(physicalComponents, card, pin, id,                place, bankName);                            default:                            return null;    // To keep compiler happy - should not happen!        }    }        /** Perform a transaction.  This method depends on the three abstract methods     *  that follow to perform the operations unique to each type of transaction     *  in the appropriate way.     *     *  @return true if customer indicates a desire to do another transaction;     *          false if customer does not desire to do another transaction     *  @exception CardRetained if card was retained due to too many invalid PIN's     */    public boolean performTransaction() throws CardRetained    {        String doAnotherMessage = "";        Status status = null;        Receipt receipt = null;                while (true)    // Terminates by return in ASKING_DO_ANOTHER_STATE or exception        {            switch(state)            {                case GETTING_SPECIFICS_STATE:                                    try                    {                                   message = getSpecificsFromCustomer();                        physicalComponents.getCustomerConsole().display("");                        state = SENDING_TO_BANK_STATE;                    }                    catch(CustomerConsole.Cancelled e)                    {                        doAnotherMessage = "Last transaction was cancelled";                        state = ASKING_DO_ANOTHER_STATE;                    }                                        break;                                    case SENDING_TO_BANK_STATE:                                                    status = connectToBank();                                    if (status.isInvalidPIN())                        state = INVALID_PIN_STATE;                    else if (status.isSuccess())                        state = COMPLETING_TRANSACTION_STATE;                    else                    {                        doAnotherMessage = status.getMessage();                        state = ASKING_DO_ANOTHER_STATE;                    }                                        break;                                case INVALID_PIN_STATE:                                    try                    {                        status = performInvalidPINExtension();                                            // If customer repeatedly enters invalid PIN's, a                        // CardRetained exception is thrown, and this method                        // terminates                                                if (status.isSuccess())                            state = COMPLETING_TRANSACTION_STATE;                        else                        {                            doAnotherMessage = status.getMessage();                            state = ASKING_DO_ANOTHER_STATE;                        }                    }                    catch(CustomerConsole.Cancelled e)                    {                        doAnotherMessage = "Last transaction was cancelled";                        state = ASKING_DO_ANOTHER_STATE;                    }                    break;                                        case COMPLETING_TRANSACTION_STATE:                    try                    {                        receipt = completeTransaction(message);                        state = PRINTING_RECEIPT_STATE;                    }                    catch(CustomerConsole.Cancelled e)                    {                        doAnotherMessage = "Last transaction was cancelled";                        state = ASKING_DO_ANOTHER_STATE;                    }                                        break;                                    case PRINTING_RECEIPT_STATE:                                    physicalComponents.getReceiptPrinter().printReceipt(receipt.getLines());                    state = ASKING_DO_ANOTHER_STATE;                                        break;                                    case ASKING_DO_ANOTHER_STATE:                                    if (doAnotherMessage.length() > 0)                        doAnotherMessage += "\n";                                            try                    {                        String [] yesNoMenu = { "Yes", "No" };                        boolean doAgain = physicalComponents.getCustomerConsole().readMenuChoice(                            doAnotherMessage +                             "Would you like to do another transaction?",                            yesNoMenu) == 0;                        return doAgain;                    }                    catch(CustomerConsole.Cancelled e)                    {                        return false;                    }            }        }    }                /** Perform the Invalid PIN Extension - reset session pin to new value if successful     *     *  @return status code returned by bank from most recent re-submission     *          of transaction     *  @exception CustomerConsole.Cancelled if customer presses the CANCEL key     *             instead of re-entering PIN     *  @exception CardRetained if card was retained due to too many invalid PIN's     */    public Status performInvalidPINExtension() throws CustomerConsole.Cancelled,                                                      CardRetained    {        Status status = null;        for (int i = 0; i < 3; i ++)        {            pin = physicalComponents.getCustomerConsole().readPIN(                "PIN was incorrect\nPlease re-enter your PIN\n" +                "Then press ENTER");            physicalComponents.getCustomerConsole().display("");                        message.setPIN(pin);                        status = connectToBank();                        if (! status.isInvalidPIN())            {                //atm.invalidPIN(pin);                return status;            }         }                physicalComponents.getCardReader().retainCard();        physicalComponents.getCustomerConsole().display(            "Your card has been retained\nPlease contact the bank.");        try        {            Thread.sleep(5000);        }        catch(InterruptedException e)        { }        physicalComponents.getCustomerConsole().display("");                        throw new CardRetained();    }        protected Status convertToStatus(String statusString){               Status status = null;                if(statusString.equals("SUCCESS")){                    status = new Success();                    }else if(statusString.equals("FAILURE")){                        status = new Failure("Failed transaction.");                    }else if(statusString.equals("INVALID PIN")){                                status = new InvalidPIN();                    }else if(statusString.equals("FAILURE Invalid account type") ){        	status = new Failure("Invalid account type");        	        }else if(statusString.equals("FAILURE Invalid card") ){        	status = new Failure("Invalid card");    	}                                return status;    }        /** Get serial number of this transaction     *     *  @return serial number     */    public int getSerialNumber()    {        return serialNumber;    }        /** Get specifics for the transaction from the customer - each     *  subclass must implement this appropriately.     *     *  @return message to bank for initiating this transaction     *  @exception CustomerConsole.Cancelled if customer cancelled this transaction     */    protected abstract Message getSpecificsFromCustomer() throws CustomerConsole.Cancelled;        /** Complete an approved transaction  - each subclass must implement     *  this appropriately.     *     *  @return receipt to be printed for this transaction     *  @exception CustomerConsole.Cancelled if customer cancelled this transaction     */    protected abstract Receipt completeTransaction(Message info) throws CustomerConsole.Cancelled;        protected abstract Status connectToBank();        // Local class representing card retained exception           /** Exception that is thrown when the customer's card is retained due to too     *  many invalid PIN entries     */    public static class CardRetained extends Exception    {        /** Constructor         */        public CardRetained()        {            super("Card retained due to too many invalid PINs");        }    }        public Balances getBalances(){                return balances;            }            // Instance variables    /** ATM parts to use for communication with the customer     */    protected AtmComponents physicalComponents;        /** Customer card for the session this transaction is part of     */    protected Card card;        /** PIN entered or re-entered by customer     */    protected int pin;        /** Serial number of this transaction     */    protected int serialNumber;        /** Message to bank describing this transaction     */    protected Message message;        /** Used to return account balances from the bank     */    protected Balances balances;        /** List of available transaction types to display as a menu     */    private static final String [] TRANSACTION_TYPES_MENU =         { "Withdrawal", "Deposit", "Transfer", "Balance Inquiry" };            /** Next serial number - used to assign a unique serial number to     *  each transaction     */    private static int nextSerialNumber = 1;        /** The current state of the transaction     */    private int state;        protected int id;        protected String place;        protected String bankName;        // Possible values for state        /** Getting specifics of the transaction from customer     */    private static final int GETTING_SPECIFICS_STATE = 1;        /** Sending transaction to bank     */    private static final int SENDING_TO_BANK_STATE = 2;        /** Performing invalid PIN extension     */    private static final int INVALID_PIN_STATE = 3;        /** Completing transaction     */    private static final int COMPLETING_TRANSACTION_STATE = 4;        /** Printing receipt     */    private static final int PRINTING_RECEIPT_STATE = 5;        /** Asking if customer wants to do another transaction     */    private static final int ASKING_DO_ANOTHER_STATE = 6;}