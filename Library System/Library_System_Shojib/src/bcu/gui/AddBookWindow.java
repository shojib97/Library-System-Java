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

import bcu.loader.BooksLoader;
import bcu.model.Book;

public class AddBookWindow extends JFrame implements ActionListener// Class used
																	// to create
																	// a window
																	// to add
																	// books
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MainWindow mw;
	private JTextField isbnText = new JTextField();// variable for a text field
													// that will be used to
													// enter ISBN of the book
	private JTextField titleText = new JTextField();// variable for a text field
													// that will be used to
													// enter title of the book
	private JTextField authText = new JTextField();// variable for a text field
													// that will be used to
													// enter the author of the
													// book
	private JTextField pubText = new JTextField();// variable for a text field
													// that will be used to
													// enter the publisher of
													// the book
	private JTextField pubDateText = new JTextField();// variable for a text
														// field that will be
														// used to enter the
														// published date of the
														// book
	private ArrayList<Book> booksList;// variable used to store books in a array
	private BooksLoader booksLoader = new BooksLoader();// variable used to load
														// books from text from
														// the class booksloader

	private JButton addBtn = new JButton("Add");// variable for a button that
												// will be used to trigger the
												// event add
	private JButton cancelBtn = new JButton("Cancel");// variable for a button
														// that will be used to
														// trigger the event
														// cancel

	public AddBookWindow(MainWindow mw)// Constructor for the class
										// AddBookWindow
	{
		this.mw = mw;

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {

		}

		// setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/icons/icon.JPG")));
		setTitle("Add a New Book");// Title of the Frame

		setSize(300, 200);// Size of the frame
		JPanel topPanel = new JPanel();// creates top panel
		topPanel.setLayout(new GridLayout(5, 2));// Used to set the layout of
													// the top panel 5 by 2
		topPanel.add(new JLabel("ISBN : "));// adds label for ISBN in the top
											// panel
		topPanel.add(isbnText);// adds ISBN text box in the top panel
		topPanel.add(new JLabel("Title : "));// adds label for Title in the top
												// panel
		topPanel.add(titleText);// adds Title text box in the top panel
		topPanel.add(new JLabel("Author : "));// adds label for Author in the
												// top panel
		topPanel.add(authText);// adds Author text box in the top panel
		topPanel.add(new JLabel("Publisher : "));// adds label for Publisher in
													// the top panel
		topPanel.add(pubText);// adds Publisher text box in the top panel
		topPanel.add(new JLabel("Publishing Date : "));// adds label for
														// Publishing Date in
														// the top panel
		topPanel.add(pubDateText); // adds Publishing Date text box in the top
									// panel

		JPanel bottomPanel = new JPanel(); // Creates bottom panel
		bottomPanel.setLayout(new GridLayout(1, 3));// Used to set the layout of
													// the bottom panel 1 by 3
		bottomPanel.add(new JLabel("     "));
		bottomPanel.add(addBtn);// adds the add button in the bottom panel
		bottomPanel.add(cancelBtn);// adds the cancel button in the bottom panel

		addBtn.addActionListener(this);// adds actionlistener for add button
		cancelBtn.addActionListener(this);// adds actionlistener for cancel
											// button

		this.getContentPane().add(topPanel, BorderLayout.CENTER); /// puts the
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

		setVisible(true);// sets the content of the frame visible

	}

	public boolean Isbntester(String isbn)// method used to test the uniqueness
											// of the isbn
	{
		boolean isbnunique = true; // sets the isbn unique as a true boolean
		boolean testisbnfine = false;
		booksList = booksLoader.loadBooks();// loads books in to a an arraylist
											// called booklist
		for (int i = 0; i < booksList.size(); i++)// the for loop is used to
													// loop through arraylist
		// starting from index 0 till it reaches the last index of the arraylist
		{
			Book book = booksList.get(i);// from the book class, it retrieves
											// the book with index i
			if (book.getIsbn().equals(isbn))// if isbn at index (i) is equals to
											// the isbn in the system
			{
				isbnunique = false;// then, isbnunique is set as false.

			}

		}
		if (isbnunique == true)// if isbnunique equals to true
		{
			testisbnfine = true;// then testisbnfine will be true
		} else {
			JOptionPane.showMessageDialog(null,
					"We are very sorry but the ISBN already exists in our system\n" + "Please insert a valid ISBN",
					"Warning Duplicate ISBN", JOptionPane.WARNING_MESSAGE);
			/// else, it will display the message above asking to put another input
		
		}
		return testisbnfine;// the method will return the boolean value of
							// testisbnfine(True or False)
	}

	@Override
	public void actionPerformed(ActionEvent ae)// Action perfomed method
	{
		String isbn = isbnText.getText(); // declares isbn variable and gets
											// input of isbn textbox
		if (ae.getSource() == addBtn && Isbntester(isbn) == true)/// if the add
																	/// button
																	/// is
																	/// clicked
																	/// and if
																	/// the isbn
																	/// is
																	/// unique
		{
			Book book = new Book();// Stores reference of the Book Class
			book.setIsbn(isbnText.getText());// the input of the isbn textbox
												// will be set as isbn
			book.setTitle(titleText.getText());// the input of the title textbox
												// will be set as title
			book.setAuthor(authText.getText());// the input of the author
												// textbox will be set as author
			book.setPublisher(pubText.getText());// the input of the publisher
													// textbox will be set as
													// publisher
			book.setPudDate(pubDateText.getText());// the input of the published
													// date textbox will be set
													// as published date
			book.setStatus("Available");// status will be set as available
			book.setbookreturndate("None");// return date will be set as None
			mw.addBookToList(book);// adds the book added into the book array
			mw.displayBooks();// will display the new book details in the
								// mainwindow
			this.setVisible(false);// will close the add book window once add
									// button is clicked

		} else if (ae.getSource() == cancelBtn)// if cancel button is clicked
		{
			this.setVisible(false);// then will close the add book window
		}

	}

}
