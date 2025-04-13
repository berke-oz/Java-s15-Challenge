package models;

import java.sql.SQLOutput;
import java.util.Map;

public class Author extends Person {
    private Map<Integer, Book> books;

    public Author(String name, Map<Integer, Book> books) {
        super(name);
        this.books = books;
    }

    public Map<Integer, Book> getBooks() {
        return books;
    }

    public void setBooks(Map<Integer, Book> books) {
        this.books = books;
    }

    public void newBook(Book book){
       this.books.put(book.getBookId(), book);
    }

    public void showBook(){
        System.out.println("Books by " + getName() + " :");
        for (Map.Entry<Integer, Book> entry : books.entrySet()) {
            System.out.println(entry.getValue().getName());  //
        }
    }




    @Override
    public String whoYouAre() {
        return "Author";
    }
}

