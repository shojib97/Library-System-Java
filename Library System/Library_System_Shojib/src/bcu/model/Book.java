package bcu.model;

public class Book {

	private String isbn; // declares isbn variable as String
	private String title; // declares title variable as String
	private String author; // declares author variable as String
	private String publisher; // declares publisher variable as String
	private String pudDate; // declares publishing date variable as String
	private String status; // declares status variable as String
	private String bookreturndate; // declares return date variable as String

	public String getStatus() {
		return status; // method returns status
	}

	public void setStatus(String status) {
		this.status = status; //method sets status as the content of status
	}

	public String getIsbn() {
		return isbn; // method returns isbn
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn; //method sets isbn as the content of isbn
	}

	public String getTitle() {
		return title; // method returns title
	}

	public void setTitle(String title) {
		this.title = title; //method sets title as the content of title
	}

	public String getAuthor() {
		return author; // method returns author
	}

	public void setAuthor(String author) {
		this.author = author; //method sets author as the content of author
	}

	public String getPublisher() {
		return publisher; // method returns publisher
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher; //method sets publisher as the content of publisher
	}

	public String getPudDate() {
		return pudDate; // method returns publishing date
	}

	public void setPudDate(String pudDate) {
		this.pudDate = pudDate; //method sets publishing date as the content of pudDate
	}

	public String getbookreturndate() {
		return bookreturndate; // method returns return date
	}

	public void setbookreturndate(String bookreturndate) {
		this.bookreturndate = bookreturndate; //method sets return date as the content of bookreturndate
	}

}
