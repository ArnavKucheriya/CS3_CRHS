package graphics_recur_labs;

import java.awt.*;
import javax.swing.*;

public class VisualBlogRunner extends JFrame
{
	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;

	public VisualBlogRunner()
	{
		super("VisualBlogRunner");

		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		//add VisualBlog to the content pane
		//look at prior runners if needed
		
		setVisible(true);

		VisualBlob vb = new VisualBlob();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		add(vb);
		pack();
	}

	public static void main( String args[] )
	{
		VisualBlogRunner run = new VisualBlogRunner();
	}
}