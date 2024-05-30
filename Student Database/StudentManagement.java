import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentManagement extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6;
    JTextField tf1, tf2, tf3;
    JComboBox<String> classComboBox, sectionComboBox;
    JRadioButton maleRadio, femaleRadio;
    ButtonGroup genderGroup;
    JButton insert, delete, update, display;
    Connection con;

    StudentManagement() {
        setTitle("Student Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        l1 = new JLabel("Roll Number:");
        l2 = new JLabel("Name:");
        l3 = new JLabel("Email:");
        l4 = new JLabel("Gender:");
        l5 = new JLabel("Class:");
        l6 = new JLabel("Section:");

        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();

        classComboBox = new JComboBox<>(new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII"});
        sectionComboBox = new JComboBox<>(new String[]{"A", "B", "C", "D"});

        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        insert = new JButton("Insert");
        delete = new JButton("Delete");
        update = new JButton("Update");
        display = new JButton("Display");

        insert.addActionListener(this);
        delete.addActionListener(this);
        update.addActionListener(this);
        display.addActionListener(this);

        setLayout(null);
        add(l1);
        add(tf1);
        add(l2);
        add(tf2);
        add(l3);
        add(tf3);
        add(l4);
        add(maleRadio);
        add(femaleRadio);
        add(l5);
        add(classComboBox);
        add(l6);
        add(sectionComboBox);
        add(insert);
        add(delete);
        add(update);
        add(display);

        l1.setBounds(50, 20, 100, 30);
        tf1.setBounds(150, 20, 150, 30);
        l2.setBounds(50, 60, 100, 30);
        tf2.setBounds(150, 60, 150, 30);
        l3.setBounds(50, 100, 100, 30);
        tf3.setBounds(150, 100, 150, 30);
        l4.setBounds(50, 140, 100, 30);
        maleRadio.setBounds(150, 140, 80, 30);
        femaleRadio.setBounds(230, 140, 80, 30);
        l5.setBounds(50, 180, 100, 30);
        classComboBox.setBounds(150, 180, 150, 30);
        l6.setBounds(50, 220, 100, 30);
        sectionComboBox.setBounds(150, 220, 150, 30);
        insert.setBounds(50, 260, 100, 30);
        delete.setBounds(160, 260, 100, 30);
        update.setBounds(270, 260, 100, 30);
        display.setBounds(380, 260, 100, 30);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "root");
        } catch (Exception e) {
            System.out.println(e);
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == insert) {
            try {
                PreparedStatement stmt = con.prepareStatement("INSERT INTO students (roll_number, name, email, gender, class, section) VALUES (?, ?, ?, ?, ?, ?)");
                stmt.setInt(1, Integer.parseInt(tf1.getText()));
                stmt.setString(2, tf2.getText());
                stmt.setString(3, tf3.getText());
                stmt.setString(4, maleRadio.isSelected() ? "Male" : "Female");
                stmt.setString(5, (String) classComboBox.getSelectedItem());
                stmt.setString(6, (String) sectionComboBox.getSelectedItem());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Student record inserted");
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else if (e.getSource() == delete) {
            try {
                PreparedStatement stmt = con.prepareStatement("DELETE FROM students WHERE roll_number = ?");
                stmt.setInt(1, Integer.parseInt(tf1.getText()));
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Student record deleted");
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else if (e.getSource() == update) {
            try {
                PreparedStatement stmt = con.prepareStatement("UPDATE students SET name = ?, email = ?, gender = ?, class = ?, section = ? WHERE roll_number = ?");
                stmt.setString(1, tf2.getText());
                stmt.setString(2, tf3.getText());
                stmt.setString(3, maleRadio.isSelected() ? "Male" : "Female");
                stmt.setString(4, (String) classComboBox.getSelectedItem());
                stmt.setString(5, (String) sectionComboBox.getSelectedItem());
                stmt.setInt(6, Integer.parseInt(tf1.getText()));
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Student record updated");
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else if (e.getSource() == display) {
            try {
                PreparedStatement stmt = con.prepareStatement("SELECT * FROM students WHERE name = ?");
                stmt.setString(1, tf2.getText());
                ResultSet rs = stmt.executeQuery();
                StringBuilder data = new StringBuilder();
                while (rs.next()) {
                    data.append("Roll Number: ").append(rs.getInt("roll_number"))
                        .append(", Name: ").append(rs.getString("name"))
                        .append(", Email: ").append(rs.getString("email"))
                        .append(", Gender: ").append(rs.getString("gender"))
                        .append(", Class: ").append(rs.getString("class"))
                        .append(", Section: ").append(rs.getString("section")).append("\n");
                }
                JOptionPane.showMessageDialog(this, data.toString());
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    public static void main(String[] args) {
        new StudentManagement();
    }
}
