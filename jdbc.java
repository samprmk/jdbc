
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Jdbc extends JFrame {
    private static final String URL = "jdbc:oracle:thin:@172.16.0.16:1521:ORACLE"; 
    private static final String USER = "1mcab29";
    private static final String PASSWORD = "iii";

    public Jdbc() {
        setTitle("DB Operations");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

   
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();

      
        JButton insertButton = new JButton("Insert");
        JButton deleteButton = new JButton("Delete");
        JButton updateButton = new JButton("Update");

       
        JLabel idLabel = new JLabel("ID:");
        JLabel nameLabel = new JLabel("Name:");

     
        idLabel.setBounds(20, 20, 80, 25);
        nameLabel.setBounds(20, 60, 80, 25);
        idField.setBounds(100, 20, 160, 25);
        nameField.setBounds(100, 60, 160, 25);
        insertButton.setBounds(20, 100, 80, 25);
        deleteButton.setBounds(110, 100, 80, 25);
        updateButton.setBounds(200, 100, 80, 25);

        
        add(idLabel);
        add(nameLabel);
        add(idField);
        add(nameField);
        add(insertButton);
        add(deleteButton);
        add(updateButton);

       
        insertButton.addActionListener(e -> executeUpdate("INSERT INTO barath (id, name) VALUES (?, ?)", idField, nameField));
        deleteButton.addActionListener(e -> executeUpdate("DELETE FROM barath WHERE id = ?", idField, nameField));
        updateButton.addActionListener(e -> executeUpdate("UPDATE barath SET name = ? WHERE id = ?", idField, nameField));
    }

    private void executeUpdate(String query, JTextField idField, JTextField nameField) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            if (query.contains("VALUES")) {
                stmt.setString(1, idField.getText());
                stmt.setString(2, nameField.getText());
            } else if (query.contains("UPDATE")) {
                stmt.setString(1, nameField.getText()); 
                stmt.setString(2, idField.getText()); 
            } else {
                stmt.setString(1, idField.getText()); 
            }

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Operation successful");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Jdbc().setVisible(true));
    }
}



