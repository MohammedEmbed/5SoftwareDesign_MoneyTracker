package controller;

import command.*;
import databases.PersonDatabase;
import databases.TicketDatabase;
import person.Person;
import ticket.Ticket;

import java.util.HashMap;
import java.util.HashSet;

import java.util.Stack;

public class Controller {

    private PersonDatabase personDB;
    private TicketDatabase ticketDB;
    private Stack<Command> commandStack;

    public Controller(PersonDatabase pdb, TicketDatabase tdb){
        this.personDB=pdb;
        this.ticketDB=tdb;
        this.commandStack = new Stack<>();

    }


    public boolean isInGroup(Person p){
        return personDB.contains(p);
    }

    public HashMap<Person,HashMap<Person,Double>> calculateAllTickets(){//TODO: Unit Test

        HashSet<Ticket> allTickets = ticketDB.getAllTickets();
        HashSet<Person> group = personDB.getGroup();
        HashMap<Person,HashMap<Person,Double>> debtsPerPerson = new HashMap<>();


        return null;
    }



    public boolean undoCommand(){ //TODO: Unit Test
        Command command = commandStack.pop();
        return command.unexecute();
    }

    public void addPerson(Person p){
        AddPersonCommand command = new AddPersonCommand(p);
        if(command.execute()){
            commandStack.add(command);
        }
    }

    public void removePerson(Person p) {
        RemovePersonCommand command = new RemovePersonCommand(p);
        if(command.execute()){
            commandStack.add(command);
        }
    }

    public void addTicket(Ticket t){
        AddTicketCommand command = new AddTicketCommand(t);
        if(command.execute()){
            commandStack.add(command);
        }
    }

    public void removeTicket(Ticket t) {
        RemoveTicketCommand command = new RemoveTicketCommand(t);
        if(command.execute()){
            commandStack.add(command);
        }
    }

    public HashSet<Person> getGroup() { return personDB.getGroup();}
    public HashSet<Ticket> getAllTickets(){
        return ticketDB.getAllTickets();
    }



}
