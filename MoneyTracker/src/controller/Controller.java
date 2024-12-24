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

    public HashMap<Person,HashMap<Person,Double>> calculateAllTickets(){

        //Adding all tickets to a total debt map
        HashSet<Ticket> allTickets = ticketDB.getAllTickets();
        HashSet<Person> group = personDB.getGroup();
        HashMap<Person,HashMap<Person,Double>> allDebts = new HashMap<>();
        for(Person p : group){
            HashMap<Person,Double> totalDebt = new HashMap<>();
            for(Ticket t : allTickets){
                if (!p.equals(t.getBeneficiary())){
                    if(totalDebt.get(t.getBeneficiary())!=null){
                        totalDebt.put(t.getBeneficiary(),totalDebt.get(t.getBeneficiary())+t.getDebts().get(p));
                    }else{
                        totalDebt.put(t.getBeneficiary(),t.getDebts().get(p));
                    }
                }
            }
            allDebts.put(p,totalDebt);
        }

        //running over the map and then subtracting/simplifying all debts

        //this next part is going to be both illegal and unreadable I apologize
        for(Person person : allDebts.keySet()){//A person
            for(Person other : allDebts.get(person).keySet()){//their debt to someone
                if(allDebts.get(other).get(person)!=null){//that someone has debt to them too
                    if(allDebts.get(other).get(person) < allDebts.get(person).get(other)){//update larger debt and remove the smaller
                        allDebts.get(person).put(other,allDebts.get(person).get(other)-allDebts.get(other).get(person));
                        allDebts.get(other).remove(person);
                    }else{
                        allDebts.get(other).put(person,allDebts.get(other).get(person)-allDebts.get(person).get(other));
                        allDebts.get(person).remove(other);
                    }

                }
            }

        }


        return allDebts;
    }



    public boolean undoCommand(){
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
