/* * ATM Example system - file Transfer.java   * * copyright (c) 2001 - Russell C. Bjork * */ package edu.gordon.atm.transaction;import edu.gordon.atm.physical.CustomerConsole;import edu.gordon.atm.physical.AtmComponents;import edu.gordon.banking.AccountInformation;import edu.gordon.banking.Card;import edu.gordon.banking.Message;import edu.gordon.banking.Money;import edu.gordon.banking.Receipt;import edu.gordon.banking.Status;/** Representation for a transfer transaction */public class Transfer extends Transaction{    /** Constructor     *     *  @param edu.gordon.atm the ATM used to communicate with customer     *  @param session the session in which the transaction is being performed     *  @param card the customer's card     *  @param pin the PIN entered by the customer     */    public Transfer(AtmComponents physicalComponents, Card card, int pin, int id,            String place, String bankName)    {        super(physicalComponents, card, pin, id, place, bankName);    }        /** Get specifics for the transaction from the customer     *     *  @return message to bank for initiating this transaction     *  @exception CustomerConsole.Cancelled if customer cancelled this transaction     */    protected Message getSpecificsFromCustomer() throws CustomerConsole.Cancelled    {        int[] cash = new int[2];                from = physicalComponents.getCustomerConsole().readMenuChoice(            "Account to transfer from",            AccountInformation.ACCOUNT_NAMES);        to = physicalComponents.getCustomerConsole().readMenuChoice(            "Account to transfer to",            AccountInformation.ACCOUNT_NAMES);        cash = physicalComponents.getCustomerConsole().readAmount("Amount to transfer");                //amount = new Money(cash[0],cash[1]);                return new Message(Message.TRANSFER,                         card.getNumber(), pin, serialNumber, from, to, cash);    }        /** Complete an approved transaction     *     *  @return receipt to be printed for this transaction     */    protected Receipt completeTransaction(Message specifics)    {        from = specifics.getFromAccount();                to = specifics.getToAccount();                amount = specifics.getAmount();                return new Receipt(id, place , bankName,                 this.card, this.getSerialNumber(), this.balances) {            {                detailsPortion = new String[2];                detailsPortion[0] = "TRANSFER FROM: " +                                     AccountInformation.ACCOUNT_ABBREVIATIONS[from] +                                    " TO: " +                                     AccountInformation.ACCOUNT_ABBREVIATIONS[to] ;                detailsPortion[1] = "AMOUNT: " + amount.toString();            }        };    }            protected Status connectToBank(){                Status status = convertToStatus(physicalComponents.getNetworkToBank().sendMessage(            message.toString(), Message.COMPLETE_DEPOSIT,                        card.getNumber(), pin, serialNumber, from, to, amount.getDollarsCents(),             balances.getTotal().getValueCents(), balances.getAvailable().getValueCents()));                return status;    }        /** Accounts to transfer from     */    private int from;        /** Account to transfer to     */    private int to;        /** Amount of money to transfer     */    private Money amount;           }