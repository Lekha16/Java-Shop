

import java.util.HashMap;

import javax.swing.ImageIcon;
 
/**
 * <pre>
 * This class represents a product in the store.
 * Each product must have a name and may contain an image.
 * 
 * Products may also contain properties that are set through the "setProperty(String, String, Object)" method.
 * Properties can be used to represent various bits of data about the product, some properties could be:
 * - Unit price
 * - Sale price
 * - Warranty information
 * - Sale "% off"
 *</pre>
 */
public class Product {

	private HashMap<String, Object> props = new HashMap<String, Object>();
	private HashMap<String, String> dispNames = new HashMap<String, String>();
	
	private String name;
	
	private ImageIcon image = null;
	// add attributes
	private double price;
	private int quantity;
	private ProductType type;

	
	/**
	 * <pre>
	 * Product constructor.
	 * </pre>
	 * @param name The name of the product
	 */
	public Product(String name,double price, int quantity, ProductType type){
		setName(name);
		setPrice(price);
		setType(type);
		setQuantity(quantity);
		this.setProperty("price", "Price (AED)", price);
	}
	
	public Product(String name,double price, int quantity){
		setName(name);
		setPrice(price);
		setQuantity(quantity);
		this.setProperty("price", "Price (AED)", price);
	}
	
	/**
	 * <pre>
	 * Sets the image to be used for the product.
	 * If this method is never called, a default "no image" icon is used.
	 * </pre>
	 * @param url The URL of the image, it will be loaded from the internet and resized.
	 */
	public void setImage(String url){
		this.image = ShopController.generateIcon(url, 150, 150);
	}
	
	/**
	 * <pre>
	 * Sets the name of this product.
	 * </pre>
	 * @param name The new product name
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * <pre>
	 * Returns the name of this product.
	 * </pre>
	 * @return The name of this product
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * <pre>
	 * Sets a property of this product.
	 * 
	 * A property consists of an ID, a display name  and a value.
	 * The ID is the name used to refer to the property via the "getPropertyValue(id)" method.
	 * 
	 * The display name and value is shown to the user in the form of "{display name}: {value}" on the product details page.
	 * Each property is displayed to the user on it's own line and formatting is taken care of.
	 * 
	 * EG:
	 *      This:          setProperty("unitPrice", "Unit price ($) ", 100f)
	 *      Results in:   "Unit price ($): 100.0"
	 * 
	 * IMPORTANT: IF PROPERTIES ARE BEING USED TO CALCULATE PRICE, THEY MUST BE FLOATS AS THE "calculatePrice" METHOD IN "Model" EXPECTS FLOATS!
	 * </pre>
	 * @param id
	 * @param displayName
	 * @param value
	 */
	public void setProperty(String id, String displayName, Object value){
		this.props.put(id, value);
		this.dispNames.put(id, displayName);
	}
	
	/**
	 * <pre>
	 * Returns the value of the specified property.
	 * IMPORTANT: MAKE SURE THAT YOU CAST THE VALUE TO THE TYPE YOU NEED!
	 * </pre>
	 * @param id The property to get the value of
	 * @return The value of the property.
	 */
	public Object getPropertyValue(String id){
		return props.get(id);
	}
	
	/**
	 * <pre>
	 * Returns the display name of the specified property.
	 * </pre>
	 * @param id The property
	 * @return The display name of the property
	 */
	public String getPropertyDisplayName(String id){
		return dispNames.get(id);
	}
	
	/**
	 * <pre>
	 * This method is used internally to generate the text seen in the "ProductDetails" dialog.
	 * </pre>
	 * @return A formatted string representation of all properties this product has.
	 */
	public String getPropertiesAsText(){
		String out = "<html>";
		for(String key : this.props.keySet()) out += (getPropertyDisplayName(key) + ": " + getPropertyValue(key).toString()) + "<br/>";
		out += "</html>";
		return out;
	}
	
	/**
	 * <pre>
	 * Checks if this products contains a specific property.
	 * </pre>
	 * @param name The property ID
	 * @return Returns true if this product contains the property, false otherwise.
	 */
	public boolean hasProperty(String id){
		return props.containsKey(id);
	}
	
	/**
	 * <pre>
	 * Returns the image associated with this product.
	 * </pre>
	 * @return The image associated with this product
	 */
	public ImageIcon getImage(){
		switch (this.type) {
		case FRUITS:
			return ShopController.FRUITS_ICON;
		case VEGETABLES:
			return ShopController.VEGETABLES_ICON;
		case STAPLES:
			return ShopController.STAPLES_ICON;
		case DAIRY:
			return ShopController.DAIRY_ICON;
		}
		if(this.image == null) return ShopController.LOGO_ICON;
		else return this.image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

}
