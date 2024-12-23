package integration;

import controller.Controller;
import databases.PersonDatabase;
import databases.TicketDatabase;
import observers.DatabaseObserver;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;
import person.Person;
import ticket.Ticket;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class ITEST_Controller {
    PersonDatabase personDB = PersonDatabase.getInstance();
    TicketDatabase ticketDB = TicketDatabase.getInstance();
    Controller controller = new Controller(personDB, ticketDB);
    Person testPerson1 = new Person("test1","1");
    Person testPerson2 = new Person("test2","2");
    Person testPerson3 = new Person("test3","3");


    @Test
    void CalculateAllTicketsTest(){
        controller.addPerson(testPerson1);
        controller.addPerson(testPerson2);
        controller.addPerson(testPerson3);

        HashMap<Person,Double> debt1 = new HashMap<>();
        debt1.put(testPerson2,21.0);
        debt1.put(testPerson3,50.0);

        HashMap<Person,Double> debt2 = new HashMap<>();
        debt2.put(testPerson1,17.0);
        debt2.put(testPerson3,7.0);

        Ticket testTicket1 = new Ticket("airplane ride",testPerson1,71.0,"Uneven split",debt1);
        Ticket testTicket2 = new Ticket("restaurant",testPerson2,24.0,"Uneven split",debt2);

        controller.addTicket(testTicket1);
        controller.addTicket(testTicket2);
        controller.removeTicket(testTicket1);
        controller.undoCommand();
        HashMap<Person,HashMap<Person,Double>> totalDebt = controller.calculateAllTickets();

        HashMap<Person,HashMap<Person,Double>> expectedDebt = new HashMap<>();
        HashMap<Person,Double> testperson1Debt = new HashMap<>();//no debt
        expectedDebt.put(testPerson1,testperson1Debt);

        HashMap<Person,Double> testperson2Debt = new HashMap<>();
        testperson2Debt.put(testPerson1,4.0);
        expectedDebt.put(testPerson2,testperson2Debt);

        HashMap<Person,Double> testperson3Debt = new HashMap<>();
        testperson3Debt.put(testPerson1,50.0);
        testperson3Debt.put(testPerson2,7.0);
        expectedDebt.put(testPerson3,testperson3Debt);

        assertEquals(expectedDebt,totalDebt);
    }


    @Test
    void undoCommand(){
        HashSet<Person> expectedGroup = controller.getGroup();
        controller.removePerson(testPerson1);
        controller.undoCommand();
        assertEquals(expectedGroup,controller.getGroup());
    }

}
