package main.java.SVAM;

import fr.ufc.m2info.svam.ATM;
import fr.ufc.m2info.svam.Account;
import fr.ufc.m2info.svam.Card;

public class ATMAdapter {

    ATM sut = new ATM();
    
    public void reset() {
        System.out.println("Reset !!!");
        sut = new ATM();
    }
    
    public void insertCard() {
        // create a card with pin code 1234 and associated account of 100 euros in balance
        Card c = new Card(1234, new Account(100));
        System.out.println("Inserted card");
        sut.insertCard(c);
    }

    public void insertIncorrectCard() {
        // create a card with pin code 1234 and associated account of 100 euros in balance but blocked
        Card c = new Card(1234, new Account(100));
        sut.insertCard(c);
        for (int i=0; i<3; i++){
            sut.inputPin(4321);
        }
        sut.takeCard();
        System.out.println("Inserted invalid card");
        sut.insertCard(c);
    }

    public void insertSwallowedCard()
    {
        System.out.println("Can't insert a swallowed card");
    }

    public void inputPinCorrect(){
        int pin = 1234;
        System.out.println("Correct pin entered");
        sut.inputPin(pin);
    }

    public void inputPinIncorrect(){
        int pin = 4321;
        System.out.println("Incorrect pin entered");
        sut.inputPin(pin);
    }

    public void retryPin(){
        System.out.println("Retry entering pin");
    }

    public void blockCard(){
        System.out.println("Incorrect pin entered 3 times. Card blocked");
    }

    public void cardBlockedEjection(){
        System.out.println("Blocked card ejected");
    }

    public void enterValidAmount(){
        System.out.println("Draw 50€ (enough). Money left on account : ");
        sut.chooseAmount(50);
    }

    public void enterInvalidAmount(){
        System.out.println("Draw 150€ (too much)");
        sut.chooseAmount(150);
    }

    public void cancel() {
        System.out.println("Pressed cancel");
        sut.cancel();
    }

    public void takeCard(){
        System.out.println("Card taken");
        sut.takeCard();
    }

    public void takeCardToGetBills(){
        System.out.println("Card taken : bill ejected");
        sut.takeCard();
    }

    public void takeBills(){
        System.out.println("Bill taken");
        sut.takeBills();
    }

    public void swallowCard(){
        System.out.println("6 seconds timeout : card swallowed");
    }

    public void swallowBills(){
        System.out.println("6 seconds timeout : bills swalloed");
    }
}
