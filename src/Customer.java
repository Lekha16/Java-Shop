/*
 * Customer object
 */



/**
 * <pre>
 * This class represents a customer, it holds all of the data that the store needs to know about someone.
 * Whilst this information is not sufficient to place an order in real life, it is detailed enough to serve as a good learning example.
 * </pre>
 * Customer is the main user of this shopping system. Customer is able to view all the products, save products into their own cart and place orders.
 */
public class Customer extends Person {

	
	private Cart cart;
	private String phone;
	// Customer account constructor
	public Customer(String name, String phone){
		super(name);
		this.phone = phone;
		if(this.cart == null){
			this.cart = new Cart();	
		}
	}
	
	// Customer constructor
	public Customer(int id, String username, String password,
			String phone) {
		super(id, username, password);
		if(this.cart == null){
			this.cart = new Cart();	
		}
		this.phone = phone;
	}

	public Cart getCart() {
		return cart;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
