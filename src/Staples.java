

/**
 * This class represents a TV series product entity,
 */
public class Staples extends Product{

	private String size;
	private int expiryMonths;
	
	public Staples(String name, float price, int quantity, ProductType type) {
		super(name, price, quantity, type);
	}
	
	public Staples(ProductType type, String name, float price, String director, int quantity, int episodes){
		super(name, price, quantity, type);
		this.size = director;
		this.expiryMonths = episodes;
		//Add and show extra property (by Richard)
				this.setProperty("size", "Size", director);
				this.setProperty("expiryMonths", "Expiry Time (Months)", episodes);
	}


	public String getDirector() {
		return size;
	}

	public void setDirector(String director) {
		this.size = director;
	}

	public int getEpisodes() {
		return expiryMonths;
	}

	public void setEpisodes(int episodes) {
		this.expiryMonths = episodes;
	}
	
	@Override
	public String toString(){
		//-Title,year,genre,price,director,quantity,episodes,star
		return this.getName() + FileHandler.SPLIT_COMMA +  
				this.getPrice()	+ FileHandler.SPLIT_COMMA + 
				this.size + FileHandler.SPLIT_COMMA + 
				this.getQuantity() + FileHandler.SPLIT_COMMA +
				this.expiryMonths;
	}
	
}
