
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;

public class TrominoTiles extends JFrame {

    DrawingPanel drawingPanel;
    JPanel buttonPanel = new JPanel();
    JButton clearButton = new JButton("Clear");
    JButton solveButton = new JButton("Solve");
    JButton sizeButton = new JButton("Size");
    final int TILESIZE = 50;
    static int POWER = 8;
    int SIZE = Math.max(680, 300 + TILESIZE * POWER);
    static Tromino[][] m;
    Polygon draggedPolygon;
    int xOffset = 0;
    int yOffset = 0;
    int xmargin = 200;
    int ymargin = 50;
    Poly whiteSquare;
    final int center = 3;
    int tromino = 0;
    boolean whiteSelected = false;
    TitleLabel titleLabel = new TitleLabel("Tromino Tiles");   // add the title
    boolean solved = false;

    enum TrominoTile {
        RED, GREEN, PINK, YELLOW
    };

    {
        int[] xW = {ymargin * 2, ymargin * 2 + TILESIZE, ymargin * 2 + TILESIZE, ymargin * 2};
        int[] yW = {ymargin * 2, ymargin * 2, ymargin * 2 + TILESIZE, ymargin * 2 + TILESIZE};
        whiteSquare = new Poly(new Polygon(xW, yW, 4), Color.white, 4);
        whiteSquare.ary = new int[]{-1};
        drawingPanel = new DrawingPanel();
    }

    public TrominoTiles() {
        initGUI();
        setTitle("Algorithms And Data Structures");
        pack();	// tell the layout manager to organize the components optimally
        setVisible(true);   // must have this line or you're components will only be in memory and can't be seen
        setDefaultCloseOperation(EXIT_ON_CLOSE);    // closes the application
        setResizable(false);        // disables resizing the window
        setLocationRelativeTo(null);    // centers relative to the screen
    }

    private void initGUI() {
        drawingPanel.removeAll();
        buttonPanel.removeAll();
        getContentPane().removeAll();
        createBoard();
        add(drawingPanel, BorderLayout.CENTER);	// flow layout the default for panels

        add(buttonPanel, BorderLayout.PAGE_END);    // adds a button panel at the bottom for all the buttons
        Dimension d = new Dimension(120, 40);
        solveButton.setPreferredSize(d);
        solveButton.addActionListener(this::solveButtonActionPerformed);  // adds listener for the button and calls the method
        clearButton.setPreferredSize(d);
        clearButton.addActionListener(this::solveButtonActionPerformed);  // adds listener for the button and calls the method
        sizeButton.setPreferredSize(d);
        sizeButton.addActionListener(this::solveButtonActionPerformed);  // adds listener for the button and calls the method

        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(sizeButton);
        revalidate();
        pack();
        setLocationRelativeTo(null);
    }

    private void createBoard() {
        SIZE = Math.max(680, 300 + TILESIZE * POWER);
        m = new Tromino[POWER][POWER];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                Rectangle r = new Rectangle(xmargin + j * TILESIZE, ymargin + i * TILESIZE, TILESIZE, TILESIZE);
                m[i][j] = new Tromino(i, j, r);
            }
        }
        getContentPane().remove(titleLabel);
        titleLabel = new TitleLabel("Tromino Tiles");   // add the title
        add(titleLabel, BorderLayout.PAGE_START);

        drawingPanel.setPreferredSize(new Dimension(SIZE, SIZE - 220)); // adds the panel to draw on
        drawingPanel.setBackground(Color.white);
        drawingPanel.addMouseMotionListener(new MouseMotionAdapter() {  // adds a listener for mouse movements
            @Override
            public void mouseDragged(MouseEvent me) {
                mouseDraggedMotion(me);
            }
        });
        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawingPanelMousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                drawingPanelMouseReleased(e);
            }
        });
        drawingPanel.revalidate();
        pack();
        setLocationRelativeTo(null);
    }

    private void clear() {
        draggedPolygon = null;
        whiteSelected = false;
        for (Tromino[] m1 : m) {
            for (Tromino m11 : m1) {
                m11.filled = false;
                m11.c = Color.blue;
                m11.walls = new boolean[]{true, true, true, true};
            }
        }
        solved = false;
    }

    private void solveButtonActionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Clear")) {
            clear();
        } else if (e.getActionCommand().equals("Solve")) {
            if (!whiteSelected) {
                return;
            }
            clear();
            int r = whiteSquare.ary[0];
            int c = whiteSquare.ary[1];
            m[r][c].c = Color.white;
            m[r][c].filled = true;
            divideAndConquer(0, 0, POWER, c, r);
            solved = true;
        } else if (e.getActionCommand().equals("Size")) {
            changeSize();
        } else if (e.getActionCommand().equals("Animate")) {
            ;
        }
        repaint();
    }

    private int getQuadrant(int c, int r, int size) {
        r = r % size;
        c = c % size;
        return r < size / 2 && c < size / 2 ? 1 : r >= size / 2 && c < size / 2 ? 2 : r >= size / 2 && c >= size / 2 ? 3 : 4;
    }

    private void divideAndConquer(int rStart, int cStart, int len, int xHole, int yHole) {
        // base case
        if (len == 2) {
            if (m[rStart][cStart].filled) {
                placeTromino(rStart, cStart, TrominoTile.YELLOW);
            } else if (m[rStart + 1][cStart].filled) {
                placeTromino(rStart, cStart, TrominoTile.PINK);
            } else if (m[rStart][cStart + 1].filled) {
                placeTromino(rStart, cStart, TrominoTile.GREEN);
            } else if (m[rStart + 1][cStart + 1].filled) {
                placeTromino(rStart, cStart, TrominoTile.RED);
            }
        } else {
            // first place a tromino tile in the appropriate spot then make 4 recursive calls
            int rCenter = rStart + len / 2 - 1;
            int cCenter = cStart + len / 2 - 1;
            switch (getQuadrant(xHole, yHole, len)) {
                case 1:
                    placeTromino(rCenter, cCenter, TrominoTile.YELLOW);
                    divideAndConquer(rStart, cStart, len / 2, xHole, yHole); // top left
                    divideAndConquer(rStart, cStart + len / 2, len / 2, cCenter + 1, rCenter); // top right
                    divideAndConquer(rStart + len / 2, cStart, len / 2, cCenter, rCenter + 1); // bot left
                    divideAndConquer(rStart + len / 2, cStart + len / 2, len / 2, cCenter + 1, rCenter + 1); // bot right
                    break;
                case 2:
                    placeTromino(rCenter, cCenter, TrominoTile.PINK);
                    divideAndConquer(rStart, cStart, len / 2, cCenter, rCenter); // top left
                    divideAndConquer(rStart, cStart + len / 2, len / 2, cCenter + 1, rCenter); // top right
                    divideAndConquer(rStart + len / 2, cStart, len / 2, xHole, yHole); // bot left
                    divideAndConquer(rStart + len / 2, cStart + len / 2, len / 2, cCenter + 1, rCenter + 1); // bot right
                    break;
                case 3:
                    placeTromino(rCenter, cCenter, TrominoTile.RED);
                    divideAndConquer(rStart, cStart, len / 2, cCenter, rCenter); // top left
                    divideAndConquer(rStart, cStart + len / 2, len / 2, cCenter + 1, rCenter); // top right
                    divideAndConquer(rStart + len / 2, cStart, len / 2, cCenter, rCenter + 1); // bot left
                    divideAndConquer(rStart + len / 2, cStart + len / 2, len / 2, xHole, yHole); // bot right
                    break;
                case 4:
                    placeTromino(rCenter, cCenter, TrominoTile.GREEN);
                    divideAndConquer(rStart, cStart, len / 2, cCenter, rCenter); // top left
                    divideAndConquer(rStart, cStart + len / 2, len / 2, xHole, yHole); // top right
                    divideAndConquer(rStart + len / 2, cStart, len / 2, cCenter, rCenter + 1); // bot left
                    divideAndConquer(rStart + len / 2, cStart + len / 2, len / 2, cCenter + 1, rCenter + 1); // bot right
                    break;
            }
        }
    }

    private void placeTromino(int r, int c, TrominoTile t) {
        switch (t) {
            case RED:
                m[r][c].filled = true;
                m[r][c].c = Color.red;
                m[r + 1][c].filled = true;
                m[r + 1][c].c = Color.red;
                m[r][c + 1].filled = true;
                m[r][c + 1].c = Color.red;
                m[r][c + 1].walls[Tromino.LEFT] = false;
                m[r + 1][c].walls[Tromino.TOP] = false;
                break;
            case GREEN:
                m[r][c].filled = true;
                m[r][c].c = Color.GREEN;
                m[r + 1][c].filled = true;
                m[r + 1][c].c = Color.GREEN;
                m[r + 1][c + 1].filled = true;
                m[r + 1][c + 1].c = Color.GREEN;
                m[r + 1][c].walls[Tromino.TOP] = false;
                m[r + 1][c + 1].walls[Tromino.LEFT] = false;
                break;
            case PINK:
                m[r][c].filled = true;
                m[r][c].c = Color.MAGENTA;
                m[r][c + 1].filled = true;
                m[r][c + 1].c = Color.MAGENTA;
                m[r + 1][c + 1].filled = true;
                m[r + 1][c + 1].c = Color.MAGENTA;
                m[r + 1][c + 1].walls[Tromino.TOP] = false;
                m[r][c + 1].walls[Tromino.LEFT] = false;
                break;
            case YELLOW:
                m[r][c + 1].filled = true;
                m[r][c + 1].c = Color.YELLOW;
                m[r + 1][c].filled = true;
                m[r + 1][c].c = Color.YELLOW;
                m[r + 1][c + 1].filled = true;
                m[r + 1][c + 1].c = Color.YELLOW;
                m[r + 1][c + 1].walls[Tromino.TOP] = false;
                m[r + 1][c + 1].walls[Tromino.LEFT] = false;
        }
    }

    private void mouseDraggedMotion(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (draggedPolygon != null && !solved) {
            int[] xP = draggedPolygon.xpoints;
            int[] yP = draggedPolygon.ypoints;
            for (int i = 0; i < xP.length; i++) {
                xP[i] = xP[i] + (x - xOffset);
                yP[i] = yP[i] + (y - yOffset);
            }
            xOffset = x;
            yOffset = y;
        }
        repaint();
    }

    private void drawingPanelMousePressed(MouseEvent e) {
        if (solved) {
            return;
        }
        Point p = e.getPoint();
        int i = 0;
        for (Polygon polygon : drawingPanel.polygons) {
            if (polygon.contains(p)) {
                if (i == 4 && whiteSelected) {
                    return;
                } else if (i != 4 && !whiteSelected) {
                    return;
                }
                tromino = i;
                draggedPolygon = new Polygon(polygon.xpoints.clone(), polygon.ypoints.clone(), polygon.xpoints.length);
                Color t = drawingPanel.colors[i];
                int r = t.getRed();
                int g = t.getGreen();
                int b = t.getBlue();
                drawingPanel.c = new Color(r - 15 < 0 ? 0 : r - 15, g - 15 < 0 ? 0 : g - 15, b - 15 < 0 ? 0 : b - 15);
                xOffset = p.x;
                yOffset = p.y;
                break;
            }
            i++;
        }

        for (i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j].r.contains(p) && m[i][j].filled) {
                    if (m[i][j].c.equals(Color.white)) {
                        return;
                    }
                    Poly temp = m[i][j].curr;
                    if (temp == null) {
                        break;
                    }
                    tromino = temp.tromino;
                    Color t = temp.c;
                    int r = t.getRed();
                    int g = t.getGreen();
                    int b = t.getBlue();
                    for (int k = 0; k < temp.ary.length / 2; k++) {
                        m[temp.ary[k * 2]][temp.ary[k * 2 + 1]].c = Color.blue;
                        m[temp.ary[k * 2]][temp.ary[k * 2 + 1]].filled = false;
                        m[temp.ary[k * 2]][temp.ary[k * 2 + 1]].walls[Tromino.LEFT] = true;
                        m[temp.ary[k * 2]][temp.ary[k * 2 + 1]].walls[Tromino.TOP] = true;
                    }
                    draggedPolygon = temp.p;
                    drawingPanel.c = new Color(r - 15 < 0 ? 0 : r - 15, g - 15 < 0 ? 0 : g - 15, b - 15 < 0 ? 0 : b - 15);
                    xOffset = p.x;
                    yOffset = p.y;
                    break;
                }
            }
        }
        repaint();
    }

    private void drawingPanelMouseReleased(MouseEvent e) {
        if (draggedPolygon == null || solved) {
            return;
        }
        int centerX = draggedPolygon.xpoints[tromino == 4 ? 0 : center];
        int centerY = draggedPolygon.ypoints[tromino == 4 ? 0 : center];
        Point p = new Point(centerX, centerY);
        outer:
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if ((m[i].length - 1 == j || m.length - 1 == i) && tromino != 4) {
                    continue;
                }
                if (m[i][j].r.contains(p)) {
                    boolean update = false;
                    switch (tromino) {
                        case 0:
                            if (!m[i][j].filled && !m[i][j + 1].filled && !m[i + 1][j].filled) {
                                Poly add = new Poly(draggedPolygon, Color.red, 0);
                                add.ary = new int[]{i, j, i, j + 1, i + 1, j};
                                update = true;
                                m[i][j].filled = true;
                                m[i][j].c = Color.red;
                                m[i][j].curr = add;
                                m[i][j].curr = add;
                                m[i][j + 1].filled = true;
                                m[i][j + 1].c = Color.red;
                                m[i][j + 1].curr = add;
                                m[i][j + 1].walls[Tromino.LEFT] = false;
                                m[i + 1][j].filled = true;
                                m[i + 1][j].c = Color.red;
                                m[i + 1][j].curr = add;
                                m[i + 1][j].walls[Tromino.TOP] = false;

                            }
                            break;
                        case 1:
                            if (!m[i][j].filled && !m[i + 1][j + 1].filled && !m[i + 1][j].filled) {
                                Poly add = new Poly(draggedPolygon, Color.green, 1);
                                add.ary = new int[]{i, j, i + 1, j + 1, i + 1, j};
                                update = true;
                                m[i][j].filled = true;
                                m[i][j].c = Color.green;
                                m[i][j].curr = add;
                                m[i + 1][j + 1].filled = true;
                                m[i + 1][j + 1].c = Color.green;
                                m[i + 1][j + 1].curr = add;
                                m[i + 1][j].filled = true;
                                m[i + 1][j].c = Color.green;
                                m[i + 1][j].curr = add;
                                m[i + 1][j].walls[Tromino.TOP] = false;
                                m[i + 1][j + 1].walls[Tromino.LEFT] = false;

                            }
                            break;
                        case 2:
                            if (!m[i + 1][j + 1].filled && !m[i][j + 1].filled && !m[i + 1][j].filled) {
                                Poly add = new Poly(draggedPolygon, Color.yellow, 2);
                                add.ary = new int[]{i + 1, j + 1, i, j + 1, i + 1, j};
                                update = true;
                                m[i + 1][j + 1].filled = true;
                                m[i + 1][j + 1].c = Color.yellow;
                                m[i + 1][j + 1].curr = add;
                                m[i][j + 1].filled = true;
                                m[i][j + 1].c = Color.yellow;
                                m[i][j + 1].curr = add;
                                m[i + 1][j].filled = true;
                                m[i + 1][j].c = Color.yellow;
                                m[i + 1][j].curr = add;
                                m[i + 1][j + 1].walls[Tromino.TOP] = false;
                                m[i + 1][j + 1].walls[Tromino.LEFT] = false;
                            }
                            break;
                        case 3:
                            if (!m[i][j].filled && !m[i][j + 1].filled && !m[i + 1][j + 1].filled) {
                                Poly add = new Poly(draggedPolygon, Color.magenta, 3);
                                add.ary = new int[]{i, j, i, j + 1, i + 1, j + 1};
                                update = true;
                                m[i][j].filled = true;
                                m[i][j].c = Color.magenta;
                                m[i][j].curr = add;
                                m[i][j + 1].filled = true;
                                m[i][j + 1].c = Color.magenta;
                                m[i][j + 1].curr = add;
                                m[i + 1][j + 1].filled = true;
                                m[i + 1][j + 1].c = Color.magenta;
                                m[i + 1][j + 1].curr = add;
                                m[i + 1][j + 1].walls[Tromino.TOP] = false;
                                m[i][j + 1].walls[Tromino.LEFT] = false;
                            }
                            break;
                        case 4:
                            if (!m[i][j].filled) {
                                Poly add = new Poly(draggedPolygon, Color.white, 4);
                                add.ary = new int[]{i, j};
                                whiteSquare.ary = new int[]{i, j};
                                m[i][j].filled = true;
                                m[i][j].c = Color.white;
                                m[i][j].curr = add;
                                whiteSelected = true;
                            }
                    }
                    if (update) {
                        int xDif = (int) m[i][j].r.getMaxX() - centerX;
                        int yDif = (int) m[i][j].r.getMaxY() - centerY;
                        Poly pp = m[tromino == 2 ? i + 1 : i][j].curr;
                        for (int k = 0; k < pp.p.xpoints.length; k++) {
                            pp.p.xpoints[k] = pp.p.xpoints[k] + xDif - 1;
                            pp.p.ypoints[k] = pp.p.ypoints[k] + yDif - 1;
                        }
                    }
                    break outer;
                }
            }
        }
        draggedPolygon = null;
        repaint();
    }

    private void changeSize() {
        OptionsDialog dialog = new OptionsDialog();
        dialog.setModal(true);
        dialog.setResizable(false);
        dialog.pack();
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        dialog.setBounds((int) (.5 * (screensize.width - dialog.getWidth())), (int) (.5 * (screensize.height - dialog.getHeight())), dialog.getWidth(), dialog.getHeight());
        dialog.setVisible(true);
        if (dialog.isCancelled()) {
            POWER = dialog.getTrominoSize();
            createBoard();
            dialog.setCancelled(false);
            setEnabled(true);
            clearButton.doClick();
        }
        solved = false;
    }

    public static void main(String[] args) {
        new TrominoTiles();
    }

    class DrawingPanel extends JPanel {

        int ymargin2 = 30;
        int offsetY = TILESIZE * 2 + 15;
        ArrayList<Polygon> polygons = new ArrayList<>();
        Color[] colors = {Color.RED, Color.GREEN, Color.yellow, Color.MAGENTA, Color.white};
        Color c;

        {
            int topLeftX = ymargin2;
            int topLeftY = ymargin2;
            int topRightX = ymargin2 + TILESIZE * 2;
            int topRightY = ymargin2;
            int botLeftX = ymargin2;
            int botLeftY = ymargin2 + TILESIZE * 2;
            int centerX = topLeftX + TILESIZE;
            int centerY = topLeftY + TILESIZE;
            int[] xPoints = new int[]{topLeftX, topRightX, topRightX, centerX, centerX, botLeftX};
            int[] yPoints = new int[]{topLeftY, topRightY, centerY, centerY, botLeftY, botLeftY};
            Polygon p = new Polygon(xPoints, yPoints, 6);
            polygons.add(p);
            int angle = 11;
            // rotate 3 times for all points
            for (int i = 0; i < 3; i++) {
                int[] x = new int[6];
                int[] y = new int[6];
                for (int j = 0; j < 6; j++) {
                    double x1 = xPoints[j] - centerX;
                    double y1 = yPoints[j] - centerY;
                    double x2 = x1 * Math.cos(angle) - y1 * Math.sin(angle);
                    double y2 = x1 * Math.sin(angle) + y1 * Math.cos(angle);
                    x[j] = (int) (x2 + centerX);
                    y[j] = (int) (y2 + centerY) + offsetY;
                }
                p = new Polygon(x, y, 6);
                polygons.add(p);
                angle += 11;
                offsetY += TILESIZE * 2 + 15;
            }
            polygons.add(whiteSquare.p);
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2D = (Graphics2D) g;
            super.paintComponent(g2D);
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m[i].length; j++) {
                    g2D.setStroke(new BasicStroke(1));
                    Rectangle r = m[i][j].r;
                    if (!m[i][j].filled) {
                        g2D.setPaint(Color.blue);
                    } else {
                        g2D.setPaint(m[i][j].c);
                    }
                    g2D.fill(r);
                    g2D.setStroke(new BasicStroke(2));
                    g2D.setPaint(Color.black);
                    if (m[i][j].walls[Tromino.TOP]) {
                        g2D.drawLine(r.x, r.y, r.x + r.width, r.y);
                    }
                    if (m[i][j].walls[Tromino.LEFT]) {
                        g2D.drawLine(r.x, r.y, r.x, r.y + r.height);
                    }
                    if (i == m.length - 1) {
                        g2D.drawLine(r.x, r.y + r.height, r.x + r.width, r.y + r.height);
                    }
                    if (j == m[i].length - 1) {
                        g2D.drawLine(r.x + r.width, r.y, r.x + r.width, r.y + r.height);
                    }
                }
            }
            // draw four tiles and white square
            int i = 0;
            for (Polygon p : polygons) {
                g.setColor(colors[i++]);
                g.fillPolygon(p);
                g.setColor(Color.black);
                g.drawPolygon(p);
            }
            // draw the dragged tile
            if (draggedPolygon != null) {
                g.setColor(c);
                g.fillPolygon(draggedPolygon);
                g.setColor(Color.black);
                g.drawPolygon(draggedPolygon);
            }
            g2D.dispose();
        }
    }

    class Poly {

        Polygon p;
        Color c;
        int tromino;
        int[] ary;

        public Poly(Polygon p, Color c, int tromino) {
            this.p = p;
            this.c = c;
            this.tromino = tromino;
        }
    }

    class Tromino {

        int r1, c1;
        Color c = Color.blue;
        boolean filled;
        Rectangle r;
        Poly curr;
        static final int TOP = 0;
        static final int RIGHT = 1;
        static final int BOTTOM = 2;
        static final int LEFT = 3;
        boolean[] walls = {true, true, true, true};

        public Tromino(int r1, int c1, Rectangle r) {
            this.r1 = r1;
            this.c1 = c1;
            this.r = r;
        }
    }
}

class OptionsDialog extends JDialog {

    private JRadioButton twoButton = new JRadioButton("2x2 Board");
    private JRadioButton fourButton = new JRadioButton("4x4 Board");
    private JRadioButton eightButton = new JRadioButton("8x8 Board");
    private JRadioButton sixteenButton = new JRadioButton("16x16 Board");
    private int size = 8;
    private boolean cancelled = false;

    public OptionsDialog() {
        setTitle("Tromino Board");
        Font f = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
        twoButton.setFont(f);
        twoButton.setFocusPainted(false);
        fourButton.setFont(f);
        fourButton.setFocusPainted(false);
        eightButton.setFont(f);
        eightButton.setFocusPainted(false);
        sixteenButton.setFont(f);
        sixteenButton.setFocusPainted(false);
        //main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel, BorderLayout.CENTER);
        JLabel typeLabel = new JLabel("");
        mainPanel.add(typeLabel);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(twoButton);
        buttonGroup.add(fourButton);
        buttonGroup.add(eightButton);
        buttonGroup.add(sixteenButton);
        mainPanel.add(twoButton);
        mainPanel.add(fourButton);
        mainPanel.add(eightButton);
        mainPanel.add(sixteenButton);
        switch (TrominoTiles.POWER) {
            case 2:
                twoButton.setSelected(true);
                break;
            case 4:
                fourButton.setSelected(true);
                break;
            case 8:
                eightButton.setSelected(true);
                //eightButton.
                break;
            case 16:
                sixteenButton.setSelected(true);
                break;
            default:
                break;
        }
        //button panel
        JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.PAGE_END);
        JButton okButton = new JButton("OK");
        okButton.addActionListener((ActionEvent ae) -> {
            setVisible(false);
            cancelled = true;
            if (twoButton.isSelected()) {
                size = 2;
            } else if (fourButton.isSelected()) {
                size = 4;
            } else if (eightButton.isSelected()) {
                size = 8;
            } else if (sixteenButton.isSelected()) {
                size = 16;
            }
        });
        buttonPanel.add(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener((ActionEvent ae) -> {
            setVisible(false);
        });
        buttonPanel.add(cancelButton);

        getRootPane().setDefaultButton(okButton);
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean b) {
        cancelled = b;
    }

    public int getTrominoSize() {
        return size;
    }
}