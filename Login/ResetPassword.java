package Login;

import Member.UserDashBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import static Login.GetLoginInfo.calculatePasswordStrength;
import static javax.swing.JOptionPane.*;

public class ResetPassword
{
    // Method to generate a verification code
    public static String generateVerificationCode()
    {
        String verificationCode = "";
        for (int i = 0; i < 6; i++)
        {
            int randomNumber = (int) (Math.random() * 10);
            verificationCode += randomNumber;
        }
        return verificationCode;
    }

    // Function that finds the email address from the database through the username
public static String findEmail(String username)
    {
        try
        {
            // Connecting to the database
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
            // Select username and password from tbllogin
            String sql = "SELECT a.USER_NAME,b.EMAIL_ID  FROM TBLUSERS b,TBLLOGIN a WHERE USER_NAME = '" + username + "' AND a.USER_ID = b.USER_ID";
            Statement sta = con.createStatement();
            int x = sta.executeUpdate(sql);
            // Check if the email is in the database
            if (x > 0)
            {
                // Get the username from the database and call the Mail class to send the username to the email
                ResultSet rs = sta.executeQuery(sql);
                while (rs.next())
                {
                    String email = rs.getString(2);
                    return email;
                }
            }
            else
                showMessageDialog(null, "The account you entered is not in the database!");
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }
        return null;
    }

    public ResetPassword()
    {
    // Create a new frame
    JFrame f =new JFrame("Login Details");
    // Set the backgound of the frame to #0286DE
    f.getContentPane().setBackground(new Color(0, 134, 222));

        f.setBackground(Color.cyan);

        // Create a panel on the top of the frame that contains a text label with  UserPortal
        JPanel topPanel = new JPanel();
        topPanel.setBounds(0, 0, 1600, 50);
        // Set the background color of the panel to #0286DE
        topPanel.setBackground(new Color(132,222,2));
        JLabel topLabel = new JLabel("Reset Password");
        // Set the font colour of User Portal to #7E014C
        topLabel.setForeground(new Color(126, 1, 76));
        topLabel.setFont(new Font("Serif", Font.BOLD, 24));
        topPanel.add(topLabel);
        f.add(topPanel);

    // Declaring all the labels,text-fields and password fields
    JLabel username, password, password_confirm;
    JTextField username_field;
    JPasswordField passwordField, passwordField1,oldpassword_field;


    username = new JLabel("Enter Username: *");
        username.setBounds(400, 150, 170, 80);
        username.setFont(new Font("Serif", Font.BOLD, 18));;
        // Set the font colour of username to #5A02DE
        username.setForeground(Color.blue);
        f.add(username);
        username_field = new JTextField();
        username_field.setBounds(600, 173, 200, 25);
        username_field.setBorder(BorderFactory.createLineBorder(Color.black));
        username_field.setFont(new Font("Serif", Font.PLAIN,18));

    // KeyListener for checking if username is entered on pressing the check button
        username_field.addKeyListener(new KeyAdapter() {
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (username_field.getText().length() == 0) {
                showMessageDialog(null, "Please enter a username");
            }
        }
    }});

    // Listener for the username field to allow only 10 characters
        username_field.addKeyListener(new KeyAdapter()
{
    public void keyTyped(KeyEvent e)
    {
        if (username_field.getText().length() >= 15)
        {
            e.consume();
        }
    }
});
        f.add(username_field);

    // Create label with "enter Strong Password Constraints" left side of the password label
    JLabel password_constraints = new JLabel("  Use a variety of characters including letters, numbers, symbols, and upper and lower case. ");
        password_constraints.setBounds(100, 70, 800, 80);
        password_constraints.setFont(new Font("Serif", Font.BOLD, 18));
    // Add colour to the label to #028A0F
        password_constraints.setForeground(new Color(2, 138, 15));
    // Add surrounding border to the label only sourrounding the text
        password_constraints.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        password_constraints.setVisible(false);
        f.add(password_constraints);


    password = new JLabel("Enter New Password: *");
        password.setBounds(400, 250, 200, 80);
        password.setFont(new Font("Serif", Font.BOLD, 18));
        // Set the font colour of password to #5A02DE
        password.setForeground(Color.blue);
        f.add(password);

    passwordField = new JPasswordField();
    // Set the text colour to #ec3b83
        passwordField.setForeground(new Color(236, 59, 131));
        passwordField.setBounds(600, 273, 200, 25);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.black));
        passwordField.setFont(new Font("Serif", Font.PLAIN,18));
    // Display the dialog box if the length of the password is less than 10
        passwordField.addKeyListener(new KeyAdapter() {
    public void keyTyped(KeyEvent e) {
        if (String.valueOf(passwordField.getPassword()).length() >= 30)
            e.consume();
    }
});

    // Code to display the password strength on a ProgressBar
    JProgressBar progressBar = new JProgressBar();
        progressBar.setBounds(200, 225, 100, 20);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(2, 138, 15));
        progressBar.setValue(0);
        progressBar.setString("");
        progressBar.setVisible(false);
        f.add(progressBar);

    //JLabel to display Password Strength
    JLabel password_strength = new JLabel("Password Strength: ");
        password_strength.setBounds(20, 225, 150, 20);
        password_strength.setFont(new Font("Calibri", Font.ITALIC, 18));
        password_strength.setVisible(false);
        f.add(password_strength);

    // Call the method to check the strength of the password
        passwordField.addKeyListener(new KeyAdapter() {
    public void keyTyped(KeyEvent e) {
        progressBar.setVisible(true);
        password_strength.setVisible(true);
        calculatePasswordStrength(String.valueOf(passwordField.getPassword()), progressBar);
    }
});

    // When the user focuses on the password field, display the password_constraints label
        passwordField.addFocusListener(new FocusAdapter() {
    public void focusGained(FocusEvent e) {
        password_constraints.setVisible(true);
    }
    public void focusLost(FocusEvent e) {
        password_constraints.setVisible(false);
    }
});
        f.add(passwordField);

    password_confirm= new JLabel("Confirm New Password: *");
        password_confirm.setBounds(400,300,200,80);
        password_confirm.setFont(new Font("Serif", Font.BOLD, 18));
        // Set the font colour of password_confirm to #5A02DE
        password_confirm.setForeground(Color.blue);
        f.add(password_confirm);
    passwordField1 = new JPasswordField();
        passwordField1.setBounds(600,323,200,25);
        passwordField1.setBorder(BorderFactory.createLineBorder(Color.black));
        passwordField1.setFont(new Font("Serif", Font.PLAIN,18));
        passwordField1.addKeyListener(new KeyAdapter() {
    public void keyTyped(KeyEvent e) {
        if (String.valueOf(passwordField1.getPassword()).length() >= 30)
            e.consume();
        // if password field is in focus
        if (passwordField1.isFocusOwner()) {
            progressBar.setVisible(false);
            password_strength.setVisible(false);
        }
    }
});

    // Create a label for the error message that passwords doesn't match
    JLabel error = new JLabel("Password fields do not match",JLabel.CENTER);
        error.setBounds(800,262,400,50);
        error.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        error.setForeground(Color.RED);
        error.setVisible(false);
        f.add(error);

    // KeyListener to check if the password and confirm password are same
        passwordField1.addKeyListener(new KeyAdapter() {
    public void keyReleased(KeyEvent e) {
        // Check if the focus is on the confirm password field
        if (passwordField1.isFocusOwner()) {
            // Check if the password and confirm password are same
            if (String.valueOf(passwordField.getPassword()).equals(String.valueOf(passwordField1.getPassword())))
                // If they are same, then dont display the error message
                error.setVisible(false);
            else
                // If they are not same, then display the error message
                error.setVisible(true);
        }
    }
});
        f.add(passwordField1);

    // When the userfield is on focus , then display the username_label
        username_field.addFocusListener(new FocusAdapter() {
    public void focusGained(FocusEvent e) {
        passwordField.setText("");
        passwordField1.setText("");
    }
});

    JLabel RoleID = new JLabel("Old Password: *");
        RoleID.setBounds(400,200,200,80);
        RoleID.setFont(new Font("Serif", Font.BOLD, 18));
        // Set the text colour to blue;
        RoleID.setForeground(Color.blue);
        f.add(RoleID);

    oldpassword_field = new JPasswordField();
    oldpassword_field.setBounds(600,223,200,25);
    oldpassword_field.setBorder(BorderFactory.createLineBorder(Color.black));
    // Set the font to Serif
    oldpassword_field.setFont(new Font("Serif", Font.PLAIN, 18));
    f.add(oldpassword_field);

    // To add a button to display password when the user clicks on it
    JButton show_password = new JButton("Show Password");
        show_password.setBounds(850, 223, 150, 25);
        show_password.setBackground(new Color(95, 179, 206));
        show_password.setForeground(Color.blue);
        show_password.setFont(new Font("Serif", Font.BOLD, 18));
        // Add white border to the button
        show_password.setBorder(BorderFactory.createLineBorder(Color.black));

    // When the user clicks on the button, then display the password
        show_password.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        if(String.valueOf(oldpassword_field.getPassword()).length()==0)
        {
            showMessageDialog(null, "Please enter the password");
        }
        passwordField.setEchoChar((char)0);
        passwordField1.setEchoChar((char)0);
        oldpassword_field.setEchoChar((char)0);
    }
});
        f.add(show_password);

        // By Default its false
        passwordField.setVisible(false);
        passwordField1.setVisible(false);
        password.setVisible(false);
        password_confirm.setVisible(false);

    JButton Check = new JButton("Verify Account");
        Check.setBounds(850,168,170,25);
        Check.setBackground(new Color(95, 179, 206));
        Check.setForeground(Color.blue);
        Check.setFont(new Font("Serif", Font.BOLD, 18));
        // Add white border to the button
        Check.setBorder(BorderFactory.createLineBorder(Color.black));
        f.add(Check);
    // Add a KeyListener to the button to check if the username and his old password are correct
        Check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                // if the username is empty then display the error message on dialog box
                if (username_field.getText().equals("") || (String.valueOf(oldpassword_field.getPassword()).equals("")))
                {
                    showMessageDialog(f, "One or more fields is empty !", "Error", ERROR_MESSAGE);
                    if(username_field.getText().equals(""))
                        username_field.requestFocus();
                    else
                        oldpassword_field.requestFocus();
                }
                else
                {

                    try {
                        // Connect to the ORACLE database
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
                        String sql = "SELECT * FROM TBLLOGIN WHERE USER_NAME = ? AND PASSWORD = ?";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1, username_field.getText());
                        ps.setString(2, String.valueOf(oldpassword_field.getPassword()));
                        ResultSet rs = ps.executeQuery();

                        if (rs.next())
                        {
                            try
                            {
                                // Connect to the database oracle
                                Connection con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
                                // Select the username from the tbllogin table where the user id is the same as the user id from the database
                                String sql1 ="SELECT a.PASSWORD,b.FIRST_NAME,b.LAST_NAME, b.EMAIL_ID,a.USER_NAME FROM  TBLLOGIN a, TBLUSERS b WHERE a.USER_NAME = '"+username_field.getText()+"' AND a.USER_ID = b.USER_ID";
                                Statement sta = con1.createStatement();
                                int x = sta.executeUpdate(sql1);

                                // Check if the email is in the database
                                if (x > 0)
                                {
                                    // Get the username from the database and call the Mail class to send the username to the email
                                    ResultSet rs1 = sta.executeQuery(sql1);
                                    while (rs1.next())
                                    {
                                        showMessageDialog(null, "Account Verified Successfully");

                                        // Call the generateVerificationCode method to generate a verification code
                                        String first_name = rs1.getString(2);
                                        String last_name = rs1.getString(3);
                                        String full_name = first_name + " " + last_name;
                                        String email = rs1.getString(4);
                                        String subject = "Gym Vale - Confirm your account";
                                        String verification_code = generateVerificationCode();

                                        showMessageDialog(null, "The verification code is being generated... Please proceed to the next step...");

                                        String message = "\nHey " + full_name + "," +
                                                "\n\nSomebody(hopefully you) requested to set a new password for the Gym Vale account for " + email + "" +
                                                " . No changes have been made to your account yet." +
                                                " \n\nYou can reset your password by using the following code : " + verification_code +
                                                "\n\n We'll be here to help you with any step along the way. " +
                                                "\n\nIf you did not request a new password, please let us know immediately by replying to this email. " +
                                                "\n\nThank you for using our service.\n\nRegards,\nThe Admin Team,\nTeam Gym Vale";

                                        // Call the sendMail method to send the email
                                        Mailer mailer = new Mailer();
                                        mailer.Send_Email(email,subject,message);

                                        // Show the user that the username has been sent to the email
                                        showMessageDialog(null, "We have sent an Verification Code to the email address associated with \n" +
                                                "your account.If you cannot find this email, please check your spam\n" +
                                                "folder. If you still cannot find it, please contact the administrator.");

                                        // Prompt a dialog box asking the user to enter the verification code
                                        String verification_code_input = showInputDialog("Please enter the verification code sent to your email address : ");
                                        // Check if the verification code is correct
                                        if (verification_code_input.equals(verification_code))
                                        {
                                            // If the verification code is correct, then show the password
                                            passwordField.setVisible(true);
                                            passwordField1.setVisible(true);
                                            password.setVisible(true);
                                            password_confirm.setVisible(true);
                                            // Set the focus on the password field
                                            passwordField.requestFocus();
                                            // Hide the check button
                                            Check.setVisible(false);
                                            // Make the username field uneditable
                                            username_field.setEditable(false);
                                            // Make the old password field uneditable
                                            oldpassword_field.setEditable(false);
                                        }
                                        else
                                            // If the verification code is incorrect, then show the error message
                                            showMessageDialog(null, "The verification code you entered is incorrect. Please try again by clicking  the verify button again.");
                                    }
                                }
                                else
                                {
                                    showMessageDialog(null, "Verification code could not be generated. Please try again later.");
                                }
                            }
                            catch (Exception ex) {
                                showMessageDialog(null, ex);
                            }
                        }
                        else
                        {
                            showMessageDialog(f, "Incorrect Credentials !", "Error", ERROR_MESSAGE);
                            oldpassword_field.setText("");
                            oldpassword_field.requestFocus();
                        }

                        } catch (SQLException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                        }
                }
                }
            });

    JButton Register = new JButton("Change Password");
        Register.setBounds(500,390,200,25);
        Register.setBackground(new Color(95, 179, 206));
        Register.setForeground(Color.blue);
        Register.setFont(new Font("Serif", Font.BOLD, 18));
        // Add white border to the button
        Register.setBorder(BorderFactory.createLineBorder(Color.black));
        f.add(Register);
        Register.addActionListener(e ->
    {
        // Create a method to add all the data from the fields to the table TBLLOGIN in the database
        String Username = username_field.getText();
        String passText = new String(passwordField.getPassword());
        String passText_confirm = new String(passwordField1.getPassword());
        String oldpassword1 = new String(oldpassword_field.getPassword());

        if (Username.trim().equals("") || passText.trim().equals("") || passText_confirm.trim().equals("") || oldpassword1.trim().equals(""))
            showMessageDialog(null, "One Or More Fields Are Empty", "Empty Fields", 2);

        // If the fields are filled then check if the password and confirm password are the same
        if (!passText.equals(passText_confirm)) {
            showMessageDialog(null, "Password Doesn't Match", "Confirm Password", 2);
            passwordField.setText("");
            passwordField1.setText("");
            passwordField.requestFocus();
        }

        // Check if the new password is the same as the old password
        if(passText.equals(oldpassword1) && passText_confirm.equals(oldpassword1)) {
            showMessageDialog(null, "Old Password and New Password Cannot be the same", "Old Password", 2);
            passwordField.setText("");
            passwordField1.setText("");
            passwordField.requestFocus();
        }

        if(passText.equals(passText_confirm) && !passText.equals(oldpassword1) && !passText_confirm.equals(oldpassword1))
        {
            // Connect to the database and update the password
            try {
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
                String sql = "UPDATE TBLLOGIN SET PASSWORD = '" + passText + "' WHERE USER_NAME = '" + Username + "'";
                Statement statement = connection.createStatement();
                int x = statement.executeUpdate(sql);
                if(x >0)
                {
                    // showMessageDialog(null, "Password Changed Successfully", "Password Changed", 1);
                    Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
                    // Select the username from the tbllogin table where the user id is the same as the user id from the database
                    String sql2 ="SELECT a.USER_NAME,a.PASSWORD,b.FIRST_NAME,b.LAST_NAME, b.EMAIL_ID FROM  TBLLOGIN a, TBLUSERS b WHERE a.USER_NAME = '"+Username+"' AND a.USER_ID = b.USER_ID";
                    Statement sta = con2.createStatement();
                    int y = sta.executeUpdate(sql2);

                    // Check if the email is in the database
                    if (y > 0)
                    {
                        // Get the username from the database and call the Mail class to send the username to the email
                        ResultSet rs2 = sta.executeQuery(sql2);
                        while (rs2.next())
                        {
                            // Call the generateVerificationCode method to generate a verification code
                            String UserName = rs2.getString(1);
                            String first_name = rs2.getString(3);
                            String last_name = rs2.getString(4);
                            String full_name = first_name + " " + last_name;
                            String email = rs2.getString(5);
                            String subject = "Gym Vale - Your password has been changed!";

                            String message = "\nHi " + full_name + ",\n\nThis email confirms that your password has been changed." +
                                    "\n\nTo log in to the Gym Vale Application, use the following credentials :" +
                                    " \n\nUsername : " +  rs2.getString(1) +"\nPassword : " + rs2.getString(2) +
                                    "\n\nIf you have any questions or encounter any problems on logging into the application," +
                                    " please contact the administrator.\n\nThank you,\nGym Vale Team ";
                            // Call the sendMail method to send the email
                            Mailer mailer = new Mailer();
                            mailer.Send_Email(email,subject,message);
                            // Show the dialog box to the user that the password has been changed
                            showMessageDialog(null, "Your password has been changed successfully.");
                            // Set all the fields to setEditable to false
                            username_field.setEditable(false);
                            passwordField.setEditable(false);
                            passwordField1.setEditable(false);
                            oldpassword_field.setEditable(false);
                            Register.setVisible(false);
                        }
                    }
                }
                else
                    showMessageDialog(null, "Password could not be changed. Please try again later.");
                connection.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        else
            showMessageDialog(null, "Password could not be changed!!", "Password could not be changed", 2);
    });

    JButton cancel = new JButton("Exit");
        cancel.setBounds(740,390,100,30);
        cancel.setBackground(new Color(95, 179, 206));
        cancel.setForeground(Color.blue);
        cancel.setFont(new Font("Serif", Font.BOLD, 18));
        // Add white border to the button
        cancel.setBorder(BorderFactory.createLineBorder(Color.black));
        // To create a dialog box with yes or no options for the cancel button
        cancel.addActionListener(e -> {
    int dialogButton = YES_NO_OPTION;
    int dialogResult = showConfirmDialog(null, "Are you sure you want to exit from the application ?", "Warning", dialogButton);
    if (dialogResult == YES_OPTION) {
        f.dispose();
    }
});
    // Change cancel button style
        cancel.setBackground(Color.WHITE);
        cancel.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
        cancel.setFont(new Font("Arial", Font.BOLD, 12));
    // Submit Button Text Color
        cancel.setForeground(Color.blue);
        f.add(cancel);

    // Creating the button to go back to the previous frame
    JButton back = new JButton("Main Page");
        back.setBounds(320,390,100,30);
        back.setForeground(Color.blue);
        back.setFont(new Font("Serif", Font.BOLD, 18));
        // Add white border to the button
        back.setBorder(BorderFactory.createLineBorder(Color.black));
    // To move to previous page
        back.addActionListener(e -> {
        f.dispose();
                    UserDashBoard ob = new UserDashBoard();
                    ob.main(null);}
        );
        f.add(back);

    JLabel label = new JLabel();
        f.add(label);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1600, 800);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new ResetPassword();
    }
}

