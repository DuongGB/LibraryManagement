package views;

import bus.ChiTietHoaDon_Bus;
import bus.HoaDon_Bus;
import bus.NhanVien_Bus;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import utils.IconUtils;
import utils.RMIServiceURL;
import utils.getCommonIcons;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
public class panel_DanhSachHoaDon extends javax.swing.JPanel {

    private static final String URL = RMIServiceURL.getDefaultURL();
    private final HoaDon_Bus hoaDon_Bus;
    private final NhanVien_Bus nhanVien_Bus;
    private final ChiTietHoaDon_Bus cthd_Bus;
    DefaultTableModel modelHD;
    DefaultTableModel modelCTHD;
    private Timer debounce;
    private List<HoaDon> dsHD;

    public panel_DanhSachHoaDon() throws SQLException, RemoteException, MalformedURLException, NotBoundException {
        hoaDon_Bus = (HoaDon_Bus) Naming.lookup(URL + "HoaDon");
        nhanVien_Bus = (NhanVien_Bus) Naming.lookup(URL + "NhanVien");
        cthd_Bus = (ChiTietHoaDon_Bus) Naming.lookup(URL + "ChiTietHoaDon");
        initComponents();
        customInitComponents();
        modelHD = (DefaultTableModel) tbl_DSHD.getModel();
        modelCTHD = (DefaultTableModel) tbl_dsCTHD.getModel();
        renderData();
    }

    private void customInitComponents() {
        txt_timKiem.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tìm kiếm...");
        btn_loc.setIcon(IconUtils.filterIcon());
        btn_lamMoi.setIcon(IconUtils.refreshIcon());
        debounce = new Timer(300, (ActionEvent e) -> {
            try {
                // Timer action: Perform search after debounce time
                timHoaDonByMa();
            } catch (SQLException ex) {
                Logger.getLogger(panel_DanhSachHoaDon.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        });
        debounce.setRepeats(false); // Set to non-repeating
    }

    private void renderData() throws SQLException, RemoteException {
        dsHD = hoaDon_Bus.getHoaDonByNhanVien(Application.getTK().getNhanVien().getMaNhanVien());
        modelHD.setRowCount(0);
        for (HoaDon i : dsHD) {
            String tenNV = nhanVien_Bus.timNhanVienByMa(i.getNhanVien().getMaNhanVien()).getTenNhanVien();
            double tongTien = cthd_Bus.getTongTienHoaDon(i.getMaHoaDon()) - i.getGiamGia();
            String tenKH = i.getKhachHang() == null ? "Khách vãng lai" : i.getKhachHang().getTenKhachHang();
            modelHD.addRow(new Object[]{i.getMaHoaDon(), tenKH, tenNV, i.getNgayLap(), tongTien});
        }
    }

    private void timHoaDonByMa() throws SQLException, RemoteException {
        dsHD = hoaDon_Bus.timHoaDon(txt_timKiem.getText().trim(), Application.getTK().getNhanVien().getMaNhanVien());
        modelHD.setRowCount(0);
        for (HoaDon i : dsHD) {
            String tenNV = nhanVien_Bus.timNhanVienByMa(i.getNhanVien().getMaNhanVien()).getTenNhanVien();
            double tongTien = cthd_Bus.getTongTienHoaDon(i.getMaHoaDon()) - i.getGiamGia();
            String tenKH = i.getKhachHang() != null ? i.getKhachHang().getTenKhachHang() : "Khách vãng lai";
            modelHD.addRow(new Object[]{i.getMaHoaDon(), tenKH, tenNV, i.getNgayLap(), tongTien});
        }
    }

    private void locHoaDon() throws SQLException, RemoteException {
        dsHD = hoaDon_Bus.getHoaDonByDateRange(jdc_from.getDate(), jdc_end.getDate(), Application.getTK().getNhanVien().getMaNhanVien());
        List<HoaDon> dsTemp = new ArrayList<>();
        switch (cmb_giaTriHD.getSelectedIndex()) {
            case 1 -> {
                for (HoaDon i : dsHD) {
                    double tongTien = cthd_Bus.getTongTienHoaDon(i.getMaHoaDon());
                    if (tongTien <= 200000) {
                        dsTemp.add(i);
                    }
                }
            }
            case 2 -> {
                for (HoaDon i : dsHD) {
                    double tongTien = cthd_Bus.getTongTienHoaDon(i.getMaHoaDon());
                    if (tongTien > 200000 && tongTien <= 500000) {
                        dsTemp.add(i);
                    }
                }
            }
            case 3 -> {
                for (HoaDon i : dsHD) {
                    double tongTien = cthd_Bus.getTongTienHoaDon(i.getMaHoaDon());
                    if (tongTien >= 500000) {
                        dsTemp.add(i);
                    }
                }
            }
            default ->
                dsTemp = dsHD;
        }
        dsHD = dsTemp;
        modelHD.setRowCount(0);
        for (HoaDon i : dsHD) {
            String tenNV = nhanVien_Bus.timNhanVienByMa(i.getNhanVien().getMaNhanVien()).getTenNhanVien();
            double tongTien = cthd_Bus.getTongTienHoaDon(i.getMaHoaDon()) - i.getGiamGia();
            String tenKH = i.getKhachHang() == null ? "Khách vãng lai" : i.getKhachHang().getTenKhachHang();
            modelHD.addRow(new Object[]{i.getMaHoaDon(), tenKH, tenNV, i.getNgayLap(), tongTien});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_tieuDe = new javax.swing.JPanel();
        lbl_tieuDe = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txt_timKiem = new javax.swing.JTextField();
        lbl_tuNgay = new javax.swing.JLabel();
        lbl_denNgay = new javax.swing.JLabel();
        cmb_giaTriHD = new javax.swing.JComboBox<>();
        btn_loc = new javax.swing.JButton();
        jdc_from = new com.toedter.calendar.JDateChooser();
        jdc_end = new com.toedter.calendar.JDateChooser();
        btn_lamMoi = new javax.swing.JButton();
        btn_quayLai = new javax.swing.JButton();
        scr_DSHD = new javax.swing.JScrollPane();
        tbl_DSHD = new javax.swing.JTable();
        scr_dsHD = new javax.swing.JScrollPane();
        tbl_dsCTHD = new javax.swing.JTable();

        setBackground(new java.awt.Color(240, 240, 240));
        setLayout(new java.awt.BorderLayout());

        pnl_tieuDe.setBackground(new java.awt.Color(240, 240, 240));

        lbl_tieuDe.setBackground(new java.awt.Color(240, 240, 240));
        lbl_tieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_tieuDe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_tieuDe.setText("QUẢN LÝ HÓA ĐƠN");
        lbl_tieuDe.setOpaque(true);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_timKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_timKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_timKiemKeyPressed(evt);
            }
        });
        jPanel1.add(txt_timKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 370, 40));

        lbl_tuNgay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_tuNgay.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbl_tuNgay.setText("Từ ngày:");
        jPanel1.add(lbl_tuNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, 40));

        lbl_denNgay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_denNgay.setText("Đến ngày:");
        jPanel1.add(lbl_denNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, -1, 40));

        cmb_giaTriHD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giá trị hóa đơn", "Thấp hơn 200.000 VND", "Từ 200.000 - 500.000 VND", "Cao hơn 500.000 VND" }));
        jPanel1.add(cmb_giaTriHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 180, 37));

        btn_loc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_loc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_locActionPerformed(evt);
            }
        });
        jPanel1.add(btn_loc, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 60, 90, 36));

        jdc_from.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(jdc_from, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, 40));

        jdc_end.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(jdc_end, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, -1, 40));

        btn_lamMoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_lamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lamMoiActionPerformed(evt);
            }
        });
        jPanel1.add(btn_lamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 60, 90, 36));

        btn_quayLai.setText("Quay lại");
        btn_quayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_quayLaiActionPerformed(evt);
            }
        });
        jPanel1.add(btn_quayLai, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 10, 90, 40));

        scr_DSHD.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Hoá Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        scr_DSHD.setPreferredSize(new java.awt.Dimension(470, 500));

        tbl_DSHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_DSHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Khách Hàng", "Nhân Viên", "Ngày Lập", "Tổng tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_DSHD.setPreferredSize(new java.awt.Dimension(375, 500));
        tbl_DSHD.setRowHeight(32);
        tbl_DSHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    tbl_DSHDMouseClicked(evt);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        scr_DSHD.setViewportView(tbl_DSHD);

        scr_dsHD.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi Tiết Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tbl_dsCTHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_dsCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Mã Sản Phẩm", "Đơn Giá", "Số Lượng", "Tổng tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_dsCTHD.setPreferredSize(new java.awt.Dimension(375, 500));
        tbl_dsCTHD.setRowHeight(32);
        scr_dsHD.setViewportView(tbl_dsCTHD);

        javax.swing.GroupLayout pnl_tieuDeLayout = new javax.swing.GroupLayout(pnl_tieuDe);
        pnl_tieuDe.setLayout(pnl_tieuDeLayout);
        pnl_tieuDeLayout.setHorizontalGroup(
            pnl_tieuDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_tieuDeLayout.createSequentialGroup()
                .addGroup(pnl_tieuDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_tieuDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(scr_DSHD, javax.swing.GroupLayout.DEFAULT_SIZE, 1085, Short.MAX_VALUE)
                        .addComponent(scr_dsHD))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1085, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_tieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 1085, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        pnl_tieuDeLayout.setVerticalGroup(
            pnl_tieuDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_tieuDeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_tieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(scr_DSHD, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(scr_dsHD, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE))
        );

        add(pnl_tieuDe, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_locActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_locActionPerformed
        try {
            // TODO add your handling code here:
            locHoaDon();
        } catch (SQLException ex) {
            Logger.getLogger(panel_DanhSachHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }//GEN-LAST:event_btn_locActionPerformed

    private void btn_lamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lamMoiActionPerformed
        // TODO add your handling code here:
        txt_timKiem.setText("");
        cmb_giaTriHD.setSelectedIndex(0);
        jdc_from.setDate(new Date());
        jdc_end.setDate(new Date());
        try {
            renderData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }//GEN-LAST:event_btn_lamMoiActionPerformed

    private void btn_quayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_quayLaiActionPerformed
        // TODO add your handling code here:
        Application.showForm(Application.getViewTaoHD());
    }//GEN-LAST:event_btn_quayLaiActionPerformed

    private void txt_timKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timKiemKeyPressed
        // TODO add your handling code here:
        debounce.restart();
    }//GEN-LAST:event_txt_timKiemKeyPressed

    private void tbl_DSHDMouseClicked(java.awt.event.MouseEvent evt) throws RemoteException {//GEN-FIRST:event_tbl_DSHDMouseClicked
        // TODO add your handling code here:
        int selectedRow = tbl_DSHD.getSelectedRow();
        if (selectedRow != -1) {
            modelCTHD.setRowCount(0);
            List<ChiTietHoaDon> dsCTHD = cthd_Bus.getChiTietByMa(tbl_DSHD.getValueAt(selectedRow, 0).toString());
            for (ChiTietHoaDon i: dsCTHD) {
                modelCTHD.addRow(new Object[]{i.getHoaDon().getMaHoaDon(), i.getSanPham().getMaSanPham(), i.getGiaBan(), i.getSoLuong(), i.getGiaBan() * i.getSoLuong()});
            }
        }
        
    }//GEN-LAST:event_tbl_DSHDMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_lamMoi;
    private javax.swing.JButton btn_loc;
    private javax.swing.JButton btn_quayLai;
    private javax.swing.JComboBox<String> cmb_giaTriHD;
    private javax.swing.JPanel jPanel1;
    private com.toedter.calendar.JDateChooser jdc_end;
    private com.toedter.calendar.JDateChooser jdc_from;
    private javax.swing.JLabel lbl_denNgay;
    private javax.swing.JLabel lbl_tieuDe;
    private javax.swing.JLabel lbl_tuNgay;
    private javax.swing.JPanel pnl_tieuDe;
    private javax.swing.JScrollPane scr_DSHD;
    private javax.swing.JScrollPane scr_dsHD;
    private javax.swing.JTable tbl_DSHD;
    private javax.swing.JTable tbl_dsCTHD;
    private javax.swing.JTextField txt_timKiem;
    // End of variables declaration//GEN-END:variables
}
