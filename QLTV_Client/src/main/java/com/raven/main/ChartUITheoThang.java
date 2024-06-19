package com.raven.main;

import bus.ChiTietHoaDon_Bus;
import bus.HoaDon_Bus;
import entity.HoaDon;
import utils.RMIServiceURL;
import views.Application;
import com.raven.chart.ModelChart;
import java.awt.Color;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChartUITheoThang extends javax.swing.JFrame {

    private boolean isNVQL;
    /**
     * Creates new form Main
     */
    private final HoaDon_Bus hoaDon_Bus;
    private final ChiTietHoaDon_Bus cthd_Bus;
    private static final String URL = RMIServiceURL.getDefaultURL();
    public ChartUITheoThang(boolean isNVQL) throws SQLException, RemoteException, MalformedURLException, NotBoundException {
        hoaDon_Bus = (HoaDon_Bus) Naming.lookup(URL + "HoaDon");
        cthd_Bus = (ChiTietHoaDon_Bus) Naming.lookup(URL + "ChiTietHoaDon");
        this.isNVQL = isNVQL;
        initComponents();
        getContentPane().setBackground(new Color(250, 250, 250));
        chart.addLegend("Doanh thu (VND)", new Color(245, 189, 135));
        renderUITheoThang(spn_year.getValue());
    }

    private void renderUITheoThang(int year) throws SQLException, RemoteException {
        chart.refresh();
        chart.repaint();
        chart.revalidate();
        for (int i = 1; i <= 12; i++) {
            String maNV = isNVQL ? "" : Application.getTK().getNhanVien().getMaNhanVien();
            List<HoaDon> dsHD = hoaDon_Bus.getHoaDonByMonthYear(i, year, maNV);
            double tongTien = 0;
            for (HoaDon j : dsHD) {
                tongTien += cthd_Bus.getTongTienHoaDon(j.getMaHoaDon()) - j.getGiamGia();
            }
            chart.addData(new ModelChart("Tháng " + i, new double[]{tongTien}));
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

        chart = new com.raven.chart.Chart();
        spn_year = new com.toedter.components.JSpinField();
        lbl_year = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        spn_year.setMinimum(201);
        spn_year.setValue(2023);
        spn_year.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                spn_yearPropertyChange(evt);
            }
        });

        lbl_year.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_year.setText("Chọn năm:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 903, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(lbl_year)
                .addGap(18, 18, 18)
                .addComponent(spn_year, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(spn_year, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_year, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void spn_yearPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_spn_yearPropertyChange
        try {
            // TODO add your handling code here:
            renderUITheoThang(spn_year.getValue());
        } catch (SQLException ex) {
            Logger.getLogger(ChartUITheoThang.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }//GEN-LAST:event_spn_yearPropertyChange

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ChartUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ChartUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ChartUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ChartUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ChartUI().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.chart.Chart chart;
    private javax.swing.JLabel lbl_year;
    private com.toedter.components.JSpinField spn_year;
    // End of variables declaration//GEN-END:variables
}
