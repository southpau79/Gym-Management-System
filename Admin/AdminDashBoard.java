package Admin;

import Instructor.InstructorDashBoard;
import Instructor.ViewProfileDetails;
import Login.Login;
import WorkOut.WorkoutPlan;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminDashBoard
{
    public AdminDashBoard()
    {
        JFrame f = new JFrame("Admin Dashboard");
        f.getContentPane().setBackground(new Color(213, 216, 28));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1600, 800);
        f.setLayout(null);
        f.setVisible(true);

        // Create a panel on the top of the frame that contains a text label with  UserPortal
        JPanel topPanel = new JPanel();
        topPanel.setBounds(0, 0, 1600, 50);
        // Set the background color of the panel to #00308f
        topPanel.setBackground(new Color(80, 48, 143));
        JLabel topLabel = new JLabel("Admin Portal");
        // Set the font colour of User Portal to white
        topLabel.setForeground(Color.white);
        topLabel.setFont(new Font("Serif", Font.BOLD, 24));
        topPanel.add(topLabel);
        f.add(topPanel);

        // Create a button on the left of the frame that contains a text label with  View WorkOut Details without panel
        JButton schedule = new JButton("Schedule workout");
        schedule.setBounds(50, 200, 250, 50);
        // Set the background color of the button to #5FB3CE
        schedule.setBackground(new Color(90, 255, 156));
        schedule.setForeground(Color.blue);
        schedule.setFont(new Font("Serif", Font.BOLD, 18));
        // Add white border to the button
        schedule.setBorder(BorderFactory.createLineBorder(Color.black));
        schedule.addActionListener(e ->
        {
            WorkoutPlan ob = new WorkoutPlan();
            f.dispose();
            ob.main(null);
        });
        f.add(schedule);

        // Create a button on the left of the frame that contains a text label with  View Membership Status without panel
        JButton view_prof = new JButton("View Profile");
        view_prof.setBounds(50, 280, 250, 50);
        view_prof.setBackground(new Color(90, 255, 156));
        view_prof.setForeground(Color.blue);
        view_prof.setFont(new Font("Serif", Font.BOLD, 18));
        // Add white border to the button
        view_prof.setBorder(BorderFactory.createLineBorder(Color.black));
        view_prof.addActionListener(e ->
        {
            ViewProfileDetails ob = new ViewProfileDetails();
            f.dispose();
            ob.main(null);
        });
        f.add(view_prof);

        // Create a button on the left of the frame that contains a text label with  View Profile without panel
        JButton change_pass = new JButton("Change Password");
        change_pass.setBounds(50, 360, 250, 50);
        change_pass.setBackground(new Color(90, 255, 156));
        change_pass.setForeground(Color.blue);
        change_pass.setFont(new Font("Serif", Font.BOLD, 18));
        // Add white border to the button
        change_pass.setBorder(BorderFactory.createLineBorder(Color.black));
        change_pass.addActionListener(e ->
        {
            // Minimize the frame
            Instructor.ResetPassword ob = new Instructor.ResetPassword();
            f.dispose();
            ob.main(null);
        });
        // Create a new frame that contains a text label with  View Profile);
        f.add(change_pass);

        // Create a button on the left of the frame that contains a text label with  Change Password without panel
        JButton logout = new JButton("Logout");
        logout.setBounds(50, 440, 250, 50);
        logout.setBackground(new Color(90, 255, 156));
        logout.setForeground(Color.blue);
        logout.setFont(new Font("Serif", Font.BOLD, 18));
        // Add white border to the button
        logout.setBorder(BorderFactory.createLineBorder(Color.black));
        logout.addActionListener
                (e ->
                {
                    // Minimize the frame
                    f.dispose();
                    Login ob = new Login();
                    ob.main(null);
                });
        f.add(logout);

        String username = Login.getUserName();

        // Add a welcome message with the username
        JLabel welcomeLabel = new JLabel("Welcome, " + findName(username) + " :-)");
        welcomeLabel.setBounds(680, 100, 400, 50);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 22));
        welcomeLabel.setForeground(Color.blue);
        f.add(welcomeLabel);

        // Create a button on the left of the frame that contains a text label with  Logout without panel
        JButton main_menu = new JButton("Main Menu");
        main_menu.setBounds(50, 520, 250, 50);
        main_menu.setBackground(new Color(90, 255, 156));
        main_menu.setForeground(Color.blue);
        main_menu.setFont(new Font("Serif", Font.BOLD, 18));
        // Add white border to the button
        main_menu.setBorder(BorderFactory.createLineBorder(Color.black));
        main_menu.addActionListener(e ->
        {
            // Minimize the frame
            f.dispose();
            InstructorDashBoard ob = new InstructorDashBoard();
            ob.main(null);
        });
        f.add(main_menu);

        // Add a text label on the center of the frame that contains a text label
        JLabel centerLabel = new JLabel("This Gym Management System is designed with Java , and it should work");
        centerLabel.setBounds(500, 200, 800, 30);
        centerLabel.setFont(new Font("Serif", Font.BOLD, 22));
        f.add(centerLabel);

        JLabel centerLabel2 = new JLabel("on later versions of  Java.");
        centerLabel2.setBounds(680, 230, 500, 30);
        centerLabel2.setFont(new Font("Serif", Font.BOLD, 22));
        f.add(centerLabel2);

        JLabel centerLabel3 = new JLabel("If you have any problem, running the software, please contact:");
        centerLabel3.setBounds(550, 260, 600, 30);
        centerLabel3.setFont(new Font("Serif", Font.BOLD, 22));
        f.add(centerLabel3);

        JLabel centerLabel4 = new JLabel("noreplytest.gymvale@gmail.com");
        centerLabel4.setBounds(650, 290, 500, 30);
        centerLabel4.setFont(new Font("Serif", Font.BOLD, 22));
        f.add(centerLabel4);

        JLabel centerLabel5 = new JLabel("There is no guarantee for this software.Your feedback is welcome");
        centerLabel5.setBounds(500, 380, 700, 30);
        centerLabel5.setFont(new Font("Serif", Font.BOLD, 22));
        f.add(centerLabel5);

        JLabel centerLabel6 = new JLabel("for newer developments of this version.");
        centerLabel6.setBounds(680, 410, 800, 30);
        centerLabel6.setFont(new Font("Serif", Font.BOLD, 22));
        f.add(centerLabel6);

        JLabel centerLabel7 = new JLabel("Thank you for using our software.");
        centerLabel7.setBounds(700, 440, 800, 30);
        centerLabel7.setFont(new Font("Serif", Font.BOLD, 22));
        f.add(centerLabel7);

        JLabel centerLabel8 = new JLabel("Team GymVale");
        centerLabel8.setBounds(780, 470, 150, 30);
        centerLabel8.setFont(new Font("Serif", Font.BOLD, 22));
        f.add(centerLabel8);


        // Add a button with Exit in the bottom right corner of the frame
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(1200, 600, 100, 50);
        exitButton.setBackground(new Color(235, 109, 20));
        exitButton.setForeground(Color.blue);
        exitButton.setFont(new Font("Serif", Font.BOLD, 18));
        // Add white border to the button
        exitButton.setBorder(BorderFactory.createLineBorder(Color.black));
        exitButton.setVisible(true);
        exitButton.addActionListener(e ->
        {
            // Exit the program
            System.exit(0);
        });
        f.add(exitButton);


    }

    private static String findName(String userName)
    {
        String name = "";
        try
        {
            // Create a connection to the database
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
            // Create a statement to execute the query
            Statement stmt = con.createStatement();
            // Create a query to find the First Name , Middle Name , Last Name  of the user by the User Name by joining the tblusers and tblinstuctor tables
            String query = "SELECT tblusers.FIRST_NAME, tblusers.MIDDLE_NAME, tblusers.LAST_NAME FROM TBLUSERS INNER JOIN TBLINSTRUCTOR ON tblusers.USER_ID = TBLINSTRUCTOR.USER_ID WHERE TBLINSTRUCTOR.USER_NAME = '" + userName + "'";
            // Execute the query
            ResultSet rs = stmt.executeQuery(query);
            // Get the First Name , Middle Name , Last Name  of the user by the User Name
            while (rs.next())
            {
                name = rs.getString("FIRST_NAME") + " " + rs.getString("MIDDLE_NAME") + " " + rs.getString("LAST_NAME");
            }
            // Close the connection
            con.close();
            return name;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return name;
    }

    public static void main(String[] args) {
        new AdminDashBoard();
    }
}
