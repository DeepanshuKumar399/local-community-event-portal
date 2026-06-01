import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Ex31_JDBCConnection {

    static final String URL  = "jdbc:mysql://localhost:3306/jdbc_demo";
    static final String USER = "root";
    static final String PASS = "your_password";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement  stmt = conn.createStatement();
             ResultSet  rs   = stmt.executeQuery("SELECT * FROM students")) {

            System.out.println("Connected to MySQL successfully!");
            System.out.println("ID | Name    | Age");
            System.out.println("---|---------|----");
            while (rs.next()) {
                System.out.printf("%-3d| %-8s| %d%n",
                    rs.getInt("id"), rs.getString("name"), rs.getInt("age"));
            }
        } catch (Exception e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }
}
