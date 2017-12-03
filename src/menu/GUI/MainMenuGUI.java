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
	private JPanel buttons;
	private JPanel hours;
	private JPanel itemAdd;
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
	private JTextField itemName;
	private JTextField itemType;
	private JTextField itemDescription;
	private JTextField itemPrep;
	private JTextField itemPrice;
	private JTextField paymentType;
	private JTextField paymentNumber;
	private JTextField paymentCVV;
	private JTextField paymentExpireDate;
	private JTextField paymentName;
	private JTextField paymentEmail;
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
	private JButton viewPayments;
	private JButton viewOrders;
	// Radio Button
	private JRadioButton customer;
	private JRadioButton driver;
	private JRadioButton restaurant;
	private JRadioButton card;
	private JRadioButton paypal;
	// Lists
	private JList entreeList;
	private JList sideList;
	private JList drinkList;
	private JList restaurantList;
	private JList cardList;
	private JList paypalList;
	private JList customerOrders;
	private JList driverOrders;
	private JList restaurantOrders;
	// Integers
	private int person; // 1 = customer, 2 = driver, 3 = restaurant
	private int pay; // 1 = CreditCard, 2 = Paypal
	private int item; // 1 = Entree, 2 = Side, 3 = Drink
	private int Frame;
	private int paymentIndex;
	private int orderWindow;
	private int numOfDrivers;
	private int currentDriverNumber;
	private int orderSelected;
	private int itemSelected;
	private int paymentWindow;
	private int customerIndex;
	private int driverIndex;
	private int restaurantIndex;
	// Other
	private MainMenu mainMenu;
	private Customer customerUser;
	private Driver driverUser;
	private Restaurant restaurantUser;
	private Timer timer;
	private String missingField;
	private ArrayList<String> restaurantHours;
	private Item newItem;
	private Order order;
	private JTextArea textArea;
	private CreditCard creditCard;
	private Paypal payPal;
	private boolean entreeExist;
	private boolean sideExist;
	private boolean drinkExist;
	
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
		
		mainMenu = MainMenu.loadData();
		
		// If information loading cases error comment out if statement below and uncomment info in GUIDriver
		if(mainMenu == null) {
			initialMenuData();
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

		// Printing
		textArea = new JTextArea();
		textArea.setEditable(false);
		PrintStream printOut = new PrintStream(new CustomStream(textArea));
		System.setOut(printOut);
				
		// Others
		getInfo = new JPanel();
		buttons = new JPanel();
		itemAdd = new JPanel();
		message = new JLabel();
		
		person = 0;
		pay = 0;
		currentDriverNumber = 0;
		numOfDrivers = mainMenu.getRestaurants().size();
		
		customerIndex = 0;
		driverIndex = 0;
		restaurantIndex = 0;
		
		timer.start();
	}
	
	private void initialMenuData() {	//	Initialize Menu Data First Time
		// If Individuals are not already entered
		if(!(mainMenu.CheckExists(1, "doeFrank@email.com", "Customer1") &&
				mainMenu.CheckExists(2, "shaws@email.com", "Driver1") &&
				mainMenu.CheckExists(3, "javaCafe@email.com", "Restaurant1"))) {
			//-------- Create Customer -----------//
			Customer cust1 = new Customer();
			
			cust1.setName("Frank Doe");
			cust1.setAddress("1535 Springs Rd.");
			cust1.setNumber("254 524 8248");
			cust1.setEmail("doeFrank@email.com");
			cust1.setPassword("Customer1");
			
			// Credit Card
			CreditCard c1 = new CreditCard();
			c1.setCVV("123");
			c1.setExpireDate("April 1 2020");	
			c1.setNumber("1234 5678 9101 1232");
			c1.setName("Frank Doe");
			cust1.addCard(c1);
			
			// PayPal
			Paypal pp1 = new Paypal();
			pp1.setEmail("doeFrank@email.com");
			cust1.addPaypal(pp1);
			
			mainMenu.addCustomer(cust1);
			
			//-------- Create Driver -----------//
			Driver driv1 = new Driver();
			
			driv1.setName("Susan Shaw");
			driv1.setAddress("4568 Dale St.");
			driv1.setNumber("984 684 6581");
			driv1.setEmail("shaws@email.com");
			driv1.setPassword("Driver1");
			driv1.setVehicle("Chevrolet Equinox");
			
			mainMenu.addDriver(driv1);
			
			//-------- Create Restaurant -----------//
			Restaurant rest1 = new Restaurant();
			
			rest1.setName("Billy Bob's Java Cafe");
			rest1.setAddress("857 Windsdrow St.");
			rest1.setNumber("166 489 4869");
			rest1.setEmail("javaCafe@email.com");
			rest1.setPassword("Restaurant1");
			
			rest1.getHours().add("8 AM to 9 PM");
			rest1.getHours().add("8 AM to 9 PM");
			rest1.getHours().add("8 AM to 9 PM");
			rest1.getHours().add("8 AM to 9 PM");
			rest1.getHours().add("8 AM to 9 PM");
			rest1.getHours().add("8 AM to 9 PM");
			rest1.getHours().add("8 AM to 9 PM");
			
			// Create Menu
			Item item1 = new Item();
			Item item2 = new Item();
			Item item3 = new Item();
			
			item1.setName("chicken wrap");
			item2.setName("ceaser salad");
			item3.setName("french fries");
			
			item1.setType("Entree");
			item2.setType("Side");
			item3.setType("Side");
			
			item1.setDescription("It is a wrap with chicken");
			item2.setDescription("It is a salad with ceaser dressing and chicken");
			item3.setDescription("They are fried potatoes");
			
			item1.setPrepTime(15);
			item2.setPrepTime(10);
			item3.setPrepTime(8);
			
			item1.setPrice(7.95);
			item2.setPrice(3.95);
			item3.setPrice(2.50);
			
			rest1.addItem(item1);
			rest1.addItem(item2);
			rest1.addItem(item3);
	
			mainMenu.addRestaurant(rest1);
			
			// -------- Main Menu -----------//
			
			MainMenu.saveData(mainMenu);
		}
	}
	
	private void setTextFields() { // Initialize TextFields	
		if(Frame == 0) {
			// For Everyone
			name = new JTextField(20);
			address = new JTextField(20);
			email = new JTextField(20);
			password = new JTextField(20);
			phoneNumber = new JTextField(20);
			
			name.setText("");
			address.setText("");
			email.setText("");
			password.setText("");
			phoneNumber.setText("");
			
			itemName = new JTextField(20);
			itemType = new JTextField(20);
			itemDescription = new JTextField(20);
			itemPrep = new JTextField(20);
			itemPrice = new JTextField(20);
			
			itemName.setText("");
			itemType.setText("");
			itemDescription.setText("");
			itemPrep.setText("");
			itemPrice.setText("");

			// For Customer ONLY
			paymentType = new JTextField(20);
			paymentNumber = new JTextField(20);
			paymentCVV = new JTextField(15);
			paymentExpireDate = new JTextField(20);
			paymentName = new JTextField(20);
			paymentEmail = new JTextField(20);
			
			paymentType.setText("");
			paymentNumber.setText("");
			paymentCVV.setText("");
			paymentExpireDate.setText("");
			paymentName.setText("");
			paymentEmail.setText("");
			
			// For Drivers ONLY
			vehicle = new JTextField(20);
			
			vehicle.setText("");
			
			// For Restaurants ONLY
			monday = new JTextField(20);
			tuesday = new JTextField(20);
			wednesday = new JTextField(20);
			thursday = new JTextField(20);
			friday = new JTextField(20);
			saturday = new JTextField(20);
			sunday = new JTextField(20);
	
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
		else if(Frame == 13) {
			paymentType.setEditable(false);
			paymentNumber.setEditable(false);
			paymentCVV.setEditable(false);
			paymentExpireDate.setEditable(false);
			paymentName.setEditable(false);
			paymentEmail.setEditable(false);
		}
		else {
			// For Everyone
			name.setText("");
			address.setText("");
			email.setText("");
			password.setText("");
			phoneNumber.setText("");

			itemName.setText("");
			itemType.setText("");
			itemDescription.setText("");
			itemPrep.setText("");
			itemPrice.setText("");

			// For Customer ONLY
			paymentType.setText("");
			paymentNumber.setText("");
			paymentCVV.setText("");
			paymentExpireDate.setText("");
			paymentName.setText("");
			paymentEmail.setText("");
			
			// For Drivers ONLY
			vehicle.setText("");
			
			// For Restaurants ONLY
			monday.setText("");
			tuesday.setText("");
			wednesday.setText("");
			thursday.setText("");
			friday.setText("");
			saturday.setText("");
			sunday.setText("");
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
		
		// View Payments (by Customer)
		viewPayments = new JButton("View Payments");
		viewPayments.addActionListener(new MenuButtonListener());
		
		// View Payments (by All)
		viewOrders = new JButton("View Orders");
		viewOrders.addActionListener(new MenuButtonListener());
	}
	
	// Methods
	public void LogingIn() { // Log In Screen (Frame = 0)	
		setSize(500,250);
		Frame = 0;
		
		setTextFields();
		
		ClearPanel(buttons);
		ClearPanel(getInfo);
		
		customer = new JRadioButton("Customer");
		driver = new JRadioButton("Driver");
		restaurant = new JRadioButton("Restaurant");
		
		ButtonGroup group = new ButtonGroup();
		group.add(customer);
		group.add(driver);
		group.add(restaurant);
		
		customer.addActionListener(new RadioButtonListener());
		driver.addActionListener(new RadioButtonListener());
		restaurant.addActionListener(new RadioButtonListener());

		message.setText("Log In:");
		setGetInfo();
		
		getInfo.setBorder(BorderFactory.createEtchedBorder());
		
		JPanel personType = new JPanel();
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

	private void SignUp() { // Sign Up for Customer (Frame = 1)	
		Frame = 1;
		
		message.setText("Please Enter The Following Information:");
		add(message, BorderLayout.NORTH);
		
		setGetInfo();
		
		buttons.add(done);
		buttons.add(back);

		add(buttons, BorderLayout.SOUTH);

		setVisible(true);
	}
	
	private void Account() { // Screen That Shows Account Information (Frame = 2/3)	
		setGetInfo();
		setExistingInfo();
		
		if(person == 3) {add(hours);}
		
		ClearPanel(buttons);
		
		if(Frame == 2) {
			edit.setText("Edit");
			buttons.add(edit);
			buttons.add(back);

		}
		else if(Frame == 3) {
			buttons.add(done);
		}

		add(buttons, BorderLayout.CENTER);
		setVisible(true);
	}

	private void Customer() { // Customer Screen (Frame = 4)	
		Frame = 4;
		setSize(400, 300);
		
		getUpdatedCustomer();
		
		Menu();
		
		title.setText("Welcome " + customerUser.getName());
		add(title);
		
		buttons.add(viewRestaurant);
		buttons.add(viewPayments);
		buttons.add(viewOrders);
		add(buttons, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	private void Driver() { // Driver Screen (Frame = 5)	
		Frame = 5;
		setSize(400, 300);
		
		getUpdatedDriver();
		
		Menu();
		
		title.setText("Welcome " + driverUser.getName());
		add(title);
		
		buttons.add(viewOrders);
		add(buttons);
		
		setVisible(true);
	}
	
	private void Restaurant() { // Restaurant Screen (Frame = 6)	
		Frame = 6;
		setSize(400, 300);

		getUpdatedRestaurant();
		
		Menu();
		
		title.setText("Welcome " + restaurantUser.getName());
		add(title);

		buttons.add(toMenu);
		buttons.add(viewOrders);
		add(buttons, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	private void RestaurantList() {	//	Restaurant Listing (Frame = 11)	
		Frame = 11;
		setSize(400, 400);
		
		title.setText("Please Select A Restaurant");
		add(title, BorderLayout.NORTH);
		
		JPanel restaurants = new JPanel();
		restaurants.setSize(300, 300);
		
		if(mainMenu.getRestaurants().size() > 0) {
			restaurantList = new JList(mainMenu.RestaurantList().toArray());
			restaurantList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			restaurantList.addListSelectionListener(new ListListener());
			
			JScrollPane window = new JScrollPane(restaurantList);
			window.setPreferredSize(new Dimension(300,200));
			window.setBackground(getBackground());
			
			restaurants.add(window);
			
			add(restaurants,BorderLayout.CENTER);
			}
		else {
			add(new JLabel("There are currently no restaurants available."));
		}
		buttons.add(back);
		add(buttons,BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	private void AddToMenu() { // Add item to menu (Frame = 7)	
		Frame = 7;
		
		setSize(500,250);
		
		setTextFields();
		setGetInfo();

		itemAdd.setBorder(BorderFactory.createTitledBorder("Add Item Info Below:"));

		buttons.add(done);
		buttons.add(back);

		add(itemAdd, BorderLayout.CENTER);
		add(buttons, BorderLayout.SOUTH);
		
		setVisible(true);

	}
	
	private void RestaurantFoodMenu() { // Restaurant Menu (Frame = 8)	
		Frame = 8;
		
		getUpdatedRestaurant();
		
		int missingItems = 0;	// = 3 No Menu Items Exist
		entreeExist = false;
		sideExist = false;
		drinkExist = false;
		
		title.setText("Menu for " + restaurantUser.getName());
		add(title, BorderLayout.NORTH);
		add(new JLabel(""), BorderLayout.NORTH);

		if(person == 1) {
			message.setText("Please Select The Items You Would Like To Order.");
			add(message, BorderLayout.NORTH);
		}
		
		// Entrees
		if(restaurantUser.getMenu().getEntreesList().size() > 0) {
			entreeExist = true;
			
			JPanel entreesPanel = new JPanel();
			entreesPanel.setSize(400, 200);
			
			entreeList = new JList(restaurantUser.getMenu().getEntreesList().toArray());
			
			if(person == 1) {
				entreeList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			}
			else if(person == 3) {
				entreeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				entreeList.addListSelectionListener(new ListListener());
			}
			
			JScrollPane entreeScroll =  new JScrollPane(entreeList);
			entreeScroll.setBackground(getBackground());
			entreeScroll.setPreferredSize(new Dimension(300,150));
			
			entreesPanel.add(entreeScroll);
			entreesPanel.setBorder(BorderFactory.createTitledBorder("Entrees"));
			add(entreesPanel,BorderLayout.NORTH);
		}
		else { missingItems++; }

		// Sides
		if(restaurantUser.getMenu().getSidesList().size() > 0) {
			sideExist = true;
			
			JPanel sidesPanel = new JPanel();
			sidesPanel.setSize(400,200);
			
			sideList = new JList(restaurantUser.getMenu().getSidesList().toArray());
			
			if(person == 1) {
				sideList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			}
			else if(person == 3) {
				sideList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				sideList.addListSelectionListener(new ListListener());
			}
			
			JScrollPane sideScroll =  new JScrollPane(sideList);
			sideScroll.setBackground(getBackground());
			sideScroll.setPreferredSize(new Dimension(300,150));
			
			sidesPanel.add(sideScroll);
			sidesPanel.setBorder(BorderFactory.createTitledBorder("Sides"));
			add(sidesPanel,BorderLayout.CENTER);
		}
		else { missingItems++; }
		
		// Drinks
		if(restaurantUser.getMenu().getDrinksList().size() > 0) {
			drinkExist = true;
			
			JPanel drinksPanel = new JPanel();
			drinksPanel.setSize(400,200);
			
			drinkList = new JList(restaurantUser.getMenu().getDrinksList().toArray());
			
			if(person == 1) {
				drinkList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			}
			else if(person == 3) {
				drinkList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				drinkList.addListSelectionListener(new ListListener());
			}
			
			JScrollPane drinkScroll =  new JScrollPane(drinkList);
			drinkScroll.setBackground(getBackground());
			drinkScroll.setPreferredSize(new Dimension(300,150));
			
			drinksPanel.add(drinkScroll);
			drinksPanel.setBorder(BorderFactory.createTitledBorder("Drinks"));
			add(drinksPanel,BorderLayout.SOUTH);
		}
		else { missingItems++; }
		
		if(missingItems == 0) {
			setSize(450, 700);
		}
		if(missingItems == 1) {
			setSize(450, 500);
		}
		else if(missingItems == 2) {
			setSize(450, 300);
		}
		else if(missingItems == 3) {
			setSize(450, 200);
			add(new JLabel("There are currently no items available."));
		}
		
		if(person == 1) {
			create.setText("Order");
			buttons.add(create);
		}
		else if(person == 3) {
			buttons.add(addItem);
		}
		
		buttons.add(back);
		add(buttons,BorderLayout.SOUTH);

		setVisible(true);
	}
	
	private void viewItem()	{	// View Selected Item (Frame = 15)	
		Frame = 15;
		setExistingInfo();
		setGetInfo();
		
		setSize(500,275);
		
		edit.setText("Remove");
		
		itemAdd.setBorder(BorderFactory.createTitledBorder("Item Info"));
		
		ClearPanel(buttons);
		
		buttons.add(edit);
		buttons.add(back);
		
		add(itemAdd, BorderLayout.NORTH);
		add(buttons, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	private void Order() {	// Order Window (Frame = 9)	
		Frame = 9;
		setSize(300,400);
		textArea.setText("");
		
		title.setText("Order");
		add(title, BorderLayout.NORTH);
		
		JPanel window = new JPanel();
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(250,250));
		window.add(scroll);
		add(window, BorderLayout.CENTER);
		
		if(person == 1) {
			if(orderWindow == 1) {
				
				order.printIncompleteOrder();
				buttons.add(done);
			}
			else if(orderWindow == 2) {
				order.printCompleteOrder();
				
				done.setText("Done");
			}
		}
		else if(person == 2) {
			order.printDriverOrderList();
			done.setText("Deliver");
			buttons.add(done);
		}
		else if(person == 3) {
			order.printRestaurantOrderList();
			done.setText("Done");
			buttons.add(done);
		}
		
		buttons.add(back);
		add(buttons);
		
		setVisible(true);
	}
	
	private void OrderList() {	// List of Orders (Frame = 14) 
		Frame = 14;
		setSize(400, 400);
		
		getUpdatedCustomer();
		getUpdatedDriver();
		getUpdatedRestaurant();
		
		title.setText("List of Orders");
		add(title, BorderLayout.NORTH);
		
		JPanel list = new JPanel();
		list.setSize(300, 300);
		
		if(person == 1) {
			if(customerUser.getOrderList().size() > 0) {
				customerOrders = new JList(customerUser.getOrderList().toArray());
				customerOrders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				customerOrders.addListSelectionListener(new ListListener());
				
				JScrollPane window1 = new JScrollPane(customerOrders);
				window1.setPreferredSize(new Dimension(300,200));
				window1.setBackground(getBackground());
				
				list.add(window1);
				
				add(list,BorderLayout.CENTER);
				}
			else {
				add(new JLabel("There are currently no orders available."));
			}
		}
		else if(person == 2) {
			if(driverUser.getOrderList().size() > 0) {
				driverOrders = new JList(driverUser.getOrderList().toArray());
				driverOrders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				driverOrders.addListSelectionListener(new ListListener());
				
				JScrollPane window2 = new JScrollPane(driverOrders);
				window2.setPreferredSize(new Dimension(300,200));
				window2.setBackground(getBackground());
				
				list.add(window2);
				
				add(list,BorderLayout.CENTER);
				}
			else {
				add(new JLabel("There are currently no orders available."));
			}
		}
		else if(person == 3) {
			if(restaurantUser.getOrderList().size() > 0) {
				restaurantOrders = new JList(restaurantUser.getOrderList().toArray());
				restaurantOrders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				restaurantOrders.addListSelectionListener(new ListListener());
				
				JScrollPane window3 = new JScrollPane(restaurantOrders);
				window3.setPreferredSize(new Dimension(300,200));
				window3.setBackground(getBackground());
				
				list.add(window3);
				
				add(list,BorderLayout.CENTER);
				}
			else {
				add(new JLabel("There are currently no orders available."));
			}
		}

		buttons.add(back);
		add(buttons,BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	private void Payments()	{	// List of Payments (Frame = 10)	
		Frame = 10;
		setSize(500, 400);
		
		getUpdatedCustomer();
		
		if(paymentWindow == 2) {
			title.setText("Please select a payment method for your order.");
		}
		else {
			title.setText("Payment Methods");
		}
		add(title, BorderLayout.NORTH);
		add(new JLabel(""));
		
		// Credit Cards
		if(customerUser.CreditCardList().size() > 0) {
		
			JPanel cards = new JPanel();
			
			cardList = new JList(customerUser.CreditCardList().toArray());
			cardList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			cardList.addListSelectionListener(new ListListener());
			
			JScrollPane window1 = new JScrollPane(cardList);
			window1.setPreferredSize(new Dimension(300,100));
			window1.setBackground(getBackground());
			
			add(new JLabel("The following are your saved Credit Card(s):"));
			add(new JLabel(""));
			cards.add(window1);
			
			add(cards,BorderLayout.CENTER);
		}
		
		// Paypals
		if(customerUser.PaypalList().size() > 0) {
			JPanel paypals = new JPanel();
			
			paypalList = new JList(customerUser.PaypalList().toArray());
			paypalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			paypalList.addListSelectionListener(new ListListener());
			
			JScrollPane window2 = new JScrollPane(paypalList);
			window2.setPreferredSize(new Dimension(300,100));
			window2.setBackground(getBackground());
			
			add(new JLabel("The following are your saved PayPal accounts:"));
			add(new JLabel(""));
			paypals.add(window2);
			
			add(paypals,BorderLayout.CENTER);
		}
		create.setText("Add Payment");
		
		buttons.add(create);
		buttons.add(back);
		add(buttons,BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	private JPanel getPaymentType() {	// Get Payment Type
		card = new JRadioButton("Credit Card");
		paypal = new JRadioButton("PayPal");
		
		ButtonGroup group = new ButtonGroup();
		group.add(card);
		group.add(paypal);
		
		JPanel payType = new JPanel();
		payType.add(new JLabel("Please Select Payment Type:"));
		payType.add(card);
		payType.add(paypal);
		
		payType.setBorder(BorderFactory.createTitledBorder("Payment Type"));
		
		return payType;
	}
	
	private void createPayments() {	// Add new payment Method (Frame = 12)
		Frame = 12;
		
		if(pay == 1) {
			setSize(500,275);
		}
		else {
			setSize(500,200);
		}
		setTextFields();
		
		ClearPanel(getInfo);
		ClearPanel(buttons);

		message.setText("Create Payment:");
		setGetInfo();
		
		getInfo.setBorder(BorderFactory.createEtchedBorder());
		
		buttons.add(done);
		buttons.add(back);
		
		add(message, BorderLayout.NORTH);
		add(getInfo, BorderLayout.CENTER);
		add(buttons, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	private void viewPayment() {	// View Selected Payment
		Frame = 13;
		setTextFields();
		setExistingInfo();
		setGetInfo();
		
		setSize(500,275);
		
		edit.setText("Remove");
		
		ClearPanel(buttons);
		
		buttons.add(edit);
		buttons.add(back);
		
		add(getInfo, BorderLayout.NORTH);
		add(buttons, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	// Managing Methods
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
		else if(Frame == 12) {
			if(pay == 1) {
				if(paymentNumber.getText().isEmpty()) {missingField = "Credit Card Number";}
				else if(paymentCVV.getText().isEmpty()) {missingField = "CVV";}
				else if(paymentExpireDate.getText().isEmpty()) {missingField = "Expiration Date";}
				else if(paymentName.getText().isEmpty()) {missingField = "Card Holder Name";}
				
				else { good = true;}
			}
			else if(pay == 2) {
				if(paymentEmail.getText().isEmpty()) {missingField = "Email";}
				
				else { good = true;}
			}
			
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
		ClearPanel(getInfo);
		ClearPanel(itemAdd);
		
		if(Frame == 0) {
			getInfo.setLayout(new GridLayout(2,2));
			getInfo.add(new JLabel("Email:"));
			getInfo.add(email, BorderLayout.CENTER);
			getInfo.add(new JLabel("Password:"));
			getInfo.add(password, BorderLayout.CENTER);	
		}
		else if(Frame == 7 || Frame == 15) {
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
		}
		else if(Frame == 12 || Frame == 13) {
			if(pay == 1) {
				getInfo.setLayout(new GridLayout(6,0));
				
				getInfo.add(new JLabel("Number:"));
				getInfo.add(paymentNumber, BorderLayout.CENTER);
				getInfo.add(new JLabel("CVV:"));
				getInfo.add(paymentCVV, BorderLayout.CENTER);
				getInfo.add(new JLabel("Expiration Date:"));
				getInfo.add(paymentExpireDate, BorderLayout.CENTER);
				getInfo.add(new JLabel("Card Holder Name:"));
				getInfo.add(paymentName, BorderLayout.CENTER);
			}
			else {
				getInfo.setLayout(new GridLayout(1,0));
				getInfo.add(new JLabel("Email:"));
				getInfo.add(paymentEmail, BorderLayout.CENTER);	
			}
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
			getUpdatedCustomer();
			
			name.setText(customerUser.getName());
			address.setText(customerUser.getAddress());
			email.setText(customerUser.getEmail());
			password.setText(customerUser.getPassword());
			phoneNumber.setText(customerUser.getNumber());
		}
		else if(person == 2) {
			getUpdatedDriver();
			
			name.setText(driverUser.getName());
			address.setText(driverUser.getAddress());
			email.setText(driverUser.getEmail());
			password.setText(driverUser.getPassword());
			phoneNumber.setText(driverUser.getNumber());
			
			vehicle.setText(driverUser.getVehicle());
		}
		else if(person == 3) {
			getUpdatedRestaurant();
			
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
		
		if(pay == 1) {
			paymentType.setText(creditCard.getType());
			paymentNumber.setText(creditCard.getNumber());
			paymentCVV.setText(creditCard.getCVV());
			paymentExpireDate.setText(creditCard.getExpireDate());
			paymentName.setText(creditCard.getName());
		}
		else if(pay == 2) {
			paymentType.setText(payPal.getType());
			paymentEmail.setText(payPal.getEmail());
		}
		
		if(Frame == 15) {
			itemName.setText(newItem.getName());
			itemType.setText(newItem.getType());
			itemDescription.setText(newItem.getDescription());
			itemPrep.setText(Integer.toString(newItem.getPrepTime()));
			itemPrice.setText(Double.toString(newItem.getPrice()));
		}
	}

	private void clearTextFields() {
	
		name.setText("");
		address.setText("");
		email.setText("");
		password.setText("");
		phoneNumber.setText("");

		vehicle.setText("");

		monday.setText("");
		tuesday.setText("");
		wednesday.setText("");
		thursday.setText("");
		friday.setText("");
		saturday.setText("");
		sunday.setText("");
		
		paymentType.setText("");
		paymentNumber.setText("");
		paymentCVV.setText("");
		paymentExpireDate.setText("");
		paymentName.setText("");
		paymentEmail.setText("");
	
	}

	private void getUpdatedCustomer() {
		customerUser = mainMenu.getCustomers().get(restaurantIndex);
	}
	
	private void getUpdatedDriver() {
		driverUser = mainMenu.getDrivers().get(restaurantIndex);
	}
	
	private void getUpdatedRestaurant() {
		restaurantUser = mainMenu.getRestaurants().get(restaurantIndex);
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
		
	private class RadioButtonListener implements ActionListener{ // Person Type Radio Buttons 
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
				clearTextFields();
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
			ClearFrame();
			ClearPanel(buttons);
			
			if((Frame == 8))  {
				int entreeIndex, sideIndex, drinkIndex;
				
				if(entreeExist) { entreeIndex = entreeList.getSelectedIndex();}
				else {entreeIndex = -1;}
				
				if(sideExist) { sideIndex = sideList.getSelectedIndex();}
				else {sideIndex = -1;}
				
				if(drinkExist) { drinkIndex = drinkList.getSelectedIndex();}
				else {drinkIndex = -1;}
				
				if((entreeIndex != -1) && (sideIndex == -1) && (drinkIndex == -1)) { // Entree Selected
					itemSelected = entreeIndex;
					item = 1;
					newItem = new Item(restaurantUser.getMenu().getEntrees().get(entreeIndex));
					viewItem();
				}
				else if((entreeIndex == -1) && (sideIndex != -1) && (drinkIndex == -1)) { // Side Selected
					itemSelected = sideIndex;
					item = 2;
					newItem = new Item(restaurantUser.getMenu().getSides().get(sideIndex));
					viewItem();
				}
				else if((entreeIndex == -1) && (sideIndex == -1) && (drinkIndex != -1)) { // Drink Selected
					itemSelected = drinkIndex;
					item = 3;
					newItem = new Item(restaurantUser.getMenu().getDrinks().get(drinkIndex));
					viewItem();
				}
				else if((entreeIndex != -1) && (sideIndex == -1) && (drinkIndex == -1)) { // One of each Selected
					JOptionPane.showMessageDialog(null,"Please select only one item.", "Menu", JOptionPane.PLAIN_MESSAGE);
				}
				
			}
			else if(Frame == 10) {
				int cardIndex, paypalIndex;
				
				cardIndex = cardList.getSelectedIndex();
				paypalIndex = paypalList.getSelectedIndex();
				
				if((cardIndex != -1) && (paypalIndex == -1)) { // Credit Card Selected
					pay = 1;
					paymentIndex = cardIndex;
					creditCard = new CreditCard(customerUser.getCreditCards().get(cardIndex));
				}
				else if((cardIndex == -1) && (paypalIndex != -1)) { // Paypal Selected
					pay = 2;
					paymentIndex = paypalIndex;
					payPal = new Paypal(customerUser.getPaypals().get(paypalIndex));
				}
				else if((cardIndex != -1) && (paypalIndex != -1)) { // Both a Credit Card and Paypal Selected
					JOptionPane.showMessageDialog(null,"Please select only one payment method.", "Payment", JOptionPane.PLAIN_MESSAGE);
				}
				
				if(paymentWindow == 1) {
					viewPayment();
				}
				else if(paymentWindow == 2){
					if((cardIndex != -1)) { order.setPayment(creditCard);}
					else if((paypalIndex != -1)) { order.setPayment(payPal);}
					
					int ok;
					
					ok = JOptionPane.showConfirmDialog(null, "Would you like to add a comment to your order?", "Create Order", JOptionPane.YES_NO_OPTION);
					
					if(ok == JOptionPane.OK_OPTION) {
						JPanel panel = new JPanel();
						JTextArea area = new JTextArea();
						area.setEditable(true);
						area.setSize(30, 30);
						
						panel.add(new JLabel("Enter your comment below."));
						panel.add(area);
						
						JOptionPane.showMessageDialog(null, panel, "Create Order", JOptionPane.PLAIN_MESSAGE);
						
						order.setComment(area.getText());
					}
					
					done.setText("Complete Order");
					MainMenu.saveData(mainMenu);
					Order();
				}
				
			}
			else if(Frame == 11) {
				int index;
				index = restaurantList.getSelectedIndex();
				
				if(index != -1) {
					restaurantUser = new Restaurant(mainMenu.getRestaurants().get(index));
					RestaurantFoodMenu();
				}
			}
			else if(Frame == 14) {
				int index;
				
				if(person == 1) {
					index = customerOrders.getSelectedIndex();
					order = mainMenu.getCustomers().get(customerIndex).getOrders().get(index);
				}
				else if(person == 2) {
					index = driverOrders.getSelectedIndex();
					order = mainMenu.getDrivers().get(driverIndex).getOrders().get(index);
				}
				else if(person == 3) {
					index = restaurantOrders.getSelectedIndex();
					order = mainMenu.getRestaurants().get(restaurantIndex).getOrders().get(index);
				}
				
				Order();
			}
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
							//customerUser = new Customer(mainMenu.getCustomer(email.getText(), password.getText()));
							customerIndex = mainMenu.getIndex(1, email.getText(), password.getText());
							
							setExistingInfo();
							
							Customer();
						}
						else if(person == 2) {
							//driverUser = new Driver(mainMenu.getDriver(email.getText(), password.getText()));
							driverIndex = mainMenu.getIndex(2, email.getText(), password.getText());
							
							setExistingInfo();
							
							Driver();
						}
						else if(person == 3) {
							//restaurantUser = new Restaurant(mainMenu.getRestaurant(email.getText(), password.getText()));
							restaurantIndex = mainMenu.getIndex(3, email.getText(), password.getText());
							
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
							
							customerIndex = mainMenu.getIndex(1, email.getText(), password.getText());
							
							MainMenu.saveData(mainMenu);
							Customer();
						}
						else if(person == 2) {
							driverUser = new Driver(name.getText(), address.getText(), email.getText(),
									password.getText(), phoneNumber.getText(), vehicle.getText());
							mainMenu.drivers.add(driverUser);
							
							driverIndex = mainMenu.getIndex(2, email.getText(), password.getText());
							
							MainMenu.saveData(mainMenu);
							
							numOfDrivers++;
							
							Driver();
						}
						else if(person == 3) {
							int ok;
							
							setHours();
							
							restaurantUser = new Restaurant(name.getText(), address.getText(), email.getText(),
									password.getText(), phoneNumber.getText(), restaurantHours);
							mainMenu.restaurants.add(restaurantUser);
							
							restaurantIndex = mainMenu.getIndex(3, email.getText(), password.getText());
							
							ok = JOptionPane.showConfirmDialog(null, "Would you like to add items to your menu?", 
									"Add To Menu", JOptionPane.YES_NO_OPTION);
							
							MainMenu.saveData(mainMenu);
							
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
			else if(Frame == 2 || Frame == 3) {	//	Account Window
				
				if(!CheckFrame() && source.equals(done)) {
					JOptionPane.showMessageDialog(null,"Please enter information for " + missingField, 
							"Missing Info", JOptionPane.PLAIN_MESSAGE);
				}
				else {
					ClearFrame();
					ClearPanel(buttons);
					
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
				else if(source.equals(viewPayments)) {
					paymentWindow = 1;
					Payments();
				}
				else if(source.equals(viewOrders)) {
					orderWindow = 2;
					OrderList();
				}
			}
			else if(Frame == 5) {	// Driver Menu
				ClearFrame();
				ClearPanel(buttons);
				
				if(source.equals(viewOrders)) {
					OrderList();
				}
			}
			else if(Frame == 6) {	// Restaurant Menu
				ClearFrame();
				ClearPanel(buttons);
				
				if(source.equals(toMenu)) {
					RestaurantFoodMenu();
				}
				else if(source.equals(viewOrders)) {
					OrderList();
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
							//restaurantUser.addItem(newItem);
							
							mainMenu.getRestaurants().get(restaurantIndex).addItem(newItem);
							
							MainMenu.saveData(mainMenu);
							
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
						//restaurantUser.addItem(newItem);
						
						if(mainMenu.CheckItemExists(newItem, mainMenu.getRestaurants().get(restaurantIndex))) {
							JOptionPane.showMessageDialog(null,"This item already exists.", 
									"Add Item", JOptionPane.PLAIN_MESSAGE);
						}
						else {
							mainMenu.getRestaurants().get(restaurantIndex).addItem(newItem);
							MainMenu.saveData(mainMenu);
						}
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
					if(person == 1) {
						orderWindow = 1;
						order = new Order();
						
						order.setCustomer(mainMenu.getCustomers().get(customerIndex));
						order.setRestaurant(mainMenu.getRestaurants().get(restaurantIndex));
						
						if(entreeExist) { 
							int[] entreesSelected = entreeList.getSelectedIndices();
							
							for(int i : entreesSelected) {
								order.setItems(restaurantUser.getMenu().getEntrees().get(i));
							}
						}
						
						if(sideExist) { 
							int[] sidesSelected = sideList.getSelectedIndices();
							
							for(int j : sidesSelected) {
								order.setItems(restaurantUser.getMenu().getSides().get(j));
							}
						}
						
						if(drinkExist) { 
							int[] drinksSelected = drinkList.getSelectedIndices();
	
							for(int k : drinksSelected) {
								order.setItems(restaurantUser.getMenu().getDrinks().get(k));
							}
						}
						
						order.setCost(order.calculateCost());
						order.setTotalTime(order.calculateTotalTime());
						order.setPrepTime(order.calculatePrepTime());
						order.setStatus("Not Ordered");
						
						paymentWindow = 2;
						
						MainMenu.saveData(mainMenu);
					}
					Payments();
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
			else if(Frame == 9) {	// Order Window
				ClearFrame();
				ClearPanel(buttons);
				
				if(source.equals(done)) {
					if(person == 1) {
						orderWindow = 2;
						
						//customerUser.addOrder(order);
						//restaurantUser.addOrder(order);
						
						int ok = mainMenu.getOrderExists(order, mainMenu.getCustomers().get(customerIndex));
						
						if(ok != -1) {
							order = mainMenu.getCustomers().get(customerIndex).getOrders().get(ok);
							
							mainMenu.getCustomers().get(customerIndex).getOrders().get(ok).setCustomer( mainMenu.getCustomers().get(customerIndex));
							mainMenu.getCustomers().get(customerIndex).getOrders().get(ok).setCustomer( mainMenu.getCustomers().get(customerIndex));
							mainMenu.getCustomers().get(customerIndex).getOrders().get(ok).setStatus("Ordered");
						}
						else {
							mainMenu.getCustomers().get(customerIndex).addOrder(order);
						}
						order.setStatus("Ordered");
						
						mainMenu.getDrivers().get(driverIndex).addOrder(order);
						order.setDriver(mainMenu.getDrivers().get(driverIndex));
						mainMenu.getRestaurants().get(restaurantIndex).addOrder(order);
						
						currentDriverNumber++;
						if(currentDriverNumber == numOfDrivers) {
							currentDriverNumber = 0;
						}
							
						
						OrderList();
					}
					else if(person == 2) {
						order.setStatus("Delivered");
						//driverUser.removeOrder(driverUser.getOrders().get(orderSelected));
						mainMenu.getDrivers().get(driverIndex).removeOrder(mainMenu.getDrivers().get(driverIndex).getOrders().get(orderSelected));
						OrderList();
					}
					else if(person == 3) {
						order.setStatus("On Route");
						//restaurantUser.removeOrder(restaurantUser.getOrders().get(orderSelected));
						mainMenu.getRestaurants().get(restaurantIndex).removeOrder(mainMenu.getRestaurants().get(restaurantIndex).getOrders().get(orderSelected));
						OrderList();
					}
					MainMenu.saveData(mainMenu);
					
				}
				else if(source.equals(addItem)) {
					AddToMenu();
				}
				else if(source.equals(back)) {
					if(person == 1 && orderWindow == 1) {
						int ok = mainMenu.getOrderExists(order, mainMenu.getCustomers().get(customerIndex));
						
						if(ok == -1) { mainMenu.getCustomers().get(customerIndex).addOrder(order);}
					}
					OrderList();
				}
			}
			else if(Frame == 10) {	// Payment Window
				ClearFrame();
				ClearPanel(buttons);
				
				if(source.equals(create)) {
					int ok;
					
					ok = JOptionPane.showConfirmDialog(null, getPaymentType() , "Select Payment Type", JOptionPane.OK_CANCEL_OPTION);
					
					if(ok == JOptionPane.OK_OPTION) {
						if(card.isSelected()) {
							pay = 1;
						}
						else if(paypal.isSelected()) {
							pay = 2;
						}
						createPayments();
					}
				}
				else if(source.equals(back)) {
					Customer();
				}
			}
			else if(Frame == 11) {	// Restaurant List Window
				ClearFrame();
				ClearPanel(buttons);
				
				if(source.equals(back)) {
					Customer();
				}
			}
			else if(Frame == 12) {	// Create Payment Window
				ClearFrame();
				ClearPanel(buttons);
				
				if(!CheckFrame() && source.equals(done)) {
					JOptionPane.showMessageDialog(null,"Please enter information for " + missingField, 
							"Missing Info", JOptionPane.PLAIN_MESSAGE);
				}
				else {
					if(source.equals(done)) {
						if(pay == 1) {
							creditCard = new CreditCard(paymentNumber.getText(), paymentCVV.getText(), paymentExpireDate.getText(), paymentName.getText());
							//customerUser.addCard(creditCard);
							mainMenu.getCustomers().get(customerIndex).addCard(creditCard);
						}
						else if(pay == 2) {
							payPal = new Paypal(paymentEmail.getText());
							//customerUser.addPaypal(payPal);
							mainMenu.getCustomers().get(customerIndex).addPaypal(payPal);
						}
						MainMenu.saveData(mainMenu);
						
						Payments();
					}
					else if(source.equals(back)) {
						Payments();
					}
				}
			}
			else if(Frame == 13) {	// Payment View Window
				ClearFrame();
				ClearPanel(buttons);
				
				if(source.equals(edit)) { // to Remove payment
					edit.setText("Edit");
					
					int ok;
					ok = JOptionPane.showConfirmDialog(null, "Are you sure you would like to remove this payment method?", "Remove Payment", JOptionPane.OK_CANCEL_OPTION);
					
					if(ok == JOptionPane.OK_OPTION) {
						if(pay == 1) {
							//customerUser.removeCard(customerUser.getCreditCards().get(paymentIndex));
							mainMenu.getCustomers().get(customerIndex).removeCard(customerUser.getCreditCards().get(paymentIndex));
						}
						else if(pay == 2) {
							//customerUser.removePaypal(customerUser.getPaypals().get(paymentIndex));
							mainMenu.getCustomers().get(customerIndex).removePaypal(customerUser.getPaypals().get(paymentIndex));
						}
						MainMenu.saveData(mainMenu);
						Payments();
					}
				}
				else if(source.equals(back)) {
					Payments();
				}
			}
			else if(Frame == 14) { // Order View List
				ClearFrame();
				ClearPanel(buttons);
				done.setText("Done");
				
				if(source.equals(back)) {
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
			else if(Frame == 15) {	// Item View Window
				ClearFrame();
				ClearPanel(buttons);
				
				if(source.equals(edit)) { // to Remove item
					edit.setText("Edit");
					
					int ok;
					ok = JOptionPane.showConfirmDialog(null, "Are you sure you would like to remove this item?", "Remove Item", JOptionPane.OK_CANCEL_OPTION);
					
					if(ok == JOptionPane.OK_OPTION) {
						if(item == 1) {
							restaurantUser.getMenu().removeEntree(restaurantUser.getMenu().getEntrees().get(itemSelected));
						}
						else if(item == 2) {
							restaurantUser.getMenu().removeSide(restaurantUser.getMenu().getSides().get(itemSelected));
						}
						else if(item == 3) {
							restaurantUser.getMenu().removeDrink(restaurantUser.getMenu().getDrinks().get(itemSelected));
						}
						MainMenu.saveData(mainMenu);
						RestaurantFoodMenu();
					}
				}
				else if(source.equals(back)) {
					RestaurantFoodMenu();
				}
			}
		}
	}

}
