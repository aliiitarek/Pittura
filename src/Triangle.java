import java.awt.Color;

import org.json.simple.JSONObject;
import org.w3c.dom.Element;

public class Triangle extends Line {
	private Line l1, l2, l3;
	private Color c;

	public Triangle(int x1, int y1, int x2, int y2, int x3, int y3, Color _c) {
		l1 = new Line(x1, y1, x2, y2, _c);
		l2 = new Line(x1, y1, x3, y3, _c);
		l3 = new Line(x3, y3, x2, y2, _c);
		
		setID(5);
	}

	public void setC(Color c) {
		this.c = l1.getColor();
	}
	
	public int getx1() {
		return l1.getX1();
	}

	public int gety1() {
		return l1.getY1();
	}

	public int getx2() {
		return l1.getX2();
	}

	public int gety2() {
		return l1.getY2();
	}

	public int getx3() {
		return l2.getX2();
	}

	public int gety3() {
		return l2.getY2();
	}
	
	

	public Color getColor() {
		return c;
	}
	

	private static int sign(int x1, int y1, int x2, int y2, int x3, int y3) {
		return (x1 - x3) * (y2 - y3) - (x2 - x3) * (y1 - y3) ;
	}

	private boolean PointInTriangle(int ptx, int pty, int x1, int y1, int x2,
			int y2, int x3, int y3) {
		boolean b1, b2, b3;

		b1 = sign(ptx, pty, x1, y1, x2, y2) < 0;
		b2 = sign(ptx, pty, x2, y2, x3, y3) < 0;
		b3 = sign(ptx, pty, x3, y3, x1, y1) < 0;

		return ((b1 == b2) && (b2 == b3));
	}

	@Override
	public boolean doesContain(int _x, int _y) {
		// TODO Auto-generated method stub
		// return super.doesContain(x, y);
		return PointInTriangle(_x, _y, this.l1.getX1(), this.l1.getY1(),
				this.l1.getX2(), this.l1.getY2(), this.l2.getX2(),
				this.l2.getY2());
	}

	@Override
	public void Draw() {
		// TODO Auto-generated method stub
		// super.Draw();
		l1.Draw();
		l2.Draw();
		l3.Draw();
		this.setC(l1.getColor());
	}
	@Override
	public void editDuplicateColor(Color co, int idx) {
		// TODO Auto-generated method stub
//		super.editDuplicate(co, idx);
		Triangle r = new Triangle(l1.getX1(), l1.getY1(), l1.getX2(), l1.getY2(), l2.getX2(), l2.getY2(), co);
		r.Draw();
		Memory.shapes.add(r);
	}
	
	
	
	public Element sendNodeForXml() {

		// DocumentBuilderFactory docf = DocumentBuilderFactory.newInstance();

		// DocumentBuilder docb = docf.newDocumentBuilder();
		// Document doc = docb.newDocument();

		Element newshape = xmlHandle.doc.createElement("Shape");
		newshape.setAttribute("id", String.valueOf(getID()));

		Element typenode = xmlHandle.doc.createElement("type");
		typenode.appendChild(xmlHandle.doc.createTextNode(String
				.valueOf("Triangle")));
		Element currentid = xmlHandle.doc.createElement("id");
		currentid.appendChild(xmlHandle.doc.createTextNode(String
				.valueOf(getID())));

		Element xx1 = xmlHandle.doc.createElement("x1");
		Element yy1 = xmlHandle.doc.createElement("y1");
		Element xx2 = xmlHandle.doc.createElement("x2");
		Element yy2 = xmlHandle.doc.createElement("y2");
		Element xx3 = xmlHandle.doc.createElement("x3");
		Element yy3 = xmlHandle.doc.createElement("y3");

		xx1.appendChild(xmlHandle.doc.createTextNode(String.valueOf(getx1())));
		yy1.appendChild(xmlHandle.doc.createTextNode(String.valueOf(gety1())));
		xx2.appendChild(xmlHandle.doc.createTextNode(String.valueOf(getx2())));
		yy2.appendChild(xmlHandle.doc.createTextNode(String.valueOf(gety1())));
		xx3.appendChild(xmlHandle.doc.createTextNode(String.valueOf(getx3())));
		yy3.appendChild(xmlHandle.doc.createTextNode(String.valueOf(gety3())));

		Element Color = xmlHandle.doc.createElement("Color");
		Color.appendChild(xmlHandle.doc.createTextNode(String
				.valueOf(getColor().getRGB())));
		newshape.appendChild(Color);

		newshape.appendChild(currentid);
		newshape.appendChild(typenode);
		newshape.appendChild(xx1);
		newshape.appendChild(yy1);
		newshape.appendChild(xx2);
		newshape.appendChild(yy2);

		newshape.appendChild(xx3);
		newshape.appendChild(yy3);

		return newshape;

	}

	public void createMyJson() {

		JSONObject obj = new JSONObject();
		obj.put("id", String.valueOf(getID()));
		obj.put("type", "Triangle");
		obj.put("x1", String.valueOf(getx1()));
		obj.put("y1", String.valueOf(gety1()));
		obj.put("x2", String.valueOf(getx2()));
		obj.put("y2", String.valueOf(gety2()));
		obj.put("x3", String.valueOf(getx3()));
		obj.put("y3", String.valueOf(gety3()));

		obj.put("Color", String.valueOf(getColor().getRGB()));

		JsonHandle.allShapes.add(obj);

	}

	
	
	
	
}
