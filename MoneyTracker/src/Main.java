import controller.Controller;
import databases.PersonDatabase;
import databases.TicketDatabase;
import observers.DatabaseObserver;
import person.Person;
import ticket.Ticket;
import view.AbstractView;
import viewfactories.AbstractViewFactory;
import viewfactories.JSwingViewFactory;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Objects;
import java.util.Properties;

public class Main {


    public static void main(String[] args){

        Properties properties = loadProperties();

        PersonDatabase personDB = PersonDatabase.getInstance();
        TicketDatabase ticketDB = TicketDatabase.getInstance();
        DatabaseObserver observer = new DatabaseObserver();
        Controller controller = new Controller(personDB, ticketDB);
        ticketDB.addObserver(observer);

        if(Objects.equals(properties.getProperty("view.type"),"JSwing")){
            AbstractViewFactory factory = new JSwingViewFactory();
            AbstractView view = factory.createView(controller);
            view.render();
        }else if(Objects.equals(properties.getProperty("view.type"),"Terminal")) {
            AbstractView view = createTerminalView(controller);
            view.render();
        }else {
            System.out.println("Error: View type could not be loaded.");
        }

        Person testPerson = new Person("test","1");
        Person testPerson2 = new Person("test2","2");
        HashMap<Person,Double> map = new HashMap<>();
        map.put(testPerson2,21.0);
        Ticket testTicket = new Ticket("airplane ride",testPerson,21.0,"Even split",map);
        controller.addTicket(testTicket);
        controller.removeTicket(testTicket);

    }


    private static Properties loadProperties() {
        try {
            String rootPath = "MoneyTracker/src/util/";
            String appConfigPath = rootPath + "config.properties";

            Properties properties = new Properties();
            properties.load(new FileInputStream(appConfigPath));
            return properties;
        }catch(Exception e){
            System.out.println("Error: Can't load properties correctly");
            e.printStackTrace();
        }
        return null;
    }


    private static AbstractView createTerminalView(Controller controller){

        return null;
    }
}
