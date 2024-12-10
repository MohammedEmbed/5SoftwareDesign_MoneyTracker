package ticket;

import person.Person;

import java.util.HashMap;
import java.util.Map;

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
        StringBuilder sb = new StringBuilder(paymentType + " ticket: €" + total + " paid by " + beneficiary + ". Paid for: [");
        String prefix = "";
        for(HashMap.Entry<Person, Double> entry: debts.entrySet()){
            sb.append(prefix);
            prefix=",";
            sb.append(entry.getKey()).append(": €").append(entry.getValue());
        }
        sb.append("]");
        return sb.toString();
    }



}
