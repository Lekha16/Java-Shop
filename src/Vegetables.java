


/**
 * This class represents a music product entity,
 */
public class Vegetables extends Product{

    private String size;
    private String origin;

    public Vegetables(String name, double price, int quantity, ProductType type){
        super(name, price, quantity, type);
    }

    public Vegetables(ProductType type, String name, double price, String genre, String singer, int quantity){
        super(name, price, quantity, type);
        this.size = singer;
        this.origin = genre;
      //Add and show extra property (by Richard)
		this.setProperty("origin", "Origin", genre);
		this.setProperty("size", "Size", singer);
    }

    public String getSinger() {
        return size;
    }

    public void setSinger(String singer) {
        this.size = singer;
    }

    public String getGenre() {
        return origin;
    }

    public void setGenre(String genre) {
        this.origin = genre;
    }


    @Override
    public String toString(){
        //-name,singer,genre,year,quantity,numOfSongs,price
        return this.getName() + FileHandler.SPLIT_COMMA +
                this.getSinger() +FileHandler.SPLIT_COMMA +
                this.getGenre() + FileHandler.SPLIT_COMMA +
     
                this.getQuantity()	+ FileHandler.SPLIT_COMMA +
              
                this.getPrice();
    }

}
