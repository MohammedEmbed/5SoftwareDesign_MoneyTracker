package view;


import java.util.Scanner;

//unused
public class TerminalView implements AbstractView{

    private Scanner sc;

    public TerminalView(){
        this.sc = new Scanner(System.in);

    }

    @Override
    public void render() {
        System.out.println();

    }

    @Override
    public void mainMenuEvent() {

    }

    @Override
    public void groupMenuEvent() {

    }

    @Override
    public void ticketMenuEvent() {

    }

    @Override
    public void calculateMenuEvent() {

    }

    @Override
    public void extraMenuEvent() {

    }
    @Override
    public void undoEvent(){

    }

    @Override
    public void addPersonEvent() {

    }

    @Override
    public void addTicketMenuEvent() {

    }

    @Override
    public void calculateDebtEvent() {

    }
}
