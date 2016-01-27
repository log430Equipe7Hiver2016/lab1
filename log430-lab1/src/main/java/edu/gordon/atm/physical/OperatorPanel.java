/* * ATM Example system - file OperatorPanel.java * * copyright (c) 2001 - Russell C. Bjork * */ package edu.gordon.atm.physical;import edu.gordon.simulation.Simulation;/** Manager for the ATM's operator panel.  In a real ATM, this would  *  manage a physical device; in this edu.gordon.simulation,  it uses classes  *  in package edu.gordon.simulation to simulate the device. */ public class OperatorPanel{    /** Constructor     *     *  @param edu.gordon.atm the ATM this panel is part of     */    public OperatorPanel()    {            }        // In a real ATM, code would be needed to sense a change in the state of the    // switch and notify the ATM - simulated in this case by a button in the GUI        /** Get the amount of cash in the cash dispenser from the operator at start up     *     *  @return dollar value of the bills in the cash dispenser (# of bills x $20)     */        public int getInitialCash()    {        return Simulation.getInstance().getInitialCash();    }       }