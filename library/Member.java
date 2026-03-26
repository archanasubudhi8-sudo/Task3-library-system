package library;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private String id, name;
    private List<String> borrowedBooks;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        borrowedBooks = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }

    public void borrowBook(String isbn) {
        borrowedBooks.add(isbn);
    }

    public void returnBook(String isbn) {
        borrowedBooks.remove(isbn);
    }
}