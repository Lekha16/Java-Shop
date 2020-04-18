

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This class is the main shop controller worked behind views
 */
public class ShopController {

	/*
	 * STATIC FIELDS
	 */
	
	/**
	 * <pre>
	 * The default product icon.
	 * </pre>
	 */
	public static ImageIcon NO_IMAGE_ICON = generateIcon("https://placeholdit.imgix.net/~text?txtsize=23&bg=ffffff&txtclr=000000&txt=No+Image&w=200&h=200", 150, 150);
	/**
	 * <pre>
	 * The store logo.
	 * </pre>
	 */
	public static ImageIcon LOGO_ICON = generateIcon("https://png.pngtree.com/png-clipart/20190903/original/pngtree-shopping-cart-icon-png-image_4423346.jpg", 150, 150);
	public static ImageIcon FRUITS_ICON = generateIcon("https://png.pngtree.com/png-clipart/20190630/original/pngtree-vector-fruit-icon-clipart-png-image_4180680.jpg", 150, 150);
	public static ImageIcon VEGETABLES_ICON = generateIcon("https://png.pngtree.com/png-clipart/20190515/original/pngtree-simple-style-vegetable-fruit-icon-element-dishbeanscucumbertomatocorneggplantstrawberrypineapplevegetablesfruitgreennutritionfoodfood-png-image_4030926.jpg", 150, 150);
	public static ImageIcon STAPLES_ICON = generateIcon("https://cdn.shopify.com/s/files/1/0349/4297/products/ricebran_oil_large.jpg", 150, 150);
	public static ImageIcon DAIRY_ICON = generateIcon("https://image.flaticon.com/icons/png/512/1509/1509470.png", 150, 150);
	/**
	 * <pre>
	 * The image cache that is used to save time and speed up loading.
	 * </pre>
	 */
	public static HashMap<String, ImageIcon> IMAGE_CACHE;
	
	/**
	 * <pre>
	 * Generates an icon that can be used elsewere in the application.
	 * </pre>
	 * @param imgLoc The URL of the image
	 * @param width The desired icon width
	 * @param height The desired icon height
	 * @return The generated icon
	 */
	public static ImageIcon generateIcon(String imgLoc, int width, int height){
		if(IMAGE_CACHE == null)  IMAGE_CACHE = new HashMap<String, ImageIcon>();
		if(IMAGE_CACHE.containsKey(imgLoc)) return IMAGE_CACHE.get(imgLoc);
		try {
			URL url = new URL(imgLoc);
			ImageIcon icon = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
			IMAGE_CACHE.put(imgLoc, icon);
			return icon;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	/*
	 * END STATIC
	 */
	
	private JFrame window = new JFrame();
	private Model backend;
	
//	private Cart cart = new Cart();
	private String currentUserID;
	
	/**
	 * <pre>
	 * Creates a new instance of ShopController.
	 * Make sure to call the "init" method after this!
	 * </pre>
	 * @param b The Model with all of the back-end links that the store is to use
	 */
	public ShopController(Model b){
		this.backend = b;
	}
	
	/**
	 * <pre>
	 * Sets the store's current view after setting the view's controller and initializing it.
	 * </pre>
	 * @param view The view to set
	 */
	public void setView(View view){
		view.setController(this);
		view.initialize();
		window.setContentPane(view);
		window.revalidate();
	}
	
	/**
	 * @return The JFrame that holds the store.
	 */
	public JFrame getWindow(){
		return window;
	}
	
	/**
	 * @return The Model instance controlling the store.
	 */
	public Model getBackend(){
		return this.backend;
	}
	
	/**
	 * <pre>
	 * Initialize and show the store window.
	 * Also displays the LoginView.
	 * </pre>
	 */
	public void init(){
		window.setResizable(false);
		window.setTitle("Shop");
		window.setSize(900, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		this.setView(new LoginView());
	}
	
	/**
	 * <pre>
	 * Shows a popup message.
	 * </pre>
	 * @param message The text in the popup window.
	 */
	public void showPopup(String message){
		JOptionPane.showMessageDialog(window, message);
	}
	
	
	/*
	 * LOGIN AND USERS
	 * ------------------------------------------------
	 */

	/**
	 * @return The customer details received from the Model instance.
	 */
	public Customer getCurrentCustomerDetails(){
		return getBackend().getUserInfo(currentUserID);
	}
	
	/**
	 * <pre>
	 * Attempts to sign up a user.
	 * 
	 * This will display an error to the user if any of the following are true:
	 * - The user ID is less than 3 chars long
	 * - The password is less than 5 chars long
	 * - The two passwords do not match.
	 * 
	 * If all of the above tests succeed then the back-end will be asked to create a user.
	 * If the back-end succeeds in creating an account, the user will be logged in, if not, they will be shown an error.
	 * </pre>
	 * @param username The entered user ID.
	 * @param pass The entered password.
	 * @param confPass The entered confirmed password.
	 */
	public void signup(String username, String phoneNum, String pass, String confPass){
		
		// Ensuring length
		if(username.length() < 3) {
			showPopup("Your user ID must be at least 3 chars long!");
			return;
		}
		
		if(phoneNum.length() < 10) {
			showPopup("Please enter a valid phone number.");
			return;
		}
		else if(pass.length() < 5){
			showPopup("Your password must be at least 5 chars long!");
			return;
		}
		else if(!pass.equals(confPass)){
			showPopup("The passwords do not match");
			return;
		}
		
		boolean success = getBackend().signup(username, phoneNum, pass);
		
		if(!success){
			showPopup("Signup failed, that userID may already be in use!");
		} else {
			showPopup("Your account has been created, continue shopping!");
			//ShopController s = getController();
			//s.getWindow().dispose();
			//s.init();

			// The created account can only be used as CUSTOMER
			attemptLogin(username, pass, "CUSTOMER");
		}
	}
	
	/**
	 * <pre>
	 * Attempts to log a user into the store.
	 * 
	 * If the login succeeds, they will be presented with the product list, if not, they will be shown an error.
	 * </pre>
	 * @param username The supplied user ID
	 * @param password The supplied password
	 */
	public void attemptLogin(String username, String password, String loginIdentity){
		if(backend.login(username, password,loginIdentity)){
			currentUserID = username;
			// If customer logged in
			if(loginIdentity.equals("CUSTOMER")){
				showProductList();
			}else{
				showAdminProductList();
			}
			
		} else {
			showPopup("Login failed! Please ensure that your user ID and password are correct.");
		}
	}
	
	/**
	 * <pre>
	 * Calls the appropriate methods on the Model instance to update the information about the current user.
	 * 
	 * If no user is logged in, an error message will be displayed in the console.
	 * </pre>
	 * @param c The new user details.
	 */
	public void updateUserDetails(Customer c){
		if(this.currentUserID != null){
			boolean success = getBackend().setUserInfo(this.currentUserID, c);
			if(!success){
				showPopup("There was an error saving your information! Please try again later.");
			}
		} else {
			System.err.println("Can't update user info, no one is signed in!");
		}
	}

	
	/*
	 * PRODUCTS
	 * ------------------------------------------------
	 */

	/**
	 * <pre>
	 * Shows the checkout dialog.
	 * </pre>
	 */
	public void showCheckout(){
		ConfirmDialog.display(this);
	}
	
	///////logout
//	public void showLogout(){
//		ConfirmDialog.display(this);
//	}
	////////////////
	
	/**
	 * @return The current user's cart.
	 */
	public Cart getCart(){
//		 return cart;
		return getCurrentCustomerDetails().getCart();
	}
	
	/**
	 * <pre>
	 * Adds a product to the cart. See "Cart.addToCart" for more information.
	 * </pre>
	 * @param p The product
	 * @param quantity The quantity to add
	 */
	public void addToCart(Product p, int quantity){
		Customer c = 	getCurrentCustomerDetails();
		Cart car = c.getCart();
		car.add(p, quantity);
//		getCurrentCustomerDetails();
	}
	
	/**
	 * <pre>
	 * Shows the cart view.
	 * </pre>
	 */
	public void showCartView(){
		setView(new CartView());
	}
	
	/**
	 * <pre>
	 * See "Model.getPrice(Cart)" for more information.
	 * </pre>
	 * @return The total price of all item in the cart
	 */
	public double getTotalCartPrice(){
		return getBackend().getPrice(getCurrentCustomerDetails().getCart());
	}

	/**
	 * <pre>
	 * Shows the product list view.
	 * </pre>
	 */
	public void showProductList() {
		setView(new ProductListView());
	}
	
	/***
	 * Show admin product list view
	 */
	public void showAdminProductList(){
		setView(new AdminProductListView());
	}

	/**
	 * <pre>
	 * Attempts a transaction using the current user's details and the current cart.
	 * </pre>
	 */
	public void attemptTransaction() {
		Customer c = getBackend().getUserInfo(currentUserID);
		String prefix = "Order failed! ";
		if(c.getUsername().trim().equals("")){
			showPopup(prefix + "You have not entered your full name!");
			return;
		}
		else if(c.getPhone().trim().equals("")){
			showPopup(prefix + "You have not entered your phone number!");
			return;
		}
		int success = getBackend().processOrder(currentUserID, getCurrentCustomerDetails().getCart());
		
		String wrongMsg = "Sorry, your order could not be placed! ";
		if( success == 1 ){
			showPopup(wrongMsg +"Product quantity is larger than stock.");
		}else if (success == 2){
			showPopup(wrongMsg + "There are no items in the cart.");
		}
		else {
			showPopup("Your order has been placed successfully! Have a nice day!");
			getCurrentCustomerDetails().getCart().clear();
			this.showCartView();
		}
	}
	
	/***
	 * Store all customers' cart data into file
	 */
	public void storeCartData(){
		String cartData = "";
		for(Entry<String, Customer> entry : getBackend().getCustomerList().entrySet()) {
		        // Retrieve each customer
		        Customer customer = entry.getValue();
		        cartData += entry.getKey();// userId
		        // Get customer's cart 
		        Cart cart = customer.getCart();
		        // Get itemList in cart
		        for (CartItem ct: cart.getList()){
		        	cartData += FileHandler.SPLIT_COMMA + ct.toString();
		        }
		        cartData += "\n";
		    }
		FileHandler.writeToFile(cartData, FileHandler.CART_FILE);
	}
	
	/***
	 * Add game into productList
	 * @param game
	 */
	public void addProduct(Product p){
		this.backend.getProductList().add(p);
	}
	
	/***
	 * Show Admin generating report view
	 */
	public void showAdminReportView(){
		setView(new AdminReportView());
	}
	
	/***
	 * Generate report
	 * @param productName product Name
	 * @param start Start time
	 * @param end End time
	 * @return List<AdminReport> 
	 */
	public List<AdminReport> generateReport(String productName, String start, String end){
		return this.backend.generateReport(productName, start, end);
	}
	
	//save all product data when admin log out
		public void storeProductInfoByAdmin (){
			String fruitData = "";
			String vegetableData = "";
			String stapleData = "";
			String dairyData = "";
			for(Product product : this.backend.getProductList()){
				switch(product.getType()){
				case FRUITS:
					Fruits g = (Fruits) product;
					fruitData += g.toString() + "\r\n";break;
				case VEGETABLES:
					Vegetables m = (Vegetables) product;
					vegetableData += m.toString() + "\r\n";break;
				case STAPLES:
					Staples t = (Staples) product;
					stapleData += t.toString() + "\r\n";break;
				case DAIRY:
					Dairy mu = (Dairy) product;
					dairyData += mu.toString() + "\r\n";break;
				default:break;
				}
			};
			FileHandler.writeToFile(fruitData, FileHandler.FRUITS_FILE);
			FileHandler.writeToFile(vegetableData, FileHandler.VEGETABLES_FILE);
			FileHandler.writeToFile(stapleData, FileHandler.STAPLES_FILE);
			FileHandler.writeToFile(dairyData, FileHandler.DAIRY_FILE);
		}
}
