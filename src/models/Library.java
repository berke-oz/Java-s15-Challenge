package models;

import java.util.HashMap;
import java.util.Map;

public class Library {
    private Map<Integer, Book> books;
    private Map<Integer, Reader> readers;

    public Library() {
        this.books = new HashMap<>();
        this.readers = new HashMap<>();
    }

    public Map<Integer, Book> getBooks() {
        return books;
    }

    public Map<Integer, Reader> getReaders() {
        return readers;
    }

    public void newBook(Book book){
        int bookId = book.getBookId();
        if(!books.containsKey(bookId)){
            books.put(bookId, book);
            System.out.println(book.getName() + " added to library.");
        }
        else{
            System.out.println("Book with ID " + bookId + " already exists:");
            books.get(bookId).display();
        }
    }

    public void lendBook(int readerId, int bookId){
        Reader reader = readers.get(readerId);
        Book book = books.get(bookId);

        if(reader == null){
            System.out.println("Reader not found!");
            return;
        }
        if(book == null){
            System.out.println("Book not found!");
            return;
        }

        reader.borrowBook(book);
    }

    public void takeBackBook(int readerId, int bookId){
        Reader reader = readers.get(readerId);
        Book book = books.get(bookId);

        if(reader == null){
            System.out.println("Reader not found !");
            return;
        }
        if (book ==null){
            System.out.println("Book not found");
            return;
        }

            reader.returnBook(book);


    }

    public void showBooks() {
        if (books == null || books.isEmpty()) {
            System.out.println("Library has no books.");
        } else {
            System.out.println("Books in the library:");
            for (Book book : books.values()) {
                if (book.getStatus() == BookStatus.AVAILABLE) {
                    book.display();
                    System.out.println("--------------");
                }
            }
        }
    }



}
