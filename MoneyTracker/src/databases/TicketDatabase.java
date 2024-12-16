package databases;

import observers.DatabaseObserver;
import ticket.Ticket;

import java.util.HashSet;

public class TicketDatabase {

    private static TicketDatabase instance=null;
    private HashSet<Ticket> db;
    private DatabaseObserver observer;

    private TicketDatabase(){
        db = new HashSet<>();
    }
    public static TicketDatabase getInstance(){
        if(instance == null){
            instance = new TicketDatabase();
        }
        return instance;
    }

    public void addObserver(DatabaseObserver observer) {
        this.observer=observer;
    }

    public void addTicket(Ticket t) {

        observer.updateTicketDB(t,true);
    }

    public void removeTicket(Ticket t) {

        observer.updateTicketDB(t,false);
    }
    public HashSet<Ticket> getAllTickets(){
        return new HashSet<>(db); // we return a copy
    }

}
