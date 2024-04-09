package myUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.*;

import javax.swing.*;

public class mypanel extends JPanel{
	
	private String shape;
	private BufferedImage image;
	private Point old_point;
	
	MouseListener ml = new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if(shape == "Cir")
				drawCir(e.getX(),e.getY());
			else if (shape == "Rec")
				drawRec(e.getX(),e.getY());
			else if (shape == "pen")
				old_point = new Point(e.getX(),e.getY());
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	};

	
	MouseMotionListener mml = new MouseMotionListener() {
		
		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			if (shape == "pen"){
				Point new_point = new Point(e.getX(), e.getY());
			    drawLine (old_point, new_point);
			
			old_point = new_point;
			}
			
		}
	};
	
	public mypanel ()
	{
		super();
		
		this.setBackground(Color.WHITE);
		this.addMouseListener(ml);
		this.addMouseMotionListener(mml);
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.black);
		g.drawRect(1, 1, this.getWidth()-3, this.getHeight()-3);
		if(image == null)
		    image = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_ARGB_PRE);
		    g.drawImage(image, 0,0, null);
				    
		    repaint();
		    
	}
	
	public void drawCir(int x, int y){
		/* this hided after added Buffered image AND instead by Graphics2d
		 * Graphics g = this.getGraphics();*/
		Graphics2D g = image.createGraphics();
		g.setColor(Color.red);
		g.drawOval(x-50, y-50, 100, 100);
		
		repaint();
	}
	
	public void clear()

	{
		image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB_PRE);
		
		repaint();
	}

	public void drawRec(int x, int y){
		//*Graphics g=this.getGraphics();
		Graphics2D g = image.createGraphics();
		g.setColor(Color.blue);
		g.drawRect(x-50, y-50, 100, 100);
	}

	public void drawLine(Point p1,Point p2){
		Graphics2D g = image.createGraphics();
		g.setColor(Color.GREEN);
		g.drawLine(p1.x, p1.y, p2.x, p2.y);
		
		repaint();
	}
	public void setShape(String shape)
	{
		this.shape=shape;
	}	
	
}
	
