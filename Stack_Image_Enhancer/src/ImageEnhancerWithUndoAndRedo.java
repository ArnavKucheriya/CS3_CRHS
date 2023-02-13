// Arnav Kucheriya (s187058)
// ArnavKucheriya@outlook.com

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ByteLookupTable;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.LookupOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.util.*;
public class ImageEnhancerWithUndoAndRedo extends Component implements ActionListener {

        Scanner in = new Scanner(System.in);

    String OriginalImage = "cropped-1920-1080-1111270.png";
    int opIndex;
    BufferedImage biTemp, biOriginal;
    BufferedImage biWorking;
    BufferedImage biFiltered;
    Graphics gOrig;
    Graphics gWorking;
    Graphics gFiltered;
    int w;
    int h;
    byte[] byte0, byte3, byte4, byte5, byte6, byte7;
    LookupOp op0, op3, op4, op5, op6, op7;

    static JPopupMenu popup;

    BufferedImageStack undoStack; // instance of undo stack
    BufferedImageStack redoStack; // instance of redo stack
    // Here, you should declare two variables to hold instances of your stack class, with one for Undo and one for Redo.

    private ImageEnhancerWithUndoAndRedo si;
    private JMenuItem undoItem;	 // new field to provide access to the Undo menu item.
    private JMenuItem redoItem;  // Redo menu item.

    private JComboBox formats;

    public static final float[] SHARPEN3x3 = { // sharpening filter kernel
            0.f, -1.f,  0.f,
            -1.f,  5.f, -1.f,
            0.f, -1.f,  0.f
    };

    public static final float[] BLUR3x3 = {
            0.1f, 0.1f, 0.1f,    // low-pass filter kernel
            0.1f, 0.2f, 0.1f,
            0.1f, 0.1f, 0.1f
    };


    public ImageEnhancerWithUndoAndRedo(JMenuItem undoItem, JMenuItem redoItem) { // Version of the constructor taking 2 arguments.
        this();
        this.undoItem = undoItem;
        this.redoItem = redoItem;
        this.undoItem.setEnabled(false); // undo menu item initially grayed out
        this.redoItem.setEnabled(false); // redo menu item initially grayed out
        // end of code for initializing menu items' state.
    }

    public ImageEnhancerWithUndoAndRedo() { // Version of the constructor taking 0 arguments.
        try {
            biTemp = ImageIO.read(new File(OriginalImage));
            w = biTemp.getWidth(null);
            h = biTemp.getHeight(null);
            biOriginal = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            gOrig = biOriginal.getGraphics();
            gOrig.drawImage(biTemp, 0, 0, null);
            biWorking = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            gWorking = biWorking.getGraphics();
            gWorking.drawImage(biOriginal, 0, 0, null);
            biFiltered = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            gFiltered = biFiltered.getGraphics();

        }

        catch (IOException e) {
            System.out.println("Image could not be read: "+OriginalImage);
            System.exit(1);
        }

        // Add code to create empty stack instances for the Undo stack and the Redo stack.
        // Put your code for this here:
        undoStack = new BufferedImageStack();
        undoStack.push(biOriginal); // "original"
        redoStack = new BufferedImageStack();

        // We add a listener to this component so that it can bring up popup menus.
        MouseListener popupListener = new PopupListener();
        addMouseListener(popupListener);
    }

    public ImageEnhancerWithUndoAndRedo getImageEnhancer() {
        if(si == null) {
            si = new ImageEnhancerWithUndoAndRedo();
        }
        return si;
    }

    public BufferedImage getBufferedImage() {
        return biWorking;
    }

    public JPopupMenu getPopupMenu() {
        return popup;
    }

    public Dimension getPreferredSize() {
        return new Dimension(w, h);
    }

    public void setOpIndex(int i) {
        opIndex = i;
    }

    public void paint(Graphics g) {
        g.drawImage(biWorking, 0, 0, null);
    }

    private LookupOp getOriginalOp() {
        byte[] lut = new byte[256];
        for (int j=0; j<256; j++) {
            lut[j] = (byte)j;
        }
        ByteLookupTable blut = new ByteLookupTable(0, lut);
        return new LookupOp(blut, null);
    }

    int lastOp;
    public void filterImage() {
        if (undoStack.isEmpty()) {
            undoStack.push(biOriginal);
        }
        BufferedImage filtered = null;
        BufferedImageOp op = null;
        lastOp = opIndex;
        switch (opIndex) {
            case 0 : /* darken. */
                if (byte0==null) {
                    byte0 = new byte[256];
                    for (int j=0; j<256; j++) {
                        byte0[j] = (byte)(j*9.0 / 10.0);
                    }
                    ByteLookupTable bbyte0 = new ByteLookupTable(0, byte0);
                    op0 = new LookupOp(bbyte0, null);
                }
                op = op0;
                break;
            case 1:  /* low pass filter */
            case 2:  /* sharpen */
                float[] data = (opIndex == 1) ? BLUR3x3 : SHARPEN3x3;
                op = new ConvolveOp(new Kernel(3, 3, data),
                        ConvolveOp.EDGE_NO_OP,
                        null);
                break;

            case 3 : /* photonegative */
                if (byte3==null) {
                    byte3 = new byte[256];
                    for (int j=0; j<256; j++) {
                        byte3[j] = (byte)(256-j);
                    }
                    ByteLookupTable bbyte3 = new ByteLookupTable(0, byte3);
                    op3 = new LookupOp(bbyte3, null);
                }
                op = op3;
                break;

            case 4 : /* threshold RGB values. */
                if (byte4==null) {
                    byte4 = new byte[256];
                    for (int j=0; j<256; j++) {
                        byte4[j] = (byte)(j < 128 ? 0: 200);
                    }
                    ByteLookupTable bbyte4 = new ByteLookupTable(0, byte4);
                    op4 = new LookupOp(bbyte4, null);

                }
                op = op4;
                break;

            case 5 : /* undo */
                if (byte5==null) {
                    byte5 = new byte[256];
                    for (int j=0; j<256; j++) {
                        byte5[j] = (byte)(j < 128 ? 0: 200);
                    }
                    ByteLookupTable bbyte5 = new ByteLookupTable(0, byte5);
                    op5 = new LookupOp(bbyte5, null);
                }
                op = op5;
                break;

            case 6 : /* redo*/
                if (byte6==null) {
                    byte6 = new byte[256];
                    for (int j=0; j<256; j++) {
                        byte6[j] = (byte)(j < 128 ? 0: 200);
                    }
                    ByteLookupTable bbyte6 = new ByteLookupTable(0, byte6);
                    op6 = new LookupOp(bbyte6, null);
                }
                op = op6;
                break;

            case 7 : /* drop redo item*/
                if (byte7==null) {
                    byte7 = new byte[256];
                    for (int j=0; j<256; j++) {
                        byte7[j] = (byte)(j < 128 ? 0: 200);
                    }
                    ByteLookupTable bbyte7 = new ByteLookupTable(0, byte7);
                    op7 = new LookupOp(bbyte7, null);
                }
                op = op7;
                break;

            default:return;
        }



        // user chose (0-4) / a filter
        if(opIndex >= 0 && opIndex <= 4) {
            redoStack.clear();
            biFiltered = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            undoStack.push(biFiltered); // adding the filtered image to the stack
            op.filter(biWorking, biFiltered);
        } else if (opIndex == 5) { // user clicked undo
            redoStack.push(undoStack.pop());
            biFiltered = undoStack.peek();
            // dropping original to get undoStack size to 0
            if (undoStack.getSize() == 1) {
                undoStack.pop();
            }
        } else if (opIndex == 6) { // user clicked redo
            undoStack.push(redoStack.pop());
            biFiltered = undoStack.peek();
        } else if (opIndex == 7) {  // drop Redo item
            redoStack.pop();
            biFiltered = undoStack.peek();
        }

        // greys/ungreys redo menu option
        if (!redoStack.isEmpty()) {
            redoItem.setEnabled(true);
        } else {
            redoItem.setEnabled(false);
        }

        // greys/ungreys undo menu option
        if (undoStack.isEmpty()) {
            undoItem.setEnabled(false);
        } else {
            undoItem.setEnabled(true);
        }

        gWorking.drawImage(biFiltered, 0, 0, null); // this draws the image
        printNumberOfElementsInBothStack();
    }

    /* Returns the formats sorted alphabetically and in lower case */
    public String[] getFormats() {
        String[] formats = ImageIO.getWriterFormatNames();
        TreeSet<String> formatSet = new TreeSet<String>();
        for (String s : formats) {
            formatSet.add(s.toLowerCase());
        }
        return formatSet.toArray(new String[0]);
    }

    class PopupListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }

        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }

        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popup.show(e.getComponent(),
                        e.getX(), e.getY());
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        try {
            JComboBox cb = (JComboBox)e.getSource();
            if (cb.getActionCommand().equals("SetFilter")) {
                setOpIndex(cb.getSelectedIndex());
                filterImage();
                repaint();
            } else if (cb.getActionCommand().equals("Formats")) {
                /* Saves the filtered image in the selected format.
                 * The selected item will be the name of the format to use
                 */
                String format = (String)cb.getSelectedItem();
                /* Uses the format name to initialise the file suffix.
                 * Format names typically correspond to suffixes.
                 */
                File saveFile = new File("savedimage."+format);
                JFileChooser chooser = new JFileChooser();
                chooser.setSelectedFile(saveFile);
                int rval = chooser.showSaveDialog(cb);
                if (rval == JFileChooser.APPROVE_OPTION) {
                    saveFile = chooser.getSelectedFile();
                    /* Writes the filtered image in the selected format,
                     * to the file chosen by the user.
                     */
                    try {
                        ImageIO.write(biFiltered, format, saveFile);
                    } catch (IOException ex) {

                    }

                }
            }
        }
        catch (Exception ee) {
            JMenuItem mi = (JMenuItem)e.getSource();
            String filterCommand = mi.getText();
            Integer i = new Integer(filterCommand.substring(0,1));
            int index = i.intValue();
            System.out.println(filterCommand);
            setOpIndex(index);
            filterImage();
            repaint();
        }
    }

    public static void main(String s[]) {
        new ImageEnhancerWithUndoAndRedo().run();
    }

    public void run() {
        JFrame f = new JFrame("IMage Enhancer with Undo and Redo (Arnav Kucheriya)");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        si = new ImageEnhancerWithUndoAndRedo();

        // menu items for undo / redo / drop redo
        JMenuItem undoIt = new JMenuItem("5: Undo");
        JMenuItem redoIt = new JMenuItem("6: Redo");
        JMenuItem dropRedo = new JMenuItem("7: Drop this Redo item");
        si = new ImageEnhancerWithUndoAndRedo(undoIt, redoIt);
        undoIt.addActionListener(si);
        redoIt.addActionListener(si);
        dropRedo.addActionListener(si);

        f.add("Center", si);
        formats = new JComboBox(si.getFormats());
        formats.setActionCommand("Formats");
        formats.addActionListener(si);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Save As"));
        panel.add(formats);
        f.add("South", panel);
        f.pack();
        f.setVisible(true);

        // We create the popup menu in the following.
        popup = new JPopupMenu();

        JMenuItem menuItem = new JMenuItem("0: Darken by 10%");
        menuItem.addActionListener(si);
        popup.add(menuItem);
        menuItem = new JMenuItem("1: Render(Convolve): Decrease Resolution");
        menuItem.addActionListener(si);
        popup.add(menuItem);
        menuItem = new JMenuItem("2: Render(Convolve): Increase Resolution");
        menuItem.addActionListener(si);
        popup.add(menuItem);
        menuItem = new JMenuItem("3: PhotoNegative");
        menuItem.addActionListener(si);
        popup.add(menuItem);
        menuItem = new JMenuItem("4: RGB Thresholds at 128");
        menuItem.addActionListener(si);
        popup.add(menuItem);

        // pop up for undo and redo menu items
        popup.add(undoIt);
        popup.add(redoIt);
        // pop up for drop redo menu item
        popup.add(dropRedo);
    }

    private void printNumberOfElementsInBothStack() {
        // Uncomment this code that prints out the numbers of elements in each of the two stacks (Undo and Redo):
        System.out.println("Undo stack has " + undoStack.getSize() + " elements.");
        System.out.println("Redo stack has " + redoStack.getSize() + " elements.");
    }
}