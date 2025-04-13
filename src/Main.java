import models.*;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Örnek Kütüphane Verileri
        Map<Integer, Reader> readers = new HashMap<>();
        Map<Integer, Book> books = new HashMap<>();

        // Kütüphane üyeleri
        readers.put(1, new Reader(1, "Ahmet", new ArrayList<>(), LocalDate.now(), 5, "Istanbul", "123456789"));
        readers.put(2, new Reader(2, "Mehmet", new ArrayList<>(), LocalDate.now(), 5, "Ankara", "987654321"));

        // Kitaplar (10 kitap ekleniyor)
        books.put(1, new Book(1, new Author("Berke Öz", new HashMap<>()), "Kitap 1", 50, BookStatus.AVAILABLE, 1, LocalDate.now(), null));
        books.put(2, new Book(2, new Author("Yazar 2", new HashMap<>()), "Kitap 2", 60, BookStatus.BORROWED, 1, LocalDate.now(), null));
        books.put(3, new Book(3, new Author("Yazar 3", new HashMap<>()), "Kitap 3", 70, BookStatus.AVAILABLE, 1, LocalDate.now(), null));
        books.put(4, new Book(4, new Author("Yazar 4", new HashMap<>()), "Kitap 4", 80, BookStatus.AVAILABLE, 1, LocalDate.now(), null));
        books.put(5, new Book(5, new Author("Yazar 5", new HashMap<>()), "Kitap 5", 90, BookStatus.BORROWED, 1, LocalDate.now(), null));
        books.put(6, new Book(6, new Author("Yazar 6", new HashMap<>()), "Kitap 6", 100, BookStatus.AVAILABLE, 1, LocalDate.now(), null));
        books.put(7, new Book(7, new Author("Yazar 7", new HashMap<>()), "Kitap 7", 110, BookStatus.BORROWED, 1, LocalDate.now(), null));
        books.put(8, new Book(8, new Author("Yazar 8", new HashMap<>()), "Kitap 8", 120, BookStatus.AVAILABLE, 1, LocalDate.now(), null));
        books.put(9, new Book(9, new Author("Yazar 9", new HashMap<>()), "Kitap 9", 130, BookStatus.BORROWED, 1, LocalDate.now(), null));
        books.put(10, new Book(10, new Author("Yazar 10", new HashMap<>()), "Kitap 10", 140, BookStatus.AVAILABLE, 1, LocalDate.now(), null));

        // Kütüphane üyeleri ve kitaplar
        Librarian librarian = new Librarian("admin", "admin123");

        Scanner scanner = new Scanner(System.in);

        // Kullanıcı türünü seçme
        System.out.println("Hoşgeldiniz! Lütfen kim olduğunuzu seçin:");
        System.out.println("1 - Kütüphaneci");
        System.out.println("2 - Okuyucu");
        int userType = scanner.nextInt();

        if (userType == 1) {
            // Kütüphaneci girişi
            System.out.print("Kullanıcı adı: ");
            String username = scanner.next();
            System.out.print("Şifre: ");
            String password = scanner.next();

            if (librarian.getName().equals(username) && librarian.getPassword().equals(password)) {
                System.out.println("Kütüphaneci olarak giriş yapıldı!");
                // Kütüphaneci işlemleri yapılabilir
                while (true) {
                    System.out.println("1 - Kitap Ekle");
                    System.out.println("2 - Kitap Sil");
                    System.out.println("3 - Kitapları Gör");
                    System.out.println("4 - Kitap Adıyla Ara");
                    System.out.println("5 - Kitap Güncelle");
                    System.out.println("6 - Çıkış");
                    System.out.print("Seçiminizi yapın: ");
                    int choice = scanner.nextInt();

                    if (choice == 1) {

                        System.out.print("Kitap adı: ");
                        String bookName = scanner.next();
                        System.out.print("Yazar adı: ");
                        String authorName = scanner.next();
                        System.out.print("Kitap sayfası: ");
                        int pages = scanner.nextInt();
                        System.out.print("Edition: ");
                        int edition = scanner.nextInt();

                        int bookId = books.size() + 1;
                        BookStatus status = BookStatus.AVAILABLE;
                        LocalDate publishDate = LocalDate.now();


                        Book newBook = new Book(bookId, new Author(authorName, new HashMap<>()), bookName, pages, status, edition, publishDate, null);
                        books.put(bookId, newBook);

                        System.out.println("Yeni kitap başarıyla eklendi: " + bookName);
                    } else if (choice == 2) {
                        // Kitap silme
                        System.out.println("Mevcut Kitaplar:");
                        for (Book book : books.values()) {
                            book.display();
                            System.out.println("----");
                        }

                        System.out.print("Silmek istediğiniz kitap ID'sini girin: ");
                        int bookId = scanner.nextInt();

                        if (books.containsKey(bookId)) {
                            librarian.deleteBook(bookId, books);
                            books.remove(bookId);
                            System.out.println("Kitap başarıyla silindi!");
                        } else {
                            System.out.println("Geçersiz kitap ID.");
                        }
                    } else if (choice == 3) {
                        // Kitapları listele
                        System.out.println("Mevcut Kitaplar:");
                        for (Book book : books.values()) {
                            book.display();
                        }
                    } else if (choice == 4) {
                        // Kitap adıyla arama
                        System.out.print("Aramak istediğiniz kitap adını girin: ");
                        String bookName = scanner.next();
                        boolean found = false;

                        for (Book book : books.values()) {
                            if (book.getName().toLowerCase().contains(bookName.toLowerCase())) {
                                book.display();
                                found = true;
                            }
                        }

                        if (!found) {
                            System.out.println("Bu isme sahip kitap bulunamadı.");
                        }
                    } else if (choice == 5) {
                        // Kitap güncelleme
                        System.out.println("Mevcut Kitaplar:");
                        for (Book book : books.values()) {
                            book.display();
                            System.out.println("----");
                        }

                        System.out.print("Güncellemek istediğiniz kitap ID'sini girin: ");
                        int bookId = scanner.nextInt();

                        if (books.containsKey(bookId)) {
                            Book bookToUpdate = books.get(bookId);
                            System.out.println("Şu anki kitap adı: " + bookToUpdate.getName());  // Mevcut adı göster
                            System.out.println("Güncellemek istediğiniz kitap bilgileri:");
                            System.out.println("Kitap Adı: " + bookToUpdate.getName());
                            System.out.println("Yazar Adı: " + bookToUpdate.getAuthor().getName());
                            System.out.println("Fiyat: " + bookToUpdate.getPrice());
                            System.out.println("Kitap ID: " + bookToUpdate.getBookId());
                            System.out.println("----");

                            System.out.println("Hangi bilgiyi değiştirmek istersiniz?");
                            System.out.println("1 - Kitap Adı");
                            System.out.println("2 - Yazar Adı");
                            System.out.println("3 - Fiyat");
                            System.out.print("Seçiminizi yapın: ");
                            int updateChoice = scanner.nextInt();
                            scanner.nextLine();  // Temizleme

                            if (updateChoice == 1) {
                                System.out.print("Yeni kitap adını girin: ");
                                String newBookName = scanner.nextLine();
                                bookToUpdate.setName(newBookName);
                            } else if (updateChoice == 2) {
                                System.out.print("Yeni yazar adını girin: ");
                                String newAuthorName = scanner.nextLine();
                                bookToUpdate.getAuthor().setName(newAuthorName);
                            } else if (updateChoice == 3) {
                                System.out.print("Yeni fiyatı girin: ");
                                double newPrice = scanner.nextDouble();
                                bookToUpdate.setPrice(newPrice);
                            } else {
                                System.out.println("Geçersiz seçim.");
                            }
                            System.out.println("Kitap başarıyla güncellendi!");
                        } else {
                            System.out.println("Geçersiz kitap ID.");
                        }
                    } else if (choice == 6) {
                        System.out.println("Çıkılıyor...");
                        break;
                    } else {
                        System.out.println("Geçersiz seçenek. Tekrar deneyin.");
                    }
                }
            } else {
                System.out.println("Geçersiz kullanıcı adı veya şifre!");
                return;
            }

        } else if (userType == 2) {
            // Okuyucu girişi
            boolean isValidReader = false;
            Reader currentReader = null;

            while (!isValidReader) {
                System.out.print("Okuyucu ID'nizi girin: ");
                int readerId = scanner.nextInt();

                if (readers.containsKey(readerId)) {
                    currentReader = readers.get(readerId);
                    isValidReader = true;
                    System.out.println("Hoşgeldiniz, " + currentReader.getName() + "!");
                } else {
                    System.out.println("Geçersiz okuyucu ID! Lütfen tekrar deneyin.");
                }
            }

            // Okuyucu işlemleri
            while (true) {
                System.out.println("1 - Kitap Al");
                System.out.println("2 - Kitapları Gör");
                System.out.println("3 - Kitap İade Et");
                System.out.println("4 - Ödünç Kitap Al");
                System.out.println("5 - Yazarın Kitaplarını Gör"); // Yeni seçenek
                System.out.println("6 - Çıkış");
                System.out.print("Seçiminizi yapın: ");
                int choice = scanner.nextInt();

                if (choice == 1) {
                    // Kullanıcı 5'ten fazla kitap alamaz
                    if (currentReader.getBooks().size() >= 5) {
                        System.out.println("Üzgünüz, 5'ten fazla kitap alamazsınız.");
                        continue;
                    }

                    // Sadece Available olan kitapları göster
                    System.out.println("Mevcut kitaplar:");
                    for (Book book : books.values()) {
                        if (book.getStatus() == BookStatus.AVAILABLE && book.getOwner() == null) {
                            book.display(); // Kitap bilgilerini display() ile göster
                            System.out.println("-------------------------------"); // Ayırıcı çizgi
                        }
                    }
                    System.out.print("Almak istediğiniz kitap ID'sini girin: ");
                    int bookId = scanner.nextInt();

                    if (books.containsKey(bookId)) {
                        Book book = books.get(bookId);
                        currentReader.purchaseBook(book);
                    } else {
                        System.out.println("Geçersiz kitap ID.");
                    }
                } else if (choice == 2) {
                    // Okuyucuya ait kitapları göster
                    System.out.println("Sahip olduğunuz kitaplar:");
                    currentReader.showBooks();
                } else if (choice == 3) {
                    // Kitap iade etme
                    System.out.println("İade etmek istediğiniz kitaplar:");

                    // Sahip olunan kitaplar ID ve isimleriyle listeleme
                    if (currentReader.getBooks().isEmpty()) {
                        System.out.println("Henüz sahip olduğunuz kitap yok.");
                    } else {
                        for (Book book : currentReader.getBooks()) {
                            System.out.println("ID: " + book.getBookId() + " - " + book.getName());
                            System.out.println("-------------------------------"); // Ayırıcı çizgi
                        }
                    }

                    System.out.print("İade etmek istediğiniz kitap ID'sini girin: ");
                    int bookId = scanner.nextInt();

                    if (books.containsKey(bookId)) {
                        Book book = books.get(bookId);
                        currentReader.returnBook(book);
                    } else {
                        System.out.println("Geçersiz kitap ID.");
                    }
                } else if (choice == 4) {
                    // Ödünç kitap al
                    System.out.println("Ödünç almak istediğiniz kitapları girin:");
                    for (Book book : books.values()) {
                        if (book.getStatus() == BookStatus.AVAILABLE) {
                            System.out.println("ID: " + book.getBookId() + " - " + book.getName());
                            System.out.println("-------------------------------"); // Ayırıcı çizgi
                        }
                    }

                    System.out.print("Ödünç almak istediğiniz kitap ID'sini girin: ");
                    int bookId = scanner.nextInt();

                    if (books.containsKey(bookId)) {
                        Book book = books.get(bookId);
                        currentReader.borrowBook(book);
                    } else {
                        System.out.println("Geçersiz kitap ID.");
                    }
                } else if (choice == 5) {
                    // Yazar adını girin den önce mevcut yazarları listele
                    System.out.println("Mevcut Yazarlar:");
                    Set<String> authors = new HashSet<>();
                    for (Book book : books.values()) {
                        authors.add(book.getAuthor().getName());
                    }

                    for (String author : authors) {
                        System.out.println(author);
                    }

                    System.out.print("Yazar adını girin: ");
                    scanner.nextLine();
                    String authorName = scanner.nextLine();
                    boolean foundBooks = false;

                    for (Book book : books.values()) {
                        if (book.getAuthor().getName().equalsIgnoreCase(authorName)) {
                            book.display();
                            foundBooks = true;
                        }
                    }

                    if (!foundBooks) {
                        System.out.println("Bu yazarın kitapları bulunmamaktadır.");
                    }
                } else if (choice == 6) {
                    System.out.println("Çıkılıyor...");
                    break;
                } else {
                    System.out.println("Geçersiz seçenek.");
                }
            }
        }
    }
}
