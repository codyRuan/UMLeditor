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
//			if(c.getSelectList().size() > 1) {
//				BaseObject comObject = new CompositeObject(c, c.getSelectList());
//				c.addObj(comObject);
//				UMLFrame.canvas.add(comObject);
//				UMLFrame.canvas.updateUI();
//			}
			if(c.getSelectList().size() > 1) {
				int groupNum = c.addGroup();
				for(BaseObject b : c.getSelectList()) {
					b.group.add(groupNum);
					b.myteems.addAll(c.getSelectList());
				}
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
				 BaseObject baseObj;
				 for(BaseObject b : ((CompositeObject)co).allObject) {
					 if(b instanceof ClassDiagram) {
						 baseObj = new ClassDiagram(c);
					 }
					 else {
						 baseObj = new UseCaseDiagram(c);
					 }
					 baseObj.setEnabled(true);
					 baseObj.setBounds(b.x+disX,b.y+disY, 
							 baseObj.getPreferredSize().width, baseObj.getPreferredSize().height);
					 c.addObj(baseObj);
					 UMLFrame.canvas.add(baseObj);
					 UMLFrame.canvas.updateUI();
				 }
				 c.removeObj(co);
				 UMLFrame.canvas.remove(co);
			}
		}
	}
}
