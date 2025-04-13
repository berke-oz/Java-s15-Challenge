package models;

import java.time.LocalDate;
import java.util.List;

public class Reader extends Person {
    private int id;
    private List<Book> books;
    private LocalDate dateOfMembership;
    private int noBooksIssued;
    private int maxBookLimit;
    private String address;
    private String phoneNo;

    public Reader(int id, String name, List<Book> books,
                  LocalDate dateOfMembership, int maxBookLimit,
                  String address, String phoneNo) {
        super(name);
        this.id = id;
        this.books = books;
        this.dateOfMembership = dateOfMembership;
        this.noBooksIssued = 0;
        this.maxBookLimit = maxBookLimit;
        this.address = address;
        this.phoneNo = phoneNo;
    }

    public int getId() {
        return id;
    }

    public void purchaseBook(Book book) {
        if (book.getStatus() == BookStatus.AVAILABLE) {
            books.add(book);
            book.changeOwner(this);
            book.updateStatus(BookStatus.SOLD);
            System.out.println(book.getName() + " purchased by " + getName());
            double bookPrice = book.getPrice();
            payBill(bookPrice);
        } else {
            System.out.println(book.getName() + " is not available for purchase");
        }
    }

    public void borrowBook(Book book) {
        if (book.getStatus() == BookStatus.AVAILABLE) {
            if (noBooksIssued >= maxBookLimit) {
                System.out.println("Borrow limit reached!");
                return;
            }
            books.add(book);
            book.changeOwner(this);
            book.updateStatus(BookStatus.BORROWED);
            incBookIssued();
            System.out.println(getName() + " has borrowed " + book.getName());
        } else {
            System.out.println(book.getName() + " is not available for borrowing");
        }
    }

    public void returnBook(Book book) {
        if (books.contains(book)) {
            books.remove(book);
            book.changeOwner(null);
            book.updateStatus(BookStatus.AVAILABLE);
            decBookIssued();
            System.out.println(getName() + " has returned " + book.getName());
        } else {
            System.out.println(getName() + " doesn't have " + book.getName());
        }
    }

    public void showBooks() {
        if (books.isEmpty()) {
            System.out.println(getName() + " has no books");
        } else {
            for (Book book : books) {
                System.out.println(book.getName());
            }
        }
    }

    public void showBooksByAuthor(Author author) {
        if (author.getBooks().isEmpty()) {
            System.out.println(author.getName() + " has no books.");
        } else {
            author.showBook();
        }
    }

    public void incBookIssued() {
        noBooksIssued++;
    }

    public void decBookIssued() {
        if (noBooksIssued > 0) noBooksIssued--;
    }

    public void payBill(double amount) {
        System.out.println(getName() + " paid ₺" + amount);
    }


    public LocalDate getDateOfMembership() {
        return dateOfMembership;
    }

    public int getNoBooksIssued() {
        return noBooksIssued;
    }

    public int getMaxBookLimit() {
        return maxBookLimit;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String whoYouAre() {
        return "Reader";
    }
}
