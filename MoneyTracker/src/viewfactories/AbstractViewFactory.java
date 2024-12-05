package viewfactories;

import databases.PersonDatabase;
import databases.TicketDatabase;
import view.AbstractView;

public interface AbstractViewFactory {
    AbstractView createView();

    default PersonDatabase createPersonDatabase(){
        return PersonDatabase.getInstance();
    }

    default TicketDatabase createTicketDatabase(){
        return TicketDatabase.getInstance();
    }
}
