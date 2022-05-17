package Payment;

import Login.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Random;

public class GetDetails
{
    public GetDetails()
    {
        // Create a new frame
        JFrame f =new JFrame("Choose your subscription");
        // Set the background color to #6C02DE
        f.getContentPane().setBackground(new Color(108, 2, 222));
        f.setBackground(Color.cyan);

        String userName= Login.getUserName();
;
        // Create a panel on the top of the frame that contains a text label with  UserPortal
        JPanel topPanel = new JPanel();
        topPanel.setBounds(0, 0, 1600, 50);
        // Set the background color of the panel to #00308f
        topPanel.setBackground(new Color(132,222,2));
        JLabel topLabel = new JLabel("Subscription Model");
        // Set the font colour of User Portal to white
        topLabel.setForeground(Color.white);
        topLabel.setFont(new Font("Serif", Font.BOLD, 24));
        topPanel.add(topLabel);
        f.add(topPanel);

        JLabel name,charge,startdate,cardnumber,expiry,cvv;
        JTextField chargeField,dateField,nameoncard_field,cardnumber_field,expiry_field,cvv_field;

        // Option List for the Gender
        String[] subscription_options =
                {
                        "Please Select",
                        "Monthly",
                        "Quarterly",
                        "Semi-Yearly",
                        "Yearly"
                };

        JComboBox SubscriptionList = new JComboBox (subscription_options);
        SubscriptionList.setBounds(675,183,130,20);
        f.add(SubscriptionList);

        //Create a  JLabel with text "Personal Information"
        charge = new JLabel("Subscription Charge :");
        charge.setBounds(400,225,400,50);
        charge.setFont(new Font("Calibri", Font.ITALIC, 24));
        charge.setForeground(Color.BLACK);
        f.add(charge);

        chargeField = new JTextField();
        chargeField.setBounds(675,237,40,25);

        JTextField enddateField = new JTextField();
        enddateField.setBounds(675,337,70,25);
        // Make the end date field null by default
        enddateField.setText("");

        // If the SubscriptionList chosen is Monthly, then display 700 in the field
        // Add a Listener to the SubscriptionList choosen is "Monthly"
        SubscriptionList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SubscriptionList.getSelectedItem().toString() == "Monthly")
                {
                    chargeField.setText("700");
                    chargeField.setEditable(false);
                    enddateField.setText(java.time.LocalDate.now().plusMonths(1).toString());
                    enddateField.setEditable(false);
                }
                else if (SubscriptionList.getSelectedItem().toString() == "Quarterly")
                {
                    chargeField.setText("2500");
                    chargeField.setEditable(false);
                    enddateField.setText(java.time.LocalDate.now().plusMonths(3).toString());
                    enddateField.setEditable(false);
                }
                else if (SubscriptionList.getSelectedItem().toString() == "Semi-Yearly")
                {
                    chargeField.setText("3800");
                    chargeField.setEditable(false);
                    enddateField.setText(java.time.LocalDate.now().plusMonths(6).toString());
                    enddateField.setEditable(false);
                }
                else if (SubscriptionList.getSelectedItem().toString() == "Please Select")
                {
                    JOptionPane.showMessageDialog(f , "Please select a subscription model to subscribe!");
                    chargeField.setText("");
                    chargeField.setEditable(false);
                    enddateField.setText("");
                    enddateField.setEditable(false);
                }
                else
                {
                    chargeField.setText("8000");
                    chargeField.setEditable(false);
                    enddateField.setText(java.time.LocalDate.now().plusYears(1).toString());
                    enddateField.setEditable(false);
                }
            }
        });
        f.add(chargeField);

        //Create a  JLabel
        startdate = new JLabel("Start Date : ");
        startdate.setBounds(400,275,400,50);
        startdate.setFont(new Font("Calibri", Font.ITALIC, 24));
        startdate.setForeground(Color.BLACK);
        f.add(startdate);

        dateField= new JTextField();
        dateField.setBounds(675,287,70,25);
        dateField.setText(java.time.LocalDate.now().toString());
        dateField.setEditable(false);
        f.add(dateField);

        JLabel enddate = new JLabel("End Date : ");
        enddate.setBounds(400,325,400,50);
        enddate.setFont(new Font("Calibri", Font.ITALIC, 24));
        enddate.setForeground(Color.BLACK);
        f.add(enddate);
        enddateField.setText(java.time.LocalDate.now().toString());
        f.add(enddateField);

        JButton submit = new JButton("Submit");
        submit.setBounds(575,400,100,50);
        submit.setFont(new Font("Calibri",Font.BOLD,18));
        submit.setBackground(Color.green);
        submit.setForeground(Color.white);
        f.add(submit);

        // Add Listener to the Submit button
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(SubscriptionList.getSelectedItem().toString() == "Please Select")
                {
                    JOptionPane.showMessageDialog(f , "Please select a subscription model to subscribe!");
                }
                else
                {
                    // Create a new connection to the oracle database
                    try
                    {
                        String id = generateID();
                        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
                        PreparedStatement pst;
                        pst = connection.prepareStatement("insert into TBLMEMBERSHIP values(?,?,?,?,?)");
                        pst.setString(1,id);
                        pst.setString(2, chargeField.getText());
                        pst.setString(3, dateField.getText());
                        pst.setString(4,enddateField.getText());
                        pst.setString(5, findUserID(userName));

                        pst.executeUpdate();


                        JOptionPane.showMessageDialog(submit, "Your Subscription details is stored... Please proceed to payment ");
                       // Close this frame
                        f.setVisible(false);
                        // Show the Payment frame
                        PaymentPage payment = new PaymentPage();
                        payment.main(null);
                        connection.close();
                    }
                    catch (SQLException e1)
                    {
                        e1.printStackTrace();
                    }
                }
            }

         });

        // Frame Properties
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1600,800);
        f.setLayout(null);
        f.setVisible(true);
    }

    // Function to find the User ID by the Username
    public static String findUserID(String userName)
    {
        String userID = "";
        try
        {
            // Create a connection to the database
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
            // Create a statement to execute the query
            Statement stmt = con.createStatement();
            // Create a query to find the user id through the USERNAME by joining TBLLOGIN and TBLUSERS
            String query = "SELECT TBLUSERS.USER_ID FROM TBLUSERS INNER JOIN TBLLOGIN ON TBLUSERS.USER_ID = TBLLOGIN.USER_ID WHERE TBLLOGIN.USER_NAME = '" + userName + "'";
            // Execute the query
            ResultSet rs = stmt.executeQuery(query);
            // Get the Membership ID of the user by the User Name
            while (rs.next())
            {
                userID = rs.getString("USER_ID");
            }
        }
        catch (Exception e)
        {e.printStackTrace();}
        return userID;
    }

    // Create a function that generates unique Membership ID of exactly 5 digits
    public static String generateID()
    {
        String id = "";
        Random rand = new Random();
        for(int i = 0; i < 5; i++)
        {
            id += rand.nextInt(10);
        }
        return id;
    }

    public static void main(String[] args) {
        new GetDetails();
    }
}
