
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTest {

    public static void main(String args[]) {

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println("Oops! Got a SQL error: " + e.getMessage());
            System.exit(1);
        }

        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");

            stmt = conn.createStatement();
            rset = stmt
                    .executeQuery("select 'Hello '||USER||'!' result from dual");
            while (rset.next())
                System.out.println(rset.getString(1));
            rset.close();
            rset = null;
            stmt.close();
            stmt = null;
            conn.close();
            conn = null;
        } catch (SQLException e) {
            System.out.println("A SQL error: " + e.getMessage());
        } finally {
            if (rset != null)
                try {
                    rset.close();
                } catch (SQLException ignore) {
                }
            if (stmt != null)
                try {
                    stmt.close();
                } catch (SQLException ignore) {
                }
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException ignore) {
                }
        }
    }
}
