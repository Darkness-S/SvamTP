package main.java.SVAM;

import nz.ac.waikato.modeljunit.*;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionCoverage;

public class ATM_FSM implements FsmModel{

int retry;
int amount;
boolean swallowed;

/**
 *  Very first version of the FSM.
 *  Automaton focusing on the card authentication tries, abstracting the bills and money (no withdraw operation)
 */

    /** Variable representing the current state */
    private int state;

    /** Variable representing the current state */
    private ATMAdapter adapter;

    /**
     *  Constructor. Initializes the data.
     */
    public ATM_FSM()
    {
        state = 0;
        retry = 3;
        amount = 100;
        swallowed = false;
        adapter = new ATMAdapter();
    }

    /**
     *  Inherited from the FsmModel interface.
     *  Provides a Comparable object that characterizes the current state.
     */
    public String getState()
    {
        return String.valueOf(state);
    }

    /**
     *  Inherited from the FsmModel interface.
     *  Provides a Comparable object that characterizes the current state.
     */
    public void reset(boolean testing)
    {
        state = 0;
        retry = 3;
        amount = 100;
        swallowed = false;
        adapter.reset();
        adapter = new ATMAdapter();
    }

    /**
     *  Guard for the transition. Should be named after the transition name, suffixed by "Guard"
     */
    public boolean insertCardGuard() { return (state == 0 && !swallowed); }
    /**
     *  Transition itself. Annotated with @Action to indicate the method is a transition of the FSM.
     */
    @Action
    public void insertCard()
    {
        // evolution of the state
        state = 1;
        // realizes the transition on the System Under Test
        adapter.insertCard();
    }

    public boolean insertIncorrectCardGuard(){ return (state == 0 && !swallowed);}

    @Action
    public void insertIncorrectCard()
    {
        state = 2;
        adapter.insertIncorrectCard();
    }

    public boolean insertSwallowedCardGuard(){ return (state == 0 && swallowed);}

    @Action
    public void insertSwallowedCard()
    {
        adapter.insertSwallowedCard();
        reset(true);
    }

    /**
     *  Guard for the transition. Should be named after the transition name, suffixed by "Guard"
     */
    public boolean cancelGuard() { return state == 1; }
    /**
     *  Transition itself. Annotated with @Action to indicate the method is a transition of the FSM.
     */
    @Action
    public void cancel()
    {
        // evolution of the state
        state = 2;
        // transmits the operation to the System Under Test
        adapter.cancel();
    }

    public boolean takeCardCancelGuard() { return state == 2;}

    @Action
    public void takeCardCancel()
    {
        state = 0;
        adapter.takeCard();
    }

    public boolean swallowCardGuard(){ return state == 2;}

    @Action
    public void swallowCard()
    {
        state = 0;
        swallowed = true;
        adapter.swallowCard();
    }

    public boolean pinCorrectGuard(){ return (state == 1 && retry >0);}

    @Action
    public void pinCorrect()
    {
        state = 6;
        adapter.inputPinCorrect();
    }

    public boolean pinIncorrectGuard(){ return (state == 1 && retry >0);}

    @Action
    public void pinIncorrect()
    {
        state = 3;
        retry--;
        adapter.inputPinIncorrect();
    }

    public boolean retryPinGuard() {return (state == 3 && retry >0);}

    @Action
    public void retryPin()
    {
        state = 1;
        adapter.retryPin();
    }

    public boolean blockCardGuard(){return (state == 3 && retry >= 0);}

    @Action
    public void blockCard()
    {
        state = 4;
        adapter.blockCard();
    }

    public boolean cardBlockedEjectionGuard() {return (state == 4);}

    @Action
    public void cardBlockedEjection()
    {
        state = 5;
        adapter.cardBlockedEjection();
    }

    public boolean cardBlockedTakenGuard(){return (state==5);}

    @Action
    public void cardBlockedTaken()
    {
        state = 0;
        adapter.takeCard();
    }

    public boolean enterValidAmountGuard(){return (state == 6 && amount > 0);}

    @Action
    public void enterValidAmount()
    {
        state = 7;
        amount = amount - 50;
        adapter.enterValidAmount();
    }

    public boolean enterInvalidAmountGuard(){return state == 6;}

    @Action
    public void enterInvalidAmount()
    {
        state = 2;
        adapter.enterInvalidAmount();
    }

    public boolean takeCardToGetBillsGuard(){return state == 7;}

    @Action
    public void takeCardToGetBills()
    {
        state = 8;
        adapter.takeCardToGetBills();
    }

    public boolean cardSwallowedBeforeBillsGuard(){return state == 7;}

    @Action
    public void cardSwallowedBeforeBills()
    {
        state = 0;
        swallowed = true;
        adapter.swallowCard();
    }

    public boolean takeBillsGuard(){return state == 8;}

    @Action
    public void takeBills()
    {
        state = 0;
        adapter.takeBills();
    }

    public boolean billsSwallowedGuard(){return state ==8;}

    @Action
    public void billsSwallowed()
    {
        state = 0;
        adapter.swallowBills();
    }

    /***
     * Main program
     */
    public static void main(String[] argv) {

        // initialization of the model
        ATM_FSM model = new ATM_FSM();
        TemporalyPattern patTem = new TemporalyPattern();

        /**
         * Test a system by making random walks through an EFSM model of the system.
         */
        //Tester tester = new RandomTester(model);

        /**
         * Test a system by making greedy walks through an EFSM model of the system.
         * A greedy random walk gives preference to transitions that have never been taken before.
         * Once all transitions out of a state have been taken, it behaves the same as a random walk.
         */
        //Tester tester = new GreedyTester(model);

        /**
         * Creates a GreedyTester that will terminate each test sequence after getLoopTolerance() visits to a state.
         */
        //AllRoundTester tester = new AllRoundTester(model);
        //tester.setLoopTolerance(3);

        /**
         * A test generator that looks N-levels ahead in the graph. It chooses the highest-valued
         * transition (action) that is enabled in the current state.
         * DEPTH = How far should we look ahead?
         * NEW_ACTION = How worthwhile is it to use a completely new action?
         * NEW_TRANS = How worthwhile is it to explore a new transition?
         */
        LookaheadTester tester = new LookaheadTester(model);
        tester.setDepth(10);
        tester.setNewActionValue(50);
        tester.setNewTransValue(100);  // priority on new transitions w.r.t. new actions

        // computes the graph to get coverage measure information
        tester.buildGraph();

        // usual paramaterization
        tester.addListener(new VerboseListener());
        tester.addListener(new StopOnFailureListener());
        tester.addCoverageMetric(new TransitionCoverage());
        tester.addCoverageMetric(new StateCoverage() {
            @Override
            public String getName() {
                return "Total state coverage";
            }
        });
        tester.addCoverageMetric(new ActionCoverage());

        // run the test generation (10 steps)  <-- CAN BE INCREASED TO PRODUCE MORE TESTS!
        tester.generate(26);
        patTem.automate11();
        patTem.automate12();

        // prints the coverage and quits the execution
        tester.printCoverage();
    }
}

