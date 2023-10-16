package auction;
import java.util.*;
import javax.swing.*;
import javax.swing.event.AncestorListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class data_link implements ActionListener {
    private Connection con;
    Scanner input = new Scanner(System.in);
    public Connection connection;
    public data_link() {
        String url = "jdbc:mysql://localhost:3306/app_users";
        String username = "root";
        String password = "Gopal@2005";
        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("The connection is established!!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1); 
        }
        
    }
    
 

public class SignUpGUI implements ActionListener{
    public SignUpGUI(Object object) {
    }
    public AncestorListener usersign() {
        JFrame signwin;
    JTextField userIdField, nameField, mobileField, addressField, emailField;
     JPasswordField passwordField;
     JComboBox<String> userTypeComboBox;
        signwin = new JFrame("Sign Up");
        signwin.setLayout(new GridLayout(8, 2));

        signwin.add(new JLabel("Enter User ID: "));
        userIdField = new JTextField();
        signwin.add(userIdField);

        signwin.add(new JLabel("Enter Your Name: "));
        nameField = new JTextField();
        signwin.add(nameField);

        signwin.add(new JLabel("Enter Password: "));
        passwordField = new JPasswordField();
        signwin.add(passwordField);

        signwin.add(new JLabel("Enter Mobile Number: "));
        mobileField = new JTextField();
        signwin.add(mobileField);

        signwin.add(new JLabel("Enter Address: "));
        addressField = new JTextField();
        signwin.add(addressField);

        signwin.add(new JLabel("Enter Email: "));
        emailField = new JTextField();
        signwin.add(emailField);

        signwin.add(new JLabel("Select User Type: "));
        String[] userTypes = {"buyer", "seller"};
        userTypeComboBox = new JComboBox<>(userTypes);
        signwin.add(userTypeComboBox);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Gather user input from GUI components
                String id = userIdField.getText();
                String name = nameField.getText();
                String userPassword = new String(passwordField.getPassword());
                int mobile = Integer.parseInt(mobileField.getText());
                String address = addressField.getText();
                String email = emailField.getText();
                String userType = (String) userTypeComboBox.getSelectedItem();

                // Call your sign_up method with the input values
                try {
                    sign_up(id, name, userPassword, mobile, address, email, userType);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        signwin.add(signUpButton);

        signwin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signwin.setSize(400, 300);
        signwin.setVisible(true);
        return null;
    }
    public void sign_up(String id, String name, String userPassword, int mobile, String address, String email, String userType) throws SQLException{
        
            String query = "INSERT INTO user_details (user_id, user_name, password, phone_num, address, email, user_type) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setString(1, id);
            pstm.setString(2, name);
            pstm.setString(3, userPassword);
            pstm.setInt(4, mobile);
            pstm.setString(5, address);
            pstm.setString(6, email);
            pstm.setString(7, userType);

            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("You are registered successfully!!");
            } else {
                System.out.println("Registration failed. Please try again.");
            }

            pstm.close();
        
   
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
public void login() {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setLayout(new GridLayout(3, 2));

        JTextField userIdField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        loginFrame.add(new JLabel("Enter User ID: "));
        loginFrame.add(userIdField);

        loginFrame.add(new JLabel("Enter Password: "));
        loginFrame.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = userIdField.getText();
                String password = new String(passwordField.getPassword());

                try {
                    // Perform login verification
                    String query = "SELECT * FROM user_details WHERE user_id = ? AND password = ?";
                    PreparedStatement pstm = con.prepareStatement(query);
                    pstm.setString(1, id);
                    pstm.setString(2, password);
                    ResultSet rs = pstm.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Login successful!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid user_id or password.");
                    }

                    rs.close();
                    pstm.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        loginFrame.add(loginButton);

        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(300, 150);
        loginFrame.setVisible(true);
    }
@Override
public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
}

}
    
 
