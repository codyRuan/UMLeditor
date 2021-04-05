package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Button extends JButton{
	Controller c;
	public Button(String text, Controller control) {
		super(text);
		this.c = control;
		this.addActionListener(new ButtonHandler(text));
	}
	private class ButtonHandler implements ActionListener{
		private String text;
		public ButtonHandler(String text) {
			this.text = text;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.printf("click  %s\r\n",text);
			UMLFrame.button1.setBackground(null);
			UMLFrame.button2.setBackground(null);
			UMLFrame.button3.setBackground(null);
			UMLFrame.button4.setBackground(null);
			UMLFrame.button5.setBackground(null);
			UMLFrame.button6.setBackground(null);	
			switch(text) {
			case "select":
				UMLFrame.button1.setBackground(Color.BLACK);
				c.changeMode(1);
				break;
			case "association_line":
				UMLFrame.button2.setBackground(Color.BLACK);
				c.changeMode(2);
				break;
			case "generation_line":
				UMLFrame.button3.setBackground(Color.BLACK);
				c.changeMode(3);
				break;
			case "composition_line":
				UMLFrame.button4.setBackground(Color.BLACK);
				c.changeMode(4);
				break;
			case "class":
				UMLFrame.button5.setBackground(Color.BLACK);
				c.changeMode(5);
				break;
			case "use_class":
				UMLFrame.button6.setBackground(Color.BLACK);
				c.changeMode(6);
				break;
			}
			
		}
	}	
}
