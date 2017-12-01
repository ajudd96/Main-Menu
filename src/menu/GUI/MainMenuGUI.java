package menu.GUI;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.util.ArrayList;

import menu.software.*;

public class MainMenuGUI extends JFrame {
	
	// Fields 
	private JLabel title;
	private JLabel message;
	// Panels
	private JPanel getInfo;
	private JPanel personType;
	private JPanel buttons;
	private JPanel hours;
	// Menus
	private JMenuBar Menu;
	private JMenuItem account;
	private JMenuItem logout;
	private JMenuItem exit;
	// Information
	private JTextField name;
	private JTextField address;
	private JTextField email;
	private JTextField password;
	private JTextField phoneNumber;
	private JTextField vehicle;
	private JTextField monday;
	private JTextField tuesday;
	private JTextField wednesday;
	private JTextField thursday;
	private JTextField friday;
	private JTextField saturday;
	private JTextField sunday;
	// Buttons
	private JButton logIn;
	private JButton signUp;
	private JButton done;
	private JButton back;
	private JButton edit;
	private JButton create;
	private JButton addItem;
	private JButton toMenu;
	private JButton viewRestaurant;
	// Log In
	private JRadioButton customer;
	private JRadioButton driver;
	private JRadioButton restaurant;
	private int person; // 1 = customer, 2 = driver, 3 = restaurant
	// Food Menu
	private JPanel itemAdd;
	private JList entreeList;
	private JList sideList;
	private JList drinkList;
	private JTextField itemName;
	private JTextField itemType;
	private JTextField itemDescription;
	private JTextField itemPrep;
	private JTextField itemPrice;
	// Other
	private MainMenu mainMenu;
	private Customer customerUser;
	private Driver driverUser;
	private Restaurant restaurantUser;
	private Timer timer;
	private String missingField;
	private int Frame;
	private ArrayList<String> restaurantHours;
	private Item newItem;
	private Order order;
	private PrintStream print;
	private JTextArea area;
	private Payment payment;
	
	// Constructor

	public MainMenuGUI() {
		super("Main Menu");
		
		setSize(500,500);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//getContentPane().setBackground(Color.BLUE); Changes Color 
		
		title = new JLabel("<HTML><center>Main Menu</center></HTML>");
		
		add(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		
		// Declarations
		MainMenu menu = MainMenu.loadData();
		
		if(menu != null) {
			mainMenu = new MainMenu(menu);
		}
		else {
			mainMenu = new MainMenu();
		}
		
		customerUser = new Customer();
		driverUser = new Driver();
		restaurantUser = new Restaurant();
		
		restaurantHours = new ArrayList<String>();
		
		//Timer
		timer = new Timer(1000, new TimerListener());

		// Inputs
		setTextFields();
		setTextArea();
		// Buttons
		setButtons();

		// Others
		print = new PrintStream(new CustomStream(area));
		System.setOut(print);
		
		getInfo = new JPanel();
		personType = new JPanel();
		buttons = new JPanel();
		
		message = new JLabel();
		
		person = 0;
		
		timer.start();
	}
	
	private void setTextFields() { // Initialize TextFields	
		if(Frame == 0) {
			// For Everyone
			name = new JTextField(15);
			address = new JTextField(15);
			email = new JTextField(15);
			password = new JTextField(15);
			phoneNumber = new JTextField(15);
			
			name.setText("");
			address.setText("");
			email.setText("");
			password.setText("");
			phoneNumber.setText("");
			
			// For Drivers ONLY
			vehicle = new JTextField(15);
			
			vehicle.setText("");
			
			// For Restaurants ONLY
			monday = new JTextField(15);
			tuesday = new JTextField(15);
			wednesday = new JTextField(15);
			thursday = new JTextField(15);
			friday = new JTextField(15);
			saturday = new JTextField(15);
			sunday = new JTextField(15);
	
			monday.setText("");
			tuesday.setText("");
			wednesday.setText("");
			thursday.setText("");
			friday.setText("");
			saturday.setText("");
			sunday.setText("");
		}
		if(Frame == 2) {
			name.setEditable(false);
			address.setEditable(false);
			email.setEditable(false);
			password.setEditable(false);
			phoneNumber.setEditable(false);
			vehicle.setEditable(false);
			
			monday.setEditable(false);
			tuesday.setEditable(false);
			wednesday.setEditable(false);
			thursday.setEditable(false);
			friday.setEditable(false);
			saturday.setEditable(false);
			sunday.setEditable(false);
		}
		else if(Frame == 3){
			name.setEditable(true);
			address.setEditable(true);
			email.setEditable(true);
			password.setEditable(true);
			phoneNumber.setEditable(true);
			vehicle.setEditable(true);
			
			monday.setEditable(true);
			tuesday.setEditable(true);
			wednesday.setEditable(true);
			thursday.setEditable(true);
			friday.setEditable(true);
			saturday.setEditable(true);
			sunday.setEditable(true);
		}	
		else if(Frame == 7) {
			itemName = new JTextField(15);
			itemType = new JTextField(15);
			itemDescription = new JTextField(15);
			itemPrep = new JTextField(15);
			itemPrice = new JTextField(15);
			
			itemName.setText("");
			itemType.setText("");
			itemDescription.setText("");
			itemPrep.setText("");
			itemPrice.setText("");
		}
	
	}

	private void setTextArea() { // Initialize TextArea 

	}
	
	private void setButtons() { // Initialize Buttons
		
		// Log In
		logIn = new JButton("Log In");
		logIn.addActionListener(new MenuButtonListener());
		
		// Sign Up 
		signUp = new JButton("Sign Up");
		signUp.addActionListener(new MenuButtonListener());
		
		// Done
		done = new JButton("Done");
		done.addActionListener(new MenuButtonListener());
		
		// Back
		back = new JButton("Back");
		back.addActionListener(new MenuButtonListener());
		
		// Edit
		edit = new JButton("Edit");
		edit.addActionListener(new MenuButtonListener());
		
		// To Food Menu
		toMenu = new JButton("To Menu");
		toMenu.addActionListener(new MenuButtonListener());
		
		// Create Order (by Customer)
		create = new JButton("Create Order");
		create.addActionListener(new MenuButtonListener());
		
		// Add Item to Menu (by Restaurant)
		addItem = new JButton("AddItem");
		addItem.addActionListener(new MenuButtonListener());
		
		// View Restaurants (by Customer)
		viewRestaurant = new JButton("View Restaurants");
		viewRestaurant.addActionListener(new MenuButtonListener());
	}
	
	// Methods
	public void LogingIn() { // Log In Screen 
		setSize(500,230);
		Frame = 0;
		
		customer = new JRadioButton("Customer");
		driver = new JRadioButton("Driver");
		restaurant = new JRadioButton("Restaurant");
		
		ButtonGroup group = new ButtonGroup();
		group.add(customer);
		group.add(driver);
		group.add(restaurant);
		
		customer.addActionListener(new PersonListener());
		driver.addActionListener(new PersonListener());
		restaurant.addActionListener(new PersonListener());

		message.setText("Log In:");

		setGetInfo();
		
		getInfo.setBorder(BorderFactory.createEtchedBorder());
		
		personType.add(new JLabel("Please Select What You Are:"));
		personType.add(customer);
		personType.add(driver);
		personType.add(restaurant);
		
		personType.setBorder(BorderFactory.createTitledBorder("Person Type"));
		
		buttons.add(logIn);
		buttons.add(signUp);
		
		add(message, BorderLayout.NORTH);
		add(getInfo, BorderLayout.CENTER);
		add(personType, BorderLayout.CENTER);
		add(buttons, BorderLayout.SOUTH);
		
		setVisible(true);
		
	}

	private void SignUp() { // Sign Up for Customer 
		Frame = 1;
		
		message.setText("Please Enter The Following Information:");
		add(message, BorderLayout.NORTH);
		
		setGetInfo();
		
		buttons.add(done);
		buttons.add(back);

		add(buttons, BorderLayout.SOUTH);

		setVisible(true);
	}
	
	private void Account() { // Screen That Shows Account Information 
		setGetInfo();
		
		if(person == 3) {add(hours);}
		
		ClearPanel(buttons);
		
		if(Frame == 2) {
			buttons.add(edit);
			buttons.add(back);

		}
		else if(Frame == 3) {
			buttons.add(done);
		}

		add(buttons, BorderLayout.CENTER);
		setVisible(true);
	}

	private void Customer() { // Customer Screen
		Frame = 4;
		
		Menu();
		
		title.setText("Welcome " + customerUser.getName());
		add(title);
		
		buttons.add(viewRestaurant);
		add(buttons);
		
		setVisible(true);
	}
	
	private void Driver() { // Driver Screen
		Frame = 5;
		
		Menu();
		
		title.setText("Welcome " + driverUser.getName());
		add(title);
		
		setVisible(true);
	}
	
	private void Restaurant() { // Restaurant Screen
		Frame = 6;
		
		Menu();
		
		title.setText("Welcome " + restaurantUser.getName());
		add(title);

		buttons.add(toMenu);
		add(buttons, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	private void RestaurantList() {	//	Restaurant Listing 
		Frame = 10;
		
		
	}
	
	private void AddToMenu() { // Add item to menu 
		Frame = 7;
		
		setSize(500,250);
		itemAdd = new JPanel();
		
		setTextFields();
		
		itemAdd.setLayout(new GridLayout(5,0));
		
		itemAdd.add(new JLabel("Name:"));
		itemAdd.add(itemName, BorderLayout.CENTER);
		itemAdd.add(new JLabel("Type:"));
		itemAdd.add(itemType, BorderLayout.CENTER);
		itemAdd.add(new JLabel("Description:"));
		itemAdd.add(itemDescription, BorderLayout.CENTER);
		itemAdd.add(new JLabel("Prep Time:"));
		itemAdd.add(itemPrep, BorderLayout.CENTER);
		itemAdd.add(new JLabel("Price:"));
		itemAdd.add(itemPrice, BorderLayout.CENTER);
		
		itemAdd.setBorder(BorderFactory.createTitledBorder("Add Item Info Below:"));

		buttons.add(done);
		buttons.add(back);

		add(itemAdd, BorderLayout.CENTER);
		add(buttons, BorderLayout.SOUTH);
		
		setVisible(true);

	}
	
	private void RestaurantFoodMenu() { // Restaurant Menu 
		Frame = 8;
		title.setText("Menu for " + restaurantUser.getName());
		add(title, BorderLayout.NORTH);

		if(person == 1) {
			message.setText("Please Select The Items You Would Like To Order.");
			add(message, BorderLayout.NORTH);
		}
		/*
		String[] entrees = new String[restaurantUser.getMenu().getEntreesList().size()];
		restaurantUser.getMenu().getEntreesList().toArray(entrees);
		
		String[] sides = new String[restaurantUser.getMenu().getSidesList().size()];
		restaurantUser.getMenu().getDrinksList().toArray(sides);
		
		String[] drinks = new String[restaurantUser.getMenu().getDrinksList().size()];
		restaurantUser.getMenu().getDrinksList().toArray(drinks);
		
		entreeList = new JList(entrees);
		entreeList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		entreeList.addListSelectionListener(new ListListener());
		
		sideList = new JList(sides);
		sideList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		sideList.addListSelectionListener(new ListListener());
		
		drinkList = new JList(drinks);
		drinkList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		drinkList.addListSelectionListener(new ListListener());
		
		JPanel entreesPanel = new JPanel();
		JPanel sidesPanel = new JPanel();
		JPanel drinksPanel = new JPanel();
		
		JScrollPane entreeScroll =  new JScrollPane(entreeList);
		JScrollPane sideScroll =  new JScrollPane(sideList);
		JScrollPane drinkScroll =  new JScrollPane(drinkList);
		
		entreesPanel.add(entreeScroll);
		sidesPanel.add(sideScroll);
		drinksPanel.add(drinkScroll);
		
		entreesPanel.setBorder(BorderFactory.createTitledBorder("Entrees"));
		sidesPanel.setBorder(BorderFactory.createTitledBorder("Sides"));
		drinksPanel.setBorder(BorderFactory.createTitledBorder("Drinks"));
		
		add(entreesPanel,BorderLayout.NORTH);
		add(sidesPanel,BorderLayout.CENTER);
		add(drinksPanel,BorderLayout.SOUTH);
		*/
		if(person == 1) {
			buttons.add(create);
		}
		else if(person == 3) {
			buttons.add(addItem);
		}
		
		buttons.add(back);
		add(buttons,BorderLayout.SOUTH);

		setVisible(true);
	}
	
	private void Order() {	// Order Window
		Frame = 9;
		done.setText("Order");
	
		buttons.add(done);
	}
	
	// Simple Methods
	private void Menu() { // Menu bar
		Menu = new JMenuBar();
		
		// File Menu
		JMenu fileMenu = new JMenu("Menu");
		
		if((Frame == 4) || (Frame == 5) || (Frame == 6)) {
			if(person == 1) {
				fileMenu.setText("Customer");
			}
			else if(person == 2) {
				fileMenu.setText("Driver");
			}
			else if(person == 3) {
				fileMenu.setText("Resturant");
			}
			
			account = new JMenuItem("Account");
			account.addActionListener(new MenuListener());
			
			fileMenu.add(account);
		}

		logout = new JMenuItem("Logout");
		logout.addActionListener(new MenuListener());
		
		exit = new JMenuItem("Exit");
		exit.addActionListener(new MenuListener());

		fileMenu.add(logout);
		fileMenu.add(exit);
					
		Menu.add(fileMenu);
		setJMenuBar(Menu);
	}
	
	private void ClearFrame() { // Clears Window 
		getContentPane().removeAll();
		getContentPane().revalidate();
		getContentPane().repaint();
	}
	
	private void ClearPanel(JPanel panel) { // Clears Panel 
		panel.removeAll();
		panel.validate();
	}
	
	private void removeFrameItem(JComponent component) { // Removes Single Item From Frame 
		remove(component);
		revalidate();
		repaint();
	}

	private boolean CheckFrame() { // Checks that All Fields Are Filled 
		boolean good = false;
		
		if(Frame == 0) {
			if(email.getText().isEmpty()) { missingField = "Email";}
			else if(password.getText().isEmpty()) { missingField = "Password";}
			else { good = true;}
		}
		else if(Frame == 1 || Frame == 3) {
			if(name.getText().isEmpty()) { missingField = "Name";}
			else if(address.getText().isEmpty()) { missingField = "Address";}
			else if(email.getText().isEmpty()) { missingField = "Email";}
			else if(password.getText().isEmpty()) { missingField = "Password";}
			else if(phoneNumber.getText().isEmpty()) { missingField = "Phone Number";}
			
			else if((person == 2) && vehicle.getText().isEmpty()) { missingField = "Vehicle";}
			
			else if((person == 3) && monday.getText().isEmpty()) { missingField = "Monday";}
			else if((person == 3) && tuesday.getText().isEmpty()) { missingField = "Tuesday";}
			else if((person == 3) && wednesday.getText().isEmpty()) { missingField = "Wednesday";}
			else if((person == 3) && thursday.getText().isEmpty()) { missingField = "Thursday";}
			else if((person == 3) && friday.getText().isEmpty()) { missingField = "Friday";}
			else if((person == 3) && saturday.getText().isEmpty()) { missingField = "Saturday";}
			else if((person == 3) && sunday.getText().isEmpty()) { missingField = "Sunday";}
			
			else { good = true;}
		}
		else if(Frame == 7) {
			if(itemName.getText().isEmpty()) {missingField = "Item Name";}
			else if(itemType.getText().isEmpty()) {missingField = "Item Type";}
			else if(itemDescription.getText().isEmpty()) {missingField = "Description";}
			else if(itemPrep.getText().isEmpty()) {missingField = "Item Prep Time";}
			else if(itemPrice.getText().isEmpty()) {missingField = "Item Price";}	
			
			else { good = true;}
		}
		
		if(Frame == 3) {
			if(person == 1) {
				if(!customerUser.getName().equals(name.getText())) { customerUser.setName(name.getText());}
				else if(!customerUser.getAddress().equals(address.getText())) { customerUser.setAddress(address.getText());}
				else if(!customerUser.getEmail().equals(email.getText())) { customerUser.setEmail(email.getText());}
				else if(!customerUser.getPassword().equals(password.getText())) { customerUser.setPassword(password.getText());}
				else if(!customerUser.getNumber().equals(phoneNumber.getText())) { customerUser.setNumber(phoneNumber.getText());}
			}
			else if(person == 2) {
				if(!driverUser.getName().equals(name.getText())) { driverUser.setName(name.getText());}
				else if(!driverUser.getAddress().equals(address.getText())) { driverUser.setAddress(address.getText());}
				else if(!driverUser.getEmail().equals(email.getText())) { driverUser.setEmail(email.getText());}
				else if(!driverUser.getPassword().equals(password.getText())) { driverUser.setPassword(password.getText());}
				else if(!driverUser.getNumber().equals(phoneNumber.getText())) { driverUser.setNumber(phoneNumber.getText());}
				else if(!driverUser.getVehicle().equals(vehicle.getText())) { driverUser.setVehicle(vehicle.getText());}
			}
			else if(person == 3) {
				if(!restaurantUser.getName().equals(name.getText())) { restaurantUser.setName(name.getText());}
				else if(!restaurantUser.getAddress().equals(address.getText())) { restaurantUser.setAddress(address.getText());}
				else if(!restaurantUser.getEmail().equals(email.getText())) { restaurantUser.setEmail(email.getText());}
				else if(!restaurantUser.getPassword().equals(password.getText())) { restaurantUser.setPassword(password.getText());}
				else if(!restaurantUser.getNumber().equals(phoneNumber.getText())) { restaurantUser.setNumber(phoneNumber.getText());}
				// Hours
				else if(!restaurantUser.getHours().get(0).equals(monday.getText())) {restaurantUser.setHour(0, monday.getText());}
				else if(!restaurantUser.getHours().get(1).equals(tuesday.getText())) {restaurantUser.setHour(0, tuesday.getText());}
				else if(!restaurantUser.getHours().get(2).equals(wednesday.getText())) {restaurantUser.setHour(0, wednesday.getText());}
				else if(!restaurantUser.getHours().get(3).equals(thursday.getText())) {restaurantUser.setHour(0, thursday.getText());}
				else if(!restaurantUser.getHours().get(4).equals(friday.getText())) {restaurantUser.setHour(0, friday.getText());}
				else if(!restaurantUser.getHours().get(5).equals(saturday.getText())) {restaurantUser.setHour(0, saturday.getText());}
				else if(!restaurantUser.getHours().get(6).equals(sunday.getText())) {restaurantUser.setHour(0, sunday.getText());}
			}
		}
		
		return good;
	}

	private void setGetInfo() { // Sets GetInfo Panel 

		if(Frame == 0) {
			getInfo.add(new JLabel("Email:"));
			getInfo.add(email, BorderLayout.CENTER);
			getInfo.add(new JLabel("Password:"));
			getInfo.add(password, BorderLayout.CENTER);	
		}
		else {
			if(person == 1) {
				setSize(500,300);
			}
	
			getInfo.removeAll();
			getInfo.validate();
			
			getInfo.add(new JLabel(""));
			getInfo.add(new JLabel(""));
			
			getInfo.setLayout(new GridLayout(7,0));
			
			getInfo.add(new JLabel("Name:"));
			getInfo.add(name, BorderLayout.CENTER);
			getInfo.add(new JLabel("Address:"));
			getInfo.add(address, BorderLayout.CENTER);
			getInfo.add(new JLabel("Email:"));
			getInfo.add(email, BorderLayout.CENTER);
			getInfo.add(new JLabel("Password:"));
			getInfo.add(password, BorderLayout.CENTER);
			getInfo.add(new JLabel("Phone Number:"));
			getInfo.add(phoneNumber, BorderLayout.CENTER);
			
			if(person == 2) {
				setSize(500,300);
				
				getInfo.setLayout(new GridLayout(7,0));
				
				getInfo.add(new JLabel("Vehicle:"));
				getInfo.add(vehicle);
			}
			
			getInfo.setBorder(BorderFactory.createTitledBorder("Information"));
			add(getInfo, BorderLayout.CENTER);
			
			if(person == 3) {
				setSize(500,510);
				
				CreateHours();
				add(hours);
			}
		}
	}
	
	private void CreateHours() { // Create TextFields for Hours 
		hours = new JPanel();
		
		hours.setLayout(new GridLayout(8,0));
		
		getInfo.add(new JLabel(""));
		getInfo.add(new JLabel(""));
		
		hours.add(new JLabel("Monday:"));
		hours.add(monday);
		hours.add(new JLabel("Tuesday:"));
		hours.add(tuesday);
		hours.add(new JLabel("Wednesday:"));
		hours.add(wednesday);
		hours.add(new JLabel("Thursday:"));
		hours.add(thursday);
		hours.add(new JLabel("Friday:"));
		hours.add(friday);
		hours.add(new JLabel("Saturday:"));
		hours.add(saturday);
		hours.add(new JLabel("Sunday:"));
		hours.add(sunday);
		
		hours.setBorder(BorderFactory.createTitledBorder("Hours of Operations"));
	}

	private void setHours() { // Stores Hours 
		restaurantHours.add(monday.getText());
		restaurantHours.add(tuesday.getText());
		restaurantHours.add(wednesday.getText());
		restaurantHours.add(thursday.getText());
		restaurantHours.add(friday.getText());
		restaurantHours.add(saturday.getText());
		restaurantHours.add(sunday.getText());	
	}

	private void setExistingInfo() { // Sets Fields For Existing Info 
		
		if(person == 1) {
			name.setText(customerUser.getName());
			address.setText(customerUser.getAddress());
			email.setText(customerUser.getEmail());
			password.setText(customerUser.getPassword());
			phoneNumber.setText(customerUser.getNumber());
		}
		else if(person == 2) {
			name.setText(driverUser.getName());
			address.setText(driverUser.getAddress());
			email.setText(driverUser.getEmail());
			password.setText(driverUser.getPassword());
			phoneNumber.setText(driverUser.getNumber());
			
			vehicle.setText(driverUser.getVehicle());
		}
		else if(person == 3) {
			name.setText(restaurantUser.getName());
			address.setText(restaurantUser.getAddress());
			email.setText(restaurantUser.getEmail());
			password.setText(restaurantUser.getPassword());
			phoneNumber.setText(restaurantUser.getNumber());
			
			monday.setText(restaurantUser.getHours().get(0));
			tuesday.setText(restaurantUser.getHours().get(1));
			wednesday.setText(restaurantUser.getHours().get(2));
			thursday.setText(restaurantUser.getHours().get(3));
			friday.setText(restaurantUser.getHours().get(4));
			saturday.setText(restaurantUser.getHours().get(5));
			sunday.setText(restaurantUser.getHours().get(6));
		}
	}

	// Classes
	private class TimerListener implements ActionListener{ // Initial Screen Timer 

		public void actionPerformed(ActionEvent e) {
			timer.stop();
			removeFrameItem(title);
				
			Menu();
			
			LogingIn();
		}
	}
		
	private class PersonListener implements ActionListener{ // Person Type Radio Buttons 
		public void actionPerformed(ActionEvent e) {
			JRadioButton source = (JRadioButton)(e.getSource());
			
			if(source.equals(customer)) {
				person = 1;
			}
			else if(source.equals(driver)) {
				person = 2;
			}
			else if(source.equals(restaurant)) {
				person = 3;
			}
		}
		
	}

	private class MenuListener implements ActionListener { // Menus 
		
		public void actionPerformed(ActionEvent e) {
			JMenuItem source = (JMenuItem)(e.getSource());
			
			ClearFrame();
			
			if(source.equals(account)) {
				Frame = 2;
				Menu();
				setTextFields();
				Account();
			}
			else if(source.equals(logout)) {
				ClearPanel(buttons);
				email.setText("");
				password.setText("");
				LogingIn();
			}
			else if(source.equals(exit)) {
				MainMenu.saveData(mainMenu);
				System.exit(0);
			}
		}
	}
		
	private class ListListener implements ListSelectionListener {	// List Selection
		
		public void valueChanged(ListSelectionEvent e) {
			
		}
		
	}
	
	private class MenuButtonListener implements ActionListener { // Buttons
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)(e.getSource());
			
			if(Frame == 0) {	//	Log In Window
				if(person == 0) {
					JOptionPane.showMessageDialog(null,"Please select person type "
							+ "(i.e. Customer, Driver, restaurant).", "Log In", JOptionPane.PLAIN_MESSAGE);
				}
				else if(!CheckFrame() && !source.equals(signUp)) {
					JOptionPane.showMessageDialog(null,"Please enter information for " + missingField, 
							"Missing Info", JOptionPane.PLAIN_MESSAGE);
				}
				else if(!mainMenu.CheckExists(person, email.getText().trim(), password.getText().trim()) && source.equals(logIn)) {
					JOptionPane.showMessageDialog(null,"No account exists with the provided information"
							, "Log In", JOptionPane.PLAIN_MESSAGE);
				}
				else {
					ClearFrame();
					ClearPanel(buttons);
	
					if(source.equals(logIn)) {
						if(person == 1) {
							customerUser = new Customer(mainMenu.getCustomer(email.getText(), password.getText()));
							setExistingInfo();
							
							Customer();
						}
						else if(person == 2) {
							driverUser = new Driver(mainMenu.getDriver(email.getText(), password.getText()));
							setExistingInfo();
							
							Driver();
						}
						else if(person == 3) {
							restaurantUser = new Restaurant(mainMenu.getRestaurant(email.getText(), password.getText()));
							setExistingInfo();
							
							Restaurant();
						}
					}
					else if(source.equals(signUp)) {
						SignUp();
					}
				}
			}
			else if(Frame == 1) {	// Sign up Window
				if(!CheckFrame() && !source.equals(back)) {
					JOptionPane.showMessageDialog(null,"Please enter information for " + missingField, 
							"Missing Info", JOptionPane.PLAIN_MESSAGE);
				}
				else if(mainMenu.CheckExists(person, email.getText().trim()) && source.equals(done)) {
					JOptionPane.showMessageDialog(null,"An account aleady exists for this email."
							, "Log In", JOptionPane.PLAIN_MESSAGE);
				}
				else {
					ClearFrame();
					ClearPanel(buttons);
					
					if(person == 3) {
						removeFrameItem(hours);
					}
		
					if(source.equals(done)) {
						if(person == 1) {
							customerUser = new Customer(name.getText(), address.getText(), email.getText(),
									password.getText(), phoneNumber.getText());
							mainMenu.customers.add(customerUser);
							Customer();
						}
						else if(person == 2) {
							driverUser = new Driver(name.getText(), address.getText(), email.getText(),
									password.getText(), phoneNumber.getText(), vehicle.getText());
							mainMenu.drivers.add(driverUser);
							Driver();
						}
						else if(person == 3) {
							int ok;
							
							setHours();
							
							restaurantUser = new Restaurant(name.getText(), address.getText(), email.getText(),
									password.getText(), phoneNumber.getText(), restaurantHours);
							mainMenu.restaurants.add(restaurantUser);
							
							ok = JOptionPane.showConfirmDialog(null, "Would you like to add items to your menu?", 
									"Add To Menu", JOptionPane.YES_NO_OPTION);
							
							if(ok == JOptionPane.OK_OPTION) {
								
								AddToMenu();
							}
							else {
								Restaurant();
							}	
						}
						
						MainMenu.saveData(mainMenu);
					}
					else if(source.equals(back)) {
						person = 0;
						email.setText("");
						password.setText("");
						LogingIn();
					}
				}
			}
			else if(Frame == 2) {	//	Account Window
				if(!CheckFrame() && source.equals(done)) {
					JOptionPane.showMessageDialog(null,"Please enter information for " + missingField, 
							"Missing Info", JOptionPane.PLAIN_MESSAGE);
				}
				else {
					if(source.equals(edit)) {
						Frame = 3;
						
						setTextFields();
						Account();
					}
					else if(source.equals(done)) {
						Frame = 2;
						setTextFields();
						
						MainMenu.saveData(mainMenu);
						
						Account();
					}
					else if(source.equals(back)) {
						if(Frame == 2) {
							ClearFrame();
			
							if(person == 1) {
								Customer();
							}
							else if(person == 2) {
								Driver();
							}
							else if(person == 3) {
								Restaurant();
							}
						}
					}
				}
			}
			else if(Frame == 4) {	//	Customer Menu
				ClearFrame();
				ClearPanel(buttons);
				
				if(source.equals(viewRestaurant)) {
					RestaurantList();
				}
			}
			else if(Frame == 6) {	// Restaurant Menu
				ClearFrame();
				ClearPanel(buttons);
				
				if(source.equals(toMenu)) {
					RestaurantFoodMenu();
				}
			}
			else if(Frame == 7) {	//	Item Add Window
				if(!CheckFrame() && source.equals(done)) {
					if(missingField.equals("Description")) {
						int ok = JOptionPane.showConfirmDialog(null, "Are you sure you would not like to add a desciption?", 
								"Add Item", JOptionPane.YES_NO_OPTION);
						
						if(ok == JOptionPane.OK_OPTION) {
							ClearFrame();
							ClearPanel(buttons);
							
							newItem = new Item(itemName.getText(), itemType.getText(), itemDescription.getText(), 
									Integer.parseInt(itemPrep.getText()), Double.parseDouble(itemPrice.getText()));
							restaurantUser.addItem(newItem);
							
							RestaurantFoodMenu();
						}
						
					}
					else {
					JOptionPane.showMessageDialog(null,"Please enter information for " + missingField, 
							"Missing Info", JOptionPane.PLAIN_MESSAGE);
					}
				}
				else {
					ClearFrame();
					ClearPanel(buttons);
					
					if(source.equals(done)) {
						
						newItem = new Item(itemName.getText(), itemType.getText(), itemDescription.getText(), 
								Integer.parseInt(itemPrep.getText()), Double.parseDouble(itemPrice.getText()));
						restaurantUser.addItem(newItem);
						
						RestaurantFoodMenu();
					}
					else if(source.equals(back)) {
						RestaurantFoodMenu();
					}
				}
			}
			else if(Frame == 8) {	//	Restaurant Menu Window
				ClearFrame();
				ClearPanel(buttons);
				
				if(source.equals(create)) {
					Order();
				}
				else if(source.equals(addItem)) {
					AddToMenu();
				}
				else if(source.equals(back)) {
					if(person == 1) {
						Customer();
					}
					else if(person == 3) {
						Restaurant();
					}
				}
			}
			else if(Frame == 9) {
				ClearFrame();
				ClearPanel(buttons);
				done.setText("Done");
				
				if(source.equals(create)) {
					done.setText("Order");
					Order();
				}
				else if(source.equals(addItem)) {
					AddToMenu();
				}
				else if(source.equals(back)) {
					if(person == 1) {
						Customer();
					}
					else if(person == 3) {
						Restaurant();
					}
				}
			}
		}
	}

}
