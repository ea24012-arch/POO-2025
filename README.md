# Gestor de productos para papelería

Este repositorio contiene un proyecto **Java + Swing** preparado para compilarse y ejecutarse con **Apache NetBeans 24** utilizando **Ant**. El sistema permite gestionar la información de los productos de una papelería, organizándolos en carpetas por categoría y generando un archivo por cada producto registrado.

## Características principales

- Interfaz gráfica (`frmProducto`) creada con Swing.
- Gestión de datos de cada producto:
  - Categoría del producto (catálogo predefinido).
  - Nombre del producto.
  - Precio unitario.
  - Cantidad en inventario.
  - Nombre y contacto del proveedor.
  - Descripción y notas adicionales.
- Creación automática de carpetas por categoría dentro de la carpeta `data/`.
- Un archivo `.properties` por producto con sus datos.
- Operaciones disponibles: crear/actualizar, listar, cargar desde la lista y eliminar productos.

## Requisitos

- JDK 17 o superior instalado y configurado en el sistema.
- Apache NetBeans 24 (o un IDE compatible con proyectos Ant).

## Estructura del proyecto

```
POO-2025/
├─ build.xml              # Script Ant principal
├─ src/main/java/         # Código fuente Java
│  └─ com/exam/papeleria/
│     ├─ Main.java
│     ├─ model/
│     │  ├─ Product.java
│     │  ├─ ProductCategory.java
│     │  └─ ProductRepository.java
│     ├─ util/
│     │  └─ FileNames.java
│     └─ view/
│        └─ ProductForm.java
├─ data/                  # Se generan carpetas y archivos de productos
└─ dist/                  # Se creará al compilar con Ant
```

## Plan A: importar el proyecto completo en NetBeans (recomendado)

1. Descarga el repositorio como `.zip` desde GitHub o clónalo con Git.
2. Descomprime la carpeta (si descargaste `.zip`).
3. Abre NetBeans 24 y selecciona **File > Open Project...**.
4. Navega a la carpeta `POO-2025` y selecciónala. NetBeans detectará el proyecto Ant automáticamente.
5. En la ventana *Projects*, haz clic derecho sobre el proyecto y elige **Clean and Build** para compilar.
6. Ejecuta el proyecto con **Run Project (F6)**. Se abrirá la interfaz gráfica `Gestor de productos de papelería`.

## Plan B: copiar y pegar manualmente los archivos

Si por algún motivo no puedes importar el proyecto completo, sigue estos pasos para recrearlo manualmente en NetBeans:

1. Crea un nuevo proyecto en NetBeans: **Java with Ant > Java Application**. Desmarca "Create Main Class".
2. Una vez creado, elimina el paquete por defecto que genera NetBeans dentro de `src`.
3. Crea los siguientes paquetes:
   - `com.exam.papeleria`
   - `com.exam.papeleria.model`
   - `com.exam.papeleria.util`
   - `com.exam.papeleria.view`
4. Copia y pega el contenido de cada archivo que se muestra en la sección [Código fuente completo](#código-fuente-completo) dentro de los archivos correspondientes en NetBeans.
5. Crea (si no existe) la carpeta `data` en la raíz del proyecto. Aquí se generarán automáticamente las subcarpetas por categoría y los archivos `.properties` de cada producto.
6. Establece `com.exam.papeleria.Main` como clase principal desde **Project Properties > Run**.
7. Compila con **Clean and Build** y ejecuta con **Run**.

## Uso de la aplicación

1. Selecciona la categoría del producto.
2. Ingresa el nombre, precio, cantidad, proveedor, contacto y notas.
3. Pulsa **Guardar/Actualizar** para crear o actualizar el archivo del producto. El sistema guarda la fecha de la última actualización.
4. La lista de la derecha muestra todos los productos guardados. Al seleccionar uno, sus datos se cargan en el formulario.
5. Para eliminar un producto, selecciónalo en la lista y pulsa **Eliminar**.
6. El botón **Limpiar formulario** reinicia los campos para capturar un nuevo producto.
7. Puedes crear, modificar o eliminar archivos directamente desde la carpeta `data` si lo necesitas.

## Ejecutar desde la terminal

Si prefieres compilar y ejecutar sin NetBeans, abre una terminal en la carpeta del proyecto y ejecuta:

```bash
ant clean
ant run
```

La primera vez que ejecutes `ant run` se compilará el proyecto, se generará `dist/GestorPapeleria.jar` y se abrirá la interfaz gráfica.

## Código fuente completo

### `build.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project name="PapeleriaManager" default="run" basedir=".">
    <description>
        Proyecto Ant generado manualmente para gestionar productos de una papelería.
    </description>

    <property name="src.dir" location="src/main/java"/>
    <property name="build.dir" location="build"/>
    <property name="classes.dir" location="${build.dir}/classes"/>
    <property name="dist.dir" location="dist"/>
    <property name="main.class" value="com.exam.papeleria.Main"/>
    <property name="application.title" value="GestorPapeleria"/>

    <target name="clean">
        <delete dir="${build.dir}"/>
        <delete dir="${dist.dir}"/>
    </target>

    <target name="prepare">
        <mkdir dir="${classes.dir}"/>
        <mkdir dir="${dist.dir}"/>
    </target>

    <target name="compile" depends="prepare">
        <javac srcdir="${src.dir}" destdir="${classes.dir}" includeantruntime="false" debug="true">
            <include name="**/*.java"/>
        </javac>
        <copy todir="${classes.dir}">
            <fileset dir="${src.dir}" excludes="**/*.java"/>
        </copy>
    </target>

    <target name="jar" depends="compile">
        <mkdir dir="${dist.dir}"/>
        <jar destfile="${dist.dir}/${application.title}.jar" basedir="${classes.dir}">
            <manifest>
                <attribute name="Main-Class" value="${main.class}"/>
                <attribute name="Class-Path" value="."/>
            </manifest>
        </jar>
    </target>

    <target name="run" depends="jar">
        <java jar="${dist.dir}/${application.title}.jar" fork="true"/>
    </target>
</project>
```

### `src/main/java/com/exam/papeleria/Main.java`

```java
package com.exam.papeleria;

import com.exam.papeleria.view.ProductForm;
import javax.swing.SwingUtilities;

/**
 * Punto de entrada de la aplicación Gestor de Papelería.
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductForm form = new ProductForm();
            form.setLocationRelativeTo(null);
            form.setVisible(true);
        });
    }
}
```

### `src/main/java/com/exam/papeleria/model/ProductCategory.java`

```java
package com.exam.papeleria.model;

/**
 * Categorías de productos disponibles en la papelería.
 */
public enum ProductCategory {
    UTILES_ESCOLARES("Útiles escolares"),
    OFICINA_ADMINISTRATIVA("Oficina y papelería administrativa"),
    ARTE_MANUALIDADES("Materiales de arte y manualidad"),
    DECORACION_TEMPORADA("Decoración y temporada"),
    LIBROS_EDUCATIVOS("Libros y material educativo"),
    MATERIAL_RECICLABLE("Material reciclable");

    private final String displayName;

    ProductCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
```

### `src/main/java/com/exam/papeleria/model/Product.java`

```java
package com.exam.papeleria.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

/**
 * Representa un producto que se gestiona en la papelería.
 */
public class Product {

    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;

    private String name;
    private ProductCategory category;
    private BigDecimal price;
    private int quantity;
    private String supplierName;
    private String supplierContact;
    private String description;
    private LocalDate lastUpdated;

    public Product() {
    }

    public Product(String name, ProductCategory category, BigDecimal price, int quantity,
                   String supplierName, String supplierContact, String description,
                   LocalDate lastUpdated) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.supplierName = supplierName;
        this.supplierContact = supplierContact;
        this.description = description;
        this.lastUpdated = lastUpdated;
    }

    public static Product fromProperties(Properties properties) {
        Product product = new Product();
        product.setName(properties.getProperty("name", ""));
        product.setCategory(ProductCategory.valueOf(properties.getProperty("category")));
        product.setPrice(new BigDecimal(properties.getProperty("price", "0")));
        product.setQuantity(Integer.parseInt(properties.getProperty("quantity", "0")));
        product.setSupplierName(properties.getProperty("supplierName", ""));
        product.setSupplierContact(properties.getProperty("supplierContact", ""));
        product.setDescription(properties.getProperty("description", ""));
        product.setLastUpdated(Optional.ofNullable(properties.getProperty("lastUpdated"))
                .map(value -> LocalDate.parse(value, DATE_FORMAT))
                .orElse(null));
        return product;
    }

    public Properties toProperties() {
        Properties properties = new Properties();
        properties.setProperty("name", getName());
        properties.setProperty("category", getCategory().name());
        properties.setProperty("price", getPrice().toPlainString());
        properties.setProperty("quantity", Integer.toString(getQuantity()));
        properties.setProperty("supplierName", Optional.ofNullable(getSupplierName()).orElse(""));
        properties.setProperty("supplierContact", Optional.ofNullable(getSupplierContact()).orElse(""));
        properties.setProperty("description", Optional.ofNullable(getDescription()).orElse(""));
        if (getLastUpdated() != null) {
            properties.setProperty("lastUpdated", DATE_FORMAT.format(getLastUpdated()));
        }
        return properties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierContact() {
        return supplierContact;
    }

    public void setSupplierContact(String supplierContact) {
        this.supplierContact = supplierContact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && category == product.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category);
    }

    @Override
    public String toString() {
        return category.getDisplayName() + " - " + name;
    }
}
```

### `src/main/java/com/exam/papeleria/model/ProductRepository.java`

```java
package com.exam.papeleria.model;

import com.exam.papeleria.util.FileNames;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

/**
 * Gestiona la persistencia de los productos en carpetas por categoría.
 */
public class ProductRepository {

    private final Path baseDirectory;

    public ProductRepository(Path baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    public ProductRepository() {
        this(Paths.get("data"));
    }

    public void initializeCategoryFolders() throws IOException {
        for (ProductCategory category : ProductCategory.values()) {
            Path categoryDir = resolveCategoryDirectory(category);
            if (Files.notExists(categoryDir)) {
                Files.createDirectories(categoryDir);
            }
        }
    }

    public Product save(Product product) throws IOException {
        validateProduct(product);
        product.setLastUpdated(LocalDate.now());
        Path productFile = resolveProductFile(product.getCategory(), product.getName());
        Files.createDirectories(productFile.getParent());
        Properties properties = product.toProperties();
        try (OutputStream outputStream = Files.newOutputStream(productFile)) {
            properties.store(outputStream, "Producto gestionado por GestorPapeleria");
        }
        return product;
    }

    public Optional<Product> find(ProductCategory category, String name) throws IOException {
        Path productFile = resolveProductFile(category, name);
        if (Files.notExists(productFile)) {
            return Optional.empty();
        }
        Properties properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(productFile)) {
            properties.load(inputStream);
        }
        return Optional.of(Product.fromProperties(properties));
    }

    public List<Product> findAll() throws IOException {
        List<Product> products = new ArrayList<>();
        for (ProductCategory category : ProductCategory.values()) {
            products.addAll(findByCategory(category));
        }
        products.sort(Comparator.comparing(Product::getCategory).thenComparing(Product::getName));
        return products;
    }

    public List<Product> findByCategory(ProductCategory category) throws IOException {
        Path categoryDirectory = resolveCategoryDirectory(category);
        List<Product> products = new ArrayList<>();
        if (Files.notExists(categoryDirectory)) {
            return products;
        }
        try (var paths = Files.list(categoryDirectory)) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().endsWith(".properties"))
                    .forEach(path -> {
                        Properties properties = new Properties();
                        try (InputStream inputStream = Files.newInputStream(path)) {
                            properties.load(inputStream);
                            products.add(Product.fromProperties(properties));
                        } catch (IOException e) {
                            throw new RuntimeException("Error al cargar " + path, e);
                        }
                    });
        }
        products.sort(Comparator.comparing(Product::getName));
        return products;
    }

    public boolean delete(ProductCategory category, String name) throws IOException {
        Path productFile = resolveProductFile(category, name);
        if (Files.exists(productFile)) {
            Files.delete(productFile);
            return true;
        }
        return false;
    }

    private void validateProduct(Product product) {
        if (product.getName() == null || product.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio");
        }
        if (product.getCategory() == null) {
            throw new IllegalArgumentException("Debe seleccionar una categoría");
        }
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio debe ser mayor o igual a cero");
        }
        if (product.getQuantity() < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
    }

    private Path resolveCategoryDirectory(ProductCategory category) {
        return baseDirectory.resolve(FileNames.safeDirectoryName(category.name()));
    }

    private Path resolveProductFile(ProductCategory category, String name) {
        String fileName = FileNames.safeFileName(name) + ".properties";
        return resolveCategoryDirectory(category).resolve(fileName);
    }
}
```

### `src/main/java/com/exam/papeleria/util/FileNames.java`

```java
package com.exam.papeleria.util;

import java.text.Normalizer;

/**
 * Utilidades para crear nombres de carpetas y archivos seguros.
 */
public final class FileNames {

    private FileNames() {
    }

    public static String safeFileName(String input) {
        return sanitize(input).replaceAll("[^a-zA-Z0-9_-]", "_");
    }

    public static String safeDirectoryName(String input) {
        return sanitize(input).replaceAll("[^a-zA-Z0-9_-]", "_");
    }

    private static String sanitize(String input) {
        String value = input == null ? "" : input;
        String normalized = Normalizer.normalize(value, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
    }
}
```

### `src/main/java/com/exam/papeleria/view/ProductForm.java`

```java
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
```

---

¡Éxitos en tu examen de Programación Orientada a Objetos!
