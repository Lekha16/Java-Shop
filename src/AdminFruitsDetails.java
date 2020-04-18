import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *This class is used as a view for admin to create a game product into system.
 */
public class AdminFruitsDetails extends AdminProductDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ////////////
	// game
	private JLabel platformLabel;
	private JLabel publisherLabel;
	private JTextField platform;
	private JTextField publisher;

	public AdminFruitsDetails(ShopController c, DefaultTableModel tm) {
		super(c,tm);
		setTitle("Create a new Fruit");
		// Create game labels and text fields
		platformLabel = new JLabel("Size (KG/GMS/Piece)");
		publisherLabel = new JLabel("Origin");
		platform = new JTextField();
		publisher = new JTextField();

		// Add game attributes into content panel
		contentPanel.add(platformLabel);
		contentPanel.add(platform);
		contentPanel.add(publisherLabel);
		contentPanel.add(publisher);

		contentPanel.setLayout(new GridLayout(6, 2));
		this.getContentPane().add(contentPanel, BorderLayout.NORTH);
	}

	public JTextField getPlatform() {
		return platform;
	}

	public JTextField getPublisher() {
		return publisher;
	}

	@Override
	public Product toProduct() {
		Product p = super.toProduct();
		String publisher = this.getPublisher().getText();
		String platform = this.getPlatform().getText();
		return new Fruits(ProductType.FRUITS, p.getName(), platform, p.getPrice(), publisher, p.getQuantity());
	}
	
	@Override
	public boolean validateInput(){
		if(super.validateInput()){
			String platform = getPlatform().getText();
			String publisher = getPublisher().getText();
			// not null
			if(Utility.isEmpty(platform) || Utility.isEmpty(publisher)){
				return false;
			}
			return true;
		}
		return false;
	}
}
