package controller;

import databases.PersonDatabase;
import databases.TicketDatabase;

public class Controller {

    private PersonDatabase personDB;
    private TicketDatabase ticketDB;

    public Controller(PersonDatabase pdb, TicketDatabase tdb){
        this.personDB=pdb;
        this.ticketDB=tdb;

    }



}
