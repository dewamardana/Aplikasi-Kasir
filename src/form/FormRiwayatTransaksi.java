
package form;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import connection.koneksi;
import java.awt.Component;

//new
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class FormRiwayatTransaksi extends javax.swing.JPanel {

    DefaultTableModel table = new DefaultTableModel();
    
    public FormRiwayatTransaksi() {
        initComponents();
        setOpaque(false);
        koneksi conn = new koneksi();
        koneksi.getKoneksi();
        
        tb_riwayat.setModel(table);
        table.addColumn("Tanggal Transaksi");
        table.addColumn("ID Transaksi");
        table.addColumn("Kode Barang");
        table.addColumn("Nama Barang");
        table.addColumn("Harga");
        table.addColumn("Jumlah");
        table.addColumn("Total Harga");
        
        tampilData();
        
    }
    private void tampilData(){
        //untuk mengahapus baris setelah input
        int row = tb_riwayat.getRowCount();
        for(int a = 0 ; a < row ; a++){
            table.removeRow(0);
        }
        
        String query = "SELECT * FROM `transaksi` ";
        
        try{
            Connection connect = koneksi.getKoneksi();//memanggil koneksi
            Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(query);//menjalanakn query
            
            while (rslt.next()){
                //menampung data sementara
                   
                    String tanggal = rslt.getString("tgl_transaksi");
                    String id = rslt.getString("id_transaksi");
                    String kode = rslt.getString("kode_barang");
                    String nama = rslt.getString("nama_barang");
                    String harga = rslt.getString("harga");
                    String jumlah = rslt.getString("jumlah_barang");
                    String total = rslt.getString("total_harga");
                    
                //masukan semua data kedalam array
                String[] data = {tanggal,id,kode,nama,harga,jumlah,total};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                table.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                tb_riwayat.setModel(table);
            
        }catch(Exception e){
            System.out.println(e);
        }
    }private void cari(){
        int row = tb_riwayat.getRowCount();
        for(int a = 0 ; a < row ; a++){
            table.removeRow(0);
        }
        
        String cari = txt_search.getText();
        
        String query = "SELECT * FROM `transaksi` WHERE "
                + "`kode_barang`  LIKE '%"+cari+"%' OR "
                + "`tgl_transaksi` LIKE '%"+cari+"%' OR"
                + "`id_transaksi` LIKE '%"+cari+"%' OR"
                + "`nama_barang` LIKE '%"+cari+"%' ";
                
       try{
           Connection connect = koneksi.getKoneksi();//memanggil koneksi
           Statement sttmnt = connect.createStatement();//membuat statement
           ResultSet rslt = sttmnt.executeQuery(query);//menjalanakn query
           
           while (rslt.next()){
                //menampung data sementara
                   
                    String tanggal = rslt.getString("tgl_transaksi");
                    String id = rslt.getString("id_transaksi");
                    String kode = rslt.getString("kode_barang");
                    String nama = rslt.getString("nama_barang");
                    String harga = rslt.getString("harga");
                    String jumlah = rslt.getString("jumlah_barang");
                    String total = rslt.getString("total_harga");
                    
                //masukan semua data kedalam array
                String[] data = {tanggal,id,kode,nama,harga,jumlah,total};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                table.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                tb_riwayat.setModel(table);
    }catch(Exception e){
           System.out.println(e);
    }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder2 = new FormComponent.panelBorder();
        btnprint = new FormComponent.Button();
        btnrefresh = new FormComponent.Button();
        btnreset = new FormComponent.Button();
        btncari = new FormComponent.Button();
        txt_search = new FormComponent.SearchText();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_riwayat = new tabledark.TableDark();
        jLabel3 = new javax.swing.JLabel();

        panelBorder2.setBackground(new java.awt.Color(32, 32, 32));

        btnprint.setBackground(new java.awt.Color(32, 32, 32));
        btnprint.setForeground(new java.awt.Color(191, 191, 191));
        btnprint.setText("PRINT");
        btnprint.setBorderColor(new java.awt.Color(223, 96, 122));
        btnprint.setColor(new java.awt.Color(32, 32, 32));
        btnprint.setColorClick(new java.awt.Color(223, 96, 122));
        btnprint.setColorOver(new java.awt.Color(223, 144, 180));
        btnprint.setRadius(30);
        btnprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprintActionPerformed(evt);
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

        btnreset.setBackground(new java.awt.Color(32, 32, 32));
        btnreset.setForeground(new java.awt.Color(191, 191, 191));
        btnreset.setText("RESET");
        btnreset.setBorderColor(new java.awt.Color(223, 96, 122));
        btnreset.setColor(new java.awt.Color(32, 32, 32));
        btnreset.setColorClick(new java.awt.Color(223, 96, 122));
        btnreset.setColorOver(new java.awt.Color(223, 144, 180));
        btnreset.setRadius(30);
        btnreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetActionPerformed(evt);
            }
        });

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

        txt_search.setBackground(new java.awt.Color(32, 32, 32));
        txt_search.setForeground(new java.awt.Color(191, 191, 191));

        tb_riwayat.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tb_riwayat);

        jLabel3.setBackground(new java.awt.Color(32, 32, 32));
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(191, 191, 191));
        jLabel3.setText("DATA TRANSAKSI");

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addComponent(btnrefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnprint, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnreset, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelBorder2Layout.createSequentialGroup()
                                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btncari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 942, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelBorder2Layout.createSequentialGroup()
                                    .addGap(287, 287, 287)
                                    .addComponent(jLabel3))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(0, 40, Short.MAX_VALUE))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel3)
                .addGap(68, 68, 68)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btncari, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnreset, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnprint, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnrefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 60, Short.MAX_VALUE)
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        // CARI DATA
        cari();
    }//GEN-LAST:event_btncariActionPerformed

    private void btnresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetActionPerformed
        // RESET TABLE TRANSAKSI
        try{
            String clear = "TRUNCATE `transaksi`";
            Connection connect = koneksi.getKoneksi();
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(clear);
            ps.execute();

        }catch(Exception e){
            System.out.println(e);
        }finally{
            tampilData();
        }
    }//GEN-LAST:event_btnresetActionPerformed

    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
        // REFRESH TABLE
        tampilData();
    }//GEN-LAST:event_btnrefreshActionPerformed

    private void btnprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprintActionPerformed
        // PRINT DATA
        try{
           String file = "/report/report_transaksi.jasper";
           JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream(file),null,koneksi.getKoneksi());
           JasperViewer.viewReport(print, false);

        }catch(Exception e){
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_btnprintActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private FormComponent.Button btncari;
    private FormComponent.Button btnprint;
    private FormComponent.Button btnrefresh;
    private FormComponent.Button btnreset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private FormComponent.panelBorder panelBorder2;
    private tabledark.TableDark tb_riwayat;
    private FormComponent.SearchText txt_search;
    // End of variables declaration//GEN-END:variables
}
