package Login;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;

public class GetBasicDetails
{
    public GetBasicDetails()
    {
        // Create a new frame
        JFrame f =new JFrame("New User Registration");
        f.getContentPane().setBackground(new Color(255,128,102));
        f.setBackground(Color.cyan);

        // Create a new Label for the Title
        JLabel title = new JLabel("User Basic Details",JLabel.CENTER);
        title.setBounds(500,10,400,50);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        title.setForeground(Color.BLUE);
        f.add(title);

        // Declaring the Labels, TextFields and Border
        JLabel fname,mname,lname,age,gender,email,doorno,strname,city,pincode,state;
        JTextField fname1,mname1,lname1,age1,gender1,email1,doorno1,strname1,city1,pincode1,state1;
        Border border = BorderFactory.createLineBorder(Color.BLACK);

        // Creating the Labels & Creating the TextFields row-wise
        fname = new JLabel("First Name: *");
        fname.setBounds(400,50,130,80);
        fname.setFont(new Font("Calibri", Font.ITALIC, 24));
        fname.setForeground(new Color(255,255,255));
        fname1 = new JTextField();
        fname1.setBounds(600,75,150,25);
        // Set the BG color of the text field to #d1d1d1
        fname1.setBackground(new Color(221,221,221));
        // Set the border
        fname1.setBorder(border);

        // Key Listener for the First Name TextField to limit the input to 30 characters
        fname1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (fname1.getText().length() >= 30 )
                    e.consume();
            }
        });

        // Key Listener for the First Name TextField to accept only alphabets
        fname1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    f.getToolkit().beep();
                    e.consume();
                }
            }
        });
        f.add(fname1);

        mname = new JLabel("Middle Name :");
        mname.setBounds(400,95,200,80);
        mname.setFont(new Font("Calibri", Font.ITALIC, 24));
        mname1 = new JTextField();
        mname1.setBounds(600,118,150,25);
        mname.setForeground(new Color(255,255,255));
        // Set the border
        mname1.setBorder(border);
        // Set the BG color of the text field to #d1d1d1
        mname1.setBackground(new Color(221,221,221));

        // Key Listener for the Middle Name TextField to limit the input to 30 characters
        mname1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (mname1.getText().length() >= 30 )
                    e.consume();
            }
        });

        // Key Listener for the Middle Name TextField to accept only alphabets
        mname1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    f.getToolkit().beep();
                    e.consume();
                }
            }
        });
        f.add(mname1);

        // Creating the Last Name Label & TextField
        lname = new JLabel("Last Name: *");
        lname.setBounds(400,140,130,80);
        lname.setFont(new Font("Calibri", Font.ITALIC, 24));
        lname.setForeground(new Color(255,255,255));
        lname1 = new JTextField();
        lname1.setBounds(600,161,150,25);
        lname1.setBorder(border);
        lname1.setBackground(new Color(221,221,221));

        // Key Listener for the Last Name TextField to limit the input to 30 characters
        lname1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (lname1.getText().length() >= 30 )
                    e.consume();
            }
        });

        // Key Listener for the Last Name TextField to accept only alphabets
        lname1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    f.getToolkit().beep();
                    e.consume();
                }
            }
        });
        f.add(lname1);

        // Creating the Label & TextField for the Age
        age = new JLabel("Age: *");
        age.setBounds(400,185,120,80);
        age.setFont(new Font("Calibri", Font.ITALIC, 24));
        age.setForeground(new Color(255,255,255));
        age1 = new JTextField();
        age1.setBounds(600,204,40,25);
        age1.setBorder(border);
        age1.setBackground(new Color(221,221,221));

        // Key Listener for the Age TextField to limit the input to 3 characters
        age1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (age1.getText().length() >= 3 )
                    e.consume();
            }
        });

        // Key Listener for the Age TextField to accept only numbers
        age1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0' && c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    f.getToolkit().beep();
                    e.consume();
                }
            }
        });
        f.add(age1);

        // Creating the Label & Combo-Box for the Gender
        gender = new JLabel("Gender: *");
        gender .setBounds(400,230,120,80);
        gender .setFont(new Font("Calibri", Font.ITALIC, 24));
        gender.setForeground(new Color(255,255,255));

        // Option List for the Gender
        String[] gender_options =
                {
                        "Please Select",
                        "Male",
                        "Female",
                        "Transgender",
                };
        JComboBox genderList = new JComboBox (gender_options);
        genderList.setBounds(600,255,130,20);
        f.add(genderList);

        // Creating the Labels and TextFields for Email
        email = new JLabel("Email ID: *");
        email.setBounds(400,275,120,80);
        email.setFont(new Font("Calibri", Font.ITALIC, 24));
        email.setForeground(new Color(255,255,255));
        email1 = new JTextField();
        email1.setBounds(600,300,250,25);
        email1.setBorder(border);
        email1.setBackground(new Color(221,221,221));

        // Key Listener for the Email TextField to limit the input to 30 characters
        email1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (email1.getText().length() >= 30 )
                    e.consume();
            }
        });

        // Listener to check if the Email is valid or not by calling the function isValidEmail()
        email1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(!isValidEmailAddress(email1.getText()))
                    email1.setBorder(BorderFactory.createLineBorder(Color.red));
                else
                    email1.setBorder(BorderFactory.createLineBorder(Color.green));
            }
        });
        f.add(email1);

        // Creating the Labels and TextFields for Door Number
        doorno = new JLabel("Door Number: *");
        doorno.setBounds(400,320,200,80);
        doorno.setFont(new Font("Calibri", Font.ITALIC, 24));
        doorno.setForeground(new Color(255,255,255));
        doorno1 = new JTextField();
        doorno1.setBounds(600,345,60,25);
        doorno1.setBorder(border);
        doorno1.setBackground(new Color(221,221,221));

        // Key Listener for the Door Number TextField to limit the input to 10 characters
        doorno1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (doorno1.getText().length() >= 10 )
                    e.consume();
            }
        });
        f.add(doorno1);

        // Creating the Labels and TextFields for Street
        strname = new JLabel("Street Name: *");
        strname.setBounds(400,365,150,80);
        strname.setFont(new Font("Calibri", Font.ITALIC, 24));
        strname.setForeground(new Color(255,255,255));
        strname1 = new JTextField();
        strname1.setBounds(600,387,250,25);
        strname1.setBorder(border);
        strname1.setBackground(new Color(221,221,221));

        // Key Listener for the Street Name TextField to limit the input to 70 characters
        strname1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (strname1.getText().length() >= 70 )
                    e.consume();
            }
        });
        f.add(strname1);

        // Creating the Labels and TextFields for City
        city = new JLabel("City: *");
        city.setBounds(400,410,150,80);
        city.setFont(new Font("Calibri", Font.ITALIC, 24));
        city.setForeground(new Color(255,255,255));
        city1 = new JTextField();
        city1.setBounds(600,430,150,25);
        city1.setBorder(border);
        city1.setBackground(new Color(221,221,221));

        // Key Listener for the City TextField to limit the input to 25 characters
        city1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (city1.getText().length() >= 25 )
                    e.consume();
            }
        });
        f.add(city1);

        // Creating the Labels and TextFields for Pin Code
        pincode = new JLabel("Pincode: *");
        pincode.setBounds(400,455,150,80);
        pincode.setFont(new Font("Calibri", Font.ITALIC, 24));
        pincode.setForeground(new Color(255,255,255));
        pincode1 = new JTextField();
        pincode1.setBounds(600,480,60,25);
        pincode1.setBorder(border);
        pincode1.setBackground(new Color(221,221,221));
        // Key Listener for the Pin Code TextField to limit the input to 6 characters
        pincode1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (pincode1.getText().length() >= 6 )
                    e.consume();
            }
        });

        // Key Listener for the Pin Code TextField to limit the input to 6 characters
        pincode1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    long number = Long.parseLong(pincode1.getText());
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(f , "Only Numbers Allowed!");
                    pincode1.setText("");
                }
            }
        });
        f.add(pincode1);

        // Creating the Labels and TextFields for State
        state = new JLabel("State: *");
        state.setBounds(400,500,150,80);
        state.setFont(new Font("Calibri", Font.ITALIC, 24));
        state.setForeground(new Color(255,255,255));
        state1 = new JTextField();
        state1.setBounds(600,523,150,25);
        state1.setBorder(border);
        state1.setBackground(new Color(221,221,221));

        // Key Listener for the State TextField to limit the input to 30 characters
        state1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (state1.getText().length() >= 30 )
                    e.consume();
            }
        });
        f.add(state1);


        // Adding the Labels to the Frame
        f.add(fname);f.add(mname);f.add(lname);f.add(age);
        f.add(gender);f.add(email);f.add(doorno);f.add(strname);
        f.add(city);f.add(pincode);f.add(state);

        // Add a label with the text "Fields with '*' are required" in different font color and style
        JLabel required = new   JLabel("  Fields with '*' are mandatory to be filled");
        required.setBounds(20,50,350,80);
        required.setFont(new Font("Calibri", Font.ITALIC, 20));
        // Set the color of the text to #CD001A
        required.setForeground(new Color(205,0,26));
        required.setOpaque(true);
        required.setBackground(Color.WHITE);
        // Adding Border to the label
        required.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
        f.add(required);

        // Creating the button to reset all the fields
        JButton reset = new JButton("Reset");
        reset.setBounds(720,600,100,30);
        reset.addActionListener(e -> {
            fname1.setText("");
            mname1.setText("");
            lname1.setText("");
            age1.setText("");
            email1.setText("");
            doorno1.setText("");
            strname1.setText("");
            city1.setText("");
            pincode1.setText("");
            state1.setText("");
            genderList.setSelectedItem("Please Select");
        });

        // Change the reset button style
        reset.setFont(new Font("Calibri", Font.ITALIC, 20));
        reset.setBackground(Color.WHITE);
        reset.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
        reset.setFont(new Font("Arial", Font.BOLD, 12));
        // Reset Button Text Color
        reset.setForeground(Color.blue);
        f.add(reset);


        // Creating the button to submit the form
        JButton submit = new JButton("Proceed");
        submit.setBounds(520,600,100,30);
        submit.addActionListener(e -> {
            // Check wheater all the fields are filled or not
            if (fname1.getText().isEmpty() || lname1.getText().isEmpty() || age1.getText().isEmpty()
                    || email1.getText().isEmpty() || doorno1.getText().isEmpty() || strname1.getText().isEmpty() || city1.getText().isEmpty()
                    || pincode1.getText().isEmpty() || state1.getText().isEmpty())
                // Show the fields that are empty
                JOptionPane.showMessageDialog(f, "Please fill all the fields!");

            // If all are filled then add the data into the database
            else {
                    // Invalid age
                if (Integer.parseInt(age1.getText()) > 100)
                    JOptionPane.showMessageDialog(f, "Age must be within than 100!");
                else {
                    // Get the values from the text fields
                    String FName = fname1.getText();
                    String MName = mname1.getText();
                    String LName = lname1.getText();
                    String Age = age1.getText();
                    String Email = email1.getText();
                    String DoorNo = doorno1.getText();
                    String StrName = strname1.getText();
                    String City = city1.getText();
                    String Pincode = pincode1.getText();
                    String State = state1.getText();
                    String Gender = genderList.getSelectedItem().toString();

                    try
                    {
                        Connection connection = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
                                "system", "orcl");

                        String query = "INSERT INTO TBLUSERS(FIRST_NAME, MIDDLE_NAME,LAST_NAME,AGE,GENDER,EMAIL_ID,DOOR_NO,STR_NAME,CITY,PINCODE,STATE) values('" + FName + "','" + MName + "','" + LName + "','" +
                                Age + "','" + Gender + "','" + Email + "','" + DoorNo + "','" + StrName + "','" + City + "','" + Pincode + "','" + State + "')";

                        Statement sta = connection.createStatement();
                        int x = sta.executeUpdate(query);
                        if (x > 0) {
                            JOptionPane.showMessageDialog(submit,
                                    "Welcome, " + "Your details is registered successfully! ");
                            submit.setVisible(false);
                            reset.setVisible(false);
                            f.setVisible(false);
                            f.dispose();
                        }
                        connection.close();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });

        // Change submit button style
        submit.setBackground(Color.WHITE);
        submit.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
        submit.setFont(new Font("Arial", Font.BOLD, 12));
        // Submit Button Text Color
        submit.setForeground(Color.blue);
        f.add(submit);


        // Creating the button to go back to the previous frame
        JButton back = new JButton("Back");
        back.setBounds(320,600,100,30);
        // To move to previous page
        back.addActionListener(e -> {
            f.dispose();
            Login ob = new Login();
            ob.main(null);
        }
        );
        // Change back button style
        back.setBackground(Color.WHITE);
        back.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
        back.setFont(new Font("Arial", Font.BOLD, 12));
        // Back Button Text Color
        back.setForeground(Color.blue);
        f.add(back);

        // Frame Properties
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1600,800);
        f.setLayout(null);
        f.setVisible(true);
    }

    //Method to validate if there exist an actual email address
    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    //Method to validate if there exist an actual phone number
    public boolean isValidPhoneNumber(String phone) {
        String ePattern = "^[0-9]{10}$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(phone);
        return m.matches();
    }

    // Main Method
    public  static void  main(String[] args)
    {
        // Anonymous Object Creation
        new GetBasicDetails();
    }

}
