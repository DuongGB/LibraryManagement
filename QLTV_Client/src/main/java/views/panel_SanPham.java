package views;

import entity.SanPham;
import java.text.NumberFormat;
import java.util.Locale;


public class panel_SanPham extends javax.swing.JPanel {

    /**
     * Creates new form panel_SanPham
     * @param sp
     */
    public panel_SanPham(SanPham sp) {
        initComponents();
        Locale locale = new Locale("vi", "VN");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        lbl_giaBan.setText("Giá bán: " + format.format(sp.getGiaMua()));
        
        lbl_image.setIcon(new javax.swing.ImageIcon(sp.getHinhAnh()));
        lbl_tenSP.setText("Tên sản phẩm: " + sp.getTenSanPham());
        lbl_tenTacGia.setText("Tác giả: " + sp.getTacGia().getTenTacGia());
        lbl_tenNXB.setText("Nhà xuất bản: " + sp.getNhaXuatBan().getTenNhaXuatBan());
        lbl_tenTheLoai.setText("Thể loại: " + sp.getTheLoai().getTenTheLoai());
        lbl_slTon.setText("Số lượng: " + Integer.toString(sp.getSoLuongTon()));
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_SanPham = new javax.swing.JPanel();
        lbl_image = new javax.swing.JLabel();
        lbl_tenSP = new javax.swing.JLabel();
        lbl_tenTacGia = new javax.swing.JLabel();
        lbl_tenNXB = new javax.swing.JLabel();
        lbl_tenTheLoai = new javax.swing.JLabel();
        lbl_slTon = new javax.swing.JLabel();
        lbl_giaBan = new javax.swing.JLabel();

        setBackground(new java.awt.Color(245, 245, 245));

        pnl_SanPham.setBackground(new java.awt.Color(245, 245, 245));
        pnl_SanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/product1.jpg"))); // NOI18N
        lbl_image.setText("jLabel6");

        lbl_tenSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_tenSP.setText("Conan cậu bé đến từ tương lai");

        lbl_tenTacGia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_tenTacGia.setText("Tác giả: Ngọc Thắng");

        lbl_tenNXB.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_tenNXB.setText("NXB: Kim Đồng");

        lbl_tenTheLoai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_tenTheLoai.setText("Thể loại: Hoạt hình");

        lbl_slTon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_slTon.setText("Số lượng tồn: 100");

        lbl_giaBan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_giaBan.setText("Giá bán: 100.000 VND");

        javax.swing.GroupLayout pnl_SanPhamLayout = new javax.swing.GroupLayout(pnl_SanPham);
        pnl_SanPham.setLayout(pnl_SanPhamLayout);
        pnl_SanPhamLayout.setHorizontalGroup(
            pnl_SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_SanPhamLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(pnl_SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_SanPhamLayout.createSequentialGroup()
                        .addGroup(pnl_SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_tenTacGia)
                            .addComponent(lbl_tenSP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 379, Short.MAX_VALUE)
                        .addGroup(pnl_SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_slTon)
                            .addComponent(lbl_giaBan))
                        .addGap(28, 28, 28))
                    .addGroup(pnl_SanPhamLayout.createSequentialGroup()
                        .addGroup(pnl_SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_tenTheLoai)
                            .addComponent(lbl_tenNXB))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnl_SanPhamLayout.setVerticalGroup(
            pnl_SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_SanPhamLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(pnl_SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_tenSP)
                    .addComponent(lbl_slTon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_tenTacGia)
                    .addComponent(lbl_giaBan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_tenNXB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_tenTheLoai)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_SanPhamLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnl_SanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbl_giaBan;
    private javax.swing.JLabel lbl_image;
    private javax.swing.JLabel lbl_slTon;
    private javax.swing.JLabel lbl_tenNXB;
    private javax.swing.JLabel lbl_tenSP;
    private javax.swing.JLabel lbl_tenTacGia;
    private javax.swing.JLabel lbl_tenTheLoai;
    private javax.swing.JPanel pnl_SanPham;
    // End of variables declaration//GEN-END:variables
}
