import java.util.ArrayList;
import java.util.List;

/**
 * Represents a library user.
 * Shows HAS-A relationship: a User has a list of borrowed Books (by id).
 */
public class User {
    private final int id;
    private String name;
    private final List<Integer> borrowedBookIds;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBookIds = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public List<Integer> getBorrowedBookIds() { return borrowedBookIds; }

    public void setName(String name) { this.name = name; }

    public void borrowBook(int bookId) {
        borrowedBookIds.add(bookId);
    }

    public boolean returnBook(int bookId) {
        return borrowedBookIds.remove((Integer) bookId);
    }

    @Override
    public String toString() {
        return String.format("User{id=%d, name='%s', borrowed=%s}", id, name, borrowedBookIds);
    }
}