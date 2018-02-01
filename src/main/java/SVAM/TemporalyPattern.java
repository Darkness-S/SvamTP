package main.java.SVAM;

import main.java.automaton.finite.FiniteAutomaton;
import main.java.automaton.finite.FiniteState;

public class TemporalyPattern  {

    // 1. It is not possible to get the bills without a correct authentication.

    /** never bill_ejection(E0)
     *  before pincode_OK(E1)
     */

    public FiniteAutomaton automate11(){

        final FiniteState s0 = new FiniteState("s0");
        final FiniteState s1 = new FiniteState("s1");
        final FiniteState s2 = new FiniteState("s2");
        final FiniteState sx = new FiniteState("sx");

        s1.setFinal();
        sx.setBlock();
        s0.addTransition(s1,"E1",'1');
        s0.addTransition(s2,"E0",'0');
        s0.addTransition(s0,"somme-{E0,E1}",'o');
        s1.addTransition(s1,"somme",'o');
        s1.addTransition(s1,"somme",'1');
        s1.addTransition(s1,"somme",'0');
        s2.addTransition(sx,"E1",'1');
        s2.addTransition(s2,"somme-{E1}",'o');
        s2.addTransition(s2,"somme-{E1}",'0');
        sx.addTransition(sx, "somme", '0');
        sx.addTransition(sx, "somme", '1');
        sx.addTransition(sx, "somme", 'o');

        FiniteAutomaton automaton = new FiniteAutomaton(s0);

        return automaton;

    }

    /** never bill_ejection (E0)
     *  after pincode_KO (E1)
     *  until pincode_OK (E2)
     */

    public FiniteAutomaton automate12(){
        final FiniteState s0 = new FiniteState("s0");
        final FiniteState s1 = new FiniteState("s1");
        final FiniteState sx = new FiniteState("sx");

        s0.setFinal();
        sx.setBlock();

        s0.addTransition(s0,"somme-{E1}",'o');
        s0.addTransition(s0,"somme-{E1}",'0');
        s0.addTransition(s0,"somme-{E1}",'2');
        s0.addTransition(s1,"E1",'1');
        s1.addTransition(s0,"E2",'2');
        s1.addTransition(s1,"somme-{E2,E0}",'o');
        s1.addTransition(s1,"somme-{E2,E0}",'1');
        s1.addTransition(sx,"E0",'0');
        sx.addTransition(sx,"somme",'0');
        sx.addTransition(sx,"somme",'1');
        sx.addTransition(sx,"somme",'o');

        FiniteAutomaton automaton = new FiniteAutomaton(s0);
        return automaton;
    }


    //2. The customer has to take back his/her card before taking the bills

    /** never bill_ejection(E0)
     *  before card_ejection(E1)
     */
    public FiniteAutomaton automate21(){

        final FiniteState s0 = new FiniteState("s0");
        final FiniteState s1 = new FiniteState("s1");
        final FiniteState s2 = new FiniteState("s2");
        final FiniteState sx = new FiniteState("sx");

        s1.setFinal();
        sx.setBlock();

        s0.addTransition(s1,"E1",'1');
        s0.addTransition(s2,"E0",'0');
        s0.addTransition(s0,"somme-{E0,E1}",'o');
        s1.addTransition(s1,"somme",'o');
        s1.addTransition(s1,"somme",'1');
        s1.addTransition(s1,"somme",'0');
        s2.addTransition(sx,"E1",'1');
        s2.addTransition(s2,"somme-{E1}",'o');
        s2.addTransition(s2,"somme-{E1}",'0');
        sx.addTransition(sx, "somme", '0');
        sx.addTransition(sx, "somme", '1');
        sx.addTransition(sx, "somme", 'o');

        FiniteAutomaton automaton = new FiniteAutomaton(s0);

        return automaton;

    }

    /** never bill_ejection (E0)
     *  after card_ejection (E1)
     *  until card_taken (E2)
     */

    public FiniteAutomaton automate22(){
        final FiniteState s0 = new FiniteState("s0");
        final FiniteState s1 = new FiniteState("s1");
        final FiniteState sx = new FiniteState("sx");

        s0.setFinal();
        sx.setBlock();

        s0.addTransition(s0,"somme-{E1}",'o');
        s0.addTransition(s0,"somme-{E1}",'0');
        s0.addTransition(s0,"somme-{E1}",'2');
        s0.addTransition(s1,"E1",'1');
        s1.addTransition(s0,"E2",'2');
        s1.addTransition(s1,"somme-{E2,E0}",'o');
        s1.addTransition(s1,"somme-{E2,E0}",'1');
        s1.addTransition(sx,"E0",'0');
        sx.addTransition(sx,"somme",'0');
        sx.addTransition(sx,"somme",'1');
        sx.addTransition(sx,"somme",'o');

        FiniteAutomaton automaton = new FiniteAutomaton(s0);
        return automaton;
    }


    //5. One can only input an amount to withdraw if (s)he is first authenticated

    /** never select_amount(E0)
     *  before pin_OK(E1)
     */
    public FiniteAutomaton automate51(){

        final FiniteState s0 = new FiniteState("s0");
        final FiniteState s1 = new FiniteState("s1");
        final FiniteState s2 = new FiniteState("s2");
        final FiniteState sx = new FiniteState("sx");

        s1.setFinal();
        sx.setBlock();
        s0.addTransition(s1,"E1",'1');
        s0.addTransition(s2,"E0",'0');
        s0.addTransition(s0,"somme-{E0,E1}",'o');
        s1.addTransition(s1,"somme",'o');
        s1.addTransition(s1,"somme",'1');
        s1.addTransition(s1,"somme",'0');
        s2.addTransition(sx,"E1",'1');
        s2.addTransition(s2,"somme-{E1}",'o');
        s2.addTransition(s2,"somme-{E1}",'0');
        sx.addTransition(sx, "somme", '0');
        sx.addTransition(sx, "somme", '1');
        sx.addTransition(sx, "somme", 'o');

        FiniteAutomaton automaton = new FiniteAutomaton(s0);

        return automaton;

    }

    /** eventually select_amount(E0)
     * at least 0 times
     * between pin_OK (E1)
     * and card_ejection (E2)
     */

    public FiniteAutomaton automate52(){
        final FiniteState s0 = new FiniteState("s0");
        final FiniteState s1 = new FiniteState("s1");

        s0.setFinal();

        s0.addTransition(s0,"somme-{E1}",'o');
        s0.addTransition(s0,"somme-{E1}",'0');
        s0.addTransition(s0,"somme-{E1}",'2');
        s0.addTransition(s1,"E1",'1');
        s1.addTransition(s0,"E2",'2');
        s1.addTransition(s1,"E0",'0');
        s1.addTransition(s1,"somme-{E0,E2}",'o');
        s1.addTransition(s1,"somme-{E0,E2}",'1');

        FiniteAutomaton automaton = new FiniteAutomaton(s0);

        return automaton;
    }

    //6. It is not possible to insert a card until the previous transaction is not over.

    /** always Wait(E0)
     *  after last card_ejection(E1)
     */

    public FiniteAutomaton automate61(){
        final FiniteState s0 = new FiniteState("s0");
        final FiniteState s1 = new FiniteState("s1");
        final FiniteState sx = new FiniteState("sx");

        s0.setFinal();
        sx.setBlock();

        s0.addTransition(s1,"E1",'1');
        s0.addTransition(s0,"somme-{E1}",'o');
        s0.addTransition(s0,"somme-{E1}",'0');
        s1.addTransition(sx,"somme-{E0}",'o');
        s1.addTransition(sx,"somme-{E0}",'1');
        s1.addTransition(s0,"E0",'0');
        sx.addTransition(sx, "somme",'0');
        sx.addTransition(sx, "somme",'1');
        sx.addTransition(sx, "somme",'o');

        FiniteAutomaton automaton = new FiniteAutomaton(s0);

        return automaton;
    }

    /** never Wait(E0)
     * between pin_code_asked(E1)
     * and card_ejection(E2)
     */

    public FiniteAutomaton automate62(){
        final FiniteState s0 = new FiniteState("s0");
        final FiniteState s1 = new FiniteState("s1");
        final FiniteState sx = new FiniteState("sx");

        s0.setFinal();
        sx.setBlock();

        s0.addTransition(s0,"somme-{E1}",'o');
        s0.addTransition(s0,"somme-{E1}",'0');
        s0.addTransition(s0,"somme-{E1}",'2');
        s0.addTransition(s1,"E1",'1');
        s1.addTransition(s0,"E2",'2');
        s1.addTransition(s1,"somme-{E0,E2}",'o');
        s1.addTransition(s1,"somme-{E0,E2}",'1');
        s1.addTransition(sx,"E0",'0');
        sx.addTransition(sx,"somme",'0');
        sx.addTransition(sx,"somme",'1');
        sx.addTransition(sx,"somme",'o');

        FiniteAutomaton automaton = new FiniteAutomaton(s0);

        return automaton;
    }

    /** eventually bill_ejection(E0)
     *  at least 1 time
     *  between card_ejection (E1)
     *  and Wait(E2)
     */


    public FiniteAutomaton automate63(){
        final FiniteState s0 = new FiniteState("s0");
        final FiniteState s1 = new FiniteState("s1");
        final FiniteState s2 = new FiniteState("s2");
        final FiniteState sx = new FiniteState("sx");

        s0.setFinal();
        sx.setBlock();

        s0.addTransition(s1,"E1",'1');
        s0.addTransition(s0,"somme-{E1}",'o');
        s0.addTransition(s0,"somme-{E1}",'0');
        s0.addTransition(s0,"somme-{E1}",'2');
        s1.addTransition(s1,"somme-{E0,E2}",'o');
        s1.addTransition(s1,"somme-{E0,E2}",'1');
        s1.addTransition(s2,"E0",'0');
        s1.addTransition(s0,"E2",'2');
        s2.addTransition(s0,"E2",'2');
        s2.addTransition(s2,"somme-{E0,E2}",'s');
        s2.addTransition(s2,"somme-{E0,E2}",'1');
        s2.addTransition(sx,"E0",'0');
        sx.addTransition(sx,"somme",'0');
        sx.addTransition(sx,"somme",'1');
        sx.addTransition(sx,"somme",'2');
        sx.addTransition(sx,"somme",'o');

        FiniteAutomaton automaton = new FiniteAutomaton(s0);

        return automaton;
    }
}
