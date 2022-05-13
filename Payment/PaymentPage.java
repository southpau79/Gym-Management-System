package Payment;


import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import Payment.Mailer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Payment.GetDetails.generateID;
import static javax.swing.JOptionPane.showMessageDialog;

public class PaymentPage
{

    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    // Function to check the valid card number by using Luhr Algorithm
    public static boolean isValidCardNumber(String cardNumber) {
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
    public static boolean isValidCVV(String cvv)
    {
        String ePattern = "^[0-9]{3}$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(cvv);
        return m.matches();
    }


    private static boolean isValidExpiryDate(String mm, String yyyy)
    {
        if(mm.length() != 2 || yyyy.length() != 4)
            return false;
        int month = Integer.parseInt(mm);
        int year = Integer.parseInt(yyyy);
        if(month < 1 || month > 12)
            return false;
        if(year < getCurrentYear())
            return false;
        return true;
    }

    // Function to get the current year from the internet
    private static int getCurrentYear()
    {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    // Function to generate a transaction id with alphanumeric characters
    private static String generateTransactionId()
    {
        StringBuilder sb = new StringBuilder();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        int charactersLength = characters.length();
        for (int i = 0; i < 10; i++)
        {
            int index = (int) (Math.random() * charactersLength);
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }


    public static void main(String[] args)
    {
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
        JTextField email_field,firstname_field,nameoncard_field,cardnumber_field,expiry_field;
        JPasswordField cvv_field;

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

        // Key Listener for the Email TextField to allow only alphabets
        firstname_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_SPACE)|| (c == KeyEvent.VK_DELETE))) {
                    f.getToolkit().beep();
                    e.consume();
                }
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

        // Key Listener to allow only alphabets and space
        nameoncard_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_SPACE)|| (c == KeyEvent.VK_DELETE))) {
                    f.getToolkit().beep();
                    e.consume();
                }
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
            public void keyReleased(KeyEvent e)
            {
                if (cardnumber_field.getText().length() == 4 || cardnumber_field.getText().length() == 9 || cardnumber_field.getText().length() == 14)
                {
                    if (cardnumber_field.getText().charAt(0) == '4') {
                        cardtype.setIcon(new ImageIcon("Payment/img/visa.png"));
                    }
                    else if (cardnumber_field.getText().startsWith("51") || cardnumber_field.getText().substring(0, 2).equals("52") || cardnumber_field.getText().substring(0, 2).equals("53") || cardnumber_field.getText().substring(0, 2).equals("54") || cardnumber_field.getText().substring(0, 2).equals("55"))
                        cardtype.setIcon(new ImageIcon("Payment/img/mastercard.jpg"));
                    else if (cardnumber_field.getText().startsWith("34") || cardnumber_field.getText().substring(0, 2).equals("37"))
                        //Show the image of AMEX card from the URL
                        cardtype.setIcon(new ImageIcon("Payment/img/amex.png"));
                    else if (cardnumber_field.getText().charAt(0) == '6')
                        cardtype.setIcon(new ImageIcon("Payment/img/discover.png"));
                        // Check if the card number is a Diners Club Card number
                    else if (cardnumber_field.getText().startsWith("300") || cardnumber_field.getText().startsWith("301") || cardnumber_field.getText().startsWith("302") || cardnumber_field.getText().startsWith("303") || cardnumber_field.getText().startsWith("304") || cardnumber_field.getText().startsWith("305") || cardnumber_field.getText().startsWith("309") || cardnumber_field.getText().startsWith("36") || cardnumber_field.getText().startsWith("38") || cardnumber_field.getText().startsWith("39"))
                        cardtype.setIcon(new ImageIcon("Payment/img/dinersclub.png"));
                    else
                        cardtype.setIcon(new ImageIcon("Payment/img/invalid.png"));
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
                if (expiry_field1.getText().length() >= 4)
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
        cvv_field = new JPasswordField();
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

                if(!isValidExpiryDate(expiry_field.getText(),expiry_field1.getText()))
                {
                    showMessageDialog(f,"Invalid Expiry Date. Please enter a valid expiry date");
                    expiry_field.requestFocus();
                }

                if(!isValidCVV(cvv_field.getText()))
                {
                    showMessageDialog(f,"Invalid CVV. Please enter a valid CVV");
                    cvv_field.requestFocus();
                }

                if(!terms.isSelected())
                {
                    showMessageDialog(f,"Please accept the Terms & Conditions & Privacy Policy");
                    terms.requestFocus();
                }

                if(!(firstname_field.getText().equals("") || email_field.getText().equals("") || nameoncard_field.getText().equals("") || cardnumber_field.getText().equals("") || expiry_field.getText().equals("") || expiry_field1.getText().equals("") || cvv_field.getText().equals("")))
                {
                    if(isValidEmailAddress(email_field.getText()) && terms.isSelected() &&
                            isValidExpiryDate(expiry_field.getText(),expiry_field1.getText()) && isValidCardNumber(cardnumber_field.getText()) && isValidCVV(cvv_field.getText()))
                    {
                        try
                        {
                            Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
                            // Select the username from the tbllogin table where the user id is the same as the user id from the database
                            String sql2 ="SELECT a.USER_NAME,a.PASSWORD,b.FIRST_NAME,b.LAST_NAME, b.EMAIL_ID, c.AMOUNT , c.MEMBERSHIP_ID FROM  TBLLOGIN a, TBLUSERS b , TBLMEMBERSHIP c WHERE b.EMAIL_ID = '" +email_field.getText() +"' AND a.USER_ID = b.USER_ID";
                            Statement sta = con2.createStatement();
                            int y = sta.executeUpdate(sql2);

                            if (y > 0)
                            {
                                // Get the username from the database and call the Mail class to send the username to the email
                                ResultSet rs2 = sta.executeQuery(sql2);
                                while (rs2.next()) {

                                    String first_name = rs2.getString(3);
                                    String last_name = rs2.getString(4);
                                    String full_name = first_name + " " + last_name;
                                    String email1 = rs2.getString(5);
                                    String amount = rs2.getString(6);
                                    String membership_id = rs2.getString(7);
                                    String subject = "Your payment to Gym Vale has been received successfully";

                                    // Get the current date and time from the oracle database
                                    String sql3 = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI:SS') FROM DUAL";
                                    Statement sta3 = con2.createStatement();
                                    ResultSet rs3 = sta3.executeQuery(sql3);
                                    String current_date_time = null;
                                    while (rs3.next()) {
                                        current_date_time = rs3.getString(1);
                                    }


                                    String message = "\nHi " + full_name + ",\n\nThis email confirms that your payment of Rs." + amount + "/- on "
                                            + current_date_time + " has been processed successfully.\n\n Please find the invoice attached below for proof of the transaction." +
                                            " \n\n Thank you for your payment!" +
                                            "\n\nThank you,\nGym Vale Team ";


                                    // GENERATE THE PDF CONTEXTS
                                    String path = "Payment/PDF Invoice/Invoice.pdf";
                                    Document document = new Document();
                                    PdfWriter.getInstance(document, new FileOutputStream(path));
                                    document.open();

                                    // Add the title to the document
                                    Paragraph title1 = new Paragraph("Payment Confirmation");
                                    title1.setAlignment(Element.ALIGN_CENTER);
                                    title1.setFont(FontFactory.getFont(FontFactory.COURIER, 36, Font.BOLD));
                                    document.add(title1);

                                    Paragraph title2 = new Paragraph("Thank You! Your payment has been received.");
                                    title2.setAlignment(Element.ALIGN_CENTER);
                                    title2.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD, 25, Font.BOLD));
                                    document.add(title2);

                                    //Store the generateTransactionID in a variable
                                    String transactionID = generateTransactionId();
                                    Paragraph title3 = new Paragraph("Transaction ID: " + transactionID);
                                    title3.setAlignment(Element.ALIGN_CENTER);
                                    title3.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.PLAIN));
                                    document.add(title3);

                                    Paragraph title4 = new Paragraph("\nPayment Details");
                                    title4.setAlignment(Element.ALIGN_CENTER);
                                    title4.setFont(FontFactory.getFont(FontFactory.TIMES, 16, Font.PLAIN));
                                    document.add(title4);


                                    Paragraph title7 = new Paragraph("Payment Made on  : " + current_date_time +
                                            "\n Payment Amount : Rs." + amount + "/-");
                                    title7.setAlignment(Element.ALIGN_CENTER);
                                    title7.setFont(FontFactory.getFont(FontFactory.TIMES, 16, Font.PLAIN));
                                    document.add(title7);

                                    Paragraph title9 = new Paragraph("Your Membership ID is : " + membership_id +
                                            "\n Please use this Membership ID to  login to the Gym Vale application.");
                                    title9.setAlignment(Element.ALIGN_CENTER);
                                    title9.setFont(FontFactory.getFont(FontFactory.TIMES, 16, Font.PLAIN));
                                    document.add(title9);

                                    Paragraph title5 = new Paragraph("\nPlease wait for some time for the amount to get processed and" +
                                            " show in your account");
                                    title5.setAlignment(Element.ALIGN_CENTER);
                                    title5.setFont(FontFactory.getFont(FontFactory.TIMES, 16, Font.ITALIC));
                                    document.add(title5);

                                    Paragraph title6 = new Paragraph("Please contact us on our email address for any query.\n\n Thank You for using our services.");
                                    title6.setAlignment(Element.ALIGN_CENTER);
                                    title6.setFont(FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 16, Font.ITALIC));
                                    document.add(title6);
                                    document.close();

                                    try {
                                        String id = generateID();
                                        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
                                        PreparedStatement pst;
                                        pst = connection.prepareStatement("insert into TBLPAYMENT values(?,?,?,?)");
                                        pst.setString(1, id);
                                        pst.setString(2, current_date_time);
                                        pst.setString(3, transactionID);
                                        pst.setString(4, membership_id);
                                        pst.executeUpdate();
                                        connection.close();
                                    } catch (SQLException e1) {
                                        e1.printStackTrace();
                                    }

                                    // Call the sendMail method to send the email
                                    Mailer mailer = new Mailer();
                                    mailer.Send_Email(email1, subject, message);


                                    showMessageDialog(null, "Your payment has been processed successfully");
                                    // Set all the fields to setEditable to false
                                    submit.setVisible(false);
                                    f.dispose();
                                    // Close the connection
                                    con2.close();
                                }
                            } else
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
                        catch (Exception e1)
                        {
                            e1.printStackTrace();
                        }

                    }
                }
            }});

        submit.setVisible(true);

        // Frame Properties
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1600,800);
        f.setLayout(null);
        f.setVisible(true);
    }

}

