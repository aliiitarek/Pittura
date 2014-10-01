import java.awt.Color;

import org.json.simple.JSONObject;
import org.w3c.dom.Element;


public class Line extends Shape{
	private int x1 , y1 , x2 , y2;
	private Color c;
	public Line(){
		
	}
	public Line(int _x1 , int _y1 , int _x2 , int _y2 , Color _c){
		x1 = _x1;
		y1 = _y1;
		x2 = _x2;
		y2 = _y2;
		c = _c;
		
		setID(6);
	}
	
	@Override
	public boolean doesContain(int _x, int _y) {
		// TODO Auto-generated method stub
//		return super.doesContain(x, y);
		double slope = (this.y2-this.y1)/(this.x2-this.x1);
		if((_y-this.y1-(slope*(_x-this.x1)) <10) && ((_y-this.y1-(slope*(_x-this.x1))) >-10)) return true;
		else return false;
	}
	
	public int getX1() {
		return x1;
	}
	public int getY1() {
		return y1;
	}
	public int getX2() {
		return x2;
	}
	public int getY2() {
		return y2;
	}
	
	public Color getColor() {
		return c;
	}
	
	public void setC(Color c) {
		this.c = c;
	}
	@Override
	public void Draw() {
		// TODO Auto-generated method stub
//		super.Draw();
		Panel.g.setColor(c);
		Panel.g.drawLine(x1, y1, x2, y2);
		Panel.g.setColor(Panel.currentDrawingColor);
	}
	@Override
	public void editDuplicateColor(Color co, int idx) {
		// TODO Auto-generated method stub
//		super.editDuplicate(co, idx);
		Line r = new Line(x1, y1, x2, y2, co);
		r.Draw();
		Memory.shapes.add(r);
	}
	public void moveBonus(int xS, int yS, int xE, int yE, boolean isFinal) {
		// TODO Auto-generated method stub
//		super.move(xS, yS, xE, yE, isFinal);
		int D = (int)Math.pow(xS-xE,2) + (int)Math.pow(yS-yE, 2);
		double m = (yE-yS)/(xE-xS);
		double a = ((m*m)+1);
		
		double V1 = y1-(m*x1);
		int U1 = (x1*x1)+(y1*y1);
		double b1 = (2*m*V1)-(2*x1)-(2*y1*m);
		double cc1 = (U1-(2*y1*V1)+(V1*V1)-D);
		double XN1 = ((-1*b1)+(Math.sqrt((b1*b1)-(4*a*cc1)))/(2*a));
		double YN1 = (m*XN1)+(V1);
		
		double V2 = y2-(m*x2);
		int U2 = (x2*x2)+(y2*y2);
		double b2 = (2*m*V2)-(2*x2)-(2*y2*m);
		double cc2 = (U2-(2*y2*V2)+(V2*V2)-D);
		double XN2 = ((-1*b2)+(Math.sqrt((b2*b2)-(4*a*cc2)))/(2*a));
		double YN2 = (m*XN2)+(V2);
		
		if(!isFinal){
			Line r;
			if( (((XN1-x1)>=0 && (xE-xS)>=0)||((XN1-x1)<0 && (xE-xS)<0)) && ((YN1-y1)>=0 && (yE-yS)>=0)||((YN1-y1)<0 && (yE-yS)<0) ){
				if( (((XN2-x2)>=0 && (xE-xS)>=0)||((XN2-x2)<0 && (xE-xS)<0)) && ((YN2-y2)>=0 && (yE-yS)>=0)||((YN2-y2)<0 && (yE-yS)<0) ){
					r = new Line((int)XN1, (int)YN1, (int)XN2, (int)YN2, c);
				}else{
					XN2 = ((-1*b2)-(Math.sqrt((b2*b2)-(4*a*cc2)))/(2*a));
					YN2 = (m*XN2)+(V2);
					r = new Line((int)XN1, (int)YN1, (int)XN2, (int)YN2, c);
				}
			}else{
				XN1 = ((-1*b1)-(Math.sqrt((b1*b1)-(4*a*cc1)))/(2*a));
				YN1 = (m*XN1)+(V1);
				if( (((XN2-x2)>=0 && (xE-xS)>=0)||((XN2-x2)<0 && (xE-xS)<0)) && ((YN2-y2)>=0 && (yE-yS)>=0)||((YN2-y2)<0 && (yE-yS)<0) ){
					r = new Line((int)XN1, (int)YN1, (int)XN2, (int)YN2, c);
				}else{
					XN2 = ((-1*b2)-(Math.sqrt((b2*b2)-(4*a*cc2)))/(2*a));
					YN2 = (m*XN2)+(V2);
					r = new Line((int)XN1, (int)YN1, (int)XN2, (int)YN2, c);
				}
			}
			r.Draw();
		}else{
			if( (((XN1-x1)>=0 && (xE-xS)>=0)||((XN1-x1)<0 && (xE-xS)<0)) && ((YN1-y1)>=0 && (yE-yS)>=0)||((YN1-y1)<0 && (yE-yS)<0) ){
				if( (((XN2-x2)>=0 && (xE-xS)>=0)||((XN2-x2)<0 && (xE-xS)<0)) && ((YN2-y2)>=0 && (yE-yS)>=0)||((YN2-y2)<0 && (yE-yS)<0) ){
					x1 = (int)XN1;
					y1 = (int)YN1;
					x2 = (int)XN2;
					y2 = (int)YN2;
				}else{
					XN2 = ((-1*b2)-(Math.sqrt((b2*b2)-(4*a*cc2)))/(2*a));
					YN2 = (m*XN2)+(V2);
					x1 = (int)XN1;
					y1 = (int)YN1;
					x2 = (int)XN2;
					y2 = (int)YN2;
				}
			}else{
				XN1 = ((-1*b1)-(Math.sqrt((b1*b1)-(4*a*cc1)))/(2*a));
				YN1 = (m*XN1)+(V1);
				if( (((XN2-x2)>=0 && (xE-xS)>=0)||((XN2-x2)<0 && (xE-xS)<0)) && ((YN2-y2)>=0 && (yE-yS)>=0)||((YN2-y2)<0 && (yE-yS)<0) ){
					x1 = (int)XN1;
					y1 = (int)YN1;
					x2 = (int)XN2;
					y2 = (int)YN2;
				}else{
					XN2 = ((-1*b2)-(Math.sqrt((b2*b2)-(4*a*cc2)))/(2*a));
					YN2 = (m*XN2)+(V2);
					x1 = (int)XN1;
					y1 = (int)YN1;
					x2 = (int)XN2;
					y2 = (int)YN2;
				}
			}
			this.Draw();
		}
	}
	
	@Override
	public void move(int xS, int yS, int xE, int yE, boolean isFinal) {
		// TODO Auto-generated method stub
//		super.move(xS, yS, xE, yE, isFinal);
	
	}
	
	@Override
	public void resize(int xS, int yS, int xE, int yE, boolean isFinal) {
		// TODO Auto-generated method stub
//		super.resize(xS, yS, xE, yE, isFinal);
		double m = (y2-y1)/(x2-x1);
		int X = (int)(( (xE)-(m*yE)+(m*m*x1)+(m*y1) )/((m*m)+1) );
		int Y = (int)((m*X)-(m*x1)+(y1));
		int Dist1 = (int)(Math.pow((X-x1), 2)+Math.pow(Y-y1, 2));
		int Dist2 = (int)(Math.pow((X-x2), 2)+Math.pow(Y-y2, 2));
		if(isFinal){
			if(Dist1<Dist2){
				x1 = X;
				y1 = Y;
			}else{
				x2 = X;
				y2 = Y;
			}
			this.Draw();
		}else{
			Line r;
			if(Dist1<Dist2){
				r = new Line(X, Y, x2, y2, c);
			}else{
				r = new Line(X, Y, x1, y1, c);
			}
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
				.valueOf("Line")));
		Element currentid = xmlHandle.doc.createElement("id");
		currentid.appendChild(xmlHandle.doc.createTextNode(String
				.valueOf(getID())));

		Element x1 = xmlHandle.doc.createElement("x1");
		Element y1 = xmlHandle.doc.createElement("y1");
		Element x2 = xmlHandle.doc.createElement("x2");
		Element y2 = xmlHandle.doc.createElement("y2");

		x1.appendChild(xmlHandle.doc.createTextNode(String.valueOf(getX1())));
		y1.appendChild(xmlHandle.doc.createTextNode(String.valueOf(getY1())));
		x2.appendChild(xmlHandle.doc.createTextNode(String.valueOf(getX2())));
		y2.appendChild(xmlHandle.doc.createTextNode(String.valueOf(getY2())));

		Element Color = xmlHandle.doc.createElement("Color");
		Color.appendChild(xmlHandle.doc
				.createTextNode(String.valueOf(getColor().getRGB()) )  );
		newshape.appendChild(Color);
		newshape.appendChild(currentid);
		newshape.appendChild(typenode);
		newshape.appendChild(x1);
		newshape.appendChild(y1);
		newshape.appendChild(x2);
		newshape.appendChild(y2);

		return newshape;

	}

	public void createMyJson() {

		JSONObject obj = new JSONObject();
		obj.put("id", String.valueOf(getID()));
		obj.put("type", "Line");
		obj.put("x1", String.valueOf(getX1()));
		obj.put("y1", String.valueOf(getY1()));
		obj.put("x2", String.valueOf(getX2()));
		obj.put("y2", String.valueOf(getY2()));
		obj.put("Color", String.valueOf(getColor().getRGB()));

		JsonHandle.allShapes.add(obj);

	}
	
	
	
}





