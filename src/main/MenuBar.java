package main;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MenuBar extends JMenuBar{
	private final Font bigFont = new Font( "console", Font.BOLD, 22 );
	private Controller c;
	public MenuBar(Controller c) {
		this.c = c;
		add(createFile());
		add(createEdit());
	}
	private JMenu createFile() {
		JMenu menu = new JMenu("file");
		JMenuItem item = new JMenuItem("create new file");
		item.setFont(bigFont);
		menu.add(item);
		menu.setFont(bigFont);
		return menu;
	}
	private JMenu createEdit() {
		JMenu menu = new JMenu("edit");
		JMenuItem item = new JMenuItem("copy");
		item.setFont(bigFont);
		menu.add(item);
		menu.addSeparator();
		item = new JMenuItem("paste");
		item.setFont(bigFont);
		menu.add(item);
		menu.addSeparator();
		item = new JMenuItem("change object name");
		AbstractAction nlistener = new nameListener();
		item.addActionListener(nlistener);
		item.setFont(bigFont);
		menu.add(item);
		menu.addSeparator();
		item = new JMenuItem("group");
		AbstractAction glistener = new groupListener();
		item.addActionListener(glistener);
		item.setFont(bigFont);
		menu.add(item);
		menu.addSeparator();
		item = new JMenuItem("ungroup");
		AbstractAction unglistener = new ungroupListener();
		item.addActionListener(unglistener);
		item.setFont(bigFont);
		menu.add(item);
		menu.setFont(bigFont);
		return menu;
	}
	private class nameListener extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String name = JOptionPane.showInputDialog("new name?");
			c.changeName(name);
		}
	}
	private class groupListener extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(c.getSelectList().size() > 1) {
				BaseObject comObject = new CompositeObject(c, c.getSelectList());
				c.addObj(comObject);
				UMLFrame.canvas.add(comObject);
				UMLFrame.canvas.updateUI();
			}
		}
	}
	private class ungroupListener extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(c.getSelectedObj().getClass() == CompositeObject.class) {
				 BaseObject co;
				 co = c.getSelectedObj();
				 int disX = co.getX() - co.creatPoint.x;
				 int disY = co.getY() - co.creatPoint.y;
				 for(BaseObject b : ((CompositeObject)co).allObject) {
					 if(b instanceof ClassDiagram) {
						 ClassDiagram cd = new ClassDiagram(c);
						 cd.setEnabled(true);
						 cd.setBounds(b.x+disX,b.y+disY, cd.getPreferredSize().width, cd.getPreferredSize().height);
						 c.addObj(cd);
						 UMLFrame.canvas.add(cd);
						 UMLFrame.canvas.updateUI();
					 }
					 else {
						 UseCaseDiagram usd = new UseCaseDiagram(c);
						 usd.setEnabled(true);
						 usd.setBounds(b.x+disX,b.y+disY, usd.getPreferredSize().width, usd.getPreferredSize().height);
						 c.addObj(usd);
						 UMLFrame.canvas.add(usd);
					     UMLFrame.canvas.updateUI();
					 }
				 }
				 c.removeObj(co);
				 UMLFrame.canvas.remove(co);
			}
		}
	}
}
