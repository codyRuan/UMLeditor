package main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UMLFrame extends JFrame{

	static JButton button1, button2, button3;
	static JButton button4, button5, button6;
	Controller control;
	
	private JPanel buttonPnl;
	static JPanel canvas;
	
	public UMLFrame() {
		super("UML editor");
		initialize();
	}
	private void initialize() {
		control = new Controller();
		buttonPnl = new BtnPanel();
		this.add(buttonPnl,BorderLayout.WEST);
		canvas = new Canvas(control);
		canvas.setLayout(null);
		this.setJMenuBar(new MenuBar(control));
		this.add(canvas,BorderLayout.CENTER);
	}

	private class BtnPanel extends JPanel{
		public BtnPanel() {
			this.setLayout(new GridLayout(6,0,10,30));
			button1 = new Button("select", control);
			button2 = new Button("association_line", control);
			button3 = new Button("generation_line", control);
			button4 = new Button("composition_line", control);
			button5 = new Button("class", control);
			button6 = new Button("use_class", control);
			this.add(button1);
			this.add(button2);
			this.add(button3);
			this.add(button4);
			this.add(button5);
			this.add(button6);
		}
	}
}

