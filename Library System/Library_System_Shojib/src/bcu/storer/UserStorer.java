package bcu.storer;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import bcu.model.User;

public class UserStorer {
	private FileWriter fw; // declares filewriter as fw
	private BufferedWriter bw; // declares bufferdwriter as bw

	public void StoreUsers(ArrayList<User> usersList) {
		try {
			fw = new FileWriter(".\\data\\users.txt"); // writes data into
														// \\data\\books.txt
			bw = new BufferedWriter(fw);

			for (int i = 0; i < usersList.size(); i++) { // for loop will run
															// till
															// the size of the
															// array
															// list usersList
				String content = ""; // Content is declared as String
				User user = usersList.get(i); // Gets content of the array list
				content += user.getId() + "::"; // Gets ID of the Member and
												// adds ::
				content += user.getName() + "::"; // Gets Name of the Member and
													// adds ::
				content += user.getNumBooksBorrowed() + "::"; // Gets Number of
																// Books
																// Borrowed of
																// the Member
																// and adds ::
				content += user.getPhone() + "::"; // Gets Phone Number of the
													// Member and adds ::
				content += user.getreturndate() + "\n"; // Gets Return Date of
														// the Member

				bw.write(content); // writes the content of the Members in the
									// text file

			}
			JOptionPane.showMessageDialog(null, "Complete storing all users!", "Store Users",
					JOptionPane.PLAIN_MESSAGE); // confirms with a message that
												// the Members are stored
		} catch (IOException e) {
			e.printStackTrace();
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
