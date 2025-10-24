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
