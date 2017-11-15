package bcu.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import bcu.model.User;

public class UserLoader {
	private ArrayList<User> users = new ArrayList<User>(); //Declares Array list for the members

	public ArrayList<User> loadUsers() {
		try {
			FileReader file = new FileReader(".\\data\\users.txt"); //reads data from data\\users.txt
			BufferedReader fileReader = new BufferedReader(file);
			String line = fileReader.readLine(); //line is declared as string
			while (line != null) { //while there is no text in the file
				String[] parts = line.split("::"); //divide each string part with ::
				User user = new User(); //insert entry in the User class
				user.setId(parts[0]); // set ID of the Member in the array with index 0
				user.setName(parts[1]); // set Name of the Member in the array with index 1
				user.setNumBooksBorrowed(Integer.parseInt(parts[2])); // set Number of Books Borrowed of the Member in the array with index 2
				user.setPhone(Integer.parseInt(parts[3])); // set Phone Number of the Member in the array with index 3
				user.setreturndate(parts[4]); // set Return Date of the Member in the array with index 4
				users.add(user); 
				line = fileReader.readLine();
			}
			fileReader.close();
		} catch (Exception exp) {
			exp.printStackTrace();
		}

		return users; //returns the books array list
	}

}
