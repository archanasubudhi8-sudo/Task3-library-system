package library;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class Library {

    private List<Book> books;
    private List<Member> members;
    private FileHandler fileHandler;

    public Library() {
        fileHandler = new FileHandler();
        books = fileHandler.loadBooks();
        members = fileHandler.loadMembers();
    }

    public void addBook(Book b) {
        books.add(b);
        fileHandler.saveBooks(books);
        System.out.println("Book added!");
    }

    public void displayAllBooks() {
        books.forEach(System.out::println);
    }

    public List<Book> searchBooks(String key) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(key.toLowerCase()) ||
                b.getAuthor().toLowerCase().contains(key.toLowerCase()) ||
                b.getIsbn().contains(key)) {
                result.add(b);
            }
        }
        return result;
    }

    public void registerMember(Member m) {
        members.add(m);
        fileHandler.saveMembers(members);
        System.out.println("Member added!");
    }

    public Book findBook(String isbn) {
        for (Book b : books)
            if (b.getIsbn().equals(isbn)) return b;
        return null;
    }

    public Member findMember(String id) {
        for (Member m : members)
            if (m.getId().equals(id)) return m;
        return null;
    }

    public void borrowBook(String isbn, String memberId) {
        Book b = findBook(isbn);
        Member m = findMember(memberId);

        if (b == null || m == null) {
            System.out.println("Invalid data");
            return;
        }

        if (!b.isAvailable()) {
            System.out.println("Already borrowed");
            return;
        }

        b.setAvailable(false);
        b.setBorrowedBy(memberId);
        b.setDueDate(LocalDate.now().plusDays(7));

        m.borrowBook(isbn);

        fileHandler.saveBooks(books);
        fileHandler.saveMembers(members);

        System.out.println("Book borrowed!");
    }

    public void returnBook(String isbn) {
        Book b = findBook(isbn);

        if (b == null || b.isAvailable()) {
            System.out.println("Invalid return");
            return;
        }

        Member m = findMember(b.getBorrowedBy());
        if (m != null) m.returnBook(isbn);

        b.setAvailable(true);
        b.setBorrowedBy(null);
        b.setDueDate(null);

        fileHandler.saveBooks(books);
        fileHandler.saveMembers(members);

        System.out.println("Book returned!");
    }

    public void calculateFine(String isbn) {
        Book b = findBook(isbn);

        if (b == null || b.isAvailable()) {
            System.out.println("Invalid book");
            return;
        }

        if (!b.isOverdue()) {
            System.out.println("No fine");
            return;
        }

        long days = ChronoUnit.DAYS.between(b.getDueDate(), LocalDate.now());
        System.out.println("Fine: ₹" + (days * 5));
    }

    public void exportCSV() {
        fileHandler.exportBooksToCSV(books);
    }

    public void stats() {
        long available = books.stream().filter(Book::isAvailable).count();
        System.out.println("Total: " + books.size());
        System.out.println("Available: " + available);
        System.out.println("Borrowed: " + (books.size() - available));
    }
}