import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordPanel extends JDialog {
    private final JButton loginButton;
    private final JButton cancelButton;
    private final JLabel labelInvalidEntry;
    private final PasswordPanelBackend signInDetails = new PasswordPanelBackend();

    // Constructor
    public PasswordPanel(JFrame owner) {
        super(owner,true);
// Creating the components
        setLayout(new BorderLayout());
        setTitle("Staff Login");
        setBounds(200, 170, 400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JLabel labelUsername = new JLabel("Username", SwingConstants.RIGHT);
        JTextField textFieldUser = new JTextField(20);
        textFieldUser.setToolTipText("Enter username");

        JLabel labelPassword = new JLabel("Password", SwingConstants.RIGHT);
        JPasswordField textFieldPassword = new JPasswordField(20);
        textFieldPassword.setToolTipText("Enter password");

        labelInvalidEntry = new JLabel("Invalid username or password. Please try again", SwingConstants.CENTER);
        labelInvalidEntry.setForeground(Color.red);
        labelInvalidEntry.setVisible(false);



        ActionListener dialogListener1 = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (event.getSource() == loginButton) {
                    signInDetails.setUsername(textFieldUser.getText());
                    signInDetails.setPassword(String.valueOf(textFieldPassword.getPassword()));
                    signInDetails.grantAccess();
                    // Remove dialogue box if password is correct
                    if (signInDetails.isAccessGranted()) {
                        //DEBUG
//                        System.out.println("User is " + signInDetails.getUsername());
//                        System.out.println("Password is " + String.valueOf(signInDetails.getPassword()));
                        dispose();
                    } else {
                        labelInvalidEntry.setVisible(true);
                    }
                } else if (event.getSource() == cancelButton) {
                    dispose();
                }
            }
        };
        loginButton = new JButton("Login");
        loginButton.addActionListener(dialogListener1);
        loginButton.setEnabled(true);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(dialogListener1);
        cancelButton.setEnabled(true);


// Adding features to the panel
        JPanel loginStuff = new JPanel(new GridLayout(3,0));
        loginStuff.add(labelUsername);
        loginStuff.add(textFieldUser);
        loginStuff.add(labelPassword);
        loginStuff.add(textFieldPassword);
        loginStuff.add(loginButton);
        loginStuff.add(cancelButton);
        add(loginStuff, BorderLayout.CENTER);

        add(labelInvalidEntry, BorderLayout.SOUTH);
        setVisible(true);
    }


    public PasswordPanelBackend showDialog() {
        return getSignInDetails();
    }

    public PasswordPanelBackend getSignInDetails() {
        return signInDetails;
    }

}