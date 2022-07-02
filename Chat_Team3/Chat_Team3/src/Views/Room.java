/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import javax.swing.ImageIcon;
import utils.XImage;

/**
 *
 * @author ACER
 */
public class Room extends javax.swing.JPanel {

    /**
     * Creates new form Room
     */
    public int id;
    public Room(String avatar, String name, String message, int id) {
        initComponents();
        this.id = id;
        lblAvatar.setIcon(new ImageIcon(XImage.read(avatar, 50, 50)));
        lblTen.setText(name);
        lblTinNhanCuoi.setText(message);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(3, 0), new java.awt.Dimension(3, 0), new java.awt.Dimension(32767, 0));
        lblAvatar = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 0));
        jPanel1 = new javax.swing.JPanel();
        lblTen = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 15));
        lblTinNhanCuoi = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setMaximumSize(new java.awt.Dimension(290, 52));
        setMinimumSize(new java.awt.Dimension(290, 52));
        setPreferredSize(new java.awt.Dimension(290, 52));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));
        add(filler3);

        lblAvatar.setText("Avatar");
        lblAvatar.setMaximumSize(new java.awt.Dimension(50, 50));
        lblAvatar.setMinimumSize(new java.awt.Dimension(50, 50));
        lblAvatar.setPreferredSize(new java.awt.Dimension(50, 50));
        add(lblAvatar);
        add(filler2);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(431443, 43423423));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));

        lblTen.setText("Ten");
        jPanel1.add(lblTen);
        jPanel1.add(filler1);

        lblTinNhanCuoi.setText("Tin nhan cuoi cung");
        jPanel1.add(lblTinNhanCuoi);

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JLabel lblTen;
    private javax.swing.JLabel lblTinNhanCuoi;
    // End of variables declaration//GEN-END:variables
}
