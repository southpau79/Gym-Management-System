package Member;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewWorkutDetails
{
    public ViewWorkutDetails()
    {
        JFrame f = new JFrame("WorkOut Details");
        // Set the background color of the frame to #00C438
        f.getContentPane().setBackground(new Color(0, 204, 56));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the size of the frame automatically to fit the content
        f.setSize(1600, 800);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel on the top of the frame that contains a text label with  UserPortal
        JPanel topPanel = new JPanel();
        topPanel.setBounds(0, 0, 1600, 50);
        topPanel.setBackground(new Color(0,196,176));
        JLabel topLabel = new JLabel("Workout Details");
        // Set the font colour of User Portal to white
        topLabel.setForeground(Color.darkGray);
        topLabel.setFont(new Font("Serif", Font.BOLD, 24));
        topPanel.add(topLabel);
        f.add(topPanel);

        // Add a text label with the Plan Name
        JLabel planNameLabel = new JLabel("Plan Name: ");
        planNameLabel.setBounds(400, 55, 150, 50);
        planNameLabel.setFont(new Font("Serif", Font.BOLD, 20));
        // Set the font colour of the text label to red
        planNameLabel.setForeground(Color.red);
        f.add(planNameLabel);
        JTextField planNameField = new JTextField();
        planNameField.setBounds(600, 65, 200, 30);
        planNameField.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        // Set the font colour on the  text field to red
        planNameField.setForeground(Color.red);
        // Add white border to the text field
        planNameField.setBorder(BorderFactory.createLineBorder(Color.black));
        f.add(planNameField);

        // Add a Button at the right of the text field
        JButton SearchButton = new JButton("Search");
        SearchButton.setBounds(870, 65, 100, 30);
        SearchButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        SearchButton.setFont(new Font("Serif", Font.BOLD, 20));
        SearchButton.setBackground(new Color(255, 153, 102));
        SearchButton.setForeground(Color.blue);
        // Add white border to the button
        SearchButton.setBorder(BorderFactory.createLineBorder(Color.black));
        f.add(SearchButton);

        // Add a Text Area Field to display the exercise name,counts and comment
        TextArea workOutDetails = new TextArea("",20,20,TextArea.SCROLLBARS_BOTH);
        workOutDetails.setBounds(600, 175, 300, 400);
        workOutDetails.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        // Set the background colour of the text area to white
        workOutDetails.setBackground(Color.white);
        // Set the font colour of the text area to
        workOutDetails.setForeground(Color.red);
        workOutDetails.setVisible(false);
        f.add(workOutDetails);

        // Add a Label for the Workout Details at the top of the text area
        JLabel workOutDetailsLabel = new JLabel("Workout Details");
        workOutDetailsLabel.setBounds(600, 130, 300, 50);
        // Horizontally centered on the left
        workOutDetailsLabel.setHorizontalAlignment(JLabel.CENTER);
        // Set the font colour of the text label to red
        workOutDetailsLabel.setForeground(Color.red);
        workOutDetailsLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        workOutDetailsLabel.setVisible(false);
        f.add(workOutDetailsLabel);


        // If the button is clicked, check if the text field is empty
        // If it is empty, display an error message
        // If it is not empty, display the details of the exercise
        SearchButton.addActionListener(e ->
        {
            if (planNameField.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Please enter an exercise name!");
                planNameField.requestFocus();
            }
            else
            {
                // Call the isValidPlanName method
                if (isValidPlanName(planNameField.getText()))
                {
                    String set ="";
                    int i =0;
                    // Connect to the oracle database and get the excerise name,counts and comments
                    try
                    {
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
                        // Create a statement object
                        Statement stmt = con.createStatement();
                        // Query to get the excercise name,counts and comments
                        String query="select tblworkout_details.exercise_name,tblworkout_details.counts,tblworkout_details.comments from tblworkout_details,tblworkout_plan where tblworkout_plan.plan_name = '" + planNameField.getText() +"' and tblworkout_details.plan_id = tblworkout_plan.plan_id";
                        // Execute the query
                        ResultSet rs = stmt.executeQuery(query);

                        // Get all the records from the result set
                        while (rs.next())
                        {
                            i++;
                            set = set + "Exercise : " + i +
                                    " \n 1. Exercise Name: " + rs.getString("exercise_name") +
                                    "\n 2. Counts: " + rs.getString("counts") +
                                    "\n 3. Reps: " + rs.getString("comments") + "\n" +
                                    "-----------------------------------------------------\n";
                        }
                        workOutDetailsLabel.setVisible(true);
                        workOutDetails.setVisible(true);
                        // Display the exercise name,counts and comments
                        workOutDetails.setText(set);
                        workOutDetails.setEditable(false);
                    }
                    catch (Exception e1)
                    {
                        e1.printStackTrace();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Invalid Plan Name!");
                    planNameField.requestFocus();
                }
            }
        });

        // Add a button that will allow the user to go back to the Main Page
        JButton backButton = new JButton("Main Page");
        backButton.setBounds(450, 600, 100, 50);
        backButton.setFont(new Font("Serif", Font.BOLD, 20));
        backButton.setBackground(new Color(255, 153, 102));
        backButton.setForeground(Color.blue);
        // Add white border to the button
        backButton.setBorder(BorderFactory.createLineBorder(Color.black));
        backButton.setVisible(true);
        backButton.addActionListener(e ->
        {
            // Close the current frame
            f.dispose();
            // Open the Main Page
            UserDashBoard ob = new UserDashBoard();
            ob.main(null);
        });
        f.add(backButton);

        // Add a button that will allow the user to  exit the application
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(650, 600, 100, 50);
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

    }

    // Function to check if the plan name is valid or not
    public boolean isValidPlanName(String planName)
    {
        boolean isValid = false;
        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
            String sql = "SELECT PLAN_NAME FROM TBLWORKOUT_PLAN WHERE PLAN_NAME = '"+planName+"'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                isValid = true;
            }
            connection.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return isValid;
    }

    public static void main(String[] args) {
        new ViewWorkutDetails();
    }
}
