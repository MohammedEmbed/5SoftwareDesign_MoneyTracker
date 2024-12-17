package command;

import databases.PersonDatabase;
import person.Person;

public class AddPersonCommand implements Command{

    private Person p;
    private PersonDatabase pdb;

    public AddPersonCommand(Person p){
        this.p=p;
        this.pdb=PersonDatabase.getInstance();
    }

    @Override
    public boolean execute() {
        return pdb.addPerson(p);
    }

    @Override
    public boolean unexecute() {
        return pdb.removePerson(p);
    }
}
