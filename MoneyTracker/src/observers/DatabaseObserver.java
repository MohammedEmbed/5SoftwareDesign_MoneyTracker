package observers;


import person.Person;
import ticket.Ticket;
import view.AbstractView;

public class DatabaseObserver {

    private AbstractView view;

    public  DatabaseObserver(AbstractView view){
        this.view = view;
    }

    public void updatePersonDB(Person p){

    }

    public void updateTicketDB(Ticket t){

    }
}
