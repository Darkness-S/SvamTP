package main.java.SVAM;

import fr.ufc.m2info.svam.ATM;
import fr.ufc.m2info.svam.Account;
import fr.ufc.m2info.svam.Card;

public class ATMAdapter {

    ATM sut = new ATM();
    
    public void reset() {
        sut = new ATM();
    }
    
    public void insertCard() {
        // create a card with pin code 1234 and associated account of 100 euros in balance
        Card c = new Card(1234, new Account(100));
        System.out.println("Inserted card");
        sut.insertCard(c);
    }

    public void insertIncorrectCard() {
        // create a card with pin code 1234 and associated account of 100 euros in balance
        Card c = new Card(1234, new Account(100));
        sut.insertCard(c);
        for (int i=0; i<3; i++){
            sut.inputPin(4321);
        }
        sut.takeCard();
        System.out.println("Inserted invalid card");
        sut.insertCard(c);
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

    public void cancel() {
        System.out.println("Pressed cancel");
        sut.cancel();
    }

    public void takeCard(){
        System.out.println("Card taken");
        sut.takeCard();
    }
    
}
