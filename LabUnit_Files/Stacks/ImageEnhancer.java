/*
 * ImageEnhancer.java
 * (c) By Steve Tanimoto,  January 2, 2016,
 * with contributions by Si J. Liu, 
 * and originally inspired by a tutorial example at Oracle.com.
 */

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ByteLookupTable;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.LookupOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ImageEnhancer extends Component implements ActionListener {
 private static final long serialVersionUID = 1L;
 String startingImage = "AYPE-Rainier-Vista.jpg";
    BufferedImage biTemp; // Used when reading in an image.
    BufferedImage biWorking; // The current image.
    BufferedImage biFiltered; // Result of processing the current image.
    Graphics gWorking; // Object that allows drawing pixels into biWorking.
    int width, height;
    byte[] darkening_lut, photoneg_lut, threshold_lut; // look-up tables
    LookupOp darkening_op, photoneg_op, threshold_op; // look-up operators
    ConvolveOp blurring_op, sharpening_op; // convolution operators
    
    // Note that the menu items are public so that they can be easily called
    // by the external autograder code.
    private static JMenuBar menuBar;
    private static JMenu fileMenu, editMenu, imageMenu;
    public static JMenuItem exitItem, undoItem, redoItem, darkenItem,
     blurItem, sharpenItem, photoNegItem, thresholdItem;

    //  Students: Here, you should declare two variables to hold instances
    //  of your stack class, with one for Undo and one for Redo.
    

    // A 3x3 filtering kernel for high-pass filtering:
    public static final float[] highPass = {
        0.f, -1.f,  0.f,
       -1.f,  5.f, -1.f,
        0.f, -1.f,  0.f
    };

    // A 3x3 filtering kernel for low-pass filtering:
    public static final float[] lowPass = {
        0.1f, 0.1f, 0.1f,
        0.1f, 0.2f, 0.1f,
        0.1f, 0.1f, 0.1f
    };
 
    private void createMenu() {
     menuBar = new JMenuBar();
     fileMenu = new JMenu("File");
     editMenu = new JMenu("Edit");
     imageMenu = new JMenu("Image");
     exitItem = new JMenuItem("Exit");
     undoItem = new JMenuItem("Undo");
     redoItem = new JMenuItem("Redo");
     darkenItem = new JMenuItem("Darken");
     blurItem = new JMenuItem("Blur");
     sharpenItem = new JMenuItem("Sharpen");
     photoNegItem = new JMenuItem("Photo-negative");
     thresholdItem = new JMenuItem("Threshold");
        exitItem.addActionListener(this);
        undoItem.addActionListener(this);
        redoItem.addActionListener(this);
        darkenItem.addActionListener(this);
        blurItem.addActionListener(this);
        sharpenItem.addActionListener(this);
        photoNegItem.addActionListener(this);
        thresholdItem.addActionListener(this);

     fileMenu.add(exitItem);
     editMenu.add(undoItem);
     editMenu.add(redoItem);
     imageMenu.add(darkenItem);
     imageMenu.add(blurItem);
     imageMenu.add(sharpenItem);
     imageMenu.add(photoNegItem);
     imageMenu.add(thresholdItem);
     menuBar.add(fileMenu);
     menuBar.add(editMenu);
     menuBar.add(imageMenu);
     
     undoItem.setEnabled(false);
     redoItem.setEnabled(false);
     
    }
    void setUpImageTransformations() {
     // Set up the Darkening (lookup) operation
     darkening_lut = new byte[256];
     for (int j=0; j<256; j++) {
      darkening_lut[j] = (byte)(j*9.0 / 10.0); 
     }
     ByteLookupTable darkening_blut = new ByteLookupTable(0, darkening_lut); 
     darkening_op = new LookupOp(darkening_blut, null);

     // Set up the Photo-negative (lookup) operation
     photoneg_lut = new byte[256];
     for (int j=0; j<256; j++) {
      photoneg_lut[j] = (byte)(255-j); 
     }
     ByteLookupTable photoneg_blut = new ByteLookupTable(0, photoneg_lut); 
     photoneg_op = new LookupOp(photoneg_blut, null);

     // Set up the Thresholding (lookup) operation
     threshold_lut = new byte[256];
     for (int j=0; j<256; j++) {
      threshold_lut[j] = (byte)(j < 128 ? 0: 255);  
     }
     ByteLookupTable threshold_blut = new ByteLookupTable(0, threshold_lut); 
     threshold_op = new LookupOp(threshold_blut, null);

     // Set up the Blurring (convolution with low-pass filtering) operation
     blurring_op = new ConvolveOp(new Kernel(3, 3, lowPass),
       ConvolveOp.EDGE_NO_OP, null);

     // Set up the Sharpening (convolution with high-pass filtering) operation
     sharpening_op = new ConvolveOp(new Kernel(3, 3, highPass),
       ConvolveOp.EDGE_NO_OP, null);
    }

    public ImageEnhancer() {
     createMenu();
     setUpImageTransformations();
        try {
            biTemp = ImageIO.read(new File(startingImage));
            width = biTemp.getWidth(null);
            height = biTemp.getHeight(null);
            biWorking = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            gWorking = biWorking.getGraphics();
            gWorking.drawImage(biTemp, 0, 0, null);
        } catch (IOException e) {
            System.out.println("Image could not be read: "+startingImage);
            System.exit(1);
        }
        
        //  Students: Add code to create empty stack instances for the Undo stack 
        //  and the Redo stack, and put your code for this here:
        
    }

    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public void paint(Graphics g) {
        g.drawImage(biWorking, 0, 0, null);
    }
    
    public void blur() {
  biFiltered = blurring_op.filter(biWorking, null);
    }
    public void sharpen() {
  biFiltered = sharpening_op.filter(biWorking, null);
    }
    public void darken() {
  biFiltered = darkening_op.filter(biWorking, null);
    }
    public void photoneg() {
  biFiltered = photoneg_op.filter(biWorking, null);
    }
    public void threshold() {
  biFiltered = threshold_op.filter(biWorking, null);
    }
       
    // We handle menu selection events here: //
    public void actionPerformed(ActionEvent e) {
        //  Students: Add code in this method to save the current buffered image for
        // undoing and dispose of any redoable actions.
        //  Also add code to enable and disable the Undo and Redo menu items, and to process
        //  these items when the user selects them.

     //System.out.println("The actionEvent is "+e); // This can be useful when debugging.
     if (e.getSource()==exitItem) { System.exit(0); }
     if (e.getSource()==blurItem) { blur(); }
     if (e.getSource()==sharpenItem) { sharpen(); }
     if (e.getSource()==darkenItem) { darken(); }
     if (e.getSource()==photoNegItem) { photoneg(); }
     if (e.getSource()==thresholdItem) { threshold(); }
        gWorking.drawImage(biFiltered, 0, 0, null); // Draw the pixels from biFiltered into biWorking.
        repaint(); // Ask Swing to update the screen.
        printNumbersOfElementsInBothStacks(); // Report on the states of the stacks.
        return;      
    }

    private ImageEnhancer image_enhancer_instance;
    public ImageEnhancer getImageEnhancer() { // For use by the autograder
      if(image_enhancer_instance == null) {
       image_enhancer_instance = new ImageEnhancer();
      }
      return image_enhancer_instance;
    }
 public BufferedImage getBufferedImage() { // For use by the autograder
  return biWorking;
 }

    public static void main(String s[]) {
     new ImageEnhancer().run(); // Called from below, and by the autograder.
    }
    
    public void run() {
        JFrame f = new JFrame("ImageEnhancer without Undo or Redo"); // Students should update this.
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        image_enhancer_instance = new ImageEnhancer(); 
        f.setJMenuBar(image_enhancer_instance.menuBar);
        f.add("Center", image_enhancer_instance);
        f.pack();
        f.setVisible(true);
    }
    
    public void printNumbersOfElementsInBothStacks() {
     //  Students: Uncomment this code that prints out the numbers of elements
     //  in each of the two stacks (Undo and Redo):
        //System.out.println("The Undo stack contains " + undoStack.getSize() + " elements.");
        //System.out.println("The Redo stack contains " + redoStack.getSize() + " elements.");
    }
}
