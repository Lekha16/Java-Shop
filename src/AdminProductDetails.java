import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *This class is used as a public view for each products to create their own customized view.
 */
public class AdminProductDetails extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static ProductType dialogType;

	protected final JPanel contentPanel;

	private JLabel nameLabel;
	private JLabel priceLabel;
	private JLabel quantityLabel;

	private JTextField productName;
	private JSpinner price;
	private JSpinner quantity;
	/***
	 * Settings for dialog
	 * 
	 * @param c
	 *            ShopController
	 * @param dialog
	 *            Product detail dialog
	 */
	public static void display(ShopController c, AdminProductDetails dialog) {
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(c.getWindow());
		dialog.setVisible(true);
	}
	
	/***
	 * Set dialog type
	 * @param type
	 */
	public static void setProductType(ProductType type){
		dialogType = type;
	}

	/***
	 * Display game detail dialog
	 * 
	 * @param c
	 *            ShopController
	 */
	public static void displayFruitsDetails(DefaultTableModel tm, ShopController c, ProductType type) {
		AdminFruitsDetails dialog = new AdminFruitsDetails(c,tm);
		setProductType(type);
		display(c, dialog);
	}

	/***
	 * Display music detail dialog
	 * 
	 * @param c
	 */
	public static void displayVegetablesDetails(DefaultTableModel dt, ShopController c, ProductType type) {
		AdminVegetablesDetails dialog = new AdminVegetablesDetails(c, dt);
		setProductType(type);
		display(c, dialog);
	}
	
	/***
	 * Display Staples detail dialog
	 * @param dt
	 * @param c
	 * @param type
	 */
	public static void displayStaplesDetails(DefaultTableModel dt, ShopController c, ProductType type) {
		AdminStaplesDetails dialog = new AdminStaplesDetails(c, dt);
		setProductType(type);
		display(c, dialog);
	}

	/***
	 * Display Dairy detail dialog
	 * @param dt
	 * @param c
	 * @param type
	 */
	public static void displayDairyDetails(DefaultTableModel dt, ShopController c, ProductType type) {
		AdminDairyDetails dialog = new AdminDairyDetails(c, dt);
		setProductType(type);
		display(c, dialog);
	}

	/***
	 * Assemble a product according to the information in this dialog
	 * 
	 * @return
	 */
	public Product toProduct() {
		String name = this.getProductName().getText();
		double price =  (double) this.getPrice().getValue();
		int quantity = (int) this.getQuantity().getValue();
		return new Product(name, price, quantity);
	}
	
	/***
	 * Validate all inputs
	 * @return
	 */
	public boolean validateInput(){
		// Text field validate
		if(Utility.isEmpty(this.getProductName().getText())){
			return false;
		}
		if(Utility.isEmpty(this.getPrice().getValue())){
			return false;
		}
		if(Utility.isEmpty(quantity)){
			return false;
		}
		return true;
	}

	public AdminProductDetails(final ShopController c, final DefaultTableModel dt) {

		setBounds(100, 100, 700, 500);
		contentPanel = new JPanel();
		dialogType = ProductType.FRUITS;
		this.getContentPane().setLayout(new BorderLayout());
		// create labels
		this.nameLabel = new JLabel("Name");
		this.priceLabel = new JLabel("Price");
		this.quantityLabel = new JLabel("Quantity");

		this.productName = new JTextField();
		this.price = new JSpinner();
		price.setModel(new SpinnerNumberModel(100.0, 0.0, 10000.0, 1.0));
		this.quantity = new JSpinner();
		quantity.setModel(new SpinnerNumberModel(1, 1, 10000, 1));
		
		this.contentPanel.add(nameLabel);
		this.contentPanel.add(productName);
		this.contentPanel.add(priceLabel);
		this.contentPanel.add(price);
		this.contentPanel.add(quantityLabel);
		this.contentPanel.add(quantity);

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				final JDialog me = this;
				JButton okButton = new JButton("Save");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// validate
						if (validateInput()){
							// Assemble dialog inputs into a product object
							Product p = toProduct();
							// Add this product into productList
							c.addProduct(p);
							// Get the table model in productListView, add a new row into this model
							dt.addRow(new Object[] { p.getType(), p.getName(), p.getPrice(), p.getQuantity() });
							// Close this dialog
							me.dispose();
						}else{
							c.showPopup("Wrong input! \nYour input can't be null.  ");
						}
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				final JDialog me = this;
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						me.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JTextField getProductName() {
		return productName;
	}

	public void setProductName(JTextField productName) {
		this.productName = productName;
	}

	public JSpinner getPrice() {
		return price;
	}

	public JSpinner getQuantity() {
		return quantity;
	}


}
