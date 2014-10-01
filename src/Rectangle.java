import java.awt.Color;

import org.json.simple.JSONObject;
import org.w3c.dom.Element;


public class Rectangle extends Shape{
	private int height , width , x , y;
	private Color c;
	public Rectangle(){
		
	}
	public Rectangle(int _x, int _y, int w, int h , Color _c){
		x = _x;
		y = _y;
		width = w;
		height = h;
		c = _c;
		setID(3);
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
		return width;
	}

	public int getHeight() {

		return height;
	}
	
	public Color getColor(){
		return c ;
	}
	
	
	
	@Override
	public boolean doesContain(int _x, int _y) {
		// TODO Auto-generated method stub
//		return super.doesContain(x, y);
		if(_x>=this.x && _y>=this.y && _x<=(x+this.width) && _y<=(y+this.height)) return true;
		else return false;
	}
	
	@Override
	public void Draw() {
		// TODO Auto-generated method stub
//		super.Draw();
		Panel.g.setColor(c);
		Panel.g.drawRect(x, y, width, height);
		Panel.g.setColor(Panel.currentDrawingColor);
	}
	@Override
	public void editDuplicateColor(Color co, int idx) {
		// TODO Auto-generated method stub
//		super.editDuplicate(co, idx);
		Rectangle r = new Rectangle(x, y, width, height, co);
		r.Draw();
		Memory.shapes.add(r);
	}
	@Override
	public void moveBonus(int xS, int yS, int xE, int yE,boolean isFinal) {
		// TODO Auto-generated method stub
//		super.move(xS, yS, xE, yE);
		int D = (int)Math.pow(xS-xE,2) + (int)Math.pow(yS-yE, 2);
		if((xE-xS)==0)return;
		double m = (yE-yS)/(xE-xS);
		if(m==0)return;
		double V = y-(m*x);
		int U = (x*x)+(y*y);
		double a = ((m*m)+1);
		double b = (2*m*V)-(2*x)-(2*y*m);
		double cc = (U-(2*y*V)+(V*V)-D);
		double XN = ((-1*b)+(Math.sqrt((b*b)-(4*a*cc)))/(2*a));
		double YN = (m*XN)+(V);
		if(!isFinal){
			Rectangle r ;
			if( (((XN-x)>=0 && (xE-xS)>=0)||((XN-x)<0 && (xE-xS)<0)) && ((YN-y)>=0 && (yE-yS)>=0)||((YN-y)<0 && (yE-yS)<0) ){
				r = new Rectangle((int)XN, (int)YN, width, height, c);
			}else{
				XN = ((-1*b)-(Math.sqrt((b*b)-(4*a*cc)))/(2*a));
				YN = (m*XN)+(V);
				r = new Rectangle((int)XN, (int)YN, width, height, c);
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
		int xN = (int)(xE-(width/2));
		int yN = (int)(yE-(height/2));
		if(!isFinal){
			Rectangle r ;
			r = new Rectangle(xN, yN, width, height, c);
			r.Draw();
			
		}else{
			this.x = xN;
			this.y = yN;
			this.Draw();
		}
	}
	
	@Override
	public void resize(int xS, int yS, int xE, int yE, boolean isFinal) {
		// TODO Auto-generated method stub
//		super.resize(xS, yS, xE, yE, isFinal);
		double xC = x+(width/2);
		double yC = y+(height/2);
		double ratio = height/width;
		double dY = Math.abs(yE-yC);
		double dX = Math.abs(xE-xC);
		if(dY<dX){
			dX = dY/ratio;
		}else{
			dY = dX*ratio;
		}
		if(isFinal){
			x = (int)(xC-dX);
			y = (int)(yC-dY);
			height = (int)(dY*2);
			width = (int)(dX*2);
			this.Draw();
			Panel.sizePressed = false;
		}else{
			int x1x = (int)(xC-dX);
			int y1y = (int)(yC-dY);
			int h1h = (int)(dY*2);
			int w1w = (int)(dX*2);
			Rectangle r = new Rectangle(x1x, y1y, w1w, h1h, c);
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
				.valueOf("Rectangle")));
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
		obj.put("type", "Rectangle");
		obj.put("xcenter", String.valueOf(getx()));
		obj.put("ycenter", String.valueOf(gety()));
		obj.put("width", String.valueOf(getWidth()));
		obj.put("height", String.valueOf(getHeight()));
		obj.put("Color", String.valueOf(getColor().getRGB()));

		JsonHandle.allShapes.add(obj);
		
		
	}
	
	
	
}


