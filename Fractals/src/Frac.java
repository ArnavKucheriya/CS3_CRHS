import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Frac extends JFrame {

    public Frac() {
        setTitle("Fractals");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        //centers the frame
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) (screensize.getWidth() / 1), (int) (screensize.getHeight() / 1));
        initGUI();
        setBounds((int) (.5 * (screensize.width - getWidth())), (int) (.5 * (screensize.height - getHeight())), getWidth(), getHeight());
    }

    public void initGUI() {
        add(new DrawingPanel());
    }

    class DrawingPanel extends JPanel {
        int level;
        String FractalType;
        Turtle maybe;

        // add a constructor
        public DrawingPanel() {
            setLayout(null);
            setSize(Frac.this.getSize());
            add(addButton("Square Tile", 0));
            add(addButton("Tree", getWidth() / 2));
            add(addButton("Julia", getWidth() / 4 * 3));
            maybe = new Turtle();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // add code here and call helper methods
            if (FractalType != null) {
                if (FractalType.equals("Square Tile")) {
                    maybe.putPenDown();
                    recurseSquareTile(g, getWidth()-1,getHeight()-1);
                    maybe.pickPenUp();
                }
                if (FractalType.equals("Tree")) {
                    maybe.putPenDown();
                    Tree fractal = new Tree();
                    fractal.paint(g);
                    maybe.pickPenUp();
                }
                if (FractalType.equals("Julia")) {
                    maybe.putPenDown();
                    JuliaFractal fractal = new JuliaFractal();
                    maybe.pickPenUp();
                }


            }

        }

        private void recurseSquareTile(Graphics yes, int index1,int index2) {
            if (!maybe.getPenPosition()) return;
            if (index1 < 0)return;

            for (int j=0; j<index2; ++j) {
                int x = index1 & (j - 2*(index1^j) + j) & index1;
                x %= 256;
                x = Math.abs(x);
                yes.setColor(new Color(x,x,x));
                yes.drawRect(index1, j, 1, 1);
            }
            recurseSquareTile(yes,index1-1,index2);
        }

        private JButton addButton(String title, int x) {
            JButton yes = new JButton(title);
            yes.setVerticalTextPosition(SwingConstants.CENTER);
            yes.setBounds(x, Frac.this.getHeight() / 9 * 7, Frac.this.getWidth() / 4, Frac.this.getHeight() / 6);
            yes.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    if (yes.getText().equals("Square Tile")) {
                        FractalType = "Square Tile";
                        repaint();
                    }
                    if (yes.getText().equals("Tree")) {
                        FractalType = "Tree";
                        repaint();
                    }
                    if (yes.getText().equals("Julia")) {
                        FractalType = "Julia";
                        repaint();
                    }


                }
            });
            return yes;
        }


    }

    public static void main(String[] args) {
        new Frac();
    }
}





