package databases;

import observers.DatabaseObserver;
import person.Person;

import java.util.HashSet;

public class PersonDatabase {
    private static PersonDatabase instance=null;
    private HashSet<Person> db; //singleton pattern
    private DatabaseObserver observer;

    private PersonDatabase(){
        this.db = new HashSet<>();
    }

    public static PersonDatabase getInstance(){
        if (instance == null){
            instance = new PersonDatabase();
        }

        return instance;
    }

    public void addObserver(DatabaseObserver observer) {
        this.observer=observer;
    }

    public boolean addPerson(Person p){
        if(this.observer!=null) {
            observer.updatePersonDB(p,true);
        }
            return db.add(p);

    }

    public boolean removePerson(Person p){
        if(this.observer!=null) {
            observer.updatePersonDB(p,false);
        }
        return db.remove(p);
    }

    public boolean contains(Person p){
        return db.contains(p);
    }

    public HashSet<Person> getGroup(){
        return new HashSet<>(this.db);
        // I need function to compare if person that makes a ticket is a person from database
        //Return new HashSet to make copy so that we don't ruin the original.
    }

}
