package main.java.SVAM;

import nz.ac.waikato.modeljunit.*;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionCoverage;

/**
 *  Very first version of the FSM.
 *  Automaton focusing on the card authentication tries, abstracting the bills and money (no withdraw operation)
 */
public class SampleFSM implements FsmModel
{
    /** Variable representing the current state */
    private int state;

    /** Variable representing the current state */
    private ATMAdapter adapter;

    /**
     *  Constructor. Initializes the data.
     */
    public SampleFSM()
    {
        state = 0;
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
        adapter = new ATMAdapter();
    }

    /**
     *  Guard for the transition. Should be named after the transition name, suffixed by "Guard"
     */
    public boolean insertCardGuard() { return state == 0; }
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
        state = 1;
        // transmits the operation to the System Under Test
        adapter.cancel();
    }

    /***
     * Main program
     */
    public static void main(String[] argv) {

        // initialization of the model
        SampleFSM model = new SampleFSM();

        /**
         * Test a system by making random walks through an EFSM model of the system.
         */
        Tester tester = new RandomTester(model);

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
        //LookaheadTester tester = new LookaheadTester(model);
        //tester.setDepth(10);
        //tester.setNewActionValue(50);
        //tester.setNewTransValue(100);  // priority on new transitions w.r.t. new actions

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
        tester.generate(10);

        // prints the coverage and quits the execution
        tester.printCoverage();
    }
}
