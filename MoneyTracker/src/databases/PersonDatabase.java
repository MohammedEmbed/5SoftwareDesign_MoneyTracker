package databases;

import person.Person;

import java.util.HashSet;

public class PersonDatabase {
    private static PersonDatabase instance;
    private static HashSet<Person> db; //singleton pattern
    private PersonDatabase(){
        db = new HashSet<>();
    }

    public static PersonDatabase getInstance(){
        if (instance == null){
            instance = new PersonDatabase();
        }

        return instance;
    }

    public boolean addPerson(Person person){
        return db.add(person);
    }

    public boolean removePerson(Person person){
        return db.remove(person);
    }

    public boolean contains(Person p){
        return db.contains(p);
    }

    public HashSet<Person> getGroup(){
        return new HashSet<>(db);
        // I need function to compare if person that makes a ticket is a person from database
        //Return new HashSet to make copy so that we don't ruin the original.
    }

}
