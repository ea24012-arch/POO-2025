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
