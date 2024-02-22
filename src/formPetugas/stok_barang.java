
package formPetugas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import connection.koneksi;
public class stok_barang extends javax.swing.JFrame {

     DefaultTableModel table = new DefaultTableModel();
    
    public stok_barang() {
        initComponents();
        koneksi conn = new koneksi();
        koneksi.getKoneksi();
        
        table_barang.setModel(table);
        table.addColumn("Kode Barang");
        table.addColumn("Nama Barang");
        table.addColumn("Harga");
        table.addColumn("Stok");
        
        tampilData();
            
    }
    private void tampilData(){
        //untuk mengahapus baris setelah input
        int row = table_barang.getRowCount();
        for(int a = 0 ; a < row ; a++){
            table.removeRow(0);
        }
        
        String query = "SELECT * FROM `tb_databarang` ";
        
        try{
            Connection connect = koneksi.getKoneksi();//memanggil koneksi
            Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(query);//menjalanakn query
            
            while (rslt.next()){
                //menampung data sementara
                   
                    String kode = rslt.getString("kode_barang");
                    String nama = rslt.getString("nama_barang");
                    String harga = rslt.getString("harga");
                    String stok = rslt.getString("stok");;
                    
                //masukan semua data kedalam array
                String[] data = {kode,nama,harga,stok};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                table.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                table_barang.setModel(table);
            
        }catch(Exception e){
            System.out.println(e);
        }
       
    }
    private void cari(){
        int row = table_barang.getRowCount();
        for(int a = 0 ; a < row ; a++){
            table.removeRow(0);
        }
        
        String cari = txt_cari.getText();
        
        String query = "SELECT * FROM `tb_databarang` WHERE `kode_barang`  LIKE '%"+cari+"%' OR `nama_barang` LIKE '%"+cari+"%' ";
                
       try{
           Connection connect = koneksi.getKoneksi();//memanggil koneksi
           Statement sttmnt = connect.createStatement();//membuat statement
           ResultSet rslt = sttmnt.executeQuery(query);//menjalanakn query
           
           while (rslt.next()){
                //menampung data sementara
                   
                    String kode = rslt.getString("kode_barang");
                    String nama = rslt.getString("nama_barang");
                    String harga = rslt.getString("harga");
                    String stok = rslt.getString("stok");
                    
                //masukan semua data kedalam array
                String[] data = {kode,nama,harga,stok};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                table.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                table_barang.setModel(table);
           
        
    }catch(Exception e){
           System.out.println(e);
    }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new FormComponent.panelBorder();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_barang = new tabledark.TableDark();
        txt_cari = new FormComponent.SearchText();
        btncari = new FormComponent.Button();
        btnrefresh = new FormComponent.Button();
        btnback = new FormComponent.Button();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelBorder1.setBackground(new java.awt.Color(32, 32, 32));
        panelBorder1.setForeground(new java.awt.Color(191, 191, 191));

        table_barang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_barangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_barang);

        txt_cari.setBackground(new java.awt.Color(32, 32, 32));
        txt_cari.setForeground(new java.awt.Color(191, 191, 191));

        btncari.setBackground(new java.awt.Color(32, 32, 32));
        btncari.setForeground(new java.awt.Color(191, 191, 191));
        btncari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        btncari.setBorderColor(new java.awt.Color(223, 96, 122));
        btncari.setColor(new java.awt.Color(32, 32, 32));
        btncari.setColorClick(new java.awt.Color(223, 96, 122));
        btncari.setColorOver(new java.awt.Color(223, 144, 180));
        btncari.setRadius(10);
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });

        btnrefresh.setBackground(new java.awt.Color(32, 32, 32));
        btnrefresh.setForeground(new java.awt.Color(191, 191, 191));
        btnrefresh.setText("REFRESH");
        btnrefresh.setBorderColor(new java.awt.Color(223, 96, 122));
        btnrefresh.setColor(new java.awt.Color(32, 32, 32));
        btnrefresh.setColorClick(new java.awt.Color(223, 96, 122));
        btnrefresh.setColorOver(new java.awt.Color(223, 144, 180));
        btnrefresh.setRadius(30);
        btnrefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefreshActionPerformed(evt);
            }
        });

        btnback.setBackground(new java.awt.Color(32, 32, 32));
        btnback.setForeground(new java.awt.Color(191, 191, 191));
        btnback.setText("KEMBALI");
        btnback.setBorderColor(new java.awt.Color(223, 96, 122));
        btnback.setColor(new java.awt.Color(32, 32, 32));
        btnback.setColorClick(new java.awt.Color(223, 96, 122));
        btnback.setColorOver(new java.awt.Color(223, 144, 180));
        btnback.setRadius(30);
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(32, 32, 32));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(191, 191, 191));
        jLabel1.setText("DATA BARANG");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                                .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btncari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnrefresh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(205, 205, 205)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btncari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_cari, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnrefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void table_barangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_barangMouseClicked
        // MEMASUKAN DATA YANG DIPILIH KE TRANSAKSI
        int row = table_barang.getSelectedRow();
        FormPenjualan menu = new FormPenjualan();

        String kode = table.getValueAt(row, 0).toString();
        menu.txt_kodebarang2.setText(kode);

        String nama = table.getValueAt(row, 1).toString();
        menu.txt_namabarang2.setText(nama);

        String harga = table.getValueAt(row, 2).toString();
        menu.txt_harga2.setText(harga);
        
        menu.setVisible(true);
        menu.pack();
        menu.setDefaultCloseOperation(FormPenjualan.DISPOSE_ON_CLOSE);
        dispose();
    }//GEN-LAST:event_table_barangMouseClicked

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        // CARI DATA
         cari();
    }//GEN-LAST:event_btncariActionPerformed

    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
        // REFRESH TABLE
        tampilData();
    }//GEN-LAST:event_btnrefreshActionPerformed

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        // KEMBALI KE FORM PENJUALAN
        new FormPenjualan().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnbackActionPerformed

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
            java.util.logging.Logger.getLogger(stok_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(stok_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(stok_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(stok_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new stok_barang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private FormComponent.Button btnback;
    private FormComponent.Button btncari;
    private FormComponent.Button btnrefresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private FormComponent.panelBorder panelBorder1;
    private tabledark.TableDark table_barang;
    private FormComponent.SearchText txt_cari;
    // End of variables declaration//GEN-END:variables
}
