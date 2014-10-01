import java.awt.Color;

import org.json.simple.JSONObject;
import org.w3c.dom.Element;

public class Circle extends Ellipse {
	private int x , y , radius;
	private Color c;
	

	public Circle(int _x, int _y, int _width, int _length, Color _color) {
		x = _x;
		y = _y;
		radius = _width;
		c = _color;
		setID(2);
	}
	
	
	public Circle(int _x , int _y , int r , Color _c){
		x = _x;
		y = _y;
		radius = r;
		c = _c;
		setID(2);
	}
	
	public void setC(Color c) {
		this.c = c;
	}
	
	public int getx() {
		return x;
	}

	public int gety() {
		return y;
	}

	public int getWidth() {
		return radius;
	}

	public int getHeight() {
		return radius;
	}
	
	public Color getColor(){
		return c ;
	}
	
	
	
//	@Override
//	public boolean doesContain(int _x, int _y) {
//		// TODO Auto-generated method stub
////		return super.doesContain(x, y);
//		int ans = (int)Math.pow(x, 2) + (int)Math.pow(y, 2);
//		if(((Math.pow(_x-this.x, 2))+(Math.pow(_y-this.y, 2)))<=(Math.pow(radius, 2)))return true;
//		else return false;
//	}
	
	public boolean doesContain(int _x, int _y) {
		// TODO Auto-generated method stub
//		return super.doesContain(x, y);
		if(_x>=this.x && _y>=this.y && _x<=(x+this.radius) && _y<=(y+this.radius)) return true;
		else return false;
	}
	
	@Override
	public void Draw() {
		// TODO Auto-generated method stub
//		super.Draw();
		Panel.g.setColor(c);
		Panel.g.drawOval(x, y, radius, radius);
		Panel.g.setColor(Panel.currentDrawingColor);
	}
	@Override
	public void editDuplicateColor(Color co, int idx) {
		// TODO Auto-generated method stub
//		super.editDuplicate(co, idx);
		Circle r = new Circle(x, y, radius, co);
		r.Draw();
		Memory.shapes.add(r);
	}
	@Override
	public void moveBonus(int xS, int yS, int xE, int yE, boolean isFinal) {
		// TODO Auto-generated method stub
//		super.move(xS, yS, xE, yE, isFinal);
		int D = (int)Math.pow(xS-xE,2) + (int)Math.pow(yS-yE, 2);
		double m = (yE-yS)/(xE-xS);
		double V = y-(m*x);
		int U = (x*x)+(y*y);
		double a = ((m*m)+1);
		double b = (2*m*V)-(2*x)-(2*y*m);
		double cc = (U-(2*y*V)+(V*V)-D);
		double XN = ((-1*b)+(Math.sqrt((b*b)-(4*a*cc)))/(2*a));
		double YN = (m*XN)+(V);
		if(!isFinal){
			Circle r ;
			if( (((XN-x)>=0 && (xE-xS)>=0)||((XN-x)<0 && (xE-xS)<0)) && ((YN-y)>=0 && (yE-yS)>=0)||((YN-y)<0 && (yE-yS)<0) ){
				r = new Circle((int)XN, (int)YN, radius, c);
			}else{
				XN = ((-1*b)-(Math.sqrt((b*b)-(4*a*cc)))/(2*a));
				YN = (m*XN)+(V);
				r = new Circle((int)XN, (int)YN, radius, c);
			}
			r.Draw();
		}else{
			if( (((XN-x)>=0 && (xE-xS)>=0)||((XN-x)<0 && (xE-xS)<0)) && ((YN-y)>=0 && (yE-yS)>=0)||((YN-y)<0 && (yE-yS)<0) ){
				x = (int)XN;
				y = (int)YN;
			}else{
				XN = ((-1*b)-(Math.sqrt((b*b)-(4*a*cc)))/(2*a));
				YN = (m*XN)+(V);
				x = (int)XN;
				y = (int)YN;
			}
			this.Draw();
		}
	}
	
	
	@Override
	public void move(int xS, int yS, int xE, int yE, boolean isFinal) {
		// TODO Auto-generated method stub
//		super.move(xS, yS, xE, yE, isFinal);
		int xN = (int)(xE-(radius/2));
		int yN = (int)(yE-(radius/2));
		if(!isFinal){
			Circle r ;
			r = new Circle(xN, yN, radius, c);
			r.Draw();
		}else{
			x = xN;
			y = yN;
			this.Draw();
		}
	}
	
	
	@Override
	public void resize(int xS, int yS, int xE, int yE, boolean isFinal) {
		// TODO Auto-generated method stub
//		super.resize(xS, yS, xE, yE, isFinal);
		double xC = x+(radius/2);
		double yC = y+(radius/2);
		double ratio = 1;
		double dY = Math.abs(yE-yC);
		double dX = Math.abs(xE-xC);
		if(dY<dX){
			dX = dY;
		}else{
			dY = dX;
		}
		if(isFinal){
			x = (int)(xC-dX);
			y = (int)(yC-dY);
			radius = (int)(dY*2);
			this.Draw();
			Panel.sizePressed = false;
		}else{
			int x1x = (int)(xC-dX);
			int y1y = (int)(yC-dY);
			int r1r = (int)(dY*2);
			
			Circle r = new Circle(x1x, y1y,r1r, c);
			r.Draw();
		}
	}
	
	

	public Element sendNodeForXml() {

		// DocumentBuilderFactory docf = DocumentBuilderFactory.newInstance();

		// DocumentBuilder docb = docf.newDocumentBuilder();
		// Document doc = docb.newDocument();

		Element newshape = xmlHandle.doc.createElement("Shape");
		newshape.setAttribute("id", String.valueOf(getID()));

		Element typenode = xmlHandle.doc.createElement("type");
		typenode.appendChild(xmlHandle.doc.createTextNode(String
				.valueOf("Circle")));
		Element currentid = xmlHandle.doc.createElement("id");
		currentid.appendChild(xmlHandle.doc.createTextNode(String
				.valueOf(getID())));

		Element xcenter = xmlHandle.doc.createElement("xcenter");
		Element ycenter = xmlHandle.doc.createElement("ycenter");
		Element ellwidth = xmlHandle.doc.createElement("width");
		Element ellheight = xmlHandle.doc.createElement("height");

		xcenter.appendChild(xmlHandle.doc.createTextNode(String.valueOf(getx())));
		ycenter.appendChild(xmlHandle.doc.createTextNode(String.valueOf(gety())));
		ellwidth.appendChild(xmlHandle.doc.createTextNode(String
				.valueOf(getWidth())));
		ellheight.appendChild(xmlHandle.doc.createTextNode(String
				.valueOf(getHeight())));

		Element Color = xmlHandle.doc.createElement("Color");
		Color.appendChild(xmlHandle.doc
				.createTextNode(String.valueOf(getColor().getRGB()) )  );
		newshape.appendChild(Color);
		newshape.appendChild(currentid);
		newshape.appendChild(typenode);
		newshape.appendChild(xcenter);
		newshape.appendChild(ycenter);
		newshape.appendChild(ellwidth);
		newshape.appendChild(ellheight);

		return newshape;

	}
	
	public void createMyJson(){

		JSONObject obj = new JSONObject();
		obj.put("id", String.valueOf(getID()));
		obj.put("type", "Circle");
		obj.put("xcenter", String.valueOf(getx()));
		obj.put("ycenter", String.valueOf(gety()));
		obj.put("width", String.valueOf(getWidth()));
		obj.put("height", String.valueOf(getHeight()));
		obj.put("Color", String.valueOf(getColor().getRGB()));

		JsonHandle.allShapes.add(obj);
		
	}
	
	
	
}
