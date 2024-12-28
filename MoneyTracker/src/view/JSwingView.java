package view;

import controller.Controller;
import person.Person;
import ticket.Ticket;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class JSwingView implements AbstractView{

    private Controller controller;
    private JFrame menuFrame;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    private JPanel menuPanel;
    private JPanel groupPanel;
    private JPanel groupListPanel;
    private JPanel ticketPanel;
    private JPanel calculatePanel;
    private JPanel groupList;
    private JTextField personField;
    private JTextField beneficiaryNameField;
    private JTextField beneficiaryBankField;
    private JTextField personField2;
    private JTextField bankNumberField;
    private JTextField bankNumberField2;
     private JTextField totalAmount;
    private JTextField amountSpended;

    private JComboBox comboBox;
    private ButtonGroup buttonGroup;

    private HashMap<Person, Double> debts;



    public JSwingView(Controller controller){

        this.controller=controller;
        this.menuFrame = new JFrame(){};
        this.cardLayout = new CardLayout();
        this.cardPanel = new JPanel();

        this.menuPanel = new JPanel(){};
        this.groupPanel = new JPanel();
        this.ticketPanel = new JPanel();
        this.calculatePanel = new JPanel();
        this.debts = new HashMap<>();

        initialize();
    }

    private void initialize() {
        menuFrame.setTitle("MoneyTracker");
        menuFrame.setSize(600,300);
        menuFrame.setBackground(Color.WHITE);
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
        groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.Y_AXIS));
        groupPanel.add(new JLabel("Group"));
        groupPanel.setSize(600,300);

        //buttonPanel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton groupToMenuButton = new JButton("Back");
        buttonPanel.add(groupToMenuButton);
        groupToMenuButton.addActionListener(e -> mainMenuEvent());

        JButton addPersonButton = new JButton("Add Person");
        buttonPanel.add(addPersonButton);
        addPersonButton.addActionListener(e -> addPersonEvent());

        JButton undoButton = new JButton("Undo");
        buttonPanel.add(undoButton);
        undoButton.addActionListener(e -> undoEvent());

        groupPanel.add(buttonPanel);

        //personPanel
        JPanel personPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        personField = new JTextField(10);
        bankNumberField = new JTextField(20);
        personPanel.add(new JLabel("Name: "));
        personPanel.add(personField);
        personPanel.add(new JLabel("Banknumber: "));
        personPanel.add(bankNumberField);
        groupPanel.add(personPanel);


        //These names are bad I know I'm sorry
        groupListPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        groupList = new JPanel();
        groupList.setLayout(new BoxLayout(groupList,BoxLayout.Y_AXIS));
        groupListPanel.add(groupList);
        groupPanel.add(groupListPanel);



    }

    private void initTicketMenu(){
        //ticketPanel
        ticketPanel.setLayout(new BoxLayout(ticketPanel, BoxLayout.Y_AXIS));
        ticketPanel.add(new JLabel("Ticket"));


        //add buttons to add ticket, go back, and undo
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton ticketToMenuButton = new JButton("Back");
        buttonPanel.add(ticketToMenuButton);
        ticketToMenuButton.addActionListener(e -> mainMenuEvent());

        JButton addTicketButton = new JButton("Add the ticket");
        addTicketButton.addActionListener(e -> addTicketMenuEvent());
        buttonPanel.add(addTicketButton);

        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(e -> undoEvent());
        buttonPanel.add(undoButton);

        ticketPanel.add(buttonPanel);


        //beneficiary
        JPanel beneficiaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        beneficiaryPanel.add(new JLabel("Name of beneficiary: "));
        beneficiaryNameField = new JTextField(10);
        beneficiaryPanel.add(beneficiaryNameField);

        beneficiaryPanel.add(new JLabel("BankNumber: "));
        beneficiaryBankField = new JTextField(10);
        beneficiaryPanel.add(beneficiaryBankField);

        ticketPanel.add(beneficiaryPanel);

        //information of ticket: type and amount that was spent
        JPanel ticketInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String [] types = {"Restaurant", "Airplane", "Taxi", "Concerts", "Others"};
        comboBox = new JComboBox(types);
        ticketInfoPanel.add(new JLabel("Type:"));
        ticketInfoPanel.add(comboBox);

        ticketInfoPanel.add(new JLabel ("Total amount: "));
        totalAmount = new JTextField(10);
        ticketInfoPanel.add(totalAmount);
        ticketPanel.add(ticketInfoPanel);

        JPanel paymentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        paymentPanel.add(new JLabel("Payemnt Type: "));
        JRadioButton kind1 = new JRadioButton("even");
        kind1.setActionCommand("even");
        JRadioButton kind2 = new JRadioButton("uneven");
        kind2.setActionCommand("uneven");
        buttonGroup = new ButtonGroup();
        buttonGroup.add(kind1);
        buttonGroup.add(kind2);
        paymentPanel.add(kind1);
        paymentPanel.add(kind2);
        ticketPanel.add(paymentPanel);

        // information of the spender
        /*JPanel spenderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        spenderPanel.add(new JLabel("Name of spender "));
        personField2 = new JTextField(10);
        spenderPanel.add(personField2);

        spenderPanel.add(new JLabel("Banknumber of spender: "));
        bankNumberField2 = new JTextField(10);
        spenderPanel.add(bankNumberField2);

        spenderPanel.add(new JLabel("Amount spent: "));
        amountSpended = new JTextField(10);
        spenderPanel.add(amountSpended);

        ticketPanel.add(spenderPanel);
*/



    }
    private void initCalculateMenu(){
        //calculatePanel
        calculatePanel.add(new JLabel("Calculate"));

        JButton calculateToMenuButton = new JButton("Back");
        calculatePanel.add(calculateToMenuButton);
        calculateToMenuButton.addActionListener(e -> mainMenuEvent());
        JButton calculateButton = new JButton("calculate tickets");
        calculatePanel.add(calculateButton);
        calculateButton.addActionListener(e -> calculateDebtEvent());
    }



    @Override
    public void render() {
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setVisible(true);
    }

    @Override
    public void mainMenuEvent(){
        cardLayout.show(cardPanel,"menu");
        menuFrame.pack();
    }

    @Override
    public void groupMenuEvent() {
        cardLayout.show(cardPanel,"group");
        menuFrame.pack();
    }

    @Override
    public void ticketMenuEvent() {
        cardLayout.show(cardPanel,"ticket");
        menuFrame.pack();
    }

    @Override
    public void calculateMenuEvent() {
        cardLayout.show(cardPanel,"calculate");
        menuFrame.pack();
    }

    @Override
    public void extraMenuEvent() {
        cardLayout.show(cardPanel,"extra");
        menuFrame.pack();
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
            System.out.println( person + "is added succesfully!");

        } else {
            JOptionPane.showMessageDialog(null, "Name CANNOT be empty!!");

        }
        updateGroupList();

    }
    private void updateGroupList(){
        HashSet<Person> currentGroup = controller.getGroup();
        groupListPanel.removeAll();
        groupList.removeAll();
        menuFrame.remove(groupListPanel);
        menuFrame.pack();

        if(currentGroup.isEmpty()){
            return;
        }
        groupList = new JPanel();
        groupList.setLayout(new BoxLayout(groupList,BoxLayout.Y_AXIS));
        int i=1;
        for(Person p : currentGroup){
            JLabel personLabel = new JLabel("Person "+ i++ + ": " + p.toString());
            groupList.add(personLabel);
        }

        groupListPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        groupListPanel.add(groupList);
        groupPanel.add(groupListPanel);
        menuFrame.pack();

    }

    @Override
    public void addTicketMenuEvent() {
        String description = comboBox.getSelectedItem().toString();
        String beneficiaryName = beneficiaryNameField.getText();
        String beneficaryBankNR = beneficiaryBankField.getText();
        Double total = Double.parseDouble(totalAmount.getText());
        String paymentType = buttonGroup.getSelection().getActionCommand();

        HashMap<Person, Double> payments = new HashMap<>();
        /*String nameSpender = personField2.getText();
        String bankSpender = bankNumberField2.getText();
        Double amountSpent = Double.parseDouble(amountSpended.getText());*/


        Person beneficiary = new Person(beneficiaryName, beneficaryBankNR);
        //Person spender = controller.compare(nameSpender, bankSpender);

        if(controller.isInGroup(beneficiary)){
            if(paymentType.equals("uneven")){
                int amountOfPeople = Integer.parseInt(JOptionPane.showInputDialog("How many people participated?"));
                for (int i = 0; i < amountOfPeople; i++){
                    String nameSpender = JOptionPane.showInputDialog("name of person " + (i + 1) + " : ");
                    String bankSpender = JOptionPane.showInputDialog("bankNR of "+nameSpender+ " :");
                    Double amountSpent = Double.parseDouble(JOptionPane.showInputDialog("How much did "+ nameSpender+ " spent?"));

                    Person spender = new Person(nameSpender, bankSpender);
                    if(controller.isInGroup(spender)){
                        debts.put(spender, amountSpent);
                    } else {
                        JOptionPane.showMessageDialog(null, "Spender not found in group!");
                    }
                }
            } else if (paymentType.equals("even")) {
                int amountOfPeople = controller.getGroup().size();
                double amountPerPerson = total / amountOfPeople;

                for(Person person : controller.getGroup()){
                    debts.put(person, amountPerPerson);
                }

            }
            //payments.put(spender, amountSpent);
            Ticket ticket = new Ticket(description, beneficiary, total, paymentType, debts);
            controller.addTicket(ticket);
            System.out.println("Ticket "+ ticket + "is added successfully!");

        } else{
            JOptionPane.showMessageDialog(null, "Benificiary or spender is not in the group!");
        }
        JOptionPane.showMessageDialog(null,"Ticket added Succesfully!");
    }

    @Override
    public void undoEvent(){
        if(!controller.undoCommand()){
            JOptionPane.showMessageDialog(null, "Could not undo command. The command is invalid!");
        }
        updateGroupList();
    }
    @Override
    public void calculateDebtEvent() {
        // Clear previous
        calculatePanel.removeAll();
        calculatePanel.setLayout(new BoxLayout(calculatePanel, BoxLayout.Y_AXIS));
        HashMap<Person, HashMap<Person, Double>> deptsPerPerson = controller.calculateAllTickets();
        calculatePanel.add(new JLabel("Dept calculations:"));

        for (Map.Entry<Person, HashMap<Person, Double>> entry : deptsPerPerson.entrySet()) {
            Person spender = entry.getKey();
            HashMap<Person, Double> debts = entry.getValue();

            for (Map.Entry<Person, Double> debtEntry : debts.entrySet()) {
                Person beneficiary = debtEntry.getKey();
                Double amount = debtEntry.getValue();
                if (amount > 0) { // if debts exist show them
                    calculatePanel.add(new JLabel(" " + spender.getName() + " owes " + beneficiary.getName() + ": €" + String.format("%.2f", amount)));
                }
            }
        }

        calculatePanel.revalidate();
        menuFrame.repaint();
    }

}
