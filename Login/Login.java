package Login;


import Instructor.InstructorDashBoard;
import Member.UserDashBoard;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {

    // Get user_id method from the database
    public static int getUserId(String username) {
        int user_id = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
                    "system", "orcl");
            PreparedStatement stmt = conn.prepareStatement("SELECT  USER_NAME FROM TBLLOGIN WHERE user_id = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user_id = rs.getInt("user_id");
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return user_id;
    }

    private static final long serialVersionUID = 1L;
    private static JTextField textField;
    private JPasswordField passwordField;
    private JButton btnUserLogin;
    private JLabel label;
    private JPanel contentPane;
    private JButton btnForgetPassword;

    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Login()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100,600);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground((new Color(26, 238, 118)));

        JLabel lblNewLabel = new JLabel("Please Login To Proceed ");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Segoe Print", Font.BOLD, 32));
        lblNewLabel.setBounds(423, 13, 500, 93);
        lblNewLabel.setForeground(new Color(148,0,211));
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Segoe Print", Font.BOLD, 20));
        textField.setBounds(481, 170, 281, 40);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Sogoe Print", Font.BOLD, 20));
        passwordField.setBounds(481, 286, 281, 40);
        contentPane.add(passwordField);

        JLabel lblUsername = new JLabel("User Name :");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblUsername.setBounds(250, 166, 193, 52);
        lblUsername.setForeground(new Color(178,34,34));
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password :");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblPassword.setBounds(250, 286, 193, 52);
        lblPassword.setForeground(new Color(178,34,34));
        contentPane.add(lblPassword);

        btnUserLogin = new JButton("Login");
        btnUserLogin.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnUserLogin.setForeground(new Color(174,34,34));
        btnUserLogin.setBackground(new Color(0,191,255));
        btnUserLogin.setBounds(545, 392, 162, 73);
        btnUserLogin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                String userName = textField.getText();
                String password = String.valueOf(passwordField.getPassword());
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
                            "system", "orcl");

                    PreparedStatement st = (PreparedStatement) connection
                            .prepareStatement("Select USER_NAME, PASSWORD from TBLLOGIN where USER_NAME=? and PASSWORD=?");

                    st.setString(1, userName);
                    st.setString(2, password);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        // Close the current frame
                        dispose();
                        JOptionPane.showMessageDialog(btnUserLogin, "You have successfully logged in");
                        // Open the new frame
                        UserDashBoard ob = new UserDashBoard();
                        ob.main(null);
                    }
                    else if (userName.trim().equals("") || password.trim().equals(""))
                        JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty", "Empty Fields", 2);
                    else
                        JOptionPane.showMessageDialog(btnUserLogin, "Wrong Username & Password");
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });

        contentPane.add(btnUserLogin);

        btnForgetPassword = new JButton("Forget Password");
        btnForgetPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnForgetPassword.setBounds(320, 392, 190, 73);
        btnForgetPassword.setForeground(new Color(174,34,34));
        btnForgetPassword.setBackground(new Color(0,191,255));
        btnForgetPassword.addActionListener( e ->
        {
            dispose();
             ForgetPassword ob = new ForgetPassword();
             ob.main(null);
        });
        contentPane.add(btnForgetPassword);

        JButton btnInstructorLogin = new JButton("Instructor Login");
        btnInstructorLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnInstructorLogin.setBounds(40, 392, 250, 73);
        btnInstructorLogin.setForeground(new Color(174,34,34));
        btnInstructorLogin.setBackground(new Color(0,191,255));
        btnInstructorLogin.addActionListener(e ->
        {
            String userName = textField.getText();
            String password = String.valueOf(passwordField.getPassword());
            try {
                Connection connection = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
                        "system", "orcl");

                PreparedStatement st = (PreparedStatement) connection
                        .prepareStatement("Select USER_NAME, PASSWORD from TBLINSTRUCTOR where USER_NAME=? and PASSWORD=?");

                st.setString(1, userName);
                st.setString(2, password);
                ResultSet rs = st.executeQuery();
                if (rs.next())
                {
                    JOptionPane.showMessageDialog(btnUserLogin, "You have successfully logged in");
                    dispose();
                    InstructorDashBoard ob = new InstructorDashBoard();
                    ob.main(null);


                }
                else if (userName.trim().equals("") || password.trim().equals(""))
                    JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty", "Empty Fields", 2);
                else
                    JOptionPane.showMessageDialog(btnUserLogin, "Wrong Instructor Credentials !");
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        });
        contentPane.add(btnInstructorLogin);


        JButton btnRegister = new JButton("New Account");
        btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnRegister.setBounds(750, 392, 250, 73);
        btnRegister.setForeground(new Color(174,34,34));
        btnRegister.setBackground(new Color(0,191,255));
        btnRegister.addActionListener(e ->
        {
            dispose();
            GetBasicDetails obj = new GetBasicDetails();
            obj.main(null);

        });
        contentPane.add(btnRegister);
        label = new JLabel("");
        label.setBounds(0, 0, 1608, 562);
        contentPane.add(label);
    }

    // Function to get the UserName typed by the user when he logs in
    public static String getUserName() {
        return textField.getText();
    }
}



