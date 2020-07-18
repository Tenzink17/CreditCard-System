package project2;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VendorScreen extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel 	   shopsPanel, backPanel,
					   shoppingCartCancelPanel,
					   checkOutCancelOrderPanel, showItemListPanel, returnPanel,
					   checkOutPanel;
	private JButton	   shop1Button, shop2Button, shop3Button,
					   shop1Item1Button, shop1Item2Button, shop1Item3Button, shop1Item4Button, shop1Item5Button,
					   shop2Item1Button, shop2Item2Button, shop2Item3Button, shop2Item4Button, shop2Item5Button,
					   shop3Item1Button, shop3Item2Button, shop3Item3Button, shop3Item4Button, shop3Item5Button,
					   shoppingCartButton, cancelButton, backButton,
					   cancelOrderButton, checkOutButton, showItemListButton, returnButton,
					   checkOutFinishButton, checkOutBackButton;
	private JTextField messageField, totalAmountField, cardNumberField, message2Field = new JTextField("  Welcome choose a vendor to shop ");
	private JLabel 	   cardNumberLabel, passwordLabel;

	private JPasswordField passwordField;

	private Font messageFont = new Font(null, Font.PLAIN, 20);
	actionHandler handler    = new actionHandler();

	private String selectedShop;
//	private List<String>  shoppingCartList    = new ArrayList<String>();
//	private int   shoppingListCounter = 0;
//	private double totalAmountNeeded   = 0;

	DecimalFormat df= new DecimalFormat("#.00");

	private SomeVendorClass FreshWear;
	private SomeVendorClass TechShop;
	private SomeVendorClass WholeMart;

	private CreditCardCompany visa;

	public VendorScreen(CreditCardCompany visa) {
		super("Vendor");
		this.visa = visa;
		FreshWear = new SomeVendorClass("FreshWear");
		TechShop = new SomeVendorClass("TechShop");
		WholeMart = new SomeVendorClass("WholeMart");
		vendorScreen();
	}

	public void vendorScreen() {

		message2Field = new JTextField("  Welcome choose a vendor to shop ");
		if(FreshWear.getTotal()> 0)
		{
			message2Field.setText("You have items in FreshWear");
		}
		else if(TechShop.getTotal()>0)
		{
			message2Field.setText("You have items in TechShop");
		}
		else if(WholeMart.getTotal()>0)
		{
			message2Field.setText("You have items in WholeMart");
		}
		message2Field.setBounds(150, 40, 350, 50);
		message2Field.setFont(messageFont);
		message2Field.setEditable(false);
		add(message2Field);

		backPanel = new JPanel();
		backPanel.setBounds(100, 450, 100, 50);
		add(backPanel);

		backButton = new JButton("Back");
		backButton.setFont(messageFont);
		backButton.addActionListener(handler);
		backPanel.add(backButton);

		shopsPanel = new JPanel();
		shopsPanel.setBounds(150, 100, 350, 300);
		shopsPanel.setLayout(new GridLayout(3,1));
		add(shopsPanel);

		shop1Button = new JButton(FreshWear.getStoreName());
		shop1Button.setFont(messageFont);
		shop1Button.addActionListener(handler);
		shopsPanel.add(shop1Button);

		shop2Button = new JButton(TechShop.getStoreName());
		shop2Button.setFont(messageFont);
		shop2Button.addActionListener(handler);
		shopsPanel.add(shop2Button);

		shop3Button = new JButton(WholeMart.getStoreName());
		shop3Button.setFont(messageFont);
		shop3Button.addActionListener(handler);
		shopsPanel.add(shop3Button);
	}

	private class actionHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == shop1Button) {
				chooseShop(event);
				selectedShop = FreshWear.getStoreName();
			}else if(event.getSource() == shop2Button) {
				chooseShop(event);
				selectedShop = TechShop.getStoreName();
			}else if(event.getSource() == shop3Button) {
				chooseShop(event);
				selectedShop = WholeMart.getStoreName();
			}else if(event.getSource() == shop1Item1Button) {
				buyItemShop1(event);
			}else if(event.getSource() == shop1Item2Button) {
				buyItemShop1(event);
			}else if(event.getSource() == shop1Item3Button) {
				buyItemShop1(event);
			}else if(event.getSource() == shop1Item4Button) {
				buyItemShop1(event);
			}else if(event.getSource() == shop1Item5Button) {
				buyItemShop1(event);
			}else if(event.getSource() == shop2Item1Button) {
				buyItemShop2(event);
			}else if(event.getSource() == shop2Item2Button) {
				buyItemShop2(event);
			}else if(event.getSource() == shop2Item3Button) {
				buyItemShop2(event);
			}else if(event.getSource() == shop2Item4Button) {
				buyItemShop2(event);
			}else if(event.getSource() == shop2Item5Button) {
				buyItemShop2(event);
			}else if(event.getSource() == shop3Item1Button) {
				buyItemShop3(event);
			}else if(event.getSource() == shop3Item2Button) {
				buyItemShop3(event);
			}else if(event.getSource() == shop3Item3Button) {
				buyItemShop3(event);
			}else if(event.getSource() == shop3Item4Button) {
				buyItemShop3(event);
			}else if(event.getSource() == shop3Item5Button) {
				buyItemShop3(event);
			}else if(event.getSource() == shoppingCartButton) {
				// View shopping cart
				shopsPanel.setVisible(false);
				shoppingCartCancelPanel.setVisible(false);
				messageField.setVisible(false);
				totalAmountField.setVisible(false);
				shoppingCartScreen(shoppingCartButton.getActionCommand());
			}else if(event.getSource() == showItemListButton) {
				// Shows JOptionPane of item list
				OptionPane optionPane = new OptionPane();
				if(selectedShop.equals("FreshWear")) {
					optionPane.viewItemList(FreshWear);
				}else if(selectedShop.equals("TechShop")) {
					optionPane.viewItemList(TechShop);
				}else if(selectedShop.equals("WholeMart"))
					optionPane.viewItemList(WholeMart);
			}else if(event.getSource() == returnButton) {
				// Back button to go back to vendor screen
				messageField.setVisible(false);
				totalAmountField.setVisible(false);
				showItemListPanel.setVisible(false);
				checkOutCancelOrderPanel.setVisible(false);
				returnPanel.setVisible(false);

				if(FreshWear.getTotal()> 0)
				{
					message2Field.setText("You have items in FreshWear");
				}
				else if(TechShop.getTotal()>0)
				{
					message2Field.setText("You have items in TechShop");
				}
				else if(WholeMart.getTotal()>0)
				{
					message2Field.setText("You have items in WholeMart");
				}
				vendorScreen();
			}else if(event.getSource() == checkOutButton) {
				// Check out of shopping cart. Must be logged in to checkout and card must not be at limit
				checkOut();

			}else if(event.getSource() == cancelOrderButton) {
				// Cancels order and resets everything
				messageField.setVisible(false);
				totalAmountField.setVisible(false);
				showItemListPanel.setVisible(false);
				checkOutCancelOrderPanel.setVisible(false);
				returnPanel.setVisible(false);
				WholeMart.resetCart();
				TechShop.resetCart();
				FreshWear.resetCart();
				shoppingCartButton.setActionCommand("");
				vendorScreen();
			}else if(event.getSource() == cancelButton) {
				// Cancel button in buy item screen to go back to vendor screen
				shopsPanel.setVisible(false);
				shoppingCartCancelPanel.setVisible(false);
				messageField.setVisible(false);
				totalAmountField.setVisible(false);
				WholeMart.resetCart();
				TechShop.resetCart();
				FreshWear.resetCart();
				shoppingCartButton.setActionCommand("");
				vendorScreen();
			}else if(event.getSource() == backButton) {
				// Back button in vendor screen to go back to the main frame
				setVisible(false);
				MainFrame mainFrame = new MainFrame();
				mainFrame.setSize(700, 600);
				mainFrame.setLocation(600, 250);
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainFrame.setLayout(null);
				mainFrame.setVisible(true);
			}else if(event.getSource() == checkOutFinishButton) {
				// Finish checking out an item

				double total = 0;
				String storeName = "";
				String description = "";

				if(FreshWear.getTotal()!=0)
				{
					total = FreshWear.getTotal();
					storeName = "FreshWear";
					description= FreshWear.getCart().get(0)+"....";
				}
				else if(TechShop.itemsInCart!= 0)
				{
					total = TechShop.getTotal();
					storeName = "TechShop";
					description= TechShop.getCart().get(0)+"....";
				}
				else if(WholeMart.itemsInCart!=0){
					total = WholeMart.getTotal();
					storeName = "WholeMart";
					description= WholeMart.getCart().get(0)+"....";
				}

				int accountNumber = Integer.parseInt(cardNumberField.getText());
				
				String username;
				double originalCreditLimit;
				double newCreditLimit;
				total = Double.parseDouble(df.format(total));
				String password = passwordField.getText();
				
				boolean check = false;
				
				for(int i= 0; i<visa.getUserCreditCards().size(); i++)
				{
					if(visa.getUserCreditCards().get(i).getAccountNumber()==accountNumber &&
							visa.getUserCreditCards().get(i).getPassword().equals(password)&& visa.getUserCreditCards().get(i).getCreditLimit()>= total)
					{
						username = visa.getUserCreditCards().get(i).getUsername();
						originalCreditLimit = visa.getUserCreditCards().get(i).getCreditLimit();
						newCreditLimit = originalCreditLimit - total;
						System.out.println("thank you for purchaseing" + username);
						visa.updateCreditLimit(accountNumber, newCreditLimit);
						visa.updateAmountDue(accountNumber, total);
						visa.createCardTransaction(accountNumber, storeName, total, description, username);
						WholeMart.resetCart();
						TechShop.resetCart();
						FreshWear.resetCart();
						check = true;
						checkOutPanel.setVisible(false);
						vendorScreen();
						break;
					}
				}
				if(!check)
					System.out.println("Wrong CreditCard Information provided or password or exceeded over credit limit");

			}else if(event.getSource() == checkOutBackButton) {
				// Back button in the check out screen to go back to the shopping cart
				checkOutPanel.setVisible(false);
				shoppingCartScreen(shoppingCartButton.getActionCommand());
			}
		}
	}

	public void chooseShop(ActionEvent event) {

		backPanel.setVisible(false);
		message2Field.setVisible(false);

		List <String> itemlist;

		if(event.getSource() == shop1Button) {

			messageField = new JTextField("Select an item");
			messageField.setBounds(320, 100, 350, 90);
			messageField.setFont(messageFont);
			messageField.setEditable(false);
			add(messageField);

			totalAmountField = new JTextField("Items in shopping cart: " + FreshWear.itemsInCart);
			totalAmountField.setBounds(320, 200, 350, 90);
			totalAmountField.setFont(messageFont);
			totalAmountField.setEditable(false);
			add(totalAmountField);

			shoppingCartCancelPanel = new JPanel();
			shoppingCartCancelPanel.setBounds(320, 300, 350, 100);
			shoppingCartCancelPanel.setLayout(new GridLayout(1,2));
			add(shoppingCartCancelPanel);

			shoppingCartButton = new JButton("Shopping Cart");
			shoppingCartButton.setActionCommand("FreshWear");
			shoppingCartButton.setFont(messageFont);
			shoppingCartButton.addActionListener(handler);
			shoppingCartCancelPanel.add(shoppingCartButton);

			cancelButton = new JButton("Cancel");
			cancelButton.setFont(messageFont);
			cancelButton.addActionListener(handler);
			shoppingCartCancelPanel.add(cancelButton);

			shopsPanel.setVisible(false);
			shopsPanel = new JPanel();
			shopsPanel.setBounds(60, 100, 250, 300);
			shopsPanel.setLayout(new GridLayout(5,1));
			add(shopsPanel);

			itemlist = new ArrayList<>(5);

			for(String x: FreshWear.getItems().keySet())
			{
				itemlist.add(x);
			}

			shop1Item1Button  = new JButton(itemlist.get(0));
			shop1Item1Button.setFont(messageFont);
			shop1Item1Button.addActionListener(handler);
			shopsPanel.add(shop1Item1Button);

			shop1Item2Button  = new JButton(itemlist.get(1));
			shop1Item2Button.setFont(messageFont);
			shop1Item2Button.addActionListener(handler);
			shopsPanel.add(shop1Item2Button);

			shop1Item3Button  = new JButton(itemlist.get(2));
			shop1Item3Button.setFont(messageFont);
			shop1Item3Button.addActionListener(handler);
			shopsPanel.add(shop1Item3Button);

			shop1Item4Button  = new JButton(itemlist.get(3));
			shop1Item4Button.setFont(messageFont);
			shop1Item4Button.addActionListener(handler);
			shopsPanel.add(shop1Item4Button);

			shop1Item5Button  = new JButton(itemlist.get(4));
			shop1Item5Button.setFont(messageFont);
			shop1Item5Button.addActionListener(handler);
			shopsPanel.add(shop1Item5Button);
		}
		if(event.getSource() == shop2Button) {

			messageField = new JTextField("Select an item");
			messageField.setBounds(320, 100, 350, 90);
			messageField.setFont(messageFont);
			messageField.setEditable(false);
			add(messageField);

			totalAmountField = new JTextField("Items in shopping cart: " + TechShop.itemsInCart);
			totalAmountField.setBounds(320, 200, 350, 90);
			totalAmountField.setFont(messageFont);
			totalAmountField.setEditable(false);
			add(totalAmountField);

			shoppingCartCancelPanel = new JPanel();
			shoppingCartCancelPanel.setBounds(320, 300, 350, 100);
			shoppingCartCancelPanel.setLayout(new GridLayout(1,2));
			add(shoppingCartCancelPanel);

			shoppingCartButton = new JButton("Shopping Cart");
			shoppingCartButton.setActionCommand("TechShop");
			shoppingCartButton.setFont(messageFont);
			shoppingCartButton.addActionListener(handler);
			shoppingCartCancelPanel.add(shoppingCartButton);

			cancelButton = new JButton("Cancel");
			cancelButton.setFont(messageFont);
			cancelButton.addActionListener(handler);
			shoppingCartCancelPanel.add(cancelButton);

			shopsPanel.setVisible(false);
			shopsPanel = new JPanel();
			shopsPanel.setBounds(60, 100, 250, 300);
			shopsPanel.setLayout(new GridLayout(5,1));
			add(shopsPanel);

			itemlist = new ArrayList<>(5);

			for(String x: TechShop.getItems().keySet())
			{
				itemlist.add(x);
			}

			shop2Item1Button  = new JButton(itemlist.get(0));
			shop2Item1Button.setFont(messageFont);
			shop2Item1Button.addActionListener(handler);
			shopsPanel.add(shop2Item1Button);

			shop2Item2Button  = new JButton(itemlist.get(1));
			shop2Item2Button.setFont(messageFont);
			shop2Item2Button.addActionListener(handler);
			shopsPanel.add(shop2Item2Button);

			shop2Item3Button  = new JButton(itemlist.get(2));
			shop2Item3Button.setFont(messageFont);
			shop2Item3Button.addActionListener(handler);
			shopsPanel.add(shop2Item3Button);

			shop2Item4Button  = new JButton(itemlist.get(3));
			shop2Item4Button.setFont(messageFont);
			shop2Item4Button.addActionListener(handler);
			shopsPanel.add(shop2Item4Button);

			shop2Item5Button  = new JButton(itemlist.get(4));
			shop2Item5Button.setFont(messageFont);
			shop2Item5Button.addActionListener(handler);
			shopsPanel.add(shop2Item5Button);
		}
		if(event.getSource() == shop3Button) {

			messageField = new JTextField("Select an item");
			messageField.setBounds(320, 100, 350, 90);
			messageField.setFont(messageFont);
			messageField.setEditable(false);
			add(messageField);

			totalAmountField = new JTextField("Items in shopping cart: " +WholeMart.itemsInCart);
			totalAmountField.setBounds(320, 200, 350, 90);
			totalAmountField.setFont(messageFont);
			totalAmountField.setEditable(false);
			add(totalAmountField);

			shoppingCartCancelPanel = new JPanel();
			shoppingCartCancelPanel.setBounds(320, 300, 350, 100);
			shoppingCartCancelPanel.setLayout(new GridLayout(1,2));
			add(shoppingCartCancelPanel);

			shoppingCartButton = new JButton("Shopping Cart");
			shoppingCartButton.setActionCommand("WholeMart");
			shoppingCartButton.setFont(messageFont);
			shoppingCartButton.addActionListener(handler);
			shoppingCartCancelPanel.add(shoppingCartButton);

			cancelButton = new JButton("Cancel");
			cancelButton.setFont(messageFont);
			cancelButton.addActionListener(handler);
			shoppingCartCancelPanel.add(cancelButton);

			shopsPanel.setVisible(false);
			shopsPanel = new JPanel();
			shopsPanel.setBounds(60, 100, 250, 300);
			shopsPanel.setLayout(new GridLayout(5,1));
			add(shopsPanel);

			itemlist = new ArrayList<>(5);

			for(String x: WholeMart.getItems().keySet())
			{
				itemlist.add(x);
			}

			shop3Item1Button  = new JButton(itemlist.get(0));
			shop3Item1Button.setFont(messageFont);
			shop3Item1Button.addActionListener(handler);
			shopsPanel.add(shop3Item1Button);

			shop3Item2Button  = new JButton(itemlist.get(1));
			shop3Item2Button.setFont(messageFont);
			shop3Item2Button.addActionListener(handler);
			shopsPanel.add(shop3Item2Button);

			shop3Item3Button  = new JButton(itemlist.get(2));
			shop3Item3Button.setFont(messageFont);
			shop3Item3Button.addActionListener(handler);
			shopsPanel.add(shop3Item3Button);

			shop3Item4Button  = new JButton(itemlist.get(3));
			shop3Item4Button.setFont(messageFont);
			shop3Item4Button.addActionListener(handler);
			shopsPanel.add(shop3Item4Button);

			shop3Item5Button  = new JButton(itemlist.get(4));
			shop3Item5Button.setFont(messageFont);
			shop3Item5Button.addActionListener(handler);
			shopsPanel.add(shop3Item5Button);
		}
	}

	public void buyItemShop1(ActionEvent event) {

		List<Double>itemPrice;
		itemPrice = new ArrayList<>(5);

		for(Double x: FreshWear.getItems().values())
		{
			itemPrice.add(x);
		}

		if(event.getSource() == shop1Item1Button) {

			FreshWear.addToCart(shop1Item1Button.getActionCommand());

			messageField.setVisible(false);
			messageField = new JTextField("Selected: "+shop1Item1Button.getActionCommand()+" Cost: $" + itemPrice.get(0));
			messageField.setBounds(320, 100, 350, 90);
			messageField.setFont(messageFont);
			messageField.setEditable(false);
			add(messageField);

			totalAmountField.setVisible(false);
			totalAmountField = new JTextField("Items in shopping cart: " + FreshWear.itemsInCart);
			totalAmountField.setBounds(320, 200, 350, 90);
			totalAmountField.setFont(messageFont);
			totalAmountField.setEditable(false);
			add(totalAmountField);
		} else if (event.getSource() == shop1Item2Button) {

			FreshWear.addToCart(shop1Item2Button.getActionCommand());

			messageField.setVisible(false);
			messageField = new JTextField("Selected: "+shop1Item2Button.getActionCommand()+" Cost: $" + itemPrice.get(1));
			messageField.setBounds(320, 100, 350, 90);
			messageField.setFont(messageFont);
			messageField.setEditable(false);
			add(messageField);

			totalAmountField.setVisible(false);
			totalAmountField = new JTextField("Items in shopping cart: " + FreshWear.itemsInCart);
			totalAmountField.setBounds(320, 200, 350, 90);
			totalAmountField.setFont(messageFont);
			totalAmountField.setEditable(false);
			add(totalAmountField);
		} else if (event.getSource() == shop1Item3Button) {

			FreshWear.addToCart(shop1Item3Button.getActionCommand());

			messageField.setVisible(false);
			messageField = new JTextField("Selected: "+shop1Item3Button.getActionCommand()+" Cost: $" + itemPrice.get(2));
			messageField.setBounds(320, 100, 350, 90);
			messageField.setFont(messageFont);
			messageField.setEditable(false);
			add(messageField);

			totalAmountField.setVisible(false);
			totalAmountField = new JTextField("Items in shopping cart: " + FreshWear.itemsInCart);
			totalAmountField.setBounds(320, 200, 350, 90);
			totalAmountField.setFont(messageFont);
			totalAmountField.setEditable(false);
			add(totalAmountField);
		}else if (event.getSource() == shop1Item4Button) {

			FreshWear.addToCart(shop1Item4Button.getActionCommand());

			messageField.setVisible(false);
			messageField = new JTextField("Selected: "+shop1Item4Button.getActionCommand()+" Cost: $" + itemPrice.get(3));
			messageField.setBounds(320, 100, 350, 90);
			messageField.setFont(messageFont);
			messageField.setEditable(false);
			add(messageField);

			totalAmountField.setVisible(false);
			totalAmountField = new JTextField("Items in shopping cart: " + FreshWear.itemsInCart);
			totalAmountField.setBounds(320, 200, 350, 90);
			totalAmountField.setFont(messageFont);
			totalAmountField.setEditable(false);
			add(totalAmountField);
		} else if (event.getSource() == shop1Item5Button) {

			FreshWear.addToCart(shop1Item5Button.getActionCommand());

			messageField.setVisible(false);
			messageField = new JTextField("Selected: "+shop1Item5Button.getActionCommand()+" Cost: $" + itemPrice.get(4));
			messageField.setBounds(320, 100, 350, 90);
			messageField.setFont(messageFont);
			messageField.setEditable(false);
			add(messageField);

			totalAmountField.setVisible(false);
			totalAmountField = new JTextField("Items in shopping cart: " + FreshWear.itemsInCart);
			totalAmountField.setBounds(320, 200, 350, 90);
			totalAmountField.setFont(messageFont);
			totalAmountField.setEditable(false);
			add(totalAmountField);
		}
	}

	public void buyItemShop2(ActionEvent event) {

		List<Double>itemPrice;
		itemPrice = new ArrayList<>(5);

		for(Double x: TechShop.getItems().values())
		{
			itemPrice.add(x);
		}
		if(event.getSource() == shop2Item1Button) {

			TechShop.addToCart(shop2Item1Button.getActionCommand());

			messageField.setVisible(false);
			messageField = new JTextField("Selected: "+shop2Item1Button.getActionCommand()+" Cost: $" + itemPrice.get(0));
			messageField.setBounds(320, 100, 350, 90);
			messageField.setFont(messageFont);
			messageField.setEditable(false);
			add(messageField);

			totalAmountField.setVisible(false);
			totalAmountField = new JTextField("Items in shopping cart: " + TechShop.itemsInCart);
			totalAmountField.setBounds(320, 200, 350, 90);
			totalAmountField.setFont(messageFont);
			totalAmountField.setEditable(false);
			add(totalAmountField);
		} else if (event.getSource() == shop2Item2Button) {

			TechShop.addToCart(shop2Item2Button.getActionCommand());

			messageField.setVisible(false);
			messageField = new JTextField("Selected: "+shop2Item2Button.getActionCommand()+" Cost: $" + itemPrice.get(1));
			messageField.setBounds(320, 100, 350, 90);
			messageField.setFont(messageFont);
			messageField.setEditable(false);
			add(messageField);

			totalAmountField.setVisible(false);
			totalAmountField = new JTextField("Items in shopping cart: " +TechShop.itemsInCart);
			totalAmountField.setBounds(320, 200, 350, 90);
			totalAmountField.setFont(messageFont);
			totalAmountField.setEditable(false);
			add(totalAmountField);
		} else if (event.getSource() == shop2Item3Button) {

			TechShop.addToCart(shop2Item3Button.getActionCommand());

			messageField.setVisible(false);
			messageField = new JTextField("Selected: "+shop2Item3Button.getActionCommand()+" Cost: $" + itemPrice.get(2));
			messageField.setBounds(320, 100, 350, 90);
			messageField.setFont(messageFont);
			messageField.setEditable(false);
			add(messageField);

			totalAmountField.setVisible(false);
			totalAmountField = new JTextField("Items in shopping cart: " +TechShop.itemsInCart);
			totalAmountField.setBounds(320, 200, 350, 90);
			totalAmountField.setFont(messageFont);
			totalAmountField.setEditable(false);
			add(totalAmountField);
		} else if (event.getSource() == shop2Item4Button) {

			TechShop.addToCart(shop2Item4Button.getActionCommand());

			messageField.setVisible(false);
			messageField = new JTextField("Selected: "+shop2Item4Button.getActionCommand()+" Cost: $" + itemPrice.get(3));
			messageField.setBounds(320, 100, 350, 90);
			messageField.setFont(messageFont);
			messageField.setEditable(false);
			add(messageField);

			totalAmountField.setVisible(false);
			totalAmountField = new JTextField("Items in shopping cart: " + TechShop.itemsInCart);
			totalAmountField.setBounds(320, 200, 350, 90);
			totalAmountField.setFont(messageFont);
			totalAmountField.setEditable(false);
			add(totalAmountField);
		} else if (event.getSource() == shop2Item5Button) {

			TechShop.addToCart(shop2Item5Button.getActionCommand());

			messageField.setVisible(false);
			messageField = new JTextField("Selected: "+shop2Item5Button.getActionCommand()+" Cost: $" + itemPrice.get(4));
			messageField.setBounds(320, 100, 350, 90);
			messageField.setFont(messageFont);
			messageField.setEditable(false);
			add(messageField);

			totalAmountField.setVisible(false);
			totalAmountField = new JTextField("Items in shopping cart: " + TechShop.itemsInCart);
			totalAmountField.setBounds(320, 200, 350, 90);
			totalAmountField.setFont(messageFont);
			totalAmountField.setEditable(false);
			add(totalAmountField);
		}
	}

	public void buyItemShop3(ActionEvent event) {

		List<Double>itemPrice;
		itemPrice = new ArrayList<>(5);

		for(Double x: WholeMart.getItems().values())
		{
			itemPrice.add(x);
		}

		if(event.getSource() == shop3Item1Button) {

			WholeMart.addToCart(shop3Item1Button.getActionCommand());

			messageField.setVisible(false);
			messageField = new JTextField("Selected: "+shop3Item1Button.getActionCommand()+" Cost: $" + itemPrice.get(0));
			messageField.setBounds(320, 100, 350, 90);
			messageField.setFont(messageFont);
			messageField.setEditable(false);
			add(messageField);

			totalAmountField.setVisible(false);
			totalAmountField = new JTextField("Items in shopping cart: " + WholeMart.itemsInCart);
			totalAmountField.setBounds(320, 200, 350, 90);
			totalAmountField.setFont(messageFont);
			totalAmountField.setEditable(false);
			add(totalAmountField);
		} else if (event.getSource() == shop3Item2Button) {

			WholeMart.addToCart(shop3Item2Button.getActionCommand());

			messageField.setVisible(false);
			messageField = new JTextField("Selected: "+shop3Item2Button.getActionCommand()+" Cost: $" + itemPrice.get(1));
			messageField.setBounds(320, 100, 350, 90);
			messageField.setFont(messageFont);
			messageField.setEditable(false);
			add(messageField);

			totalAmountField.setVisible(false);
			totalAmountField = new JTextField("Items in shopping cart: " + WholeMart.itemsInCart);
			totalAmountField.setBounds(320, 200, 350, 90);
			totalAmountField.setFont(messageFont);
			totalAmountField.setEditable(false);
			add(totalAmountField);
		}else if (event.getSource() == shop3Item3Button) {

			WholeMart.addToCart(shop3Item3Button.getActionCommand());

			messageField.setVisible(false);
			messageField = new JTextField("Selected: "+shop3Item3Button.getActionCommand()+" Cost: $" + itemPrice.get(2));
			messageField.setBounds(320, 100, 350, 90);
			messageField.setFont(messageFont);
			messageField.setEditable(false);
			add(messageField);

			totalAmountField.setVisible(false);
			totalAmountField = new JTextField("Items in shopping cart: " + WholeMart.itemsInCart);
			totalAmountField.setBounds(320, 200, 350, 90);
			totalAmountField.setFont(messageFont);
			totalAmountField.setEditable(false);
			add(totalAmountField);
		}else if (event.getSource() == shop3Item4Button) {

			WholeMart.addToCart(shop3Item4Button.getActionCommand());

			messageField.setVisible(false);
			messageField = new JTextField("Selected: "+shop3Item4Button.getActionCommand()+" Cost: $" + itemPrice.get(3));
			messageField.setBounds(320, 100, 350, 90);
			messageField.setFont(messageFont);
			messageField.setEditable(false);
			add(messageField);

			totalAmountField.setVisible(false);
			totalAmountField = new JTextField("Items in shopping cart: " +WholeMart.itemsInCart);
			totalAmountField.setBounds(320, 200, 350, 90);
			totalAmountField.setFont(messageFont);
			totalAmountField.setEditable(false);
			add(totalAmountField);
		} else if (event.getSource() == shop3Item5Button) {

			WholeMart.addToCart(shop3Item5Button.getActionCommand());

			messageField.setVisible(false);
			messageField = new JTextField("Selected: "+shop3Item5Button.getActionCommand()+" Cost: $" + itemPrice.get(4));
			messageField.setBounds(320, 100, 350, 90);
			messageField.setFont(messageFont);
			messageField.setEditable(false);
			add(messageField);

			totalAmountField.setVisible(false);
			totalAmountField = new JTextField("Items in shopping cart: " + WholeMart.itemsInCart);
			totalAmountField.setBounds(320, 200, 350, 90);
			totalAmountField.setFont(messageFont);
			totalAmountField.setEditable(false);
			add(totalAmountField);
		}
	}

	public void shoppingCartScreen(String storename) {

		messageField = new JTextField("Shopping Cart");
		messageField.setBounds(10, 30, 600, 50);
		messageField.setFont(messageFont);
		messageField.setEditable(false);
		add(messageField);

		if(storename.equals("FreshWear")){
			if(FreshWear.getTotal() == 0.0) {
				totalAmountField = new JTextField("Total Amount Needed: $0.00");
				totalAmountField.setBounds(10, 90, 600, 50);
				totalAmountField.setFont(messageFont);
				totalAmountField.setEditable(false);
				add(totalAmountField);
			}else {
				totalAmountField = new JTextField("Total Amount Needed: $" + df.format(FreshWear.getTotal()));
				totalAmountField.setBounds(10, 90, 600, 50);
				totalAmountField.setFont(messageFont);
				totalAmountField.setEditable(false);
				add(totalAmountField);
				}
		}
		else if(storename.equals("TechShop")){
			if(TechShop.getTotal()== 0) {
				totalAmountField = new JTextField("Total Amount Needed: $0.00");
				totalAmountField.setBounds(10, 90, 600, 50);
				totalAmountField.setFont(messageFont);
				totalAmountField.setEditable(false);
				add(totalAmountField);
			}else {
				totalAmountField = new JTextField("Total Amount Needed: $" + df.format(TechShop.getTotal()));
				totalAmountField.setBounds(10, 90, 600, 50);
				totalAmountField.setFont(messageFont);
				totalAmountField.setEditable(false);
				add(totalAmountField);
				}
		}
		else
		{
			if(WholeMart.getTotal() == 0.0) {
				totalAmountField = new JTextField("Total Amount Needed: $0.00");
				totalAmountField.setBounds(10, 90, 600, 50);
				totalAmountField.setFont(messageFont);
				totalAmountField.setEditable(false);
				add(totalAmountField);
			}else {
				totalAmountField = new JTextField("Total Amount Needed: $" + df.format(WholeMart.getTotal()));
				totalAmountField.setBounds(10, 90, 600, 50);
				totalAmountField.setFont(messageFont);
				totalAmountField.setEditable(false);
				add(totalAmountField);
				}
		}

		showItemListPanel = new JPanel();
		showItemListPanel.setBounds(150, 200, 350, 100);
		showItemListPanel.setLayout(new GridLayout(1,1));
		add(showItemListPanel);

		showItemListButton = new JButton("Show item list ");
		showItemListButton.setFont(messageFont);
		showItemListButton.addActionListener(handler);
		showItemListPanel.add(showItemListButton);

		checkOutCancelOrderPanel = new JPanel();
		checkOutCancelOrderPanel.setBounds(150, 310, 350, 100);
		checkOutCancelOrderPanel.setLayout(new GridLayout(1,2));
		add(checkOutCancelOrderPanel);

		checkOutButton = new JButton("Check Out ");
		checkOutButton.setFont(messageFont);
		checkOutButton.addActionListener(handler);
		checkOutCancelOrderPanel.add(checkOutButton);

		cancelOrderButton = new JButton("Cancel Order ");
		cancelOrderButton.setFont(messageFont);
		cancelOrderButton.addActionListener(handler);
		checkOutCancelOrderPanel.add(cancelOrderButton);

		returnPanel = new JPanel();
		returnPanel.setBounds(10, 450, 250, 50);
		returnPanel.setLayout(new GridLayout(1,1));
		add(returnPanel);

		returnButton = new JButton("Return to shopping ");
		returnButton.setFont(messageFont);
		returnButton.addActionListener(handler);
		returnPanel.add(returnButton);
	}

	public void checkOut() {

		messageField.setVisible(false);
		totalAmountField.setVisible(false);
		showItemListPanel.setVisible(false);
		checkOutCancelOrderPanel.setVisible(false);
		returnPanel.setVisible(false);

		checkOutPanel = new JPanel();
		checkOutPanel.setBounds(180, 150, 300, 110);
		checkOutPanel.setLayout(new GridLayout(3,3));
		add(checkOutPanel);

		cardNumberLabel = new JLabel("Card Number: ");
		cardNumberLabel.setFont(messageFont);
		checkOutPanel.add(cardNumberLabel);

		cardNumberField = new JTextField();
		checkOutPanel.add(cardNumberField);

		passwordLabel = new JLabel("Password: ");
		passwordLabel.setFont(messageFont);
		checkOutPanel.add(passwordLabel);

		passwordField = new JPasswordField();
		checkOutPanel.add(passwordField);

		checkOutBackButton = new JButton("Back");
		checkOutBackButton.setFont(messageFont);
		checkOutBackButton.addActionListener(handler);
		checkOutPanel.add(checkOutBackButton);

		checkOutFinishButton = new JButton("Buy ");
		checkOutFinishButton.setFont(messageFont);
		checkOutFinishButton.addActionListener(handler);
		checkOutPanel.add(checkOutFinishButton);
	}
}
