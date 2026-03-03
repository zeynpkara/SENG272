import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProjectFormPanel extends JPanel {
    private JTextField txtProjectName, txtTeamLeader, txtStartDate;
    private JComboBox<String> cbTeamSize, cbProjectType;
    private JButton btnSave, btnClear;
    public ProjectFormPanel() {
        setLayout(new GridLayout(7, 2, 10, 10));

        add(new JLabel("Project Name:"));
        txtProjectName = new JTextField();
        add(txtProjectName);

        add(new JLabel("Team Leader:"));
        txtTeamLeader = new JTextField();
        add(txtTeamLeader);

        add(new JLabel("Team Size:"));
        String[] sizes = {"1-3", "4-6", "7-10", "10+"};
        cbTeamSize = new JComboBox<>(sizes);
        add(cbTeamSize);

        add(new JLabel("Project Type:"));
        String[] types = {"Web", "Mobile", "Desktop", "API"};
        cbProjectType = new JComboBox<>(types);
        add(cbProjectType);

        add(new JLabel("Start Date (DD/MM/YYYY):"));
        txtStartDate = new JTextField();
        add(txtStartDate);

        btnSave = new JButton("Save");
        btnClear = new JButton("Clear");
        add(btnSave);
        add(btnClear);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
    }

    private void saveData() {
        if (txtProjectName.getText().isEmpty() || txtTeamLeader.getText().isEmpty() || txtStartDate.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lütfen tüm alanları doldurun!", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String recordTime = now.format(formatter);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("projects.txt", true))) {
            writer.write("=== Project Entry ===\n");
            writer.write("Project Name : " + txtProjectName.getText() + "\n");
            writer.write("Team Leader  : " + txtTeamLeader.getText() + "\n");
            writer.write("Team Size    : " + cbTeamSize.getSelectedItem() + "\n");
            writer.write("Project Type : " + cbProjectType.getSelectedItem() + "\n");
            writer.write("Start Date   : " + txtStartDate.getText() + "\n");
            writer.write("Record Time  : " + recordTime + "\n");
            writer.write("======\n\n");

            JOptionPane.showMessageDialog(ProjectFormPanel.this, "Proje başarıyla kaydedildi!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Dosya yazma hatası!", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        txtProjectName.setText("");
        txtTeamLeader.setText("");
        txtStartDate.setText("");
        cbTeamSize.setSelectedIndex(0);
        cbProjectType.setSelectedIndex(0);
    }
}