package Member;

import Login.Login;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ViewMembershipDetails
{
    public ViewMembershipDetails()
    {
        JFrame f = new JFrame("Membership Details");
        // Set the background color of the frame to #E4BF94
        f.getContentPane().setBackground(new Color(228,191,148));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the size of the frame automatically to fit the content
        f.setSize(1600, 800);
        f.setLayout(null);
        f.setVisible(true);

        // Create a panel on the top of the frame that contains a text label with  UserPortal
        JPanel topPanel = new JPanel();
        topPanel.setBounds(0, 0, 1600, 50);
        topPanel.setBackground(new Color(191,148,228));
        JLabel topLabel = new JLabel("User Membership Info");
        // Set the font colour of User Portal to #5C0AFF
        topLabel.setForeground(new Color(92, 10, 255));
        topLabel.setFont(new Font("Serif", Font.BOLD, 24));
        topPanel.add(topLabel);
        f.add(topPanel);

        String username = Login.getUserName();

        String[] details = findDetails(username);
        // Get the amount, joining date and expiry date of the user from the array
        String amount = details[0];
        String joiningDate = details[1];
        String expiryDate = details[2];

        // Add a text label with the Membership ID of the user
        JLabel membershipIDLabel = new JLabel("Membership ID: ");
        membershipIDLabel.setBounds(400, 105, 150, 50);
        membershipIDLabel.setFont(new Font("Serif", Font.BOLD, 18));
        // Set the font colour of the label to #ff007f
        membershipIDLabel.setForeground(new Color(255,0,127));
        f.add(membershipIDLabel);
        // Add a text label with the Membership ID of the user
        JLabel membershipID = new JLabel(findMembershipID(username));
        membershipID.setBounds(550,105 , 400, 50);
        membershipID.setFont(new Font("Serif", Font.BOLD, 18));
        f.add(membershipID);

        // Add a text label  with the Name of the user
        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(400, 55, 150, 50);
        nameLabel.setFont(new Font("Serif", Font.BOLD, 18));
        // Set the font colour of the label to #ff007f
        nameLabel.setForeground(new Color(255,0,127));
        f.add(nameLabel);
        // Add a text label with the Name of the user
        JLabel name = new JLabel(findName(username));
        name.setBounds(550, 55, 400, 50);
        name.setFont(new Font("Serif", Font.BOLD, 18));
        f.add(name);

        // Add a text label with the Joining Date of the user
        JLabel joiningDateLabel = new JLabel("Joining Date: ");
        joiningDateLabel.setBounds(400, 155, 150, 50);
        joiningDateLabel.setFont(new Font("Serif", Font.BOLD, 18));
        // Set the font colour of the label to #ff007f
        joiningDateLabel.setForeground(new Color(255,0,127));
        f.add(joiningDateLabel);
        // Add a text label with the Joining Date of the user
        JLabel joiningDateText = new JLabel(joiningDate);
        joiningDateText.setBounds(550, 155, 400, 50);
        joiningDateText.setFont(new Font("Serif", Font.BOLD, 18));
        f.add(joiningDateText);

        // Add a text label with the Membership Expiry Date of the user
        JLabel expiryDateLabel = new JLabel("Expiry Date: ");
        expiryDateLabel.setBounds(400, 205, 150, 50);
        expiryDateLabel.setFont(new Font("Serif", Font.BOLD, 18));
        // Set the font colour of the label to #ff007f
        expiryDateLabel.setForeground(new Color(255,0,127));
        f.add(expiryDateLabel);
        // Add a text label with the Membership Expiry Date of the user
        JLabel expiryDateText = new JLabel(expiryDate);
        expiryDateText.setBounds(550, 205, 400, 50);
        expiryDateText.setFont(new Font("Serif", Font.BOLD, 18));
        f.add(expiryDateText);

        // Add a text label with the Amount of the user
        JLabel amountLabel = new JLabel("Amount Paid: ");
        amountLabel.setBounds(400, 255, 150, 50);
        amountLabel.setFont(new Font("Serif", Font.BOLD, 18));
        // Set the font colour of the label to #ff007f
        amountLabel.setForeground(new Color(255,0,127));
        f.add(amountLabel);
        // Add a text label with the Amount of the user
        JLabel amountTextLabel = new JLabel(amount);
        amountTextLabel.setBounds(550, 255, 400, 50);
        amountTextLabel.setFont(new Font("Serif", Font.BOLD, 18));
        f.add(amountTextLabel);

        // Add a button that will allow the user to go back to the Main Page
        JButton backButton = new JButton("Main Page");
        backButton.setBounds(450, 325, 100, 50);
        backButton.setFont(new Font("Serif", Font.BOLD, 20));
        backButton.setBackground(new Color(191,148,228));
        backButton.setForeground(Color.blue);
        // Add white border to the button
        backButton.setBorder(BorderFactory.createLineBorder(Color.black));
        backButton.setVisible(true);
        backButton.addActionListener(e ->
        {
            // Close the current frame
            f.dispose();
            // Open the Main Page
            UserDashBoard ob = new UserDashBoard();
            ob.main(null);
        });
        f.add(backButton);

        // Add a button that will allow the user to  exit the application
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(650, 325, 100, 50);
        exitButton.setFont(new Font("Serif", Font.BOLD, 20));
        exitButton.setBackground(new Color(191,148,228));
        exitButton.setForeground(Color.blue);
        // Add white border to the button
        exitButton.setBorder(BorderFactory.createLineBorder(Color.black));
        exitButton.setVisible(true);
        exitButton.addActionListener(e ->
        {
            // Close the current frame
            f.dispose();
            // Exit the application
            System.exit(0);
        });
        f.add(exitButton);

    }

    // Function to find the Full Name by  User Name
    public static String findName(String userName)
    {
        String fullName = "";

        try
        {
            // Create a connection to the database
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
            // Create a statement to execute the query
            Statement stmt = con.createStatement();
            // Create a query to find the password,email,phone number,address,gender and age by joining the TBLLOGIN and TBLUSERS table
            String query = "SELECT TBLUSERS.FIRST_NAME,TBLUSERS.MIDDLE_NAME,TBLUSERS.LAST_NAME FROM TBLUSERS INNER JOIN TBLLOGIN ON tblusers.USER_ID = tbllogin.USER_ID WHERE tbllogin.USER_NAME = '" + userName + "'";
            // Execute the query
            ResultSet rs = stmt.executeQuery(query);
            // Get the First Name , Middle Name , Last Name  of the user by the User Name
            while (rs.next())
            {
                fullName = rs.getString("FIRST_NAME") + " " + rs.getString("MIDDLE_NAME") + " " + rs.getString("LAST_NAME");
            }
        }
        catch (Exception e)
        {e.printStackTrace();}

        // Return the user's details as a single string
        return fullName;
    }

    // Find the membership ID by User Name
    public static String findMembershipID(String userName)
    {
        String membershipID = "";

        try
        {
            // Create a connection to the database
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
            // Create a statement to execute the query
            Statement stmt = con.createStatement();
            // Create a query to find the membership ID by using the TBLUSERS and the TBLMEMBERSHIP table
            String query = "SELECT TBLMEMBERSHIP.MEMBERSHIP_ID FROM TBLLOGIN INNER JOIN TBLMEMBERSHIP ON TBLLOGIN.USER_ID = TBLMEMBERSHIP.USER_ID WHERE TBLLOGIN.USER_NAME = '" + userName + "'";
            // Execute the query
            ResultSet rs = stmt.executeQuery(query);
            // Get the Membership ID of the user by the User Name
            while (rs.next())
            {
                membershipID = rs.getString("MEMBERSHIP_ID");
            }
        }
        catch (Exception e)
        {e.printStackTrace();}
        return membershipID;
    }

    // Find the Membership Details by connecting to the DB
    public static String[] findDetails(String userName)
    {
        String amount = "";
        String joinDate = "";
        String expiryDate = "";

        try
        {
            // Create a connection to the database
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
            // Create a statement to execute the query
            Statement stmt = con.createStatement();
            // Create a query to find the membershipid,amount,joining date,end date from the TBLMEMBERSHIP table and the TBLUSERS table
            String query = "SELECT TBLMEMBERSHIP.AMOUNT,TBLMEMBERSHIP.JOINING_DATE,TBLMEMBERSHIP.END_DATE FROM TBLLOGIN INNER JOIN TBLMEMBERSHIP ON TBLLOGIN.USER_ID = TBLMEMBERSHIP.USER_ID WHERE TBLLOGIN.USER_NAME = '" + userName + "'";
            // Execute the query
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
            {
                amount = rs.getString("AMOUNT");
                joinDate = rs.getString("JOINING_DATE");
                expiryDate = rs.getString("END_DATE");
            }
        }
        catch (Exception e)
        {e.printStackTrace();}

        // Return the user's details as a single string array
        return new String[]{amount, joinDate, expiryDate};
    }

    public static void main(String[] args)
    {
        new ViewMembershipDetails();
    }
}
