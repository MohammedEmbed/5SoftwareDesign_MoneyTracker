package databases;

import java.util.HashSet;

public class PersonDatabase {
 // heeft set van personen en hoe hasht (hoe zie ik de verscchillende personen door bankrekening)
    private static HashSet<Person> PersonSet; //singleton pattern
    private PersonDatabase(){
        PersonSet = new HashSet<>();
    }

    public static HashSet<Person> getInstance(){
        if (PersonSet == null){
            PersonSet = new HashSet<>();
        }

        return PersonSet ;
    }

}
