package ticket;

import person.Person;

import java.util.HashMap;

public class EvenlySplitTicket extends Ticket{
    public EvenlySplitTicket(Person beneficiary, Double total, String paymentType, HashMap<Person, Double> debts) {
        super(beneficiary, total, paymentType, debts);
    }
}
