/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sewamotor;
import static sewamotor.Koneksi.buatkoneks;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bagus Oka
 */
public class SewamotorFrame extends javax.swing.JFrame {

    private DefaultTableModel TM = new DefaultTableModel();
    
    /**
     * key = index
     * value = id
     */
    private HashMap<Integer, Integer> matchId = new HashMap<Integer, Integer>();
    private int currentIndex = 0;
    
    
    public int getCurrentId() {
        return this.matchId.get(this.currentIndex);
    }
    /**
     * Creates new form Frame
     */
    public SewamotorFrame() throws SQLException {
        initComponents();
        jTable1.setModel(TM);
        
        TM.addColumn("Motor");
        TM.addColumn("Pengguna");
        TM.addColumn("Tanggal sewa");
        TM.addColumn("Lama Hari");
        
        this.mount();
//        fillSelects();
////        if (State.motor.isEmpty()) {
////            State.initMotor();
////        }
////        
////         if (State.pengguna.isEmpty()) {
////            State.initPengguna();
////        }
////        
////        State.motor.forEach((key, value) -> {
////            System.out.println(value);
////            motorSelect.addItem(key);
////        });
////        
////        State.pengguna.forEach((key, value) -> {
////            System.out.println(value);
////            penggunaSelect.addItem(key);
////        });
//                
//        List_All();
//        kosongkanform();
    }
    
    void mount() throws SQLException {
        System.out.println("Jalan");
        fillSelects();
        List_All();
        kosongkanform();
    }
    
    void fillSelects() {
        State.initMotor();
        State.initPengguna();
         
        motorSelect.removeAllItems();
        penggunaSelect.removeAllItems();
        
        State.motor.forEach((key, value) -> {
            System.out.printf("nama: %s, value: %d", key, value);
            motorSelect.addItem(key);
        });
        
        State.pengguna.forEach((key, value) -> {
            penggunaSelect.addItem(key);
        });
    }
    
    
    private void kosongkanform(){
        tanggalSewaInput.setText("");
        lamaSewaInput.setText("");
        penggunaSelect.setSelectedIndex(0);
    }

    private void StoreData()throws SQLException{
        Connection cnn = buatkoneks();
        String TS = tanggalSewaInput.getText ();
        String LS = lamaSewaInput.getText();
        
        String PG = (String) penggunaSelect.getSelectedItem();
        Integer PGID = State.pengguna.get(PG);
      
        String MT = (String) motorSelect.getSelectedItem();
        Integer MTID = State.motor.get(MT);

        
        if(!cnn.isClosed()){
            PreparedStatement PS = cnn.prepareStatement(
            "INSERT INTO sewamotor (id_motor,id_pengguna,tanggal_sewa,lama_sewa) VALUES(?,?,?,?);");
            PS.setInt(1, MTID);
            PS.setInt(2, PGID);
            PS.setString(3, TS);            
            PS.setString(4, LS);

            PS.executeUpdate();
        }
    }
    
    private void List_All() throws SQLException{
        TM.getDataVector().removeAllElements();
        TM.fireTableDataChanged();
        
        Connection cnn = buatkoneks();
        int index = 0;
        
        if( !cnn.isClosed() ){       
            String sql = ""
                    + "SELECT *, CONCAT(lama_sewa, ' hari') AS lama_hari, CONCAT(merek, ' - ', plat_motor) AS nama_motor FROM sewamotor "
                    + "INNER JOIN motor ON sewamotor.id_motor = motor.id_motor "
                    + "INNER JOIN pengguna ON sewamotor.id_pengguna = pengguna.id_pengguna "
                    + "ORDER BY id_sewamotor DESC;";
            
            PreparedStatement PS = cnn.prepareStatement(sql);
            ResultSet rs = PS.executeQuery();
            
            
            while( rs.next() ){
                Object[] dta = new Object[4];
                dta[0] = rs.getString("nama_motor");
                dta[1] = rs.getString("nama_lengkap");
                dta[2] = rs.getString("tanggal_sewa");
                dta[3] = rs.getString("lama_hari");

                this.matchId.put(index, rs.getInt("id_sewamotor"));
                
                TM.addRow(dta);
                index += 1;
            }
        }
        
    }
    
    private void UpdateData() throws SQLException{

        Connection cnn = buatkoneks();
        String TS = tanggalSewaInput.getText ();
        String LS = lamaSewaInput.getText();
        
        String PG = (String) penggunaSelect.getSelectedItem();
        Integer PGID = State.pengguna.get(PG);
      
        String MT = (String) motorSelect.getSelectedItem();
        Integer MTID = State.motor.get(MT);
        
        if(!cnn.isClosed()){
            PreparedStatement PS = cnn.prepareStatement("UPDATE sewamotor SET id_motor=?, id_pengguna=?, tanggal_sewa=?, lama_sewa=? WHERE id_sewamotor=?;");
            PS.setInt(1, MTID);
            PS.setInt(2, PGID);
            PS.setString(3, TS);            
            PS.setString(4, LS);
            PS.setInt(5, this.getCurrentId());
            
            
            PS.executeUpdate();
            cnn.close();
        }
  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txID = new javax.swing.JTextField();
        txNAMA = new javax.swing.JTextField();
        txALAMAT = new javax.swing.JTextField();
        txTELP = new javax.swing.JTextField();
        label1 = new java.awt.Label();
        penggunaBtn = new java.awt.Button();
        motorBtn = new java.awt.Button();
        hapusBtn = new java.awt.Button();
        label2 = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lamaSewaInput = new javax.swing.JTextField();
        tanggalSewaInput = new javax.swing.JTextField();
        penggunaSelect = new javax.swing.JComboBox<>();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        label5 = new java.awt.Label();
        buatBtn = new java.awt.Button();
        updateBtn = new java.awt.Button();
        sewamotorBtn1 = new java.awt.Button();
        motorSelect = new javax.swing.JComboBox<>();
        label7 = new java.awt.Label();

        txID.setText("Sebutkan Cepat");

        txNAMA.setText("Tulis Cepatlah");

        txALAMAT.setText("Mana Alamatnya Cepat");

        txTELP.setText("Kirim Sekalian Sini");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        label1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label1.setText("Motorku");

        penggunaBtn.setBackground(new java.awt.Color(240, 240, 240));
        penggunaBtn.setLabel("pengguna");
        penggunaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                penggunaBtnActionPerformed(evt);
            }
        });

        motorBtn.setLabel("motor");
        motorBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                motorBtnActionPerformed(evt);
            }
        });

        hapusBtn.setLabel("Selesai Sewa");
        hapusBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusBtnActionPerformed(evt);
            }
        });

        label2.setAlignment(java.awt.Label.CENTER);
        label2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label2.setText("Tabel Sewamotor");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Motor", "Pengguna", "Tanggal sewa", "Lama sewa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTable1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        tanggalSewaInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanggalSewaInputActionPerformed(evt);
            }
        });

        penggunaSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                penggunaSelectActionPerformed(evt);
            }
        });

        label3.setText("Motor");

        label4.setText("Lama Sewa (hari)");

        label5.setText("Tanggal sewa");

        buatBtn.setLabel("Buat");
        buatBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buatBtnActionPerformed(evt);
            }
        });

        updateBtn.setLabel("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        sewamotorBtn1.setBackground(new java.awt.Color(0, 153, 255));
        sewamotorBtn1.setLabel("sewa motor");

        motorSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                motorSelectActionPerformed(evt);
            }
        });

        label7.setText("Pengguna");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(penggunaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(motorBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sewamotorBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buatBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hapusBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lamaSewaInput, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                        .addComponent(label5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tanggalSewaInput, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(penggunaSelect, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(motorSelect, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(motorBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(penggunaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sewamotorBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tanggalSewaInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(penggunaSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lamaSewaInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(motorSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buatBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(hapusBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void penggunaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_penggunaBtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
        try {
            Sewamotor.penggunaFrame.mount();
        } catch (SQLException ex) {
            Logger.getLogger(MotorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        Sewamotor.penggunaFrame.setVisible(true);
    }//GEN-LAST:event_penggunaBtnActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        motorSelect.setSelectedItem(
            jTable1.getValueAt( jTable1.getSelectedRow(),0 ).toString()
        );
        penggunaSelect.setSelectedItem(
            jTable1.getValueAt( jTable1.getSelectedRow(),1 ).toString()
        );
        
        tanggalSewaInput.setText( jTable1.getValueAt( jTable1.getSelectedRow(),2 ).toString() );
        String lamaHari = jTable1.getValueAt( jTable1.getSelectedRow(), 3).toString();
        
        lamaSewaInput.setText(lamaHari.substring(0,1));
        this.currentIndex = jTable1.getSelectedRow();
        
//        jTable1.getSelectedRow()
        
//        txALAMAT.setText( jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString() );
//        txTELP.setText( jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString() );
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTable1AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1AncestorAdded

    private void penggunaSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_penggunaSelectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_penggunaSelectActionPerformed

    private void tanggalSewaInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanggalSewaInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tanggalSewaInputActionPerformed

    private void buatBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buatBtnActionPerformed
        try {
            // TODO add your handling code here:
            this.StoreData();
            this.List_All();
            this.kosongkanform();

        } catch (SQLException ex) {
            Logger.getLogger(SewamotorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buatBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        try {
            // TODO add your handling code here:
            this.UpdateData();
            this.List_All();
            this.kosongkanform();
        } catch (SQLException ex) {
            Logger.getLogger(SewamotorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_updateBtnActionPerformed
    private void destroyData() throws SQLException{
        Connection cnn = buatkoneks();
        if(!cnn.isClosed()){
            PreparedStatement PS = cnn.prepareStatement("DELETE FROM sewamotor WHERE id_sewamotor=?;");
            PS.setInt(1, this.getCurrentId());
            PS.executeUpdate();
            cnn.close();
        }
    }
    private void hapusBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusBtnActionPerformed
       int jwb = JOptionPane.showOptionDialog(
            this,
            "Anda ingin sudah selesai menyewa motor "+ (String) motorSelect.getSelectedItem()+"?",
            "Sudah",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE,
            null,
            null,
            null
       );
        if (jwb != JOptionPane.YES_OPTION) {
            return ;
        } 
        try {
             
            // TODO add your handling code here:
            this.destroyData();
            this.List_All();
            this.kosongkanform();
        } catch (SQLException ex) {
            Logger.getLogger(SewamotorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_hapusBtnActionPerformed

    private void motorBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_motorBtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
        try {
            Sewamotor.motorFrame.mount();
        } catch (SQLException ex) {
            Logger.getLogger(SewamotorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        Sewamotor.motorFrame.setVisible(true);
    }//GEN-LAST:event_motorBtnActionPerformed

    private void motorSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_motorSelectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_motorSelectActionPerformed

    
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
            java.util.logging.Logger.getLogger(SewamotorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SewamotorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SewamotorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SewamotorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new SewamotorFrame().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(SewamotorFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button buatBtn;
    private java.awt.Button hapusBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label label7;
    private javax.swing.JTextField lamaSewaInput;
    private java.awt.Button motorBtn;
    private javax.swing.JComboBox<String> motorSelect;
    private java.awt.Button penggunaBtn;
    private javax.swing.JComboBox<String> penggunaSelect;
    private java.awt.Button sewamotorBtn1;
    private javax.swing.JTextField tanggalSewaInput;
    private javax.swing.JTextField txALAMAT;
    private javax.swing.JTextField txID;
    private javax.swing.JTextField txNAMA;
    private javax.swing.JTextField txTELP;
    private java.awt.Button updateBtn;
    // End of variables declaration//GEN-END:variables
}
