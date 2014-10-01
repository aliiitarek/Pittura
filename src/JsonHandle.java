import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonHandle {
	public static JSONObject JsonObj = new JSONObject();
	public static JSONArray allShapes = new JSONArray();

	public static void createJsonfile(Stack<Shape> stk) {

		for (int i = 0; i < stk.size(); i++) {

			Shape currentShape = stk.get(i);
			currentShape.createMyJson();

		}

		try {

			FileWriter file = new FileWriter("c:\\files\\test.json");

			JsonObj.put("allShapes", allShapes);
			file.write(JsonObj.toJSONString());
			// file.write(allShapes.toJSONString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Stack<Shape> loadJsonFile() {

		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader("c:\\files\\test.json"));
			JSONObject jobj = (JSONObject) obj;
			JSONArray jarray = (JSONArray) jobj.get("allShapes");

			// for (int i = 0; i < jarray.size(); i++) {
			// System.out.println(jarray.get(i));
			// }

			Stack<Shape> allShapes = new Stack<Shape>();

			// System.out.println(jarray.size());
			for (int i = 0; i < jarray.size(); i++) {

				JSONObject currentShape = (JSONObject) jarray.get(i);
				// int id = (Integer) currentShape.get("id");
				String str = (String) currentShape.get("id");
				int id = Integer.parseInt(str);

				if (id == 1) {

					str = (String) currentShape.get("xcenter");
					int xcenter = Integer.parseInt(str);
					str = (String) currentShape.get("ycenter");
					int ycenter = Integer.parseInt(str);
					str = (String) currentShape.get("width");
					int width = Integer.parseInt(str);

					str = (String) currentShape.get("height");
					int height = Integer.parseInt(str);

					str = (String) currentShape.get("Color");
					int col = Integer.parseInt(str);
					Color color = new Color(col);

					Shape newShape = new Ellipse(xcenter, ycenter, width,
							height, color);
					newShape.Draw();
					allShapes.add(newShape);

				} else if (id == 2) {

					str = (String) currentShape.get("xcenter");
					int xcenter = Integer.parseInt(str);
					str = (String) currentShape.get("ycenter");
					int ycenter = Integer.parseInt(str);
					str = (String) currentShape.get("width");
					int width = Integer.parseInt(str);

					str = (String) currentShape.get("height");
					int height = Integer.parseInt(str);

					str = (String) currentShape.get("Color");
					int col = Integer.parseInt(str);
					Color color = new Color(col);

					Shape newShape = new Circle(xcenter, xcenter, width, color);
					
					newShape.Draw();
					allShapes.add(newShape);

				} else if (id == 3) {

					str = (String) currentShape.get("xcenter");
					int xcenter = Integer.parseInt(str);
					str = (String) currentShape.get("ycenter");
					int ycenter = Integer.parseInt(str);
					str = (String) currentShape.get("width");
					int width = Integer.parseInt(str);

					str = (String) currentShape.get("height");
					int height = Integer.parseInt(str);

					str = (String) currentShape.get("Color");
					int col = Integer.parseInt(str);
					Color color = new Color(col);

					Shape newShape = new Rectangle(xcenter, ycenter, width,
							height, color);
					newShape.Draw();
					allShapes.add(newShape);

				} else if (id == 4) {

					str = (String) currentShape.get("xcenter");
					int xcenter = Integer.parseInt(str);
					str = (String) currentShape.get("ycenter");
					int ycenter = Integer.parseInt(str);
					str = (String) currentShape.get("width");
					int width = Integer.parseInt(str);

					str = (String) currentShape.get("height");
					int height = Integer.parseInt(str);

					str = (String) currentShape.get("Color");
					int col = Integer.parseInt(str);
					Color color = new Color(col);

					Shape newShape = new Square(xcenter, ycenter, width, color);
					newShape.Draw();
					allShapes.add(newShape);

				} else if (id == 5) {

					str = (String) currentShape.get("x1");
					int x1 = Integer.parseInt(str);
					str = (String) currentShape.get("y1");
					int y1 = Integer.parseInt(str);
					str = (String) currentShape.get("x2");
					int x2 = Integer.parseInt(str);
					str = (String) currentShape.get("y2");
					int y2 = Integer.parseInt(str);
					str = (String) currentShape.get("x3");
					int x3 = Integer.parseInt(str);
					str = (String) currentShape.get("y3");
					int y3 = Integer.parseInt(str);

					str = (String) currentShape.get("Color");
					int col = Integer.parseInt(str);
					Color color = new Color(col);

					Shape newShape = new Triangle(x1, y1, x2, y2, x3, y3, color);
					newShape.Draw();
					allShapes.add(newShape);

				} else {

					str = (String) currentShape.get("x1");
					int x1 = Integer.parseInt(str);
					str = (String) currentShape.get("y1");
					int y1 = Integer.parseInt(str);
					str = (String) currentShape.get("x2");
					int x2 = Integer.parseInt(str);
					str = (String) currentShape.get("y2");
					int y2 = Integer.parseInt(str);

					str = (String) currentShape.get("Color");
					int col = Integer.parseInt(str);
					Color color = new Color(col);

					Shape newShape = new Line(x1, y1, x2, y2, color);
					newShape.Draw();
					allShapes.add(newShape);

				}

			}

			return allShapes;

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}
