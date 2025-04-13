package models;

import java.time.LocalDate;

public class Book {
    private int bookId;
    private Author author;
    private String name;
    private double price;
    private BookStatus status;
    private int edition;
    private LocalDate dateOfPurchase;
    private Reader owner;

    public Book(int bookId, Author author, String name, double price, BookStatus status, int edition, LocalDate dateOfPurchase, Reader owner) {
        this.bookId = bookId;
        this.author = author;
        this.name = name;
        this.price = price;
        this.status = status;
        this.edition = edition;
        this.dateOfPurchase = dateOfPurchase;
        this.owner = owner;
    }

    public int getBookId() {
        return bookId;
    }

    public double getPrice() {
        return price;
    }

    public int getEdition() {
        return edition;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public String getName(){
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    public BookStatus getStatus() {
        return status;
    }

    public Reader getOwner(){
        return owner;

    }

    public void updateStatus(BookStatus status) {
        this.status = status;
    }


    public void changeOwner(Reader owner){
        this.owner = owner;
    }

    public void display() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + name);
        System.out.println("Author: " + author.getName());
        System.out.println("Price: " + price);
        System.out.println("Edition: " + edition);
        System.out.println("Date of Purchase: " + dateOfPurchase);
        if (owner != null) {
            System.out.println("Owner: " + owner.getName());
        } else {
            System.out.println("Owner: No owner assigned yet.");
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", author=" + author +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", edition=" + edition +
                ", dateOfPurchase=" + dateOfPurchase +
                ", owner=" + owner +
                '}';
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }
}
