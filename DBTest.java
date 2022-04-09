import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class DBTest
{
    public static Connection connect()
        {
            Connection con=null;
            try {
                Class.forName("");
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/database name?","userName","password");
                // JOptionPane.showConfirmDialog(null,"connect to data base");

            } catch (Exception e) {
                System.out.println("inter.DBConnect.connect()");
                JOptionPane.showMessageDialog(null, e);
            }
            return con;
        }

    }

