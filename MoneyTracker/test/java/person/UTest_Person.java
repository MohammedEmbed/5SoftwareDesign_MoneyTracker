package person;

import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UTest_Person {


    @Test
    void checkToStringOverride(){
        Person person1 = new Person("testperson","testBankNumber");
        assertEquals("testperson, testBankNumber",person1.toString());
    }
    @Test
    void compare2PeopleTest(){//Two different instantiations of a person are the same if their name and banknumber are the same
        Person person1 = new Person("testperson","testBankNumber");
        Person person2 = new Person("testperson","testBankNumber");
        assertEquals(person1, person2);
        assertEquals(person1.hashCode(),person2.hashCode());
    }



}