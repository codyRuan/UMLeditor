package main;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import line.BaseLine;
import object.BaseObject;


public class Controller {
	private int mode;
	private int g = 0;
	private final ArrayList<BaseObject> objList = new ArrayList<>();
	private final ArrayList<BaseObject> selectedList = new ArrayList<>();
	private final ArrayList<Integer> groupNums = new ArrayList<>();
	final ArrayList<BaseLine> lineList = new ArrayList<>();
	private BaseObject selectObj;
	private BaseObject enteredObj;
	private static Controller instance = null;
	private Controller() {
		this.mode = 0;
	}
	public static Controller getInstance() {
		if(instance == null) {
			instance = new Controller();
		}
		return instance;
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
				if(!obj.getGroup().isEmpty()) {
					for(BaseObject o : obj.getGroup().get(obj.getGroup().size()-1).second) {
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
	public ArrayList<BaseLine> getLineList(){
		return lineList;
	}
	public int addGroup() {
		this.g++;
		return this.g;
	}
	
	public int getNumsOfGroup() {
		int groupNum;
		for(BaseObject b : this.selectedList) {
			if(b.getGroup().isEmpty()) {
				groupNums.add(-1);
			}
			else {
				groupNum = b.getGroup().get(b.getGroup().size()-1).first;
				if(!groupNums.contains(groupNum)) {
					groupNums.add(groupNum);
				}
			}
		}
		return groupNums.size();
	}
}
