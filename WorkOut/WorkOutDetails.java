package WorkOut;



import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;

public class WorkOutDetails {
    public WorkOutDetails()
    {
        // Create a new frame
        JFrame f = new JFrame("Insert the WorkOut Details");
        f.getContentPane().setBackground(new Color(230, 230, 0));
        f.setBackground(Color.cyan);

        // Create a new Label for the Title
        JLabel title = new JLabel("Enter the Workout Details", JLabel.CENTER);
        title.setBounds(500, 50, 400, 80);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        title.setForeground(Color.BLUE);
        f.add(title);

        JLabel sno, planid, exname, counts, comments;
        JTextField sno1, planid1, exname1, counts1;

        // Create a JLabel with the text "Sno"
        sno = new JLabel("Sno");
        sno.setBounds(400, 125, 100, 30);
        sno.setFont(new Font("Calibri", Font.ITALIC, 24));
        sno.setForeground(Color.BLACK);
        f.add(sno);
        sno1 = new JTextField();
        sno1.setBounds(675, 125, 45, 25);
        sno1.setFont(new Font("Calibri", Font.ITALIC, 18));
        sno1.setForeground(Color.BLACK);
        // Set the text for the Sno field by calling the getSno() method
        sno1.setText(getSno());
        sno1.setEditable(false);
        sno1.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });
        f.add(sno1);


        //Create a  JLabel with text "Plan ID"
        planid = new JLabel("Plan ID :");
        planid.setBounds(400, 180, 400, 50);
        planid.setFont(new Font("Calibri", Font.ITALIC, 24));
        planid.setForeground(Color.BLACK);
        f.add(planid);
        planid1 = new JTextField();
        planid1.setBounds(675, 180, 45, 25);
        planid1.setFont(new Font("Calibri", Font.PLAIN, 18));
        planid1.setForeground(Color.BLACK);
        planid1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0' && c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    f.getToolkit().beep();
                    e.consume();
                }
            }
        });
        // Listener for the Plan ID field to call the checkPlanID() method after user finished typing  and to display the error message if the Plan ID is not present
        // Key Released is used to check the Plan ID after the user finished typing
        planid1.addKeyListener(new KeyAdapter()
        {
            public void keyReleased(KeyEvent e)
            {
                if (checkPlanID(planid1.getText()))
                {
                    // Border the Plan ID field with a green border
                    planid1.setBorder(BorderFactory.createLineBorder(Color.green));
                } else
                {
                    planid1.setText("");
                    planid1.setBorder(BorderFactory.createLineBorder(Color.red));
                    // Display Dialog Box with the error message that plan ID is not present!
                    JOptionPane.showMessageDialog(null, "Plan ID is not present!", "Error", JOptionPane.ERROR_MESSAGE);
                    planid1.requestFocus();
                }
            }
        });
        f.add(planid1);

        //Create a  JLabel with the text "Exercise Name"
        exname = new JLabel("Exercise Name : ");
        exname.setBounds(400, 230, 400, 50);
        exname.setFont(new Font("Calibri", Font.ITALIC, 24));
        exname.setForeground(Color.BLACK);
        f.add(exname);
        exname1 = new JTextField();
        exname1.setBounds(675, 230, 200, 25);
        exname1.setFont(new Font("Calibri", Font.PLAIN, 18));
        exname1.setForeground(Color.BLACK);
        exname1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    f.getToolkit().beep();
                    e.consume();
                }
            }
        });
        f.add(exname1);

        // Create a  JLabel with the text "Counts"
        counts = new JLabel("Counts :");
        counts.setBounds(400, 280, 400, 50);
        counts.setFont(new Font("Calibri", Font.ITALIC, 24));
        counts.setForeground(Color.BLACK);
        f.add(counts);
        counts1 = new JTextField();
        counts1.setBounds(675, 280, 40, 25);
        counts1.setFont(new Font("Calibri", Font.PLAIN, 18));
        counts1.setForeground(Color.BLACK);
        counts1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0' && c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    f.getToolkit().beep();
                    e.consume();
                }
            }
        });
        f.add(counts1);

        // Create a  JLabel with the text "Comments"
        comments = new JLabel("Comments :");
        comments.setBounds(400, 330, 200, 50);
        comments.setFont(new Font("Calibri", Font.ITALIC, 24));
        comments.setForeground(Color.BLACK);
        f.add(comments);
        TextArea comments1 = new TextArea("", 90, 90, TextArea.SCROLLBARS_BOTH);
        comments1.setBounds(675, 330, 180, 90);
        comments1.setFont(new Font("Calibri", Font.PLAIN, 18));
        f.add(comments1);


        // Add Listener to the Submit button
        JButton submit = new JButton("Submit");
        submit.setBounds(600, 450, 100, 25);
        f.add(submit);

        submit.addActionListener(e ->
        {
            // Check whether all the fields are filled
            if(sno1.getText().isEmpty() || planid1.getText().isEmpty() || exname1.getText().isEmpty() || counts1.getText().isEmpty())
            {
                // Display Dialog Box with the error message that all the fields are not filled
                JOptionPane.showMessageDialog(null, "All the fields are not filled!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                // Get the values from the fields
                String snot = sno1.getText();
                String planidt = planid1.getText();
                String exnamet = exname1.getText();
                String countst = counts1.getText();
                String commentst = comments1.getText();
                // Connect to the oracle database
                try
                {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
                            "system", "orcl");

                    String query = "INSERT INTO TBLWORKOUT_DETAILS(SNO, PLAN_ID, EXERCISE_NAME, COUNTS, COMMENTS) values('" + snot + "','" + planidt + "','" + exnamet + "','" +
                            countst + "','" + commentst + "')";

                    Statement sta = connection.createStatement();
                    int x = sta.executeUpdate(query);
                    if (x > 0) {
                        JOptionPane.showMessageDialog(submit, "Workout Details Added Successfully!");
                        submit.setVisible(false);
                        f.setVisible(false);
                        f.dispose();
                    }
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        // Frame Properties
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1600, 800);
        f.setLayout(null);
        f.setVisible(true);
    }
    private boolean checkPlanID(String text)
    {
        // Check wheater the Plan ID is present or not in the tblworkout_plan table
        try
        {
            // Create a connection to the oracle database
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tblworkout_plan where plan_id = '" + text + "'");
            if (rs.next())
                return true;
            else
                return false;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    // Create a function that reads the tbl_workout_details table and gets the last sno and adds 1 to it
    public String getSno()
    {
        int sno = 0;
        try {
            // get the class of oracle driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // create a connection to the database oracle
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");

            PreparedStatement ps = con.prepareStatement("select max(sno) from TBLWORKOUT_DETAILS");
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                sno = rs.getInt(1);
                sno++;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return Integer.toString(sno);
    }
    public static void main(String[] args) {
        new WorkOutDetails();
    }
}
