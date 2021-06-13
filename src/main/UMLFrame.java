package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UMLFrame extends JFrame{

	static JButton button1, button2, button3;
	static JButton button4, button5, button6;
	Controller control;
	
	private JPanel buttonPnl;
	private Canvas canvas;
	
	public UMLFrame() {
		super("UML editor");
		initialize();
	}
	private void initialize() {
		control = Controller.getInstance();
		buttonPnl = new BtnPanel();
		this.add(buttonPnl,BorderLayout.WEST);
		this.setJMenuBar(new MenuBar());
		this.add(Canvas.getInstance(),BorderLayout.CENTER);
	}

	private class BtnPanel extends JPanel{
		public BtnPanel() {
			this.setLayout(new GridLayout(6,0,20,30));
			Icon icon = new ImageIcon(new ImageIcon("src\\picture\\mouse-removebg.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
			button1 = new Button("select", icon, control);
			icon = new ImageIcon(new ImageIcon("src\\picture\\association.png").getImage().getScaledInstance(150, 80, Image.SCALE_DEFAULT));
			button2 = new Button("association_line", icon, control);
			icon = new ImageIcon(new ImageIcon("src\\picture\\generation.png").getImage().getScaledInstance(150, 80, Image.SCALE_DEFAULT));
			button3 = new Button("generation_line", icon, control);
			icon = new ImageIcon(new ImageIcon("src\\picture\\composition.png").getImage().getScaledInstance(150, 80, Image.SCALE_DEFAULT));
			button4 = new Button("composition_line", icon, control);
			icon = new ImageIcon(new ImageIcon("src\\picture\\class.png").getImage().getScaledInstance(100, 80, Image.SCALE_DEFAULT));
			button5 = new Button("class", icon, control);
			icon = new ImageIcon(new ImageIcon("src\\picture\\object.png").getImage().getScaledInstance(120, 80, Image.SCALE_DEFAULT));
			button6 = new Button("use_class", icon, control);
			this.add(button1);
//			this.add(label1);
			this.add(button2);
			this.add(button3);
			this.add(button4);
			this.add(button5);
			this.add(button6);
		}
	}
}

