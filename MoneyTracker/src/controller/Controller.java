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
        HashSet<Ticket> allTickets = getAllTickets();
        HashSet<Person> group = personDB.getGroup();
        HashMap<Person,HashMap<Person,Double>> allDebts = new HashMap<>();
        for(Person p : group){//each person in the group
            HashMap<Person,Double> totalDebt = new HashMap<>();//their debt to everyone
            for(Ticket t : allTickets){//calculated from all tickets
                if (!p.equals(t.getBeneficiary())){//debt to someone else
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
        HashMap<Person, HashMap<Person, Double>> updates = new HashMap<>(); //temporary map that is used so that we don't directly change alldebts during iteration
        //this next part is going to be both illegal and unreadable I apologize
        for(Person person : allDebts.keySet()){//A person
            for(Person other : allDebts.get(person).keySet()){//their debt to someone
                if(allDebts.get(other).get(person)!=null){//that someone has debt to them too
                    double personToOther = allDebts.get(person).get(other);
                    double otherToPerson = allDebts.get(other).get(person);
                    if(otherToPerson < personToOther){//update larger debt and remove the smaller
                        updates.computeIfAbsent(person, k -> new HashMap<>()).put(other, personToOther - otherToPerson);
                        updates.computeIfAbsent(other, k -> new HashMap<>()).remove(person);
                    }else{
                        updates.computeIfAbsent(other, k -> new HashMap<>()).put(person, otherToPerson - personToOther);
                        updates.computeIfAbsent(person, k -> new HashMap<>()).remove(other);
                    }

                }
            }

        }
        for (Person person : updates.keySet()) {
            allDebts.put(person, updates.getOrDefault(person, new HashMap<>()));
        }

        return allDebts;
    }



    public boolean undoCommand(){
        if(commandStack.isEmpty()){
            return false;
        }
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
