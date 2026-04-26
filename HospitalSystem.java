import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

// Main Class
public class HospitalSystem {

    // ===== Patient Model =====
    static class Patient {
        String id, name, age, gender, blood, address, contact;

        Patient(String id, String name, String age, String gender,
                String blood, String address, String contact) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.blood = blood;
            this.address = address;
            this.contact = contact;
        }
    }

    // ===== Data Storage =====
    static ArrayList<Patient> patients = new ArrayList<>();

    // ===== LOGIN =====
    static class Login extends JFrame implements ActionListener {
        JTextField username;
        JPasswordField password;
        JButton login;

        Login() {
            setLayout(null);

            JLabel l1 = new JLabel("Username");
            l1.setBounds(40, 30, 100, 30);
            add(l1);

            username = new JTextField();
            username.setBounds(150, 30, 150, 30);
            add(username);

            JLabel l2 = new JLabel("Password");
            l2.setBounds(40, 80, 100, 30);
            add(l2);

            password = new JPasswordField();
            password.setBounds(150, 80, 150, 30);
            add(password);

            login = new JButton("Login");
            login.setBounds(120, 140, 100, 30);
            login.addActionListener(this);
            add(login);

            setSize(350, 250);
            setLocation(500, 250);
            setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
            if (username.getText().equals("admin") &&
                new String(password.getPassword()).equals("1234")) {
                new Home();
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Login");
            }
        }
    }

    // ===== HOME =====
    static class Home extends JFrame implements ActionListener {
        JButton add, update, view;

        Home() {
            setLayout(null);

            add = new JButton("Add Patient");
            add.setBounds(50, 50, 200, 30);
            add.addActionListener(this);
            add(add);

            update = new JButton("Update Patient");
            update.setBounds(50, 100, 200, 30);
            update.addActionListener(this);
            add(update);

            view = new JButton("View Patients");
            view.setBounds(50, 150, 200, 30);
            view.addActionListener(this);
            add(view);

            setSize(300, 300);
            setLocation(500, 200);
            setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == add) {
                new AddPatient();
            } else if (e.getSource() == update) {
                new UpdatePatient();
            } else {
                new PatientDetails();
            }
        }
    }

    // ===== ADD PATIENT =====
    static class AddPatient extends JFrame implements ActionListener {
        JTextField t1,t2,t3,t4,t5,t6,t7;
        JButton save;

        AddPatient() {
            setLayout(null);

            String labels[] = {"ID","Name","Age","Gender","Blood","Address","Contact"};
            JTextField fields[] = new JTextField[7];

            for(int i=0;i<7;i++) {
                JLabel l = new JLabel(labels[i]);
                l.setBounds(30, 30+i*40, 100, 30);
                add(l);

                fields[i] = new JTextField();
                fields[i].setBounds(150, 30+i*40, 150, 30);
                add(fields[i]);
            }

            t1=fields[0]; t2=fields[1]; t3=fields[2];
            t4=fields[3]; t5=fields[4]; t6=fields[5]; t7=fields[6];

            save = new JButton("Save");
            save.setBounds(120, 320, 100, 30);
            save.addActionListener(this);
            add(save);

            setSize(350, 420);
            setLocation(500, 200);
            setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
            Patient p = new Patient(
                t1.getText(), t2.getText(), t3.getText(),
                t4.getText(), t5.getText(), t6.getText(), t7.getText()
            );

            patients.add(p);

            JOptionPane.showMessageDialog(null, "Patient Added");
            setVisible(false);
        }
    }

    // ===== UPDATE PATIENT =====
    static class UpdatePatient extends JFrame implements ActionListener {
        JTextField id, name, contact;
        JButton update;

        UpdatePatient() {
            setLayout(null);

            JLabel l1 = new JLabel("Patient ID");
            l1.setBounds(30, 30, 100, 30);
            add(l1);

            id = new JTextField();
            id.setBounds(150, 30, 150, 30);
            add(id);

            JLabel l2 = new JLabel("New Name");
            l2.setBounds(30, 80, 100, 30);
            add(l2);

            name = new JTextField();
            name.setBounds(150, 80, 150, 30);
            add(name);

            JLabel l3 = new JLabel("New Contact");
            l3.setBounds(30, 130, 100, 30);
            add(l3);

            contact = new JTextField();
            contact.setBounds(150, 130, 150, 30);
            add(contact);

            update = new JButton("Update");
            update.setBounds(120, 200, 100, 30);
            update.addActionListener(this);
            add(update);

            setSize(350, 300);
            setLocation(500, 200);
            setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
            for(Patient p : patients) {
                if(p.id.equals(id.getText())) {
                    p.name = name.getText();
                    p.contact = contact.getText();
                    JOptionPane.showMessageDialog(null, "Updated");
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Patient Not Found");
        }
    }

    // ===== VIEW PATIENTS =====
    static class PatientDetails extends JFrame {
        PatientDetails() {
            setLayout(null);

            String column[] = {"ID","Name","Age","Gender","Blood","Address","Contact"};
            DefaultTableModel model = new DefaultTableModel(column, 0);

            for(Patient p : patients) {
                String row[] = {
                    p.id, p.name, p.age, p.gender,
                    p.blood, p.address, p.contact
                };
                model.addRow(row);
            }

            JTable table = new JTable(model);
            JScrollPane sp = new JScrollPane(table);
            sp.setBounds(20, 20, 450, 300);
            add(sp);

            setSize(500, 400);
            setLocation(450, 200);
            setVisible(true);
        }
    }

    // ===== MAIN =====
    public static void main(String[] args) {
        new Login();
    }
}