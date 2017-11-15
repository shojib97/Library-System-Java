package bcu.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import bcu.model.Book;

public class BooksLoader {

	private ArrayList<Book> books = new ArrayList<Book>(); //Declares Array list for the books

	public ArrayList<Book> loadBooks() {
		try {
			FileReader file = new FileReader(".\\data\\books.txt");// reads data
																	// from
																	// data\\books.txt
			BufferedReader fileReader = new BufferedReader(file);
			String line = fileReader.readLine();//line is declared as string
			while (line != null) { //while there is no text in the file
				String[] parts = line.split("::"); //divide each string part with ::
				Book book = new Book();//insert entry in the Books class
				book.setIsbn(parts[0]); // set ISBN of the book in the array with index 0
				book.setTitle(parts[1]); // set Title of the book in the array with index 1
				book.setAuthor(parts[2]); // set Author of the book in the array with index 2
				book.setPublisher(parts[3]); // set Publisher of the book in the array with index 2
				book.setPudDate(parts[4]); // set Publishing of the book in the array with index 2
				book.setStatus(parts[5]); // set Status of the book in the array with index 2
				book.setbookreturndate(parts[6]); // set Return date of the book in the array with index 2
				books.add(book);
				line = fileReader.readLine();
			}
			fileReader.close();
		} catch (Exception exp) {
			exp.printStackTrace();
		}

		return books;//returns the books array list
	}

}
