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

    public boolean addTicket(Ticket t) {
        if(this.observer!=null) {
            observer.updateTicketDB(t, true);
        }
        return this.db.add(t);
    }

    public boolean removeTicket(Ticket t) {//TODO: check if logs should actually be updated with the removal of the ticket.
        if(this.observer!=null) {
            observer.updateTicketDB(t, false);
        }
        return this.db.remove(t);
    }
    public HashSet<Ticket> getAllTickets(){
        return new HashSet<>(db); // we return a copy
    }

}
