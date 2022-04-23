package Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import static Login.GetLoginInfo.calculatePasswordStrength;

public class ForgetPassword
{
    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    // Function that will find weather the user exists or not
    public boolean findUser(String username, String email)
    {
        try
        {
            // Connecting to the database
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
            String sql = "SELECT a.PASSWORD,a.USER_NAME,b.EMAIL_ID FROM  TBLLOGIN a, TBLUSERS b WHERE b.EMAIL_ID = '" + email + "' AND a.USER_ID = b.USER_ID";
            Statement sta = con.createStatement();
            int x = sta.executeUpdate(sql);
            // Check if the email is in the database
            if (x > 0)
            {
                // Get the username from the database and call the Mail class to send the username to the email
                ResultSet rs = sta.executeQuery(sql);
                while (rs.next())
                {
                    String user_name = rs.getString(2);
                    String email_id = rs.getString(3);
                    if (user_name.equals(username) && email_id.equals(email))
                        return true;

                    else
                        return false;
                }
            }
            else
                JOptionPane.showMessageDialog(null, "The email you entered is not in the database");
        }
        catch(Exception e)
        {
            System.out.println(e);
            return false;
        }
        return false;
    }
    ForgetPassword()
    {
        // Create a new frame
        JFrame f =new JFrame("Forget Password");
        f.getContentPane().setBackground(new Color(230,230,0));
        f.setBackground(Color.cyan);

        // Create a new Label for the Title
        JLabel title = new JLabel("Forget Password",JLabel.CENTER);
        title.setBounds(500,10,400,50);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        title.setForeground(Color.BLUE);
        f.add(title);

        // Declaring all the labels,text-fields and password fields
        JLabel username, password, email;
        JTextField username_field,email_field;
        JTextField passwordField;


        username = new JLabel("Enter Username: *");
        username.setBounds(400, 150, 170, 80);
        username.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(username);

        username_field = new JTextField();
        username_field.setBounds(600, 173, 200, 25);

        // KeyListener for checking if username is entered on pressing the check button
        username_field.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (username_field.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "Please enter a username");
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


        // Creating the Labels and TextFields for Email
        email = new JLabel("Email ID: *");
        email.setBounds(400,200,120,80);
        email.setFont(new Font("Calibri", Font.ITALIC, 24));
        email_field = new JTextField();
        email_field.setBounds(600,225,200,25);

        // Key Listener for the Email TextField to limit the input to 30 characters
        email_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (email_field.getText().length() >= 30 )
                    e.consume();
            }
        });
        f.add(email);
        f.add(email_field);

        JButton Check = new JButton("Verify Account");
        Check.setBounds(850,168,170,25);
        f.add(Check);
        // Add a KeyListener to the button to check if the username and his old password are correct
        Check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (username_field.getText().length() == 0)
                {
                    JOptionPane.showMessageDialog(null, "Please enter the username !");
                    // Set red border to the text field
                    username_field.setBorder(BorderFactory.createLineBorder(Color.red));
                    username_field.requestFocus();
                }

                if(email_field.getText().length() == 0)
                {
                    JOptionPane.showMessageDialog(null, "Please enter the Email ID!");
                    email_field.setBorder(BorderFactory.createLineBorder(Color.red));
                    email_field.requestFocus();
                }

                else
                {
                    // Cheking if the email is valid
                    if(isValidEmailAddress(email_field.getText()))
                    {
                        if(findUser(username_field.getText(),email_field.getText()))
                        {
                            JOptionPane.showMessageDialog(null, "The username and email are correct");
                            // Set green border to the text field
                            username_field.setBorder(BorderFactory.createLineBorder(Color.green));
                            email_field.setBorder(BorderFactory.createLineBorder(Color.green));

                            try
                            {
                                // Connect to the database oracle
                                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
                                // Select the username from the tbllogin table where the user id is the same as the user id from the database
                                String sql ="SELECT a.PASSWORD,b.FIRST_NAME,b.LAST_NAME, b.EMAIL_ID FROM  TBLLOGIN a, TBLUSERS b WHERE b.EMAIL_ID = '"+email_field.getText()+"' AND a.USER_ID = b.USER_ID";
                                Statement sta = con.createStatement();
                                int x = sta.executeUpdate(sql);
                                // Show a dialog box showing that the password is being retrieved from the database
                                JOptionPane.showMessageDialog(null, "The password is being retrieved from the database. Please wait ..");

                                // Check if the email is in the database
                                if (x > 0)
                                {
                                    // Get the username from the database and call the Mail class to send the username to the email
                                    ResultSet rs = sta.executeQuery(sql);
                                    while (rs.next())
                                    {
                                        String password = rs.getString(1);
                                        String first_name = rs.getString(2);
                                        String last_name = rs.getString(3);
                                        String full_name = first_name + " " + last_name;
                                        String subject = "Gym Vale - Forgot Password";

                                        String message = "\nHi " + full_name + ",\n\nYour Password is : " + password + "\n\nIf these changes were made in error, or if you suspect an unauthorised person has requested for your username to be sent to you, " +
                                                "please contact the administrator to let us know.\n\nThank you for using our service.\n\nRegards,\nThe Admin Team,\nTeam Gym Vale\n\nNote: This is a system generated mail, please do not reply.";

                                        Mailer mailer = new Mailer();
                                        mailer.Send_Email(email_field.getText(),subject, message);

                                        // Show the user that the username has been sent to the email
                                        JOptionPane.showMessageDialog(null, "We have sent your password to the email address associated with \n" +
                                                "your account.If you cannot find this email, please check your spam\n" +
                                                "folder. If you still cannot find it, please contact the administrator.");
                                    }
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(null, "An Account with the specified User Name is not found!");
                                    // Set the username field to blank
                                    username_field.setText("");
                                    // Set the focus to the username field
                                    username_field.requestFocus();
                                }
                            }
                            catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, ex);
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "The username and email are not correct");
                            // Set red border to the text field
                            username_field.setBorder(BorderFactory.createLineBorder(Color.red));
                            email_field.setBorder(BorderFactory.createLineBorder(Color.red));
                        }
                    }
                    else
                    {
                        // Show that the email format is invalid
                        JOptionPane.showMessageDialog(null, "Email is not formatted properly ! Please retype with the correct email format.");
                        // Set the username field to blank
                        email_field.setText("");
                        // Set the focus to the username field
                        email_field.requestFocus();
                        //Set red border to the text field
                        email_field.setBorder(BorderFactory.createLineBorder(Color.red));
                    }
                }
            };
        });


        JButton cancel = new JButton("Exit");
        cancel.setBounds(740,390,100,30);
        // To create a dialog box with yes or no options for the cancel button
        cancel.addActionListener(e -> {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit from the application ?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
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
        JButton back = new JButton("Back");
        back.setBounds(320,390,100,30);
        // To move to previous page
        back.addActionListener(e -> {
                    f.dispose();
                    new Login();
                    Login.main(null);
                }
        );
        // Change back button style
        back.setBackground(Color.WHITE);
        back.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
        back.setFont(new Font("Arial", Font.BOLD, 12));
        // Back Button Text Color
        back.setForeground(Color.blue);
        f.add(back);


        JLabel label = new JLabel();
        f.add(label);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1600, 800);
        f.setLayout(null);
        f.setVisible(true);
    }
    public static void main(String[] args) {
        new ForgetPassword();
    }
}

