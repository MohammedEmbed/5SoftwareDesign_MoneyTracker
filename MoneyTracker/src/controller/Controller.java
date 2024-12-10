package controller;

import databases.PersonDatabase;
import databases.TicketDatabase;
import person.Person;
import ticket.Ticket;

import java.util.HashSet;

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

    public void addTicket(Ticket ticket){
        ticketDB.addTicket(ticket);
    }
    public void removeTicket(Ticket t) {ticketDB.removeTicket(t);}



}
