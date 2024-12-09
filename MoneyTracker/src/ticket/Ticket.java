package ticket;

import person.Person;

import java.util.HashMap;

public class Ticket {
    private Person beneficiary;
    private Double total;
    private String paymentType;
    private HashMap<Person, Double> debts;

    public Ticket(Person beneficiary,Double total,String paymentType, HashMap<Person,Double> debts){
        this.beneficiary=beneficiary;
        this.total=total;
        this.paymentType=paymentType;
        this.debts=debts;

    }

    @Override
    public String toString(){//TODO: make Person and debts have a toString method
        return paymentType + " ticket: " + total + " paid by " + beneficiary + " paid for: " + debts;
    }



}
