package com.exam.papeleria.view;

import com.exam.papeleria.model.Product;
import com.exam.papeleria.model.ProductCategory;
import com.exam.papeleria.model.ProductRepository;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.text.NumberFormatter;

/**
 * Formulario gráfico para gestionar los productos.
 */
public class ProductForm extends JFrame {

    private final ProductRepository repository;
    private final DefaultListModel<Product> productListModel;

    private JComboBox<ProductCategory> categoryComboBox;
    private JTextField nameField;
    private JFormattedTextField priceField;
    private JFormattedTextField quantityField;
    private JTextField supplierNameField;
    private JTextField supplierContactField;
    private JTextArea descriptionArea;
    private JLabel lastUpdatedLabel;
    private JList<Product> productList;

    public ProductForm() {
        super("Gestor de productos de papelería");
        this.repository = new ProductRepository(Paths.get("data"));
        this.productListModel = new DefaultListModel<>();
        configureWindow();
        initComponents();
        loadInitialData();
    }

    private void configureWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(900, 600));
    }

    private void initComponents() {
        JPanel content = new JPanel(new BorderLayout(16, 16));
        content.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        setContentPane(content);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Datos del producto"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Categoría
        formPanel.add(new JLabel("Categoría:"), gbc);
        gbc.gridx = 1;
        categoryComboBox = new JComboBox<>(ProductCategory.values());
        categoryComboBox.setSelectedIndex(0);
        formPanel.add(categoryComboBox, gbc);

        // Nombre
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Nombre del producto:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField();
        formPanel.add(nameField, gbc);

        // Precio
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Precio unitario (MXN):"), gbc);
        gbc.gridx = 1;
        priceField = new JFormattedTextField(createDecimalFormatter());
        priceField.setValue(0.0);
        formPanel.add(priceField, gbc);

        // Cantidad
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Cantidad en inventario:"), gbc);
        gbc.gridx = 1;
        quantityField = new JFormattedTextField(createIntegerFormatter());
        quantityField.setValue(0);
        formPanel.add(quantityField, gbc);

        // Proveedor
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Proveedor:"), gbc);
        gbc.gridx = 1;
        supplierNameField = new JTextField();
        formPanel.add(supplierNameField, gbc);

        // Contacto proveedor
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Contacto del proveedor:"), gbc);
        gbc.gridx = 1;
        supplierContactField = new JTextField();
        formPanel.add(supplierContactField, gbc);

        // Descripción
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        formPanel.add(new JLabel("Descripción y notas:"), gbc);
        gbc.gridx = 1;
        descriptionArea = new JTextArea(5, 30);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane descriptionScroll = new JScrollPane(descriptionArea);
        formPanel.add(descriptionScroll, gbc);
        gbc.anchor = GridBagConstraints.CENTER;

        // Última actualización
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Última actualización:"), gbc);
        gbc.gridx = 1;
        lastUpdatedLabel = new JLabel("-");
        formPanel.add(lastUpdatedLabel, gbc);

        // Botones
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(new JSeparator(), gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Guardar/Actualizar");
        JButton deleteButton = new JButton("Eliminar");
        JButton clearButton = new JButton("Limpiar formulario");
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);

        // Lista de productos
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder("Productos guardados"));
        productList = new JList<>(productListModel);
        productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScroll = new JScrollPane(productList);
        listPanel.add(listScroll, BorderLayout.CENTER);

        JButton reloadButton = new JButton("Recargar");
        listPanel.add(reloadButton, BorderLayout.SOUTH);

        content.add(formPanel, BorderLayout.CENTER);
        content.add(listPanel, BorderLayout.EAST);

        // Acciones
        saveButton.addActionListener(event -> handleSave());
        deleteButton.addActionListener(event -> handleDelete());
        clearButton.addActionListener(event -> clearForm());
        reloadButton.addActionListener(event -> refreshProductList());
        productList.addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                Optional.ofNullable(productList.getSelectedValue()).ifPresent(this::loadProduct);
            }
        });
    }

    private NumberFormatter createDecimalFormatter() {
        NumberFormatter formatter = new NumberFormatter(new java.text.DecimalFormat("#0.00"));
        formatter.setValueClass(Double.class);
        formatter.setAllowsInvalid(false);
        formatter.setMinimum(0.0);
        return formatter;
    }

    private NumberFormatter createIntegerFormatter() {
        NumberFormatter formatter = new NumberFormatter(new java.text.DecimalFormat("#0"));
        formatter.setValueClass(Integer.class);
        formatter.setAllowsInvalid(false);
        formatter.setMinimum(0);
        return formatter;
    }

    private void loadInitialData() {
        try {
            repository.initializeCategoryFolders();
        } catch (Exception e) {
            showError("No se pudieron crear las carpetas de categorías: " + e.getMessage());
        }
        refreshProductList();
    }

    private void refreshProductList() {
        productListModel.clear();
        try {
            for (Product product : repository.findAll()) {
                productListModel.addElement(product);
            }
        } catch (Exception e) {
            showError("Error al cargar los productos: " + e.getMessage());
        }
    }

    private void loadProduct(Product product) {
        categoryComboBox.setSelectedItem(product.getCategory());
        nameField.setText(product.getName());
        priceField.setValue(product.getPrice().doubleValue());
        quantityField.setValue(product.getQuantity());
        supplierNameField.setText(product.getSupplierName());
        supplierContactField.setText(product.getSupplierContact());
        descriptionArea.setText(product.getDescription());
        if (product.getLastUpdated() != null) {
            lastUpdatedLabel.setText(product.getLastUpdated().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        } else {
            lastUpdatedLabel.setText("-");
        }
    }

    private void handleSave() {
        try {
            Product product = buildProductFromForm();
            repository.save(product);
            showMessage("Producto guardado correctamente");
            refreshProductList();
        } catch (IllegalArgumentException ex) {
            showError(ex.getMessage());
        } catch (Exception ex) {
            showError("No se pudo guardar el producto: " + ex.getMessage());
        }
    }

    private void handleDelete() {
        Product selected = productList.getSelectedValue();
        if (selected == null) {
            showError("Seleccione un producto para eliminar");
            return;
        }
        int result = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de eliminar el producto seleccionado?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            try {
                boolean deleted = repository.delete(selected.getCategory(), selected.getName());
                if (deleted) {
                    showMessage("Producto eliminado");
                    refreshProductList();
                    clearForm();
                } else {
                    showError("No se encontró el archivo del producto");
                }
            } catch (Exception e) {
                showError("No se pudo eliminar: " + e.getMessage());
            }
        }
    }

    private Product buildProductFromForm() {
        Product product = new Product();
        product.setCategory((ProductCategory) categoryComboBox.getSelectedItem());
        product.setName(nameField.getText().trim());
        product.setPrice(BigDecimal.valueOf(((Number) priceField.getValue()).doubleValue()));
        product.setQuantity(((Number) quantityField.getValue()).intValue());
        product.setSupplierName(supplierNameField.getText().trim());
        product.setSupplierContact(supplierContactField.getText().trim());
        product.setDescription(descriptionArea.getText().trim());
        return product;
    }

    private void clearForm() {
        categoryComboBox.setSelectedIndex(0);
        nameField.setText("");
        priceField.setValue(0.0);
        quantityField.setValue(0);
        supplierNameField.setText("");
        supplierContactField.setText("");
        descriptionArea.setText("");
        lastUpdatedLabel.setText("-");
        productList.clearSelection();
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
