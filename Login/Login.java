package Login;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login
{
    // Driver Code
	public static void main(String args[])
	{
		Login pe=new Login();
	}

    public Login()
    {
        Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
        int x=screensize.height;
        int y=screensize.width;

        final JFrame f1 =new JFrame("Login Page");
        f1.setResizable(false);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setBounds((y/2)-250,(x/2)-250,500,500);
        f1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel title=new JLabel("        GYM MANAGEMENT SYSTEM");
        title.setLayout(null);
        title.setBounds(35,-200,500,500);
        title.setFont(new Font("Serif", Font.PLAIN, 25));

        final JTextField usern=new JTextField("User-name");
        final JPasswordField pass=new JPasswordField("Password");
        usern.setBounds(90,210,300,50 );
        pass.setBounds(90,270,300,50);

        ImageIcon icon = new ImageIcon("img/login.jpg");
        ImageIcon icon2 = new ImageIcon("img/login2.jpg");

        JButton loginButton =new JButton("Login");
        loginButton.setBounds(160,350,180,60);
        loginButton.setBorderPainted(false);
        loginButton.setBorder(null);
        loginButton.setMargin(new Insets(0, 0, 0, 0));
        loginButton.setContentAreaFilled(false);
        loginButton.setIcon(icon2);
        loginButton.setRolloverIcon(icon2);
        loginButton.setPressedIcon(icon);
        loginButton.setDisabledIcon(icon);

        // Lower JPanel
        JPanel me=new JPanel();
        JLabel me2=new JLabel("Developed by Yogesh.S and Sree Krishnan.T , B.E CSE , Mepco Schlenk Engineering College");
        me2.setFont(new Font("Serif", Font.PLAIN, 12));
        me.setBackground(Color.white);
        me.setBounds(0,435,500,25);
        me.add(me2);

        ActionListener al=new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                String user=usern.getText();
                String passw=String.valueOf(pass.getPassword());
                /*Username*/
                if(user.equals("admin") && passw.equals("admin"))
                {
                    System.out.println("Login Successful");
                    f1.dispose();
                    loginsucc();
                }
                else if (user.trim().equals("") || passw.trim().equals(""))
                    JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty", "Empty Fields", 2);
                else{
                    JOptionPane.showMessageDialog(null, "Wrong Username Or Password");
                    usern.requestFocusInWindow();
                }
            }
        };


        loginButton.addActionListener(al);

        usern.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                String temp=usern.getText();
                if(temp.equals("User-name"))
                    usern.setText("");}
        });

        pass.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent ae)
            {
                pass.setText("");
            }

        });

        usern.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode()==KeyEvent.VK_ENTER)
                {
                    pass.requestFocusInWindow();
                }
            }
        });

        usern.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usern.selectAll();
            }

        });

        pass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pass.selectAll();
            }

        });

        pass.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode()==KeyEvent.VK_ENTER)
                {
                    String user=usern.getText();
                    String passw=String.valueOf(pass.getPassword());
                    if(user.equals("admin")&&passw.equals("admin"))
                    {
                        f1.dispose();
                        loginsucc();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Wrong Username Or Password");
                        usern.requestFocusInWindow();
                    }
                }
            }
        });
        f1.setLayout(null);
        f1.add(title);
        f1.add(usern);
        f1.add(pass);
        f1.setVisible(true);
        f1.add(loginButton);
        f1.add(me);
    }

    public void loginsucc()
    {
        //Add  code after successful login
/*
	add code to run after login is successful
	*/

        System.out.println("Login Successful Running post Code");
    }
}