package bcu.storer;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import bcu.model.Book;

public class BookStorer {
	private FileWriter fw; // declares filewriter as fw
	private BufferedWriter bw; // declares bufferdwriter as bw

	public void StoreBooks(ArrayList<Book> bookList) {
		try {
			fw = new FileWriter(".\\data\\books.txt"); // writes data into
														// \\data\\books.txt
			bw = new BufferedWriter(fw);

			for (int i = 0; i < bookList.size(); i++) {// for loop will run till
														// the size of the array
														// list booksList
				String content = "";// Content is declared as String
				Book book = bookList.get(i); // Gets content of the array list
				content += book.getIsbn() + "::"; // Gets ISBN of the book and
													// adds ::
				content += book.getTitle() + "::"; // Gets Title of the book and
													// adds ::
				content += book.getAuthor() + "::"; // Gets Author of the book
													// and adds ::
				content += book.getPublisher() + "::"; // Gets Publisher of the
														// book and adds ::
				content += book.getPudDate() + "::"; // Gets Publishing Date of
														// the book and adds ::
				content += book.getStatus() + "::"; // Gets Status of the book
													// and adds ::
				content += book.getbookreturndate() + "\n"; // Gets Return Date
															// of the book

				bw.write(content); // writes the content of the books in the
									// text file

			}
			JOptionPane.showMessageDialog(null, "Complete storing all books!", "Store Books",
					JOptionPane.PLAIN_MESSAGE); // confirms with a message that
												// the books are stored

		} catch (IOException e) {

		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

}
