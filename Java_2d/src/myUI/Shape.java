package myUI;

import java.awt.*;

public abstract class Shape
{
	public enum ShapeType {Circle,Rectangle,Pen};
	public Color color;
	public Point start_point;
	public ShapeType type;
	
	public void Draw(Graphics2D g, Color c)
	{
		this.color = c;
		Draw(g);	
	}
	
	public abstract void Draw(Graphics2D g);
	
	//position of shape
	public abstract boolean IsIn(Point p);
}
