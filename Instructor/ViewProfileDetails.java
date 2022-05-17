package Instructor;

import Login.Login;
import Member.UserDashBoard;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewProfileDetails
{
    public ViewProfileDetails()
    {
        JFrame f = new JFrame("Instructor Profile");
        // Set the background color of the frame to #66FF99
        f.getContentPane().setBackground(new Color(102, 255, 153));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the size of the frame automatically to fit the content
        f.setSize(1600, 800);
        f.setLayout(null);
        f.setVisible(true);

        // Create a panel on the top of the frame that contains a text label with  UserPortal
        JPanel topPanel = new JPanel();
        topPanel.setBounds(0, 0, 1600, 50);
        topPanel.setBackground(new Color(255, 153, 102));
        JLabel topLabel = new JLabel("Instructor Profile");
        // Set the font colour of User Portal to #5C0AFF
        topLabel.setForeground(new Color(92, 10, 255));
        topLabel.setFont(new Font("Serif", Font.BOLD, 24));
        topPanel.add(topLabel);
        f.add(topPanel);

        String username = Login.getUserName();
        String phone = findPhoneNumber(username);
        // Call the findName method to get the details of the user
        String[] name = findDetails(username);
        // Get the full name of the user
        String full_Name = name[0];
        // Get the password of the user
        String password = name[1];
        // Get the email of the user
        String email = name[2];
        // Get the full address of the user
        String address = name[3];
        // Get the gender of the user
        String gender = name[4];
        // Get the age of the user
        String age = name[5];

        // Add a text label with the Full Name of the user
        JLabel fullNameLabel = new JLabel("Full Name: ");
        fullNameLabel.setBounds(400, 55, 100, 50);
        fullNameLabel.setFont(new Font("Serif", Font.BOLD, 20));
        // Set the font colour of the text label to #9966FF
        fullNameLabel.setForeground(new Color(153, 102, 255));
        f.add(fullNameLabel);
        JLabel fullName = new JLabel(full_Name);
        fullName.setBounds(550, 55, 400, 50);
        fullName.setFont(new Font("Serif", Font.BOLD, 20));
        // Set the font colour of the text label to #100c08
        fullName.setForeground(new Color(16, 12, 8));
        f.add(fullName);

        // Add a text label with the Name of the user
        JLabel nameLabel = new JLabel("User Name: ");
        nameLabel.setBounds(400, 100, 150, 30);
        nameLabel.setFont(new Font("Serif", Font.BOLD, 20));
        // Set the font colour of the text label to #9966FF
        nameLabel.setForeground(new Color(153, 102, 255));
        f.add(nameLabel);
        JLabel nameText = new JLabel(username);
        nameText.setBounds(550, 100,400 , 30);
        nameText.setFont(new Font("Serif", Font.BOLD, 20));
        // Set the font colour of the text label to #100c08
        nameText.setForeground(new Color(16, 12, 8));
        f.add(nameText);

        // Add a text label with the Password of the user
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(400, 135, 150, 30);
        passwordLabel.setFont(new Font("Serif", Font.BOLD, 20));
        // Set the font colour of the text label to #9966FF
        passwordLabel.setForeground(new Color(153, 102, 255));
        f.add(passwordLabel);
        // Add a text field that will display the password of the user at the right of the label
        JLabel passwordText = new JLabel(password);
        passwordText.setBounds(550, 135, 400, 30);
        passwordText.setFont(new Font("Serif", Font.BOLD, 20));
        // Set the font colour of the text label to #100c08
        passwordText.setForeground(new Color(16, 12, 8));
        f.add(passwordText);

        // Add a text label with the Email of the user
        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setBounds(400, 170, 150, 30);
        emailLabel.setFont(new Font("Serif", Font.BOLD, 20));
        emailLabel.setVisible(true);
        // Set the font colour of the text label to #9966FF
        emailLabel.setForeground(new Color(153, 102, 255));
        f.add(emailLabel);
        JLabel emailText = new JLabel(email);
        emailText.setBounds(550, 170, 400, 30);
        emailText.setFont(new Font("Serif", Font.BOLD, 20));
        emailText.setVisible(true);
        // Set the font colour of the text label to #100c08
        emailText.setForeground(new Color(16, 12, 8));
        f.add(emailText);


        // Add a text label with the Phone Number of the user
        JLabel phoneLabel = new JLabel("Phone Number: ");
        phoneLabel.setBounds(400, 205, 150, 30);
        phoneLabel.setFont(new Font("Serif", Font.BOLD, 20));
        phoneLabel.setVisible(true);
        // Set the font colour of the text label to #9966FF
        phoneLabel.setForeground(new Color(153, 102, 255));
        f.add(phoneLabel);
        // Add a text field that will display the phone number of the user at the right of the label
        JLabel phoneText = new JLabel(phone);
        phoneText.setBounds(550, 205, 400, 30);
        phoneText.setFont(new Font("Serif", Font.BOLD, 20));
        phoneText.setVisible(true);
        // Set the font colour of the text label to #100c08
        phoneText.setForeground(new Color(16, 12, 8));
        f.add(phoneText);

        // Add a text label with the Address of the user
        JLabel addressLabel = new JLabel("Address: ");
        addressLabel.setBounds(400, 240, 150, 30);
        addressLabel.setFont(new Font("Serif", Font.BOLD, 20));
        addressLabel.setVisible(true);
        // Set the font colour of the text label to #9966FF
        addressLabel.setForeground(new Color(153, 102, 255));
        f.add(addressLabel);
        // Add a text field that will display the address of the user at the right of the label
        JLabel addressText = new JLabel(address);
        addressText.setBounds(550, 240, 600, 30);
        addressText.setFont(new Font("Serif", Font.BOLD, 20));
        addressText.setVisible(true);
        // Set the font colour of the text label to #100c08
        addressText.setForeground(new Color(16, 12, 8));
        f.add(addressText);

        // Add a text label with the Gender of the user
        JLabel genderLabel = new JLabel("Gender: ");
        genderLabel.setBounds(400, 275, 150, 30);
        genderLabel.setFont(new Font("Serif", Font.BOLD, 20));
        genderLabel.setVisible(true);
        // Set the font colour of the text label to #9966FF
        genderLabel.setForeground(new Color(153, 102, 255));
        f.add(genderLabel);
        // Add a label that will display the gender of the user
        JLabel genderText = new JLabel(gender);
        genderText.setBounds(550, 275, 400, 30);
        genderText.setFont(new Font("Serif", Font.BOLD, 20));
        genderText.setVisible(true);
        // Set the font colour of the text label to #100c08
        genderText.setForeground(new Color(16, 12, 8));
        f.add(genderText);

        // Add a text label with the Age of the user
        JLabel ageLabel = new JLabel("Age: ");
        ageLabel.setBounds(400, 305, 150, 30);
        ageLabel.setFont(new Font("Serif", Font.BOLD, 20));
        ageLabel.setVisible(true);
        // Set the font colour of the text label to #9966FF
        ageLabel.setForeground(new Color(153, 102, 255));
        f.add(ageLabel);
        // Add a label that will display the age of the user
        JLabel ageText = new JLabel(age);
        ageText.setBounds(550, 305, 400, 30);
        ageText.setFont(new Font("Serif", Font.BOLD, 20));
        ageText.setVisible(true);
        // Set the font colour of the text label to #100c08
        ageText.setForeground(new Color(16, 12, 8));
        f.add(ageText);

        // Add a button that will allow the user to go back to the Main Page
        JButton backButton = new JButton("Main Page");
        backButton.setBounds(450, 400, 100, 50);
        backButton.setFont(new Font("Serif", Font.BOLD, 20));
        backButton.setBackground(new Color(255, 153, 102));
        backButton.setForeground(Color.blue);
        // Add white border to the button
        backButton.setBorder(BorderFactory.createLineBorder(Color.black));
        backButton.setVisible(true);
        backButton.addActionListener(e ->
        {
            // Close the current frame
            // Open the Main Page
            InstructorDashBoard ob = new InstructorDashBoard();
            f.dispose();
            ob.main(null);
        });
        f.add(backButton);

        // Add a button that will allow the user to  exit the application
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(650, 400, 100, 50);
        exitButton.setFont(new Font("Serif", Font.BOLD, 20));
        exitButton.setBackground(new Color(255, 153, 102));
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

    private static String[] findDetails(String userName)
    {
        String fullName = "";
        String password = "";
        String email = "";
        String address = "";
        String gender = "";
        String age = "";
        try
        {
            // Create a connection to the database
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
            // Create a statement to execute the query
            Statement stmt = con.createStatement();
            // Create a query to find the password,email,phone number,address,gender and age by joining the TBLLOGIN and TBLUSERS table
            String query = "SELECT TBLUSERS.FIRST_NAME,TBLUSERS.MIDDLE_NAME,TBLUSERS.LAST_NAME,TBLINSTRUCTOR.PASSWORD, tblusers.EMAIL_ID, TBLUSERS.DOOR_NO,TBLUSERS.STR_NAME,TBLUSERS.CITY,TBLUSERS.PINCODE,TBLUSERS.STATE,tblusers.GENDER, tblusers.AGE FROM TBLUSERS INNER JOIN TBLINSTRUCTOR ON tblusers.USER_ID = TBLINSTRUCTOR.USER_ID WHERE TBLINSTRUCTOR.USER_NAME = '" + userName + "'";
            // Execute the query
            ResultSet rs = stmt.executeQuery(query);
            // Get the First Name , Middle Name , Last Name  of the user by the User Name
            while (rs.next())
            {
                fullName = rs.getString("FIRST_NAME") + " " + rs.getString("MIDDLE_NAME") + " " + rs.getString("LAST_NAME");
                password = rs.getString("PASSWORD");
                email = rs.getString("EMAIL_ID");
                address = rs.getString("DOOR_NO") + "," + rs.getString("STR_NAME") + "," + rs.getString("CITY") + "," + rs.getString("PINCODE") + "," + rs.getString("STATE");
                gender = rs.getString("GENDER");
                age = rs.getString("AGE");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // Return the user's details as a single string
        return new String[]
                {
                        fullName, password, email, address,gender,age
                };
    }

    // Function to find the phone number of the User
    private static String findPhoneNumber(String userName)
    {
        String phone = "";
        try
        {
            // Create a connection to the database
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
            // Create a statement to execute the query
            Statement stmt = con.createStatement();
            // Create a query to retrieve the user phone number by joining the TBLUSER_PHONE_NUMBER and TBLUSERS table
            String query = "SELECT TBLUSER_PHONE_NUMBER.PHONE_NUMBER FROM TBLUSER_PHONE_NUMBER INNER JOIN TBLINSTRUCTOR ON TBLUSER_PHONE_NUMBER.USER_ID = TBLINSTRUCTOR.USER_ID WHERE TBLINSTRUCTOR.USER_NAME = '" + userName + "'";
            // Execute the query
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
            {
                phone = rs.getString("PHONE_NUMBER");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // Return the user's details as a single string
        return phone;
    }

    public static void main(String[] args)
    {
        new Instructor.ViewProfileDetails();
    }

}
