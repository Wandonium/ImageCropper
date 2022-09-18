/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wandonium.imagecropper;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author Hillary Wando
 */
public class DrawOnImage {
    
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI()
    {
        DrawingArea drawingArea = new DrawingArea();
        ButtonPanel buttonPanel = new ButtonPanel( drawingArea );

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Draw On Image");
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.getContentPane().add(drawingArea);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
    }

    static class ButtonPanel extends JPanel implements ActionListener
    {
        private DrawingArea drawingArea;

        public ButtonPanel(DrawingArea drawingArea)
        {
            this.drawingArea = drawingArea;

            add( createButton("	", Color.BLACK) );
            add( createButton("	", Color.RED) );
            add( createButton("	", Color.GREEN) );
            add( createButton("	", Color.BLUE) );
            add( createButton("	", Color.ORANGE) );
            add( createButton("	", Color.YELLOW) );
            add( createButton("Clear Drawing", null) );
        }

        private JButton createButton(String text, Color background)
        {
            JButton button = new JButton( text );
            button.setBackground( background );
            button.addActionListener( this );

            return button;
        }

        public void actionPerformed(ActionEvent e)
        {
            JButton button = (JButton)e.getSource();

            if ("Clear Drawing".equals(e.getActionCommand()))
                drawingArea.clear();
            else
                drawingArea.setForeground( button.getBackground() );
        }
    }

    static class DrawingArea extends JLabel
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
    
}
