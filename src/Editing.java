import java.awt.Color;
import java.util.Stack;

public class Editing {
	
	public static void EditColor(int x, int y) {
		Panel.g.clearRect(0, 0, 300, 300);
		int siz = Memory.shapes.size();
		for (int j = 0; j < (siz); j++) {
			Memory.shapes.get(j).Draw();
			if (Memory.shapes.get(j).doesContain(x, y)) {
				Memory.shapes.get(j).editDuplicateColor(Panel.currentEditingColor, j);
			}
		}
	}
	
	
	public static void Move(int xS,int yS,int xE, int yE,boolean b){
		Panel.g.clearRect(0, 0, 300, 300);
		int siz = Memory.shapes.size();
		for (int j = 0; j < (siz); j++) {
			if (Memory.shapes.get(j).doesContain(xS, yS)) {
				Memory.shapes.get(j).move(xS, yS, xE, yE, b);
			}else Memory.shapes.get(j).Draw();
		}
	}
	
	public static void Resize(int xS,int yS,int xE, int yE,boolean b){
		Panel.g.clearRect(0, 0, 300, 300);
		int siz = Memory.shapes.size();
		boolean notYet = true;
		for (int j = 0; j < (siz); j++) {
			if (Memory.shapes.get(j).doesContain(xS, yS) && notYet) {
				Memory.shapes.get(j).resize(xS, yS, xE, yE, b);
				notYet = false;
			}else Memory.shapes.get(j).Draw();
		}
	}
	
	
	

	public static void deleteObject(int xselect, int yselect) {

		Stack<Shape> stk = new Stack<Shape>();
		for (int i = 0; i < Memory.shapes.size(); i++) {
			if (!Memory.shapes.get(i).doesContain(xselect, yselect)) {
				stk.add(Memory.shapes.get(i));
			}

		}

		Panel.g.clearRect(0, 0, 300, 300);

		for (int i = 0; i < stk.size(); i++) {
			stk.get(i).Draw();
		}

		Memory.shapes = stk;
	}

	
	
	
}