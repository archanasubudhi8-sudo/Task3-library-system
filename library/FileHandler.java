package library;

import java.io.*;
import java.util.*;

public class FileHandler {

    private final String BOOK_FILE = "data/books.txt";
    private final String MEMBER_FILE = "data/members.txt";

    public void saveBooks(List<Book> books) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(BOOK_FILE))) {
            for (Book b : books) {
                pw.println(b.getIsbn() + "," + b.getTitle() + "," +
                        b.getAuthor() + "," + b.getYear());
            }
        } catch (Exception e) {
            System.out.println("Error saving books");
        }
    }

    public List<Book> loadBooks() {
        List<Book> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BOOK_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                list.add(new Book(d[0], d[1], d[2], Integer.parseInt(d[3])));
            }
        } catch (Exception e) {
            System.out.println("No books file found.");
        }
        return list;
    }

    public void saveMembers(List<Member> members) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(MEMBER_FILE))) {
            for (Member m : members) {
                pw.println(m.getId() + "," + m.getName());
            }
        } catch (Exception e) {
            System.out.println("Error saving members");
        }
    }

    public List<Member> loadMembers() {
        List<Member> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(MEMBER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                list.add(new Member(d[0], d[1]));
            }
        } catch (Exception e) {
            System.out.println("No members file found.");
        }
        return list;
    }

    public void exportBooksToCSV(List<Book> books) {
        try (PrintWriter pw = new PrintWriter("data/books.csv")) {
            pw.println("ISBN,Title,Author,Year");
            for (Book b : books) {
                pw.println(b.getIsbn() + "," + b.getTitle() + "," +
                        b.getAuthor() + "," + b.getYear());
            }
            System.out.println("Exported to books.csv");
        } catch (Exception e) {
            System.out.println("Export failed");
        }
    }
}