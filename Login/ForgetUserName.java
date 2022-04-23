package Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;

public class ForgetUserName {
    // Method to check if the emal is valid
    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public ForgetUserName()
    {
        // Create a new frame
        JFrame f =new JFrame("Login Details");
        f.getContentPane().setBackground(new Color(230,230,0));
        f.setBackground(Color.cyan);

        // Create a new Label for the Title
        JLabel title = new JLabel("Forget Username",JLabel.CENTER);
        title.setBounds(500,10,400,50);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        title.setForeground(Color.BLUE);
        f.add(title);

        // Declaring all the labels,text-fields and password fields
        JLabel username;
        JTextField username_field;

        username = new JLabel("Enter Email: *");
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
                        JOptionPane.showMessageDialog(null, "Please enter an Username");
                    }
                }
            }});

        // Listener for the username field to allow only 10 characters
        username_field.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                if (username_field.getText().length() >= 30)
                {
                    e.consume();
                }
            }
        });
        f.add(username_field);

        // Submit Button for the recover username form
        JButton submit = new JButton("Submit");
        submit.setBounds(850,168,170,25);
        submit.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(submit);

        // Listener for the submit button to check if the email is in the database
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (username_field.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Please enter an email !");
                    // Set red border to the text field
                    username_field.setBorder(BorderFactory.createLineBorder(Color.red));
                    username_field.requestFocus();
                }
                else
                {
                    // Cheking if the email is valid
                    if(isValidEmailAddress(username_field.getText()))
                    {
                        try
                        {
                            // Connect to the database oracle
                            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
                            // Get the user id from the database
                            // Select the username from the tbllogin table where the user id is the same as the user id from the database
                            String sql ="SELECT a.USER_NAME,b.FIRST_NAME,b.LAST_NAME, b.EMAIL_ID FROM  TBLLOGIN a, TBLUSERS b WHERE b.EMAIL_ID = '"+username_field.getText()+"' AND a.USER_ID = b.USER_ID";
                            Statement sta = con.createStatement();
                            int x = sta.executeUpdate(sql);
                            // Check if the email is in the database
                            if (x > 0)
                            {
                                // Get the username from the database and call the Mail class to send the username to the email
                                ResultSet rs = sta.executeQuery(sql);
                                while (rs.next())
                                {
                                    String user_name = rs.getString(1);
                                    String first_name = rs.getString(2);
                                    String last_name = rs.getString(3);
                                    String full_name = first_name + " " + last_name;
                                    String subject = "Gym Vale - Forget Username";

                                    String message = "\nHi " + full_name + ",\n\nYour Username is : " + user_name + "\n\nIf these changes were made in error, or if you suspect an unauthorised person has requested for your username to be sent to you, " +
                                            "please contact the administrator to let us know.\n\nThank you for using our service.\n\nRegards,\nThe Admin Team,\nTeam Gym Vale\n\nNote: This is a system generated mail, please do not reply.";

                                    Mailer mailer = new Mailer();
                                    mailer.Send_Email(username_field.getText(),subject,message);

                                    // Show the user that the username has been sent to the email
                                    JOptionPane.showMessageDialog(null, "We have sent your username to the email address associated with \n" +
                                            "your account.If you cannot find this email, please check your spam\n" +
                                            "folder. If you still cannot find it, please contact the administrator.");
                                }
                                // Set the username field to false visible
                                username_field.setVisible(false);
                                // Set the submit button to false visible
                                submit.setVisible(false);
                                //Set the username label to false visible
                                username.setVisible(false);
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "An Account with the specified Email is not found!");
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
                        // Show that the email format is invalid
                        JOptionPane.showMessageDialog(null, "Email is not formatted properly ! Please retype with the correct email format.");
                        // Set the username field to blank
                        username_field.setText("");
                        // Set the focus to the username field
                        username_field.requestFocus();
                        //Set red border to the text field
                        username_field.setBorder(BorderFactory.createLineBorder(Color.red));
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
        new ForgetUserName();
    }
}
