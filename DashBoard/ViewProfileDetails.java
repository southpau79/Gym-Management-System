package com.company;

import javax.swing.*;
import java.awt.*;

public class ViewProfileDetails
{
    public ViewProfileDetails()
    {
        JFrame f =new JFrame("User Profile");
        f.getContentPane().setBackground(new Color(173,216,230));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1600,800);
        f.setLayout(null);
        f.setVisible(true);

        // Create a panel on the top of the frame that contains a text label with  UserPortal
        JPanel topPanel = new JPanel();
        topPanel.setBounds(0,0,1600,50);
        // Set the background color of the panel to #00308f
        topPanel.setBackground(new Color(0,48,143));
        JLabel topLabel = new JLabel("User Profile");
        // Set the font colour of User Portal to white
        topLabel.setForeground(Color.white);
        topLabel.setFont(new Font("Serif", Font.BOLD, 24));
        topPanel.add(topLabel);
        f.add(topPanel);

        // Add a text label with the Name of the user
        JLabel nameLabel = new JLabel("User Name: ");
        nameLabel.setBounds(400,100,150,30);
        nameLabel.setFont(new Font("Serif", Font.BOLD, 20));
        f.add(nameLabel);
        // Add a text field that will display the name of the user at the right of the label
        JTextField nameField = new JTextField();
        nameField.setBounds(600,100,200,30);
        nameField.setFont(new Font("Serif", Font.BOLD, 20));
        nameField.setVisible(true);
        f.add(nameField);

        // Add a text label with the Password of the user
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(400,150,150,30);
        passwordLabel.setFont(new Font("Serif", Font.BOLD, 20));
        f.add(passwordLabel);
        // Add a text field that will display the password of the user at the right of the label
        JTextField passwordField = new JTextField();
        passwordField.setBounds(600,150,200,30);
        passwordField.setFont(new Font("Serif", Font.BOLD, 20));
        passwordField.setVisible(true);
        f.add(passwordField);

        // Add a text label with the Email of the user
        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setBounds(400,200,150,30);
        emailLabel.setFont(new Font("Serif", Font.BOLD, 20));
        emailLabel.setVisible(true);
        f.add(emailLabel);
        // Add a text field that will display the email of the user at the right of the label
        JTextField emailField = new JTextField();
        emailField.setBounds(600,200,200,30);
        emailField.setFont(new Font("Serif", Font.BOLD, 20));
        emailField.setVisible(true);
        f.add(emailField);


        // Add a text label with the Phone Number of the user
        JLabel phoneLabel = new JLabel("Phone Number: ");
        phoneLabel.setBounds(400,250,150,30);
        phoneLabel.setFont(new Font("Serif", Font.BOLD, 20));
        phoneLabel.setVisible(true);
        f.add(phoneLabel);
        // Add a text field that will display the phone number of the user at the right of the label
        JTextField phoneField = new JTextField();
        phoneField.setBounds(600,250,200,30);
        phoneField.setFont(new Font("Serif", Font.BOLD, 20));
        phoneField.setVisible(true);
        f.add(phoneField);

        // Add a text label with the Address of the user
        JLabel addressLabel = new JLabel("Address: ");
        addressLabel.setBounds(400,300,150,30);
        addressLabel.setFont(new Font("Serif", Font.BOLD, 20));
        addressLabel.setVisible(true);
        f.add(addressLabel);
        // Add a text field that will display the address of the user at the right of the label
        JTextField addressField = new JTextField();
        addressField.setBounds(600,300,200,30);
        addressField.setFont(new Font("Serif", Font.BOLD, 20));
        addressField.setVisible(true);
        f.add(addressField);


    }

    public static void main(String[] args) {
        new ViewProfileDetails();
    }
}
