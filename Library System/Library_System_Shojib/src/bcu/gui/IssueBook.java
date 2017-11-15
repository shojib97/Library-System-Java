package bcu.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import bcu.model.User;
import bcu.loader.BooksLoader;
import bcu.loader.UserLoader;
import bcu.model.Book;

public class IssueBook extends JFrame implements ActionListener {
	private MainWindow mw; // declares variable of the imported Mainwindow
	private ArrayList<Book> booksList;// Declare array list of the books
	private ArrayList<User> userList; // Declare array list of the members
	private JButton issueBtn = new JButton("Issue"); // Declare issue button
	private JButton returnBtn = new JButton("Return"); // Declare return button
	private JButton cancelBtn = new JButton("Cancel"); // Declare cancel button
	private JCheckBox[] check; // Declare array list of books checkboxes
	private JCheckBox[] checkuser; // Declare array list of members checkboxes

	public IssueBook(MainWindow mw) {
		this.mw = mw;
		booksList = mw.getBooks();// loads books from the mainwindow
		userList = mw.getUsers(); // loads users from the mainwindow
		initialize();
	}

	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
		}

		setTitle("Issue Book");// set title as issue book
		setSize(400, 300); // set size as 400 by 300
		JPanel TopPanel = new JPanel(); // creates top panel
		TopPanel.add(new JLabel("Please select book to be issued to the user"));// adds
																				// label
		TopPanel.setLayout(new GridLayout(3, 2));// sets layout 3 by 2
		JPanel westPanel = new JPanel(); // creates west panel
		westPanel.setLayout(new GridLayout(7, 1));// sets west panel 7 by 1
		westPanel.add(new JLabel("Books")); // adds books label in the west
											// panel
		check = new JCheckBox[booksList.size()];
		for (int i = 0; i < booksList.size(); i++) { // creates a list of
														// checkboxes with the
														// size of the bookslist
			Book bk = booksList.get(i);

			check[i] = new JCheckBox();
			check[i].setText(bk.getTitle());

			westPanel.add(check[i]);// adds books checkboxes in the west panel
		}
		JPanel eastPanel = new JPanel(); // creates west panel
		eastPanel.setLayout(new GridLayout(7, 1)); // sets west panel 7 by 1
		eastPanel.add(new JLabel("Users")); // adds users label in the east
											// panel
		checkuser = new JCheckBox[userList.size()];
		for (int i = 0; i < userList.size(); i++) { // creates a list of user
													// checkboxes with the
													// size of the userlist
			User us = userList.get(i);

			checkuser[i] = new JCheckBox();
			checkuser[i].setText(us.getName());

			eastPanel.add(checkuser[i]); // adds users checkboxes in the east
											// panel
		}
		JPanel bottomPanel = new JPanel(); // Creates bottom panel
		bottomPanel.setLayout(new GridLayout(1, 3)); // Used to set the layout
														// of
														// the bottom panel 1 by
														// 3
		bottomPanel.add(new JLabel("     "));
		bottomPanel.add(issueBtn); // adds the issue button in the bottom panel
		bottomPanel.add(returnBtn); // adds the return button in the bottom
									// panel
		bottomPanel.add(cancelBtn); // adds the cancel button in the bottom
									// panel

		issueBtn.addActionListener(this); // adds actionlistener for issue
											// button
		returnBtn.addActionListener(this); // adds actionlistener for return
											// button
		cancelBtn.addActionListener(this); // adds actionlistener for cancel
											// button

		// adds the panels in the frame
		this.getContentPane().add(TopPanel, BorderLayout.NORTH);
		this.getContentPane().add(westPanel, BorderLayout.WEST);
		this.getContentPane().add(eastPanel, BorderLayout.EAST);
		this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);

		setVisible(true);

	}

	public String duedate(String duedate) { // due date methods
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); // set
																		// format

		Calendar cal = Calendar.getInstance();
		Date date1 = new Date();
		cal.setTime(date1); // gets current date
		cal.add(Calendar.DATE, 14); // add 14 days
		date1 = cal.getTime(); // set date after 14 days
		String date = format.format(date1);
		duedate = date;

		return duedate; // return due date

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource() == issueBtn) {// if issue button is clicked
			int issuebookindex = 0;
			String duedate = null;
			for (int i = 0; i < booksList.size();) { // loop through the
														// checkboxes

				if (check[i].isSelected()) {// if check boxes is selected
					issuebookindex = i;// get index

					break; // and break the loop

				} else { // else go to the next index
					i++;
				}

			}
			Book bk = booksList.get(issuebookindex); // get the book details
														// from the checkbox
														// selected

			if (bk.getStatus().equals("Available")) { // if books is available
				bk.setStatus("Unavailable"); // set status as unavailable
				bk.setbookreturndate(duedate(duedate));// set return date as
														// duedate

			} else {
				JOptionPane.showMessageDialog(null,
						"We are sorry but the book is already taken\n" + "Please choose another book", "Warning",
						JOptionPane.WARNING_MESSAGE); // else display books
														// taken warning message

			}
			int issuememeberindex = 0;
			for (int i = 0; i < userList.size();) { // loop through users
													// checkboxes

				if (checkuser[i].isSelected()) { // if checkbox is selected
					issuememeberindex = i; // get index
					break; // and break the loop

				} else { // else go to the next index
					i++;

				}
			}
			User us = userList.get(issuememeberindex); // get the members
														// details from the
														// checkbox selected
			if (us.getNumBooksBorrowed() < 5) {// if numbers of books borrowed
												// is less than 5

				us.setNumBooksBorrowed(us.getNumBooksBorrowed() + 1); // add 1
																		// in
																		// the
																		// numbers
																		// of
																		// books
																		// borrowed
				us.setreturndate(duedate(duedate));// set return date as duedate
			} else {
				JOptionPane.showMessageDialog(null, "We are very sorry but a member can only borrow up to 5 books\n"
						+ "Please choose another member", "Warning", JOptionPane.WARNING_MESSAGE);
				// else display warning message
			}
			this.setVisible(false);// exit the window
		}

		else if (event.getSource() == returnBtn) {// if return button is checked
			int returnbookindex = 0;
			for (int i = 0; i < booksList.size();) {// loop through books
													// checkboxes

				if (check[i].isSelected()) { // if checkbox is selected
					returnbookindex = i; // get index
					break;// and break the loop

				} else { // else go to the next index
					i++;
				}

			}
			Book bk = booksList.get(returnbookindex); // get the books details
														// from the checkbox
														// selected
			if (bk.getStatus().equals("Unavailable")) {// if book is unavailable
				bk.setStatus("Available");// set books as available
				bk.setbookreturndate(("None"));// set return date as none

			} else {
				JOptionPane.showMessageDialog(null,
						"You cannot return an available book\n" + "Please choose another book", "Warning",
						JOptionPane.WARNING_MESSAGE);
				// else display warning message
			}
			int returnuserindex = 0;
			for (int i = 0; i < userList.size();) { // loop through members
													// checkboxes

				if (checkuser[i].isSelected()) { // if checkbox is selected
					returnuserindex = i;// get index
					break;// and break the loop

				} else { // else go to the next index
					i++;
				}

			}

			User us = userList.get(returnuserindex); // get the members details
														// from the checkbox
														// selected

			if (us.getNumBooksBorrowed() > 0) { // if numbers of books borrowed
												// is more than 0
				us.setNumBooksBorrowed(us.getNumBooksBorrowed() - 1); // subtract
																		// 1
																		// from
																		// the
																		// number
																		// of
																		// books
																		// borowed
																		// of
																		// the
																		// member
				us.setreturndate(("None"));// set return date as none
			} else {
				JOptionPane.showMessageDialog(null,
						"We are very sorry but the member selected does not have any borrowed book\n"
								+ "Please choose another member",
						"Warning", JOptionPane.WARNING_MESSAGE);
				// Display warning message
			}

			this.setVisible(false);// close window
		} else if (event.getSource() == cancelBtn) { // if cancel button is
														// clicked
			this.setVisible(false);// exit the window
		}

	}

}
