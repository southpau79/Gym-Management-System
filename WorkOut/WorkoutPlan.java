package WorkOut;

import Instructor.InstructorDashBoard;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;

public class WorkoutPlan {
    public WorkoutPlan()
    {
        JFrame f = new JFrame("WORKOUT PLAN SCHEDULER");
        f.getContentPane().setBackground(new Color(142, 243, 84));
        f.setBackground(Color.cyan);

        JLabel title = new JLabel("Workout plan schedule",JLabel.CENTER);
        title.setBounds(500,10,400,50);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        title.setForeground(Color.BLUE);
        f.add(title);

        JLabel  plan_id, Instructor_id, workout_dur, plan_name;
        JTextField plan_id_txt, instructor_id_txt, workout_dur_txt, plan_name_txt;
        Border border = BorderFactory.createBevelBorder(1);

        plan_id = new JLabel("Plan ID : *");
        plan_id.setBounds(400,125,100,30);
        plan_id.setFont(new Font("Calibri", Font.ITALIC, 24));
        plan_id.setForeground(Color.BLUE);
        f.add(plan_id);

        plan_id_txt = new JTextField();
        plan_id_txt.setBounds(675, 125, 45, 25);
        plan_id_txt.setFont(new Font("Calibri", Font.ITALIC, 18));
        plan_id_txt.setBorder(border);
        // Set the text for the Sno field by calling the getSno() method
        plan_id_txt.setText(getPlanNo());
        plan_id_txt.setEditable(false);
        plan_id_txt.addKeyListener(new KeyAdapter()
        {
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                e.consume();
            }
        }
        });
        f.add(plan_id_txt);

        Instructor_id = new JLabel("Instructor ID");
        Instructor_id.setBounds(400, 180, 400, 50);
        Instructor_id.setFont(new Font("Calibri", Font.ITALIC, 24));
        Instructor_id.setForeground(Color.BLUE);
        f.add(Instructor_id);

        instructor_id_txt = new JTextField();
        instructor_id_txt.setBounds(675, 180, 60, 25);
        instructor_id_txt.setFont(new Font("Calibri", Font.PLAIN, 18));
        instructor_id_txt.setBorder(border);
        // Listener to allow only numbers to be entered in the text field
        instructor_id_txt.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });
        // Add a listener to the text field to prompt a message if the text field is not having 5 digits
        instructor_id_txt.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (instructor_id_txt.getText	().length() >= 6) {
                    e.consume();
                }
            }
        });

        f.add(instructor_id_txt);

        workout_dur = new JLabel("Workout Duration: *");
        workout_dur.setBounds(400,280,200,50);
        workout_dur.setFont(new Font("Calibri", Font.ITALIC, 24));
        workout_dur.setForeground(Color.BLUE);
        f.add(workout_dur);

        workout_dur_txt = new JTextField();
        workout_dur_txt.setBounds(675, 285, 45, 25);
        workout_dur_txt.setFont(new Font("Calibri", Font.PLAIN, 18));
        workout_dur_txt.setBorder(border);
        workout_dur_txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0' && c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    f.getToolkit().beep();
                    e.consume();
                }
            }
        });
        f.add(workout_dur_txt);

        // Add a label to the right of the text field with the text "minutes"
        JLabel minutes = new JLabel("minutes");
        minutes.setBounds(730, 280, 200, 50);
        minutes.setFont(new Font("Calibri", Font.ITALIC, 24));
        minutes.setForeground(Color.BLUE);
        f.add(minutes);
        plan_name = new JLabel("Plan Name");
        plan_name.setBounds(400, 230, 400, 50);
        plan_name.setFont(new Font("Calibri", Font.ITALIC, 24));
        plan_name.setForeground(Color.BLUE);
        f.add(plan_name);

        plan_name_txt = new JTextField();
        plan_name_txt.setBounds(675, 230, 200, 25);
        plan_name_txt.setFont(new Font("Calibri", Font.PLAIN, 18));
        plan_name_txt.setBorder(border);
        // Listener for the text field to allow only alphabets
        plan_name_txt.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                char c = e.getKeyChar();
                if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    f.getToolkit().beep();
                    e.consume();
                }
            }
        });
        f.add(plan_name_txt);

        JButton btn = new JButton("Submit");
        btn.setBounds(600, 400, 100, 50);
        btn.setFont(new Font("Serif", Font.BOLD, 20));
        btn.setBackground(new Color(255, 153, 102));
        btn.setForeground(Color.blue);
        // Add white border to the button
        btn.setBorder(BorderFactory.createLineBorder(Color.black));
        btn.setVisible(true);
        btn.addActionListener(e ->
        {
            // Check if the text fields are filled and if not display an error message
            if (plan_id_txt.getText().isEmpty() || instructor_id_txt.getText().isEmpty() || workout_dur_txt.getText().isEmpty() || plan_name_txt.getText().isEmpty())
                JOptionPane.showMessageDialog(null, "Please fill all the fields");
            else {
                // Check if the instructor id is having 5 digits
                if (instructor_id_txt.getText().length() != 6) {
                    JOptionPane.showMessageDialog(null, "Instructor ID should be of 6 digits");
                    // Clear the instructor id text field
                    instructor_id_txt.setText("");
                    instructor_id_txt.requestFocus();
                } else
                {
                    // Check if the instructor id typed is not already present in the database using checkInstructorId() method
                    if (!checkInstructor(instructor_id_txt.getText()))
                    {
                        JOptionPane.showMessageDialog(null, "An instructor with this ID is not registered!");
                        // Clear the instructor id text field
                        instructor_id_txt.setText("");
                        instructor_id_txt.requestFocus();
                    }
                    else
                    {
                        // Get the values from the text fields as String variables
                        String plan_id1 = plan_id_txt.getText();
                        String instructor_id1 = instructor_id_txt.getText();
                        String workout_dur1 = workout_dur_txt.getText();
                        String plan_name1 = plan_name_txt.getText();
                        try {
                            Connection connection = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
                                    "system", "orcl");

                            String query = "INSERT INTO TBLWORKOUT_PLAN(PLAN_ID, INSTUCTOR_ID, WORKOUT_DURATION, PLAN_NAME) values('" + plan_id1 + "','" + instructor_id1 + "','" + workout_dur1 + "','" +
                                    plan_name1 + "')";

                            Statement sta = connection.createStatement();
                            int x = sta.executeUpdate(query);
                            if (x > 0) {
                                JOptionPane.showMessageDialog(btn, "Workout Plan Added Successfully! Proceed to add the details of the exercise to this current Plan");
                                btn.setVisible(false);
                                // Close this frame
                                f.setVisible(false);
                                // Show the Payment frame
                                WorkOutDetails ob = new WorkOutDetails();
                                ob.main(null);
                                connection.close();
                            }
                            connection.close();
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                }
            }
        });
        f.add(btn);

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
        exitButton.setBounds(750, 400, 100, 50);
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

        f.setSize(1600,1600);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Function to check if the instructor id is present in the tblinstuctor table
    public static boolean checkInstructor(String instructor_id) {
        boolean flag = false;
        try {
            Connection connection = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
                    "system", "orcl");
            String query = "SELECT * FROM TBLINSTRUCTOR WHERE INSTRUCTOR_ID = '" + instructor_id + "'";
            Statement sta = connection.createStatement();
            ResultSet rs = sta.executeQuery(query);
            if (rs.next()) {
                flag = true;
            }
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return flag;
    }

    // Function to check the max of the plan_id in the tblworkout_plan table and increment it by 1
    public String getPlanNo()
    {
        int planid = 0;
        try {
            // get the class of oracle driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // create a connection to the database oracle
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");

            PreparedStatement ps = con.prepareStatement("select max(PLAN_ID) from TBLWORKOUT_PLAN");
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                planid = rs.getInt(1);
                planid++;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return Integer.toString(planid);
    }

    public static void main(String[] args) {
        new WorkoutPlan();
    }
}

