package observers;


import person.Person;
import ticket.Ticket;
import view.AbstractView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class DatabaseObserver {

    private AbstractView view;
    private File log;
    private Calendar calendar;

    public  DatabaseObserver(){
        this.calendar = Calendar.getInstance();
        createlog();
    }

    private void createlog() {
        try {
            this.log = new File("Moneytracker/logs/history.log");
            if(log.createNewFile()){
                System.out.println("log created successfully!");
            }else{//TODO: maybe instead of overwriting make a file with a timestamp in the name
                System.out.println("file exists already! Overwriting...");
                log.delete();
                log.createNewFile();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void log(String logEntry){
        try {
            FileWriter writer = new FileWriter("Moneytracker/logs/history.log",true);
            writer.write(logEntry);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: Could not write to log!");
        }
    }

    public void updatePersonDB(Person p, boolean isAdded){

        int year = this.calendar.get(Calendar.YEAR);
        int month = this.calendar.get(Calendar.MONTH);
        int day = this.calendar.get(Calendar.DAY_OF_MONTH);
        int hour = this.calendar.get(Calendar.HOUR_OF_DAY);
        int minute = this.calendar.get(Calendar.MINUTE);
        int second = this.calendar.get(Calendar.SECOND);


        String logAction = isAdded ? "Person added: " : "Person removed: ";
        String logEntry = "[" + year + "-" + month + "-" + day + " " + hour+":"+minute+":"+second+"] LOG: "+logAction + p+"\n";
        log(logEntry);
    }

    public void updateTicketDB(Ticket t, boolean isAdded){


        int year = this.calendar.get(Calendar.YEAR);
        int month = this.calendar.get(Calendar.MONTH);
        int day = this.calendar.get(Calendar.DAY_OF_MONTH);
        int hour = this.calendar.get(Calendar.HOUR_OF_DAY);
        int minute = this.calendar.get(Calendar.MINUTE);
        int second = this.calendar.get(Calendar.SECOND);


        String logAction = isAdded ? "Ticket added: " : "Ticket removed: ";
        String logEntry = "[" + year + "-" + month + "-" + day + " " + hour+":"+minute+":"+second+"] LOG: "+logAction + t+"\n";
        log(logEntry);
    }
}
