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

    // Getters y setters

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
