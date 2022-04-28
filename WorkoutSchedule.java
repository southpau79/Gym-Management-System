package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorkoutSchedule {
    public WorkoutSchedule()
    {
        JFrame f = new JFrame("Workout Schedule");
        f.getContentPane().setBackground(new Color(255, 128, 102));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800, 800);
        f.setLayout(null);
        f.setVisible(true);
        JLabel title = new JLabel("Workout Schedule");
        title.setBounds(250, 30, 1000, 30);
        f.setLocationRelativeTo(null);


        title.setFont(new Font("Arial", Font.BOLD, 30));
        f.add(title);
        JLabel workout_id = new JLabel("Workout ID");
        JLabel instructor_id = new JLabel("Instructor ID");
        JLabel day = new JLabel("Day");
        JLabel start_time = new JLabel("Start Time");
        JLabel end_time = new JLabel("End Time");
        JLabel workout_type = new JLabel("Workout Type");
        JLabel workout_description = new JLabel("Workout Description");

        JTextField workout_id_text = new JTextField();
        JTextField instructor_id_text = new JTextField();
        JTextField day_text = new JTextField();
        JTextField start_time_text = new JTextField();
        JTextField end_time_text = new JTextField();
        JTextField workout_type_text = new JTextField();
        TextArea workout_description_text = new TextArea("",180,90,TextArea.SCROLLBARS_VERTICAL_ONLY);

        JButton schedule_button = new JButton("Schedule");
        JButton clear_button = new JButton("Clear");
        JButton exit_button = new JButton("Exit");

        workout_id.setBounds(50, 100, 100, 30);
        f.add(workout_id);

        workout_id_text.setBounds(200, 100, 200, 30);
        f.add(workout_id_text);

        instructor_id.setBounds(50, 150, 100, 30);
        f.add(instructor_id);

        instructor_id_text.setBounds(200, 150, 200, 30);
        f.add(instructor_id_text);

        day.setBounds(50, 200, 100, 30);
        f.add(day);

        day_text.setBounds(200, 200, 200, 30);
        f.add(day_text);

        start_time.setBounds(50, 250, 100, 30);
        f.add(start_time);

        start_time_text.setBounds(200, 250, 200, 30);
        f.add(start_time_text);

        end_time.setBounds(50, 300, 100, 30);
        f.add(end_time);

        end_time_text.setBounds(200, 300, 200, 30);
        f.add(end_time_text);

        workout_type.setBounds(50, 350, 100, 30);
        f.add(workout_type);

        workout_type_text.setBounds(200, 350, 200, 30);
        f.add(workout_type_text);

        workout_description.setBounds(50, 400, 150, 30);
        f.add(workout_description);

        workout_description_text.setBounds(200, 400, 250, 100);
        f.add(workout_description_text);

        schedule_button.setBounds(50, 550, 125, 40);
        f.add(schedule_button);

        clear_button.setBounds(200, 550, 125, 40);
        f.add(clear_button);

        exit_button.setBounds(350, 550, 125, 40);
        f.add(exit_button);

        schedule_button.addActionListener(e -> {
            String workout_id_text_string = workout_id_text.getText();
            String instructor_id_text_string = instructor_id_text.getText();
            String day_text_string = day_text.getText();
            String start_time_text_string = start_time_text.getText();
            String end_time_text_string = end_time_text.getText();
            String workout_type_text_string = workout_type_text.getText();
            String workout_description_text_string = workout_description_text.getText();

            System.out.println("Workout ID: " + workout_id_text_string);
            System.out.println("Instructor ID: " + instructor_id_text_string);
            System.out.println("Day: " + day_text_string);
            System.out.println("Start Time: " + start_time_text_string);
            System.out.println("End Time: " + end_time_text_string);
            System.out.println("Workout Type: " + workout_type_text_string);
            System.out.println("Workout Description: " + workout_description_text_string);
            if(workout_id_text_string.equals("") || instructor_id_text_string.equals("") || day_text_string.equals("") || start_time_text_string.equals("") || end_time_text_string.equals("") || workout_type_text_string.equals("") || workout_description_text_string.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Please fill in all the fields");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Workout Scheduled Successfully");
            }


        });

        clear_button.addActionListener(e -> {
            workout_id_text.setText("");
            instructor_id_text.setText("");
            day_text.setText("");
            start_time_text.setText("");
            end_time_text.setText("");
            workout_type_text.setText("");
            workout_description_text.setText("");
        }
   );
        exit_button.addActionListener(e -> {
             int s = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?");
             if(s == JOptionPane.YES_OPTION){
                 System.exit(0);
             }
             else if(s == JOptionPane.NO_OPTION || s == JOptionPane.CANCEL_OPTION){}

        });
        }

        public static void main (String[]args)
            {
                WorkoutSchedule ws = new WorkoutSchedule();
            }
        }