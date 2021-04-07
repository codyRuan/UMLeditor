package main;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;


public class Controller {
	private int mode;
	private int g = 0;
	private final ArrayList<BaseObject> objList = new ArrayList<>();
	private final ArrayList<BaseObject> selectedList = new ArrayList<>();
	final ArrayList<BaseLine> lineList = new ArrayList<>();
	private BaseObject selectObj;
	private BaseObject enteredObj;
	public Controller() {
		this.mode = 0;
	}
	public void changeMode(int m) {
		this.mode = m;
	}
	public int getMode() {
		return mode;
	}
	public void addObj(BaseObject obj) {
		objList.add(obj);
	}
	public void removeObj(BaseObject obj) {
		objList.remove(obj);
	}
	public void hideAll() {
		for(BaseObject obj : objList) {
			obj.hidePoint();
		}
		selectedList.clear();
	}
	public void selected(BaseObject b) {
		this.selectObj = b;
	}
	public void entered(BaseObject b) {
		this.enteredObj = b;
	}
	public BaseObject getSelectedObj() {
		return selectObj;
	}
	public BaseObject getEnteredObj() {
		return enteredObj;
	}
	public void selectedAll(Point start, Point end) {
		Point obj_s, obj_e;
		int lx, ly, rx, ry;
		lx = Math.min(start.x, end.x);
		ly = Math.min(start.y, end.y);
		rx = Math.max(start.x, end.x);
		ry = Math.max(start.y, end.y);
		for(BaseObject obj : objList) {
			obj_s = new Point(obj.getLocation());
			obj_e = new Point(obj.getX()+obj.getWidth(),
					obj.getY()+obj.getHeight());
			if(obj_s.x >= lx && obj_s.y >= ly
					&& obj_e.x <= rx && obj_e.y <= ry) {
				if(!obj.group.isEmpty()) {
					for(BaseObject o : obj.group.get(obj.group.size()-1).second) {
						o.showPoint();
						if(!selectedList.contains(o))
							selectedList.add(o);
					}
				}
				else
					obj.showPoint();
				if(!selectedList.contains(obj))
					selectedList.add(obj);
			}
		}
	}
	
	public void changeName(String name) {
		if(selectObj != null) {
			selectObj.changeName(name);
		}
	}
	
	public ArrayList<BaseObject> getSelectList(){
		return selectedList;
	}
	
	public void addLine(BaseLine line) {
		lineList.add(line);
	}
	
	public void drawLine(Graphics g) {
		for(BaseLine line : lineList) {
			line.draw(g);
		}
	}
	
	public int addGroup() {
		this.g++;
		return this.g;
	}
}
