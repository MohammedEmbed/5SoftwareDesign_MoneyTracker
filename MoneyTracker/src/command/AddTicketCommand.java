package command;

import databases.TicketDatabase;
import ticket.Ticket;

public class AddTicketCommand implements Command{

    private Ticket t;
    private TicketDatabase tdb;

    public AddTicketCommand(Ticket t){
        this.t=t;
        this.tdb=TicketDatabase.getInstance();
    }

    @Override
    public boolean execute() {
        return tdb.addTicket(t);
    }

    @Override
    public boolean unexecute() {
        return tdb.removeTicket(t);
    }
}
