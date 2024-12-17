package person;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o){
        if(o.getClass()!=this.getClass()){
            return false;
        }
        Person other = (Person) o;

        return Objects.equals(this.name, other.getName()) && Objects.equals(this.bankNumber, other.getBankNumber());
    }

    @Override
    public int hashCode() {
        return name.hashCode()+bankNumber.hashCode();
    }

}
