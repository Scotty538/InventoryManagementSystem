

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductBrowserUpdatePanel extends JFrame {
    private final JTable productTable;
    private final DefaultTableModel tableModel;
    private final JComboBox<String> categoryComboBox;
    private final JComboBox<String> typeComboBox;
    private final JPanel productPanel;
    private final JPanel detailsPanel;
    private final JTextField iDTextField;
    private final JComboBox<String> categoryBox;
    private final JComboBox<String> typeBox;
    private final JTextField brandTextField;
    private final JTextField cpuFamilyTextField;
    private final JTextField memorySizeTextField;
    private final JTextField ssdCapacityTextField;
    private final JTextField screenSizeTextField;
    private final JTextField priceTextField;
    private JPanel mainProductPanel;
    private JButton loginButton;
    private JButton logoutButton;
    private final JPanel updateDetailsPanel;
    private final JLabel memorySizeLabel;
    private final JLabel ssdCapacityLabel;
    private final JLabel screenSizeLabel;
    private PasswordPanelBackend userPasswordDetails;

    private final Map<String, ArrayList<String>> subCategoriesByCategory;
    //Constructor
    public ProductBrowserUpdatePanel(ArrayList<Item> pC) {
        // Setting up the JFrame
        setTitle("Computer Products Management System");
        setLocation(40,40);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Setting up the login button
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BorderLayout());

        // Try/catch ensures system will still run even if image file is missing
        try {
            Image image = ImageIO.read(new File("src/main/resources/S_Mart.png")).getScaledInstance(400, 200, Image.SCALE_DEFAULT);
            loginButton = new JButton(" CLICK TO LOG IN      ", new ImageIcon(image));
        } catch (IOException e) {
            System.out.println("Specified image file not found in the source folder");
            loginButton = new JButton("CLICK TO LOG IN");
        }

        loginButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        loginButton.setVerticalTextPosition(SwingConstants.CENTER);
        loginButton.setFont(new Font("arial rounded", Font.BOLD, 25));
        loginButton.setForeground(Color.black);
        loginPanel.add(loginButton);
        loginButton.setEnabled(true);
        loginButton.setVisible(true);


        // Adding action listener to login button and creating a new object to receive login information
        loginButton.addActionListener(e -> {
            PasswordPanel passPanel = new PasswordPanel(this);
            userPasswordDetails = passPanel.showDialog();
            // DEBUG
            // System.out.println("User is " + userPasswordDetails.getUsername());
            // System.out.println("Password is " + String.valueOf(userPasswordDetails.getPassword()));
            if (userPasswordDetails.getUsername() != null) {
                if (userPasswordDetails.isAccessGranted()) {
                    loginButton.setVisible(false);
                    mainProductPanel.setVisible(true);
                    logoutButton.setVisible(true);
                    // Logging in as salesman will disable the editable text fields in the details panel
                    if (userPasswordDetails.isManager()) {
                        enableAllTextFieldsAndButtons(true);
                    } else {
                        enableAllTextFieldsAndButtons(false);
                    }
                    pack();
                }
            }
        });
        add(loginPanel, BorderLayout.NORTH);

        // Setting up the tabbed pane inside the mainProduct panel
        mainProductPanel = new JPanel();
        mainProductPanel.setLayout(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();
        mainProductPanel.add(tabbedPane);
        add(mainProductPanel, BorderLayout.CENTER);
        mainProductPanel.setVisible(false);

        // Setting up the product display tab
        productPanel = new JPanel();
        productPanel.setLayout(new BorderLayout());

        // Setting up the category and sub-category combo boxes
        categoryComboBox = new JComboBox<>();
        typeComboBox = new JComboBox<>();
        JPanel comboBoxPanel = new JPanel(new GridLayout(2, 2));
        JLabel compCatLabel = new JLabel("Computer Category: ", SwingConstants.RIGHT);
        JLabel compTypeLabel = new JLabel("Computer Type: ", SwingConstants.RIGHT);
        comboBoxPanel.add(compCatLabel);
        comboBoxPanel.add(categoryComboBox);
        comboBoxPanel.add(compTypeLabel);
        comboBoxPanel.add(typeComboBox);
        productPanel.add(comboBoxPanel, BorderLayout.NORTH);

        // Creating the String arrays to pass to the DefaultTableModel in order to create JTable
        String[][] twoDcatalogue = new String[pC.size()][6];
        String[] column = {"Category", "Type", "ID", "Brand", "CPU Family", "Price ($)"};
        int counter = 0;
        for (Item i : pC) {
            twoDcatalogue[counter] = i.toArray();
            counter++;
        }

        // Setting up the table model ensuring the cells are not editable
        tableModel = new DefaultTableModel(twoDcatalogue, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Setting up the JTable panel
        JTable jt = new JTable(tableModel);
        jt.setRowSelectionAllowed(true);

        productTable = jt;
        JScrollPane scrollPane = new JScrollPane(productTable);
        productPanel.add(scrollPane, BorderLayout.CENTER);

        // Setting up the check/update details tab
        JPanel detailsWindow = new JPanel(new BorderLayout());
        detailsPanel = new JPanel(new GridLayout(0, 2));

        JLabel modelIdLabel = new JLabel("Model ID:", SwingConstants.RIGHT);
        detailsPanel.add(modelIdLabel);
        iDTextField = new JTextField();
        detailsPanel.add(iDTextField);

        JLabel categoryLabel = new JLabel("Category:", SwingConstants.RIGHT);
        detailsPanel.add(categoryLabel);
        categoryBox = new JComboBox<>();
        detailsPanel.add(categoryBox);

        JLabel typeLabel = new JLabel("Type:", SwingConstants.RIGHT);
        detailsPanel.add(typeLabel);
        typeBox = new JComboBox<>();
        detailsPanel.add(typeBox);

        JLabel brandLabel = new JLabel("Brand:", SwingConstants.RIGHT);
        detailsPanel.add(brandLabel);
        brandTextField = new JTextField();
        detailsPanel.add(brandTextField);

        JLabel cpuFamilyLabel = new JLabel("CPU Family:", SwingConstants.RIGHT);
        detailsPanel.add(cpuFamilyLabel);
        cpuFamilyTextField = new JTextField();
        detailsPanel.add(cpuFamilyTextField);

        memorySizeLabel = new JLabel("Memory Size:",SwingConstants.RIGHT);
        detailsPanel.add(memorySizeLabel);
        memorySizeTextField = new JTextField();
        detailsPanel.add(memorySizeTextField);

        ssdCapacityLabel = new JLabel("SSD Capacity:", SwingConstants.RIGHT);
        detailsPanel.add(ssdCapacityLabel);
        ssdCapacityTextField = new JTextField();
        detailsPanel.add(ssdCapacityTextField);

        screenSizeLabel = new JLabel("Screen Size:", SwingConstants.RIGHT);
        detailsPanel.add(screenSizeLabel);
        screenSizeTextField = new JTextField();
        detailsPanel.add(screenSizeTextField);

        JLabel priceLabel = new JLabel("Price:", SwingConstants.RIGHT);
        detailsPanel.add(priceLabel);
        priceTextField = new JTextField();
        detailsPanel.add(priceTextField);

        detailsWindow.add(detailsPanel, BorderLayout.CENTER);

        // Adding the buttons that will be used to update the product catalogue
        updateDetailsPanel = new JPanel(new GridLayout(2,2));
        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton clearButton = new JButton("Clear");

        updateDetailsPanel.add(addButton);
        updateDetailsPanel.add(updateButton);
        updateDetailsPanel.add(deleteButton);
        updateDetailsPanel.add(clearButton);
        detailsWindow.add(updateDetailsPanel, BorderLayout.SOUTH);
        detailsWindow.setVisible(true);

        // Adding action listeners to relevant buttons
        // Created a method with a boolean parameter ('false'' if adding a product, 'true' if updating a product) to prevent excess code duplication
        addButton.addActionListener(e -> {
            addProduct(pC, false);
        });

        updateButton.addActionListener(e -> {
            addProduct(pC, true);
        });


        deleteButton.addActionListener(e -> {
            boolean isExistingID = false;
            int index = -1;
            for (Item i : pC) {
                if (iDTextField.getText().equals(i.getId())) {
                    isExistingID = true;
                    index = pC.indexOf(i);
                }
            }

            if (isExistingID) {
                pC.remove(index);
                clearDetailsPanel();
                setTextFieldsWhite();
                updateProductTable(pC);
                JOptionPane.showMessageDialog(null, "The record for the product was successfully deleted.", "Message",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                iDTextField.setText("Must be an existing model ID");
                iDTextField.setBackground(Color.red);
            }
        });

        clearButton.addActionListener(e -> {
            clearDetailsPanel();
            setTextFieldsWhite();
        });

        // Adding the tabs to the tabbed pane
        tabbedPane.addTab("Browse Products", productPanel);
        tabbedPane.addTab("Selected Product Details", detailsWindow);

        // Setting up the category and sub-category data
        subCategoriesByCategory = new HashMap<>();
        subCategoriesByCategory.put("Desktop PC", new ArrayList<>() {{
            add("Gaming");
            add("Home & Study");
            add("Business");
            add("Compact");
        }});
        subCategoriesByCategory.put("Laptop", new ArrayList<>() {{
            add("Gaming");
            add("Home & Study");
            add("Business");
            add("Thin & Light");
        }});
        subCategoriesByCategory.put("Tablet", new ArrayList<>() {{
            add("Android");
            add("Apple");
            add("Windows");
        }});

        // Setting up the category combo box
        categoryComboBox.addItem("All");
        for (String category : subCategoriesByCategory.keySet()) {
            categoryComboBox.addItem(category);
        }

        // Setting up the sub-category combo box
        updateSubCategoryComboBox();

        // Setting up the product table
        updateProductTable(pC);

        // Setting up the category combo box listener
        categoryComboBox.addActionListener(e -> {
            updateSubCategoryComboBox();
            updateProductTable(pC);
        });

        // Setting up the sub-category combo box listener
        typeComboBox.addActionListener(e -> {
            updateProductTable(pC);
        });

        // Setting up the category combo box for the check/update details panel
        for (String category : subCategoriesByCategory.keySet()) {
            categoryBox.addItem(category);
        }

        // Setting up the type combo box for the check/update details panel
        updateTypeBox();

        // Setting up the category combo box action listener for the check/update details panel
        // Selection of this comboBox needs to update the type comboBox and also erase content and
        // disable certain text fields on the check/update details pane
        categoryBox.addActionListener(e -> {
            updateTypeBox();

            if(userPasswordDetails.isManager()) {
                enableAllLabelsAndTextFields();
                setTextFieldsWhite();
                if (categoryBox.getSelectedItem() != null && categoryBox.getSelectedItem().equals("Desktop PC")) {
                    screenSizeTextField.setText("");
                    screenSizeLabel.setEnabled(false);
                    screenSizeTextField.setEnabled(false);
                } else if (categoryBox.getSelectedItem() != null && categoryBox.getSelectedItem().equals("Tablet")) {
                    memorySizeTextField.setText("");
                    memorySizeLabel.setEnabled(false);
                    ssdCapacityTextField.setText("");
                    ssdCapacityLabel.setEnabled(false);
                    memorySizeTextField.setEnabled(false);
                    ssdCapacityTextField.setEnabled(false);
                }
            }
        });

        // Setting up the product table listener
        // The selection populates the text fields of the check/update details panel
        jt.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow != -1) {
                String id = (String) productTable.getValueAt(selectedRow, 2);
                String cat = (String) productTable.getValueAt(selectedRow, 0);
                String type = (String) productTable.getValueAt(selectedRow, 1);
                String brand = (String) productTable.getValueAt(selectedRow, 3);
                String cpu = (String) productTable.getValueAt(selectedRow, 4);

                String memory = getItemInformation(pC, (String) productTable.getValueAt(selectedRow, 2), "memory");
                String ssd = getItemInformation(pC, (String) productTable.getValueAt(selectedRow, 2), "ssd");
                String screen = getItemInformation(pC, (String) productTable.getValueAt(selectedRow, 2), "screen");

                String price = (String) productTable.getValueAt(selectedRow, 5);

                iDTextField.setText((id != null) ? id : "");
                categoryBox.setSelectedItem((id != null) ? cat : "");
                typeBox.setSelectedItem((type != null) ? type : "");
                brandTextField.setText((brand != null) ? brand : "");
                cpuFamilyTextField.setText((cpu != null) ? cpu : "");
                memorySizeTextField.setText((memory != null) ? memory : "");
                ssdCapacityTextField.setText((ssd != null) ? ssd : "");
                screenSizeTextField.setText((screen != null) ? screen : "");
                priceTextField.setText((price != null) ? price : "");
                setTextFieldsWhite();

                if(userPasswordDetails.isManager()) {
                    enableAllLabelsAndTextFields();
                    if (categoryBox.getSelectedItem() != null && categoryBox.getSelectedItem().equals("Desktop PC")) {
                        screenSizeLabel.setEnabled(false);
                        screenSizeTextField.setEnabled(false);
                    } else if (categoryBox.getSelectedItem() != null && categoryBox.getSelectedItem().equals("Tablet")) {
                        memorySizeLabel.setEnabled(false);
                        ssdCapacityLabel.setEnabled(false);
                        memorySizeTextField.setEnabled(false);
                        ssdCapacityTextField.setEnabled(false);
                    }
                }
            }
        });

        // Setting up the logout button
        JPanel logoutPanel = new JPanel();
        logoutPanel.setLayout(new BorderLayout());
        // Try/catch ensures system will still run even if image file is missing
        try{
            Image image = ImageIO.read(new File("S_Mart.png")).getScaledInstance(400, 200, Image.SCALE_DEFAULT);
            logoutButton = new JButton("CLICK TO LOG OUT     ", new ImageIcon(image));
        }
        catch (IOException e) {
            System.out.println("Specified image file not found in the source folder");
            logoutButton = new JButton("CLICK TO LOG OUT");
        }

        logoutButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        logoutButton.setVerticalTextPosition(SwingConstants.CENTER);
        logoutButton.setFont(new Font("arial rounded", Font.BOLD ,25));
        logoutButton.setForeground(Color.black);
        logoutButton.setBackground(Color.lightGray);
        logoutButton.setVisible(false);
        logoutPanel.add(logoutButton);
        add(logoutPanel, BorderLayout.SOUTH);

        // Adding action listener to the logout button which hides main panel and logout button and makes login button visible
        logoutButton.addActionListener(e -> {
            loginButton.setVisible(true);
            mainProductPanel.setVisible(false);
            logoutButton.setVisible(false);

            // Resetting category combo box selection
            categoryComboBox.setSelectedItem("All");
            // Resetting tabbed pane to product panel
            tabbedPane.setSelectedComponent(productPanel);
            // Resetting product detail panel
            clearDetailsPanel();
            pack();
        });

        logoutButton.setEnabled(true);

        pack();
        setVisible(true);
    }

    private void updateSubCategoryComboBox() {
        typeComboBox.removeAllItems();
        String selectedCategory = (String) categoryComboBox.getSelectedItem();
        if (selectedCategory != null) {
            ArrayList<String> subCategories = subCategoriesByCategory.get(selectedCategory);
            if (subCategories != null) {
                typeComboBox.addItem("All");
                for (String subCategory : subCategories) {
                    typeComboBox.addItem(subCategory);
                }
            }
        }
    }

    private void updateTypeBox() {
        typeBox.removeAllItems();
        String selectedCategory = (String) categoryBox.getSelectedItem();
        if (selectedCategory != null) {
            ArrayList<String> subCategories = subCategoriesByCategory.get(selectedCategory);
            if (subCategories != null) {
                for (String subCategory : subCategories) {
                    typeBox.addItem(subCategory);
                }
            }
        }
    }
    private void updateProductTable(ArrayList<Item> pC) {
        tableModel.setRowCount(0);
        String selectedCategory = (String) categoryComboBox.getSelectedItem();
        String selectedSubCategory = (String) typeComboBox.getSelectedItem();

        for (Item i : pC) {
            if ((selectedCategory == null || i.getCategory().equals(selectedCategory) || selectedCategory.equals("All")) &&
                    (selectedSubCategory == null || i.getType().equals(selectedSubCategory) || selectedSubCategory.equals("All"))) {
                String category = i.getCategory();
                String type = i.getType();
                String id = i.getId();
                String brand = i.getBrand();
                String cpuFamily = i.getCpuFamily();
                String price = String.valueOf(i.getPrice());
                tableModel.addRow(new String[] {category, type, id, brand, cpuFamily, price});
            }
        }
    }
    private String getItemInformation(ArrayList<Item> pC, String ID, String desiredInformation) {
        String output = "";
        String memory = "";
        String ssd = "";
        String screen = "";

        for (Item i : pC) {
            if (i.getId().equals(ID)) {
                if (i instanceof Desktop) {
                    memory = String.valueOf(((Desktop) i).getMemorySize());
                    ssd = String.valueOf(((Desktop) i).getSsdCapacity());
                } else if (i instanceof Laptop) {
                    memory = String.valueOf(((Laptop) i).getMemorySize());
                    ssd = String.valueOf(((Laptop) i).getSsdCapacity());
                    screen = String.valueOf(((Laptop) i).getScreenSize());
                } else {
                    screen = String.valueOf(((Tablet) i).getScreenSize());
                }
                switch (desiredInformation) {
                    case ("memory"): output = memory;
                        break;
                    case ("ssd"): output = ssd;
                        break;
                    case ("screen"): output = screen;
                        break;
                    default: output = "Beats me!";
                }
            }
        }
        return output;
    }

    public void enableAllTextFieldsAndButtons(boolean boo) {
        JTextField temp;
        JButton temp2;
        JComboBox temp3;
        for(Component c:detailsPanel.getComponents()) {
            if (c.getClass().toString().contains("javax.swing.JTextField")) {
                temp = (JTextField) c;
                temp.setEnabled(boo);
            }
            if (c.getClass().toString().contains("javax.swing.JComboBox")) {
                if (c instanceof JComboBox) {
                    temp3 = (JComboBox) c;
                    temp3.setEnabled(boo);
                }
            }
        }
        for(Component c:updateDetailsPanel.getComponents()) {
            if (c.getClass().toString().contains("javax.swing.JButton")) {
                temp2 = (JButton) c;
                temp2.setEnabled(boo);
            }
        }
    }


    public void enableAllLabelsAndTextFields() {
        JTextField temp;
        JLabel temp2;
        JComboBox temp3;
        for(Component c:detailsPanel.getComponents()) {
            if (c.getClass().toString().contains("javax.swing.JTextField")) {
                if (c instanceof JTextField) {
                    temp = (JTextField) c;
                    temp.setEnabled(true);
                }
            }
            if (c.getClass().toString().contains("javax.swing.JLabel")) {
                if (c instanceof JLabel) {
                    temp2 = (JLabel) c;
                    temp2.setEnabled(true);
                }
            }
            if (c.getClass().toString().contains("javax.swing.JComboBox")) {
                if (c instanceof JComboBox) {
                    temp3 = (JComboBox) c;
                    temp3.setEnabled(true);
                }
            }
        }
    }

    public void clearDetailsPanel() {
        JTextField temp;
        for(Component c:detailsPanel.getComponents()) {
            if (c.getClass().toString().contains("javax.swing.JTextField")) {
                temp = (JTextField) c;
                temp.setText("");
            }
        }
    }

    public void setTextFieldsWhite() {
        JTextField temp;
        for(Component c:detailsPanel.getComponents()) {
            if (c.getClass().toString().contains("javax.swing.JTextField")) {
                temp = (JTextField) c;
                temp.setBackground(Color.white);
            }
        }
    }
// The following method is used when the 'Add' and 'Update' buttons are pressed
// The method has a boolean parameter ('false'' if adding a product, 'true' if updating a product) to prevent excess code duplication

    public void addProduct(ArrayList<Item> pC, boolean updating) {
        boolean isExistingID = false;
        boolean isValid = true;
        int index = -1;

        for (Item i : pC) {
            if (iDTextField.getText().equals(i.getId()) || iDTextField.getText().equals("Must be a unique model ID") || iDTextField.getText().equals("Must be an existing model ID")) {
                isExistingID = true;
                index = pC.indexOf(i);
            }
        }

        boolean condition;
        if (updating) {
            condition = isExistingID;
        } else {
            condition = !isExistingID;
        }

        if (condition) {
            String id = iDTextField.getText();
            String category = String.valueOf(categoryBox.getSelectedItem());
            String type = String.valueOf(typeBox.getSelectedItem());
            String brand = brandTextField.getText();
            String cpuFamily = cpuFamilyTextField.getText();

            int memorySize = 0;
            int ssdCapacity = 0;
            double screenSize = 0.0;
            double price = 0.0;

// Try/catches to ensure numerical data is entered into numeric fields.
// Also to ensure relevant text fields are enabled or cleared and disabled for the relevant computer category.
            if(category.equals("Desktop PC")) {
                try {
                    memorySize = Integer.parseInt(memorySizeTextField.getText());
                } catch (NumberFormatException except) {
                    memorySizeTextField.setText("This needs to be a number");
                    memorySizeTextField.setBackground(Color.red);
                    isValid = false;
                }
                try {
                    ssdCapacity = Integer.parseInt(ssdCapacityTextField.getText());
                } catch (NumberFormatException except) {
                    ssdCapacityTextField.setText("This needs to be a number");
                    ssdCapacityTextField.setBackground(Color.red);
                    isValid = false;
                }
            } else if (category.equals("Laptop")) {
                try {
                    memorySize = Integer.parseInt(memorySizeTextField.getText());
                } catch (NumberFormatException except) {
                    memorySizeTextField.setText("This needs to be a number");
                    memorySizeTextField.setBackground(Color.red);
                    isValid = false;
                }

                try {
                    ssdCapacity = Integer.parseInt(ssdCapacityTextField.getText());
                } catch (NumberFormatException except) {
                    ssdCapacityTextField.setText("This needs to be a number");
                    ssdCapacityTextField.setBackground(Color.red);
                    isValid = false;
                }

                try {
                    screenSize = Double.parseDouble(screenSizeTextField.getText());
                } catch (NumberFormatException except) {
                    screenSizeTextField.setText("This needs to be a number");
                    screenSizeTextField.setBackground(Color.red);
                    isValid = false;
                }
            } else {
                try {
                    screenSize = Double.parseDouble(screenSizeTextField.getText());
                } catch (NumberFormatException except) {
                    screenSizeTextField.setText("This needs to be a number");
                    screenSizeTextField.setBackground(Color.red);
                    isValid = false;
                }
            }


            try {
                price = Double.parseDouble(priceTextField.getText());
            } catch (NumberFormatException except) {
                priceTextField.setText("This needs to be a number");
                priceTextField.setBackground(Color.red);
                isValid = false;
            }

            if(isValid) {
                if (category.equals("Desktop PC")) {
                    pC.add(new Desktop(category, type, id, brand, cpuFamily, price, memorySize, ssdCapacity));
                } else if (category.equals("Laptop")) {
                    pC.add(new Laptop(category, type, id, brand, cpuFamily, price, memorySize, ssdCapacity, screenSize));
                } else {
                    pC.add(new Tablet(category, type, id, brand, cpuFamily, price, screenSize));
                }
                setTextFieldsWhite();

                if (updating) {
                    updateProductTable(pC);
                    pC.remove(index);
                    updateProductTable(pC);
                    JOptionPane.showMessageDialog(null, "The record for the product was successfully updated.", "Message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    updateProductTable(pC);
                    JOptionPane.showMessageDialog(null, "The record for the product was successfully added.", "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else {
            if (updating) {
                iDTextField.setText("Must be an existing model ID");
                iDTextField.setBackground(Color.red);
            } else {
                iDTextField.setText("Must be a unique model ID");
                iDTextField.setBackground(Color.red);
            }
        }
    }}
