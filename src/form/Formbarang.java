package form;

import com.toedter.calendar.JDateChooser;
import connection.DatabaseConnection;
import connection.koneksi;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

//new
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
public class Formbarang extends javax.swing.JPanel {

    DefaultTableModel table = new DefaultTableModel();
 
    public Formbarang() {
        initComponents();
        setOpaque(false);
        
        
        koneksi conn = new koneksi();
        koneksi.getKoneksi();
        
        table_barang1.setModel(table);
        table.addColumn("Kode Barang");
        table.addColumn("Nama Barang");
        table.addColumn("Harga");
        table.addColumn("Stok");
        table.addColumn("Tanggal Masuk");
            
        tampilData();
        
    }
private void tampilData(){
        //untuk mengahapus baris setelah input
        int row = table_barang1.getRowCount();
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
                    String stok = rslt.getString("stok");
                    String tanggal = rslt.getString("tanggal");
                    
                //masukan semua data kedalam array
                String[] data = {kode,nama,harga,stok,tanggal};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                table.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                table_barang1.setModel(table);
            
        }catch(Exception e){
            System.out.println(e);
        }
       
    }
    private void clear(){
//        txt_kodebarang.setText(null);
        txt_namabarang.setText("");
        txt_harga.setText("");
        txt_stok.setText("");
        txt_tanggal.setText("");
        
    }
    private void tambahData(){
//        String kode = txt_kodebarang.getText();
        String nama = txt_namabarang.getText(); 
        String harga = txt_harga.getText();
        String stok = txt_stok.getText();
        String tanggal = txt_tanggal.getText();
        
        //panggil koneksi
        Connection connect = koneksi.getKoneksi();
        //query untuk memasukan data
        String query = "INSERT INTO `tb_databarang` (kode_barang, `nama_barang`, `harga`,`satuan`, `tanggal`) "
                     + "VALUES (NULL, '"+nama+"', '"+harga+"', '"+stok+"', '"+tanggal+"')";
        
        try{
            //menyiapkan statement untuk di eksekusi
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(query);
            ps.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Data Berhasil Disimpan");
            
        }catch(SQLException | HeadlessException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Data Gagal Disimpan");
            
        }finally{
            tampilData();
            clear();
            
        }
    }
    
    private void hapusData(){
        //ambill data no pendaftaran
        int i = table_barang1.getSelectedRow();
        
        String kode = table.getValueAt(i, 0).toString();
        
        Connection connect = koneksi.getKoneksi();
        
        String query = "DELETE FROM `tb_databarang` WHERE `tb_databarang`.`kode_barang` = "+kode+" ";
        try{
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(query);
            ps.execute();
            JOptionPane.showMessageDialog(null , "Data Berhasil Dihapus");
        }catch(SQLException | HeadlessException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus");
        }finally{
            tampilData();
            clear();
        }
        
    }
    private void editData(){
        int i = table_barang1.getSelectedRow();
        
        String kode = table.getValueAt(i, 0).toString();
        String nama = txt_namabarang.getText();
        String harga = txt_harga.getText();
        String stok = txt_stok.getText();
        String tanggal = txt_tanggal.getText();
        
        Connection connect = koneksi.getKoneksi();
        
        String query = "UPDATE `tb_databarang` SET `nama_barang` = '"+nama+"', `harga` = '"+harga+"', `stok` = '"+stok+"', `tanggal` = '"+tanggal+"' "
                + "WHERE `tb_databarang`.`kode_barang` = '"+kode+"';";

        try{
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(query);
            ps.executeUpdate(query);
            JOptionPane.showMessageDialog(null , "Data Update");
        }catch(SQLException | HeadlessException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Gagal Update");
        }finally{
            tampilData();
            clear();
        }
    }
    private void cari(){
        int row = table_barang1.getRowCount();
        for(int a = 0 ; a < row ; a++){
            table.removeRow(0);
        }
        
        String cari = txt_search.getText();
        
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
                    String satuan = rslt.getString("satuan");
                    String tanggal = rslt.getString("tanggal");
                    
                //masukan semua data kedalam array
                String[] data = {kode,nama,harga,stok,satuan,tanggal};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                table.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                table_barang1.setModel(table);
           
        
    }catch(Exception e){
           System.out.println(e);
    }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        date = new com.raven.datechooser.DateChooser();
        panelBorder1 = new FormComponent.panelBorder();
        txt_namabarang = new FormComponent.textfield();
        txt_harga = new FormComponent.textfield();
        txt_stok = new FormComponent.textfield();
        txt_tanggal = new FormComponent.textfield();
        btnadd = new FormComponent.Button();
        btnclear = new FormComponent.Button();
        btnedit = new FormComponent.Button();
        btnrefresh = new FormComponent.Button();
        btnhapus = new FormComponent.Button();
        btnprint = new FormComponent.Button();
        txt_search = new FormComponent.SearchText();
        btnsearch = new FormComponent.Button();
        button2 = new FormComponent.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_barang1 = new tabledark.TableDark();
        jLabel1 = new javax.swing.JLabel();

        date.setDateFormat("yyyy-MM-dd");
        date.setTextRefernce(txt_tanggal);

        panelBorder1.setBackground(new java.awt.Color(32, 32, 32));

        txt_namabarang.setBackground(new java.awt.Color(32, 32, 32));
        txt_namabarang.setForeground(new java.awt.Color(191, 191, 191));
        txt_namabarang.setLabelText("Nama Barang");

        txt_harga.setBackground(new java.awt.Color(32, 32, 32));
        txt_harga.setForeground(new java.awt.Color(191, 191, 191));
        txt_harga.setLabelText("Harga");

        txt_stok.setBackground(new java.awt.Color(32, 32, 32));
        txt_stok.setForeground(new java.awt.Color(191, 191, 191));
        txt_stok.setLabelText("Stok");
        txt_stok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_stokActionPerformed(evt);
            }
        });

        txt_tanggal.setBackground(new java.awt.Color(32, 32, 32));
        txt_tanggal.setForeground(new java.awt.Color(191, 191, 191));
        txt_tanggal.setLabelText("Tanggal");

        btnadd.setBackground(new java.awt.Color(32, 32, 32));
        btnadd.setForeground(new java.awt.Color(191, 191, 191));
        btnadd.setText("ADD");
        btnadd.setBorderColor(new java.awt.Color(223, 96, 122));
        btnadd.setColor(new java.awt.Color(32, 32, 32));
        btnadd.setColorClick(new java.awt.Color(223, 96, 122));
        btnadd.setColorOver(new java.awt.Color(223, 144, 180));
        btnadd.setRadius(30);
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        btnclear.setBackground(new java.awt.Color(32, 32, 32));
        btnclear.setForeground(new java.awt.Color(191, 191, 191));
        btnclear.setText("CLEAR");
        btnclear.setBorderColor(new java.awt.Color(223, 96, 122));
        btnclear.setColor(new java.awt.Color(32, 32, 32));
        btnclear.setColorClick(new java.awt.Color(223, 96, 122));
        btnclear.setColorOver(new java.awt.Color(223, 144, 180));
        btnclear.setRadius(30);
        btnclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclearActionPerformed(evt);
            }
        });

        btnedit.setBackground(new java.awt.Color(32, 32, 32));
        btnedit.setForeground(new java.awt.Color(191, 191, 191));
        btnedit.setText("EDIT");
        btnedit.setBorderColor(new java.awt.Color(223, 96, 122));
        btnedit.setColor(new java.awt.Color(32, 32, 32));
        btnedit.setColorClick(new java.awt.Color(223, 96, 122));
        btnedit.setColorOver(new java.awt.Color(223, 144, 180));
        btnedit.setRadius(30);
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
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

        btnhapus.setBackground(new java.awt.Color(32, 32, 32));
        btnhapus.setForeground(new java.awt.Color(191, 191, 191));
        btnhapus.setText("DELETE");
        btnhapus.setBorderColor(new java.awt.Color(223, 96, 122));
        btnhapus.setColor(new java.awt.Color(32, 32, 32));
        btnhapus.setColorClick(new java.awt.Color(223, 96, 122));
        btnhapus.setColorOver(new java.awt.Color(223, 144, 180));
        btnhapus.setRadius(30);
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });

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

        txt_search.setBackground(new java.awt.Color(32, 32, 32));
        txt_search.setForeground(new java.awt.Color(191, 191, 191));

        btnsearch.setBackground(new java.awt.Color(32, 32, 32));
        btnsearch.setForeground(new java.awt.Color(191, 191, 191));
        btnsearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        btnsearch.setBorderColor(new java.awt.Color(223, 96, 122));
        btnsearch.setColor(new java.awt.Color(32, 32, 32));
        btnsearch.setColorClick(new java.awt.Color(223, 96, 122));
        btnsearch.setColorOver(new java.awt.Color(223, 144, 180));
        btnsearch.setRadius(10);
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

        button2.setBackground(new java.awt.Color(32, 32, 32));
        button2.setForeground(new java.awt.Color(191, 191, 191));
        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/calendar.png"))); // NOI18N
        button2.setBorderColor(new java.awt.Color(223, 96, 122));
        button2.setColor(new java.awt.Color(32, 32, 32));
        button2.setColorClick(new java.awt.Color(223, 96, 122));
        button2.setColorOver(new java.awt.Color(223, 144, 180));
        button2.setRadius(50);
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        table_barang1.setModel(new javax.swing.table.DefaultTableModel(
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
        table_barang1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_barang1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_barang1);

        jLabel1.setBackground(new java.awt.Color(32, 32, 32));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(191, 191, 191));
        jLabel1.setText("DATA BARANG");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(btnclear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(btnedit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 254, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(16, 16, 16)))
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_search, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addComponent(txt_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(btnrefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(btnprint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_stok, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                            .addComponent(txt_namabarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnsearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_search, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                    .addComponent(jLabel1))
                .addGap(48, 48, 48)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_namabarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_stok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnclear, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnedit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnprint, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnrefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 60, Short.MAX_VALUE)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        //TAMBAH DATA
        tambahData();
    }//GEN-LAST:event_btnaddActionPerformed

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
        // MENGHAPUS COLOM
        clear();
    }//GEN-LAST:event_btnclearActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // EDIT DATA
        editData();
    }//GEN-LAST:event_btneditActionPerformed

    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
        // REFRESH DATA
        tampilData();
    }//GEN-LAST:event_btnrefreshActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // HAPUS DATA
        hapusData();
    }//GEN-LAST:event_btnhapusActionPerformed

    private void btnprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprintActionPerformed
        // PRINT DATA
        try{
            String file = "/report/report_barang.jasper";
            JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream(file),null,koneksi.getKoneksi());
            JasperViewer.viewReport(print, false);

        }catch(Exception e){
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_btnprintActionPerformed

    private void txt_stokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_stokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_stokActionPerformed

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        // CARI DATA
        cari();
    }//GEN-LAST:event_btnsearchActionPerformed

    private void table_barang1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_barang1MouseClicked
        // KLIK TABLE BARANG
        int baris = table_barang1.getSelectedRow();

        String nama = table.getValueAt(baris,1).toString();
        txt_namabarang.setText(nama);

        String harga = table.getValueAt(baris, 2).toString();
        txt_harga.setText(harga);

        String stok = table.getValueAt(baris, 3).toString();
        txt_stok.setText(stok);
        
        String tanggal = table.getValueAt(baris, 4).toString();
        txt_tanggal.setText(tanggal);
    }//GEN-LAST:event_table_barang1MouseClicked

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        date.showPopup();
    }//GEN-LAST:event_button2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private FormComponent.Button btnadd;
    private FormComponent.Button btnclear;
    private FormComponent.Button btnedit;
    private FormComponent.Button btnhapus;
    private FormComponent.Button btnprint;
    private FormComponent.Button btnrefresh;
    private FormComponent.Button btnsearch;
    private FormComponent.Button button2;
    private com.raven.datechooser.DateChooser date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private FormComponent.panelBorder panelBorder1;
    private tabledark.TableDark table_barang1;
    private FormComponent.textfield txt_harga;
    private FormComponent.textfield txt_namabarang;
    private FormComponent.SearchText txt_search;
    private FormComponent.textfield txt_stok;
    private FormComponent.textfield txt_tanggal;
    // End of variables declaration//GEN-END:variables
}
