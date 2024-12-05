package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JSwingView implements AbstractView{

    //TODO: add private Controller controller
    private JFrame menuFrame;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    private JPanel menuPanel;
    private JPanel groupPanel;
    private JPanel ticketPanel;
    private JPanel calculatePanel;


    public JSwingView(){

        this.menuFrame = new JFrame(){};
        this.cardLayout = new CardLayout();
        this.cardPanel = new JPanel();

        this.menuPanel = new JPanel(){};
        this.groupPanel = new JPanel();
        this.ticketPanel = new JPanel();
        this.calculatePanel = new JPanel();


        initialize();
    }

    private void initialize() {
        menuFrame.setTitle("MoneyTracker");
        menuFrame.setSize(600,300);
        cardPanel.setLayout(cardLayout);


        //menuPanel
        menuPanel.add(new JLabel("Menu"));
        JButton menuToGroupButton = new JButton("Group");
        menuPanel.add(menuToGroupButton);
        menuToGroupButton.addActionListener(e -> groupMenuEvent());

        JButton menuToTicketButton = new JButton("Tickets");
        menuPanel.add(menuToTicketButton);
        menuToTicketButton.addActionListener(e -> ticketMenuEvent());

        JButton menuToCalculateButton = new JButton("Calculate");
        menuPanel.add(menuToCalculateButton);
        menuToCalculateButton.addActionListener(e -> calculateMenuEvent());

        //groupPanel
        groupPanel.setLayout(new BorderLayout());
        groupPanel.add(new JLabel("Group"));

        JButton groupToMenuButton = new JButton("Back");
        groupPanel.add(groupToMenuButton,BorderLayout.NORTH);
        groupToMenuButton.addActionListener(e -> mainMenuEvent());

        JPanel groupList = new JPanel();
        groupList.setLayout(new BoxLayout(groupList,BoxLayout.Y_AXIS));
        groupList.add(new JLabel("person1:"));
        groupList.add(new JLabel("person2:"));
        groupList.add(new JLabel("person3:"));


        groupPanel.add(groupList,BorderLayout.SOUTH);

        //ticketPanel
        ticketPanel.add(new JLabel("Ticket"));
        JButton ticketToMenuButton = new JButton("Back");
        ticketPanel.add(ticketToMenuButton);
        ticketToMenuButton.addActionListener(e -> mainMenuEvent());

        //calculatePanel
        calculatePanel.add(new JLabel("Calculate"));

        JButton calculateToMenuButton = new JButton("Back");
        calculatePanel.add(calculateToMenuButton);
        calculateToMenuButton.addActionListener(e -> mainMenuEvent());


        cardPanel.add(menuPanel,"menu");
        cardPanel.add(groupPanel,"group");
        cardPanel.add(ticketPanel,"ticket");
        cardPanel.add(calculatePanel,"calculate");

        menuFrame.add(cardPanel);

    }


    @Override
    public void render() {
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setVisible(true);
    }

    @Override
    public void mainMenuEvent(){
        cardLayout.show(cardPanel,"menu");

    }

    @Override
    public void groupMenuEvent() {
        cardLayout.show(cardPanel,"group");

    }

    @Override
    public void ticketMenuEvent() {
        cardLayout.show(cardPanel,"ticket");
    }

    @Override
    public void calculateMenuEvent() {
        cardLayout.show(cardPanel,"calculate");

    }

    @Override
    public void extraMenuEvent() {
        cardLayout.show(cardPanel,"extra");

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
