package models;

import java.util.HashMap;
import java.util.Map;

public class Librarian {
    private String name;
    private String password;

    public Librarian(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void searchBook(String bookName, Map<Integer, Book> books){
        System.out.println("Searching for books containing: " + bookName);
        boolean found = false;
        for(Book book : books.values()){
            if(book.getName().toLowerCase().contains(bookName.toLowerCase())){ // contains() ile kısmi arama
                book.display();
                System.out.println("--------------"); // Ayırıcı çizgi
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found containing: " + bookName);
        }
    }


    public void updateBookDetails(Book book, String newName, String newAuthorName, double newPrice) {
        book.setName(newName);
        book.setPrice(newPrice);
        book.setAuthor(new Author(newAuthorName, new HashMap<>()));
    }

    public void displayBooks(Map<Integer, Book> books) {
        for (Book book : books.values()) {
            book.display();
            System.out.println("--------------");
        }
    }

    public boolean verifyMember(int memberId, Map<Integer, Reader> readers) {
        return readers.containsKey(memberId);
    }

    public void issueBook(int readerId, int bookId, Map<Integer, Reader> readers, Map<Integer, Book> books) {
        if (!readers.containsKey(readerId)) {
            System.out.println("Reader not found. Please try again.");
            return;
        }

        if (!books.containsKey(bookId)) {
            System.out.println("Book not found. Please try again.");
            return;
        }

        Reader reader = readers.get(readerId);
        Book book = books.get(bookId);


        if (reader.getBooks().size() >= 5) {
            System.out.println("This user has already received 5 books. No more books can be given.");
            return;
        }


        if (book.getStatus() == BookStatus.AVAILABLE && book.getOwner() == null) {
            reader.borrowBook(book);
            System.out.println("The book was lent:  " + book.getName());
        } else {
            System.out.println("The book is not available or has already been borrowed.");
        }
    }


    public double calculateFine(int delayedDays){
        double finePerDay = 4.5;
        return delayedDays * finePerDay;
    }

    public void createBill(String readerName, double bookPrice) {
        System.out.println("Bill for " + readerName + ": ₺" + bookPrice);
    }

    public void returnBook(int readerId, int bookId, Map<Integer, Reader> readers, Map<Integer, Book> books) {
        if (verifyMember(readerId, readers) && books.containsKey(bookId)) {
            Reader reader = readers.get(readerId);
            Book book = books.get(bookId);

            reader.returnBook(book);
        } else {
            System.out.println("Reader or Book not found.");
        }
    }

    public void deleteBook(int bookId, Map<Integer, Book> books) {
        if (books.containsKey(bookId)) {
            Book bookToDelete = books.get(bookId);
            if (bookToDelete.getStatus() == BookStatus.BORROWED) {
                System.out.println("Bu kitap şu an ödünç alınmış ve silinemez.");
            } else {
                books.remove(bookId);
                System.out.println("Kitap silindi: " + bookToDelete.getName());
            }
        } else {
            System.out.println("Geçersiz kitap ID.");
        }
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
