import java.awt.Canvas;
import java.awt.Graphics;
import java.util.Scanner;

import javax.swing.JFrame;

public class work {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Panel me = new Panel();

		Scanner in = new Scanner(System.in);
		int sc = in.nextInt();

		if (sc == 1) {
			xmlHandle.createXml(Memory.shapes);
		} else if (sc == 2) {
			JsonHandle.createJsonfile(Memory.shapes);
		} else if (sc == 3) {
			xmlHandle.readXml();
		} else {
			JsonHandle.loadJsonFile();
		}

		// ClassLoader classLoader = work.class.getClassLoader();
		//
		// try {
		// Class aClass = classLoader.loadClass("com.jenkov.MyClass");
		// System.out.println("aClass.getName() = " + aClass.getName());
		// } catch (ClassNotFoundException e) {
		// e.printStackTrace();
		// }
	}

}
