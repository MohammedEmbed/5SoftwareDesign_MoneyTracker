package view;

import controller.Controller;
import person.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JSwingView implements AbstractView{

    private Controller controller;
    private JFrame menuFrame;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    private JPanel menuPanel;
    private JPanel groupPanel;
    private JPanel ticketPanel;
    private JPanel calculatePanel;
    private JPanel personPannel;
    private JTextField personField;
    private JTextField bankNumberField;

    private JTextField amount;

    private JComboBox comboBox;



    public JSwingView(Controller controller){

        this.controller=controller;
        this.menuFrame = new JFrame(){};
        this.cardLayout = new CardLayout();
        this.cardPanel = new JPanel();

        this.menuPanel = new JPanel(){};
        this.groupPanel = new JPanel();
        this.ticketPanel = new JPanel();
        this.calculatePanel = new JPanel();
        this.personPannel = new JPanel();

        initialize();
    }

    private void initialize() {
        menuFrame.setTitle("MoneyTracker");
        menuFrame.setSize(600,300);
        cardPanel.setLayout(cardLayout);

        initMainMenu();
        initGroupMenu();
        initTicketMenu();
        initCalculateMenu();
        cardPanel.add(menuPanel,"menu");
        cardPanel.add(groupPanel,"group");
        cardPanel.add(ticketPanel,"ticket");
        cardPanel.add(calculatePanel,"calculate");

        menuFrame.add(cardPanel);

    }

    private void initMainMenu(){
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
    }

    private void initGroupMenu(){
        //groupPanel
        groupPanel.setLayout(new BorderLayout());
        groupPanel.add(new JLabel("Group"));

        JButton groupToMenuButton = new JButton("Back");
        groupPanel.add(groupToMenuButton,BorderLayout.NORTH);
        groupToMenuButton.addActionListener(e -> mainMenuEvent());


        JButton addPersonButton = new JButton("Add Person");
        personField = new JTextField("name");
        bankNumberField = new JTextField("banknumber");
        groupPanel.add(addPersonButton, BorderLayout.EAST);
        addPersonButton.addActionListener(e -> addPersonEvent());
        groupPanel.add(personField,BorderLayout.WEST);
        groupPanel.add(bankNumberField);
        JPanel groupList = new JPanel();
        groupList.setLayout(new BoxLayout(groupList,BoxLayout.Y_AXIS));
        groupList.add(new JLabel("person1:"));
        groupList.add(new JLabel("person2:"));
        groupList.add(new JLabel("person3:"));


        groupPanel.add(groupList,BorderLayout.SOUTH);

    }
    private void initTicketMenu(){
        //ticketPanel
        ticketPanel.add(new JLabel("Ticket"));
        JButton ticketToMenuButton = new JButton("Back");
        ticketPanel.add(ticketToMenuButton);
        ticketToMenuButton.addActionListener(e -> mainMenuEvent());

        personField = new JTextField("name");
        bankNumberField = new JTextField("banknumber");
        ticketPanel.add(personField);
        ticketPanel.add(bankNumberField);

        String [] types = {"Restaurant", "Airplane", "Taxi", "Concerts", "Others"};
        comboBox = new JComboBox(types);
        ticketPanel.add(comboBox);

        JRadioButton Kind1 = new JRadioButton("even");
        JRadioButton Kind2 = new JRadioButton("uneven");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(Kind1);
        buttonGroup.add(Kind2);
        ticketPanel.add(Kind1);
        ticketPanel.add(Kind2);

        amount = new JTextField("0.0");
        ticketPanel.add(amount);

        JButton addTicketButton = new JButton("Add the ticket");
        ticketPanel.add(addTicketButton);
        addTicketButton.addActionListener(e -> addTicketMenuEvent());


    }
    private void initCalculateMenu(){
        //calculatePanel
        calculatePanel.add(new JLabel("Calculate"));

        JButton calculateToMenuButton = new JButton("Back");
        calculatePanel.add(calculateToMenuButton);
        calculateToMenuButton.addActionListener(e -> mainMenuEvent());
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
    public void addPersonEvent() {//tell the controller to update the person Database with a person
        //we send to controller to add the person.
        String name = personField.getText();
        String bankNumber = bankNumberField.getText();
        Person person = new Person(name, bankNumber);

        if (!name.isEmpty()){
            controller.addPerson(person);
            personField.setText("");
            bankNumberField.setText("");
            System.out.println(person);
        } else {
            JOptionPane.showMessageDialog(null, "Name CANNOT be empty!!");

        }
        //TODO: update groupPanel to show new person



    }

    @Override
    public void addTicketMenuEvent() {
        String description = comboBox.getSelectedItem().toString();
        String name = personField.getText();
        String bankNumber = bankNumberField.getText();
        Double total = Double.parseDouble(amount.getText());

        //get person from database
        /*
        if(name == "name from database" && banknumber == "name from banknumber"){
            controller.addTicket(description, name, );
        }*/



    }

    @Override
    public void calculateDebtEvent() {

    }

}
