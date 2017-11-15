package bcu.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import bcu.model.Admin;

public class AddAdminWindow extends JFrame implements ActionListener { // Class
																		// used
																		// to
																		// create
																		// a
																		// window
																		// to
																		// add
																		// books

	private static final long serialVersionUID = 1L;

	private MainWindow mw;
	private JTextField username = new JTextField(); // variable for a text field
													// that will be used to
													// enter username of the
													// admin
	private JTextField password = new JTextField(); // variable for a text field
													// that will be used to
													// enter password of the
													// admin

	private JButton addBtn = new JButton("Add"); // variable for a button that
													// will be used to trigger
													// the
													// event add
	private JButton cancelBtn = new JButton("Cancel"); // variable for a button
														// that will be used to
														// trigger the event
														// cancel

	public AddAdminWindow(MainWindow mw) { // Constructor for the class
											// AddAdminWindow
		this.mw = mw;
		initialize();
	}

	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {

		}

		setTitle("Add a New Admin"); // Title of the Frame
		setSize(300, 200); // Size of the frame
		JPanel topPanel = new JPanel(); // creates top panel
		topPanel.setLayout(new GridLayout(5, 2)); // Used to set the layout of
													// the top panel 5 by 2
		topPanel.add(new JLabel("Username : ")); // adds label for username in
													// the top
													// panel
		topPanel.add(username); // adds username text box in the top panel
		topPanel.add(new JLabel("Password : ")); // adds label for password in
													// the top
													// panel
		topPanel.add(password); // adds password text box in the top panel

		JPanel bottomPanel = new JPanel(); // Creates bottom panel
		bottomPanel.setLayout(new GridLayout(1, 3)); // Used to set the layout
														// of
														// the bottom panel 1 by
														// 3
		bottomPanel.add(new JLabel(" "));
		bottomPanel.add(addBtn); // adds the add button in the bottom panel
		bottomPanel.add(cancelBtn); // adds the cancel button in the bottom
									// panel
		addBtn.addActionListener(this); // adds actionlistener for add button
		cancelBtn.addActionListener(this); // adds actionlistener for cancel

		// this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(topPanel, BorderLayout.CENTER);/// puts the
																	/// top
																	/// panel at
																	/// the
																	/// center
																	/// of the
																	/// frame
		this.getContentPane().add(bottomPanel, BorderLayout.SOUTH); // puts the
																	// bottom
																	// panel at
																	// the
																	// bottom of
																	// the frame

		setVisible(true); // sets the content of the frame visible

	}

	@Override
	public void actionPerformed(ActionEvent event) { // Action perfomed method
		if (event.getSource() == addBtn) { /// if the add button is clicked

			Admin admin = new Admin(); // Stores reference of the Book Class
			admin.setUsername(username.getText()); // the input of the username
													// textbox will be set as
													// username
			admin.setPassword(password.getText()); // the input of the password
													// textbox will be set as
													// password
			JOptionPane.showMessageDialog(null, "Welcome to the library system " + username.getText(), "Welcome",
					JOptionPane.PLAIN_MESSAGE); //Message will be displayed to welcome the admin
			this.setVisible(false);//will close the window

		} else if (event.getSource() == cancelBtn) { // if cancel button is clicked
			this.setVisible(false); //then window will be closed
		}

	}

}
