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
public class PenggunaFrame extends javax.swing.JFrame {

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
    public PenggunaFrame() throws SQLException {
        initComponents();
        jTable1.setModel(TM);
        
        TM.addColumn("Nama");
        TM.addColumn("No. Telepon");
        TM.addColumn("Kewarganegaraan");
        
        this.mount();
//        if (State.kewarganegaraan.isEmpty()) {
//            State.initKewarganegaraan();
//        }
//        
//        State.kewarganegaraan.forEach((key, value) -> {
//            System.out.println(value);
//            kewarganegaraanSelect.addItem(key);
//        });
//        
//        System.out.println(State.kewarganegaraan.size());
//        
//        List_All();
//        kosongkanform();
    }
    
    public void mount() throws SQLException {
        fillSelects();
        List_All();
        kosongkanform();
    }
    
    void fillSelects() {
        kewarganegaraanSelect.removeAllItems();
        
        State.initKewarganegaraan();
        State.kewarganegaraan.forEach((key, value) -> {
            System.out.println(value);
            kewarganegaraanSelect.addItem(key);
        });
    }
    
    private void kosongkanform(){
        nameInput.setText("");
        phoneInput.setText("");
        kewarganegaraanSelect.setSelectedIndex(0);
    }

    private void StoreData()throws SQLException{
        Connection cnn = buatkoneks();
        String NM = nameInput.getText ();
        String TL = phoneInput.getText();
        String KG = (String) kewarganegaraanSelect.getSelectedItem();
        Integer KGID = State.kewarganegaraan.get(KG);
      
        if(!cnn.isClosed()){
            PreparedStatement PS = cnn.prepareStatement(
            "INSERT INTO pengguna (nama_lengkap,no_telepon,id_kewarganegaraan) VALUES(?,?,?);");
            PS.setString(1, NM);
            PS.setString(2, TL);
            PS.setInt(3, KGID);
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
                    + "SELECT * FROM pengguna "
                    + "INNER JOIN kewarganegaraan ON pengguna.id_kewarganegaraan"
                    + " = kewarganegaraan.id_kewarganegaraan;";
            
            PreparedStatement PS = cnn.prepareStatement(sql);
            ResultSet rs = PS.executeQuery();
            
            
            while( rs.next() ){
                Object[] dta = new Object[4];
                dta[0] = rs.getString("nama_lengkap");
                dta[1] = rs.getString("no_telepon");
                dta[2] = rs.getString("nama");
                
                this.matchId.put(index, rs.getInt("id_pengguna"));
                
                TM.addRow(dta);
                index += 1;
            }
        }
        
    }
    
    private void UpdateData() throws SQLException{
        Connection cnn=buatkoneks();
        String NM = nameInput.getText ();
        String TL = phoneInput.getText();
        String KG = (String) kewarganegaraanSelect.getSelectedItem();
        Integer KGID = State.kewarganegaraan.get(KG);
        
        if(!cnn.isClosed()){
            PreparedStatement PS = cnn.prepareStatement("UPDATE pengguna SET nama_lengkap=?, no_telepon=?, id_kewarganegaraan=? WHERE id_pengguna=?;");
            
            PS.setString(1, NM);
            PS.setString(2, TL);
            PS.setInt(3, KGID);
            PS.setInt(4, this.getCurrentId());
            
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
        phoneInput = new javax.swing.JTextField();
        nameInput = new javax.swing.JTextField();
        kewarganegaraanSelect = new javax.swing.JComboBox<>();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        label5 = new java.awt.Label();
        buatBtn = new java.awt.Button();
        updateBtn = new java.awt.Button();
        sewamotorBtn1 = new java.awt.Button();

        txID.setText("Sebutkan Cepat");

        txNAMA.setText("Tulis Cepatlah");

        txALAMAT.setText("Mana Alamatnya Cepat");

        txTELP.setText("Kirim Sekalian Sini");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        label1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label1.setText("Motorku");

        penggunaBtn.setBackground(new java.awt.Color(0, 153, 255));
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

        hapusBtn.setLabel("Hapus");
        hapusBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusBtnActionPerformed(evt);
            }
        });

        label2.setAlignment(java.awt.Label.CENTER);
        label2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label2.setText("Tabel Pengguna");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama", "No. Telepon", "Kewarganegaraan"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
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

        nameInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameInputActionPerformed(evt);
            }
        });

        kewarganegaraanSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kewarganegaraanSelectActionPerformed(evt);
            }
        });

        label3.setText("Kewarganegaraan");

        label4.setText("No. Telepon");

        label5.setText("Nama lengkap");

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

        sewamotorBtn1.setLabel("sewa motor");
        sewamotorBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sewamotorBtn1ActionPerformed(evt);
            }
        });

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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(phoneInput, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                    .addComponent(label5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nameInput, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(kewarganegaraanSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(buatBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(hapusBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
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
                        .addComponent(nameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kewarganegaraanSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(phoneInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    }//GEN-LAST:event_penggunaBtnActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        nameInput.setText( jTable1.getValueAt( jTable1.getSelectedRow(),0 ).toString() );
        phoneInput.setText( jTable1.getValueAt( jTable1.getSelectedRow(), 1).toString() );
        kewarganegaraanSelect.setSelectedItem(
            jTable1.getValueAt( jTable1.getSelectedRow(),2 ).toString()
        );
        
        this.currentIndex = jTable1.getSelectedRow();
        
//        jTable1.getSelectedRow()
        
//        txALAMAT.setText( jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString() );
//        txTELP.setText( jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString() );
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTable1AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1AncestorAdded

    private void kewarganegaraanSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kewarganegaraanSelectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kewarganegaraanSelectActionPerformed

    private void nameInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameInputActionPerformed

    private void buatBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buatBtnActionPerformed
        try {
            // TODO add your handling code here:
            this.StoreData();
            this.List_All();
            this.kosongkanform();

        } catch (SQLException ex) {
            Logger.getLogger(PenggunaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buatBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        try {
            // TODO add your handling code here:
            this.UpdateData();
            this.List_All();
            this.kosongkanform();
        } catch (SQLException ex) {
            Logger.getLogger(PenggunaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_updateBtnActionPerformed
    private void destroyData() throws SQLException{
        Connection cnn = buatkoneks();
        if(!cnn.isClosed()){
            PreparedStatement PS = cnn.prepareStatement("DELETE FROM pengguna WHERE id_pengguna=?;");
            PS.setInt(1, this.getCurrentId());
            PS.executeUpdate();
            cnn.close();
        }
    }
    private void hapusBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusBtnActionPerformed
       int jwb = JOptionPane.showOptionDialog(
            this,
            "Yakin akan menghapus data "+nameInput.getText()+"?",
            "Hapus Data",
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
            Logger.getLogger(PenggunaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_hapusBtnActionPerformed

    private void motorBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_motorBtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
        try {
            Sewamotor.motorFrame.mount();
        } catch (SQLException ex) {
            Logger.getLogger(PenggunaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        Sewamotor.motorFrame.setVisible(true);

    }//GEN-LAST:event_motorBtnActionPerformed

    private void sewamotorBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sewamotorBtn1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        try {
            Sewamotor.sewamotorFrame.mount();
        } catch (SQLException ex) {
            Logger.getLogger(PenggunaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        Sewamotor.sewamotorFrame.setVisible(true);
    }//GEN-LAST:event_sewamotorBtn1ActionPerformed

    
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
            java.util.logging.Logger.getLogger(PenggunaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PenggunaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PenggunaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PenggunaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PenggunaFrame().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(PenggunaFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button buatBtn;
    private java.awt.Button hapusBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> kewarganegaraanSelect;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Button motorBtn;
    private javax.swing.JTextField nameInput;
    private java.awt.Button penggunaBtn;
    private javax.swing.JTextField phoneInput;
    private java.awt.Button sewamotorBtn1;
    private javax.swing.JTextField txALAMAT;
    private javax.swing.JTextField txID;
    private javax.swing.JTextField txNAMA;
    private javax.swing.JTextField txTELP;
    private java.awt.Button updateBtn;
    // End of variables declaration//GEN-END:variables
}
