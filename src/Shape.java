import java.awt.Color;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;

public class Shape {

	private int id;

	public void setID(int goId) {
		id = goId;
	}

	public int getID() {
		return id;
	}

	public void Draw() {

	}

	public boolean doesContain(int _x, int _y) {
		return false;
	}

	public void setC(Color c) {

	}

	public void editDuplicateColor(Color co, int idx) {

	}

	public void move(int xS, int yS, int xE, int yE, boolean isFinal) {

	}

	public void resize(int xS, int yS, int xE, int yE, boolean isFinal) {

	}

	public Element sendNodeForXml() throws ParserConfigurationException {
		return null;

	}

	public void createMyJson() {

	}

	public void moveBonus(int xS, int yS, int xE, int yE, boolean isFinal) {

	}

}
