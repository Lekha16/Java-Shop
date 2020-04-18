


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * This class is the view shows customer's personal information.
 */
public class UserDetails extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField fullName;
	private JTextField phoneNumber;
	private JTextField homeAddr;
	private JTextField cardNum;
	private JTextField doB;

	public static void display(ShopController c){
		UserDetails dialog = new UserDetails(c);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(c.getWindow());
		dialog.setVisible(true);
	}
	
	public Customer toCustomer(Customer customer){
		return customer;
//		return new Customer(fullName.getText(), homeAddr.getText(), cardNum.getText(), phoneNumber.getText());
	}
	
	public UserDetails(final ShopController c) {
		
		Customer user = c.getCurrentCustomerDetails();
		
		setTitle("User Information");
		setBounds(100, 100, 450, 300);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblFullName = new JLabel("Full name:");
			lblFullName.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblFullName = new GridBagConstraints();
			gbc_lblFullName.insets = new Insets(0, 0, 5, 5);
			gbc_lblFullName.gridx = 1;
			gbc_lblFullName.gridy = 1;
			contentPanel.add(lblFullName, gbc_lblFullName);
		}
		{
			fullName = new JTextField();
			GridBagConstraints gbc_fullName = new GridBagConstraints();
			gbc_fullName.insets = new Insets(0, 0, 5, 5);
			gbc_fullName.fill = GridBagConstraints.HORIZONTAL;
			gbc_fullName.gridx = 2;
			gbc_fullName.gridy = 1;
			contentPanel.add(fullName, gbc_fullName);
			fullName.setColumns(10);
			fullName.setText(user.getUsername());
		}
		{
			JLabel lblPhoneNumber = new JLabel("Phone number:");
			GridBagConstraints gbc_lblPhoneNumber = new GridBagConstraints();
			gbc_lblPhoneNumber.anchor = GridBagConstraints.EAST;
			gbc_lblPhoneNumber.insets = new Insets(0, 0, 5, 5);
			gbc_lblPhoneNumber.gridx = 1;
			gbc_lblPhoneNumber.gridy = 2;
			contentPanel.add(lblPhoneNumber, gbc_lblPhoneNumber);
		}
		{
			phoneNumber = new JTextField();
			GridBagConstraints gbc_phoneNumber = new GridBagConstraints();
			gbc_phoneNumber.insets = new Insets(0, 0, 5, 5);
			gbc_phoneNumber.fill = GridBagConstraints.HORIZONTAL;
			gbc_phoneNumber.gridx = 2;
			gbc_phoneNumber.gridy = 2;
			contentPanel.add(phoneNumber, gbc_phoneNumber);
			phoneNumber.setColumns(10);
			phoneNumber.setText(user.getPhone());
		}
		{
			JLabel lblHomeAddress = new JLabel("Home address:");
			lblHomeAddress.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblHomeAddress = new GridBagConstraints();
			gbc_lblHomeAddress.anchor = GridBagConstraints.EAST;
			gbc_lblHomeAddress.insets = new Insets(0, 0, 5, 5);
			gbc_lblHomeAddress.gridx = 1;
			gbc_lblHomeAddress.gridy = 3;
			contentPanel.add(lblHomeAddress, gbc_lblHomeAddress);
		}
		{
			homeAddr = new JTextField();
			GridBagConstraints gbc_homeAddr = new GridBagConstraints();
			gbc_homeAddr.insets = new Insets(0, 0, 5, 5);
			gbc_homeAddr.fill = GridBagConstraints.HORIZONTAL;
			gbc_homeAddr.gridx = 2;
			gbc_homeAddr.gridy = 3;
			contentPanel.add(homeAddr, gbc_homeAddr);
			homeAddr.setColumns(10);
		
		}

		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				final JDialog me = this;
				JButton okButton = new JButton("Save");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						c.updateUserDetails(toCustomer(c.getCurrentCustomerDetails()));
						me.dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				final JDialog me = this;
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						me.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
