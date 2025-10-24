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
