package clases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class GestionMascota {

    private String ruta;
    private String rutaUltimo;
    private String contenido = "", contenidoUltimo;

    public void guardar(String dui, int id, String nombre, String especie, int edad, String fechaRegistro, char sexo) {
        ruta = "src/archivos/"+dui+"/"+id; 
        File directorio = new File(ruta);

        // mkdir() crea UN solo directorio
        if (directorio.mkdir()) {
        rutaUltimo = "src/archivos/ultimo.txt";
        this.ruta = this.ruta + "/" + id + ".txt";
        this.contenido = id + "\n" + nombre + "\n" + especie + "\n" + edad + "\n" + fechaRegistro + "\n" + sexo;
        this.contenidoUltimo = String.valueOf(id);
        File archivo = new File(ruta);
        File archivoUltimo = new File(rutaUltimo);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                FileWriter escritura = new FileWriter(archivo);
                try (BufferedWriter datos = new BufferedWriter(escritura)) {
                    datos.write(contenido);
                    JOptionPane.showMessageDialog(null, "Archivo creado con éxito");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,"Excepción: " + e);
                }
                archivoUltimo.createNewFile();
                FileWriter escrituraUltimo = new FileWriter(archivoUltimo);
                try (BufferedWriter datos = new BufferedWriter(escrituraUltimo)) {
                    datos.write(contenidoUltimo);
                    //JOptionPane.showMessageDialog(null, "Archivo creado con éxito");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,"Excepción: " + e);
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(null, "El archivo ya existe");
        }
        }
        else{
            JOptionPane.showMessageDialog(null, "El directorio no pudo crearse, revise si ya existe");
        }
    }

    public String recuperar(String dui, int id,int linea) {
        File documento = new File("src/archivos/" + dui + "/" + id + "/" + id + ".txt");
        this.ruta = "src/archivos/" + dui + "/" + id + "/" + id + ".txt";
        Scanner obj;
        String salida="";
        int iteracion=1;
        
        File archivo = new File(ruta);
        if (archivo.exists()){
        try {
            obj = new Scanner(documento);
            while (obj.hasNextLine() && iteracion<=linea) {
                salida = obj.nextLine();
                iteracion=iteracion+1;
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Excepción: " + ex);
        }
        }

        return salida;
    }
    
    public int contar(){
        int ultimo=0;
        File documento = new File("src/archivos/ultimo.txt");

        Scanner obj;
        try {
            obj = new Scanner(documento);
            while (obj.hasNextLine()) {
                ultimo=Integer.parseInt(obj.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Excepción: " + ex);
        }
        return ultimo;
    }
    
    public String verificar(String dui, String id){
        String datosMascota="";
        Scanner obj;
        this.ruta = "src/archivos/" + dui + "/" + id + "/" + id + ".txt";
        File documento = new File(ruta);
        File archivo = new File(ruta);
        if (archivo.exists()){
        try {
            obj = new Scanner(documento);
            while (obj.hasNextLine()) {
                datosMascota = datosMascota+" - "+obj.nextLine();
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Excepción: " + ex);
        }
        }
        return datosMascota;
    }

}
