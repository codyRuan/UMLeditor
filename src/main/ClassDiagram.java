package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ClassDiagram extends BaseObject{
	private JLabel className;
	private JLabel col1, col2;
	MouseAdapter listener;
	MouseMotionAdapter Mlistener;
	public ClassDiagram(Controller c) {
		super(c);
        initialize();
	}
	private void initialize() {
		Font displayFont = new Font("Serif", Font.BOLD, 18);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		className = new JLabel("ClassName");
		className.setBackground(new Color(255, 255, 185));
        className.setFont(displayFont);
        className.setHorizontalAlignment(JTextField.CENTER);
        className.setBorder(blackline);
        this.setLayout(new GridBagLayout());
        this.setBorder(blackline);
        GridBagConstraints GBC = new GridBagConstraints();
        GBC.insets = new Insets(1, 1, 1, 1);
        GBC.fill = GridBagConstraints.BOTH;
        GBC.anchor = GridBagConstraints.NORTHEAST;
        GBC.gridx = 0;
        GBC.gridy = 0;
        GBC.gridwidth = 1;
        GBC.gridheight = 1;
        GBC.weightx = 30;
        GBC.weighty = 30;
        this.add(className, GBC);
        
        col1 = new JLabel("column1");
        col1.setHorizontalAlignment(JTextField.CENTER);
        col1.setBorder(blackline);
        GBC = new GridBagConstraints();
        GBC.insets = new Insets(1, 1, 1, 1);
        GBC.fill = GridBagConstraints.BOTH;
        GBC.anchor = GridBagConstraints.NORTHEAST;
        GBC.gridx = 0;
        GBC.gridy = 1;
        GBC.gridwidth = 1;
        GBC.gridheight = 1;
        GBC.weightx = 30;
        GBC.weighty = 30;
        this.add(col1, GBC);
        
        col2 = new JLabel("column2");
        col2.setHorizontalAlignment(JTextField.CENTER);
        col2.setBorder(blackline);
        GBC = new GridBagConstraints();
        GBC.insets = new Insets(1, 1, 1, 1);
        GBC.fill = GridBagConstraints.BOTH;
        GBC.anchor = GridBagConstraints.NORTHEAST;
        GBC.gridx = 0;
        GBC.gridy = 2;
        GBC.gridwidth = 1;
        GBC.gridheight = 1;
        GBC.weightx = 30;
        GBC.weighty = 30;
        this.add(col2, GBC);
        
        listener = new Listener();
        Mlistener = new MotionListener();
        this.addMouseListener(listener);
        this.addMouseMotionListener(Mlistener);
        width = this.getPreferredSize().width;
        height = this.getPreferredSize().height;
        me = this;
	}
		
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);	
		if(visible) {
			g.fillOval(width/2-4, height-8, 8, 8);
			g.fillOval(width/2-4, 0, 8, 8);
			g.fillOval(0, height/2-4, 8, 8);
			g.fillOval(width-8, height/2-4, 8, 8);
		}
	}
	@Override
	public void changeName(String s) {
		className.setText(s);
	}
	
	public void deleteListener() {
		this.removeMouseListener(listener);
		this.removeMouseMotionListener(Mlistener);
	}
}
