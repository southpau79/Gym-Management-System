package Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import static Login.GetLoginInfo.calculatePasswordStrength;

public class ForgetPassword
{
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


        password = new JLabel("Your Password: ");
        password.setBounds(400, 250, 220, 80);
        password.setFont(new Font("Calibri", Font.ITALIC, 20));
        password.setVisible(false);
        f.add(password);

        passwordField = new JTextField();
        // Set the text colour to #ec3b83
        passwordField.setForeground(new Color(236, 59, 131));
        passwordField.setBounds(600, 275, 200, 25);
        passwordField.setVisible(false);

        // Display the dialog box if the length of the password is less than 10
        passwordField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (passwordField.getText().length() >= 30)
                    e.consume();
            }
        });

        // Call the method to check the strength of the password

        // When the userfield is on focus , then display the username_label
        username_field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                passwordField.setText("");
            }
        });


        // By Default its false
        f.add(passwordField);

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
                // if the username is empty then display the error message on dialog box
                if (username_field.getText().equals("") || email_field.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(f, "One or more fields is empty !", "Error", JOptionPane.ERROR_MESSAGE);
                    if(username_field.getText().equals(""))
                        username_field.requestFocus();
                    else
                        email_field.requestFocus();
                }
                else
                {
                    // Connect with the database and check if the username and password is correct
                    try {
                        // Connect to the ORACLE database
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
                        String sql = "SELECT TBLUSERS.EMAIL_ID,TBLLOGIN.PASSWORD,TBLLOGIN.USER_NAME\n" +
                                "FROM TBLUSERS\n" +
                                "         JOIN TBLLOGIN ON TBLUSERS.USER_ID = TBLLOGIN.USER_ID;";
                        PreparedStatement pst = con.prepareStatement(sql);
                        ResultSet rs = pst.executeQuery();
                        // If the username and password is correct then display the password field
                        if (rs.next()) {
                            passwordField.setVisible(true);
                            password.setVisible(true);
                            // Set the password to the field
                            passwordField.setText(rs.getString("PASSWORD"));
                        }
                        // If the username is not in db then display the error message on dialog box
                        else
                        {
                            JOptionPane.showMessageDialog(f, "Username does not exist !", "Error", JOptionPane.ERROR_MESSAGE);
                            username_field.setText("");
                            username_field.requestFocus();
                        }
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }});


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

