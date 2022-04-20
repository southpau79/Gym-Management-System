package Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

        username = new JLabel("Enter Username: *");
        username.setBounds(400, 150, 170, 80);
        username.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(username);
        username_field = new JTextField();
        username_field.setBounds(600, 173, 200, 25);
        f.add(username_field);

        password = new JLabel("Enter Password: *");
        password.setBounds(400, 200, 200, 80);
        password.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(password);
        passwordField = new JPasswordField();
        passwordField.setBounds(600, 223, 200, 25);
        f.add(passwordField);

        password_confirm= new JLabel("Confirm Password: *");
        password_confirm.setBounds(400,250,200,80);
        password_confirm.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(password_confirm);
        passwordField1 = new JPasswordField();
        passwordField1.setBounds(600,273,200,25);
        // KeyListener to check if the password and confirm password are same
        passwordField1.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals( String.valueOf(passwordField1.getPassword()) ))
                    passwordField1.setBackground(Color.GREEN);
                 else
                    passwordField1.setBackground(Color.RED);
            }
        }
        );
        f.add(passwordField1);

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
        f.add(roleList);

        JButton Check = new JButton("Check Availability");
        Check.setBounds(850,168,170,25);
        f.add(Check);
        Check.addActionListener(e ->
        {
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
                    JOptionPane.showMessageDialog(null, "This Username(Email) is Already Registered with us ! , Choose Another One", "Username Failed", 2);}
                else
                    JOptionPane.showMessageDialog(null, "Username Available... Create a password for your account","Username Available",1);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } ;
        });

        JButton Register = new JButton("Proceed to Registration");
        Register.setBounds(500,390,200,25);
        f.add(Register);
        Register.addActionListener(e ->
        {
            String Username= username_field.getText();
            String passText = new String(passwordField.getPassword());
            String passText_confirm = new String(passwordField1.getPassword());
            String RollIDText = roleList.getSelectedItem().toString();
            if(Username.trim().equals("") || passText.trim().equals("") || passText_confirm.trim().equals("") ||RollIDText.trim().equals("Please Select"))
                JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty","Empty Fields",2);
            else if(!passText.equals(passText_confirm))
                JOptionPane.showMessageDialog(null, "Password Doesn't Match","Confirm Password",2);
            else
            {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb", "root", "root");
                    PreparedStatement pst;
                    pst = connection.prepareStatement("insert into TBLLOGIN(USER_NAME, PASSWORD, ROLE_ID)values(?,?,?)");
                    pst.setString(1,Username);
                    pst.setString(2,passText);
                    pst.setString(3,RollIDText);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(Register, "Your Login Details are stored...");
                    connection.close();

                    // Calling The NewRegisterForm if it exists
                    // GetBasicDetails obj = new GetBasicDetails();
                } catch (Exception e1) {
                    e1.printStackTrace();
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

    public static void main(String[] args) {
        new GetLoginInfo();
    }
}