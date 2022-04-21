package Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ForgetPassword {
    public ForgetPassword()
    {
        // Create a new frame
        JFrame f =new JFrame("Forget Password");
        f.getContentPane().setBackground(new Color(255,128,102));
        f.setBackground(Color.cyan);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1600, 800);
        f.setLayout(null);
        f.setVisible(true);

        // Create a new Label for the Title
        JLabel title = new JLabel("Forget Password",JLabel.CENTER);
        title.setBounds(500,10,400,50);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        title.setForeground(Color.BLUE);
        f.add(title);

        // Declaring all the labels,text-fields and password fields
        JLabel usernameLabel, passwordLabel;
        JTextField username_field;
        JPasswordField passwordField;

        usernameLabel = new JLabel("Enter Username: *");
        usernameLabel.setBounds(400, 150, 170, 80);
        usernameLabel.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(usernameLabel);

        username_field = new JTextField();
        username_field.setBounds(600, 173, 200, 25);
        username_field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        f.add(username_field);
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


        passwordLabel = new JLabel("Your Password: ");
        passwordLabel.setBounds(400, 150, 200, 80);
        passwordLabel.setFont(new Font("Calibri", Font.ITALIC, 22));
        passwordLabel.setVisible(false);
        f.add(passwordLabel);

        passwordField = new JPasswordField();
        // Set the text colour to #ec3b83
        passwordField.setBounds(600, 223, 200, 25);
        passwordField.setVisible(false);
        f.add(passwordField);

        // Create a new button for checking if the username is present in the database
        JButton check = new JButton("Check if account exists");
        check.setBounds(600, 400, 200, 30);
        check.setFont(new Font("Calibri", Font.ITALIC, 22));
        check.setVisible(true);
        f.add(check);

        // Listener for the check button to check if the username is present in the oracle database
        check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from TBLLOGIN where USER_NAME='" + username_field.getText() + "'");
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Username is present in the database");
                        // Show the password to the password Field
                        passwordField.setVisible(true);
                        passwordField.setText(rs.getString("PASSWORD"));
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Username is not present in the database");
                        // Show the ERROR message to the password Field
                        passwordField.setVisible(true);
                        passwordField.setText("ERROR");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error");
                }
            }
        });

    }

    public static void main(String[] args) {
        new ForgetPassword();
    }
}
