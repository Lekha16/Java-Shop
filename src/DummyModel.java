import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * This class contains some basic funtions of shopping system
 */
public class DummyModel implements Model {
	// product name must not the same!!!
	ArrayList<Product> productList = new ArrayList<Product>();
	HashMap<String, String> passwords = new HashMap<>();
	HashMap<String, Customer> customerList = new HashMap<>();
	HashMap<String, Admin> adminList = new HashMap<>();

	List<Order> orderList = new ArrayList<Order>();

	public DummyModel() {
		loadData();
	}

	private void loadData() {
		// load product data
		loadProductData();
		// load customer data
		loadCusteomerData();
		// load admin data
		loadAdminData();
		// load orer data
		orderList = loadOrderList();
	}

	/***
	 * Load admin data
	 */
	private void loadAdminData() {
		// Admin detail text
		// id,name,username,password,securityQuestion,securityAnswer,DOB
		String adminData = FileHandler.readFromFile(FileHandler.ADMIN_DATA);
		String[] adminArray = adminData.split(FileHandler.SPLIT_CEMI);
		for (int i = 0; i < adminArray.length; i++) {
			String[] customers = adminArray[i].split(FileHandler.SPLIT_COMMA);
			String idStr = customers[0].trim();
			int id = Integer.parseInt(idStr.trim());
			//String name = customers[1];
			String username = customers[1];
			String password = customers[2];
			//String securityQuestion = customers[4];
			//String securityAnswer = customers[5];
			//String dob = customers[6];
			// String phone = customers[7];
			// String address = customers[8];
			// String cardNumber = customers[9];
			Admin c = new Admin(id, username, password);
			// c.setPhone(phone);
			// c.setAddress(address);
			// c.setCardNumber(cardNumber);
			adminList.put(username, c);
			passwords.put(username, password);
		}
	}

	private void loadFruitsData() {
		String gameData = FileHandler.readFromFile(FileHandler.FRUITS_FILE);
		String[] gameArray = gameData.split(FileHandler.SPLIT_CEMI);
		for (int i = 0; i < gameArray.length; i++) {
			String[] game = gameArray[i].split(FileHandler.SPLIT_COMMA);
			String name = game[0];
			String platform = game[1];
			String price = game[2];
			String publisher = game[3];
			String quantity = game[4];
			Fruits c = new Fruits(ProductType.FRUITS, name, platform,
					Float.parseFloat(price), publisher,
					Integer.parseInt(quantity));
			productList.add(c);
		}
	}

	/***
	 * Load movie data from file File format:
	 * name,year,genre,price,director,quantity,numberOfFilms
	 */
	private void loadDairyData() {
		String movieData = FileHandler.readFromFile(FileHandler.DAIRY_FILE);
		String[] movieArray = movieData.split(FileHandler.SPLIT_CEMI);
		for (int i = 0; i < movieArray.length; i++) {
			String[] movie = movieArray[i].split(FileHandler.SPLIT_COMMA);
			String name = movie[0];
			String genre = movie[1];
			double price = Double.parseDouble(movie[2]);
			int quantity = Integer.parseInt(movie[3]);
			Dairy c = new Dairy(ProductType.DAIRY, name, price, genre, quantity);
			productList.add(c);
		}
	}

	/***
	 * load TV data
	 */
	private void loadStapleData() {
		String TVData = FileHandler.readFromFile(FileHandler.STAPLES_FILE);
		String[] TVArray = TVData.split(FileHandler.SPLIT_CEMI);
		for (int i = 0; i < TVArray.length; i++) {
			String[] tv = TVArray[i].split(FileHandler.SPLIT_COMMA);
			String name = tv[0];
			float price = Float.parseFloat(tv[1]);
			String director = tv[2];
			int quantity = Integer.parseInt(tv[3]);
			int episodes = Integer.parseInt(tv[4]);
			// ProductType type, String name, float price, int year, String
			// genre, String director, int quantity, int episodes, String star
			Staples tvObj = new Staples(ProductType.STAPLES, name, price,
					director, quantity, episodes);
			productList.add(tvObj);
		}
	}

	/***
	 * load product data
	 */
	private void loadProductData() {
		loadFruitsData();
	    loadDairyData();
		loadStapleData();
		loadVegetablesData();
	}

	/***
	 * Load customer data
	 */
	private void loadCusteomerData() {
		loadCustomerDetail();
		loadCustomerCart();
	}

	/***
	 * Load customer personal information
	 */
	private void loadCustomerDetail() {
		String customerData = FileHandler
				.readFromFile(FileHandler.CUSTOMER_DATA);
		String[] customerArray = customerData.split(FileHandler.SPLIT_CEMI);
		for (int i = 0; i < customerArray.length; i++) {
			String[] customers = customerArray[i]
					.split(FileHandler.SPLIT_COMMA);
			String idStr = customers[0].trim();
			int id = Integer.parseInt(idStr.trim());
			//String name = customers[1];
			String username = customers[1];
			String password = customers[2];
			String phone = customers[3];
			Customer c = new Customer(id, username, password, phone);
			//c.setPhone(phone);
			customerList.put(username, c);
			passwords.put(username, password);
		}
	}

	/***
	 * load Music data
	 */
	private void loadVegetablesData() {
		String musicData = FileHandler.readFromFile(FileHandler.VEGETABLES_FILE);
		String[] musicArray = musicData.split(FileHandler.SPLIT_CEMI);
		for (int i = 0; i < musicArray.length; i++) {
			String[] music = musicArray[i].split(FileHandler.SPLIT_COMMA);
			String name = music[0];
			String singer = music[1];
			String genre = music[2];
			int quantity = Integer.parseInt(music[3]);
			float price = Float.parseFloat(music[4]);
			// -name,singer,genre,year,quantity,numOfSongs,price
			Vegetables musicObj = new Vegetables(ProductType.VEGETABLES, name, price,
					genre, singer, quantity);
			productList.add(musicObj);
		}
	}

	/***
	 * Load customer cart information
	 */
	private void loadCustomerCart() {
		String cartData = FileHandler.readFromFile(FileHandler.CART_FILE);
		String[] cartArray = cartData.split(FileHandler.SPLIT_CEMI);
		for (int i = 0; i < cartArray.length; i++) {
			String[] cartOfCustomer = cartArray[i]
					.split(FileHandler.SPLIT_COMMA);
			String userId = cartOfCustomer[0];
			Cart cart = customerList.get(userId).getCart();
			for (int j = 1; j < cartOfCustomer.length; j = j + 2) {
				Product p = getProductByName(cartOfCustomer[j]);
				int productQuantity = Integer.parseInt(cartOfCustomer[j + 1]);
				CartItem ct = new CartItem(p, productQuantity);
				cart.add(ct);
			}
		}
	}

	/***
	 * Return a product by its name
	 * 
	 * @param name
	 *            product name
	 * @return product
	 */
	private Product getProductByName(String name) {
		for (Product p : productList) {
			if (p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}

	@Override
	public List<Product> getProducts() {
		return productList;
	}

	@Override
	public boolean login(String username, String password, String loginIdentity) {
		if (loginIdentity.equals("CUSTOMER")) {
			Customer loginUser = customerList.get(username);
			if (loginUser != null && password.equals(loginUser.getPassword())) {
				return true;
			}
		} else {
			// /TODO
			// add admin login validate
			Admin loginAdmin = adminList.get(username);
			if (loginAdmin != null && password.equals(loginAdmin.getPassword())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean signup(String username, String phoneNum, String password) {
		if (passwords.containsKey(username)) {
			return false;
		}
		passwords.put(username, password);
		int id = customerList.size() + 1;

		// Add order into orderList
		Customer c = new Customer(username, phoneNum);
		Cart cart = new Cart();
		c.setId(id);
		c.setUsername(username);
		c.setPhone(phoneNum);
		c.setPassword(password);
		c.setCart(cart);
		customerList.put(username, c);

		// orderId,currentUserID,time,productName,productQuantity,productName,productQuantity
		String customerData = id + FileHandler.SPLIT_COMMA + username
				+ FileHandler.SPLIT_COMMA + password + FileHandler.SPLIT_COMMA + phoneNum + FileHandler.SPLIT_COMMA;
		customerData += "\n";
		FileHandler.appendToFile(customerData, FileHandler.CUSTOMER_DATA);
		
		return true;
	}

	@Override
	public Customer getUserInfo(String username) {
		return customerList.get(username);
	}

	@Override
	public boolean setUserInfo(String username, Customer info) {
		customerList.put(username, info);
		return true;
	}

	@Override
	public double getPrice(Cart cart) {
		double total = 0;
		for (CartItem item : cart.getList()) {
			if (item.product.hasProperty("price")) {
				total += ((double) item.product.getPropertyValue("price"))
						* item.quantity;
			}
		}
		return total;
	}

	@Override
	public int processOrder(String currentUserID, Cart cart) {
		// Check if the quantity is enough
		if(cart.getList().size() == 0)
		{
			return 2;
		}
		for (CartItem ct : cart.getList()) {
			// if product quantity is not enough, not processing
			int productQuantity = ct.product.getQuantity();
			if (productQuantity < ct.quantity) {
				return 1;
			}
			// Update the quantity of the product
			int currentProductQuantity = (int) (productQuantity - ct.quantity);
			ct.product.setQuantity(currentProductQuantity);
		}
		// save product
		saveProductData();
		// save order
		saveOrderData(currentUserID, cart);
		return 0;
	}

	/***
	 * Save order data into file
	 */
	public void saveOrderData(String currentUserID, Cart cart) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		// order file data format
		String dateStr = dateFormat.format(date);
		// Order id
		int orderid = orderList.size() + 1;

		// Add order into orderList
		Order order = new Order();
		order.setCustomerId(currentUserID);
		order.setOrderItem(cart.getList());
		order.setOrderedDate(dateStr);
		order.setOrderId(orderid);
		orderList.add(order);

		String orderData = orderid + FileHandler.SPLIT_COMMA + currentUserID
				+ FileHandler.SPLIT_COMMA + dateStr + FileHandler.SPLIT_COMMA;
		// orderId,currentUserID,time,productName,productQuantity,productName,productQuantity
		double totalPrice = 0.0;
		int numOrders = cart.getList().size();
		orderData += numOrders + FileHandler.SPLIT_COMMA;;
		for (CartItem ct : cart.getList()) {
			// different product and its quantity is separated by ","
			
			totalPrice += ct.product.getPrice();
			orderData += ct.toString() + FileHandler.SPLIT_COMMA;
		}
		double totalVAT = totalPrice +(totalPrice*0.05); 
		orderData += totalPrice + FileHandler.SPLIT_COMMA + totalVAT + FileHandler.SPLIT_COMMA + "Thank You"  + FileHandler.SPLIT_COMMA;;
		orderData += "\n";
		FileHandler.appendToFile(orderData, FileHandler.ORDER_FILE);
	}

	/*
	 * Save product data into file
	 */
	public void saveProductData() {
		// initialize different data which needs to be stored in file
		String gameData = "";
		String movieData = "";
		String TVData = "";
		String musicData = "";
		for (Product p : productList) {
			switch (p.getType()) {
			case FRUITS:
				gameData += p.toString() + "\n";
				break;
			case VEGETABLES:
				musicData += p.toString() + "\n";
				break;
			case DAIRY:
				movieData += p.toString() + "\n";
				break;
			case STAPLES:
				TVData += p.toString() + "\n";
			default:
				break;
			}
		}
		FileHandler.writeToFile(gameData, FileHandler.FRUITS_FILE);
		FileHandler.writeToFile(movieData, FileHandler.DAIRY_FILE);
		FileHandler.writeToFile(TVData, FileHandler.STAPLES_FILE);
		FileHandler.writeToFile(musicData, FileHandler.VEGETABLES_FILE);
	}

	@Override
	public HashMap<String, Customer> getCustomerList() {
		return customerList;
	}

	@Override
	public List<Product> getProductList() {
		return productList;
	}

	private List<Order> loadOrderList() {
		List<Order> orders = new ArrayList<Order>();
		String orderData = FileHandler.readFromFile(FileHandler.ORDER_FILE);
		String[] orderArray = orderData.split(FileHandler.SPLIT_CEMI);
		Order oo;
		for (int i = 0; i < orderArray.length; i++) {
			oo = new Order();
			String[] order = orderArray[i].split(FileHandler.SPLIT_COMMA);
			int orderId = Integer.parseInt(order[0]);
			String customerId = order[1];
			String orderedDate = order[2].substring(0, 10);
			// Set attributes into Order object
			oo.setOrderId(orderId);
			oo.setCustomerId(customerId);
			oo.setOrderedDate(orderedDate);
			// Assemble order's cartItem data
			int length = Integer.parseInt(order[3]);
			length = length*2 + 4;
			for (int j = 4; j < length; j = j + 2) {
				String pname = order[j];
				Product p = getProductByName(pname);
				int pquantity = Integer.parseInt(order[j + 1]);
				CartItem ci = new CartItem(p, pquantity);
				oo.getOrderItem().add(ci);
			}
			double totalPrice = Double.parseDouble(order[length]);
			double totalVatPrice =  Double.parseDouble(order[length+1]);
			oo.settotalPrice(totalPrice);
			oo.settotalVatPrice(totalVatPrice);
			orders.add(oo);
		}
		return orders;
	}

	@Override
	public List<AdminReport> generateReport(String productName, String start,
			String end) {
		List<AdminReport> reports = new ArrayList<AdminReport>();
		AdminReport ar = null;
		for (Order order : loadOrderList()) {
			// Find the periods
			String orderedDate = order.getOrderedDate();
			List<CartItem> cartItems = order.getOrderItem();
			for (CartItem ct : cartItems) {
				// / d{4}/d{2}/d{2}
				if (ct.product.getName().equals(productName)) {
					boolean isAdd = false;
					// no date input
					if (Utility.isEmpty(start) && Utility.isEmpty(end)) {
						isAdd = true;
						// end date is empty
					} else if (Utility.isEmpty(start) && !Utility.isEmpty(end)) {
						if(orderedDate.compareTo(end) <= 0){
							isAdd = true;
						}
						// start date is empty
					}else if (Utility.isEmpty(end) && !Utility.isEmpty(start)){
						if(orderedDate.compareTo(start) >= 0){
							isAdd = true;
						}
						// date is not empty
					}else if(orderedDate.compareTo(start) >= 0
							&& orderedDate.compareTo(end) <= 0){
						isAdd = true;
					}
					if (isAdd) {
						ar = new AdminReport();
						String customerId = order.getCustomerId();
						ar.setCustomerId(customerId);
						ar.setCustomerName(getUserInfo(customerId).getUsername());
						ar.setOrderId(order.getOrderId() + "");
						ar.setOrderedDate(orderedDate);
						ar.setItemName(ct.product.getName());
						ar.setQuantityOfItem(ct.quantity + "");
						reports.add(ar);
					}
				}

			}
		}
		return reports;
	}

}
