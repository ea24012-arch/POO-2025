package clases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class GestionMedico {

    private String ruta, rutaUltimoMedico, contenido, contenidoUltimo;
    //DefaultComboBoxModel<String> modeloMedico = new DefaultComboBoxModel<>();

    public int contar() {
        int ultimo = 0;
        File documento = new File("src/archivos/ultimoMedico.txt");

        Scanner obj;
        try {
            obj = new Scanner(documento);
            while (obj.hasNextLine()) {
                ultimo = Integer.parseInt(obj.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Excepción: " + ex);
        }
        return ultimo;
    }

    public void guardar(int id, String nombre, String especialidad) {
        ruta = "src/archivos/medicos.txt";
        rutaUltimoMedico = "src/archivos/ultimoMedico.txt";
        if (id == 1) {
            contenido = id + "\t" + nombre + "\t" + especialidad;
        } else {
            contenido = "\n" + id + "\t" + nombre + "\t" + especialidad;
        }

        //anexar datos al archivo medicos.txt
        try (FileWriter fw = new FileWriter(ruta, true)) { // true = agregar
            fw.write(contenido);
            fw.close();
            System.out.println("Datos agregados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }

        //actualizar la numeración del medico
        contenidoUltimo = String.valueOf(id);
        File archivoUltimo = new File(rutaUltimoMedico);
        try {
            archivoUltimo.createNewFile();
            FileWriter escrituraUltimo = new FileWriter(archivoUltimo);
            try (BufferedWriter datos = new BufferedWriter(escrituraUltimo)) {
                datos.write(contenidoUltimo);
                //JOptionPane.showMessageDialog(null, "Archivo creado con éxito");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Excepción: " + e);
            }
        } catch (IOException ex) {
            Logger.getLogger(GestionMedico.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public DefaultComboBoxModel<String> obtenerModeloMedicos(String especialidad) {
        
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        this.ruta = "src/archivos/medicos.txt";
        File documento = new File(ruta);
        Scanner obj;
        String linea="";

        try {
            obj = new Scanner(documento);
            while (obj.hasNextLine()) {
                linea=obj.nextLine();
                //JOptionPane.showMessageDialog(null, linea);
                if (linea.contains(especialidad)){
                    
                    modelo.addElement(linea);}
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Excepción: " + ex);
        }

        return modelo;

    }

}
