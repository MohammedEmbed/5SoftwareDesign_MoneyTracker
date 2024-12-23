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
        ticketDB.addObserver(observer);
        Controller controller = new Controller(personDB, ticketDB);

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
