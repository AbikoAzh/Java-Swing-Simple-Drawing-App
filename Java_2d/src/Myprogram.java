import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import myUI.*;

public class Myprogram {

	static mypanel panel=new mypanel();
	public static void main(String[] args) {
		
		ActionListener al=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setShape((((JButton) e.getSource()).getText()));	
			}
		};
		
		ActionListener al2= new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.clear();
				
			}
		};
		
		MyFrame frame = new MyFrame ("this is my frame");
		
		JButton btn1=new JButton("Cir");
		btn1.setSize(100, 50);
		btn1.setLocation(60, 50);
		
		btn1.addActionListener(al);
		
		JButton btn2=new JButton("Rec");
		btn2.setSize(100, 50);
		btn2.setLocation(180, 50);
		
		btn2.addActionListener(al);
		
		JButton btn3 = new JButton("Clr");
		btn3.setSize(100, 50);
		btn3.setLocation(300, 50);
		
		btn3.addActionListener(al2);
		
		
		JButton btn4 = new JButton("pen");
		btn4.setSize(100, 50);
		btn4.setLocation(420, 50);
		
		btn4.addActionListener(al);
		
		panel.setSize(500,500);
		panel.setLocation(50, 150);
		
		frame.addComponent(btn1);
		frame.addComponent(btn2);
		frame.addComponent(btn3);
		frame.addComponent(btn4);
		frame.addComponent(panel);
		

	}
}
