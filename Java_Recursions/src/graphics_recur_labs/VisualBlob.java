package graphics_recur_labs;

import math.grid.Grid;
import math.location.Location;

import java.awt.*;
import java.awt.event.*;

public class VisualBlob extends Canvas implements MouseListener
{
	private Grid<Integer> grid;
	private int x;
	private int y;
	private Rectangle canvasSize;
	private Rectangle square;
	public static final int OFFSET = 10;

	private final int center = 50;

	public VisualBlob( )
	{
		setBackground(Color.WHITE);

		x = 10;
		y = 10;
		System.out.println(getHeight());

		//size grid to 10 x 10
		grid = new Grid<>(new Integer[10][10]);
		setWindowSizes();

		//load grid with random numbers
		for (Location loc : grid) {
			grid.setSpot(loc, (int)(Math.round(Math.random())));
		}
		addMouseListener( this );
		repaint();
	}

	public void paint( Graphics window )
	{
		window.setColor(Color.BLACK);
		window.drawString("Visual Blob ", 50, 40);
		
		
		//call drawGrid
		//pass in a Graphics and a color
		drawGrid(window, Color.BLACK);

		Location mouseLoc = getLoc(x, y);
		window.drawString("" + mouseLoc.getRow() + "  " + mouseLoc.getCol(), 50, 80);
	}
	
	public void drawGrid(Graphics window, Color col )
	{
		//set the color
		window.setColor(col);
		for (Location loc : grid) {
			drawSquare(window, loc);
		}
	}
	
	public void changeBlob(Location loc)
	{
		if (grid.inMatrix(loc) && grid.getSpot(loc )== 1) {
			grid.setSpot(loc, 0);
			changeBlob(loc.nextCol(new Location()));
			changeBlob(loc.nextRow(new Location()));
			changeBlob(loc.prevCol(new Location()));
			changeBlob(loc.prevRow(new Location()));
		}
	}

	public void drawSquare(Graphics window, Location loc) {
		int[] pos = getAbsPos(loc);
		if (grid.getSpot(loc) == 1) {
			window.fillRect(pos[0], pos[1], square.width, square.height);
		}
		else {
			window.drawRect(pos[0], pos[1], square.width, square.height);
		}
	}
	
	public void mouseClicked(MouseEvent e) 
	{
		//nothing to do here
		//grabbing the mouse click
		//getting the x and y
		x = e.getX();
		y = e.getY();

		changeBlob(getLoc(x, y));

		//leave this alone
		repaint();
	}

	private int[] getAbsPos(Location loc) {
		return new int[]{loc.getCol() * (OFFSET + square.width) + center,
						loc.getRow() * (OFFSET + square.height) + center};
	}

	private Location getLoc(int x, int y) {
		return new Location((y - center) / (OFFSET + square.height),
				(x - center) / (OFFSET + square.width));
	}

	private void setWindowSizes() {
		canvasSize = new Rectangle(600, 600);
		square = new Rectangle((canvasSize.height - OFFSET * grid.getRows()) / grid.getRows(),
				(canvasSize.width - OFFSET * grid.getCols()) / grid.getCols());
	}

	//these methods are part of the MouseListener interface
	//as we are implementing the MouseListener interface
	//we must override and implement all of the interface methods
	//even though we are only using the mouseClicked method
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e) {}	
}