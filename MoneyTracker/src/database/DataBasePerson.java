package database;

import person.Person;

import java.util.HashSet;

public class DataBasePerson {
 // heeft set van personen en hoe hasht (hoe zie ik de verscchillende personen door bankrekening)
    private static HashSet<Person> PersonSet; //singleton pattern
    private DataBasePerson(){
        PersonSet = new HashSet<>();
    }

    public static synchronized HashSet<Person> getInstance(){
        if (PersonSet == null){
            PersonSet = new HashSet<>();
        }

        return PersonSet ;
    }

}
