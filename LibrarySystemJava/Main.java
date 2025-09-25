/**
 * Demo main that shows create/add books & users, issue and return operations.
 * Use this when testing the system.
 */
public class Main {
    public static void main(String[] args) {
        Library lib = new Library();

        // Add some books
        lib.addBook(new Book(1, "Clean Code", "Yashar"));
        lib.addBook(new Book(2, "Introduction to Algorithms", "Cormen et al."));
        lib.addBook(new Book(3, "Effective Java", "Joshua Bloch"));

        // Add users
        lib.addUser(new User(101, "Rishitha"));
        lib.addUser(new User(102, "Mubarak"));
        lib.addUser(new User(103, "Vijay"));

        // Initial state
        System.out.println("=== Initial State ===");
        lib.listBooks();
        lib.listUsers();
        System.out.println();

        // Issue book id=2 to user 101
        System.out.println("=== Issue Attempt 1 ===");
        lib.issueBook(101, 2); // success
        System.out.println();

        // Try to issue same book to another user (should fail)
        System.out.println("=== Issue Attempt 2 ===");
        lib.issueBook(102, 2); // fail: not available
        System.out.println();

        // Return book
        System.out.println("=== Return Attempt ===");
        lib.returnBook(101, 2); // success
        System.out.println();

        // Now issue again
        System.out.println("=== Issue Attempt 3 ===");
        lib.issueBook(102, 2); // success now
        System.out.println();

        // Final state
        System.out.println("=== Final State ===");
        lib.listBooks();
        lib.listUsers();
    }
}