import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel extends JFrame {
	private JButton rectangle;
	private JButton circle;
	private JButton ellipse;
	private JButton square;
	private JButton line;
	private JButton triangle;
	Canvas drawArea;
	public static Graphics g;

	private JButton loadJson;
	private JButton saveJson;
	private JButton loadXml;
	private JButton saveXml;
	private JButton delete;

	public boolean stateForLine = false;
	public static JButton ShapeX;

	public Class_Loader cl;

	// ---------------------------------------------------

	private JButton getClass;

	// ---------------------------------------------------

	public static boolean editColorRunning = false;
	// ------------------------------------------------

	public static boolean selectPressed = false;
	public static int xSelect = 0, ySelect = 0;

	// ------------------------------------------------

	public static int x_Coordinate = 0;
	public static int y_Coordinate = 0; // these varibales are used for drawing
										// the line
										// they hold the coordinates of the two
										// endpoint of a line
	public static int x_Coordinate2 = 0;
	public static int y_Cooridinate2 = 0;

	// ------------------------------------------------

	public static int x_center = 0;
	public static int y_center = 0; // these variables hold the center of the
									// shape being drawn However they are
									// only related to the ellipse , circle ,
									// rectangle and square .

	public static int shape_width = 0;
	public static int shape_height = 0;

	// -------------------------------------------------

	public static int one_xCoordinate = 0;
	public static int one_yCoordinate = 0;
	public static int two_xCoordinate = 0; // these variables hold the
											// co-ordiantes of the three points
	public static int two_yCoordinate = 0; // of the current triangle being
											// drawn .
	public static int three_xCoordiante = 0;
	public static int three_yCoordinate = 0;

	public boolean stateForTriangle_1 = false;
	public boolean stateForTriangle_2 = false;

	// --------------------------------------------------

	public static Color currentDrawingColor;
	public static int currentDrawingObject;
	public static Color currentEditingColor = Color.cyan;

	// ---------------------------------------------------

	public static boolean movePressed = false;
	public static boolean sizePressed = false;

	// ---------------------------------------------------

	public int ss;
	public int mm;

	// ids for all supported shapes
	// 1 for ellipse
	// 2 for circle
	// 3 for rectangle
	// 4 for square
	// 5 for triangle
	// 6 for line

	// colors in the color panel

	private JButton redColor;
	private JButton blueColor;
	private JButton greenColor;
	private JButton yellowColor;
	private JButton orangeColor;
	private JButton pinkColor;
	private JButton blackColor;
	private JButton ReDo;
	private JButton UnDo;
	private JButton EditColor;
	private JButton Select;
	private JButton EditPosition;
	private JButton EditSize;

	public Panel() {

		super("paint");
		setLayout(new FlowLayout());
		setSize(300, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.weightx = 3;
		c.weighty = 1;

		rectangle = new JButton();
		circle = new JButton();
		ellipse = new JButton();
		square = new JButton();
		triangle = new JButton();
		line = new JButton();

		getClass = new JButton("getClass");

		ReDo = new JButton("redo");
		UnDo = new JButton("undo");

		EditColor = new JButton("Change Color");
		EditPosition = new JButton("Move");
		EditSize = new JButton("ReSize");

		Select = new JButton("Select");

		loadJson = new JButton("loadjson");
		saveJson = new JButton("saveJson");
		loadXml = new JButton("loadXml");
		saveXml = new JButton("saveXml");
		delete = new JButton("delete");
		ShapeX = new JButton();
		add(delete);

		// rectangle.setBorderPainted(false);
		// rectangle.setContentAreaFilled(false);

		File rectangleImage = new File("rectangle.jpg");
		File squareImage = new File("square.jpg");
		File triangleImage = new File("triangle.jpg");
		File circleImage = new File("circle.jpg");
		File lineImage = new File("line.png");
		File ellipseImage = new File("ellipse.jpg");

		if (rectangleImage.exists())
			rectangle.setIcon(new ImageIcon((new ImageIcon("rectangle.jpg"))
					.getImage().getScaledInstance(100, 100,
							java.awt.Image.SCALE_SMOOTH)));
		else
			rectangle.setText("rectangle");

		if (circleImage.exists())
			circle.setIcon(new ImageIcon((new ImageIcon("circle.jpg"))
					.getImage().getScaledInstance(100, 100,
							java.awt.Image.SCALE_SMOOTH)));
		else
			circle.setText("circle");

		if (triangleImage.exists())
			triangle.setIcon(new ImageIcon((new ImageIcon("triangle.jpg"))
					.getImage().getScaledInstance(100, 100,
							java.awt.Image.SCALE_SMOOTH)));
		else
			triangle.setText("triangle");

		if (lineImage.exists())
			line.setIcon(new ImageIcon((new ImageIcon("line.png")).getImage()
					.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
		else
			line.setText("line");

		if (ellipseImage.exists())
			ellipse.setIcon(new ImageIcon((new ImageIcon("ellipse.jpg"))
					.getImage().getScaledInstance(100, 100,
							java.awt.Image.SCALE_SMOOTH)));
		else
			ellipse.setText("ellipse");

		if (squareImage.exists())
			square.setIcon(new ImageIcon((new ImageIcon("square.jpg"))
					.getImage().getScaledInstance(100, 100,
							java.awt.Image.SCALE_SMOOTH)));
		else
			square.setText("square");

		Dimension me = new Dimension();
		me.height = 100;
		me.width = 100;
		circle.setSize(me);

		c.gridx = 0;
		c.gridy = 0;
		// c.gridwidth = 1;
		add(rectangle, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		// c.gridwidth = 1;
		add(circle, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		// c.gridwidth = 1;
		add(ellipse, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		// c.gridwidth = 1;
		add(triangle, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		// c.gridwidth = 1;
		add(square, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		// c.gridwidth = 1;
		add(line, c);

		redColor = new JButton("red");
		orangeColor = new JButton("orange");
		greenColor = new JButton("green");
		blueColor = new JButton("blue");
		pinkColor = new JButton("pink");
		yellowColor = new JButton("yellow");
		blackColor = new JButton("black");

		changeColors handleChangeColors = new changeColors();
		blueColor.addActionListener(handleChangeColors);
		redColor.addActionListener(handleChangeColors);
		orangeColor.addActionListener(handleChangeColors);
		blackColor.addActionListener(handleChangeColors);
		pinkColor.addActionListener(handleChangeColors);
		yellowColor.addActionListener(handleChangeColors);
		greenColor.addActionListener(handleChangeColors);

		add(redColor);
		add(blueColor);
		add(greenColor);
		add(pinkColor);
		add(yellowColor);
		add(orangeColor);
		add(blackColor);

		add(loadJson);
		add(saveJson);
		add(saveXml);
		add(loadXml);

		drawArea = new Canvas();
		currentDrawingColor = Color.black;
		currentDrawingObject = 1;
		//
		// 1 for ellipse
		// 2 for circle
		// 3 for rectangle
		// 4 for square
		// 5 for triangle
		// 6 for line

		drawArea.setSize(300, 300);
		drawArea.setBackground(Color.white);
		drawArea.setVisible(true);
		add(drawArea);
		g = drawArea.getGraphics();

		changeShape handleChangeShape = new changeShape();
		line.addActionListener(handleChangeShape);
		rectangle.addActionListener(handleChangeShape);
		circle.addActionListener(handleChangeShape);
		triangle.addActionListener(handleChangeShape);
		square.addActionListener(handleChangeShape);
		ellipse.addActionListener(handleChangeShape);
		delete.addActionListener(handleChangeShape);

		UnDo.addActionListener(handleChangeShape);
		ReDo.addActionListener(handleChangeShape);
		add(UnDo);
		add(ReDo);

		loadJson.addActionListener(handleChangeShape);
		saveJson.addActionListener(handleChangeShape);
		saveXml.addActionListener(handleChangeShape);
		loadXml.addActionListener(handleChangeShape);

		EditColor.addActionListener(handleChangeShape);
		add(EditColor);
		EditPosition.addActionListener(handleChangeShape);
		add(EditPosition);
		EditSize.addActionListener(handleChangeShape);
		add(EditSize);

		Select.addActionListener(handleChangeShape);
		add(Select);

		getClass.addActionListener(handleChangeShape);
		add(getClass);

		ShapeX.addActionListener(handleChangeShape);
		drawUsingMouse mainDrawing = new drawUsingMouse();
		drawArea.addMouseListener(mainDrawing);

		handleMotion dd = new handleMotion();

		drawArea.addMouseMotionListener(dd);

		triangle.setBorder(null);
		square.setBorder(null);
		line.setBorder(null);
		ellipse.setBorder(null);
		rectangle.setBorder(null);
		circle.setBorder(null);

		redColor.setBorder(null);
		blueColor.setBorder(null);
		blackColor.setBorder(null);
		greenColor.setBorder(null);
		orangeColor.setBorder(null);
		pinkColor.setBorder(null);
		yellowColor.setBorder(null);

	}

	public class changeShape implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == circle) {

				circle.setBorder(BorderFactory.createLoweredBevelBorder());
				triangle.setBorder(null);
				square.setBorder(null);
				line.setBorder(null);
				ellipse.setBorder(null);
				rectangle.setBorder(null);

				currentDrawingObject = 2;

			} else if (e.getSource() == triangle) {
				triangle.setBorder(BorderFactory.createLoweredBevelBorder());
				circle.setBorder(null);
				square.setBorder(null);
				line.setBorder(null);
				ellipse.setBorder(null);
				rectangle.setBorder(null);

				currentDrawingObject = 5;
			} else if (e.getSource() == square) {
				square.setBorder(BorderFactory.createLoweredBevelBorder());
				triangle.setBorder(null);
				circle.setBorder(null);
				line.setBorder(null);
				ellipse.setBorder(null);
				rectangle.setBorder(null);

				currentDrawingObject = 4;
			} else if (e.getSource() == rectangle) {
				rectangle.setBorder(BorderFactory.createLoweredBevelBorder());
				triangle.setBorder(null);
				square.setBorder(null);
				line.setBorder(null);
				ellipse.setBorder(null);
				circle.setBorder(null);

				currentDrawingObject = 3;
			} else if (e.getSource() == ellipse) {
				ellipse.setBorder(BorderFactory.createLoweredBevelBorder());
				triangle.setBorder(null);
				square.setBorder(null);
				line.setBorder(null);
				circle.setBorder(null);
				rectangle.setBorder(null);

				currentDrawingObject = 1;
			} else if (e.getSource() == line) {
				line.setBorder(BorderFactory.createLoweredBevelBorder());
				triangle.setBorder(null);
				square.setBorder(null);
				circle.setBorder(null);
				ellipse.setBorder(null);
				rectangle.setBorder(null);

				currentDrawingObject = 6;
			} else if (e.getSource() == ReDo) {
				Memory.redo();
			} else if (e.getSource() == UnDo) {
				Memory.undo();
			} else if (e.getSource() == EditColor) {
				if (selectPressed) {
					JFrame guiFrame = new JFrame();
					currentEditingColor = JColorChooser.showDialog(guiFrame,
							"Pick a Color", Color.BLACK);
					Editing.EditColor(xSelect, ySelect);
					selectPressed = false;
					Select.setBorder(null);
				} else {
					JOptionPane.showMessageDialog(null,
							"please press the select button", "warning",
							JOptionPane.WARNING_MESSAGE);
				}
			} else if (e.getSource() == EditPosition) {
				movePressed = true;
				if (selectPressed) {
					Select.setBorder(null);
					selectPressed = false;
				} else {
					JOptionPane.showMessageDialog(null,
							"please press the select button", "warning",
							JOptionPane.WARNING_MESSAGE);
				}
			} else if (e.getSource() == EditSize) {
				sizePressed = true;
				if (selectPressed) {
					Select.setBorder(null);
					selectPressed = false;
				} else {
					JOptionPane.showMessageDialog(null,
							"please press the select button", "warning",
							JOptionPane.WARNING_MESSAGE);
				}
			} else if (e.getSource() == Select) {
				selectPressed = true;
				Select.setBorder(BorderFactory.createLoweredBevelBorder());
				JOptionPane.showMessageDialog(null, "please select a Shape",
						"warning", JOptionPane.WARNING_MESSAGE);

			} else if (e.getSource() == getClass) {
				JFileChooser jfc = new JFileChooser();
				jfc.showDialog(null, "Please Select the File");
				jfc.setVisible(true);
				File filename = jfc.getSelectedFile();
				// System.out.println("File name " +
				// filename.getAbsolutePath());
				try {
					String f = filename.getCanonicalPath();
					cl = new Class_Loader(f);
					add(Panel.ShapeX);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					// e1.printStackTrace();

				}
			}

			else if (e.getSource() == saveJson) {

				JsonHandle.createJsonfile(Memory.shapes);

			} else if (e.getSource() == saveXml) {
				xmlHandle.createXml(Memory.shapes);
			} else if (e.getSource() == loadJson) {

				g.clearRect(0, 0, 300, 300);
				Memory.shapes = JsonHandle.loadJsonFile();

			} else if (e.getSource() == loadXml) {
				g.clearRect(0, 0, 300, 300);
				Memory.shapes = xmlHandle.readXml();

			} else if (e.getSource() == delete) {

				if (selectPressed) {
					Select.setBorder(null);
					selectPressed = false;
					Editing.deleteObject(xSelect, ySelect);
				} else {
					JOptionPane.showMessageDialog(null,
							"please press the select button", "warning",
							JOptionPane.WARNING_MESSAGE);
				}

			}

			else if (e.getSource() == ShapeX) {
				currentDrawingObject = 10;

			}

		}

	}

	private class changeColors implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == redColor) {

				if (editColorRunning == false) {
					currentDrawingColor = Color.red;
					g.setColor(currentDrawingColor);

					redColor.setBorder(BorderFactory.createLoweredBevelBorder());
					blueColor.setBorder(null);
					blackColor.setBorder(null);
					greenColor.setBorder(null);
					orangeColor.setBorder(null);
					pinkColor.setBorder(null);
					yellowColor.setBorder(null);
				} else {
					currentEditingColor = Color.red;
				}

			} else if (e.getSource() == blueColor) {

				if (editColorRunning == false) {
					currentDrawingColor = Color.blue;
					g.setColor(currentDrawingColor);

					redColor.setBorder(null);
					blueColor.setBorder(BorderFactory
							.createLoweredBevelBorder());
					blackColor.setBorder(null);
					greenColor.setBorder(null);
					orangeColor.setBorder(null);
					pinkColor.setBorder(null);
					yellowColor.setBorder(null);

				} else {
					currentEditingColor = Color.blue;
				}

			} else if (e.getSource() == yellowColor) {

				if (editColorRunning == false) {

					currentDrawingColor = Color.yellow;
					g.setColor(currentDrawingColor);

					redColor.setBorder(null);
					blueColor.setBorder(null);
					blackColor.setBorder(null);
					greenColor.setBorder(null);
					orangeColor.setBorder(null);
					pinkColor.setBorder(null);
					yellowColor.setBorder(BorderFactory
							.createLoweredBevelBorder());

				} else {
					currentEditingColor = Color.yellow;
				}

			} else if (e.getSource() == greenColor) {

				if (editColorRunning == false) {

					currentDrawingColor = Color.green;
					g.setColor(currentDrawingColor);

					redColor.setBorder(null);
					blueColor.setBorder(null);
					blackColor.setBorder(null);
					greenColor.setBorder(BorderFactory
							.createLoweredBevelBorder());
					orangeColor.setBorder(null);
					pinkColor.setBorder(null);
					yellowColor.setBorder(null);

				} else {
					currentEditingColor = Color.green;
				}

			} else if (e.getSource() == pinkColor) {

				if (editColorRunning == false) {

					currentDrawingColor = Color.pink;
					g.setColor(currentDrawingColor);

					redColor.setBorder(null);
					blueColor.setBorder(null);
					blackColor.setBorder(null);
					greenColor.setBorder(null);
					orangeColor.setBorder(null);
					pinkColor.setBorder(BorderFactory
							.createLoweredBevelBorder());
					yellowColor.setBorder(null);

				} else {
					currentEditingColor = Color.pink;
				}

			} else if (e.getSource() == blackColor) {

				if (editColorRunning == false) {

					currentDrawingColor = Color.black;
					g.setColor(currentDrawingColor);

					redColor.setBorder(null);
					blueColor.setBorder(null);
					blackColor.setBorder(BorderFactory
							.createLoweredBevelBorder());
					greenColor.setBorder(null);
					orangeColor.setBorder(null);
					pinkColor.setBorder(null);
					yellowColor.setBorder(null);

				} else {
					currentEditingColor = Color.black;
				}

			} else {

				if (editColorRunning == false) {

					currentDrawingColor = Color.orange;
					g.setColor(currentDrawingColor);

					redColor.setBorder(null);
					blueColor.setBorder(null);
					blackColor.setBorder(null);
					greenColor.setBorder(null);
					orangeColor.setBorder(BorderFactory
							.createLoweredBevelBorder());
					pinkColor.setBorder(null);
					yellowColor.setBorder(null);

				} else {
					currentEditingColor = Color.orange;
				}

			}

		}

	}

	private class drawUsingMouse implements MouseListener {

		public void mouseClicked(MouseEvent e) {

		}

		public void mousePressed(MouseEvent e) {
			if (selectPressed) {
				xSelect = e.getX();
				ySelect = e.getY();
				if (!movePressed && !editColorRunning && !sizePressed) {
					boolean notYet = true;
					for (int j = 0; j < Memory.shapes.size() && notYet; j++) {
						if (Memory.shapes.get(j).doesContain(xSelect, ySelect)) {
							notYet = false;
							((Graphics2D) g).setStroke(new BasicStroke(6));

							Memory.shapes.get(j).Draw();
							((Graphics2D) g).setStroke(new BasicStroke(1));
						}
					}
				}
			} else if (movePressed) {
				Editing.Move(xSelect, ySelect, e.getX(), e.getY(), false);
			} else if (sizePressed) {
				Editing.Resize(xSelect, ySelect, e.getX(), e.getY(), false);
			} else if (currentDrawingObject == 1) {

				//
				//
				// JTextField w = new JTextField(5);
				// JTextField l = new JTextField(5);
				//
				// JPanel panel = new JPanel();
				// panel.add(new JLabel("width: "));
				// panel.add(w);
				// panel.add(Box.createHorizontalStrut(15)); // a spacer
				// panel.add(new JLabel("length: "));
				// panel.add(l);
				//
				// int result = JOptionPane.showConfirmDialog(null, panel,
				// "Please Enter width and length Values of ellipse",
				// JOptionPane.OK_CANCEL_OPTION);
				//
				// if (result == JOptionPane.OK_OPTION) {
				// int length = Integer.parseInt(l.getText());
				// int width = Integer.parseInt(w.getText());
				//
				// int _xCoordinate = e.getX();
				// int _yCoordinate = e.getY();
				//
				// System.out.println(_xCoordinate + "  " + _yCoordinate);
				//
				// Shape x = new Ellipse(_xCoordinate, _yCoordinate, width,
				// length, currentDrawingColor);
				// Memory.shapes.add(x);
				// Memory.clearMemory();
				// x.Draw();
				//
				// }

				x_center = e.getX();
				y_center = e.getY();

			} else if (currentDrawingObject == 2) {

				//
				//
				// JTextField r = new JTextField(10);
				//
				// JPanel panel = new JPanel();
				// panel.add(new JLabel("radius: "));
				// panel.add(r);
				// panel.add(Box.createHorizontalStrut(15)); // a spacer
				//
				// int result = JOptionPane.showConfirmDialog(null, panel,
				// "Please Enter width and length Values of ellipse",
				// JOptionPane.OK_CANCEL_OPTION);
				//
				// if (result == JOptionPane.OK_OPTION) {
				// int radius = Integer.parseInt(r.getText());
				//
				// int _xCoordinate = e.getX();
				// int _yCoordinate = e.getY();
				//
				// Shape x = new Circle(_xCoordinate, _yCoordinate, radius,
				// currentDrawingColor);
				// Memory.shapes.add(x);
				// Memory.clearMemory();
				// x.Draw();
				//
				// }

				x_center = e.getX();
				y_center = e.getY();

			} else if (currentDrawingObject == 3) {

				//
				//
				// JTextField w = new JTextField(5);
				// JTextField l = new JTextField(5);
				//
				// JPanel panel = new JPanel();
				// panel.add(new JLabel("width: "));
				// panel.add(w);
				// panel.add(Box.createHorizontalStrut(15)); // a spacer
				// panel.add(new JLabel("length: "));
				// panel.add(l);
				//
				// int result = JOptionPane.showConfirmDialog(null, panel,
				// "Please Enter width and length Values of ellipse",
				// JOptionPane.OK_CANCEL_OPTION);
				//
				// if (result == JOptionPane.OK_OPTION) {
				// int length = Integer.parseInt(l.getText());
				// int width = Integer.parseInt(w.getText());
				//
				// int _xCoordinate = e.getX();
				// int _yCoordinate = e.getY();
				//
				// Shape x = new Rectangle(_xCoordinate, _yCoordinate, width,
				// length, currentDrawingColor);
				// Memory.shapes.add(x);
				// Memory.clearMemory();
				// x.Draw();
				//
				// }
				x_center = e.getX();
				y_center = e.getY();

			} else if (currentDrawingObject == 4) {

				//
				//
				// JTextField l = new JTextField(5);
				//
				// JPanel panel = new JPanel();
				// panel.add(new JLabel("side of square: "));
				// panel.add(l);
				// panel.add(Box.createHorizontalStrut(15)); // a spacer
				//
				// int result = JOptionPane.showConfirmDialog(null, panel,
				// "Please Enter width and length Values of ellipse",
				// JOptionPane.OK_CANCEL_OPTION);
				//
				// if (result == JOptionPane.OK_OPTION) {
				// int side = Integer.parseInt(l.getText());
				//
				// int _xCoordinate = e.getX();
				// int _yCoordinate = e.getY();
				//
				// Shape x = new Square(_xCoordinate, _yCoordinate, side,
				// currentDrawingColor);
				// Memory.shapes.add(x);
				// Memory.clearMemory();
				// x.Draw();
				//
				// }

				x_center = e.getX();
				y_center = e.getY();

			} else if (currentDrawingObject == 5) {

				//
				//
				// if (stateForTriangle_1 == false && stateForTriangle_2 ==
				// false) {
				// one_xCoordinate = e.getX();
				// one_yCoordinate = e.getY();
				// stateForTriangle_1 = true;
				// }
				//
				// else if (stateForTriangle_1 == true
				// && stateForTriangle_2 == false) {
				// two_xCoordinate = e.getX();
				// two_yCoordinate = e.getY();
				// stateForTriangle_2 = true;
				//
				// } else {
				//
				// int three_xCoordinate = e.getX();
				// int three_yCoordinate = e.getY();
				//
				// Shape x = new Triangle(one_xCoordinate, one_yCoordinate,
				// two_xCoordinate, two_yCoordinate,
				// three_xCoordinate, three_yCoordinate,
				// currentDrawingColor);
				//
				// Memory.shapes.add(x);
				// Memory.clearMemory();
				// x.Draw();
				//
				// stateForTriangle_1 = false;
				// stateForTriangle_2 = false;
				//
				// }

				if (stateForTriangle_1 == false && stateForTriangle_2 == false) {
					one_xCoordinate = e.getX();
					one_yCoordinate = e.getY();
					stateForTriangle_1 = true;

				}

			}

			else if (currentDrawingObject == 10) {

				x_center = e.getX();
				y_center = e.getY();

			}

			else {

				x_Coordinate = e.getX();
				y_Coordinate = e.getY();

				System.out.println(x_Coordinate + "  " + y_Coordinate);
				//
				// if (stateForLine == false) {
				// x_Coordinate = e.getX();
				// y_Coordinate = e.getY();
				// stateForLine = true;
				//
				// } else {
				//
				// Shape x = new Line(x_Coordinate, y_Coordinate,
				// two_xCoordinate, two_yCoordinate,
				// currentDrawingColor);
				// x.Draw();
				// stateForLine = false;
				// Memory.shapes.add(x);
				// Memory.clearMemory();
				//
				// }

			}

		}

		public void mouseReleased(MouseEvent e) {
			if (selectPressed) {

			} else if (movePressed) {
				Editing.Move(xSelect, ySelect, e.getX(), e.getY(), true);
				movePressed = false;
			} else if (sizePressed) {
				Editing.Resize(xSelect, ySelect, e.getX(), e.getY(), true);
				selectPressed = false;
			} else if (currentDrawingObject == 1) {

				Shape newEllipse = new Ellipse(x_center, y_center, shape_width,
						shape_height, currentDrawingColor);
				Memory.shapes.add(newEllipse);

			}

			else if (currentDrawingObject == 2) {
				Shape newCircle = new Circle(x_center, y_center, shape_height,
						currentDrawingColor);
				Memory.shapes.add(newCircle);
			}

			else if (currentDrawingObject == 3) {
				Shape newRectangle = new Rectangle(x_center, y_center,
						shape_width, shape_height, currentDrawingColor);
				Memory.shapes.add(newRectangle);
			}

			else if (currentDrawingObject == 4) {
				Shape newSquare = new Square(x_center, y_center, shape_height,
						currentDrawingColor);
				Memory.shapes.add(newSquare);
			}

			else if (currentDrawingObject == 5) {

				if (stateForTriangle_1 == true && stateForTriangle_2 == false) {
					stateForTriangle_2 = true;

				}

				else if (stateForTriangle_1 && stateForTriangle_2) {
					Shape newTriangle = new Triangle(one_xCoordinate,
							one_yCoordinate, two_xCoordinate, two_yCoordinate,
							three_xCoordiante, three_yCoordinate,
							currentDrawingColor);
					stateForTriangle_1 = false;
					stateForTriangle_2 = false;
					Memory.shapes.add(newTriangle);
				}

			}
			// MADE A CHANGE HERE
			else if (currentDrawingObject == 6) {

				Shape newLine = new Line(x_Coordinate, y_Coordinate,
						x_Coordinate2, y_Cooridinate2, currentDrawingColor);
				Memory.shapes.add(newLine);

			}

			else if (currentDrawingObject == 10) {

				try {
					Shape runTimeShape = (Shape) cl.constructors[0]
							.newInstance(x_center, y_center, shape_width,
									shape_height, currentDrawingColor);
					Memory.shapes.add(runTimeShape);

				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		}

		public void mouseEntered(MouseEvent e) {
			// drawArea.setBackground(currentDrawingColor);

		}

		public void mouseExited(MouseEvent e) {
			// drawArea.setBackground(currentDrawingColor);

		}

	}

	private class handleMotion implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub

			// drawArea.setBackground(Color.blue);

			if (movePressed) {
				int xx = e.getX(), yy = e.getY();
				// System.out.println(xSelect+" "+ySelect+" <> "+xx+" "+yy);
				Editing.Move(xSelect, ySelect, xx, yy, false);
			}

			else if (sizePressed) {
				Editing.Resize(xSelect, ySelect, e.getX(), e.getY(), false);
			} else if (currentDrawingObject == 1) {

				Panel.g.clearRect(0, 0, 300, 300);

				Stack<Shape> stk = new Stack<Shape>();
				stk = Memory.shapes;
				for (Shape s : stk) {
					s.Draw();
				}

				int xCurrentPosition = e.getX();
				int yCurrentPosition = e.getY();

				shape_width = Math.abs(xCurrentPosition - x_center);
				shape_height = Math.abs(yCurrentPosition - y_center);

				Shape myEllipse = new Ellipse(x_center, y_center, shape_width,
						shape_height, currentDrawingColor);
				myEllipse.Draw();

			} else if (currentDrawingObject == 2) {

				Panel.g.clearRect(0, 0, 300, 300);

				Stack<Shape> stk = new Stack<Shape>();
				stk = Memory.shapes;
				for (Shape s : stk) {
					s.Draw();
				}

				int xCurrentPosition = e.getX();
				int yCurrentPosition = e.getY();

				shape_width = Math.abs(xCurrentPosition - x_center);
				shape_height = Math.abs(yCurrentPosition - y_center);

				Shape myCircle = new Circle(x_center, y_center, shape_height,
						currentDrawingColor);

				myCircle.Draw();

			} else if (currentDrawingObject == 3) {

				Panel.g.clearRect(0, 0, 300, 300);

				Stack<Shape> stk = new Stack<Shape>();
				stk = Memory.shapes;
				for (Shape s : stk) {
					s.Draw();
				}

				int xCurrentPosition = e.getX();
				int yCurrentPosition = e.getY();

				shape_width = Math.abs(xCurrentPosition - x_center);
				shape_height = Math.abs(yCurrentPosition - y_center);

				Shape myRectangle = new Rectangle(x_center, y_center,
						shape_width, shape_height, currentDrawingColor);
				myRectangle.Draw();

			}

			else if (currentDrawingObject == 4) {

				Panel.g.clearRect(0, 0, 300, 300);

				Stack<Shape> stk = new Stack<Shape>();
				stk = Memory.shapes;
				for (Shape s : stk) {
					s.Draw();
				}

				int xCurrentPosition = e.getX();
				int yCurrentPosition = e.getY();

				shape_width = Math.abs(xCurrentPosition - x_center);
				shape_height = Math.abs(yCurrentPosition - y_center);

				Shape mySquare = new Square(x_center, y_center, shape_height,
						currentDrawingColor);
				mySquare.Draw();

			}

			else if (currentDrawingObject == 5) {

				if (stateForTriangle_1 == true && stateForTriangle_2 == false) {

					Panel.g.clearRect(0, 0, 300, 300);

					Stack<Shape> stk = new Stack<Shape>();
					stk = Memory.shapes;
					for (Shape s : stk) {
						s.Draw();
					}

					two_xCoordinate = e.getX();
					two_yCoordinate = e.getY();

					Shape myline = new Line(one_xCoordinate, one_yCoordinate,
							two_xCoordinate, two_yCoordinate,
							currentDrawingColor);
					myline.Draw();

				}

				else if (stateForTriangle_1 && stateForTriangle_2) {

					Panel.g.clearRect(0, 0, 300, 300);

					Stack<Shape> stk = new Stack<Shape>();
					stk = Memory.shapes;
					for (Shape s : stk) {
						s.Draw();
					}

					Shape triangleBase = new Line(one_xCoordinate,
							one_yCoordinate, two_xCoordinate, two_yCoordinate,
							currentDrawingColor);
					triangleBase.Draw();

					three_xCoordiante = e.getX();
					three_yCoordinate = e.getY();

					Shape side1 = new Line(one_xCoordinate, one_yCoordinate,
							three_xCoordiante, three_yCoordinate,
							currentDrawingColor);
					Shape side2 = new Line(two_xCoordinate, two_yCoordinate,
							three_xCoordiante, three_yCoordinate,
							currentDrawingColor);
					side1.Draw();
					side2.Draw();

				}

			}

			else if (currentDrawingObject == 6) {

				Panel.g.clearRect(0, 0, 300, 300);

				Stack<Shape> stk = new Stack<Shape>();
				stk = Memory.shapes;
				for (Shape s : stk) {
					s.Draw();
				}

				x_Coordinate2 = e.getX();
				y_Cooridinate2 = e.getY();

				Shape myLine = new Line(x_Coordinate, y_Coordinate,
						x_Coordinate2, y_Cooridinate2, currentDrawingColor);
				myLine.Draw();

			}

			else if (currentDrawingObject == 10) {

				Panel.g.clearRect(0, 0, 300, 300);

				Stack<Shape> stk = new Stack<Shape>();
				stk = Memory.shapes;
				for (Shape s : stk) {
					s.Draw();
				}

				int xCurrentPosition = e.getX();
				int yCurrentPosition = e.getY();

				shape_width = Math.abs(xCurrentPosition - x_center);
				shape_height = Math.abs(yCurrentPosition - y_center);
				//
				// Shape mySquare = new Square(x_center, y_center, shape_height,
				// currentDrawingColor);
				// mySquare.Draw();
				//

				try {
					Shape runtimeShape = (Shape) cl.constructors[0]
							.newInstance(x_center, y_center, shape_width,
									shape_height, currentDrawingColor);
					runtimeShape.Draw();

				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

			// drawArea.setBackground(Color.yellow);

		}

	}

}
