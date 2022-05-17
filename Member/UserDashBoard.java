package Member;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import Login.ResetPassword;
import Login.Login;
import Payment.GetDetails;
public class UserDashBoard
{
    public UserDashBoard() {
        JFrame f = new JFrame("User Dashboard");
        f.getContentPane().setBackground(new Color(173, 216, 230));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1600, 800);
        f.setLayout(null);
        f.setVisible(true);

        // Create a panel on the top of the frame that contains a text label with  UserPortal
        JPanel topPanel = new JPanel();
        topPanel.setBounds(0, 0, 1600, 50);
        // Set the background color of the panel to #00308f
        topPanel.setBackground(new Color(0, 48, 143));
        JLabel topLabel = new JLabel("User Portal");
        // Set the font colour of User Portal to white
        topLabel.setForeground(Color.white);
        topLabel.setFont(new Font("Serif", Font.BOLD, 24));
        topPanel.add(topLabel);
        f.add(topPanel);

        // Create a button on the left of the frame that contains a text label with  View WorkOut Details without panel
        JButton viewWorkOutButton = new JButton("View WorkOut Details");
        viewWorkOutButton.setBounds(50, 200, 250, 50);
        // Set the background color of the button to #5FB3CE
        viewWorkOutButton.setBackground(new Color(95, 179, 206));
        viewWorkOutButton.setForeground(Color.blue);
        viewWorkOutButton.setFont(new Font("Serif", Font.BOLD, 18));
        // Add white border to the button
        viewWorkOutButton.setBorder(BorderFactory.createLineBorder(Color.black));
        viewWorkOutButton.addActionListener(e ->
        {
            // Close the frame
            f.dispose();
            ViewWorkutDetails ob = new ViewWorkutDetails();
            ob.main(null);
        });
        f.add(viewWorkOutButton);

        // Create a button on the left of the frame that contains a text label with  View Membership Status without panel
        JButton viewMembershipButton = new JButton("View Membership Status");
        viewMembershipButton.setBounds(50, 280, 250, 50);
        viewMembershipButton.setBackground(new Color(95, 179, 206));
        viewMembershipButton.setForeground(Color.blue);
        viewMembershipButton.setFont(new Font("Serif", Font.BOLD, 18));
        // Add white border to the button
        viewMembershipButton.setBorder(BorderFactory.createLineBorder(Color.black));
        viewMembershipButton.addActionListener(e ->
        {
            ViewMembershipDetails ob = new ViewMembershipDetails();
            ob.main(null);
        });
        f.add(viewMembershipButton);

        // Create a button on the left of the frame that contains a text label with  View Profile without panel
        JButton viewProfileButton = new JButton("View Profile");
        viewProfileButton.setBounds(50, 360, 250, 50);
        viewProfileButton.setBackground(new Color(95, 179, 206));
        viewProfileButton.setForeground(Color.blue);
        viewProfileButton.setFont(new Font("Serif", Font.BOLD, 18));
        // Add white border to the button
        viewProfileButton.setBorder(BorderFactory.createLineBorder(Color.black));
        viewProfileButton.addActionListener(e ->
        {
            // Minimize the frame
            f.dispose();
            ViewProfileDetails ob = new ViewProfileDetails();
            ob.main(null);
        });
        // Create a new frame that contains a text label with  View Profile);
        f.add(viewProfileButton);

        // Create a button on the left of the frame that contains a text label with  Change Password without panel
        JButton changePasswordButton = new JButton("Change Password");
        changePasswordButton.setBounds(50, 440, 250, 50);
        changePasswordButton.setBackground(new Color(95, 179, 206));
        changePasswordButton.setForeground(Color.blue);
        changePasswordButton.setFont(new Font("Serif", Font.BOLD, 18));
        // Add white border to the button
        changePasswordButton.setBorder(BorderFactory.createLineBorder(Color.black));
        changePasswordButton.addActionListener
                (e ->
                {
                    // Close the frame
                    f.dispose();
                    ResetPassword ob = new ResetPassword();
                    ob.main(null);
                });
        f.add(changePasswordButton);

        // Create a button on the left of the frame that contains a text label with  Logout without panel
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(50, 520, 250, 50);
        logoutButton.setBackground(new Color(95, 179, 206));
        logoutButton.setForeground(Color.blue);
        logoutButton.setFont(new Font("Serif", Font.BOLD, 18));
        // Add white border to the button
        logoutButton.setBorder(BorderFactory.createLineBorder(Color.black));
        f.add(logoutButton);

        String username = Login.getUserName();

        // Add a welcome message with the username
        JLabel welcomeLabel = new JLabel("Welcome, " + findName(username) + " :-)");
        welcomeLabel.setBounds(680, 100, 400, 50);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 22));
        welcomeLabel.setForeground(Color.blue);
        f.add(welcomeLabel);

        // Add a text label on the center of the frame that contains a text label
        JLabel centerLabel = new JLabel("This Gym Management System is designed with Java , and it should work");
        centerLabel.setBounds(500, 200, 800, 30);
        centerLabel.setFont(new Font("Serif", Font.BOLD, 22));
        f.add(centerLabel);

        JLabel centerLabel2 = new JLabel("on later versions of  Java.");
        centerLabel2.setBounds(680, 230, 500, 30);
        centerLabel2.setFont(new Font("Serif", Font.BOLD, 22));
        f.add(centerLabel2);

        JLabel centerLabel3 = new JLabel("If you have any problem, running the software, please contact:");
        centerLabel3.setBounds(550, 260, 600, 30);
        centerLabel3.setFont(new Font("Serif", Font.BOLD, 22));
        f.add(centerLabel3);

        JLabel centerLabel4 = new JLabel("noreplytest.gymvale@gmail.com");
        centerLabel4.setBounds(650, 290, 500, 30);
        centerLabel4.setFont(new Font("Serif", Font.BOLD, 22));
        f.add(centerLabel4);

        JLabel centerLabel5 = new JLabel("There is no guarantee for this software.Your feedback is welcome");
        centerLabel5.setBounds(500, 380, 700, 30);
        centerLabel5.setFont(new Font("Serif", Font.BOLD, 22));
        f.add(centerLabel5);

        JLabel centerLabel6 = new JLabel("for newer developments of this version.");
        centerLabel6.setBounds(680, 410, 800, 30);
        centerLabel6.setFont(new Font("Serif", Font.BOLD, 22));
        f.add(centerLabel6);

        JLabel centerLabel7 = new JLabel("Thank you for using our software.");
        centerLabel7.setBounds(700, 440, 800, 30);
        centerLabel7.setFont(new Font("Serif", Font.BOLD, 22));
        f.add(centerLabel7);

        JLabel centerLabel8 = new JLabel("Team GymVale");
        centerLabel8.setBounds(780, 470, 150, 30);
        centerLabel8.setFont(new Font("Serif", Font.BOLD, 22));
        f.add(centerLabel8);

        // Add a button with Exit in the bottom right corner of the frame
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(1200, 600, 100, 50);
        exitButton.setBackground(new Color(95, 179, 206));
        exitButton.setForeground(Color.blue);
        exitButton.setFont(new Font("Serif", Font.BOLD, 18));
        // Add white border to the button
        exitButton.setBorder(BorderFactory.createLineBorder(Color.black));
        exitButton.setVisible(true);
        exitButton.addActionListener(e ->
        {
            // Exit the program
            System.exit(0);
        });
        f.add(exitButton);

        // Add a button with Main Page in the bottom left corner of the frame
        JButton mainPageButton = new JButton("Renew Payment");
        mainPageButton.setBounds(50, 600, 250, 50);
        mainPageButton.setBackground(new Color(95, 179, 206));
        mainPageButton.setForeground(Color.blue);
        mainPageButton.setFont(new Font("Serif", Font.BOLD, 18));
        // Add white border to the button
        mainPageButton.setBorder(BorderFactory.createLineBorder(Color.black));
        mainPageButton.setVisible(true);
        mainPageButton.addActionListener(e ->
        {
            // Close the current Frame
            f.dispose();
            // Open the GetDetails class from Payment package
            GetDetails ob = new GetDetails();
            ob.main(null);
        });
        f.add(mainPageButton);
    }


    // Function to find the First Name , Middle Name , Last Name  of the user by the User Name
    public static String findName(String userName)
    {
        String name = "";
        try
        {
            // Create a connection to the database
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
            // Create a statement to execute the query
            Statement stmt = con.createStatement();
            // Create a query to find the First Name , Middle Name , Last Name  of the user by the User Name by joining the tblusers and tbllogin tables
            String query = "SELECT tblusers.FIRST_NAME, tblusers.MIDDLE_NAME, tblusers.LAST_NAME FROM TBLUSERS INNER JOIN TBLLOGIN ON tblusers.USER_ID = tbllogin.USER_ID WHERE tbllogin.USER_NAME = '" + userName + "'";
            // Execute the query
            ResultSet rs = stmt.executeQuery(query);
            // Get the First Name , Middle Name , Last Name  of the user by the User Name
            while (rs.next())
            {
                name = rs.getString("FIRST_NAME") + " " + rs.getString("MIDDLE_NAME") + " " + rs.getString("LAST_NAME");
            }
            // Close the connection
            con.close();
            return name;
             }
        catch (Exception e)
            {
                e.printStackTrace();
            }
        return name;
    }


    public static void main(String[] args) {
        new UserDashBoard();
    }
}

