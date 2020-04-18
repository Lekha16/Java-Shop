import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *This class is used as a view for admin to create a music product into system.
 */
public class AdminVegetablesDetails extends AdminProductDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// music
	private JLabel singerLabel;
	private JLabel genreLabel;
	private JTextField singer;
	private JTextField genre;

	public AdminVegetablesDetails(ShopController c,DefaultTableModel tm) {
		super(c,tm);
		setTitle("Create a new Vegetable");
		// Create music labels and text fields
		singerLabel = new JLabel("Size (KG/GMS/Piece)");
		genreLabel = new JLabel("Origin");
		singer = new JTextField();
		genre = new JTextField();


		// Add music attributes into content panel
		contentPanel.add(singerLabel);
		contentPanel.add(singer);
		contentPanel.add(genreLabel);
		contentPanel.add(genre);

		contentPanel.setLayout(new GridLayout(7, 2));
		
		contentPanel.setLayout(new GridLayout(7, 2));
		this.getContentPane().add(contentPanel, BorderLayout.NORTH);
	}
	
	@Override
	public Product toProduct() {
		Product p = super.toProduct();
		String singer = this.getSinger().getText();
		String genre = this.getGenre().getText();
		// ProductType type, String name, float price, int year, String genre, String singer, int quantity, int numOfSongs
		return new Vegetables(ProductType.VEGETABLES, p.getName(),p.getPrice(), genre, singer, p.getQuantity());
	}

	public JTextField getSinger() {
		return singer;
	}

	public JTextField getGenre() {
		return genre;
	}
	
	@Override
	public boolean validateInput(){
		if(super.validateInput()){
			String genreTxtField = getGenre().getText();
			String singerTxtField = getSinger().getText();
			// not null
			if(Utility.isEmpty(genreTxtField) || Utility.isEmpty(singerTxtField)){
				return false;
			}
			// numofFilms must be digit
//			if (!Utility.isInteger(numOfSongs)){
//				return false;
//			}
			return true;
		}
		return false;
	}
}
