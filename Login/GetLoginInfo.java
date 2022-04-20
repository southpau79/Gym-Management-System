package Login;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class GetLoginInfo
{
    public GetLoginInfo()
    {
        // Create a new frame
        JFrame f =new JFrame("Login Details");
        f.getContentPane().setBackground(new Color(230,230,0));
        f.setBackground(Color.cyan);

        // Create a new Label for the Title
        JLabel title = new JLabel("User Login Details",JLabel.CENTER);
        title.setBounds(500,10,400,50);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        title.setForeground(Color.BLUE);
        f.add(title);

        // Declaring all the labels,text-fields and password fields
        JLabel username, password, password_confirm;
        JTextField username_field;
        JPasswordField passwordField, passwordField1;

        // Create a JLabel with the text "The Username should be atleast 6 characters long and should not be a email address"
        JLabel username_label = new JLabel("     The Username should be atleast 6 characters long and at maximum 10 characters long. " +
                "It should not be a email address format");
        username_label.setBounds(80,70,1200,50);
        username_label.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        username_label.setForeground(new Color(2, 138, 15));
        username_label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        username_label.setVisible(false);
        f.add(username_label);

        username = new JLabel("Enter Username: *");
        username.setBounds(400, 150, 170, 80);
        username.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(username);
        username_field = new JTextField();
        username_field.setBounds(600, 173, 200, 25);

        // KeyListener for allowing only 10 characters in the username field
        username_field.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                // Check if the character typed is not @ and .
                if((e.getKeyChar() == '@' || e.getKeyChar() == '.')) {
                    e.consume();
                }
                if (username_field.getText().length() >= 10)
                    e.consume();
            }});


        // KeyListener for displaying a message if the username field is empty and the user clicks on the Check button
        username_field.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (username_field.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "Please enter a username");
                    }
                }
            }
        });
        f.add(username_field);

        // Create label with "enter Strong Password Constraints" left side of the password label
        JLabel password_constraints = new JLabel("Enter Password with at-least 8 characters with 1 uppercase, " +
                "1 lowercase, 1 digit and 1 special character");
        password_constraints.setBounds(100, 70, 870, 80);
        password_constraints.setFont(new Font("Calibri", Font.BOLD, 20));
        // Add colour to the label to #028A0F
        password_constraints.setForeground(new Color(2, 138, 15));
        // Add surrounding border to the label only sourrounding the text
        password_constraints.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        password_constraints.setVisible(false);
        f.add(password_constraints);


        password = new JLabel("Enter Password: *");
        password.setBounds(400, 200, 200, 80);
        password.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(password);
        passwordField = new JPasswordField();
        passwordField.setBounds(600, 223, 200, 25);
        // Display the dialog box if the length of the password is less than 10
        passwordField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (String.valueOf(passwordField.getPassword()).length() >= 10)
                    e.consume();
            }
        });

        // When types the passsword, call the method passwordStrength()
        passwordField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                passwordStrength(passwordField);
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

        password_confirm= new JLabel("Confirm Password: *");
        password_confirm.setBounds(400,250,200,80);
        password_confirm.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(password_confirm);
        passwordField1 = new JPasswordField();
        passwordField1.setBounds(600,273,200,25);
        passwordField1.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (String.valueOf(passwordField.getPassword()).length() >= 10)
                    e.consume();
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
                username_label.setVisible(true);
            }
            public void focusLost(FocusEvent e) {
                username_label.setVisible(false);
            }
        });

        JLabel RoleID = new JLabel("Select your Role: *");
        RoleID.setBounds(400,300,200,80);
        RoleID.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(RoleID);

        String[] role_options =
                {
                        "Please Select",
                        "BodyBuilding Coach",
                        "Dietitian",
                        "Equipment Technician",
                        "DB Administrator",
                        "Gym Member",
                        "Gym Instructor"
                };

        JComboBox roleList = new JComboBox (role_options);
        roleList.setBounds(600,320,200,25);

        // Display dialog box if the user selects "Please Select" itself
        roleList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (roleList.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Please select a role from the list");
                }
            }
        });
    f.add(roleList);

        // To add a button to display password when the user clicks on it
        JButton show_password = new JButton("Show Password");
        show_password.setBounds(850, 223, 150, 25);
        show_password.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (passwordField.getEchoChar() == '*') {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('*');
                }
            }
        });
        f.add(show_password);

        JButton Check = new JButton("Check Availability");
        Check.setBounds(850,168,170,25);
        f.add(Check);
        Check.addActionListener(e ->
        {
            // Display dialog box if the username field is empty
            if (username_field.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter a username");
            } else {
                String userName = username_field.getText();
                String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
                String PASS = "system";
                String USER = "orcl";
                try {
                    Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
                    PreparedStatement preparedStatement = (PreparedStatement) connection
                            .prepareStatement("Select USER_NAME from TBLLOGIN where USER_NAME=?");
                    preparedStatement.setString(1, userName);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(null, "This Username(Email) is Already Registered with us ! , Choose Another One", "Username Failed", 2);
                    } else
                        JOptionPane.showMessageDialog(null, "Username Available... Create a password for your account", "Username Available", 1);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ;
            }
        });

        JButton Register = new JButton("Proceed to Registration");
        Register.setBounds(500,390,200,25);
        f.add(Register);
        Register.addActionListener(e ->
        {
            // Check if the Password Field is strong enough
            if (!(String.valueOf(passwordField.getPassword()).matches(".*[A-Z].*") &&
                    String.valueOf(passwordField.getPassword()).matches(".*[a-z].*") &&
                    String.valueOf(passwordField.getPassword()).matches(".*[0-9].*") &&
                    String.valueOf(passwordField.getPassword()).matches(".*[!@#$%^&*()_+].*"))) {
                JOptionPane.showMessageDialog(null, "Password must contain at-least one Uppercase, Lowercase, Digit and Special Character");
            }
            else {
                String Username = username_field.getText();
                String passText = new String(passwordField.getPassword());
                String passText_confirm = new String(passwordField1.getPassword());
                String RollIDText = roleList.getSelectedItem().toString();
                if (Username.trim().equals("") || passText.trim().equals("") || passText_confirm.trim().equals("") || RollIDText.trim().equals("Please Select"))
                    JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty", "Empty Fields", 2);
                else if (!passText.equals(passText_confirm))
                    JOptionPane.showMessageDialog(null, "Password Doesn't Match", "Confirm Password", 2);
                else {
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
                        PreparedStatement pst;
                        pst = connection.prepareStatement("insert into TBLLOGIN(USER_NAME, PASSWORD, ROLE_ID)values(?,?,?)");
                        pst.setString(1, Username);
                        pst.setString(2, passText);
                        pst.setString(3, RollIDText);
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(Register, "Your Login Details are stored...");
                        connection.close();

                        // Calling The NewRegisterForm if it exists
                        // GetBasicDetails obj = new GetBasicDetails();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        JLabel label = new JLabel();
        f.add(label);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1600, 800);
        f.setLayout(null);
        f.setVisible(true);
    }

    // Create a method to display password strength bar and display it as soon password is typed
    public void passwordStrength(JPasswordField passwordField) {
        passwordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkPasswordStrength(passwordField);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkPasswordStrength(passwordField);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkPasswordStrength(passwordField);
            }
        });
    }

    private void checkPasswordStrength(JPasswordField passwordField) {
        String password = new String(passwordField.getPassword());
        int strength = 0;
        if (password.length() > 0) {
            if (password.matches(".*[a-z]+.*")) {
                strength++;
            }
            if (password.matches(".*[A-Z]+.*")) {
                strength++;
            }
            if (password.matches(".*[0-9]+.*")) {
                strength++;
            }
            if (password.matches(".*[!@#$%^&*()_+].*")) {
                strength++;
            }
        }
        if (strength == 1) {
            passwordField.setBackground(Color.red);
        } else if (strength == 2) {
            passwordField.setBackground(Color.orange);
        } else if (strength == 3) {
            passwordField.setBackground(Color.green);
        } else if (strength == 4) {
            passwordField.setBackground(Color.blue);
        }
    }

    // Create a method to get the role id
    public static String getRoleID(String roleName) {
        String roleID = "";
        /**try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
            PreparedStatement pst;
            pst = connection.prepareStatement("select ROLE_ID from TBLLOGIN ");
            pst.setString(1, roleName);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                roleID = rs.getString(1);
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
*/        return roleID;
    }
    public static void main(String[] args) {
        new GetLoginInfo();
    }
}