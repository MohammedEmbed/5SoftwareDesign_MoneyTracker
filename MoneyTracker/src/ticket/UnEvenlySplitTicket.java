package ticket;

import person.Person;

import java.util.HashMap;

public class UnEvenlySplitTicket extends Ticket{
    public UnEvenlySplitTicket(Person beneficiary, Double total, String paymentType, HashMap<Person, Double> debts) {
        super(beneficiary, total, paymentType, debts);
    }
}
