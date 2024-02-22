
package formPetugas;
 
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
//NEW
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import connection.koneksi;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class FormPenjualan extends javax.swing.JFrame {

    DefaultTableModel table = new DefaultTableModel();
    
    public FormPenjualan() {
        initComponents();
        koneksi.getKoneksi();
        totalnya();
        
        
        tb_keranjang.setModel(table);
        table.addColumn("ID");
        table.addColumn("Nama Barang");
        table.addColumn("Harga");
        table.addColumn("Jumlah");
        table.addColumn("Total Harga");
        
        tampilData();
    }
     
     private void tampilData(){
        //untuk mengahapus baris setelah input
        int row = tb_keranjang.getRowCount();
        for(int a = 0 ; a < row ; a++){
            table.removeRow(0);
        }
        
        String query = "SELECT * FROM `tb_keranjang` ";
        String procedures = "CALL `total_harga_transaksi`()";
        
        try{
            Connection connect = koneksi.getKoneksi();//memanggil koneksi
            Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(query);//menjalanakn query
            
            while (rslt.next()){
                //menampung data sementara
                   
                    String kode = rslt.getString("id_transaksi");
                    String nama = rslt.getString("nama_barang");
                    String harga = rslt.getString("harga");
                    String jumlah = rslt.getString("jumlah");
                    String total = rslt.getString("total_harga");
                    
                //masukan semua data kedalam array
                String[] data = {kode,nama,harga,jumlah,total};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                table.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                tb_keranjang.setModel(table);
            
        }catch(Exception e){
            System.out.println(e);
        }
       
    }
    private void clear(){
        txt_kodebarang2.setText("");
        txt_namabarang2.setText("");
        txt_harga2.setText("");
        txt_jumlah2.setText("");
        txt_totalharga.setText("");
        txt_satuan.setText("");
    }
    private void keranjang(){
        String kode = txt_kodebarang2.getText();
        String nama = txt_namabarang2.getText();
        String harga = txt_harga2.getText();
        String jumlah = txt_jumlah2.getText();
        String total = txt_totalharga.getText();
        String tanggal = tgl_transaksi.getText();
        
        
        //panggil koneksi
        Connection connect = koneksi.getKoneksi();
        //query untuk memasukan data
        String query = "INSERT INTO `transaksi` (`tgl_transaksi`, `id_transaksi`, `kode_barang`, `nama_barang`, `harga`, `jumlah_barang`, `total_harga`) "
                + "VALUES ('"+tanggal+"', NULL, '"+kode+"', '"+nama+"', '"+harga+"', '"+jumlah+"', '"+total+"')";
        
        try{
            //menyiapkan statement untuk di eksekusi
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(query);
            ps.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Data Masuk Ke-Keranjang");
            
        }catch(SQLException | HeadlessException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Data Gagal Disimpan");
            
        }finally{
            tampilData();
            clear();
            
        }
        totalnya();
    }
    private void hapusData(){
        //ambill data no pendaftaran
        int i = tb_keranjang.getSelectedRow();
        
        String kode = table.getValueAt(i, 0).toString();
        
        Connection connect = koneksi.getKoneksi();
        
        String query = "DELETE FROM `tb_keranjang` WHERE `tb_keranjang`.`id_transaksi` = '"+kode+"' ";
        try{
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(query);
            ps.execute();
        }catch(SQLException | HeadlessException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus");
        }finally{
            tampilData();
            clear();
        }
        totalnya();
    }
    private void totalnya(){
        String procedures = "CALL `total_harga_transaksi`()";
        
        try{
            Connection connect = koneksi.getKoneksi();//memanggil koneksi
            Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(procedures);//menjalanakn query\
                while(rslt.next()){
                    txt_totalharga2.setText(rslt.getString(1));
                }
                
        }catch(Exception e){
            System.out.println(e);
        }
        
        
    }
    private void total(){
        String harga = txt_harga2.getText();
        String jumlah = txt_jumlah2.getText();
        
        int hargaa = Integer.parseInt(harga);
        try{
        int jumlahh = Integer.parseInt(jumlah);
        
        int total = hargaa * jumlahh;
        String total_harga = Integer.toString(total);
        
        txt_totalharga.setText(total_harga);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Only Number");
            txt_jumlah2.setText("");
        }
    }
    private void reset(){
        txt_uang.setText("");
    }
    private void kembalian(){
        String total = txt_totalharga2.getText();
        String uang = txt_uang.getText();
        
        int totals = Integer.parseInt(total);
        try{
            int uangs = Integer.parseInt(uang);     
            int kembali = (uangs - totals);
            String fix = Integer.toString(kembali);
            txt_kembalian.setText(fix);
            JOptionPane.showMessageDialog(null, "Transaksi Berhasil!");
        }catch(NumberFormatException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Invalid Payment");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        date = new com.raven.datechooser.DateChooser();
        panelBorder1 = new FormComponent.panelBorder();
        jLabel7 = new javax.swing.JLabel();
        txt_namabarang2 = new FormComponent.textfield();
        txt_harga2 = new FormComponent.textfield();
        txt_jumlah2 = new FormComponent.textfield();
        txt_totalharga = new FormComponent.textfield();
        txt_uang = new FormComponent.textfield();
        txt_kodebarang2 = new FormComponent.SearchText();
        txt_kembalian = new FormComponent.textfield();
        txt_totalharga2 = new FormComponent.textfield();
        btnsearch = new FormComponent.Button();
        btnadd = new FormComponent.Button();
        btndelete = new FormComponent.Button();
        btnbayar = new FormComponent.Button();
        btnreset = new FormComponent.Button();
        btnprint = new FormComponent.Button();
        btnback = new FormComponent.Button();
        tgl_transaksi = new FormComponent.textfield();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_keranjang = new tabledark.TableDark();
        txt_satuan = new FormComponent.textfield();

        date.setDateFormat("yyyy-MM-dd");
        date.setTextRefernce(tgl_transaksi);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelBorder1.setBackground(new java.awt.Color(32, 32, 32));

        jLabel7.setBackground(new java.awt.Color(32, 32, 32));
        jLabel7.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(191, 191, 191));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("KEMBALIAN");

        txt_namabarang2.setBackground(new java.awt.Color(32, 32, 32));
        txt_namabarang2.setForeground(new java.awt.Color(191, 191, 191));
        txt_namabarang2.setLabelText("Nama Barang");
        txt_namabarang2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namabarang2ActionPerformed(evt);
            }
        });

        txt_harga2.setBackground(new java.awt.Color(32, 32, 32));
        txt_harga2.setForeground(new java.awt.Color(191, 191, 191));
        txt_harga2.setLabelText("Harga");

        txt_jumlah2.setBackground(new java.awt.Color(32, 32, 32));
        txt_jumlah2.setForeground(new java.awt.Color(191, 191, 191));
        txt_jumlah2.setLabelText("Jumlah");
        txt_jumlah2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_jumlah2KeyReleased(evt);
            }
        });

        txt_totalharga.setBackground(new java.awt.Color(32, 32, 32));
        txt_totalharga.setForeground(new java.awt.Color(191, 191, 191));
        txt_totalharga.setLabelText("Total Harga");
        txt_totalharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalhargaActionPerformed(evt);
            }
        });

        txt_uang.setBackground(new java.awt.Color(32, 32, 32));
        txt_uang.setForeground(new java.awt.Color(191, 191, 191));
        txt_uang.setLabelText("Jumlah Uang");

        txt_kodebarang2.setBackground(new java.awt.Color(32, 32, 32));
        txt_kodebarang2.setForeground(new java.awt.Color(191, 191, 191));
        txt_kodebarang2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kodebarang2ActionPerformed(evt);
            }
        });

        txt_kembalian.setBackground(new java.awt.Color(32, 32, 32));
        txt_kembalian.setForeground(new java.awt.Color(191, 191, 191));
        txt_kembalian.setLabelText("Kembalian");

        txt_totalharga2.setBackground(new java.awt.Color(32, 32, 32));
        txt_totalharga2.setForeground(new java.awt.Color(191, 191, 191));
        txt_totalharga2.setLabelText("Jumlah Bayar");
        txt_totalharga2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalharga2ActionPerformed(evt);
            }
        });

        btnsearch.setBackground(new java.awt.Color(32, 32, 32));
        btnsearch.setForeground(new java.awt.Color(191, 191, 191));
        btnsearch.setText("CARI DATA");
        btnsearch.setBorderColor(new java.awt.Color(223, 96, 122));
        btnsearch.setColor(new java.awt.Color(32, 32, 32));
        btnsearch.setColorClick(new java.awt.Color(223, 96, 122));
        btnsearch.setColorOver(new java.awt.Color(223, 144, 180));
        btnsearch.setRadius(10);
        btnsearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsearchMouseClicked(evt);
            }
        });
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

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

        btndelete.setBackground(new java.awt.Color(32, 32, 32));
        btndelete.setForeground(new java.awt.Color(191, 191, 191));
        btndelete.setText("DELETE");
        btndelete.setBorderColor(new java.awt.Color(223, 96, 122));
        btndelete.setColor(new java.awt.Color(32, 32, 32));
        btndelete.setColorClick(new java.awt.Color(223, 96, 122));
        btndelete.setColorOver(new java.awt.Color(223, 144, 180));
        btndelete.setRadius(30);
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        btnbayar.setBackground(new java.awt.Color(32, 32, 32));
        btnbayar.setForeground(new java.awt.Color(191, 191, 191));
        btnbayar.setText("BAYAR");
        btnbayar.setBorderColor(new java.awt.Color(223, 96, 122));
        btnbayar.setColor(new java.awt.Color(32, 32, 32));
        btnbayar.setColorClick(new java.awt.Color(223, 96, 122));
        btnbayar.setColorOver(new java.awt.Color(223, 144, 180));
        btnbayar.setRadius(30);
        btnbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbayarActionPerformed(evt);
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

        tgl_transaksi.setBackground(new java.awt.Color(32, 32, 32));
        tgl_transaksi.setForeground(new java.awt.Color(191, 191, 191));
        tgl_transaksi.setLabelText("Tanggal Transaksi");

        tb_keranjang.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tb_keranjang);

        txt_satuan.setBackground(new java.awt.Color(32, 32, 32));
        txt_satuan.setForeground(new java.awt.Color(191, 191, 191));
        txt_satuan.setLabelText("Satuan");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tgl_transaksi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                            .addComponent(txt_totalharga, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_jumlah2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_harga2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_totalharga2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnreset, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnbayar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                    .addComponent(txt_uang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(100, 100, 100)
                                .addComponent(txt_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(txt_namabarang2, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(btnsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_kodebarang2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                                .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnprint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(0, 243, Short.MAX_VALUE)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(panelBorder1Layout.createSequentialGroup()
                                        .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(652, 652, 652))
                                    .addGroup(panelBorder1Layout.createSequentialGroup()
                                        .addGap(63, 63, 63)
                                        .addComponent(txt_satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(87, 87, 87)))
                .addGap(10, 10, 10))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_kodebarang2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsearch, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(txt_namabarang2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(txt_harga2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_jumlah2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_satuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addComponent(txt_totalharga, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(tgl_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnprint, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(83, Short.MAX_VALUE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(btnreset, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_totalharga2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_uang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addComponent(btnbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_jumlah2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jumlah2KeyReleased
        total();
    }//GEN-LAST:event_txt_jumlah2KeyReleased

    private void btnsearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsearchMouseClicked
        // MEMBUKA STOK BARANG
        new stok_barang().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnsearchMouseClicked

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        // MENAMBAH DATA KE KERANJANG
        keranjang();
    }//GEN-LAST:event_btnaddActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // MENGHAPUS TABLE KERANJANG
        hapusData();
        txt_uang.setText("");
        txt_kembalian.setText("");
    }//GEN-LAST:event_btndeleteActionPerformed

    private void btnbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbayarActionPerformed
        // MELAKUKAN PEMBAYARAN DAN MEMBERIKAN KEMBALAN
        kembalian();
    }//GEN-LAST:event_btnbayarActionPerformed

    private void btnresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetActionPerformed
        // MENGHAPUS KERANJANG
        
        try{
            String clear = "TRUNCATE `tb_keranjang`";
            Connection connect = koneksi.getKoneksi();
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(clear);
            ps.execute();
            //            keranjang();

        }catch(Exception e){
            System.out.println(e);
        }finally{
            tampilData();
            totalnya();
            txt_uang.setText("");
            txt_kembalian.setText("");
        }
    }//GEN-LAST:event_btnresetActionPerformed

    private void btnprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprintActionPerformed
        // PRINT DATA BELANJAAN
        try{
            String file = "/struk/struk.jasper";

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            HashMap param = new HashMap();

            param.put("total",txt_totalharga2.getText());
            param.put("uang",txt_uang.getText());
            param.put("kembalian",txt_kembalian.getText());

            JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream(file),param,koneksi.getKoneksi());
            JasperViewer.viewReport(print, false);

        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException | JRException e){
            System.out.println(e);
        }
    }//GEN-LAST:event_btnprintActionPerformed

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        // MEMBUKA FORM MENU PETUGAS
        new petugasMenu().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnbackActionPerformed

    private void txt_totalhargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalhargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalhargaActionPerformed

    private void txt_totalharga2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalharga2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalharga2ActionPerformed

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsearchActionPerformed

    private void txt_kodebarang2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kodebarang2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kodebarang2ActionPerformed

    private void txt_namabarang2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namabarang2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namabarang2ActionPerformed

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
            java.util.logging.Logger.getLogger(FormPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPenjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private FormComponent.Button btnadd;
    private FormComponent.Button btnback;
    private FormComponent.Button btnbayar;
    private FormComponent.Button btndelete;
    private FormComponent.Button btnprint;
    private FormComponent.Button btnreset;
    private FormComponent.Button btnsearch;
    private com.raven.datechooser.DateChooser date;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private FormComponent.panelBorder panelBorder1;
    private tabledark.TableDark tb_keranjang;
    private FormComponent.textfield tgl_transaksi;
    public FormComponent.textfield txt_harga2;
    public FormComponent.textfield txt_jumlah2;
    private FormComponent.textfield txt_kembalian;
    public FormComponent.SearchText txt_kodebarang2;
    public FormComponent.textfield txt_namabarang2;
    public FormComponent.textfield txt_satuan;
    private FormComponent.textfield txt_totalharga;
    private FormComponent.textfield txt_totalharga2;
    private FormComponent.textfield txt_uang;
    // End of variables declaration//GEN-END:variables
}
