package forms;

import clases.GestionCliente;
import clases.GestionMascota;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class frmCliente extends javax.swing.JFrame {

    private String dui;
    GestionCliente gestion = new GestionCliente();
    GestionMascota mascota = new GestionMascota();
    ImageIcon img = new ImageIcon("src/imagenes/cliente.png");

    public frmCliente() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setIconImage(img.getImage());
        cmbEspecialidad.setMaximumRowCount(15);
    }
    
    public frmCliente(String dui) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setIconImage(img.getImage());
        this.dui=dui;
        ftxtDui.setText(dui);
        this.btnBuscar.doClick();
        cmbEspecialidad.setMaximumRowCount(15);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDui = new javax.swing.JLabel();
        ftxtDui = new javax.swing.JFormattedTextField();
        btnGuardar = new javax.swing.JButton();
        lnlNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnAgregarMascota = new javax.swing.JButton();
        lblCelular = new javax.swing.JLabel();
        ftxtCelular = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaMascotas = new javax.swing.JTextArea();
        btnBuscar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        lblCita = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lblMotivo = new javax.swing.JLabel();
        txtMotivo = new javax.swing.JTextField();
        lblEspecialidad = new javax.swing.JLabel();
        cmbEspecialidad = new javax.swing.JComboBox<>();
        btnProcesar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestión de clientes");

        lblDui.setText("D.U.I.");

        try {
            ftxtDui.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar cliente");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        lnlNombre.setText("Nombre");

        btnAgregarMascota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo.png"))); // NOI18N
        btnAgregarMascota.setText("Agregar mascota");
        btnAgregarMascota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarMascotaActionPerformed(evt);
            }
        });

        lblCelular.setText("Celular");

        try {
            ftxtCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        txtaMascotas.setEditable(false);
        txtaMascotas.setColumns(20);
        txtaMascotas.setRows(5);
        jScrollPane1.setViewportView(txtaMascotas);

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btnBuscar.setText("Buscar D.U.I.");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cliente.png"))); // NOI18N
        btnNuevo.setText("Nuevo cliente");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        lblCita.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCita.setForeground(new java.awt.Color(255, 0, 0));
        lblCita.setText("¿Desea hacer una cita médica?");

        lblId.setText("Id de mascota");

        lblMotivo.setText("Motivo");

        lblEspecialidad.setText("Especialidad");

        cmbEspecialidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Medicina General", "Medicina interna veterinaria", "Odontología veterinaria", "Cirugía veterinaria", "Dermatología veterinaria", "Cardiología veterinaria", "Neurología veterinaria", "Ortopedia veterinaria", "Reproducción y obstetricia veterinaria", "Oftalmología veterinaria", "Microbiología y patología veterinaria", "Medicina preventiva y salud pública veterinaria", "Veterinaria de grandes animales o producción", "Veterinaria de animales pequeños", "Veterinaria de animales exóticos y silvestres" }));

        btnProcesar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cita.png"))); // NOI18N
        btnProcesar.setText("Crear Cita");
        btnProcesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDui)
                            .addComponent(lnlNombre)
                            .addComponent(lblCelular))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ftxtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ftxtDui, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)
                                .addComponent(btnBuscar))
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAgregarMascota, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCita, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEspecialidad))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMotivo)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmbEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(btnProcesar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDui)
                            .addComponent(ftxtDui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBuscar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lnlNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCelular)
                    .addComponent(ftxtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnNuevo)
                    .addComponent(btnAgregarMascota))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCita)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblId)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMotivo)
                    .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEspecialidad)
                    .addComponent(cmbEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProcesar))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {ftxtCelular, ftxtDui, txtNombre});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        gestion.guardar(ftxtDui.getText(), txtNombre.getText(), ftxtCelular.getText());
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnAgregarMascotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarMascotaActionPerformed
        frmMascota form1 = new frmMascota(ftxtDui.getText(), txtNombre.getText());
        form1.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAgregarMascotaActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
     
        int iteracion = 1;
        String dato = gestion.recuperar(ftxtDui.getText(), iteracion);

        if (!dato.equals("")) {
            for (iteracion = 1; iteracion <= 3; iteracion++) {
                dato = gestion.recuperar(ftxtDui.getText(), iteracion);
                switch (iteracion) {
                    case 1:
                        ftxtDui.setText(dato);
                        break;
                    case 2:
                        txtNombre.setText(dato);
                        break;
                    case 3:
                        ftxtCelular.setText(dato);
                        break;
                }
            }
        } 
        else {
            JOptionPane.showMessageDialog(null, "No existe ese D.U.I., tome datos al Cliente");
            txtNombre.requestFocus();
        } 
        
        if (!txtNombre.getText().isEmpty()){
            txtaMascotas.setText(gestion.mostrarMascotas(ftxtDui.getText(), txtNombre.getText()));
        }
        

        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        ftxtDui.setText("");
        txtNombre.setText("");
        ftxtCelular.setText("");
        txtaMascotas.setText("");
        this.dui="";
        ftxtDui.requestFocus();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesarActionPerformed
        String datos="",especialidad;
        if (!txtNombre.getText().isEmpty()){
            if (!txtId.getText().isEmpty()){
               //¿existe la mascota?
               if (!mascota.verificar(ftxtDui.getText(),txtId.getText()).equals("")){
                   datos="Cliente: "+ftxtDui.getText()+" - "+txtNombre.getText()+" - "+"Celular: "+ftxtCelular.getText();
                   datos=datos+"\nMascota: "+mascota.verificar(ftxtDui.getText(),txtId.getText());
                   especialidad=String.valueOf(cmbEspecialidad.getSelectedItem());
                   datos=datos+"\nEspecialidad: "+String.valueOf(cmbEspecialidad.getSelectedItem());
                   frmCita cita = new frmCita(datos,especialidad);
                   cita.setVisible(true);
                   
               }
               else{
                   JOptionPane.showMessageDialog(null, "La mascota no está relacionada al cliente mostrado"); 
               }
            }
            else{
                JOptionPane.showMessageDialog(null, "Falta indicar el Id de su mascota");
                txtId.requestFocus();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Primero debe ingresar datos del cliente");
            ftxtDui.requestFocus();
        }
        
        
    }//GEN-LAST:event_btnProcesarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarMascota;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnProcesar;
    private javax.swing.JComboBox<String> cmbEspecialidad;
    private javax.swing.JFormattedTextField ftxtCelular;
    private javax.swing.JFormattedTextField ftxtDui;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCelular;
    private javax.swing.JLabel lblCita;
    private javax.swing.JLabel lblDui;
    private javax.swing.JLabel lblEspecialidad;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblMotivo;
    private javax.swing.JLabel lnlNombre;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMotivo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextArea txtaMascotas;
    // End of variables declaration//GEN-END:variables
}
