

/**
 * This class represents a movie product entity,
 */
public class Dairy extends Product{

//	private int year;
	private String size;
	
	public Dairy(String name, float price, int quantity, ProductType type) {
		super(name, price, quantity, type);
		// TODO Auto-generated constructor stub
	}
	
	public Dairy(ProductType type, String name, double price, String genre, int quantity){
		super(name, price, quantity, type);
		this.size = genre;
		//Add and show extra property (by Richard)
				this.setProperty("size", "Size", genre);
	}


	public String getGenre() {
		return size;
	}

	public void setGenre(String genre) {
		this.size = genre;
	}


	 @Override
		public String toString() {
		// File format: name,year,genre,price,director,quantity,numberOfFilms
			return this.getName() + FileHandler.SPLIT_COMMA +  
					this.size +FileHandler.SPLIT_COMMA + 
					this.getPrice()	+ FileHandler.SPLIT_COMMA +  
					this.getQuantity() ;
		}

}
