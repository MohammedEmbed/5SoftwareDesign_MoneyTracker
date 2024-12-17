package command;

import java.util.Stack;

public interface Command {

    boolean execute();
    boolean unexecute();

}
