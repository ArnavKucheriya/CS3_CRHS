
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
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

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

        labelPanel.setLayout(new GridBagLayout());  // layout manager similar to a matrix w/ more flexibility.
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

    class Box <T> {
        public T item;
        public Box(T item) {
            this.item = item;
        }
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
        Point leftMostPoint = pointsList.stream()
                .min((Point t, Point t1) -> Integer.compare(t.x, t1.x)).get();
        Point mostCCW = leftMostPoint;
        int len = pointsList.size();
        int lastIndex = 0; // remember the index of the most CCW point found (see loop) so the next iteration can start from there
        ArrayList<Point> convexHull = new ArrayList<>();
        // This loop is way easier to understand if index % len is replaced with a next() method of a list of nodes.
        // Basically, the loop goes through all the points and finds the most counterclockwise point for each point.
        // But because the method uses ArrayLists, then the last index is important to remember so each new iterations
        // searches for the most CCW point subsequent to the last index. Very analogous to a linked list, but modified to
        // work with ArrayLists.
        do {
            if (convexHull.size() > 0 && mostCCW == convexHull.get(convexHull.size() - 1)) {} // check for duplicates
            else convexHull.add(mostCCW);
            int index = ++lastIndex;
            Point mostCCWFound = pointsList.get( index% len); // resembles a next() method of a linked list
            // a search max algorithm for the most CCW point
            // could have been simplified with max() method of a stream if a LinkedList was used instead of an ArrayList as the data structure to store the points
            for (int i = 0; i < len; i++) {
                Point unknownPoint = pointsList.get((i + index) % len); // linked list like style
                if (ccw(mostCCW, mostCCWFound, unknownPoint) == -1) {
                    mostCCWFound = unknownPoint;
                    lastIndex = i;
                }
            }
            mostCCW = mostCCWFound;
        } while (mostCCW != leftMostPoint);
        return convexHull;
    }

    private ArrayList<Point> constructHull(ArrayList<Point> list) {
        // 1) if size is 1 or 2 then the hull is trivial and is a point or a line segment
        // 2) for small hulls use brute force - I used 5 points or less
        // 3) follow algorithm from lecture
        // a) split the points in half which are already sorted by x values
        // b) recursively construct the left hull then the right hull
        // c) merge the two hulls based on the upper/lower tangent lines - use helper method below
        if (list.size() <= 2)
            return list;

        else if (list.size() <= 5)
            return bruteForce(list);

        int mid = list.size() / 2;
        ArrayList<Point> leftHull = constructHull(new ArrayList<>(list.subList(0, mid)));
        ArrayList<Point> rightHull = constructHull(new ArrayList<>(list.subList(mid, list.size())));
        return mergeHulls(leftHull, rightHull);
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
        Point leftPoint = left.stream()
                .max((Point t, Point t1) -> Integer.compare(t.x, t1.x)).get();
        Point righPoint = right.stream()
                .min((Point t, Point t1) -> Integer.compare(t.x, t1.x)).get();
        Line lowerTangent = searchTangent(left, right, leftPoint, righPoint, false);
        Line upperTangent = searchTangent(left, right, leftPoint, righPoint, true);
        return traceHull(left, right, upperTangent, lowerTangent);
    }

    /**
     * Search for the upper or lower tangent line between two convex hulls. Notice how a new CCW path is created
     * by the upper tangent and the lower. Therefore, tracing the new path in the bigger hull using as connections
     * the tangent lines will naturally remove the points that are not part of the new hull. Also, this method
     * could be much faster, readable and efficient if a LinkedList were to use instead of an ArrayList, because
     * this method is inserting the upper and lower tangent points into the new hull as you would insert
     * a node into a linked list.
     * @param left
     * @param right
     * @param upperTangent
     * @param lowerTangent
     * @return
     */
    private static ArrayList<Point> traceHull(ArrayList<Point> left, ArrayList<Point> right, Line upperTangent, Line lowerTangent) {
        ArrayList<Point> convexHull = new ArrayList<>();
        convexHull.add(upperTangent.a);
        int index = left.indexOf(upperTangent.a);

        while (left.get(index) != lowerTangent.a) {
            convexHull.add(left.get(index));
            index = (index + 1) % left.size();
        }

        convexHull.add(lowerTangent.a);
        index = right.indexOf(lowerTangent.b);

        while (right.get(index) != upperTangent.b) {
            convexHull.add(right.get(index));
            index = (index + 1) % right.size();
        }

        convexHull.add(upperTangent.b);
        return convexHull;
    }

    /**
     * Search a tangent line between two convex hulls. The algorithm to find the lower and upper tangent lines is identical but with sign flipped.
     * Finding the tangent line is done by recursively finding exterior points until no more CCW or CW points are found in respect to the seed points of the recursion.
     * @param left
     * @param right
     * @param leftPoint is the point from the right hull that is the leftmost point.
     * @param rightPoint the rightmost point from the left hull.
     * @param upper wether searching for upper or lower tangent line.
     * @return
     */
    private Line searchTangent(ArrayList<Point> left, ArrayList<Point> right, Point leftPoint, Point rightPoint, boolean upper) {
        int sign = upper ? 1 : -1;
        Point leftUpper = left.stream()
                .reduce(leftPoint, (Point mostExtremeFound, Point unknown) -> {
                    if (ccw(rightPoint, mostExtremeFound, unknown) == -sign)
                        return unknown;
                    return mostExtremeFound;
                });

        Point rightUpper = right.stream()
                .reduce(rightPoint, (Point mostExtremeFound, Point unknown) -> {
                    if (ccw(leftPoint, mostExtremeFound, unknown) == sign)
                        return unknown;
                    return mostExtremeFound;
                });

        // check if the new points are extreme points of the hulls, if not, reccur to find the extreme points.
        if (left.stream().anyMatch((Point unknown) -> ccw(rightUpper, leftUpper, unknown) == -sign) ||
                right.stream().anyMatch((Point unknown) -> ccw(leftUpper, rightUpper, unknown) == sign)) {
            return searchTangent(left, right, leftUpper, rightUpper, upper);
        }
        return new Line(leftUpper, rightUpper);
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

    public static String getPoint(Point p) {
        return "(" + p.x + ", " + p.y + ")";
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