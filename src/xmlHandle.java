import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class xmlHandle {

	public static DocumentBuilderFactory docFactory;
	public static DocumentBuilder docBuilder;
	public static DocumentBuilder docBuilder2;
	public static Document doc;
	public static Document doc2;

	public static void createXml(Stack<Shape> stk) {

		docFactory = DocumentBuilderFactory.newInstance();
		try {
			docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.newDocument();

			Element root = doc.createElement("Shapes");
			doc.appendChild(root);

			for (int i = 0; i < stk.size(); i++) {
				Shape shape = stk.get(i);
				Element currentFormationElement = shape.sendNodeForXml();
				root.appendChild(currentFormationElement);
			}

			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(
					"C:\\files\\file.xml"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Stack<Shape> readXml() {

		Stack<Shape> stk = new Stack<Shape>();
		File xmlFile = new File("c:\\files\\file.xml");
		

		if(xmlFile.exists()){
			System.out.println("ee");
		}
		try {
			docFactory = DocumentBuilderFactory.newInstance();
			docBuilder2 = docFactory.newDocumentBuilder();
			Document doc = docBuilder2.parse(xmlFile);
			doc.getDocumentElement().normalize();

			NodeList shapeList = doc.getElementsByTagName("Shape");

			for (int i = 0; i < shapeList.getLength(); i++) {

				org.w3c.dom.Node currentNode = shapeList.item(i);
				if (currentNode.getNodeType() == Node.ELEMENT_NODE) {

					Element currentElement = (Element) currentNode;
					int id = Integer
							.parseInt(currentElement.getAttribute("id"));

					if (id == 1) {
						String str = currentElement
								.getElementsByTagName("xcenter").item(0)
								.getTextContent();
						int xcenter = Integer.parseInt(str);
						str = currentElement.getElementsByTagName("ycenter")
								.item(0).getTextContent();
						int ycenter = Integer.parseInt(str);
						str = currentElement.getElementsByTagName("width")
								.item(0).getTextContent();
						int width = Integer.parseInt(str);
						str = currentElement.getElementsByTagName("height")
								.item(0).getTextContent();
						int height = Integer.parseInt(str);

						str = currentElement.getElementsByTagName("Color")
								.item(0).getTextContent();
			//			Color color = Color.getColor(str);
						Color color = new Color(Integer.parseInt(str));

						Shape myShape = new Ellipse(xcenter, ycenter, width,
								height, color);
						myShape.Draw();
						
						
						stk.push(myShape);

					} else if (id == 2) {

						String str = currentElement
								.getElementsByTagName("xcenter").item(0)
								.getTextContent();
						int xcenter = Integer.parseInt(str);
						str = currentElement.getElementsByTagName("ycenter")
								.item(0).getTextContent();
						int ycenter = Integer.parseInt(str);
						str = currentElement.getElementsByTagName("width")
								.item(0).getTextContent();
						int width = Integer.parseInt(str);
						str = currentElement.getElementsByTagName("height")
								.item(0).getTextContent();
						int height = Integer.parseInt(str);

						str = currentElement.getElementsByTagName("Color")
								.item(0).getTextContent();
						Color color = new Color(Integer.parseInt(str));

						Shape myShape = new Circle(xcenter, ycenter, height,
								color);
						myShape.Draw();
						stk.push(myShape);

					} else if (id == 3) {
						String str = currentElement
								.getElementsByTagName("xcenter").item(0)
								.getTextContent();
						int xcenter = Integer.parseInt(str);
						str = currentElement.getElementsByTagName("ycenter")
								.item(0).getTextContent();
						int ycenter = Integer.parseInt(str);
						str = currentElement.getElementsByTagName("width")
								.item(0).getTextContent();
						int width = Integer.parseInt(str);
						str = currentElement.getElementsByTagName("height")
								.item(0).getTextContent();
						int height = Integer.parseInt(str);

						str = currentElement.getElementsByTagName("Color")
								.item(0).getTextContent();
						Color color = new Color(Integer.parseInt(str));

						Shape myShape = new Rectangle(xcenter, ycenter, width,
								height, color);

						myShape.Draw();
						stk.push(myShape);

					} else if (id == 4) {

						String str = currentElement
								.getElementsByTagName("xcenter").item(0)
								.getTextContent();
						int xcenter = Integer.parseInt(str);
						str = currentElement.getElementsByTagName("ycenter")
								.item(0).getTextContent();
						int ycenter = Integer.parseInt(str);
						str = currentElement.getElementsByTagName("width")
								.item(0).getTextContent();
						int width = Integer.parseInt(str);
						str = currentElement.getElementsByTagName("height")
								.item(0).getTextContent();
						int height = Integer.parseInt(str);

						str = currentElement.getElementsByTagName("Color")
								.item(0).getTextContent();
						Color color = new Color(Integer.parseInt(str));

						Shape myShape = new Square(xcenter, ycenter, height,
								color);
						myShape.Draw();
						stk.push(myShape);

					} else if (id == 5) {

						String str = currentElement.getElementsByTagName("x1")
								.item(0).getTextContent();
						int x1 = Integer.parseInt(str);
						str = currentElement.getElementsByTagName("y1").item(0)
								.getTextContent();
						int y1 = Integer.parseInt(str);
						str = currentElement.getElementsByTagName("x2").item(0)
								.getTextContent();
						int x2 = Integer.parseInt(str);
						str = currentElement.getElementsByTagName("y2").item(0)
								.getTextContent();
						int y2 = Integer.parseInt(str);

						str = currentElement.getElementsByTagName("x3").item(0)
								.getTextContent();
						int x3 = Integer.parseInt(str);
						str = currentElement.getElementsByTagName("y3").item(0)
								.getTextContent();
						int y3 = Integer.parseInt(str);

						str = currentElement.getElementsByTagName("Color")
								.item(0).getTextContent();
						Color color = new Color(Integer.parseInt(str));

						Shape myShape = new Triangle(x1, y1, x2, y2, x3, y3,
								color);
						
						myShape.Draw();
						stk.push(myShape);

					} else {

						String str = currentElement.getElementsByTagName("x1")
								.item(0).getTextContent();
						int x1 = Integer.parseInt(str);
						str = currentElement.getElementsByTagName("y1").item(0)
								.getTextContent();
						int y1 = Integer.parseInt(str);
						str = currentElement.getElementsByTagName("x2").item(0)
								.getTextContent();
						int x2 = Integer.parseInt(str);
						str = currentElement.getElementsByTagName("y2").item(0)
								.getTextContent();
						int y2 = Integer.parseInt(str);

						str = currentElement.getElementsByTagName("Color")
								.item(0).getTextContent();
						Color color = new Color(Integer.parseInt(str));

						Shape myShape = new Line(x1, y1, x2, y2, color);
						
						myShape.Draw();
						stk.push(myShape);

					}

				}

			}

			return stk;

		} catch (Exception e) {

			System.out.println("err");
			e.printStackTrace();
			return null;
		}

	}

}
