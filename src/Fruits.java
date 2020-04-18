


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * This class represents a Fruit product entity,
 */
public class Fruits extends Product{

	private String size ; 
    private String origin;
            
    public Fruits(ProductType type, String name,String platform, double price,String publisher,int quantity)
    {
        super(name,price,quantity,type);
        this.size = platform;
//        this.issueYear  = issueYear;
        this.origin = publisher;
//        this.quantity = quantity;
      //Add and show extra property (by Richard)
  		this.setProperty("size", "Size", platform);
  		this.setProperty("origin", "Origin", publisher);
    }


    public String getFruitType()
    {
        return size;
    }

    public String getOrigin()
    {
        return origin;
    }

    public void setFruitType(String platform)
    {
        this.size = platform;
    }

    public void setOrigin(String publisher)
    {
        this.origin = publisher;
    }

    @Override
	public String toString() {
		return this.getName() + FileHandler.SPLIT_COMMA + 
				this.size +FileHandler.SPLIT_COMMA + 
				this.getPrice()	+ FileHandler.SPLIT_COMMA + 
				this.origin + FileHandler.SPLIT_COMMA + 
				this.getQuantity();
	}
    
}
