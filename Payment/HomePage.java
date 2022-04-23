package Payment;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HomePage
{
    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
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

        // Frame Properties
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1600,800);
        f.setLayout(null);
        f.setVisible(true);

        JLabel email,firstname,nameoncard,cardnumber,expiry,cvv;
        JTextField email_field,firstname_field,nameoncard_field,cardnumber_field,expiry_field,cvv_field;
        Border border = BorderFactory.createLineBorder(Color.BLACK);

        // Creating the Labels & Creating the TextFields row-wise
        email = new JLabel("EMAIL ADDRESS");
        email.setBounds(400, 150, 170, 80);
        email.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(email);

        email_field = new JTextField();
        email_field.setBounds(600, 173, 200, 25);
        // Key Listener for the First Name TextField to limit the input to 30 characters
        email_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (email_field.getText().length() >= 30 )
                    e.consume();
            }
        });

        email_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    f.getToolkit().beep();
                    e.consume();
                }
            }
        });
        f.add(email_field);
    }

    public static void main(String[] args) {
        new HomePage();
    }
}
