import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

/**
 *This class is used as a view for admin to create a TV series product into system.
 */
public class AdminStaplesDetails extends AdminProductDetails {

	
	private JLabel directorsLabel;
	private JLabel episodesLabel;

	private JTextField genreTxtField;
	private JTextField directorTxtField;
	private JSpinner episodesSpinner;
	private JTextField starsTxtField;

	public AdminStaplesDetails(ShopController c, DefaultTableModel tm) {
		super(c, tm);
		setTitle("Create a Staple");
		// Create music labels and text fields
		directorsLabel = new JLabel("Size (GMS/KG):");
		episodesLabel = new JLabel("Exipry Time (Months):");
		directorTxtField = new JTextField();
		episodesSpinner = new JSpinner();
		episodesSpinner.setModel(new SpinnerNumberModel(1, 1, 10000, 1));

		// Add music attributes into content panel
		contentPanel.add(directorsLabel);
		contentPanel.add(directorTxtField);
		contentPanel.add(episodesLabel);
		contentPanel.add(episodesSpinner);
		contentPanel.setLayout(new GridLayout(8, 2));

		this.getContentPane().add(contentPanel, BorderLayout.NORTH);
	}

	@Override
	public Product toProduct() {
		Product p = super.toProduct();
		String director = this.getDirector().getText();
		int episodes = (int) this.getEpisodes().getValue();
		// ProductType type, String name, float price, int year, String genre,
		// String director, int quantity, int episodes, String stars
		return new Staples(ProductType.STAPLES, p.getName(), (float) p.getPrice(),
			director, p.getQuantity(), episodes);
	}

	public JTextField getGenre() {
		return genreTxtField;
	}

	public JTextField getDirector() {
		return directorTxtField;
	}

	public JSpinner getEpisodes() {
		return episodesSpinner;
	}

	public JTextField getStars() {
		return starsTxtField;
	}

	@Override
	public boolean validateInput() {
		if (super.validateInput()) {
			String directorTxtField = getDirector().getText();
			int episodesTxtField = (int) getEpisodes().getValue();
			// not null
			if (Utility.isEmpty(directorTxtField)) {
				return false;
			}
			// episodes must be digit
			 if (Utility.isEmpty(episodesTxtField)) {
				 return false;
			 }
			return true;
		}
		return false;
	}
}
