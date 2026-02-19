package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import manager.StaffManager;
import model.Staff;

public class LoginPanel extends JPanel {
    public LoginPanel(MainFrame frame) {
        setLayout(new GridBagLayout()); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblUser = new JLabel("Kullanici Adi:");
        JTextField txtUser = new JTextField(15);
        JLabel lblPass = new JLabel("Sifre:");
        JPasswordField txtPass = new JPasswordField(15);
        JButton btnLogin = new JButton("Giris Yap");


        gbc.gridx = 0; gbc.gridy = 0; add(lblUser, gbc);
        gbc.gridx = 1; add(txtUser, gbc);
        gbc.gridx = 0; gbc.gridy = 1; add(lblPass, gbc);
        gbc.gridx = 1; add(txtPass, gbc);
        gbc.gridx = 1; gbc.gridy = 2; add(btnLogin, gbc);

        btnLogin.addActionListener(e -> {
            String user = txtUser.getText().trim(); 
            String pass = new String(txtPass.getPassword());

            boolean isAuthorized = false; 


            if (user.equals("admin") && pass.equals("123")) {
                isAuthorized = true;
            }

            if (!isAuthorized) {
                for (Staff s : StaffManager.getInstance().getAllStaff()) {
                    if (user.equals(s.getName()) && pass.equals(s.getId())) {
                        isAuthorized = true;
                        break; 
                    }
                }
            }

            if (isAuthorized) {
                frame.showScreen("ADMIN");
            } else {
                frame.showScreen("BOOKING");
            }
        });
    }
}