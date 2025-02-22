package views;

import bus.TaiKhoan_Bus;
import entity.TaiKhoan;
import utils.NotifyToast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import api.speedsms.vn.SpeedSMSAPI;
import com.formdev.flatlaf.FlatClientProperties;
import utils.RMIServiceURL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;
import java.util.TimerTask;
import javax.swing.Timer;


public class JDialogLayLaiMatKhau extends javax.swing.JDialog {

    private static final String URL = RMIServiceURL.getDefaultURL();
    private String OTP;
    private Timer debounce;
    private Timer countdownTimer;
    private TaiKhoan_Bus tk_Bus;

    /**
     * Creates new form JDialog
     */
    public JDialogLayLaiMatKhau(java.awt.Frame parent, boolean modal) throws RemoteException, MalformedURLException, NotBoundException {
        super(parent, modal);
        tk_Bus = (TaiKhoan_Bus) Naming.lookup(URL + "TaiKhoan");
        initComponents();

        txT_matKhauMoi.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true;"
                + "showCapsLock:true");
        txt_xacNhanMKM.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true;"
                + "showCapsLock:true");
        // Khởi tạo Timer

        // Lên lịch task để chạy sau 60 giây
        debounce = new Timer(60000, (ActionEvent e) -> {
            // Timer action: Perform search after debounce time
            this.OTP = generateOTP();
        });
        debounce.setRepeats(false); // Set to non-repeating
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public static String generateOTP() {
        // Độ dài của mã OTP
        int otpLength = 6;

        // Ký tự được sử dụng để tạo mã OTP
        String digits = "0123456789";

        // StringBuilder để xây dựng mã OTP
        StringBuilder otp = new StringBuilder(otpLength);

        // Sử dụng đối tượng Random để sinh số ngẫu nhiên
        Random random = new Random();

        // Tạo mã OTP bằng cách lấy ngẫu nhiên ký tự từ chuỗi digits
        for (int i = 0; i < otpLength; i++) {
            int index = random.nextInt(digits.length());
            char digit = digits.charAt(index);
            otp.append(digit);
        }

        return otp.toString();
    }

    private boolean validData() throws RemoteException {
        String tenTK = txt_tenTaiKhoan.getText();
        String otpCode = txt_MaXacNhan.getText();
        String matKhau = txT_matKhauMoi.getText();
        String matKhau2 = txt_xacNhanMKM.getText();
        TaiKhoan tk = tk_Bus.getTaiKhoanByTen(tenTK);
        if (tk == null) {
            NotifyToast.showErrorToast("Tài khoản không tồn tại");
            return false;
        }
        if (!otpCode.equals(OTP)) {
            NotifyToast.showErrorToast("Mã OTP không hợp lệ");
            return false;
        }
        if (matKhau.length() < 8) {
            NotifyToast.showErrorToast("Mật khẩu ít nhất 8 kí tự");
            return false;
        }
        if (!matKhau.equals(matKhau2)) {
            NotifyToast.showErrorToast("Mật khẩu xác nhận thất bại");
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnl_1 = new javax.swing.JPanel();
        lblTenTaikhoan = new javax.swing.JLabel();
        txt_tenTaiKhoan = new javax.swing.JTextField();
        txt_maXacNhan = new javax.swing.JLabel();
        lbl_matKhauMoi = new javax.swing.JLabel();
        lbl_xacNhanMK = new javax.swing.JLabel();
        txt_MaXacNhan = new javax.swing.JTextField();
        btn_GuiMa = new javax.swing.JButton();
        txT_matKhauMoi = new javax.swing.JPasswordField();
        txt_xacNhanMKM = new javax.swing.JPasswordField();
        pnl_2 = new javax.swing.JPanel();
        btn_xacNhan = new javax.swing.JButton();
        btn_thoat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LẤY LẠI MẬT KHẨU");

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
                pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlTopLayout.setVerticalGroup(
                pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlTopLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(18, Short.MAX_VALUE))
        );

        lblTenTaikhoan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenTaikhoan.setText("Tên Tài Khoản");

        txt_maXacNhan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_maXacNhan.setText("Mã Xác Nhận");

        lbl_matKhauMoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_matKhauMoi.setText("Mật Khẩu Mới");

        lbl_xacNhanMK.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_xacNhanMK.setText("Xác Nhận Mật Khẩu Mới");

        txt_MaXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MaXacNhanActionPerformed(evt);
            }
        });

        btn_GuiMa.setBackground(new java.awt.Color(51, 51, 255));
        btn_GuiMa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_GuiMa.setForeground(new java.awt.Color(255, 255, 255));
        btn_GuiMa.setText("Gửi Mã");
        btn_GuiMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btn_GuiMaActionPerformed(evt);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        javax.swing.GroupLayout pnl_1Layout = new javax.swing.GroupLayout(pnl_1);
        pnl_1.setLayout(pnl_1Layout);
        pnl_1Layout.setHorizontalGroup(
                pnl_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnl_1Layout.createSequentialGroup()
                                .addGroup(pnl_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(pnl_1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(txT_matKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnl_1Layout.createSequentialGroup()
                                                .addGap(117, 117, 117)
                                                .addGroup(pnl_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(pnl_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(txt_tenTaiKhoan)
                                                                .addComponent(lblTenTaikhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE))
                                                        .addGroup(pnl_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addGroup(pnl_1Layout.createSequentialGroup()
                                                                        .addComponent(txt_MaXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(btn_GuiMa, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addComponent(txt_maXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(lbl_matKhauMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(lbl_xacNhanMK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addComponent(txt_xacNhanMKM, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(119, Short.MAX_VALUE))
        );
        pnl_1Layout.setVerticalGroup(
                pnl_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnl_1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblTenTaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_tenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_maXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnl_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_MaXacNhan, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                                        .addComponent(btn_GuiMa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl_matKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txT_matKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_xacNhanMK, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_xacNhanMKM, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(15, Short.MAX_VALUE))
        );

        btn_xacNhan.setBackground(new java.awt.Color(204, 204, 204));
        btn_xacNhan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_xacNhan.setText("Xác Nhận");
        btn_xacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xacNhanActionPerformed(evt);
            }
        });

        btn_thoat.setBackground(new java.awt.Color(255, 0, 0));
        btn_thoat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_thoat.setText("Thoát");
        btn_thoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_2Layout = new javax.swing.GroupLayout(pnl_2);
        pnl_2.setLayout(pnl_2Layout);
        pnl_2Layout.setHorizontalGroup(
                pnl_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_2Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_thoat, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_xacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(118, 118, 118))
        );
        pnl_2Layout.setVerticalGroup(
                pnl_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnl_2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnl_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btn_xacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn_thoat, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnl_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnl_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnl_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                                .addComponent(pnl_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_MaXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MaXacNhanActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_txt_MaXacNhanActionPerformed

    private void btn_GuiMaActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException {//GEN-FIRST:event_btn_GuiMaActionPerformed
        // TODO add your handling code here:
//        startCountdown();
//        this.OTP = generateOTP();
//        debounce.restart();
//        SpeedSMSAPI api = new SpeedSMSAPI("cNNgRJVRW_lKvV7OQBArJeDpuwPeSA6l");
//        try {
//            String result = api.sendSMS(txt_tenTaiKhoan.getText(), "Mã OTP là: " + this.OTP, 5, "c72a2d5ba58a7945");
//            System.out.println(result);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        String tenTK = txt_tenTaiKhoan.getText();
        TaiKhoan tk = tk_Bus.getTaiKhoanByTen(tenTK);
        if (tk == null) {
            NotifyToast.showErrorToast("Tài khoản không tồn tại");
            return;
        }
        startCountdown();
        this.OTP = generateOTP();
        debounce.restart();
        SpeedSMSAPI api = new SpeedSMSAPI("cNNgRJVRW_lKvV7OQBArJeDpuwPeSA6l");
        try {
            String result = api.sendSMS(txt_tenTaiKhoan.getText(), "Mã OTP là: " + this.OTP, 5, "c72a2d5ba58a7945");
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_GuiMaActionPerformed

    private void btn_thoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thoatActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btn_thoatActionPerformed

    private void btn_xacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xacNhanActionPerformed
        // TODO add your handling code here:
        try {
            if (validData()) {
                if (tk_Bus.updateMatKhau(txt_tenTaiKhoan.getText(), txT_matKhauMoi.getText())) {
                    dispose();
                } else {
                    NotifyToast.showErrorToast("Có lỗi xảy ra");
                }
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }//GEN-LAST:event_btn_xacNhanActionPerformed

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
            java.util.logging.Logger.getLogger(JDialogLayLaiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogLayLaiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogLayLaiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogLayLaiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialogLayLaiMatKhau dialog = null;
                try {
                    dialog = new JDialogLayLaiMatKhau(new javax.swing.JFrame(), true);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (NotBoundException e) {
                    throw new RuntimeException(e);
                }
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    private void startCountdown() {
        btn_GuiMa.setEnabled(false);

        final int[] countdownSeconds = {60};

        countdownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countdownSeconds[0]--;

                if (countdownSeconds[0] >= 0) {
                    btn_GuiMa.setText(countdownSeconds[0] + "s");
                } else {
                    stopCountdown();
                }
            }
        });

        countdownTimer.start();
    }

    private void stopCountdown() {
        if (countdownTimer != null) {
            countdownTimer.stop();
            btn_GuiMa.setEnabled(true);
            btn_GuiMa.setText("Gửi mã");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_GuiMa;
    private javax.swing.JButton btn_thoat;
    private javax.swing.JButton btn_xacNhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblTenTaikhoan;
    private javax.swing.JLabel lbl_matKhauMoi;
    private javax.swing.JLabel lbl_xacNhanMK;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JPanel pnl_1;
    private javax.swing.JPanel pnl_2;
    private javax.swing.JPasswordField txT_matKhauMoi;
    private javax.swing.JTextField txt_MaXacNhan;
    private javax.swing.JLabel txt_maXacNhan;
    private javax.swing.JTextField txt_tenTaiKhoan;
    private javax.swing.JPasswordField txt_xacNhanMKM;
    // End of variables declaration//GEN-END:variables
}
