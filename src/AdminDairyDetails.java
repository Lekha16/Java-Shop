import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *This class is used as a view for admin to create a movie product into system.
 */
public class AdminDairyDetails extends AdminProductDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		// Movie
		private JLabel genreLabel;
		
		private JTextField genreTxtField;


		public AdminDairyDetails(ShopController c,DefaultTableModel tm) {
			super(c,tm);
			setTitle("Create a new Dairy Product");
			// Create music labels and text fields
			genreLabel = new JLabel("Size (KG/GMS/ML/Piece):");
			genreTxtField = new JTextField();



			// Add music attributes into content panel
			contentPanel.add(genreLabel);
			contentPanel.add(genreTxtField);
			contentPanel.setLayout(new GridLayout(7, 2));
			
			this.getContentPane().add(contentPanel, BorderLayout.NORTH);
		}
		
		@Override
		public Product toProduct() {
			Product p = super.toProduct();
			String genre = this.getGenre().getText();
			return new Dairy(ProductType.DAIRY, p.getName(),p.getPrice(), genre, p.getQuantity());
		}

		public JTextField getGenre() {
			return genreTxtField;
		}

		
		@Override
		public boolean validateInput(){
			if(super.validateInput()){
				String genreTxtField = getGenre().getText();
				// not null
				if(Utility.isEmpty(genreTxtField)){
					return false;
				}
//				// numofFilms must be digit
//				if (!Utility.isInteger(numOfFilms)){
//					return false;
//				}
				return true;
			}
			return false;
		}

}
