package person;

public class Person {
    private String name;
    private String bankNumber;
    public Person(String name, String bankNumber){
        this.name=name;
        this.bankNumber=bankNumber;
    }


    @Override
    public String toString(){

        return this.name;
    }


}
