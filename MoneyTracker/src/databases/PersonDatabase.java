package databases;

import person.Person;

import java.util.HashSet;

public class PersonDatabase {
    private static PersonDatabase instance;
 // heeft set van personen en hoe hasht (hoe zie ik de verscchillende personen door bankrekening)
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

}
