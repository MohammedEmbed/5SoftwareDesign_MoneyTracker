package view;

public interface AbstractView {

    //All render methods
    void render();


    //events to navigate the menu
    void mainMenuEvent();
    void groupMenuEvent();
    void ticketMenuEvent();
    void calculateMenuEvent();
    void extraMenuEvent();

    //events passed to the controller
    void addPersonEvent();
    void addTicketMenuEvent();
    void calculateDebtEvent();




}
