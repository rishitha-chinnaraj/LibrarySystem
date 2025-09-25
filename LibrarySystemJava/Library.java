import java.util.ArrayList;
import java.util.List;

/**
 * Library class that manages books and users.
 * Demonstrates Abstraction (exposes high-level methods issueBook/returnBook),
 * Encapsulation (manages internal lists), and basic error handling.
 */
public class Library {
    private final List<Book> books;
    private final List<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    // Add a book to the library
    public void addBook(Book book) {
        books.add(book);
    }

    // Add a user
    public void addUser(User user) {
        users.add(user);
    }

    // Find book by id (simple linear search)
    private Book findBookById(int bookId) {
        for (Book b : books) if (b.getId() == bookId) return b;
        return null;
    }

    // Find user by id
    private User findUserById(int userId) {
        for (User u : users) if (u.getId() == userId) return u;
        return null;
    }

    // Issue a book to a user
    public boolean issueBook(int userId, int bookId) {
        User user = findUserById(userId);
        Book book = findBookById(bookId);

        if (user == null) {
            System.out.printf("Issue failed: User with id %d not found.%n", userId);
            return false;
        }
        if (book == null) {
            System.out.printf("Issue failed: Book with id %d not found.%n", bookId);
            return false;
        }
        if (!book.isAvailable()) {
            System.out.printf("Issue failed: Book '%s' is currently not available.%n", book.getTitle());
            return false;
        }

        // mark as issued
        book.setAvailable(false);
        user.borrowBook(bookId);
        System.out.printf("Success: Book '%s' (id=%d) issued to %s (id=%d).%n", book.getTitle(), book.getId(), user.getName(), user.getId());
        return true;
    }

    // Return a book from a user
    public boolean returnBook(int userId, int bookId) {
        User user = findUserById(userId);
        Book book = findBookById(bookId);

        if (user == null) {
            System.out.printf("Return failed: User with id %d not found.%n", userId);
            return false;
        }
        if (book == null) {
            System.out.printf("Return failed: Book with id %d not found.%n", bookId);
            return false;
        }

        boolean removed = user.returnBook(bookId);
        if (!removed) {
            System.out.printf("Return failed: User %s (id=%d) did not borrow book id=%d.%n", user.getName(), user.getId(), bookId);
            return false;
        }

        book.setAvailable(true);
        System.out.printf("Success: Book '%s' (id=%d) returned by %s (id=%d).%n", book.getTitle(), book.getId(), user.getName(), user.getId());
        return true;
    }

    // List all books
    public void listBooks() {
        System.out.println("Library Books:");
        for (Book b : books) {
            System.out.printf("  - %s (id=%d) by %s - %s%n",
                    b.getTitle(), b.getId(), b.getAuthor(), b.isAvailable() ? "Available" : "Issued");
        }
    }

    // List all users
    public void listUsers() {
        System.out.println("Library Users:");
        for (User u : users) {
            System.out.printf("  - %s (id=%d) Borrowed: %s%n", u.getName(), u.getId(), u.getBorrowedBookIds());
        }
    }
}