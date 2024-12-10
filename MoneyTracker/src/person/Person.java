package person;

public class Person {
    private String name;
    private String bankNumber;
    public Person(String name, String bankNumber){
        this.name=name;
        this.bankNumber=bankNumber;
    }

    public String getName() {
        return name;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    @Override
    public String toString(){

        return "name:" + name + ", Banknumber: "+ bankNumber;
    }


}
