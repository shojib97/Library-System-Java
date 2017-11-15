package bcu.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import bcu.loader.UserLoader;
import bcu.model.User;

public class AddUserWindow extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MainWindow mw;
	private JTextField id = new JTextField();// variable for id textbox
	private JTextField name = new JTextField();// variable for name textbox
	private JTextField noBorrowedBooks = new JTextField(); // variable for
															// number of books
															// borrowed textbox
	private JTextField phoneNum = new JTextField(); // variable for phone number
													// textbox

	private JButton addBtn = new JButton("Add"); // variable for add button
	private JButton cancelBtn = new JButton("Cancel"); // variable for add
														// button
	private UserLoader userLoader = new UserLoader(); // variable for the class
														// userloader
	private ArrayList<User> userList = new ArrayList<User>(); // variable used
																// to store
																// books in a
																// array

	public AddUserWindow(MainWindow mw) { // constructor for the addUserWindow
		this.mw = mw;
		initialize();
	}

	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {

		}

		setTitle("Add a New User");// title of the window
		setSize(300, 200);// size of the window
		JPanel topPanel = new JPanel();// creates to panel
		topPanel.setLayout(new GridLayout(5, 2));// set layout 5 by 2
		topPanel.add(new JLabel("ID : ")); // adds ID label on top panel
		topPanel.add(id); // adds ID textbox on top panel
		topPanel.add(new JLabel("Name : ")); // adds Name label on top panel
		topPanel.add(name); // adds Name textbox on top panel
		topPanel.add(new JLabel("Number of Books Borrowed : ")); // adds Number
																	// of Books
																	// Borrowed
																	// label on
																	// top panel
		topPanel.add(noBorrowedBooks); // adds Number of Books Borrowed textbox
										// on top panel
		topPanel.add(new JLabel("Phone Number : ")); // adds Phone Number label
														// on top panel
		topPanel.add(phoneNum); // adds Phone Number textbox on top panel

		JPanel bottomPanel = new JPanel(); // Creates bottom panel
		bottomPanel.setLayout(new GridLayout(1, 3));// set layout 1 by 3
		bottomPanel.add(new JLabel(" "));
		bottomPanel.add(addBtn); // adds the add button in the bottom panel
		bottomPanel.add(cancelBtn); // adds the cancel button in the bottom
									// panel
		addBtn.addActionListener(this); // adds actionlistener for add button
		cancelBtn.addActionListener(this); // adds actionlistener for cancel
											// button

		// adds the panles in the frame
		this.getContentPane().add(topPanel, BorderLayout.CENTER);
		this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);

		setVisible(true);// set the frame visible

	}

	public boolean Idtester(String uid) { // method used to test the uniqueness
											// of the id
		boolean idunique = true;// sets the id unique as a true boolean
		boolean testidfine = false;
		userList = userLoader.loadUsers(); // loads members in to a an arraylist
											// called userList
		for (int i = 0; i < userList.size(); i++) { // loop through arraylist
			// starting from index 0 till it reaches the last index of the
			// arraylist
			User user = userList.get(i); // from the user class, it retrieves
			// the user with index i
			if (user.getId().equals(uid)) { // if id at index (i) is equals to
											// the id in the system
				idunique = false; // then, idunique is set as false.

			}

		}
		if (idunique == true) { // if idunique equals to true
			testidfine = true; // then testidfine will be true
		} else {
			JOptionPane
					.showMessageDialog(null,
							"We are very sorry but the User ID already exists in our system\n"
									+ "Please enter another User ID.",
							"Waring Duplicate ID", JOptionPane.WARNING_MESSAGE);
			// else display warning message
		}
		return testidfine; // the method will return the boolean value of
		// testidfine(True or False)
	}

	@Override
	public void actionPerformed(ActionEvent event) { // Action perfomed method
		String uid = id.getText(); // declares id variable and gets
									// input of id textbox
		if (event.getSource() == addBtn && Idtester(uid) == true) { /// if the
																	/// add
																	/// button
																	/// is
																	/// clicked
																	/// and if
																	/// the id
																	/// is
																	/// unique
			User user = new User(); // Stores reference of the Book Class
			user.setId(id.getText()); // the input of the id textbox will be set
										// as id
			user.setName(name.getText()); // the input of the name textbox will
											// be set as name
			user.setPhone(Integer.parseInt(phoneNum.getText())); // the input of
																	// the Phone
																	// Number
																	// textbox
																	// will be
																	// set as
																	// Phone
																	// Number
			user.setNumBooksBorrowed(Integer.parseInt(noBorrowedBooks.getText())); // the
																					// input
																					// of
																					// the
																					// Number
																					// of
																					// Books
																					// Borrowed
																					// textbox
																					// will
																					// be
																					// set
																					// as
																					// Number
																					// of
																					// Books
																					// Borrowed
			user.setreturndate("None"); // return date will be set as None
			mw.addUserToList(user); // adds the members added into the member
									// array
			mw.displayUsers(); // will display the new members details in the
								// mainwindow
			this.setVisible(false); // will close the add user window once add
									// button is clicked

		} else if (event.getSource() == cancelBtn) { // if cancel button is
														// clicked
			this.setVisible(false); // then will close the add book window
		}

	}

}
