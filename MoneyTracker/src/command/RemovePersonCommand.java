package command;

import databases.PersonDatabase;
import person.Person;

public class RemovePersonCommand implements Command{

    private Person p;
    private PersonDatabase pdb;

    public RemovePersonCommand(Person p){
        this.p=p;
        this.pdb=PersonDatabase.getInstance();
    }

    @Override
    public boolean execute() {
        return pdb.removePerson(p);
    }

    @Override
    public boolean unexecute() {
        return pdb.addPerson(p);
    }
}
