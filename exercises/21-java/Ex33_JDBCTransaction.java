import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Ex33_JDBCTransaction {

    static final String URL  = "jdbc:mysql://localhost:3306/jdbc_demo";
    static final String USER = "root";
    static final String PASS = "your_password";

    static void transfer(Connection conn, int fromId, int toId, double amount) throws Exception {
        String debit  = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
        String credit = "UPDATE accounts SET balance = balance + ? WHERE id = ?";

        conn.setAutoCommit(false);
        try (PreparedStatement ps1 = conn.prepareStatement(debit);
             PreparedStatement ps2 = conn.prepareStatement(credit)) {

            ps1.setDouble(1, amount); ps1.setInt(2, fromId); ps1.executeUpdate();
            ps2.setDouble(1, amount); ps2.setInt(2, toId);   ps2.executeUpdate();

            conn.commit();
            System.out.println("✅ Transfer of ₹" + amount + " successful.");
        } catch (Exception e) {
            conn.rollback();
            System.out.println("❌ Transfer failed. Rolled back. Reason: " + e.getMessage());
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            transfer(conn, 1, 2, 200.00);
        } catch (Exception e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }
}
