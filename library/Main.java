package library;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Library lib = new Library();
        Scanner sc = new Scanner(System.in);

        int ch;

        do {
            System.out.println("\n1.Add\n2.View\n3.Search\n4.Member\n5.Borrow\n6.Return\n7.Fine\n8.CSV\n9.Stats\n10.Exit");
            ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("ISBN: ");
                    String i = sc.nextLine();
                    System.out.print("Title: ");
                    String t = sc.nextLine();
                    System.out.print("Author: ");
                    String a = sc.nextLine();
                    System.out.print("Year: ");
                    int y = sc.nextInt();
                    lib.addBook(new Book(i,t,a,y));
                    break;

                case 2:
                    lib.displayAllBooks();
                    break;

                case 3:
                    System.out.print("Search: ");
                    lib.searchBooks(sc.nextLine()).forEach(System.out::println);
                    break;

                case 4:
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Name: ");
                    String n = sc.nextLine();
                    lib.registerMember(new Member(id,n));
                    break;

                case 5:
                    System.out.print("ISBN: ");
                    String bi = sc.nextLine();
                    System.out.print("Member ID: ");
                    String mi = sc.nextLine();
                    lib.borrowBook(bi, mi);
                    break;

                case 6:
                    System.out.print("ISBN: ");
                    lib.returnBook(sc.nextLine());
                    break;

                case 7:
                    System.out.print("ISBN: ");
                    lib.calculateFine(sc.nextLine());
                    break;

                case 8:
                    lib.exportCSV();
                    break;

                case 9:
                    lib.stats();
                    break;

                case 10:
                    System.out.println("Exit");
                    break;
            }

        } while (ch != 10);
    }
}