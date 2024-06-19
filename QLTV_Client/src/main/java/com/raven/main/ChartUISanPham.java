/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.main;

import bus.ChiTietHoaDon_Bus;
import com.raven.chart.ModelChart;
import utils.RMIServiceURL;

import java.awt.Color;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RAVEN
 */
public class ChartUISanPham extends javax.swing.JFrame {

/**
     * Creates new form Main
     */
    private final ChiTietHoaDon_Bus cthd_Bus;
    private static final String URL = RMIServiceURL.getDefaultURL();
    public ChartUISanPham() throws SQLException, RemoteException, MalformedURLException, NotBoundException {
        cthd_Bus = (ChiTietHoaDon_Bus) Naming.lookup(URL + "ChiTietHoaDon");
        initComponents();
        getContentPane().setBackground(new Color(250, 250, 250));
        chart.addLegend("Doanh thu (VND)", new Color(245, 189, 135));
        renderUI(jdc_date.getDate());
//        chart.addData(new ModelChart("January", new double[]{500, 200, 80,89}));
//        chart.addData(new ModelChart("February", new double[]{600, 750, 90,150}));
//        chart.addData(new ModelChart("March", new double[]{200, 350, 460,900}));
//        chart.addData(new ModelChart("April", new double[]{480, 150, 750,700}));
//        chart.addData(new ModelChart("May", new double[]{350, 540, 300,150}));
//        chart.addData(new ModelChart("June", new double[]{190, 280, 81,200}));
    }

    private void renderUI(Date date) throws SQLException, RemoteException {
        chart.refresh();
        chart.repaint();
        chart.revalidate();
        List<Object[]> obj = cthd_Bus.getSpBanChay(5, null, null, null);
        for (Object[] i : obj) {
            chart.addData(new ModelChart((String) i[0], new double[]{(double) i[1]}));
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
        lbl_date = new javax.swing.JLabel();
        jdc_date = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(585, 382));

        lbl_date.setText("Chọn ngày:");

        jdc_date.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdc_datePropertyChange(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 903, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_date)
                .addGap(18, 18, 18)
                .addComponent(jdc_date, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jdc_date, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(lbl_date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jdc_datePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdc_datePropertyChange
        try {
            // TODO add your handling code here:

            renderUI(jdc_date.getDate());
        } catch (SQLException ex) {
            Logger.getLogger(ChartUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }//GEN-LAST:event_jdc_datePropertyChange

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
    private com.toedter.calendar.JDateChooser jdc_date;
    private javax.swing.JLabel lbl_date;
    // End of variables declaration//GEN-END:variables
}
