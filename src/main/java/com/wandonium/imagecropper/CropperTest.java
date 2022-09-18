/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wandonium.imagecropper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author Futuristic Ltd
 */
public class CropperTest {
    
    private final static int AREA_SIZE = 400;
    
    private int cropX, cropY, cropWidth, cropHeight, cropCounter;
    private BufferedImage bufferedImage, croppedImage;
    private Image img;
    private File imageFile, croppedFile;
    
    private JLabel croppedImageLbl;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CropperTest();
            }
        });
    }
    
    public CropperTest() {
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
        
        JFrame frame = new JFrame("Crop Image Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane();
        frame.setLayout(new FlowLayout());
        frame.setPreferredSize(new Dimension(900, 700));
        frame.pack();
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
        
        imageFile = new File("C:/Test/image.jpeg");
        cropCounter = 0;
        try {
            bufferedImage = ImageIO.read(imageFile);
            img = bufferedImage.getScaledInstance(AREA_SIZE, 
                    AREA_SIZE,Image.SCALE_SMOOTH);
        } catch (IOException ex) {
            Logger.getLogger(SwingCropper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DrawingArea drawingArea = new DrawingArea(img);
        drawingArea.setForeground(Color.RED);
//        drawingArea.setBounds(0, 0, 400, 400);
        System.out.println("drawingArea width");
        
        croppedImageLbl = new JLabel("Cropped Image goes here...", 
                SwingConstants.CENTER);
        croppedImageLbl.setPreferredSize(new Dimension(400,400));
        croppedImageLbl.setBorder(BorderFactory.createLineBorder(Color.black));
        
        JTextArea infoErrTxt = new JTextArea(2, 40);
        infoErrTxt.setText("Please drag your mouse over the image on "
                + "left to draw a rectangle. Then click the 'Crop Image' button "
                + "below to crop the image.");
        infoErrTxt.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
        infoErrTxt.setWrapStyleWord(true);
        infoErrTxt.setLineWrap(true);
        infoErrTxt.setOpaque(false);
        infoErrTxt.setEditable(false);
        infoErrTxt.setFocusable(false);
        infoErrTxt.setFont(new Font("Times New Roman", Font.BOLD, 20));
        infoErrTxt.setBackground(Color.LIGHT_GRAY);
        infoErrTxt.setMargin(new Insets(2,15,2,2));

        
        JButton cropBtn = new JButton("Crop Image");
        JButton resetBtn = new JButton("Reset");
        JButton doneBtn = new JButton("Done");
        
        cropBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cropImage();
            }
        });
        
        JPanel topPanel = new JPanel();
        topPanel.add(drawingArea);
        topPanel.add(croppedImageLbl);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(600, 100));
//        centerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        centerPanel.add(infoErrTxt);
        
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        bottomPanel.setPreferredSize(new Dimension(900, 50));
        
        JPanel innerPanel = new JPanel();
        innerPanel.add(cropBtn);
        innerPanel.add(resetBtn);
        innerPanel.add(doneBtn);
        
        bottomPanel.add(innerPanel);
       
        frame.add(topPanel);
        frame.add(centerPanel);
        frame.add(bottomPanel);
    }
    
    public void cropImage() {
        try
        {
            cropCounter++;
            System.out.println("cropX: " + cropX + "\ncropY: " + cropY);
            System.out.println("cropWidth: " + cropWidth + "\ncropHeight: " 
                    + cropHeight);
            croppedImage = bufferedImage.getSubimage(cropX, cropY, cropWidth, 
                    cropHeight);
            croppedFile = new File("C:/Test/image-crop-" + cropCounter + ".jpeg");
            ImageIO.write(croppedImage,"jpeg", croppedFile);
            img = croppedImage.getScaledInstance(croppedImageLbl.getWidth(), 
                    croppedImageLbl.getHeight(),Image.SCALE_SMOOTH);
            croppedImageLbl.setIcon(new ImageIcon(img));
        }
        catch (IOException e) 
        {
            System.out.println(e);
        } 
    }
    
    class DrawingArea extends JLabel
    {
        private BufferedImage image = new BufferedImage(AREA_SIZE, AREA_SIZE, 
                BufferedImage.TYPE_INT_ARGB);
        private Rectangle shape;
        private int counter;

        public DrawingArea(Image theImage)
        {
            setBackground(Color.WHITE);

            MyMouseListener ml = new MyMouseListener();
            addMouseListener(ml);
            addMouseMotionListener(ml);
           
            
            setIcon(new ImageIcon(theImage));
            counter = 0;
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
            if(counter < 1) {
                //  Draw the Rectangle onto the BufferedImage
                Graphics2D g2d = (Graphics2D)image.getGraphics();
                g2d.setColor( color );
                g2d.draw( rectangle );
                repaint();
                counter++;
            }
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
                if(counter < 1) {
                    startPoint = e.getPoint();
                    shape = new Rectangle();
                }
            }

            public void mouseDragged(MouseEvent e)
            {
//                int x = Math.min(startPoint.x, e.getX());
//                int y = Math.min(startPoint.y, e.getY());
//                int width = Math.abs(startPoint.x - e.getX());
//                int height = Math.abs(startPoint.y - e.getY());
                cropX = Math.min(startPoint.x, e.getX());
                cropY = Math.min(startPoint.y, e.getY());
                cropWidth = Math.abs(startPoint.x - e.getX());
                cropHeight = Math.abs(startPoint.y - e.getY());

                if(counter < 1) {
//                    shape.setBounds(x, y, width, height);
                    shape.setBounds(cropX, cropY, cropWidth, cropHeight);
                    repaint();
                }
            }

            public void mouseReleased(MouseEvent e)
            {
                if(shape != null) {
                    if (shape.width != 0 || shape.height != 0)
                    {
                        addRectangle(shape, e.getComponent().getForeground());
                    }
                }

                shape = null;
            }
        }
    }
    
}
