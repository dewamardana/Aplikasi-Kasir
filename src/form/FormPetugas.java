
package form;
import connection.koneksi;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class FormPetugas extends javax.swing.JPanel {

 DefaultTableModel table = new DefaultTableModel();
    public FormPetugas() {
        initComponents();
        setOpaque(false);
       
        koneksi conn = new koneksi();
        koneksi.getKoneksi();
        
        table_user.setModel(table);
        table.addColumn("ID");
        table.addColumn("Nama Petugas");
        table.addColumn("Email");
        table.addColumn("Alamat");
        table.addColumn("Username");
        table.addColumn("Password");
        table.addColumn("Tanggal Pendaftaran");
        
        
        tampilData();
        
    }
    private void tampilData(){
        //untuk mengahapus baris setelah input
        int row = table_user.getRowCount();
        for(int a = 0 ; a < row ; a++){
            table.removeRow(0);
        }
        
        String query = "SELECT * FROM `tb_datapetugas` ";
        
        try{
            Connection connect = koneksi.getKoneksi();//memanggil koneksi
            Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(query);//menjalanakn query
            
            while (rslt.next()){
                //menampung data sementara
                   
                    String id= rslt.getString("id_petugas");
                    String nama = rslt.getString("nama_petugas");
                    String email = rslt.getString("email");
                    String alamat = rslt.getString("alamat");
                    String username = rslt.getString("username");
                    String password = rslt.getString("password");
                    String tanggal = rslt.getString("tanggal_pendaftaran");
                    
                //masukan semua data kedalam array
                String[] data = {id,nama,email,alamat,username,password,tanggal};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                table.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                table_user.setModel(table);
            
        }catch(Exception e){
            System.out.println(e);
        }
       
    }
    
   
    private void clear(){
//        txt_kodebarang.setText(null);
        txt_nama.setText("");
        txt_email.setText("");
        txt_alamat.setText("");
        txt_username.setText("");
        txt_password.setText("");
        tgl_daftar.setText("");
        
    }
    private void tambahData(){
//        String kode = txt_kodebarang.getText();
        Icon profile;
        String nama = txt_nama.getText();
        String email = txt_email.getText();
        String alamat = txt_alamat.getText();
        String username = txt_username.getText();
        String password = txt_password.getText();
        profile =  profile = new ImageIcon(getClass().getResource("/LoginIcon/user.png"));
        String tanggal = tgl_daftar.getText();
        
        //panggil koneksi
        Connection connect = koneksi.getKoneksi();
        //query untuk memasukan data
        String query = "INSERT INTO `tb_datapetugas` (`id_petugas`, `nama_petugas`, `email`,`alamat`, `username`, `password`,`Profile`, `tanggal_pendaftaran`) "
                     + "VALUES (NULL, '"+nama+"', '"+email+"','"+alamat+"', '"+username+"', '"+password+"','"+profile+"','"+tanggal+"')";
        
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
        int i = table_user.getSelectedRow();
        
        String id = table.getValueAt(i, 0).toString();
        
        Connection connect = koneksi.getKoneksi();
        
        String query = "DELETE FROM `tb_datapetugas` WHERE `tb_datapetugas`.`id_petugas` = "+id+" ";
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
        int i = table_user.getSelectedRow();
        Icon profile;
        String id = table.getValueAt(i, 0).toString();
        String nama = txt_nama.getText();
        String email = txt_email.getText();
        String alamat = txt_alamat.getText();
        String username = txt_username.getText();
        String password = txt_password.getText();
        profile = new ImageIcon(getClass().getResource("/LoginIcon/user.png"));
        String tanggal = tgl_daftar.getText();
        
        Connection connect = koneksi.getKoneksi();
        
        String query = "UPDATE `tb_datapetugas` SET `nama_petugas` = '"+nama+"', `email` = '"+email+"', `alamat` = '"+alamat+"', `tanggal_pendaftaran` = '"+tanggal+"', "
                + "`username` = '"+username+"', `password` = '"+password+"',`Profile` = '"+profile+"' "
                + "WHERE `tb_datapetugas`.`id_petugas` = '"+id+"';";

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        date = new com.raven.datechooser.DateChooser();
        panelBorder1 = new FormComponent.panelBorder();
        txt_nama = new FormComponent.textfield();
        txt_email = new FormComponent.textfield();
        txt_alamat = new FormComponent.textfield();
        txt_username = new FormComponent.textfield();
        txt_password = new FormComponent.textfield();
        tgl_daftar = new FormComponent.textfield();
        btnadd = new FormComponent.Button();
        btnedit = new FormComponent.Button();
        btnclear = new FormComponent.Button();
        btndelete = new FormComponent.Button();
        btntanggal = new FormComponent.Button();
        btnprint = new FormComponent.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_user = new tabledark.TableDark();
        jLabel1 = new javax.swing.JLabel();

        date.setDateFormat("yyyy-MM-dd");
        date.setTextRefernce(tgl_daftar);

        setPreferredSize(new java.awt.Dimension(1081, 633));

        panelBorder1.setBackground(new java.awt.Color(32, 32, 32));
        panelBorder1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_nama.setBackground(new java.awt.Color(32, 32, 32));
        txt_nama.setForeground(new java.awt.Color(191, 191, 191));
        txt_nama.setLabelText("Nama");
        panelBorder1.add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 320, -1));

        txt_email.setBackground(new java.awt.Color(32, 32, 32));
        txt_email.setForeground(new java.awt.Color(191, 191, 191));
        txt_email.setLabelText("Email");
        panelBorder1.add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 320, -1));

        txt_alamat.setBackground(new java.awt.Color(32, 32, 32));
        txt_alamat.setForeground(new java.awt.Color(191, 191, 191));
        txt_alamat.setLabelText("Alamat");
        panelBorder1.add(txt_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 320, -1));

        txt_username.setBackground(new java.awt.Color(32, 32, 32));
        txt_username.setForeground(new java.awt.Color(191, 191, 191));
        txt_username.setLabelText("Username");
        panelBorder1.add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 120, 310, -1));

        txt_password.setBackground(new java.awt.Color(32, 32, 32));
        txt_password.setForeground(new java.awt.Color(191, 191, 191));
        txt_password.setLabelText("Password");
        txt_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_passwordActionPerformed(evt);
            }
        });
        panelBorder1.add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 170, 310, -1));

        tgl_daftar.setBackground(new java.awt.Color(32, 32, 32));
        tgl_daftar.setForeground(new java.awt.Color(191, 191, 191));
        tgl_daftar.setLabelText("Tanggal");
        panelBorder1.add(tgl_daftar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 220, 260, -1));

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
        panelBorder1.add(btnadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 290, 88, 37));

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
        panelBorder1.add(btnedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 290, 88, 37));

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
        panelBorder1.add(btnclear, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 290, 88, 37));

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
        panelBorder1.add(btndelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(907, 560, 88, 37));

        btntanggal.setBackground(new java.awt.Color(32, 32, 32));
        btntanggal.setForeground(new java.awt.Color(191, 191, 191));
        btntanggal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/calendar.png"))); // NOI18N
        btntanggal.setBorderColor(new java.awt.Color(223, 96, 122));
        btntanggal.setColor(new java.awt.Color(32, 32, 32));
        btntanggal.setColorClick(new java.awt.Color(223, 96, 122));
        btntanggal.setColorOver(new java.awt.Color(223, 144, 180));
        btntanggal.setRadius(50);
        btntanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntanggalActionPerformed(evt);
            }
        });
        panelBorder1.add(btntanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 220, -1, 39));

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
        panelBorder1.add(btnprint, new org.netbeans.lib.awtextra.AbsoluteConstraints(783, 560, 88, 37));

        table_user.setModel(new javax.swing.table.DefaultTableModel(
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
        table_user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_userMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_user);

        panelBorder1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 333, 940, 189));

        jLabel1.setBackground(new java.awt.Color(32, 32, 32));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(191, 191, 191));
        jLabel1.setText("DATA PETUGAS");
        panelBorder1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 32, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 60, Short.MAX_VALUE)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 1021, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_passwordActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        // TAMBAH DATA
        tambahData();
    }//GEN-LAST:event_btnaddActionPerformed

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
        // RESET COLOM
        clear();
    }//GEN-LAST:event_btnclearActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // EDIT DATA
         editData();
    }//GEN-LAST:event_btneditActionPerformed

    private void btnprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprintActionPerformed
        // PRINT DATA
        try{
            String file = "/report/report_petugas.jasper";
            JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream(file),null,koneksi.getKoneksi());
            JasperViewer.viewReport(print, false);

        }catch(Exception e){
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_btnprintActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // HAPUS DATA
        hapusData();
    }//GEN-LAST:event_btndeleteActionPerformed

    private void table_userMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_userMouseClicked
        int baris = table_user.getSelectedRow();
        
        String nama = table.getValueAt(baris,1).toString();
        txt_nama.setText(nama);
        
        String email = table.getValueAt(baris, 2).toString();
        txt_email.setText(email);
        
        String alamat = table.getValueAt(baris, 3).toString();
        txt_alamat.setText(alamat);
        
        String username = table.getValueAt(baris, 4).toString();
        txt_username.setText(username);
        
        String password = table.getValueAt(baris, 5).toString();
        txt_password.setText(password);
        
        String tanggal = table.getValueAt(baris, 6).toString();
        tgl_daftar.setText(tanggal);

    }//GEN-LAST:event_table_userMouseClicked

    private void btntanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntanggalActionPerformed
        date.showPopup();
    }//GEN-LAST:event_btntanggalActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private FormComponent.Button btnadd;
    private FormComponent.Button btnclear;
    private FormComponent.Button btndelete;
    private FormComponent.Button btnedit;
    private FormComponent.Button btnprint;
    private FormComponent.Button btntanggal;
    private com.raven.datechooser.DateChooser date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private FormComponent.panelBorder panelBorder1;
    private tabledark.TableDark table_user;
    private FormComponent.textfield tgl_daftar;
    private FormComponent.textfield txt_alamat;
    private FormComponent.textfield txt_email;
    private FormComponent.textfield txt_nama;
    private FormComponent.textfield txt_password;
    private FormComponent.textfield txt_username;
    // End of variables declaration//GEN-END:variables
}
