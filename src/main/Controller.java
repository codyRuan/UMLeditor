package main;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;


public class Controller {
	private int mode;
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
		for(BaseObject obj : objList) {
			obj_s = new Point(obj.getLocation());
			obj_e = new Point(obj.getX()+obj.getWidth(),
					obj.getY()+obj.getHeight());
			if(obj_s.x >= start.x && obj_s.y >= start.y
					&& obj_e.x <= end.x && obj_e.y <= end.y) {
				obj.showPoint();
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
			g.drawLine(line.front.x, line.front.y,
					line.to.x, line.to.y);
		}
	}
}
