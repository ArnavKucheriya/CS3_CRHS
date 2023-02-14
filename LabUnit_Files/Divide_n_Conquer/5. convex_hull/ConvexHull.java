
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author bryce
 */
public class ConvexHull extends JFrame {

    JPanel drawingPanel = new DrawingPanel();
    JPanel buttonPanel = new JPanel();
    JButton convexHullButton = new JButton("Draw Hull");
    JButton bruteForceButton = new JButton("Brute Force");
    JButton convexHullFillButton = new JButton("Fill Hull");
    JButton clearButton = new JButton("Clear");
    JLabel xLabel = new JLabel("X: ");   // to show mouse location
    JLabel yLabel = new JLabel("Y: ");
    JPanel labelPanel = new JPanel();   // to hold the labels
    final int SIZE = 500;       // size of drawing panel
    static boolean filled, draw;
    static ArrayList<Point> pointsList = new ArrayList<>(); // holds all points
    static ArrayList<Point> convexList = new ArrayList<>(); // holds the points to construct the hull

    public ConvexHull() {
        initGUI();
        setTitle("Algorithms And Data Structures");
        pack();	// tell the layout manager to organize the components optimally
        setVisible(true);   // must have this line or you're components will only be in memory and can't be seen
        setDefaultCloseOperation(EXIT_ON_CLOSE);    // closes the application
        setResizable(false);        // disables resizing the window
        setLocationRelativeTo(null);    // centers relative to the screen
    }

    private void initGUI() {
        TitleLabel titleLabel = new TitleLabel("Convex Hull");   // add the title
        add(titleLabel, BorderLayout.PAGE_START);

        drawingPanel.setPreferredSize(new Dimension(SIZE, SIZE)); // adds the panel to draw on
        drawingPanel.setBackground(Color.white);
        drawingPanel.addMouseMotionListener(new MouseMotionAdapter() {  // adds a listener for mouse movements
            @Override
            public void mouseMoved(MouseEvent me) {
                mouseMovedMotion(me);
            }
        });
        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawingPanelMousePressed(e);
            }
        });
        add(drawingPanel, BorderLayout.CENTER);	// flow layout the default for panels

        loadButtonPanel();  // add all of the buttons to the bottom and the labels for the mouse's x and y coordinates

    }

    private void loadButtonPanel() {
        add(buttonPanel, BorderLayout.PAGE_END);    // adds a button panel at the bottom for all the buttons
        int w = 110, h = 50;
        convexHullButton.setPreferredSize(new Dimension(w, h));
        convexHullButton.addActionListener(this::convexHullButtonActionPerformed);  // adds listener for the button and calls the method

        convexHullFillButton.setPreferredSize(new Dimension(w, h));
        convexHullFillButton.addActionListener(this::convexHullButtonActionPerformed);  // adds listener for the button and calls the method

        bruteForceButton.setPreferredSize(new Dimension(w, h));
        bruteForceButton.addActionListener(this::convexHullButtonActionPerformed);  // adds listener for the button and calls the method

        clearButton.setPreferredSize(new Dimension(w, h));
        clearButton.addActionListener(this::convexHullButtonActionPerformed);

        labelPanel.setLayout(new GridBagLayout());  // layout manager similar to a matrix w/ more flexiblity
        //labelPanel.setPreferredSize(new Dimension(100, 100));
        Font f = new Font(Font.MONOSPACED, Font.BOLD, 14);
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.fill = GridBagConstraints.HORIZONTAL;
        xLabel.setPreferredSize(new Dimension(100, 20));
        xLabel.setForeground(Color.blue);
        xLabel.setFont(f);
        labelPanel.add(xLabel, gc);
        gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.fill = GridBagConstraints.HORIZONTAL;
        yLabel.setPreferredSize(new Dimension(100, 20));
        yLabel.setForeground(Color.blue);
        yLabel.setFont(f);
        labelPanel.add(yLabel, gc);

        buttonPanel.add(labelPanel);
        buttonPanel.add(convexHullButton);
        buttonPanel.add(convexHullFillButton);
        buttonPanel.add(bruteForceButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.PAGE_END);    // adds the button panel to the bottom

    }

    /**
     * Method is invoked anytime a button is clicked.
     *
     * @param e
     */
    private void convexHullButtonActionPerformed(ActionEvent e) {
        if (pointsList.size() < 2) {
            return;
        }
        JButton temp = (JButton) e.getSource();
        switch (temp.getActionCommand()) {
            case "Brute Force":
                convexList = bruteForce(pointsList);
                filled = true;
                draw = false;
                break;
            case "Fill Hull":
                Collections.sort(pointsList, (Point t, Point t1) -> Integer.compare(t.x, t1.x));
                convexList = constructHull(pointsList);
                filled = true;
                draw = false;
                break;
            case "Draw Hull":
                Collections.sort(pointsList, (Point t, Point t1) -> Integer.compare(t.x, t1.x));
                convexList = constructHull(pointsList);
                filled = false;
                draw = true;
                break;
            case "Clear":
                pointsList.clear();
                convexList.clear();
                filled = false;
                draw = false;
                break;
        }
        repaint();
    }

    /**
     * Updates x/y labels to display the cursor's location based on the Cartesian plane.
     *
     * @param e
     */
    private void mouseMovedMotion(MouseEvent e) {
        xLabel.setText("X " + e.getX());
        yLabel.setText("Y " + (drawingPanel.getHeight() - e.getY()));
    }

    /**
     * Adds the point associated w/ the mouse click to a list to be processed later.
     *
     * @param me
     */
    private void drawingPanelMousePressed(MouseEvent me) {
        Point point = new Point(me.getX(), drawingPanel.getHeight() - me.getY());
        for (Point p : pointsList) {
            if (Math.hypot(point.x - p.x, point.y - p.y) < 4) { // don't add points that are too close together
                return;
            }
        }
        pointsList.add(point);
        repaint();
    }

    /**
     * brute force algorithm from lecture - basically calculate all extreme edges and then sort in
     * counterclockwise order
     *
     * @param pointsList
     * @return
     */
    private ArrayList<Point> bruteForce(ArrayList<Point> pointsList) {
        // 1) first add all of the extreme edges to a list
        // 2) then match up all the endpoints to make a polygon
        // 3) lastly return all the points of the convex hull in a counterclockwise order
        return null;
    }

    private ArrayList<Point> constructHull(ArrayList<Point> list) {
        // 1) if size is 1 or 2 then the hull is trivial and is a point or a line segment
        // 2) for small hulls use brute force - I used 5 points or less
        // 3) follow algorithm from lecture
        // a) split the points in half which are already sorted by x values
        // b) recursively construct the left hull then the right hull
        // c) merge the two hulls based on the upper/lower tangent lines - use helper method below
        return null;
    }

    /**
     * Merge two convex hulls by the algorithm discussed in lecture.
     *
     * @param left
     * @param right
     * @return
     */
    private ArrayList<Point> mergeHulls(ArrayList<Point> left, ArrayList<Point> right) {
        // 1) find the rightmost point of left and leftmost point of right
        // 2) find lower tangent points
        // 3) find upper tangent points
        // 4) verify these four points are correct before preceeding
        // 5) start at the left upper tangent point and add all points moving in a counterclockwise direction
        // to a list until the left lower tangent is reached. Similarly do the same starting at the right
        // bottom tangent to the right upper tangent.
        // 6) return the points from the newly constructed hull from part 5.
        return null;
    }

    /**
     * Ascertains the rotational direction of 3 points based on their slopes.
     *
     * @param p
     * @param q
     * @param r
     * @return
     */
    public static int ccw(Point p, Point q, Point r) {
        double val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        return (val == 0) ? 0 : val > 0 ? 1 : -1; // collinear, clockwise, counterclockwise respectively
    }

    public static void main(String[] args) {
        new ConvexHull();
    }

    class DrawingPanel extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2D = (Graphics2D) g;
            super.paintComponent(g2D);
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setStroke(new BasicStroke(2));
            g2D.setPaint(Color.BLUE);
            ConvexHull.pointsList.stream().forEach((e) -> {
                g2D.fill(new Ellipse2D.Double(e.getX() - 2, drawingPanel.getHeight() - e.getY() - 2, 4, 4));
            });
            if (convexList.size() < 2) {
                return;
            }
            g2D.setPaint(Color.RED);
            if (convexList.size() == 2) {
                g2D.drawLine((int) pointsList.get(0).getX(), drawingPanel.getHeight() - (int) pointsList.get(0).getY(),
                        (int) pointsList.get(1).getX(), drawingPanel.getHeight() - (int) pointsList.get(1).getY());
                return;
            }
            int i = 0;
            GeneralPath myPolygon = new GeneralPath();
            for (Point item : convexList) {
                if (i++ == 0) {
                    myPolygon.moveTo((float) item.getX(), drawingPanel.getHeight() - (float) item.getY());
                } else {
                    myPolygon.lineTo((float) item.getX(), drawingPanel.getHeight() - (float) item.getY());
                }
            }
            myPolygon.closePath();
            g2D.setPaint(new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)));
            //g2D.setPaint(Color.red);
            if (filled) {
                g2D.fill(myPolygon);
            } else if (draw) {
                g2D.draw(myPolygon);
            }
            g2D.dispose();
        }
    }
}
// optional class that represents a line segment

class Line {

    Point a;
    Point b;

    public Line(Point a, Point b) {
        this.a = a;
        this.b = b;
    }
}
