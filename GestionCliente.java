package clases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class GestionCliente {

    private String ruta;

    public GestionCliente() {
    }

    public void guardar(String dui, String nombre, String celular) {

        this.ruta = "src/archivos/" + dui;
        String contenido;

        File directorio = new File(ruta);

        // mkdir() crea UN solo directorio
        if (directorio.mkdir()) {
            JOptionPane.showMessageDialog(null, "Directorio creado correctamente.");
            ruta = ruta + "/" + dui + ".txt";
            contenido = dui + "\n" + nombre + "\n" + celular;
            File archivo = new File(ruta);
            if (!archivo.exists()) {
                try {
                    archivo.createNewFile();
                    FileWriter escritura = new FileWriter(archivo);
                    try (BufferedWriter datos = new BufferedWriter(escritura)) {
                        datos.write(contenido);
                        JOptionPane.showMessageDialog(null, "Archivo creado con éxito");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Excepción: " + e);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El archivo ya existe");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo crear el directorio, verifique si ya existe");
        }
    }

    public String recuperar(String dui, int linea) {
        this.ruta = "src/archivos/" + dui + "/" + dui + ".txt";
        File documento = new File(ruta);

        Scanner obj;
        String salida = "";
        int iteracion = 1;

        File archivo = new File(ruta);
        if (archivo.exists()) {
            try {
                obj = new Scanner(documento);
                while (obj.hasNextLine() && iteracion <= linea) {
                    salida = obj.nextLine();
                    iteracion = iteracion + 1;
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Excepción: " + ex);
            }
        }

        return salida;
    }

    public String mostrarMascotas(String dui, String nombre) {
        ruta = "src/archivos/" + dui + "/";
        String salida = "";
        File carpeta = new File(ruta);

        // Verifica que la ruta sea un directorio
        if (carpeta.isDirectory()) {
            // Obtiene todos los archivos/carpetas dentro
            File[] archivos = carpeta.listFiles();
            if (archivos != null) {
                salida = salida + "Mascotas a cargo de: " + dui + " - " + nombre + "\n";
                salida = salida + "Id\tNombre\t\tEspecie\tEdad\tRegistro\tSexo\n";
                for (File archivo : archivos) {
                    if (archivo.isDirectory()) {
                        //buscar los datos de la mascota
                        int linea=0;
                        ruta = "src/archivos/" + dui + "/" + archivo.getName()+"/"+archivo.getName()+".txt";
                        File documento = new File(ruta);
                        Scanner obj;
                        try {
                            obj = new Scanner(documento);
                            while (obj.hasNextLine()) {
                                if (linea == 0) {
                                    salida = salida + obj.nextLine();
                                } else if (linea==2) {
                                    salida = salida + "\t\t" + obj.nextLine();
                                }
                                else{
                                   salida = salida + "\t" + obj.nextLine(); 
                                }
                                linea = linea + 1;
                            }
                            salida=salida+"\n";
                        } catch (FileNotFoundException ex) {
                            JOptionPane.showMessageDialog(null,"Excepción: " + ex);
                        }
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Carpeta vacía");
            }
        } else {
            JOptionPane.showMessageDialog(null,ruta + " no es un directorio válido.");
        }
        return salida;
    }
    
    
}
