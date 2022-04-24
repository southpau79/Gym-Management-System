package Payment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static javax.swing.JOptionPane.showMessageDialog;
public class HomePage
{
    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    // Function to check the valid card number by using Luhr Algorithm
    public boolean isValidCardNumber(String cardNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    // Function that checks if user enters correct CVV
    public boolean isValidCVV(String cvv)
    {
        String ePattern = "^[0-9]{3}$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(cvv);
        return m.matches();
    }

    public HomePage()
    {
        // Create a new frame
        JFrame f =new JFrame("Forget Password");
        f.getContentPane().setBackground(new Color(230,230,0));
        f.setBackground(Color.cyan);

        // Create a new Label for the Title
        JLabel title = new JLabel("Payment Page",JLabel.CENTER);
        title.setBounds(500,10,400,50);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        title.setForeground(Color.BLUE);
        f.add(title);


        JLabel email,firstname,nameoncard,cardnumber,expiry,cvv;
        JTextField email_field,firstname_field,nameoncard_field,cardnumber_field,expiry_field,cvv_field;

        //Create a  JLabel with text "Personal Information"
        JLabel info_label = new JLabel("Personal Information");
        info_label.setBounds(565,70,400,50);
        info_label.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        info_label.setForeground(Color.red);
        f.add(info_label);


        email = new JLabel("Email ID: *");
        email.setBounds(400,100,120,80);
        email.setFont(new Font("Calibri", Font.ITALIC, 18));
        email_field = new JTextField();
        email_field.setBounds(400,150,200,25);

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

        firstname = new JLabel("FULL NAME");
        firstname.setBounds(400,150,120,80);
        firstname.setFont(new Font("Calibri",Font.ITALIC,18));
        firstname_field = new JTextField();
        firstname_field.setBounds(400,200,200,25);
        // Key Listener for the Email TextField to limit the input to 30 characters
        firstname_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (email_field.getText().length() >= 30 )
                    e.consume();
            }
        });
        f.add(firstname);
        f.add(firstname_field);

        //Create a  JLabel with text "Personal Information"
        JLabel info_label1 = new JLabel("Payment Information");
        info_label1.setBounds(565,225,400,50);
        info_label1.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        info_label1.setForeground(Color.red);
        f.add(info_label1);

        nameoncard = new JLabel("NAME ON CARD");
        nameoncard.setBounds(400,250,120,80);
        nameoncard.setFont(new Font("Calibri",Font.ITALIC,18));
        nameoncard_field = new JTextField();
        nameoncard_field.setBounds(400,300,200,25);
        // Key Listener for the Email TextField to limit the input to 30 characters
        nameoncard_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (nameoncard_field.getText().length() >= 30 )
                    e.consume();
            }
        });
        f.add(nameoncard);
        f.add(nameoncard_field);

        cardnumber = new JLabel("CARD NUMBER");
        cardnumber.setBounds(400,300,120,80);
        cardnumber.setFont(new Font("Calibri",Font.ITALIC,18));
        cardnumber_field = new JTextField();
        cardnumber_field.setBounds(400,350,200,25);


        cardnumber_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (cardnumber_field.getText().length() >= 16 )
                    e.consume();
            }
        });

        // Create a JLabel with text Card Type and set its setBounds
        JLabel cardtype = new JLabel("");
        cardtype.setBounds(650,325,120,80);
        cardtype.setFont(new Font("Calibri",Font.ITALIC,18));
        f.add(cardtype);
        f.setVisible(false);

        // Key Listener for the Card Number TextField to call the isValidCardNumber function after every key stroke
        cardnumber_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (isValidCardNumber(cardnumber_field.getText()))
                    cardnumber_field.setBorder(BorderFactory.createLineBorder(Color.green));
                else
                    cardnumber_field.setBorder(BorderFactory.createLineBorder(Color.red));
            }
        });
cardnumber_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (cardnumber_field.getText().length() == 4 || cardnumber_field.getText().length() == 9 || cardnumber_field.getText().length() == 14)
                {
                    if (cardnumber_field.getText().charAt(0) == '4')
                            // Show the image of VISA card from the img folder of Payment Package
                        cardtype.setIcon(new ImageIcon("C:\\Users\\vikaa\\IdeaProjects\\Gym-Management-System\\Payment\\img\\visa.png"));
                    else if (cardnumber_field.getText().startsWith("51") || cardnumber_field.getText().substring(0, 2).equals("52") || cardnumber_field.getText().substring(0, 2).equals("53") || cardnumber_field.getText().substring(0, 2).equals("54") || cardnumber_field.getText().substring(0, 2).equals("55"))
                        cardtype.setIcon(new ImageIcon("C:\\Users\\vikaa\\IdeaProjects\\Gym-Management-System\\Payment\\img\\mastercard.jpg"));
                    else if (cardnumber_field.getText().startsWith("34") || cardnumber_field.getText().substring(0, 2).equals("37"))
                        cardtype.setIcon(new ImageIcon("C:\\Users\\vikaa\\IdeaProjects\\Gym-Management-System\\Payment\\img\\amex.png"));
                    else if(cardnumber_field.getText().charAt(0) == '6')
                        cardtype.setIcon(new ImageIcon("C:\\Users\\vikaa\\IdeaProjects\\Gym-Management-System\\Payment\\img\\discover.png"));
                    // Check if the card number is a Diners Club Card number
                    else if (cardnumber_field.getText().startsWith("300") || cardnumber_field.getText().startsWith("301") || cardnumber_field.getText().startsWith("302") || cardnumber_field.getText().startsWith("303") || cardnumber_field.getText().startsWith("304") || cardnumber_field.getText().startsWith("305") || cardnumber_field.getText().startsWith("309") || cardnumber_field.getText().startsWith("36") || cardnumber_field.getText().startsWith("38") || cardnumber_field.getText().startsWith("39"))
                        cardtype.setIcon(new ImageIcon("C:\\Users\\vikaa\\IdeaProjects\\Gym-Management-System\\Payment\\img\\dinersclub.png"));
                    else
                        cardtype.setIcon(new ImageIcon("C:\\Users\\vikaa\\IdeaProjects\\Gym-Management-System\\Payment\\img\\invalid.png"));
                }
            }
        });


        // Key Listener for Card Number to allow only numbers
        cardnumber_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    f.getToolkit().beep();
                    e.consume();
                }
            }
        });


        f.add(cardnumber);
        f.add(cardnumber_field);

        expiry = new JLabel("EXPIRATION");
        expiry.setBounds(400,350,120,80);
        expiry.setFont(new Font("Calibri",Font.ITALIC,18));
        expiry_field = new JTextField();
        expiry_field.setBounds(400,400,35,25);
        expiry_field.setText("MM");
        // Key Listener for the Email TextField to limit the input to 30 characters
        expiry_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (expiry_field.getText().length() >= 2 )
                    e.consume();
            }
        });

        // Key Listener for the Email TextField to reset the field when the user clicks on it
        expiry_field.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                expiry_field.setText("");
            }
        });

        JTextField expiry_field1 = new JTextField();
        expiry_field1.setBounds(450,400,35,25);
        expiry_field1.setText("YYYY");
        // Key Listener for the Email TextField to limit the input to 30 characters
        expiry_field1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (expiry_field1.getText().length() >= 30 )
                    e.consume();
            }
        });

        // Key Listener for the Email TextField to reset the field when the user clicks on it
        expiry_field1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                expiry_field1.setText("");
            }
        });

        f.add(expiry);
        f.add(expiry_field);
        f.add(expiry_field1);

        cvv = new JLabel("CVV");
        cvv.setBounds(600,350,120,80);
        cvv.setFont(new Font("Calibri",Font.ITALIC,18));
        cvv_field = new JTextField();
        cvv_field.setBounds(600,400,50,25);
        // Key Listener for the Email TextField to limit the input to 30 characters
        cvv_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (cvv_field.getText().length() >= 3 )
                    e.consume();
            }
        });

        // Key Listener for CVV to call the checkvalidcvv method as the user types in the field

        cvv_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e)
            {
                    if(isValidCVV(cvv_field.getText()))
                        // MAKE THE BORDER OF THE FIELD GREEN
                        cvv_field.setBorder(BorderFactory.createLineBorder(Color.green));
                    else
                        cvv_field.setBorder(BorderFactory.createLineBorder(Color.red));
                }
        });
        f.add(cvv);
        f.add(cvv_field);

        JCheckBox terms = new JCheckBox("By checking this box, I agree to the Terms & Conditions and the Privacy Policy.");
        terms.setBounds(400,450,500,25);
        f.add(terms);

        // Action Listener for the Checkbox to check
        terms.addActionListener(e->
        {
                if (terms.isSelected()) {
                    showMessageDialog(f, "Terms & Conditions & Privacy Policy are accepted");
                } else {
                    showMessageDialog(f, "Terms & Conditions & Privacy Policy are not accepted");
                }
        });

        JButton submit = new JButton("Submit");
        submit.setBounds(450,500,100,50);
        submit.setFont(new Font("Calibri",Font.BOLD,18));
        submit.setBackground(Color.green);
        submit.setForeground(Color.white);
        f.add(submit);

        // Action Listener for the Submit Button
        submit.addActionListener(e ->
        {
            if(firstname_field.getText().equals("") || email_field.getText().equals("") || nameoncard_field.getText().equals("") || cardnumber_field.getText().equals("") || expiry_field.getText().equals("") || expiry_field1.getText().equals("") || cvv_field.getText().equals(""))
            {
                showMessageDialog(f, "Please fill all the fields");
            }
            else
            {
                if(!isValidEmailAddress(email_field.getText()))
                {
                    showMessageDialog(f,"Invalid Email Address. Please enter a valid email address");
                    email_field.requestFocus();
                }

                if(!terms.isSelected())
                {
                    showMessageDialog(f,"Please accept the Terms & Conditions & Privacy Policy");
                    terms.requestFocus();
                }

                if(!(firstname_field.getText().equals("") || email_field.getText().equals("") || nameoncard_field.getText().equals("") || cardnumber_field.getText().equals("") || expiry_field.getText().equals("") || expiry_field1.getText().equals("") || cvv_field.getText().equals("")))
                {
                    if(isValidEmailAddress(email_field.getText()) && terms.isSelected())
                    {
                        showMessageDialog(f,"Your payment has been successfully processed");
                    }
                }
                else
                {
                    showMessageDialog(f,"Payment Failed! Retry Again");
                    // Set all fileds to empty
                    firstname_field.setText("");
                    email_field.setText("");
                    nameoncard_field.setText("");
                    cardnumber_field.setText("");
                    expiry_field.setText("");
                    expiry_field1.setText("");
                    cvv_field.setText("");
                    terms.setSelected(false);
                    firstname_field.requestFocus();
                }
            }
        });

        submit.setVisible(true);

        // Frame Properties
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1600,800);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new HomePage();
    }
}
