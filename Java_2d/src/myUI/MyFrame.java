package myUI;

import java.awt.Color;
import java.awt.Component;
import javax.swing.*;

public class MyFrame extends JFrame  {
	
	JPanel main_panel = new JPanel();
	
	public MyFrame (String Title ){
		
		super(Title);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		main_panel.setBackground(Color.lightGray);
		main_panel.setLayout(null);
		
		
		this.add(main_panel);
		this.setVisible(true);
		
	}
	/*public void addComponent(Component c)
	{
		main_panel.add(c);
	}*/
	public void addComponent(Component c) {
		// TODO Auto-generated method stub
		main_panel.add(c);
	}

}
