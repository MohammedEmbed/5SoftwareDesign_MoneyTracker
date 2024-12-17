package command;

import databases.TicketDatabase;
import ticket.Ticket;

public class RemoveTicketCommand implements Command{

    private Ticket t;
    private TicketDatabase tdb;


    public RemoveTicketCommand(Ticket t){
        this.t=t;
        this.tdb=TicketDatabase.getInstance();
    }
    @Override
    public boolean execute() {
        return tdb.removeTicket(t);
    }

    @Override
    public boolean unexecute() {
        return tdb.addTicket(t);
    }
}
