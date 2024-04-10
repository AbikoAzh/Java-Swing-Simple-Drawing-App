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

import myUI.Shape.ShapeType;

public class mypanel extends JPanel{
	
	private Shape.ShapeType shape;
	private BufferedImage image, orginal_image;
	private Shape cshape;
	private Shape[] shapes = new Shape[1000];
	private int shapeindex = 0;
	private Point clickpoint;
	private boolean IsShapePressed = false;
	private boolean IsResizable = false;
	private int PressedShapeIndex;
	
	
	MouseListener ml = new MouseListener() 
	{
		@Override
		public void mouseReleased(MouseEvent e)
		{
			// TODO Auto-generated method stub
			if(!IsShapePressed)
				shapes[shapeindex++] = cshape;
			else
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		
		
		@Override
		public void mousePressed(MouseEvent e) 
		{
			boolean IsIn = false;
			for(int i=0; i<shapeindex; i++)
			{
				if(shapes[i].IsIn(new Point(e.getX(),e.getY())))
				{
					IsIn = true;
					IsShapePressed = true;
					PressedShapeIndex = i;
					break;
				}
			}
			
			if(IsIn)
			{		
				    // added this to change shape size
					IsResizable = false;
					
					//move shape (circle)
					
					// save mouse click after draw to move object
					clickpoint = new Point(e.getX(), e.getY());
					
					setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
					
					orginal_image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
					for(int i=0; i<shapeindex; i++)
					{
						if(i!=PressedShapeIndex)
						{
							if(shapes[i].type == ShapeType.Pen)
							{
								Pen x = (Pen)shapes[i];
								x.DrawAll(orginal_image.createGraphics());
							} else
								{ 
									shapes[i].Draw(orginal_image.createGraphics());
								}
						}
					}
					
					if(shapes[PressedShapeIndex].type == ShapeType.Rectangle)
					{
						// resize or move(rectangle)
						Rectangle z =(Rectangle) shapes[PressedShapeIndex];
						Point end_point = new Point(z.start_point.x + z.width, z.start_point.y + z.height);
						
						//if i'm on the corner
						if(e.getX() > end_point.x-15 && e.getY() > end_point.y-15)
						{
							setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
							IsResizable = true;
							
						}
					}
					
			}else
				{
					IsShapePressed = false;
					PressedShapeIndex = -1;
					setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
				
					orginal_image = cloneImage(image);
					if (shape == ShapeType.Pen)
					{
						cshape = new Pen(e.getX(),e.getY());
					}
					else if (shape == ShapeType.Circle)
						{
							cshape = new Circle(e.getX(),e.getY(), 0, 0);
						}
					else if (shape == ShapeType.Rectangle)
						{
							cshape = new Rectangle(e.getX(),e.getY(), 0, 0);
						}
				}
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
	
	
	
	MouseMotionListener mml = new MouseMotionListener() 
	{
		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
			//position of shape
			boolean IsIn = false;
			int index = -1;
			for(int i=0; i<shapeindex; i++)
			{
				if(shapes[i].IsIn(new Point(e.getX(),e.getY())))
				{
					IsIn = true;
					index = i;
					break;
				}
			}
			
			if(IsIn)
			{
				
				if(shapes[index].type == ShapeType.Rectangle)
				{
					Rectangle z =(Rectangle) shapes[index];
					Point end_point = new Point(z.start_point.x + z.width, z.start_point.y + z.height);
					
					if(e.getX() > end_point.x-15 && e.getY() > end_point.y-15)
						setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
					else
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					
				}else
				
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}else
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
				}
		}
		
		@Override
		public void mouseDragged(MouseEvent e) 
		{
			Point new_point = new Point(e.getX(), e.getY());
			// if it's pressed and if his type pen
			if(IsShapePressed && shapes[PressedShapeIndex].type != ShapeType.Pen)
			{
				// copy
				image = cloneImage(orginal_image);
				// sub move
				int xshift = new_point.x - clickpoint.x;
				int yshift = new_point.y - clickpoint.y;
				clickpoint = new Point(new_point.x, new_point.y);
				// if it's able to resize
				if(IsResizable)
				{
					// resize
					Rectangle r =(Rectangle)shapes[PressedShapeIndex];
					r.width += xshift;
					r.height += yshift;
					r.Draw(image.createGraphics());
					
				}else
				{
					// move
					shapes[PressedShapeIndex].start_point.x += xshift;
					shapes[PressedShapeIndex].start_point.y += yshift;
					shapes[PressedShapeIndex].Draw(image.createGraphics());
				}
				repaint();
				
			}else
				{
					if (cshape.type == ShapeType.Pen)
					{
						Pen x = (Pen)cshape;
						x.AddPoint(new_point);
						x.Draw(image.createGraphics(),Color.GREEN);
						repaint();
					}
					else if (cshape.type == ShapeType.Circle)
						{
							image = cloneImage(orginal_image);
							int width = new_point.x - cshape.start_point.x;
							int height= new_point.y - cshape.start_point.y;
							
							Circle x =(Circle)cshape;
							x.width =width;
							x.height = height;
							
							x.Draw(image.createGraphics(),Color.RED);
							repaint();
						}
					else if (cshape.type == ShapeType.Rectangle)
						{
							image = cloneImage(orginal_image);
							int width = new_point.x - cshape.start_point.x;
							int height= new_point.y - cshape.start_point.y;
							Rectangle x = (Rectangle)cshape;
							x.width=width;
							x.height=height;
							x.color=Color.BLUE;
							x.Draw(image.createGraphics());
						}
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
		
	
	public void clear()
	{
		image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB_PRE);
		shapeindex = 0;
		repaint();
	}

		
	public void setShape(ShapeType shape)
	{
		this.shape=shape;
	}	
	
	
	private BufferedImage cloneImage(BufferedImage _image)
	{
		if(_image == null)
			return null;
		ColorModel cm = _image.getColorModel();
		boolean isAplpha = cm.isAlphaPremultiplied();
		WritableRaster raster = _image.copyData(null);
		
		return new BufferedImage(cm,raster,isAplpha,null);
	}
	
}
	
