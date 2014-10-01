import java.util.Stack;

public class Memory {
	public static Stack<Shape> shapes = new Stack<Shape>();
	private static Stack<Shape> helper = new Stack<Shape>();
	public static void undo(){
		if(!shapes.isEmpty()){
			helper.add(shapes.pop());
			Panel.g.clearRect(0, 0, 300, 300);
			for(int i=0;i<shapes.size();i++){
				shapes.get(i).Draw();
			}
		}
	}
	public static void redo(){
		if(!helper.isEmpty()){
			shapes.add(helper.pop());
			shapes.peek().Draw();
		}
	}
	public static void clearMemory(){
		helper.clear();
	}
}
