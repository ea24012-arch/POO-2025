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
