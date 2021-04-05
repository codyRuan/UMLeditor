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
import java.awt.event.MouseMotionAdapter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UseCaseDiagram extends BaseObject{
	private JLabel objectName;
	MouseAdapter listener;
	MouseMotionAdapter Mlistener;
	public UseCaseDiagram(Controller c) {
		super(c);
		initialize();
	    setOpaque(false);
	}
	
	private void initialize() {
		Font displayFont = new Font("Serif", Font.BOLD, 18);
		objectName = new JLabel("ObjectName");
		objectName.setBackground(new Color(255, 255, 185));
		objectName.setFont(displayFont);
		objectName.setHorizontalAlignment(JTextField.CENTER);
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.GRAY);
		GridBagConstraints GBC = new GridBagConstraints();
        GBC.insets = new Insets(1, 2, 1, 1);
        GBC.fill = GridBagConstraints.BOTH;
        GBC.anchor = GridBagConstraints.NORTHEAST;
        GBC.gridx = 0;
        GBC.gridy = 0;
        GBC.gridwidth = 1;
        GBC.gridheight = 2;
        GBC.weightx = 30;
        GBC.weighty = 30;
        this.add(objectName, GBC);
        
        listener = new Listener();
        Mlistener = new MotionListener();
        this.addMouseListener(listener);
        this.addMouseMotionListener(Mlistener);
        width = this.getPreferredSize().width;
        height = this.getPreferredSize().height;
        me = this;
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);	
		g.setColor(Color.white);
		g.drawOval(0, 0, width, height-2);
		g.setColor(Color.black);
		if(visible) {
			g.fillOval(width/2-4, height-8, 8, 8);
			g.fillOval(width/2-4, 0, 8, 8);
			g.fillOval(0, height/2-4, 8, 8);
			g.fillOval(width-8, height/2-4, 8, 8);
		}
	}

	@Override
	public void changeName(String s) {
		// TODO Auto-generated method stub
		objectName.setText(s);
	}
	
	public void deleteListener() {
		this.removeMouseListener(listener);
		this.removeMouseMotionListener(Mlistener);
	}
}
