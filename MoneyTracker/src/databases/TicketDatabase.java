package databases;

import observers.DatabaseObserver;
import ticket.Ticket;

import java.util.HashSet;

public class TicketDatabase {

    private TicketDatabase instance=null;
    private HashSet<Ticket> db;
    private DatabaseObserver observer;

    private TicketDatabase(){
        db = new HashSet<Ticket>();
    }
    public TicketDatabase getInstance(){
        if(this.instance == null){
            this.instance = new TicketDatabase();
        }
        return instance;
    }

    public void addObserver(DatabaseObserver observer) {
        this.observer=observer;
    }

    public void addTicket(Ticket t) {

        observer.updateTicketDB(t);
    }


}
