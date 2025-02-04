package ticket;

import person.Person;

import java.util.HashMap;

public class Ticket {
    private String description;
    private Person beneficiary;
    private Double total;
    private String paymentType;
    private HashMap<Person, Double> debts;

    public Ticket(String description,Person beneficiary,Double total,String paymentType, HashMap<Person,Double> debts){
        this.description=description;
        this.beneficiary=beneficiary;
        this.total=total;
        this.paymentType=paymentType;
        this.debts =debts;

    }

    public Person getBeneficiary() {
        return beneficiary;
    }

    public Double getTotal() {
        return total;
    }

    public HashMap<Person, Double> getDebts() {
        return debts;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(paymentType + " ticket: '"+description+"' €" + total + " paid by " + beneficiary + ". Paid for: [");
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
