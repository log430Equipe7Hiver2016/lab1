/* * ATM Example system - file Session.java * * copyright (c) 2001 - Russell C. Bjork * */ package edu.gordon.atm.transaction;import edu.gordon.atm.physical.*;import edu.gordon.banking.Card;/** Representation for one ATM session serving a single customer. */public class Session{    /** Constructor     *     *  @param edu.gordon.atm the ATM on which the session is performed     */    public Session(AtmComponents physicalComponents, int id, String place,            String bankName)    {        this.physicalComponents = physicalComponents;        this.id = id;        this.place = place;        this.bankName = bankName;    }        /** Perform the Session Use Case     */    public void performSession()    {        Card card = null;        Transaction currentTransaction = null;                actionState = READING_CARD_STATE;                while (actionState != FINAL_STATE)        {            switch(actionState)            {                case READING_CARD_STATE:                                                    card = new Card(physicalComponents.getCardReader().readCard());                                        if (card.getNumber() != 0)                        actionState = READING_PIN_STATE;                    else                    {                        physicalComponents.getCustomerConsole().display("Unable to read card");                        actionState = EJECTING_CARD_STATE;                    }                    break;                                    case READING_PIN_STATE:                                    try                    {                        setPIN( physicalComponents.getCustomerConsole().readPIN(                            "Please enter your PIN\n" +                            "Then press ENTER"));                        actionState = CHOOSING_TRANSACTION_STATE;                    }                    catch(CustomerConsole.Cancelled e)                    {                        actionState = EJECTING_CARD_STATE;                    }                    break;                                case CHOOSING_TRANSACTION_STATE:                                    try                    {                        setChoosingState();                        currentTransaction =                             Transaction.makeTransaction(physicalComponents,                                    card, pin,                                    id,place , bankName);                        actionState = PERFORMING_TRANSACTION_STATE;                    }                    catch(CustomerConsole.Cancelled e)                    {                        actionState = EJECTING_CARD_STATE;                    }                    break;                                    case PERFORMING_TRANSACTION_STATE:                                    try                    {                        boolean doAgain =                             currentTransaction.performTransaction();                        if (doAgain)                            actionState = CHOOSING_TRANSACTION_STATE;                        else                            actionState = EJECTING_CARD_STATE;                    }                    catch(Transaction.CardRetained e)                    {                        actionState = FINAL_STATE;                    }                    break;                                    case EJECTING_CARD_STATE:                                    physicalComponents.getCardReader().ejectCard();                    actionState = FINAL_STATE;                    break;            }        }    }        /** Change the pin recorded for the customer (if invalid pin extension     *  was performed by a transaction     *     *  @param pin the newly entered pin     */    public void setPIN(int pin)    {        this.pin = pin;    }        public int getPIN(){        return pin;    }        public void setChoosingState(){        actionState = CHOOSING_TRANSACTION_STATE;    }        public void setPerformingState(){        actionState = PERFORMING_TRANSACTION_STATE;    }    // Instance variables        /** The PIN entered (or re-entered) by the customer     */    private int pin;        /** The current state of the session     */    private int actionState;            // Possible values for state        /** Reading the customer's card     */    private static final int READING_CARD_STATE = 1;        /** Asking the customer to enter a PIN     */    private static final int READING_PIN_STATE = 2;        /** Asking the customer to choose a transaction type     */    private static final int CHOOSING_TRANSACTION_STATE = 3;        /** Peforming a transaction     */    private static final int PERFORMING_TRANSACTION_STATE = 4;        /** Ejecting the customer's card     */    private static final int EJECTING_CARD_STATE = 5;        /** Session finished     */    private static final int FINAL_STATE = 6;        private AtmComponents physicalComponents;        private int id;        private String place;        private String bankName;    }