package command;

import java.util.Stack;

public abstract class Command {

    //maybe not necessary
    int NOT_EXECUTED = 0;
    int EXECUTED_SUCCESSFULLY = 1;


    public abstract int execute();
    public abstract int unexecute();

}
