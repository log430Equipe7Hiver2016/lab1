/* * ATM Example system - file NetworkToBank.java * * copyright (c) 2001 - Russell C. Bjork * */ package edu.gordon.atm.physical;import java.net.InetAddress;import edu.gordon.simulation.Simulation;/** Manager for the ATM's network connection.  In a real ATM, this would  *  manage a physical device; in this edu.gordon.simulation,  it uses classes  *  in package edu.gordon.simulation to simulate the device. */ public class NetworkToBank{    /** Constructor     *     *  @param log the log in which to record sending of messages and responses     *  @param bankAddress the network address of the bank     */    public NetworkToBank(Log log, InetAddress bankAddress)    {        this.log = log;        this.bankAddress = bankAddress;    }        /** Open connection to bank at system startup     */    public void openConnection()    {        // Since the network is simulated, we don't have to do anything    }        /** Close connection to bank at system shutdown     */    public void closeConnection()    {        // Since the network is simulated, we don't have to do anything    }        /** Send a message to bank     *     *  @param message the message to send     *  @param balances (out) balances in customer's account as reported     *         by bank     *  @return status code returned by bank     */    public String sendMessage(String message,int messageCode, int cardNumber, int pin,                    int serialNumber, int fromAccount, int toAccount, int[] cash,                   long total, long available)    {        // Log sending of the message                log.logSend(message);                // Simulate the sending of the message - here is where the real code        // to actually send the message over the network would go                String result = Simulation.getInstance().sendMessage(messageCode,                cardNumber, pin, serialNumber, fromAccount, toAccount,                cash, total, available);                // Log the response gotten back                log.logResponse(result);                return result;    }        // Log into which to record messages        private Log log;        // Network address of the bank        private InetAddress bankAddress;}