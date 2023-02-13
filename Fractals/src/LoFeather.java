import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class FractalJPanel extends JPanel {
    private Color color; // stores color used to draw fractal
    private int level; // stores current level of fractal

    private static final int WIDTH = 400; // defines width of JPanel
    private static final int HEIGHT = 400; // defines height of JPanel

    public FractalJPanel(int currentLevel) {
        color = Color.BLUE;//from ww  w .  java 2 s.c o  m
        level = currentLevel; // set initial fractal level
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public void drawFractal(int level, int xA, int yA, int xB, int yB, Graphics g) {
        if (level == 0)
            g.drawLine(xA, yA, xB, yB);
        else {
            // calculate midpoint between (xA, yA) and (xB, yB)
            int xC = (xA + xB) / 2;
            int yC = (yA + yB) / 2;

            // calculate the fourth point (xD, yD) which forms an
            // isosceles right triangle between (xA, yA) and (xC, yC)
            // where the right angle is at (xD, yD)
            int xD = xA + (xC - xA) / 2 - (yC - yA) / 2;
            int yD = yA + (yC - yA) / 2 + (xC - xA) / 2;

            // recursively draw the Fractal
            drawFractal(level - 1, xD, yD, xA, yA, g);
            drawFractal(level - 1, xD, yD, xC, yC, g);
            drawFractal(level - 1, xD, yD, xB, yB, g);
        }
    }

    // start the drawing of fractal
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        drawFractal(level, 100, 90, 290, 200, g);
    }

    public void setColor(Color c) {
        color = c;
    }

    public void setLevel(int currentLevel) {
        level = currentLevel;
    }

    public int getLevel() {
        return level;
    }
}

public class LoFeather extends JFrame {
    private static final int WIDTH = 400; // define width of GUI
    private static final int HEIGHT = 480; // define height of GUI
    private static final int MIN_LEVEL = 0, MAX_LEVEL = 15;

    private JButton changeColorJButton, increaseLevelJButton,
            decreaseLevelJButton;
    private JLabel levelJLabel;
    private FractalJPanel drawSpace;
    private JPanel mainJPanel, controlJPanel;

    public LoFeather() {
        controlJPanel = new JPanel();
        controlJPanel.setLayout(new FlowLayout());

        changeColorJButton = new JButton("Color");
        controlJPanel.add(changeColorJButton);
        changeColorJButton.addActionListener(e -> {
            Color color = JColorChooser.showDialog(LoFeather.this, "Choose a color",
                    Color.BLUE);

            // set default color, if no color is returned
            if (color == null)
                color = Color.BLUE;

            drawSpace.setColor(color);
        });

        // set up decrease level button to add to control panel and
        // register listener
        decreaseLevelJButton = new JButton("Decrease Level");
        controlJPanel.add(decreaseLevelJButton);
        decreaseLevelJButton.addActionListener(e -> {
                    int level = drawSpace.getLevel();
                    --level;

                    // modify level if possible
                    if ((level >= MIN_LEVEL) && (level <= MAX_LEVEL)) {
                        levelJLabel.setText("Level: " + level);
                        drawSpace.setLevel(level);
                        repaint();
                    }
                }

        );

        // set up increase level button to add to control panel
        // and register listener
        increaseLevelJButton = new JButton("Increase Level");
        controlJPanel.add(increaseLevelJButton);
        increaseLevelJButton.addActionListener(e -> {
            int level = drawSpace.getLevel();
            ++level;

            // modify level if possible
            if ((level >= MIN_LEVEL) && (level <= MAX_LEVEL)) {
                levelJLabel.setText("Level: " + level);
                drawSpace.setLevel(level);
                repaint();

            }
        });

        // set up levelJLabel to add to controlJPanel
        levelJLabel = new JLabel("Level: 0");
        controlJPanel.add(levelJLabel);

        drawSpace = new FractalJPanel(0);

        // create mainJPanel to contain controlJPanel and drawSpace
        mainJPanel = new JPanel();
        mainJPanel.add(controlJPanel);
        mainJPanel.add(drawSpace);

        add(mainJPanel); // add JPanel to JFrame

        setSize(WIDTH, HEIGHT); // set size of JFrame
        setVisible(true); // display JFrame
    }

    public static void main(String[] args) {
        LoFeather demo = new LoFeather();
        demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}