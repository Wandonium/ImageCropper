/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wandonium.imagecropper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author Futuristic Ltd
 */
public class SwingCropper extends javax.swing.JFrame {
    
    private BufferedImage bufferedImage;
    private Image img;
    private int x, y, width, height, counter;

    /**
     * Creates new form SwingCropper
     */
    public SwingCropper() {
        initComponents();
        File imageFile = new File("C:/Test/image.jpeg");
        counter = 0;
        try {
            bufferedImage = ImageIO.read(imageFile);
            img = bufferedImage.getScaledInstance(originalImageLbl.getWidth(), 
                    originalImageLbl.getHeight(),Image.SCALE_SMOOTH);
        } catch (IOException ex) {
            Logger.getLogger(SwingCropper.class.getName()).log(Level.SEVERE, null, ex);
        }
        originalImageLbl.setIcon(new ImageIcon(img)); 
    }
    
    class DrawingArea extends JLabel
    {
        private final static int AREA_SIZE = 400;
        private BufferedImage image =
                new BufferedImage(AREA_SIZE, AREA_SIZE, BufferedImage.TYPE_INT_ARGB);
        private Rectangle shape;
        private BufferedImage bufferedImage;
        private Image img;

        public DrawingArea()
        {
            setBackground(Color.WHITE);

            MyMouseListener ml = new MyMouseListener();
            addMouseListener(ml);
            addMouseMotionListener(ml);
            
//            JLabel photoLbl = new JLabel();
//            photoLbl.setPreferredSize(new Dimension(AREA_SIZE, AREA_SIZE));
            File imageFile = new File("C:/Test/image.jpeg");
            try {
                bufferedImage = ImageIO.read(imageFile);
                img = bufferedImage.getScaledInstance(AREA_SIZE, 
                        AREA_SIZE,Image.SCALE_SMOOTH);
            } catch (IOException ex) {
                Logger.getLogger(SwingCropper.class.getName()).log(Level.SEVERE, null, ex);
            }
            setIcon(new ImageIcon(img));
//            photoLbl.setIcon(new ImageIcon(img));
//            add(photoLbl);
        }

        @Override
        public Dimension getPreferredSize()
        {
            return isPreferredSizeSet() ?
                super.getPreferredSize() : new Dimension(AREA_SIZE, AREA_SIZE);
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            //  Custom code to support painting from the BufferedImage

            if (image != null)
            {
                g.drawImage(image, 0, 0, null);
            }

            //  Paint the Rectangle as the mouse is being dragged

            if (shape != null)
            {
                Graphics2D g2d = (Graphics2D)g;
                g2d.draw( shape );
            }
        }

        public void addRectangle(Rectangle rectangle, Color color)
        {
            //  Draw the Rectangle onto the BufferedImage

            Graphics2D g2d = (Graphics2D)image.getGraphics();
            g2d.setColor( color );
            g2d.draw( rectangle );
            repaint();
        }

        public void clear()
        {
            createEmptyImage();
            repaint();
        }

        private void createEmptyImage()
        {
            image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = (Graphics2D)image.getGraphics();
            g2d.setColor(Color.BLACK);
            g2d.drawString("Add a rectangle by doing mouse press, drag and release!", 40, 15);
        }

        class MyMouseListener extends MouseInputAdapter
        {
            private Point startPoint;

            public void mousePressed(MouseEvent e)
            {
                startPoint = e.getPoint();
                shape = new Rectangle();
            }

            public void mouseDragged(MouseEvent e)
            {
                int x = Math.min(startPoint.x, e.getX());
                int y = Math.min(startPoint.y, e.getY());
                int width = Math.abs(startPoint.x - e.getX());
                int height = Math.abs(startPoint.y - e.getY());

                shape.setBounds(x, y, width, height);
                repaint();
            }

            public void mouseReleased(MouseEvent e)
            {
                if (shape.width != 0 || shape.height != 0)
                {
                    addRectangle(shape, e.getComponent().getForeground());
                }

                shape = null;
            }
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

        TopPanel = new javax.swing.JPanel();
        originalImageLbl = new javax.swing.JLabel();
        croppedImageLbl = new javax.swing.JLabel();
        enterXLbl = new javax.swing.JLabel();
        enterXText = new javax.swing.JTextField();
        enterYLbl = new javax.swing.JLabel();
        enterYText = new javax.swing.JTextField();
        enterWidthLbl = new javax.swing.JLabel();
        enterWidthTxt = new javax.swing.JTextField();
        enterHeightLbl = new javax.swing.JLabel();
        enterHeightTxt = new javax.swing.JTextField();
        cropBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout TopPanelLayout = new javax.swing.GroupLayout(TopPanel);
        TopPanel.setLayout(TopPanelLayout);
        TopPanelLayout.setHorizontalGroup(
            TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopPanelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(originalImageLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(croppedImageLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TopPanelLayout.setVerticalGroup(
            TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(croppedImageLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                    .addComponent(originalImageLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        enterXLbl.setText("Enter X:");

        enterXText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterXTextActionPerformed(evt);
            }
        });

        enterYLbl.setText("Enter Y:");

        enterYText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterYTextActionPerformed(evt);
            }
        });

        enterWidthLbl.setText("Enter width:");

        enterWidthTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterWidthTxtActionPerformed(evt);
            }
        });

        enterHeightLbl.setText("Enter height:");

        enterHeightTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterHeightTxtActionPerformed(evt);
            }
        });

        cropBtn.setText("Crop Image");
        cropBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cropBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(enterYLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(enterYText, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(enterXLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(enterXText, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(enterWidthLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(enterWidthTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(enterHeightLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(enterHeightTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(355, 355, 355)
                        .addComponent(cropBtn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(TopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(enterXLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enterXText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(enterYLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enterYText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(enterWidthLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enterWidthTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(enterHeightLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enterHeightTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(cropBtn)
                .addGap(0, 32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enterXTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterXTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enterXTextActionPerformed

    private void enterYTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterYTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enterYTextActionPerformed

    private void enterWidthTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterWidthTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enterWidthTxtActionPerformed

    private void enterHeightTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterHeightTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enterHeightTxtActionPerformed

    private void cropBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cropBtnActionPerformed
        // TODO add your handling code here:
        x = Integer.parseInt(enterXText.getText());
        y = Integer.parseInt(enterYText.getText());
        width = Integer.parseInt(enterWidthTxt.getText());
        height = Integer.parseInt(enterHeightTxt.getText());
        counter++;
        try
            {
            BufferedImage image=bufferedImage.getSubimage(x,y,width,height);
            File pathFile = new File("C:/Test/image-crop-" + counter + ".jpeg");
            ImageIO.write(image,"jpeg", pathFile);
            img = image.getScaledInstance(croppedImageLbl.getWidth(), 
                    croppedImageLbl.getHeight(),Image.SCALE_SMOOTH);
            croppedImageLbl.setIcon(new ImageIcon(img));   
            System.out.println("Image cropped successfully. Check it in folder: "
                    + "C:/Test/image-crop.jpeg");
          }
        catch (IOException e) 
            {
                System.out.println(e);
            } 
    }//GEN-LAST:event_cropBtnActionPerformed

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
            java.util.logging.Logger.getLogger(SwingCropper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SwingCropper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SwingCropper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SwingCropper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SwingCropper().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel TopPanel;
    private javax.swing.JButton cropBtn;
    private javax.swing.JLabel croppedImageLbl;
    private javax.swing.JLabel enterHeightLbl;
    private javax.swing.JTextField enterHeightTxt;
    private javax.swing.JLabel enterWidthLbl;
    private javax.swing.JTextField enterWidthTxt;
    private javax.swing.JLabel enterXLbl;
    private javax.swing.JTextField enterXText;
    private javax.swing.JLabel enterYLbl;
    private javax.swing.JTextField enterYText;
    private javax.swing.JLabel originalImageLbl;
    // End of variables declaration//GEN-END:variables
}
