package myUI;

import java.awt.*;

public class Pen extends Shape
{
	
	public Pen(int x, int y)
	{
		this.start_point= new Point(x,y);
		this.color=color.black;
		this.type= ShapeType.Pen;
		
	}

	public Point[] points = new Point[1000];
	private int index=0;
	
	public boolean AddPoint(Point p)
	{
		if (index < points.length)
		{
			points[index++]= new Point(p.x,p.y);
			return true;
		}
		return false;
	}
	
	public void DrawAll(Graphics2D g)
	{
		g.setColor(color);
		g.drawLine(start_point.x, start_point.y, points[0].x, points[0].y);
		for(int i=1;i<index; i++)
		{
			g.drawLine(points[i-1].x, points[i-1].y, points[i].x, points[i].y);
		}
	}
	
	public void Draw(Graphics2D g)
	{
		g.setColor(color);
		if(index == 1)
			g.drawLine(start_point.x, start_point.y, points[0].x, points[0].y);
		else
			g.drawLine(points[index-2].x, points[index-2].y, points[index-1].x, points[index-1].y);
		
	}
	
	//position of shape
	public boolean IsIn(Point p)
	{
		for(int i=1;i<index;i++)
		{
			if(p.x == points[i].x &&
					p.y == points[i].y)
				return true;
		}
		
		return false;
	}
	
}
