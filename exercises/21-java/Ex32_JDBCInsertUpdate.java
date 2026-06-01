import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Ex32_JDBCInsertUpdate {

    static final String URL  = "jdbc:mysql://localhost:3306/jdbc_demo";
    static final String USER = "root";
    static final String PASS = "your_password";

    static class StudentDAO {

        private final Connection conn;
        StudentDAO(Connection conn) { this.conn = conn; }

        void insert(String name, int age) throws Exception {
            String sql = "INSERT INTO students (name, age) VALUES (?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setInt(2, age);
                int rows = ps.executeUpdate();
                System.out.println("Inserted " + rows + " row(s): " + name);
            }
        }

        void update(int id, String newName) throws Exception {
            String sql = "UPDATE students SET name = ? WHERE id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, newName);
                ps.setInt(2, id);
                int rows = ps.executeUpdate();
                System.out.println("Updated " + rows + " row(s) for ID " + id);
            }
        }
    }

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            StudentDAO dao = new StudentDAO(conn);
            dao.insert("Diana", 23);
            dao.update(1, "Alice Johnson");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
