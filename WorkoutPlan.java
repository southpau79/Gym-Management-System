import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

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

        plan_id = new JLabel("Plan ID");
        plan_id.setBounds(500,100,300,30);
        plan_id.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        plan_id.setForeground(Color.BLUE);
        f.add(plan_id);

        plan_id_txt = new JTextField();
        plan_id_txt.setBounds(700,100,140,30);
        plan_id_txt.setFont(new Font("Comic Sans MS", Font.ITALIC, 20));
        plan_id_txt.setBorder(border);
        f.add(plan_id_txt);

        Instructor_id = new JLabel("Instructor ID");
        Instructor_id.setBounds(500,200,300,50);
        Instructor_id.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        Instructor_id.setForeground(Color.BLUE);
        f.add(Instructor_id);

        instructor_id_txt = new JTextField();
        instructor_id_txt.setBounds(700,200,120,50);
        instructor_id_txt.setFont(new Font("Comic Sans MS", Font.ITALIC, 20));
        instructor_id_txt.setBorder(border);
        f.add(instructor_id_txt);

        workout_dur = new JLabel("Workout Duration");
        workout_dur.setBounds(500,300,180,50);
        workout_dur.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        workout_dur.setForeground(Color.BLUE);
        f.add(workout_dur);

        workout_dur_txt = new JTextField();
        workout_dur_txt.setBounds(700,300,120,50);
        workout_dur_txt.setFont(new Font("Comic Sans MS", Font.ITALIC, 20));
        workout_dur_txt.setBorder(border);
        f.add(workout_dur_txt);

        plan_name = new JLabel("Plan Name");
        plan_name.setBounds(500,400,300,40);
        plan_name.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        plan_name.setForeground(Color.BLUE);
        f.add(plan_name);

        plan_name_txt = new JTextField();
        plan_name_txt.setBounds(700,400,120,50);
        plan_name_txt.setFont(new Font("Comic Sans MS", Font.ITALIC, 20));
        plan_name_txt.setBorder(border);
        f.add(plan_name_txt);

        JButton btn = new JButton("Submit");
        btn.setBounds(700,600,100,50);
        btn.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        btn.setForeground(Color.GREEN);
        f.add(btn);

        f.setSize(1000,1000);
        f.setLayout(null);
        f.setVisible(true);
    }
    public static void main(String[] args) {
        new WorkoutPlan();
    }
}

