package bcu.model;

public class User {
	private String id; // declares id variable as String
	private String name; // declares name variable as String
	private int phone; // declares phone variable as integer
	private int numBooksBorrowed; // declares Number Of Books Borrowed variable as integer
	private String returndate; // declares Return date variable as String

	public String getId() {
		return id; //Method returns id
	}

	public void setId(String id) {
		this.id = id; //method sets id as the content of id
	}

	public String getName() {
		return name; //Method returns name
	}

	public void setName(String name) {
		this.name = name; //method sets name as the content of name
	}

	public int getPhone() {
		return phone; //Method returns phone number
	}

	public void setPhone(int phone) {
		this.phone = phone; //method sets phone number as the content of phone 
	}

	public int getNumBooksBorrowed() {
		return numBooksBorrowed; //Method returns number of books borrowed
	}

	public void setNumBooksBorrowed(int numBooksBorrowed) {
		this.numBooksBorrowed = numBooksBorrowed; //method sets number of books borrowed as the content of numBooksBorrowed
	}

	public String getreturndate() {
		return returndate; //Method returns return date
	}

	public void setreturndate(String returndate) {
		this.returndate = returndate; //method sets return date as the content of returndate
	}
}
