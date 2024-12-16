package controller;

import databases.PersonDatabase;
import databases.TicketDatabase;
import person.Person;
import ticket.Ticket;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Controller {

    private PersonDatabase personDB;
    private TicketDatabase ticketDB;

    public Controller(PersonDatabase pdb, TicketDatabase tdb){
        this.personDB=pdb;
        this.ticketDB=tdb;

    }

    public void addPerson(Person p){personDB.addPerson(p);}
    public void removePerson(Person p) {personDB.removePerson(p);}


    // compare method needed for checking names for tickets.
    public Person compare(String name, String banknumber){
        for(Person person : personDB.getAllPersons()){
            if(person.getName().equals(name) && person.getBankNumber().equals(banknumber)){
                return person;
            }
        }

        return null;
    }

    public void addTicket(String description, Person beneficiary, Double totalAmount, String paymentType, HashMap<Person, Double> payments){
        HashMap<Person, Double> debts = new HashMap<>();
        if (payments.equals("even")){
            int amountOfPeople = personDB.getAllPersons().size();
            double amountPerPerson = totalAmount / amountOfPeople;

            for (Person person : personDB.getAllPersons()){
                debts.put(person, amountPerPerson);

            }
        } else if (paymentType.equals("uneven")) {
            debts.putAll(payments);

        }
        Ticket ticket = new Ticket(description, beneficiary, totalAmount, paymentType, debts);
        ticketDB.addTicket(ticket);
        System.out.println("Ticket "+ ticket + "is added successfully!");
    }
    public void removeTicket(Ticket t) {ticketDB.removeTicket(t);}

    public Set<Ticket> getAllTickets(){
        return ticketDB.getAllTickets();
    }



}
