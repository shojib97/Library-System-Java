package bcu.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import bcu.loader.BooksLoader;
import bcu.loader.UserLoader;
import bcu.model.Book;
import bcu.model.User;
import bcu.storer.BookStorer;
import bcu.storer.UserStorer;

public class MainWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JMenuBar menuBar; // Creates variable for the menubar
	private JMenu admin; // Creates variable for the menu Admin
	private JMenu books; // Creates variable for the menu Books
	private JMenu members; // Creates variable for the menu Members

	private JMenuItem adminAdd; // Creates variable for the Admin menu item Add
								// Admin
	private JMenuItem adminChngePwd;// Creates variable for the Admin menu item
									// Change Password
	private JMenuItem adminExit;// Creates variable for the Admin menu item Exit

	private JMenuItem booksView; // Creates variable for the Books menu item View
	private JMenuItem booksAdd; // Creates variable for the Books menu item Add
	private JMenuItem booksDel; // Creates variable for the Books menu item Delete
	private JMenuItem booksIssue; // Creates variable for the Books menu item Issue
	private JMenuItem booksSearch; // Creates variable for the Books menu item Search
	private JMenuItem booksStore; // Creates variable for the Books menu item Store

	private JMenuItem memView; // Creates variable for the Members menu item View
	private JMenuItem memAdd;// Creates variable for the Members menu item Add
	private JMenuItem memDel;// Creates variable for the Members menu item Delete
	private JMenuItem memSearch;// Creates variable for the Members menu item Search
	private JMenuItem memStore;// Creates variable for the Members menu item Store

	private JTable table; //Creates variable for Books table
	private JTable usertable; //Creates variable for Members table
	private TableRowSorter<TableModel> BookrowSorter;//Creates variable for Book table row sorter
	private TableRowSorter<TableModel> UserrowSorter;//Creates variable for Members table row sorter
	private DefaultTableModel model;
	private DefaultTableModel usrmodel;

	private JTextField BookFilter = new JTextField();//Creates textbox variable for Books search
	private JTextField UserFilter = new JTextField();//Creates textbox variable for Members search

	private BooksLoader booksLoader = new BooksLoader(); //Creates a variable for the imported class BooksLoader
	private UserLoader userLoader = new UserLoader(); //Creates a variable for the imported class UserLoader
	private UserStorer usrStorer = new UserStorer(); //Creates a variable for the imported class UserStorer
	private BookStorer bookStorer = new BookStorer(); //Creates a variable for the imported class BookStorer
	private ArrayList<Book> booksList = new ArrayList<Book>();//Creates a variable for the books Array
	private ArrayList<User> userList = new ArrayList<User>();//Creates a variable for the members Array

	public MainWindow() { //Constructor for the MainWindow Class

		initialize(); //runs the initialize method

		booksList = booksLoader.loadBooks(); //loads the books in the arraylist booksList 
		userList = userLoader.loadUsers();//loads the Members in the arraylist userList 
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
		setTitle("Library Management System");//sets title as Library Management System

		menuBar = new JMenuBar();//Creates a Menu bar variable in the Main Window
		setJMenuBar(menuBar); //adds a Menu bar in the Main Window

		// adding admin menu and menu items

		admin = new JMenu("Admin");
		menuBar.add(admin);

		adminAdd = new JMenuItem("Add Admin");
		adminChngePwd = new JMenuItem("Change Password");
		adminExit = new JMenuItem("Exit");
		admin.add(adminAdd);
		admin.add(adminChngePwd);
		admin.add(adminExit);
		adminAdd.addActionListener(this);
		adminChngePwd.addActionListener(this);
		adminExit.addActionListener(this);

		// adding books menu and menu items
		books = new JMenu("Books");
		menuBar.add(books);

		booksView = new JMenuItem("View");
		booksAdd = new JMenuItem("Add");
		booksDel = new JMenuItem("Delete");
		booksIssue = new JMenuItem("Issue");
		booksSearch = new JMenuItem("Search");
		booksStore = new JMenuItem("Store");
		books.add(booksView);
		books.add(booksAdd);
		books.add(booksDel);
		books.add(booksIssue);
		books.add(booksSearch);
		books.add(booksStore);
		booksView.addActionListener(this);
		booksAdd.addActionListener(this);
		booksDel.addActionListener(this);
		booksIssue.addActionListener(this);
		booksSearch.addActionListener(this);
		booksStore.addActionListener(this);

		// adding members menu and menu items

		members = new JMenu("Members");
		menuBar.add(members);

		memView = new JMenuItem("View");
		memAdd = new JMenuItem("Add");
		memDel = new JMenuItem("Delete");
		memSearch = new JMenuItem("Search");
		memStore = new JMenuItem("Store");

		members.add(memView);
		members.add(memAdd);
		members.add(memDel);
		members.add(memSearch);
		members.add(memStore);

		memView.addActionListener(this);
		memAdd.addActionListener(this);
		memDel.addActionListener(this);
		memSearch.addActionListener(this);
		memStore.addActionListener(this);

		setSize(800, 500);

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new MainWindow();//runs MainWindow in the main Method
	}

	@Override
	public void actionPerformed(ActionEvent ae) {//Action Perfomed Method

		if (ae.getSource() == adminAdd) {//if Add is clicked in the admin menu
			new AddAdminWindow(this);// Then, AddAdminWindow Class will run

		} else if (ae.getSource() == adminChngePwd) {

		} else if (ae.getSource() == adminExit) { //if Exit is clicked in the admin menu
			System.exit(0); //Then, it will exit form the system
		} else if (ae.getSource() == booksView) {//if View is clicked in the Books menu
			displayBooks(); //Then, displayBooks method will run

		} else if (ae.getSource() == booksAdd) {//if Add is clicked in the Books menu
			new AddBookWindow(this); //Then, AddBookWindow Class will run

		} else if (ae.getSource() == booksDel) { //if Delete is clicked in the Books menu
			int d = table.getSelectedRow();
			booksList.remove(d);//Then, it will delete the book selected from the table

		} else if (ae.getSource() == booksIssue) { //if Issue is clicked in the Books menu
			new IssueBook(this); //Then, IssueBook Class will run

		} else if (ae.getSource() == booksSearch) {//if Search is clicked in the Books menu
			searchBooks(); //Then, searchBooks method will run
			
		} else if (ae.getSource() == booksStore) {//if Store is clicked in the Books menu
			bookStorer.StoreBooks(booksList);//Stores the book from the array list
			
		} else if (ae.getSource() == memView) {//if view is clicked in the Members menu
			displayUsers(); //Then, displayUsers method will run

		} else if (ae.getSource() == memAdd) { //if add is clicked in the Members menu
			new AddUserWindow(this);//Then, AddBookWindow Class will run

		} else if (ae.getSource() == memDel) { //if delete is clicked in the Members menu
			int f = usertable.getSelectedRow();
			userList.remove(f); //Then, it will delete the users selected from the table

		} else if (ae.getSource() == memSearch) { //if search is clicked in the Members menu
			searchUsers(); //Then, searchBooks method will run
		}

		else if (ae.getSource() == memStore) { //if store is clicked in the Members menu
			usrStorer.StoreUsers(userList); //Stores the members from the array list
		}

	}

	public void addBookToList(Book book) { //public method used 
		booksList.add(book);//to add book in the array list booksList
	}

	public ArrayList getBooks() {//arraylist method used to return the books array list
		return booksList; 
	}

	public ArrayList getUsers() { //arraylist method used to return the members array list
		return userList;
	}

	public void displayBooks() {
		// headers for the table
		String[] columns = new String[] { "ISBN", "Title", "Author", "Publisher", "Pub Date", "Status", "Return Date" };

		Object[][] data = new Object[booksList.size()][columns.length]; //imports books in the table
		for (int i = 0; i < booksList.size(); i++) {
			Book book = booksList.get(i);
			data[i][0] = book.getIsbn(); // row 1 is ISBN
			data[i][1] = book.getTitle(); // row 2 is Title
			data[i][2] = book.getAuthor(); // row 3 is Author
			data[i][3] = book.getPublisher(); // row 4 is Publisher
			data[i][4] = book.getPudDate(); // row 5 is Publishing Date
			data[i][5] = book.getStatus(); // row 6 is Status
			data[i][6] = book.getbookreturndate(); // row 7 is Return Date
		}

		model = new DefaultTableModel(data, columns);
		table = new JTable(model);
		this.getContentPane().removeAll();
		this.getContentPane().add(new JScrollPane(table));// puts table in the frame
		this.revalidate();

	}

	public JTable booking() {
		return table; //return the books table
	}

	public void searchBooks() {
		JTable table1 = booking();  // imports Books table
		BookrowSorter = new TableRowSorter<>(table1.getModel()); // uses book table for searching
		table1.setRowSorter(BookrowSorter);

		JPanel panel = new JPanel();// creates a panel in the frame
		panel.setLayout(new GridLayout(3, 2));
		panel.add(new JLabel("Specify a book to match:")); 
		panel.add(BookFilter); // adds the Books search box in the Panel

		this.getContentPane().removeAll();
		this.getContentPane().add(new JScrollPane(table1));// adds the sorted table in the Frame
		this.getContentPane().add(panel, BorderLayout.SOUTH);
		this.revalidate();

		BookFilter.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) { // event for inserting text
				String text = BookFilter.getText();// gets text from the search text box

				if (text.trim().length() == 0) { // if there is no book matching the text
					BookrowSorter.setRowFilter(null);//nothing will be displayed
				} else {
					BookrowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				} // else, it will display the books found
			}

			@Override
			public void removeUpdate(DocumentEvent e) { // event for removing text text
				String text = BookFilter.getText(); // gets text form the search text box

				if (text.trim().length() == 0) {  // if there is no book matching the text
					BookrowSorter.setRowFilter(null); //nothing will be displayed
				} else {
					BookrowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}// else, it will display the books found
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				throw new UnsupportedOperationException("Not supported yet.");
			}

		});
	}

	public void addUserToList(User user) {//method used
		userList.add(user); //to add members in the array list userList
	}

	public void displayUsers() {
		// headers for the table
		String[] columns = new String[] { "ID", "Name", "Number of Books Borrowed", "Phone Number", "Return Date" };
		
		Object[][] data = new Object[userList.size()][columns.length]; //imports members in the table
		for (int i = 0; i < userList.size(); i++) {
			User user = userList.get(i);
			data[i][0] = user.getId(); // row 1 is ID
			data[i][1] = user.getName(); // row 2 is Name
			data[i][2] = user.getNumBooksBorrowed(); // row 3 is Number of Books borrowed
			data[i][3] = user.getPhone();// row 4 is Phone Number
			data[i][4] = user.getreturndate(); // row 5 is Return Date
		}

		usrmodel = new DefaultTableModel(data, columns);
		usertable = new JTable(usrmodel);
		this.getContentPane().removeAll();
		this.getContentPane().add(new JScrollPane(usertable)); // puts table in the frame
		this.revalidate();

	}

	public JTable usrtable() {
		return usertable;  //return the members table
	}

	public void searchUsers() {
		JTable usrt = usrtable(); // imports the members table
		UserrowSorter = new TableRowSorter<>(usrt.getModel()); // uses members table for searching
		usrt.setRowSorter(UserrowSorter);

		JPanel panel = new JPanel(); // creates a panel in the frame
		panel.setLayout(new GridLayout(3, 2));
		panel.add(new JLabel("Specify a user to match:"));
		panel.add(UserFilter); // adds the Members search box in the Panel

		this.getContentPane().removeAll();
		this.getContentPane().add(new JScrollPane(usrt)); // adds the sorted table in the Frame
		this.getContentPane().add(panel, BorderLayout.SOUTH);
		this.revalidate();

		UserFilter.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) { // event for inserting text
				String text = UserFilter.getText(); // gets text from the search text box

				if (text.trim().length() == 0) { // if there is no Members matching the text
					UserrowSorter.setRowFilter(null); //nothing will be displayed
				} else { 
					UserrowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}  // else, it will display the members found
			}

			@Override
			public void removeUpdate(DocumentEvent e) { // event for removing text text
				String text = UserFilter.getText(); // gets text from the search text box
 
				if (text.trim().length() == 0) { // if there is no Members matching the text
					UserrowSorter.setRowFilter(null); //nothing will be displayed
				} else {
					UserrowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				} // else, it will display the members found
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				throw new UnsupportedOperationException("Not supported yet.");
			}

		});
	}

}
